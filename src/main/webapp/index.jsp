<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
    <title>Insert title here</title>
</head>
<body>
<form action="SelectServlet" method="post" id="InsertFrom">
    method&nbsp;<input type="text" name="method" class="InputText"><br>
    Type&nbsp;<input type="text" name="Type" class="InputText"><br>
    方法&nbsp;<input type="text" name="method" class="InputText"><br>
    <input type="submit">
</form>

<form action="recommendServlet" method="post" id="recommendFrom">
    用户&nbsp;<input type="text" name="user" class="InputText"><br>
    数量&nbsp;<input type="text" name="num" class="InputText"><br>
    <input type="submit">
</form>
</body>
</html>