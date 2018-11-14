<%-- 
    Document   : editUserRoles
    Created on : Nov 7, 2018, 12:33:30 PM
    Author     : pupil
--%>

<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <h1>Назначение ролей пользователей</h1>
        <form action="changeUserRole" method="POST">
            <table>
                <tr>
                    <th>Пользователь</th>
                    <th>Новая роль</th>
                    <th></th>
                    <th></th>
                </tr>
                <tr>
                    <td><select name="user">
                            <c:forEach var="entry" items="${mapUsers}">
                                <option value="${entry.key.id}">${entry.key.login},роль:${entry.value}</option>
                            </c:forEach>

                        </select></td>
                    <td> <select  name="role">
                            <c:forEach var="role" items="${listRoles}">
                                <option value="${role.id}">${role.name}</option>
                            </c:forEach>

                        </select></td>
                    <td><input type="submit" name="setButton" value="Назначить"></td><br>

                    <td><input type="submit" name="deleteButton" value="Удалить"></td>
                </tr>
            </table>



        </form>
        <a href="welcome">На главную </a>

    </body>
</html>
