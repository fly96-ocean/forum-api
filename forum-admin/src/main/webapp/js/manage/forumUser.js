$(function () {
    $("#jqGrid").Grid({
        url: '../forumUser/list',
        colModel: [
            {label: '主键', name: 'oId', key: true, hidden: true},
            {label: '账号', name: 'userName', width: 200},
            {label: '姓名', name: 'userNickname'},

            {label: '所属门店', name: 'userShopName'},
            {label: '发帖数', name: 'userArticleCount'},
            {label: '积分数', name: 'userPoint'},
            {
                label: '状态', name: 'userStatus',
                formatter: function (item) {
                    if (item === 0) {
                        return '<span class="label label-primary">正常</span>';
                    }else if(item == 1){
                        return '<span class="label label-inverse">删除</span>';
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
        article:{
            oId: null,
            userName: null,
            userNickname: null,
            userShopName: null,
            userArticleCount:0,
            userPoint:0,
            userStatus:0
        },
        showList: true,
        title: null,
    },
    methods: {
        query: function () {
            vm.reload();
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
        del: function (event) {
            var ids = getSelectedRows("#jqGrid");
            if (ids == null) {
                return;
            }

            confirm('确定要删除选中的记录？', function () {
                Ajax.request({
                    type: "POST",
                    url: "../forumUser/delete",
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

            confirm('确定要回复选中的记录？', function () {
                Ajax.request({
                    type: "POST",
                    url: "../forumUser/active",
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
                postData: {'userNickname': vm.q.userNickname},
                page: page
            }).trigger("reloadGrid");
            vm.handleReset('formValidate');
        }
    }
});