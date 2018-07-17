var app = new Vue({
    el: '#app',
    data: {
        message: 'Hello Vue!',
        loginName:'',
        password:'',
        codes:''
    },
    methods: {
        loginIn: function () {
            $.ajax({
                url:"/loginIn",
                type:"post",
                data:{
                    loginName:this.loginName,
                    password:$.md5(this.password)
                },
                dataType:"json",
                success:function(result){
                    if(result.state){
                        alert("登录成功");
                        window.location.href="../metronic/index.html";
                    }else {
                        alert("登录失败");
                    }
                },
                error:function(){
                    alert("请求失败");
                }
            })
        },
        init: function () {
            $.ajax({
                url:"/code/getMemberType",
                type:"post",
                dataType:"json",
                success:function(result){
                    if(result.state){
                        this.codes = result.data;
                    }
                },
                error:function(){
                    alert("请求失败");
                }
            })
        }
    }
    // mounted: function () {
    //     this.init();
    // }
})