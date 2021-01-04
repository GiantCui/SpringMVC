<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: cui_a
  Date: 2020/12/3
  Time: 18:21
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>雷达列表</title>

    <link href="https://cdn.staticfile.org/twitter-bootstrap/3.3.7/css/bootstrap.min.css" rel="stylesheet">
    <!-- jQuery文件。务必在bootstrap.min.js 之前引入 -->
    <script src="http://apps.bdimg.com/libs/jquery/1.11.1/jquery.min.js"></script>

    <!-- 最新的 Bootstrap 核心 JavaScript 文件 -->
    <script src="http://apps.bdimg.com/libs/bootstrap/3.2.0/js/bootstrap.min.js"></script>
    <script>
        function myFunction(state){
            console.log(state);
            var str;
            if (state){
                str = '<img src="/static/start.svg" width="30ipx" height="30ipx">'
                return str;
            }
            else {
                str = '<img src="/static/123.gif" width="30ipx" height="30ipx">'
                return str;
            }
        }
        function shows(a){
            console.log(a);
            $('.buttonText').text(a);
        }
    </script>
    <style>
        table{
            table-layout: auto;
        }
        table th{
            text-align: center;
        }
        table td{
            max-width: 200px;
            min-width: 80px;
            overflow: hidden;
            text-overflow: ellipsis;
            text-align: center;
            white-space: nowrap;
        }
        table td:hover{
            overflow: visible;
            white-space: pre-wrap;
        }
        #option:hover{
            overflow: hidden;
            white-space: nowrap;
        }
        .container{
            min-width: 1700px;
        }
    </style>

</head>
<body>

<div class="container">

    <div class="row container-fluid">
        <div class="col-xl-1" style="float: right">
            <label>欢迎您，<%=session.getAttribute("username")%></label>
            &nbsp;
            <a href="${pageContext.request.contextPath}/cfg/ipconfig">设置</a>
            &nbsp; | &nbsp;
            <a href="${pageContext.request.contextPath}/logout">注销</a>
        </div>
        <div class="col-xl-11 column">
            <div class="page-header">
                <h2 class="text-center">雷达列表——显示所有雷达</h2>
            </div>
        </div>

    </div>
    <div class="row container-fluid">
        <div class="col-md-8 column">
            <!--新增雷达-->
            <a class="btn btn-primary" href="${pageContext.request.contextPath}/radar/toAddRadar?href=/radar/allRadar">新增雷达</a>
            <!--日志列表-->
            <a class="btn btn-primary" href="${pageContext.request.contextPath}/log/allLog">日志列表</a>
            <!--实时监控-->
            <a class="btn btn-primary" href="${pageContext.request.contextPath}/radar/radarView">实时监控</a>
        </div>
        <div class="col-lg-4 ">
            <div class="input-group">
                <div class="input-group-btn">
                    <button type="button" class="btn btn-primary dropdown-toggle" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                        <span class="buttonText">雷达编号</span>
                        <span class="caret"></span>
                    </button>
                    <ul class="dropdown-menu">
                        <li><a href="#" onclick="shows($(this).text())">雷达ID</a></li>
                        <li><a href="#" onclick="shows($(this).text())">雷达编号</a></li>
                    </ul>
                </div>
                <input type="text" class="form-control" aria-label="...">
                <span class="input-group-btn">
                <button class="btn btn-primary" type="button">搜索</button>
                </span>
            </div>
<%--            <form class="form-inline" style="float: right">--%>
<%--                <!--查询-->--%>
<%--                <label>雷达编号:--%>
<%--                    <input type="text" class="form-control">--%>
<%--                    <input formaction="" type="submit" value="查询" class="btn btn-primary">--%>
<%--                </label>--%>
<%--            </form>--%>
        </div>
    </div>
    <div class="row container-fluid">
        <div class="col-xl-12 column" >
            <table class="table table-hover table-striped">
                <thead>
                    <tr>
                        <th>雷达ID</th>
                        <th>IP地址</th>
                        <th>端口号</th>
                        <th>雷达编号</th>
                        <th>工作状态</th>
                        <th>异物检测状态</th>
                        <th>屏蔽门状态</th>
                        <th>警报状态</th>
                        <th>最近日志</th>
                        <th>备注</th>
                        <th>操作</th>
                    </tr>
                </thead>
                <!--雷达从数据库中查询出来-->
                <tbody>
                    <c:forEach var="radar" items="${list}">
                        <tr>
                            <td>${radar.radarid}</td>
                            <td>${radar.radarip}</td>
                            <td>${radar.port}</td>
                            <td>${radar.sirialnum}</td>
                            <td><script>document.write(myFunction(${radar.workstate}))</script></td>
                            <td><script>document.write(myFunction(${radar.foreignmatter}))</script></td>
                            <td><script>document.write(myFunction(${radar.safetydoor}))</script></td>
                            <td><script>document.write(myFunction(${radar.radarerror}))</script></td>
                            <td>${radar.lastlog}</td>
                            <td>${radar.comment}</td>
                            <td id="option">
                                <a href="${pageContext.request.contextPath}/radar/toUpdate?id=${radar.radarid}&href=/radar/allRadar">修改</a>
                                &nbsp; | &nbsp;
                                <a href="${pageContext.request.contextPath}/radar/deleteRadar?id=${radar.radarid}">删除</a>
                            </td>
                        </tr>
                    </c:forEach>
                </tbody>
            </table>
        </div>
    </div>
</div>
</body>
</html>
