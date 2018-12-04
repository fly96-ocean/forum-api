$(function () {
    $("#jqGrid").Grid({
        url: '../domain/list',
        colModel: [
            {label: '主键', name: 'oId', key: true, hidden: true},
            {label: '频道名称', name: 'domainTitle', width: 200},
            {label: '频道类型', name: 'domainType',
                formatter: function (item) {
                    if (item === 0) {
                        return '<span class="label label-warning">普通</span>';
                    }else if(item == 1){
                        return '<span class="label label-primary">视频</span>';
                    }
                }
            },
            {label: '排序', name:'domainSort'},
            {
                label: '频道状态', name: 'domainStatus',
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
        domain:{
            oId: null,
            domainTitle: null,
            domainURI: null,
            domainDescription: null,
            domainType:0,
            domainSort:0,
            domainStatus:0,
            domainSeoTitle:null,
            domainSeoKeywords:null,
            domainSeoDesc:null
        },
        showList: true,
        title: null,
        isLogin: [],
        ruleValidate: {
            domainTitle: [
                {required: true, message: '标题不能为空', trigger: 'blur'},
            ]
        },
        domainTypes: [{key: 0, title: "普通"},{key: 1, title: "视频"}]
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
            var url = vm.domain.oId == null ? "../domain/save" : "../domain/update";

            Ajax.request({
                type: "POST",
                url: url,
                contentType: "application/json",
                params: JSON.stringify(vm.domain),
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
                    url: "../domain/delete",
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
                    url: "../domain/active",
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
                url: "../domain/info/" + id,
                async: true,
                successCallback: function (r) {
                    console.log(r.domain);
                    vm.domain = r.domain;
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
                postData: {'domainTitle': vm.q.domainTitle, 'domainType': vm.q.domainType},
                page: page
            }).trigger("reloadGrid");
            vm.handleReset('formValidate');
        }
    }
});