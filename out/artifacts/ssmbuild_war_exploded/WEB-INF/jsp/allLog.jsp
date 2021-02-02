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
    <script src="//cdn.bootcdn.net/ajax/libs/jquery/3.5.1/jquery.js"></script>
    <script src="https://cdn.staticfile.org/jquery/2.1.1/jquery.js"></script>
    <script src="https://cdn.staticfile.org/jquery/2.1.1/jquery.min.js"></script>
    <link href="https://cdn.staticfile.org/twitter-bootstrap/3.3.7/css/bootstrap.min.css" rel="stylesheet">
    <script src="https://cdn.staticfile.org/twitter-bootstrap/3.3.7/js/bootstrap.min.js"></script>

    <script>
        let pageSize = 20;
        let pageNum;
        let currentPage=1;
        window.onload=function (){
            console.log("获取页数")
            setPageNum();
            getPage(1)
        }
        function setPageNum(){
            $.ajax({
                type: "POST",
                url: "${pageContext.request.contextPath}/log/getPageNum",
                data: {"pageSize":pageSize},
                success: function (data){
                    pageNum = data
                }
            })
            document.getElementById("tbody").innerHTML = ""
            document.getElementById("tbody").innerHTML = showLog()
        }
        function getPage(page = currentPage){
            currentPage=page
            $.ajax({
                type: "POST",
                url: "${pageContext.request.contextPath}/log/getPage",
                data: {"page": currentPage, "pageSize": pageSize},
                success: function (data){
                    setPage(data)
                }
            })
        }
        function setPage(data){
            console.log("第", currentPage, "页", ",数据：", data.length, "条")
            for (let i=0; i<pageSize; i++){
                let logNum, radarID, log, time
                if (i >= data.length){
                    document.getElementById("logNum"+i).hidden = true
                    document.getElementById("radarID"+i).hidden = true
                    document.getElementById("log"+i).hidden = true
                    document.getElementById("time"+i).hidden = true
                }
                else {
                    document.getElementById("logNum"+i).hidden = false
                    document.getElementById("radarID"+i).hidden = false
                    document.getElementById("log"+i).hidden = false
                    document.getElementById("time"+i).hidden = false
                    document.getElementById("logNum"+i).innerText = data[i]["logNum"]
                    document.getElementById("radarID"+i).innerText = data[i]["radarID"]
                    document.getElementById("log"+i).innerText = data[i]["log"]
                    document.getElementById("time"+i).innerText = data[i]["time"]
                }

            }
            setPageBar()
        }
        function showLog(){
            console.log("pageSize", pageSize)
            let str = ""
            for (let i=0; i<pageSize; i++){
                let logNum = "logNum"+i
                let radarID = "radarID"+i
                let log = "log"+i
                let time = "time"+i
                str += "<tr>" +
                    "<td id=\""+logNum+"\"></td>" +
                    "<td id=\""+radarID+"\"></td>" +
                    "<td id=\""+log+"\"></td>" +
                    "<td id=\""+time+"\"></td>" +
                "</tr>"
            }
            //document.write(str)
            return str
        }
        function setPageBar(){
            console.log("当前页",currentPage)
            // 判断是否为首页
            if (currentPage === 1){
                document.getElementById("previousPage").setAttribute("class", "disabled")
                document.getElementById("previousPage").setAttribute("onclick", "")
                document.getElementById("firstPage").setAttribute("class", "disabled")
                document.getElementById("firstPage").setAttribute("onclick", "")
            }
            else {
                let li = document.getElementById("previousPage")
                li.setAttribute("class", "default")
                li.onclick = function (){getPage(currentPage-1)}
                li = document.getElementById("firstPage")
                li.setAttribute("class", "default")
                li.onclick = function (){getPage(1)}
            }
            // 判断是否为末页
            if (currentPage === pageNum){
                document.getElementById("nextPage").setAttribute("class", "disabled")
                document.getElementById("nextPage").setAttribute("onclick", "")
                document.getElementById("lastPage").setAttribute("class", "disabled")
                document.getElementById("lastPage").setAttribute("onclick", "")
            }
            else {
                let li = document.getElementById("nextPage")
                li.setAttribute("class", "default")
                li.onclick = function (){getPage(currentPage+1)}
                li = document.getElementById("lastPage")
                li.setAttribute("class", "default")
                li.onclick = function (){getPage(pageNum)}
            }
            // 页数移动
            movePageBar()
        }
        function movePageBar(){
            for (let i=1; i <= 5; i++){
                let li = document.getElementById("li"+i)
                let value
                if (currentPage < 3){
                    value = i
                }
                else if (currentPage > pageNum-3){
                    value = pageNum-4+i
                }
                else {
                    value = currentPage-3+i
                }
                li.dataset["id"] = value.toString()
                document.getElementById(i.toString()).innerText = value.toString()
                if (li.dataset["id"] === currentPage.toString()){
                    li.setAttribute("class", "active")
                }
                else if (i > pageNum){
                    li.setAttribute("class", "disabled")
                    li.onclick = ""
                }
                else {
                    li.setAttribute("class", "default")
                    li.onclick = function (){getPage(value)}
                }

            }
        }
        function setPageSize(size){
            pageSize = size;
            setPageNum();
            getPage();
        }
        function download(){
            $.ajax({
                type: "POST",
                url: "/log/downloadLog",
                success:function (data){

                }
            })
        }
    </script>
    <style>
        body{
            background-color: #ada991;
        }
        table{
            table-layout: auto;
            text-align: center;
        }
        table th{
            text-align: center;
            background: #a6d9a6;
        }
        table td{
            max-width: 200px;
            min-width: 90px;
            overflow: hidden;
            text-overflow: ellipsis;
            white-space: nowrap;
        }
        #mytable tr:nth-child(even){
            background: #a6d9a6;
        }
        #mytable tr:nth-child(odd){
            background: #eae3e3;
        }
        table td:hover{
            overflow: visible;
            white-space: pre-wrap;
        }
        .container{
            min-width: 1700px;
        }
        h1{
            color: #000000;
        }
    </style>
</head>
<body>
<div class="container">
    <div class="row clearfix">
        <div class="col-md-12 column">
            <div class="page-header">
                <h1 class="text-center">日志列表——显示所有日志</h1>
            </div>
        </div>
    </div>
    <div class="row container-fluid">
        <a class="btn btn-success" href="${pageContext.request.contextPath}/log/downloadLog" style="color: white">导出日志</a>

        <div >
            <table id="mytable" class="table table-hover table-striped">
                <thead>
                <tr>
                    <th>日志编号</th>
                    <th>雷达编号</th>
                    <th>日志信息</th>
                    <th>日志时间</th>
                </tr>
                </thead>
                <!--雷达从数据库中查询出来-->
                <tbody id="tbody">
                    <script>showLog()</script>
                </tbody>
            </table>
        </div>
    </div>
    <nav style="text-align: center">
        <ul class="pagination pagination-lg">
            <li id="firstPage"><a>&lt;首页</a></li>
            <li id="previousPage"><a >&laquo;上一页</a></li>
            <li id="li1" data-id="1"><a id="1">1</a></li>
            <li id="li2" data-id="2"><a id="2">2</a></li>
            <li id="li3" data-id="3"><a id="3">3</a></li>
            <li id="li4" data-id="4"><a id="4">4</a></li>
            <li id="li5" data-id="5"><a id="5">5</a></li>
            <li id="nextPage"><a>下一页&raquo;</a></li>
            <li id="lastPage"><a>末页&gt;</a></li>
            <li role="presentation" class="dropup">
                <a class="dropdown-toggle" data-toggle="dropdown" href="#" role="button" aria-haspopup="true" aria-expanded="false">
                    显示行数 <span class="caret"></span>
                </a>
                <ul class="dropdown-menu">
                    <li onclick="setPageSize(20)"><a href="#">20</a></li>
                    <li onclick="setPageSize(50)"><a href="#">50</a></li>
                    <li onclick="setPageSize(100)"><a href="#">100</a></li>
                    <li onclick="setPageSize(200)"><a href="#">200</a></li>
                    <li onclick="setPageSize(500)"><a href="#">500</a></li>
                    <li onclick="setPageSize(1000)"><a href="#">1000</a></li>
                </ul>
            </li>
        </ul>

    </nav>
</div>

</body>
</html>
