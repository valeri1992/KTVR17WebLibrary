<%-- 
    Document   : page2
    Created on : Sep 26, 2018, 9:37:07 AM
    Author     : Melnikov
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Главная</title>
    </head>
    <body>
        <h1>Навигация по сайту</h1>
        ${textToPage}<br>
        <a href="newBook">добавить книгу</a><br>
        <a href="newReader">добавить читателя</a><br>
        <a href="showBooks">Список книг</a><br>
        <a href="showReader">Список читателей</a><br>
        <a href="library">Выдать книгу</a><br>
        <a href="showTakeBook">Список выданных книг</a>
        <br>
        Добавлена книга:<br>
        Название: ${book.nameBook}<br>
        Автор: ${book.author}
        <hr>
        Добавлен читатель:<br>
        Имя: ${reader.name}<br>
        Фамилия: ${reader.surname}
        
    </body>
</html>
