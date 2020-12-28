<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: lenovo
  Date: 2020/12/23
  Time: 11:21
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>日志信息</title>
    <link href="https://cdn.staticfile.org/twitter-bootstrap/3.3.7/css/bootstrap.min.css" rel="stylesheet">
    <script type="text/javascript" src="../../js/radarViewer.js"></script>
    <script type="text/javascript" src="../../js/webSocketJS.js"></script>
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
        .container{
            min-width: 1700px;
        }
        img{
            vertical-align: middle;
        }
    </style>
    <style type="text/css"> @import "../../css/radarview.css";</style>
</head>
<body>
    <div class="container" >
        <div class="row clearfix">
            <div class="col-md-12 column">
                <div class="page-header">
                    <h2 class="text-center">雷达实时监控</h2>
                </div>
            </div>
        </div>
    </div>

    <div class="container">
        <nav class="navbar navbar-inverse">
            <div >
                <ul class="nav navbar-nav">
                    <li><a href="${pageContext.request.contextPath}/radar/allRadar">雷达列表</a></li>
                    <li><a href="${pageContext.request.contextPath}/log/allLog">日志列表</a></li>
                </ul>
            </div>

        </nav>


        <div class="col-lg-12">
            <table class="table table-bordered">
                <%-- 显示雷达 --%>
                <script>showRadars(${radars})</script>
                <%-- 图例示意 --%>
                <script>showAnnotation()</script>
            </table>
        </div>
        <div class="col-lg-12" id="conslelog">
            <textarea class="form-control" id="message" rows="3" readonly></textarea>
        </div>
    </div>



</body>
</html>
