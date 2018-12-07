$(function () {
    $("#jqGrid").Grid({
        url: '../article/list?articleType=0',
        colModel: [
            {label: '主键', name: 'oId', key: true, hidden: true},
            {label: '帖子标题', name: 'articleTitle', width: 200},
            {label: '发帖人', name: 'userNickName'},
            {
                label: '是否精华', name: 'articlePerfect',
                formatter: function (item) {
                    if (item === 1) {
                        return '<span class="label label-warning-light">精华</span>';
                    }else if (item === 0) {
                        return '<span class="label label-primary">普通</span>';
                    }
                }
            },
            {
                label: '是否置顶', name: 'isStick',
                formatter: function (item) {
                    if (item === 1) {
                        return '<span class="label label-primary">是</span>';
                    }else {
                        return '<span class="label label-default">否</span>';
                    }
                }
            },
            {label: '浏览数', name: 'articleViewCount'},
            {label: '评论数', name: 'articleCommentCount'},
            {label: '点赞数', name: 'articleGoodCnt'},
            {label: '收藏数', name: 'articleCollectCnt'},
            {
                label: '帖子状态', name: 'articleStatus',
                formatter: function (item) {
                    if (item === 0) {
                        return '<span class="label label-primary">正常</span>';
                    }else if (item === 3) {
                        return '<span class="label label-danger">封禁</span>';
                    }else if (item === 2) {
                        return '<span class="label label-info">锁定</span>';
                    }else if(item == 1){
                        return '<span class="label label-inverse">删除</span>';
                    }
                }
            },
            {
                label: '发帖时间', name: 'articleCreateTime', formatter:
                    function (value) {
                    return transDate(value);
                }
            }
        ]
    });

    $('#articleContent').editable({
        inlineMode: false,
        alwaysBlank: true,
        height: '500px', //高度
        minHeight: '200px',
        language: "zh_cn",
        spellcheck: false,
        plainPaste: true,
        enableScript: false,
        imageButtons: ["floatImageLeft", "floatImageNone", "floatImageRight", "linkImage", "replaceImage", "removeImage"],
        allowedImageTypes: ["jpeg", "jpg", "png", "gif"],
        imageUploadURL: '../sys/oss/upload',
        imageUploadParams: {id: "edit"},
        imagesLoadURL: '../sys/oss/queryAll'
    });

    $('#articleContent').on('editable.blur', function (e, editor) {
        vm.article.articleContent = $('#articleContent').editable("getHTML");
        console.log(vm.article.articleContent);
    })
});

var vm = new Vue({
    el: '#rrapp',
    data: {
        q: {
            key: null
        },
        article:{
            oId: null,
            articleTitle: null,
            articleContent: null,
            userNickName: null,
            articlePerfect:0,
            articleViewCount:0,
            articleCommentCount:0,
            articleGoodCnt:0,
            articleCollectCnt:0,
            articleCreateTime:null,
            articleAnonymous: 0,
            articleCommentable: 0,
            articleType: 0,
            articleDomainId: null
        },
        showList: true,
        title: null,
        isLogin: [],
        ruleValidate: {
            articleTitle: [
                {required: true, message: '标题不能为空', trigger: 'blur'},
                {type: 'string', min: 6, message: '标题不能少于6个字', trigger: 'blur'}
            ],
            articleContent: [
                {required: true, message: '内容不能为空', trigger: 'blur'}
            ]
            // articleDomainId: [
            //     {required: true, message: '请选择投放频道', trigger: 'change'}
            // ]
        },
        domains: [],
        point: 0
    },
    methods: {
        query: function () {
            vm.reload();
        },
        add: function () {
            vm.showList = false;
            vm.title = "新增";
            this.getArticleDomains();
        },
        update: function (event) {
            var id = getSelectedRow("#jqGrid");
            if (id == null) {
                return;
            }
            vm.showList = false;
            vm.title = "修改";

            vm.getInfo(id);
            this.getArticleDomains();
        },
        saveOrUpdate: function (event) {
            var url = vm.article.oId == null ? "../article/save" : "../article/update";

            Ajax.request({
                type: "POST",
                url: url,
                contentType: "application/json",
                params: JSON.stringify(vm.article),
                successCallback: function () {
                    alert('操作成功', function (index) {
                        vm.reload();
                    });
                }
            });
        },
        del: function (event) {
            var ids = getSelectedRows("#jqGrid");
            if (ids == null) {
                return;
            }

            confirm('确定要删除选中的记录？', function () {
                Ajax.request({
                    type: "POST",
                    url: "../article/delete",
                    contentType: "application/json",
                    params: JSON.stringify(ids),
                    successCallback: function () {
                        alert('操作成功', function (index) {
                            vm.reload();
                        });
                    }
                });
            });
        },
        active: function (event) {
            var ids = getSelectedRows("#jqGrid");
            if (ids == null) {
                return;
            }

            confirm('确定要恢复选中的记录？', function () {
                Ajax.request({
                    type: "POST",
                    url: "../article/active",
                    contentType: "application/json",
                    params: JSON.stringify(ids),
                    successCallback: function () {
                        alert('操作成功', function (index) {
                            vm.reload();
                        });
                    }
                });
            });
        },
        setPerfect: function (event) {
            var ids = getSelectedRows("#jqGrid");
            if (ids == null) {
                return;
            }
            this.$Modal.confirm({
                title:'赠送积分',
                render: (h) => {
                    return h('Input', {
                        props: {
                            value: this.value,
                            autofocus: true,
                            placeholder: '请输入赠送的积分数'
                        },
                        on: {
                            input: (val) => {
                                this.point = val;
                            }
                        }
                    })
                },
                onOk: () => {
                    if(this.point!=null && this.point!=undefined){
                        Ajax.request({
                            type: "POST",
                            url: "../article/setPerfect?ids="+ids+"&point="+this.point,
                            contentType: "application/json",
                            // params: JSON.stringify(ids),
                            successCallback: function () {
                                alert('操作成功', function (index) {
                                    vm.reload();
                                });
                            }
                        });
                    }else{
                        alert('赠送积分不能为空');
                        return;
                    }

                }
            })
        },
        cancelPerfect: function (event) {
            var ids = getSelectedRows("#jqGrid");
            if (ids == null) {
                return;
            }

            this.$Modal.confirm({
                title:'取消精华帖并收回赠送积分',
                render: (h) => {
                return h('Input', {
                    props: {
                        value: this.value,
                        autofocus: true,
                        placeholder: '请输入取消赠送的积分数'
                    },
                    on: {
                        input: (val) => {
                        this.point = val;
        }
        }
        })
        },
            onOk: () => {
                if(this.point!=null && this.point!=undefined){
                    Ajax.request({
                        type: "POST",
                        url: "../article/cancelPerfect?ids="+ids+"&point="+this.point,
                        contentType: "application/json",
                        // params: JSON.stringify(ids),
                        successCallback: function () {
                            alert('操作成功', function (index) {
                                vm.reload();
                            });
                        }
                    });
                }else{
                    alert('取消赠送积分不能为空');
                    return;
                }

            }
        })
        },
        stick: function (event) {
            var ids = getSelectedRows("#jqGrid");
            if (ids == null) {
                return;
            }

            confirm('确定要将选中的记录设置置顶？', function () {
                Ajax.request({
                    type: "POST",
                    url: "../article/stick",
                    contentType: "application/json",
                    params: JSON.stringify(ids),
                    successCallback: function () {
                        alert('操作成功', function (index) {
                            vm.reload();
                        });
                    }
                });
            });
        },
        cancelStick: function (event) {
            var ids = getSelectedRows("#jqGrid");
            if (ids == null) {
                return;
            }

            confirm('确定要将选中的记录取消置顶？', function () {
                Ajax.request({
                    type: "POST",
                    url: "../article/cancelStick",
                    contentType: "application/json",
                    params: JSON.stringify(ids),
                    successCallback: function () {
                        alert('操作成功', function (index) {
                            vm.reload();
                        });
                    }
                });
            });
        },
        getInfo: function (id) {
            Ajax.request({
                url: "../article/info/" + id,
                async: true,
                successCallback: function (r) {
                    vm.article = r.article;
                    $('#articleContent').editable('setHTML', vm.article.articleContent);
                }
            });
        },
        handleSubmit: function (name) {
            handleSubmitValidate(this, name, function () {
                vm.saveOrUpdate()
            });
        },
        handleReset: function (name) {
            handleResetForm(this, name);
        },

        reload: function (event) {
            vm.showList = true;
            var page = $("#jqGrid").jqGrid('getGridParam', 'page');
            $("#jqGrid").jqGrid('setGridParam', {
                postData: {'articleTitle': vm.q.articleTitle},
                page: page
            }).trigger("reloadGrid");
            vm.handleReset('formValidate');
        },
        getArticleDomains: function () {
            Ajax.request({
                url: "../domain/queryAll?domainType=0",
                contentType: "application/json",
                async: true,
                successCallback: function (r) {
                    vm.domains = r.list;
                }
            });
        }
    }
});