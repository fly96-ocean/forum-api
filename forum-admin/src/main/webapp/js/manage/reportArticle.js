$(function () {
    $("#jqGrid").Grid({
        url: '../report/list?reportType=0',
        colModel: [
            {label: '主键', name: 'oId', key: true, hidden: true},
            {label: '帖子标题', name: 'articleTitle', width: 200},
            {label: '发帖人', name: 'userNickName'},
            {label: '发帖时间', name: 'userNickName'},
            {
                label: '举报理由', name: 'articlePerfect',
                formatter: function (item) {
                    if (item === 1) {
                        return '<span class="label label-warning-light">精华</span>';
                    }else if (item === 0) {
                        return '<span class="label label-primary">普通</span>';
                    }
                }
            },
            {label: '被举报次数', name: 'articleViewCount'},
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
                label: '举报人', name: 'isStick',
                formatter: function (item) {
                    if (item === 1) {
                        return '<span class="label label-primary">是</span>';
                    }else {
                        return '<span class="label label-default">否</span>';
                    }
                }
            },
            {
                label: '举报时间', name: 'articleCreateTime', formatter:
                    function (value) {
                        return transDate(value);
                    }
            }
        ]
    });
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

    },
    methods: {
        query: function () {
            alert("ddd");
            vm.reload();
        },
        handle: function (event) {
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
        ignore: function (event) {
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

        reload: function (event) {
            vm.showList = true;
            var page = $("#jqGrid").jqGrid('getGridParam', 'page');
            $("#jqGrid").jqGrid('setGridParam', {
                postData: {'reportType': 0},
                page: page
            }).trigger("reloadGrid");
            vm.handleReset('formValidate');
        }
    }
});