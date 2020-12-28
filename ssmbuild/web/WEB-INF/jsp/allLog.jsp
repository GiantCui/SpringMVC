<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: cui_a
  Date: 2020/12/8
  Time: 10:43
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>

<head>
    <title>日志信息</title>
    <link href="https://cdn.staticfile.org/twitter-bootstrap/3.3.7/css/bootstrap.min.css" rel="stylesheet">
    <script>
        function myFunction(state){
            console.log(state);
            var str;
            if (state){
                str = '<img src="/static/start.svg" width="30ipx" height="30ipx">'
                return str;
            }
            else {
                str = '<img src="/static/stop.svg" width="30ipx" height="30ipx">'
                return str;
            }
        }
    </script>
    <style>

        table{
            table-layout: auto;
            text-align: center;
        }
        table th{
            text-align: center;
        }
        table td{
            max-width: 200px;
            min-width: 90px;
            overflow: hidden;
            text-overflow: ellipsis;
            white-space: nowrap;
        }
        table td:hover{
            overflow: visible;
            white-space: pre-wrap;
        }
        .container{
            min-width: 1700px;
        }
    </style>
</head>
<body>
<div class="container">
    <div class="row clearfix">
        <div class="col-md-12 column">
            <div class="page-header">
                <h2 class="text-center">日志列表——显示所有日志</h2>
            </div>
        </div>
    </div>


</div>
<div class="row container-fluid">
    <div >
        <table class="table table-hover table-striped">
            <thead>
            <tr>
                <th>雷达id</th>
                <th>IP地址</th>
                <th>端口号</th>
                <th>雷达编号</th>
                <th>工作状态</th>
                <th>异物检测状态</th>
                <th>屏蔽门状态</th>
                <th>警报状态</th>
                <th>最近日志</th>
                <th>备注</th>
                <th>日志编号</th>
                <th>开始时间</th>
                <th>结束时间</th>
                <th>日志信息</th>
            </tr>
            </thead>
            <!--雷达从数据库中查询出来-->
            <tbody>
            <c:forEach var="log" items="${list}">
                <tr>
                    <td>${log.radarid}</td>
                    <td>${log.radarip}</td>
                    <td>${log.port}</td>
                    <td>${log.sirialnum}</td>
                    <td><script>document.write(myFunction(${log.workstate}))</script></td>
                    <td><script>document.write(myFunction(${log.foreignmatter}))</script></td>
                    <td><script>document.write(myFunction(${log.safetydoor}))</script></td>
                    <td><script>document.write(myFunction(${log.radarerror}))</script></td>
                    <td>${log.lastlog}</td>
                    <td>${log.comment}</td>
                    <td>${log.lognum}</td>
                    <td>${log.starttime}</td>
                    <td>${log.endtime}</td>
                    <td>${log.log}</td>
                </tr>
            </c:forEach>
            </tbody>
        </table>
    </div>
</div>
</body>
</html>
