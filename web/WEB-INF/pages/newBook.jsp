

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Новая книга</title>
    </head>
    <body>
        <h1>Добавляем в библиотеку новую книгу</h1>
        <form action="addBook" method="POST" name="form1" id="_form1">
             Название:<br>
            <input type="text" name="nameBook"><br>
             Автор:<br>
            <input type="text" name="author"><br>
             Год издания:<br>
            <input type="text" name="yearPublished"><br>
             ISBN:<br>
            <input type="text" name="isbn"><br>
             Количество экземпляров:<br>
            <input type="text" name="count"><br>
            <br>
            <input type="submit" value="Добавить">
        </form><br>
         <a href="welcom">На главную </a>
        
    </body>
</html>
