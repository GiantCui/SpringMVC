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
    <title>工控机列表</title>
    <script src="//cdn.bootcdn.net/ajax/libs/jquery/3.5.1/jquery.js"></script>
    <script src="https://cdn.staticfile.org/jquery/2.1.1/jquery.js"></script>
    <script src="https://cdn.staticfile.org/jquery/2.1.1/jquery.min.js"></script>
    <link href="https://cdn.staticfile.org/twitter-bootstrap/3.3.7/css/bootstrap.min.css" rel="stylesheet">
    <link rel="stylesheet" href="../../css/font-awesome.css">
    <link rel="stylesheet" href="../../css/font-awesome.min.css">
    <script src="https://cdn.staticfile.org/twitter-bootstrap/3.3.7/js/bootstrap.min.js"></script>
    <script type="text/javascript" src="../../js/ipcJS.js"></script>
    <script type="text/javascript" src="../../js/coco-message.js"></script>
    <style type="text/css">
        @-webkit-keyframes spin {
            to {
                -webkit-transform: rotate(360deg);
                transform: rotate(360deg);
            }
        }

        @keyframes spin {
            to {
                -webkit-transform: rotate(360deg);
                transform: rotate(360deg);
            }
        }
        .div-waiting{
            position: fixed;
            z-index: 998;
            top: 0;
            right: 0;
            bottom: 0;
            left: 0;
            opacity: 1;
            background: rgba(0,0,0,0.2);
            vertical-align: middle;
            text-align: center;
        }
        .icon-waiting{
            position: relative;
            top: 48%;
            width: 5rem;
            height: 5rem;
            margin: 0 auto;
            border-radius: 50%;
            border: 0.5rem solid rgba(21, 21, 21, 0.4);
            border-top-color: #e1e1e1;
            -webkit-animation: 1.5s spin infinite linear;
            animation: 1.5s spin infinite linear;
        }
        .icon-position{
            position: relative;
            top: 48%;
            margin: 0 auto;
            font-size: 30px;
        }
        button{
            padding: 6px 12px;
            margin: 10px;
            background: #00bbee;
            border: 1px solid #00bfff;
            border-radius: 3px;
        }
    </style>

    <style type="text/css">

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
    </style>
    <script type="text/javascript">
        "use strict";
        cocoMessage.config({
            duration: 3000,
        });
        function ping(ipAddress, id, show=true){
            $.ajax({
                async:true,
                beforeSend: showWaiting(),
                type:"POST",
                url:"/ipc/singlePing",
                data:{"ipAddress": ipAddress},
                success:function (data){
                    closeWaiting()
                    let ob = document.getElementById(id)
                    if (data === '成功'){
                        ob.setAttribute("class", "success")
                        if (show){
                            const div1 = document.createElement("div");
                            div1.innerText = ipAddress + '连接' + data
                            cocoMessage.success(div1)
                        }
                    }
                    else {
                        ob.setAttribute("class", "danger")
                        if (show){
                            cocoMessage.error(ipAddress + '连接' + data);
                        }
                    }
                }
            });
        }
        function changeTbColor(ip, state){
            console.log(ip, state)
            let ob = document.getElementById(ip)
            ob.setAttribute("class", state)
        }
        function pingAll(){
            $.ajax({
                type: "POST",
                beforeSend: showWaiting(),
                data: {"ipcs": '${ipcsJSON}'},
                url: "${pageContext.request.contextPath}/ipc/pingAll",
                success:function (data){
                    closeWaiting()
                    const all = JSON.parse('${ipcsJSON}').length
                    const error = JSON.parse(data)
                    if (error.length === 0){
                        const div1 = document.createElement("div");
                        div1.innerText = "工控机IP地址全部正常";
                        cocoMessage.success(div1);
                    }
                    else {
                        cocoMessage.error("共有"+all+"个工控机，"+"其中"+(all-error.length)+"个正常，"+error.length+"个IP地址连接失败！");
                    }
                    for (let i=0; i<all; i++){
                        if(error.includes(i)){
                            changeTbColor(i+1, "danger")
                        }
                        else changeTbColor(i+1, "success")
                    }
                }
            })
            //closeWaiting()
        }
        function delIpc(index){
            $.ajax({
                type: "POST",
                data: {"index":index},
                url: "/ipc/delIpc",
                success:function (data){
                    location.reload()
                }
            })
        }
        function submitAdd(){
            let data = {"id":document.getElementById("model_id").value, "port":document.getElementById("model_port").value}
            $.ajax({
                type:"POST",
                url: "/ipc/addIpx",
                contentType: 'application/json;charset=UTF-8',
                data: JSON.stringify(data),
                success:function (data){
                    location.reload()
                }
            })
            $('#addIpc').modal('hide')
        }
        function initValue(ip, port, num){
            document.getElementById("model_id").value = ip
            document.getElementById("model_port").value = port
            document.getElementById("model_num").value = num
        }
        //初始化雷达
        function initRadar(){
            $.ajax({
                type: "POST",
                beforeSend: showWaiting(),
                url: "/ipc/initRadar",
                data: {"ipcs": '${ipcsJSON}'},
                success: function (data){
                    const div1 = document.createElement("div");
                    div1.innerText = "雷达初始化完毕";
                    cocoMessage.success(div1);
                    closeWaiting()
                }
            })
        }
    </script>
</head>
<body id="body" >
<div class="container">
    <div class="row clearfix">
        <div class="col-md-12 column">
            <div class="page-header">
                <h2 class="text-center">工控机列表</h2>
            </div>
        </div>
    </div>

</div>
<div class="row container-fluid">
    <div class="container">
        <div class="col-md-8 column">
            <!--新增雷达-->
            <button type="button" class="btn btn-primary" data-toggle="modal"  data-target="#addIpc"  rel="noopener noreferrer">
                <span class="glyphicon glyphicon-plus" aria-hidden="true"></span>
                新增
            </button>
            <button type="button" class="btn btn-primary"  id="pingTest" onclick="pingAll()">
                <span class="glyphicon glyphicon-refresh" aria-hidden="true"></span>
                测试连接
            </button>
            <button type="button" class="btn btn-primary" onclick="initRadar()">
                <span class="glyphicon glyphicon-cloud-download" aria-hidden="true"></span>
                初始化雷达
            </button>
        </div>
        <table class="table table-hover table-striped">
            <thead>
            <tr>
                <th>编号</th>
                <th>IP地址</th>
                <th>端口号</th>
                <th>操作</th>
            </tr>
            </thead>
            <!--雷达从数据库中查询出来-->
            <tbody>
            <c:forEach var="ipc" items="${ipcs}" varStatus="num">
                <tr id="${num.count}">
                    <td>${num.count}</td>
                    <td id="${ipc.id}">${ipc.id}</td>
                    <td>${ipc.port}</td>
                    <td>
                        <div class="btn-group btn-group" role="group" aria-label="...">
                            <button type="button" class="btn btn-default" onclick="ping('${ipc.id}', '${num.count}')">测试</button>
                            <button type="button" class="btn btn-default" data-toggle="modal"  data-target="#addIpc"  onclick="initValue('${ipc.id}', '${ipc.port}', '${num.count}')">修改</button>
                            <button type="button" class="btn btn-default" onclick="delIpc('${num.count}')">删除</button>
                        </div>
                    </td>
                </tr>
            </c:forEach>
            </tbody>
        </table>
    </div>
    <%--------------------------------------模态框-------------------------------------------%>
    <!-- 修改雷达模态框（Modal） -->
    <div class="modal fade" id="addIpc" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
        <div class="modal-dialog">
            <div class="modal-content">
                <div class="modal-header">
                    <button type="button" class="close" data-dismiss="modal" aria-hidden="true">
                        &times;
                    </button>
                    <h4 class="modal-title" id="myModalLabel">
                        新增工控机
                    </h4>
                </div>
                <div class="modal-body">
                    <form>
                        <input type="hidden" name="num" class="form-control" id="model_num">
                        <div class="form-group">
                            <label>IP地址</label>
                            <input type="text" name="id" class="form-control" id="model_id" required>
                        </div>
                        <div class="form-group">
                            <label>端口号</label>
                            <input type="text" name="port" class="form-control" id="model_port" required>
                        </div>
                    </form>
                </div>
                <div class="modal-footer">
                    <button type="button" class="btn btn-default" data-dismiss="modal">关闭
                    </button>
                    <button type="button" class="btn btn-primary" onclick="submitAdd()">
                        提交更改
                    </button>
                </div>
            </div><!-- /.modal-content -->
        </div><!-- /.modal -->
    </div>
</div>
</body>
</html>
