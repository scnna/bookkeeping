$(function () {
    RCDOperation.query();
});

/**
 * 前端操作的主体，所有在Vue模块中无法执行的操作，
 * 统统放在该对象内。
 * 该对象采用立即执行匿名函数机制，将局部变量封装在
 * 匿名函数内，只将需要返回的操作和属性作为新的JS对
 * 象返回。这样就实现了良好的数据封装。
 */
var RCDOperation = (function ($) {

    return {
        clear: function () {
            queryApp.queryParam.areaCode = null;
            queryApp.queryParam.areaCodeId = null;
            queryApp.queryParam.nodeType = null;
            queryApp.queryParam.key = null;
            queryApp.queryParam.startTime = null;
            queryApp.queryParam.endTime = null;
            queryApp.queryParam.activeName = null;
        },

        query: function () {
            var url = "/workRecord/pagination";
            $.get(url, function (result) {
                queryApp.records = result.data;
                // queryApp.total = result.data.total;
                // queryApp.pageNum = result.data.pageNum;
                // queryApp.pages = result.data.pages;
            });
        },
        getBatch: function () {
            var date = new Date();
            return date.getFullYear() + "" + (date.getMonth() + 1 ) + "" + date.getDate()
                + date.getHours() + date.getMinutes() + date.getSeconds();
        },
        getDate: function () {
            var date = new Date();
            var month = date.getMonth() + 1;
            if (month < 10) {
                month = "0" + month;
            }
            return date.getFullYear() + "-" + month + "-" + (date.getDate() > 9 ? date.getDate() : "0" + date.getDate())
                + " " + (date.getHours() > 9 ? date.getHours() : "0" + date.getHours() ) + ":"
                + (date.getMinutes() > 9 ? date.getMinutes() : "0" + date.getMinutes());
        },
        getProds: function () {
            var result = '';
            $.ajaxSettings.async = false;
            $.get("/prodCome/getList", null, function (data) {
                if (data.state) {
                    result = data.data;
                } else {
                    return null;
                }
            });
            return result;
            $.ajaxSettings.async = true;
        },
        getMembers: function () {
            var result = '';
            $.ajaxSettings.async = false;
            $.get("/member/getList?type=1", null, function (data) {
                result = data;
            });
            return result;
            $.ajaxSettings.async = true;
        },
        cancel: function () {
            window.parent.location.reload(); //刷新父页面
        },
        change: function () {
            if (updateApp.prodName) {
                updateApp.validator = true;
            }
        }
    }
}(jQuery));
var queryApp = new Vue({
    el: '#app',
    data: {
        search: '',
        records: '',
        queryParam: {}
    },
    methods: {
        query: function () {
            var url = "/workRecord/pagination";
            var _self = this;
            $.get(url, function (result) {
                _self.records = result.data.list;
                _self.total = result.data.total;
                _self.pageNum = result.data.pageNum;
                _self.pages = result.data.pages;
            })
        },
        //分页跳转
        goto: function (index) {
            if (this.pageNum == this.pages || index == this.pageNum) return;
            this.pageNum = index;
            this.$options.methods.query(this.pageNum, this.pageSize);
        },

        //分页跳转
        deleteById: function (id) {
            layer.confirm("确认要删除吗，删除后不能恢复", {title: "删除确认"}, function (index) {
                layer.close(index);
                $.get("/workRecord/delete", {id: id}, function (data) {
                    layer.alert(data, {
                            title: "删除操作",
                            btn: ['确定']
                        },
                        function (index, item) {
                            //layer.close(index);
                            location.reload();
                        });
                });
            });
        },
        /**
         * 打开修改对话框
         * @param
         */
        openUpdateDialog: function (data) {
            // 获取当前位置对应的数据对象
            updateApp.validator = true;
            updateApp.id = data.id;
            updateApp.batch = data.batch; // 节点类型（AJAX获取）
            updateApp.prodName = data.prodId;//地区
            updateApp.memberName = data.memberId;  // 信用代码
            updateApp.price = data.price;  // 信用代码
            updateApp.count = data.comeCount;  // 信用代码
            updateApp.comeTime = data.comeTime;
            updateApp.prods = RCDOperation.getProds();//产品列表
            updateApp.members = RCDOperation.getMembers();//地区
            updateApp.page_type = 3;
            //iframe窗
            layer.open({
                type: 1,
                title: ["修改产品信息", 'background-color:#CCC'],
                shadeClose: true,//点击遮罩关闭层
                area: ['90%', '90%'],
                content: $("#updateApp"),
            });
        },
        /**
         * 打开新增对话框
         * @param
         */
        openAddDialog: function () {
            // 获取当前位置对应的数据对象
            updateApp.validator = false;
            updateApp.id = '';
            updateApp.prodName = ''; // 节点类型（AJAX获取）
            updateApp.count = '';//地区
            updateApp.members = RCDOperation.getMembers();//地区
            updateApp.prods = RCDOperation.getProds();//地区
            updateApp.prod = updateApp.prods[0];//地区
            updateApp.memberName = '';  // 信用代码
            // updateApp.comeTime = "2013-02-15 12:29";  // 信用代码
            updateApp.startTime = RCDOperation.getDate();  // 信用代码
            updateApp.endTime = RCDOperation.getDate();  // 信用代码
            updateApp.page_type = 2;
            layer.open({
                type: 1,
                title: ["新增产品信息", 'background-color:#CCC'],
                area: ['90%', '90%'],
                shadeClose: true,//点击遮罩关闭层
                content: $("#updateApp"),
            });
        },
        openDetailDialog: function (date) {
            // 获取当前位置对应的数据对象
            // 获取当前位置对应的数据对象
            updateApp.validator = true;
            updateApp.id = data.id;
            updateApp.batch = data.batch; // 节点类型（AJAX获取）
            updateApp.prodName = data.prodId;//地区
            updateApp.memberName = data.memberId;  // 信用代码
            updateApp.price = data.price;  // 信用代码
            updateApp.count = data.comeCount;  // 信用代码
            updateApp.comeTime = data.comeTime;
            updateApp.prods = RCDOperation.getProds();//地区
            updateApp.members = RCDOperation.getMembers();//地区
            updateApp.page_type = 1;
            //iframe窗
            layer.open({
                type: 1,
                title: ["修改产品信息", 'background-color:#CCC'],
                shadeClose: true,//点击遮罩关闭层
                area: ['90%', '90%'],
                content: $("#updateApp"),
            });
        },

    }
});
var updateApp = new Vue({
    el: '#updateApp',
    data: {
        id: '',
        prodName: '',
        prod:'',
        startTime: '',
        endTime: '',
        count: '',
        prods: '',
        members: '',
        page_type: "",
        validator: '',
    },
    methods: {
        //保存新增数据
        save: function () {
            var obj = $(this);
            $.ajax({
                url: "/workRecord/insert",
                type: "POST",
                // traditional: true,
                data: {
                    id: this.id,
                    comeId: this.prod.id,
                    currentCount:this.prod.currentCount,
                    prodId:this.prod.prodId,
                    startTime: this.startTime,
                    endTime: this.endTime,
                    memberList: JSON.stringify(this.members),
                    count: this.count,
                },
                success: function (result) {
                    if (result.state) {
                        layer.msg(result.message);
                        setTimeout(function () {
                            window.parent.location.reload(); //刷新父页面
                        }, 1000);
                    } else {
                        layer.msg(result.message);
                    }
                },
                error: function (msg) {
                    $(".notice").html('Error:' + msg);
                }
            })
            return false;
        },


    }
});