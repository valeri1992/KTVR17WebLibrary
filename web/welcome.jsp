
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Главная</title>
    </head>
    <body>
        <h1>Навигация по сайту</h1>
        ${info}<br>
        <br>
        <a href="showLogin">Войти в систему</a><br><br>
        <a href="logout">Выйти из системы</a><br><br>
        <a href="newBook">добавить книгу</a><br><br>
        <a href="newReader">добавить читателя</a><br><br>
        <a href="showBooks">Список книг</a><br><br>
        <a href="showReader">Список читателей</a><br><br>
        <a href="library">Выдать книгу</a><br><br>
        <a href="showTakeBook">Список выданных книг</a>
        <br>
         <br><br>
        Для администратора<br>
        <a href="newRole">Новая роль</a><br>
        <a href="editUserRoles">Назначить роль</a>
        <br>
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
