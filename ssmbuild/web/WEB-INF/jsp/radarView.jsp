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
    <title>雷达实时监控</title>
    <script src="//cdn.bootcdn.net/ajax/libs/jquery/3.5.1/jquery.js"></script>
    <script src="https://cdn.staticfile.org/jquery/2.1.1/jquery.js"></script>
    <script src="https://cdn.staticfile.org/jquery/2.1.1/jquery.min.js"></script>
    <link href="https://cdn.staticfile.org/twitter-bootstrap/3.3.7/css/bootstrap.min.css" rel="stylesheet">
    <script src="https://cdn.staticfile.org/twitter-bootstrap/3.3.7/js/bootstrap.min.js"></script>


    <script type="text/javascript" src="../../js/radarViewer.js"></script>
    <script type="text/javascript" src="../../js/webSocketJS.js"></script>
    <style type="text/css">
        table{
            table-layout: auto;
            text-align: center;
            padding: 1px;
            border-spacing: 0;
            letter-spacing: 1px;
        }
        table th{
            text-align: center;
        }
        table td{
            max-width: 200px;
            min-width: 150px;
            overflow: hidden;
            text-overflow: ellipsis;
            white-space: nowrap;
            vertical-align: middle;
        }
        .container{
            min-width: 1800px;
        }
        img{
            vertical-align: middle;
        }
        h1{
            color: aliceblue;
        }
        h3{
            color: aliceblue;
        }
        #tableList td{
            vertical-align: middle;
            text-align: center;
        }
        button{
            width: 120px;
            height: 50px;
            font-size: 20px;
        }
        input{
            width: 120px;
            height: 50px;
        }
        #tip_message {
            z-index: 9999;
            position: fixed;
            left: 0;
            top: 40%;
            text-align: center;
            width: 100%;
        }

        #tip_message span {
            background-color: #03C440;
            opacity: .8;
            padding: 20px 50px;
            border-radius: 5px;
            text-align: center;
            color: #fff;
            font-size: 20px;
        }

        #tip_message span.error {
            background-color: #EAA000;
        }
    </style>
    <script>
        let radar = null;
        $(function () {
            $('#myModal').on('show.bs.modal', function (e) {
                AjaxPost($(e.relatedTarget).data('id'))
                $("#msg").html("ok").hide(3000);
            })
        });

        function AjaxPost(id){
            $.ajax({
                type:'POST',
                url:"${pageContext.request.contextPath}/radar/toUpdate",
                data:{"id":id},
                success:function (data){
                    radar = JSON.parse(data)
                    document.getElementById("radarid").value = radar.radarID
                    document.getElementById("radarip").value = radar.radarIP
                    document.getElementById("port").value = radar.port
                    document.getElementById("sirialnum").value = radar.sirialnum
                    document.getElementById("workstate").value = radar.workstate
                    document.getElementById("foreignmatter").value = radar.foreignmatter
                    document.getElementById("safetydoor").value = radar.safetydoor
                    document.getElementById("radarerror").value = radar.radarerror
                    document.getElementById("lastlog").value = radar.lastlog
                    document.getElementById("comment").value = radar.comment
                }
            });
        }

        function submit(){
            const data = {
                "radarid": document.getElementById("radarid").value,
                "radarip": document.getElementById("radarip").value,
                "port": document.getElementById("port").value,
                "sirialnum": document.getElementById("sirialnum").value,
                "workstate": document.getElementById("workstate").value,
                "foreignmatter": document.getElementById("foreignmatter").value,
                "safetydoor": document.getElementById("safetydoor").value,
                "radarerror": document.getElementById("radarerror").value,
                "lastlog": document.getElementById("lastlog").value,
                "comment": document.getElementById("comment").value,
            };
            console.log(JSON.stringify(data))
            $.ajax({
                type:'POST',
                url:"${pageContext.request.contextPath}/radar/updateRadar",
                data:JSON.stringify(data),
                contentType: 'application/json;charset=UTF-8',
                dataType:"json",
                success:function (){
                    alert("修改成功！")
                }
            });
            $('#myModal').modal('hide')
        }
    </script>
    <style type="text/css"> @import "../../css/radarview.css";</style>

</head>
<body id="body">
    <div class="container" >
        <div class="row clearfix">
            <div class="col-md-12 column">
                <div class="col-xl-1" style="float: right">
                    <label style="font-size: large">欢迎您，<%=session.getAttribute("username")%></label>
                    &nbsp;
                    <a style="font-size: large" href="${pageContext.request.contextPath}/cfg/ipconfig" target="_blank">设置</a>
                    &nbsp; | &nbsp;
                    <a style="font-size: large" href="${pageContext.request.contextPath}/logout">注销</a>
                </div>
                <div class="page-header">
                    <h1 class="text-center">雷达实时监控</h1>
                </div>
            </div>
        </div>
    </div>

    <div class="container">
        <nav class="navbar navbar-inverse">
            <div >
                <ul class="nav navbar-nav">
                    <li><a href="${pageContext.request.contextPath}/radar/allRadar" target="_blank">雷达列表</a></li>
                    <li><a href="${pageContext.request.contextPath}/log/allLog" target="_blank">日志列表</a></li>
                    <li><a href="${pageContext.request.contextPath}/ipc/ipcCfg" target="_blank">工控机列表</a></li>
                </ul>
            </div>

        </nav>


        <div class="col-lg-12">
            <table class="table table-bordered" id="tableList">
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
    <audio src="${pageContext.request.contextPath}/static/警铃.wav" loop="loop" preload="auto" id="sound"></audio>

<%-----------------------------------------------------------------------------------------------------------------%>

    <!-- 修改雷达模态框（Modal） -->
    <div class="modal fade" id="myModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
        <div class="modal-dialog">
            <div class="modal-content">
                <div class="modal-header">
                    <button type="button" class="close" data-dismiss="modal" aria-hidden="true">
                        &times;
                    </button>
                    <h4 class="modal-title" id="myModalLabel">
                        修改雷达信息
                    </h4>
                </div>
                <div class="modal-body">
                    <form>
                        <div class="form-group">
                            <label>雷达id</label>
                            <input type="text" name="radarid" class="form-control" id="radarid" readonly>
                        </div>
                        <div class="form-group">
                            <label>IP地址</label>
                            <input type="text" name="radarip" class="form-control" id="radarip" required>
                        </div>
                        <div class="form-group">
                            <label>端口号</label>
                            <input type="text" name="port" class="form-control" id="port" required>
                        </div>
                        <div class="form-group">
                            <label>雷达编号</label>
                            <input type="text" name="sirialnum" class="form-control" id="sirialnum" required>
                        </div>
                        <div class="form-group">
                            <label>工作状态</label>
                            <input type="text" name="workstate" class="form-control" id="workstate" readonly>
                        </div>
                        <div class="form-group">
                            <label>异物检测状态</label>
                            <input type="text" name="foreignmatter" class="form-control" id="foreignmatter" readonly>
                        </div>
                        <div class="form-group">
                            <label>安全门状态</label>
                            <input type="text" name="safetydoor" class="form-control" id="safetydoor" readonly>
                        </div>
                        <div class="form-group">
                            <label>警报状态</label>
                            <input type="text" name="radarerror" class="form-control" id="radarerror" readonly>
                        </div>
                        <div class="form-group">
                            <label>最近日志</label>
                            <input type="text" name="lastlog" class="form-control" id="lastlog" readonly>
                        </div>
                        <div class="form-group">
                            <label>最近日志</label>
                            <input type="text" name="comment" class="form-control" id="comment">
                        </div>
                    </form>
                </div>
                <div class="modal-footer">
                    <button type="button" class="btn btn-default" data-dismiss="modal">关闭
                    </button>
                    <button type="button" class="btn btn-primary" onclick="submit()">
                        提交更改
                    </button>
                </div>
            </div><!-- /.modal-content -->
        </div><!-- /.modal -->
    </div>

</body>
</html>
