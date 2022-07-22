<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>书评网数据管理系统</title>
    <link rel="stylesheet" href="/resources/layui/css/layui.css">
    <link rel="icon" href="https://s1.ax1x.com/2022/05/05/OZLOqx.png">
    <style>

        body{
            background-color: #f2f2f2;
        }

        .oa-container {
            /*background-color: white;*/
            position: absolute;
            width: 400px;
            height: 350px;
            top: 50%;
            left: 50%;
            padding: 20px;
            margin-left: -220px; /*两侧外边距共增加40px，所以左移在200px的基础上增加20px*/
            margin-top: -195px; /*和左移同理*/
        }

        #username,#password{
            text-align: center;
            font-size: 24px;
        }

    </style>
</head>
<body>
    <div class="oa-container">
        <h1 style="text-align: center; margin-bottom: 20px">书评网数据管理系统</h1>
        <form class="layui-form">
            <div class="layui-form-item">
                <input type="text" id="username" lay-verify="required" name="username" placeholder="请输入用户名" autocomplete="off" class="layui-input">
            </div>
            <div class="layui-form-item">
                <input type="password" id="password" lay-verify="required" name="password" placeholder="请输入密码" autocomplete="off" class="layui-input">
            </div>
            <div class="layui-form-item">
                <button class="layui-btn layui-btn-fluid" lay-submit lay-filter="login">登陆</button>
            </div>
        </form>
    </div>
<script src="/resources/layui/layui.all.js"></script>
<script>
    layui.form.on("submit(login)",function (formdata){
        layui.$.ajax({
            url:"/management/user/check_login",
            data: formdata.field,
            type: "post",
            dataType: "json",
            success: function(json){
                 console.log(json);
                 if(json.code === "0") {
                     layui.layer.msg("登陆成功"); //弹出提示信息
                     //跳转至指定url
                     window.location.href = "/management/index.html";
                 }else{
                     layui.layer.msg(json.msg);
                 }
            },
            error:function(json){
                console.log(json);
                layui.layer.msg(json.msg);
            }
        });
        return false; //返回true说明表单提交，false说明阻止表单提交
    }) //绑定lay-filter=login的表单提交按钮
</script>
</body>
</html>