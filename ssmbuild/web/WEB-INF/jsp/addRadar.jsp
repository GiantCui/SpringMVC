<%--
  Created by IntelliJ IDEA.
  User: cui_a
  Date: 2020/12/4
  Time: 14:56
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>雷达列表</title>

    <link href="https://cdn.staticfile.org/twitter-bootstrap/3.3.7/css/bootstrap.min.css" rel="stylesheet">

</head>
<body>

<div class="container">
    <div class="row clearfix">
        <div class="col-md-12 column">
            <div class="page-header">
                <h3>新增雷达</h3>
            </div>
        </div>

    </div>

    <form action="${pageContext.request.contextPath}/radar/addRadar" method="post">
        <div class="form-group">
            <label>雷达序列号</label>
            <input type="text" name="sirialnum" class="form-control" value="1" required>
        </div>
        <div class="form-group">
            <label>IP地址</label>
            <input type="text" name="radarip" class="form-control" value="0.0.0.0" required>
        </div>
        <div class="form-group">
            <label>端口号</label>
            <input type="text" name="port" class="form-control" value="0000" required>
        </div>
        <div class="form-group">
            <label>备注</label>
            <input type="text" name="comment" class="form-control" value="" required>
        </div>
        <div class="form-group">
            <input type="submit" class="form-control" value="添加雷达">
        </div>
    </form>

</div>
</body>
</html>

