$(function () {
    var pageNum = 1;
    var pageSize = 5;
    RCDOperation.query(pageNum, pageSize);
});
var queryApp = new Vue({
    el: '#app',
    data: {
        search: '',
        members: '',
        prodInfos: '',
        total: '',
        pageNum: 1,
        pages: '',
        pageSize: 5,
        queryParam: {}
    },
    methods: {
        query: function (currentPage, pageSize) {
            var url = "/member/pagination?currentPage=" + currentPage + "&pageSize=" + pageSize;
            var _self = this;
            $.get(url, function (result) {
                _self.members = result.data.list;
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

        //单条删除
        deleteById: function (id) {
            layer.confirm("确认要删除吗，删除后不能恢复", {title: "删除确认"}, function (index) {
                layer.close(index);
                $.get("/member/delete", {id: id}, function (data) {
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
            updateApp.id = data.id;
            updateApp.phone = data.phone; // 手机号
            updateApp.name = data.name; // 姓名
            updateApp.page_type = 3;
            //iframe窗
            layer.open({
                type: 1,
                title: ["修改工作组成员", 'background-color:#CCC'],
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
            updateApp.id = '';
            updateApp.phone = ''; // 手机号
            updateApp.name = ''; // 姓名
            updateApp.page_type = 2;
            layer.open({
                type: 1,
                title: ["新增工作组成员", 'background-color:#CCC'],
                area: ['90%', '90%'],
                shadeClose: true,//点击遮罩关闭层
                content: $("#updateApp"),
            });
        },
        openDetailDialog: function (date) {
            // 获取当前位置对应的数据对象
            updateApp.id = data.id;
            updateApp.phone = data.phone; // 手机号
            updateApp.name = data.name; // 姓名
            updateApp.page_type = 1;
            //iframe窗
            layer.open({
                type: 1,
                title: ["查看工作组成员", 'background-color:#CCC'],
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
        phone: '',
        name: '',
        page_type: "",
    },
    methods: {
        //保存新增数据
        save: function () {
            var obj = $(this);
            $.ajax({
                url: "/member/insert",
                type: "POST",
                data: {
                    id: this.id,
                    num: this.num,
                    name: this.name,
                    phone: this.phone
                },
                dataType: "json",
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
        },

        query: function (pageNum, pageSize) {
            var url = "/member/pagination";
            $.get(url, function (result) {
                queryApp.members = result.data;
                // queryApp.total = result.data.total;
                // queryApp.pageNum = result.data.pageNum;
                // queryApp.pages = result.data.pages;
            });
        },
        cancel:function () {
            window.parent.location.reload(); //刷新父页面
        }
    }
}(jQuery));