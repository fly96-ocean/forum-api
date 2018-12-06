$(function () {
    $("#jqGrid").Grid({
        url: '../report/list?reportDataType=1',
        colModel: [
            {label: '主键', name: 'oId', key: true, hidden: true},
            {label: '评论内容', name: 'reportedComment.commentContent', width: 200},
            {label: '评论人', name: 'reportedComment.userNickName'},
            {label: '评论时间', name: 'reportedComment.commentCreateTime', formatter:
                    function (value) {
                        return transDate(value);
                    }},
            {
                label: '举报内容', name: 'reportType',
                formatter: function (item) {
                    if (item === 0) {
                        return '<span class="label label-warning-light">垃圾广告</span>';
                    }else if (item === 1) {
                        return '<span class="label label-primary">色情低俗</span>';
                    }else if (item === 2) {
                        return '<span class="label label-primary">违法违规</span>';
                    }else if (item === 3) {
                        return '<span class="label label-primary">涉嫌侵权</span>';
                    }else if (item === 4) {
                        return '<span class="label label-primary">人身攻击</span>';
                    }
                }
            },
            {label: '被举报次数', name: 'commentContent.commentReportCnt'},
            {
                label: '帖子状态', name: 'commentContent.commentStatus',
                formatter: function (item) {
                    if (item === 0) {
                        return '<span class="label label-primary">正常</span>';
                    }else if (item === 1) {
                        return '<span class="label label-danger">封禁</span>';
                    }else{
                        return '<span class="label label-danger">未知</span>';
                    }
                }
            },
            {
                label: '举报人', name: 'reportUser.userNickname'
            },
            {
                label: '举报时间', name: 'reportCreateTime', formatter:
                    function (value) {
                        return transDate(value);
                    }
            },
            {
                label: '状态', name: 'reportHandled', formatter: function (item) {
                    if (item === 0) {
                        return '<span class="label label-danger">未处理</span>';
                    }else if (item === 1) {
                        return '<span class="label label-primary">已处理</span>';
                    }else if (item === 2) {
                        return '<span class="label label-default">已忽略</span>';
                    }
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
        report:{
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
            vm.reload();
        },
        handle: function (event) {
            var ids = getSelectedRows("#jqGrid");
            if (ids == null) {
                return;
            }

            confirm('确定要处理选中的记录？', function () {
                Ajax.request({
                    type: "POST",
                    url: "../report/handle",
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

            confirm('确定要忽略选中的记录？', function () {
                Ajax.request({
                    type: "POST",
                    url: "../report/ignore",
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
                url: "../report/info/" + id,
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
                postData: {'reportDataType': 0},
                page: page
            }).trigger("reloadGrid");
            vm.handleReset('formValidate');
        }
    }
});