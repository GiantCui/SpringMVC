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
<div class="container">

    <div class="col-xl-11 column">
        <div class="page-header">
            <h2 class="text-center">设置</h2>
        </div>
    </div>
    <form action="${pageContext.request.contextPath}/updateCfg" method="get">
        <div class="form-group col-lg-12" >
            <div class="col-md-6">
                <label>本机IP地址</label>
                <input type="text" class="form-control" placeholder=${localAddress} readonly>
            </div>
            <div class="col-md-6">
                <label>接受端口号</label>
                <input type="text" class="form-control" name="receivePort" placeholder=${serverPort}>
            </div>
        </div>
        <div class="form-group col-lg-12" >
            <div class="col-md-6">
                <label>目标IP地址</label>
                <input type="text" class="form-control" name="targetIP" placeholder=${targetAddress}>
            </div>
            <div class="col-md-6">
                <label>发送端口号</label>
                <input type="text" class="form-control" name="sendPort" placeholder=${clientPort}>
            </div>
        </div>
        <div class="form-group">
            <input type="submit" class="form-control" value="确定">
        </div>
    </form>


</div>
</body>
</html>
