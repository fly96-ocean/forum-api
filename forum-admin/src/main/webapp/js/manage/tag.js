$(function () {
    $("#jqGrid").Grid({
        url: '../tag/list',
        colModel: [
            {label: '主键', name: 'oId', key: true, hidden: true},
            {label: '名称', name: 'tagTitle', width: 200},
            {label: '描述', name:'tagDescription'},
            {
                label: '状态', name: 'tagStatus',
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
        tag:{
            oId: null,
            tagTitle: null,
            tagDescription: null,
            tagStatus:0
        },
        showList: true,
        title: null,
        isLogin: [],
        ruleValidate: {
            domainTitle: [
                {required: true, message: '名称不能为空', trigger: 'blur'},
            ]
        }
    },
    methods: {
        query: function () {
            vm.reload();
        },
        add: function () {
            vm.showList = false;
            vm.title = "新增";
        },
        update: function (event) {
            var id = getSelectedRow("#jqGrid");
            if (id == null) {
                return;
            }
            vm.showList = false;
            vm.title = "修改";

            vm.getInfo(id);
        },
        saveOrUpdate: function (event) {
            var url = vm.tag.oId == null ? "../tag/save" : "../tag/update";

            Ajax.request({
                type: "POST",
                url: url,
                contentType: "application/json",
                params: JSON.stringify(vm.tag),
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
                    url: "../tag/delete",
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
                    url: "../tag/active",
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
                url: "../tag/info/" + id,
                async: true,
                successCallback: function (r) {
                    vm.tag = r.tag;
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
                postData: {'tagTitle': vm.q.tagTitle},
                page: page
            }).trigger("reloadGrid");
            vm.handleReset('formValidate');
        }
    }
});