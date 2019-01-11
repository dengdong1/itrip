<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
    <script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery-1.8.3.min.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery.cookie.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/js/retoken.js"></script>
    <script type="text/javascript">
        $(function () {
            $("#search").click(function () {
           /*     var str = {
                    "linkUserName": $("#name").val()
                };*/
                var arrs = JSON.stringify(str);
                $.ajax({
                    "url": "http://localhost:8001/itrip-biz/api/userinfo/queryuserlinkuser",
                    "type": "POST",
                    "contentType": 'application/json',
                    "dataType": "json",
                    "data": arrs,
                    "success": function (data) {
                        console.log(data);
                        if (data.success == "true") {
                            var list = data.data;
                            $("#tabDiv").html("");
                            var tab = $("<table border='1'></table>").append("<tr><td>id</td><td>userId</td><td>用户名</td><td>电话</td><td>操作</td></tr>").appendTo($("#tabDiv"));
                            $(list).each(function () {
                                tab.append("<tr><td>" + this.id + "</td><td>" + this.userId + "</td><td>" + this.linkUserName + "</td><td>" + this.linkPhone + "</td><td><a href='#' class='dis'>刪除</a></td><td><a href='#'class='update'>修改</a></td></tr>");
                            });

                            $(".dis").click(function () {
                                var sid=[$(this).parent().siblings(0).html()];
                                var arrs = JSON.stringify(sid);
                                alert(arrs);
                                $.ajax({
                                    "url": "http://localhost:8001/itrip-biz/api/userinfo/deluserlinkuser",
                                    "type": "POST",
                                    "contentType": 'application/json',
                                    "dataType": "json",
                                    "data": arrs,
                                    "success": function (data) {
                                        console.log(data);
                                        if (data.success == "true") {
                                            var list = data.data;
                                            $("#tabDiv").html("");
                                            var tab = $("<table border='1'></table>").append("<tr><td>id</td><td>userId</td><td>用户名</td><td>电话</td><td>操作</td></tr>").appendTo($("#tabDiv"));
                                            $(list).each(function () {
                                                tab.append("<tr><td>" + this.id + "</td><td>" + this.userId + "</td><td>" + this.linkUserName + "</td><td>" + this.linkPhone + "</td><td><a href='#'class='dis'>刪除</a></td> <td><a href='#'class='update'>修改</a></td></tr>");
                                            });
                                        }
                                    },
                                    beforeSend: function (request) {
                                        request.setRequestHeader("token", $.cookie("token"));
                                    }
                                });
                            });

                            $(".update").click(function () {

                                var id=$(this).parent().siblings(0).html();
                                /*var arrs = JSON.stringify(id);*/
                                alert(arrs);
                                $.ajax({
                                    "url": "http://localhost:8001/itrip-biz/api/userinfo/updateid",
                                    "type": "POST",
                                    "contentType": 'application/json',
                                    "dataType": "json",
                                    "data": arrs,
                                    "success": function (data) {
                                        console.log(data);
                                        alert(data);
                                        if (data.success == "true") {
                                            var list = data.data;
                                            alert(list)
                                            alert(data.id+"sw")
                                            $("#tabDivUpdate").show();
                                          $(list).each(function () {
                                                      alert(this.id+"sw")
                                                $("#linkUserName1").attr("value",this.linkUserName);
                                                $("#linkIdCard1").attr("value",this.linkIdCard);
                                                $("#linkPhone1").attr("value",this.linkPhone);
                                                $("#userId1").attr("value",this.userId);
                                                $("#id").attr("value",this.id);
                                            });
                                        }
                                    },
                                    beforeSend: function (request) {
                                        request.setRequestHeader("token", $.cookie("token"));
                                    }
                                });
                            });
                        } else {
                            alert("token失效，请重新登录");
                        }
                    },
                    beforeSend: function (request) {
                        request.setRequestHeader("token", $.cookie("token"));
                    }
                })
                });
            });

        $(function () {
            $("#add").click(function () {
                $("#tabDivAdd").toggle();
            });

            $("#addTijiao").click(function () {
                var str = {
                    linkUserName: $("#linkUserName").val(),
                    linkIdCard: $("#linkIdCard").val(),
                    linkPhone: $("#linkPhone").val(),
                    linkIdCardType:0
                };


                var arrs = JSON.stringify(str);

                console.log(arrs);

                $.ajax({
                    "url": "http://localhost:8001/itrip-biz/api/userinfo/adduserlinkuser",
                    "type": "POST",
                    "contentType": 'application/json',
                    "dataType": "json",
                    "data": arrs,
                    "success": function (data) {
                        console.log(data);
                        if (data.success == "true") {
                            var list = data.data;
                            $("#tabDiv").html("");
                            var tab = $("<table border='1'></table>").append("<tr><td>id</td><td>userId</td><td>用户名</td><td>电话</td><td>操作</td></tr>").appendTo($("#tabDiv"));
                            $(list).each(function () {
                                tab.append("<tr><td>" + this.id + "</td><td>" + this.userId + "</td><td>" + this.linkUserName + "</td><td>" + this.linkPhone + "</td><td><a href=''>刪除</a></td></td><a href='#'class='update'>修改</a></td></tr>");
                            });

                        } else {
                            alert("token失效，请重新登录");
                        }
                    },
                    beforeSend: function (request) {
                        request.setRequestHeader("token", $.cookie("token"));
                    }
                });
            });
        })

        $(function () {
            var sid=$(this).parent().siblings(0).html();
            $("#update").click(function () {
                $("#tabDivUpdate").toggle();
            });

            $("#updateTijiao").click(function () {
                var str = {
                    id:$("#id").val(),
                    linkUserName: $("#linkUserName1").val(),
                    linkIdCard: $("#linkIdCard1").val(),
                    linkPhone: $("#linkPhone1").val(),
                    userId: $("#userId1").val(),
                    linkIdCardType:0
                };
                var arrs = JSON.stringify(str);
                console.log(arrs);
                $.ajax({
                    "url": "http://localhost:8001/itrip-biz/api/userinfo/updateuserlinkuser",
                    "type": "POST",
                    "contentType": 'application/json',
                    "dataType": "json",
                    "data": arrs,
                    "success": function (data) {
                        console.log(data);
                        if (data.success == "true") {
                            var list = data.data;
                            $("#tabDiv").html("");
                            var tab = $("<table border='1'></table>").append("<tr><td>id</td><td>userId</td><td>用户名</td><td>电话</td><td>操作</td></tr>").appendTo($("#tabDiv"));
                            $(list).each(function () {
                                alert(this.id)
                                tab.append("<tr><td>" + this.id + "</td><td>" + this.userId + "</td><td>" + this.linkUserName + "</td><td>" + this.linkPhone + "</td><td><a href=''>刪除</a></td></td><a href='#'class='update'>修改</a></td></tr>");
                            });
                        } else {
                            alert("token失效，请重新登录");
                        }
                    },
                    beforeSend: function (request) {
                        request.setRequestHeader("token", $.cookie("token"));
                    }
                });
            });
        })
    </script>
</head>
<body>
<div>
    旅客姓名：<input type="text" id="name"/>
    <input type="button" id="search" value="获取常用联系人列表"/>
    <input type="button" id="add" value="新增1"/>
    <input type="button" id="del" value="删除"/>
    <input type="button" id="update" value="更新"/>
</div>
<div id="tabDiv">

</div>


<div id="tabDivAdd" style="display: none;">
    <form>
        <table>
            <tr>
                <td>
                    常用联系人姓名:
                </td>
                <td>
                    <input type="text" id="linkUserName" name="linkUserName">
                </td>
            </tr>
            <tr>
                <td>
                    证件号码:
                </td>
                <td>
                    <input type="text" id="linkIdCard" name="linkIdCard">
                </td>
            </tr>
            <tr>
                <td>
                    常用联系人电话:
                </td>
                <td>
                    <input type="text" id="linkPhone" name="linkPhone">
                </td>
            </tr>
            <tr>
                <td>
                    <input type="button" id="addTijiao" value="提交"/>
                </td>
                <td>
                    <input type="reset" value="重置"/>
                </td>
            </tr>
        </table>
    </form>
</div>


<div id="tabDivUpdate" style="display: none;">
    <form>
        <table>
            <tr>
                <td>
                    常用联系人姓名:
                </td>
                <td>
                    <input type="text" id="linkUserName1" name="linkUserName1" >
                    <input type="text" id="id" name="id" style="display: none">
                </td>
            </tr>
            <tr>
                <td>
                    证件号码:
                </td>
                <td>
                    <input type="text" id="linkIdCard1" name="linkIdCard1">
                </td>
            </tr>
            <tr>
                <td>
                    常用联系人电话:
                </td>
                <td>
                    <input type="text" id="linkPhone1" name="linkPhone1">
                </td>
            </tr>
            <tr>
                <td>
                    用户id:
                </td>
                <td>
                    <input type="text" id="userId1" name="userId1">
                </td>
            </tr>
            <tr>
                <td>
                    <input type="button" id="updateTijiao" value="提交"/>
                </td>
                <td>
                    <input type="reset" value="重置"/>
                </td>
            </tr>
        </table>
    </form>
</div>
</body>
</html>