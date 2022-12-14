<%@ page language="java" contentType="text/html;charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html;charset=UTF-8">
<title>CRM客户关系管理系统</title>
<link rel="stylesheet" href="/css/style.css">
<script type="text/javascript" src="/js/jquery-easyui/jquery.min.js"></script>
  <script type="text/javascript">
    // 提交登录账号和密码:把账号和密码传递到后台
    function submitForm() {
/*      console.log($("form").serialize());*/
      $.ajax({
        type: "POST",
        url: "/login",
/*        dataType: "json",*/
        data: $("form").serialize(),
        success: function(data){
          if(data.success){
            window.location.href="/index";
          }else{
            $.messager.alert("温馨提示",data.msg,"warning");
          }
        }
      });
    }
    // 清除账号和密码
    function resetForm() {
      $("form").form("clear");
    }

  </script>
</head>
<body>
  <section class="container">
    <div class="login">
      <h1>用户登录</h1>
      <form method="post">
        <p><input type="text" name="username" value="" placeholder="账号"></p>
        <p><input type="password" name="password" value="" placeholder="密码"></p>
        <p class="submit">
        	<input type="button" value="登录" onclick="submitForm()">
        	<input type="button" value="重置" onclick="resetForm()">
        </p>
      </form>
    </div>
  </section>
</body>
</html>