<%--
  Created by IntelliJ IDEA.
  User: cui_a
  Date: 2020/12/4
  Time: 16:30
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>修改雷达</title>
    <link href="https://cdn.staticfile.org/twitter-bootstrap/3.3.7/css/bootstrap.min.css" rel="stylesheet">
    <script>
        function myFunction(state, kind='other'){
            var str, op;
            if (kind === "work"){op = '关闭';}else {op='异常';}
            if (state){
                str = "<option selected value='true'>正常</option>" +
                    "<option value='false'>"+op+"</option>";
            }
            else {
                str = "<option value='true'>正常</option>" +
                    "<option selected value='false'>"+op+"</option>";
            }
            return str;
        }
    </script>
</head>
<body>

<div class="container">
    <div class="row clearfix">
        <div class="col-md-12 column">
            <div class="page-header">
                <h3>修改雷达</h3>
            </div>
        </div>

    </div>

    <form action="${pageContext.request.contextPath}/radar/updateRadar?href=${href}" method="post">
        <div class="form-group">
            <label>雷达id</label>
            <input type="text" name="radarid" class="form-control" value="${QRadar.radarid}" readonly>
        </div>
        <div class="form-group">
            <label>IP地址</label>
            <input type="text" name="radarip" class="form-control" value="${QRadar.radarip}" required>
        </div>
        <div class="form-group">
            <label>端口号</label>
            <input type="text" name="port" class="form-control" value="${QRadar.port}" required>
        </div>
        <div class="form-group">
            <label>雷达编号</label>
            <input type="text" name="sirialnum" class="form-control" value="${QRadar.sirialnum}" required>
        </div>
        <div class="form-group">
            <label>工作状态</label>
            <select class="form-control" name="workstate">
                <script>document.write(myFunction(${QRadar.workstate}, 'work'))</script>
            </select>
        </div>
        <div class="form-group">
            <label>异物检测状态</label>
            <select class="form-control" name="foreignmatter">
                <script>document.write(myFunction(${QRadar.foreignmatter}))</script>
            </select>
        </div>
        <div class="form-group">
            <label>安全门状态</label>
            <select class="form-control" name="safetydoor">
                <script>document.write(myFunction(${QRadar.safetydoor}))</script>
            </select>
        </div>
        <div class="form-group">
            <label>警报状态</label>
            <select class="form-control" name="radarerror">
                <script>document.write(myFunction(${QRadar.radarerror}))</script>
            </select>
        </div>
        <div class="form-group">
            <label>最近日志</label>
            <input type="text" name="lastlog" class="form-control" value="${QRadar.lastlog}" readonly>
        </div>
        <div class="form-group">
            <label>最近日志</label>
            <input type="text" name="comment" class="form-control" value="${QRadar.comment}">
        </div>
        <div class="form-group">
            <input type="submit" class="form-control" value="修改">
        </div>
    </form>

</div>
</body>
</html>
