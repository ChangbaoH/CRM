<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>系统首页</title>
    <!-- 样式文件 -->
    <link rel="stylesheet" type="text/css" href="/js/jquery-easyui/themes/default/easyui.css">
    <!-- 图标样式 -->
    <link rel="stylesheet" type="text/css" href="/js/jquery-easyui/themes/icon.css">
    <!-- jquery核心库 -->
    <script type="text/javascript" src="/js/jquery-easyui/jquery.min.js"></script>
    <!-- easyui核心库 -->
    <script type="text/javascript" src="/js/jquery-easyui/jquery.easyui.min.js"></script>
    <script type="text/javascript" src="/js/views/index.js"></script>

</head>
<body>
<div class="easyui-layout" fit="true" style="width:600px;height:400px;">
    <div data-options="region:'north'"
         style="height:100px;background:  url('/img/back.png')  no-repeat;background-size:cover;">
        <div id="top">
            <div id="top_links">
                <div style="float: right;width: 200px;margin-top: 10px;">
                    <p>
                        <font style="font-size: 14px;color: #FAFAFA;margin-right: 10px">当前用户:${username}</font>
                        <a href="/login.jsp" class="btn-logout">安全退出</a>
                    </p>
                </div>

            </div>
        </div>
    </div>
    <div data-options="region:'west'" style="width:180px;">
        <!-- 手风琴+菜单 -->
        <div class="easyui-accordion" fit="true">
            <div title="菜单">
                <!-- 使用树组件来定义菜单 -->
                <ul id="menuTree"></ul>
            </div>
            <div title="帮助"></div>
            <div title="简介"></div>
        </div>
    </div>
    <div data-options="region:'center'" style="padding:5px;background:#eee;">
        <!-- 正文内容 -->
        <div id="myTabs" class="easyui-tabs" fit="true">
            <div title="欢迎页" closable="true">
                <center><h1>欢迎登陆系统</h1></center>
                <div id="pp">
                    <div style="width:20%"></div>
                    <div style="width:55%"></div>
                    <div style="width:25%"></div>
                </div>


            </div>
        </div>
    </div>
    <div data-options="region:'south'"
         style="height:30px;  background: url('/img/tail.png') no-repeat; background-size: cover">
        <center>Copyright ©2015-2016 广州小码哥教育科技有限公司 (<font style="color: #fdfffb;">版权所有,侵权必究</font>)</center>
    </div>
</div>
</body>
</html>
