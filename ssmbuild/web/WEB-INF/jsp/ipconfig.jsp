<%--
  Created by IntelliJ IDEA.
  User: lenovo
  Date: 2020/12/29
  Time: 16:55
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
    <!-- Bootstrap 核心 CSS 文件 -->
    <link rel="stylesheet" href="//cdn.bootcss.com/bootstrap/3.3.5/css/bootstrap.min.css">

    <!-- jQuery文件。务必在bootstrap.min.js 之前引入 -->
    <script src="//cdn.bootcdn.net/ajax/libs/jquery/3.5.1/jquery.js"></script>
</head>
<body>
<form>
    <div class="form-group">
        <label for="exampleInputEmail1">IP address</label>
        <input type="text" class="form-control" id="exampleInputEmail1" placeholder=${ipaddress} readonly>
    </div>
</form>
</body>
</html>
