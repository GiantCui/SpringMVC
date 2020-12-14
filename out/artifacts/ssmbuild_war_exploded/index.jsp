<%--
  Created by IntelliJ IDEA.
  User: cui_a
  Date: 2020/12/3
  Time: 14:17
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
  <meta charset="utf-8">
  <meta http-equiv="X-UA-Compatible" content="IE=edge">
  <meta name="viewport" content="width=device-width, initial-scale=1">
  <title>用户登录</title>
  <!-- Bootstrap 核心 CSS 文件 -->
  <link rel="stylesheet" href="//cdn.bootcss.com/bootstrap/3.3.5/css/bootstrap.min.css">

  <!-- jQuery文件。务必在bootstrap.min.js 之前引入 -->
  <script src="//cdn.bootcdn.net/ajax/libs/jquery/3.5.1/jquery.js"></script>
  <script>
    <%-- 校验用户名 --%>
    function checkId(){
      $.post({
        url:"${pageContext.request.contextPath}/checkUser",
        data:{"userid":$("#userid").val()},
        success:function (data){
          if(data === "success"){
            let cls = "glyphicon glyphicon-ok form-control-feedback";
            $("#userInfo").removeClass();
            $("#userInfo").addClass(cls);
            $("#userInfo").removeAttr('style');
            $("#userInfo").css("color", "green");
            $("#userInfo").data("id", "true");
          }
          else {
            let cls = "glyphicon glyphicon-remove form-control-feedback";
            $("#userInfo").removeClass();
            $("#userInfo").addClass(cls);
            $("#userInfo").removeAttr('style');
            $("#userInfo").css("color", "red");
            $("#userInfo").data("id", "false");
          }
        }
      });
    }

    <%-- 校验密码 --%>
    function checkPwd(){
      $.post({
        url:"${pageContext.request.contextPath}/checkPwd",
        data:{"userid": $("#userid").val(), "pwd":$("#pwd").val()},
        success:function (data){
          if(data === "success"){
            let cls = "glyphicon glyphicon-ok form-control-feedback";
            $("#pwdInfo").removeClass();
            $("#pwdInfo").addClass(cls);
            $("#pwdInfo").removeAttr('style');
            $("#pwdInfo").css("color", "green");
            $("#pwdInfo").data("pwd", "true");
          }
          else {
            let cls = "glyphicon glyphicon-remove form-control-feedback";
            $("#pwdInfo").removeClass();
            $("#pwdInfo").addClass(cls);
            $("#pwdInfo").removeAttr('style');
            $("#pwdInfo").css("color", "red");
            $("#pwdInfo").data("pwd", "false");
          }
        }
      });
    }

    <%-- 提交校验 --%>
    function formsubmit(){
      let id = $("#userInfo").data("id");
      let pwd = $("#pwdInfo").data('pwd');
      console.log("id==>" + id);
      console.log('pwd==>' + pwd);
      if(id==="true" && pwd==="true"){
        return true;
      }
      else {
        alert("用户名或密码错误" + id);
        return false;
      }
    }


  </script>
  <style>
    /*web background*/
    .container{
      display:table;
      height:100%;
    }

    .row{
      display: table-cell;
      vertical-align: middle;
    }
    /* centered columns styles */
    .row-centered {
      text-align:center;
    }
    .col-centered {
      display:inline-block;
      float:none;
      text-align:left;
      margin-right:-4px;
    }
  </style>
</head>

<body>
<div class="container">
  <div class="row row-centered">
    <div class="well col-md-6 col-centered">
      <h2>欢迎登录</h2>

      <form action="${pageContext.request.contextPath}/radar/allRadar" method="post" role="form" onsubmit="return formsubmit();">
        <div class="form-group has-success has feedback">
          <div class="input-group input-group-md ">
            <span class="input-group-addon" ><i class="glyphicon glyphicon-user" aria-hidden="true"></i></span>
            <input type="text" class="form-control" id="userid" name="userid" placeholder="请输入用户ID" aria-describedby="inputStatus" onblur="checkId()" data-id="black"/>
            <span id="userInfo" class="glyphicon glyphicon-warning-sign form-control-feedback" aria-hidden="true" style="color: #c79700" ></span>
            <span id="inputStatus" class="sr-only">(warning)</span>
          </div>
        </div>
        <div class="form-group has-success has feedback">
          <div class="input-group input-group-md">
            <span class="input-group-addon" id="sizing-addon1"><i class="glyphicon glyphicon-lock"></i></span>
            <input type="password" class="form-control" id="pwd" name="pwd" placeholder="请输入密码" aria-describedby="inputStatus" onblur="checkPwd()" data-pwd="black"/>
            <span id="pwdInfo" class="glyphicon glyphicon-warning-sign form-control-feedback" aria-hidden="true" style="color: #c79700"></span>
            <span id="pwdStatus" class="sr-only">(warning)</span>
          </div>
        </div>

        <br/>
        <input type="submit" class="btn btn-success btn-block" value="登录">
      </form>
    </div>
  </div>
</div>



<!-- 最新的 Bootstrap 核心 JavaScript 文件 -->
<script src="//cdn.bootcss.com/bootstrap/3.3.5/js/bootstrap.min.js"></script>
</body>
</html>
