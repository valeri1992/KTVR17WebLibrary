<%-- 
    Document   : showLogin
    Created on : Nov 12, 2018, 9:17:24 AM
    Author     : pupil
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
           <h1>Войдите</h1>
        ${info}
        <form action="login" method="POST" name="form1" id="_form1">
            Логин:<br>
            <input type="text" name="login"><br>
            <br>
            Пароль:<br>
            <input type="text" name="password"><br>
            <br>
            <input type="submit" value="Войти">
        </form><br>
    </body>
</html>
