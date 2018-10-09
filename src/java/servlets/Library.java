/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlets;

import entity.Book;
import entity.History;
import entity.Reader;
import java.io.IOException;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;
import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import session.BookFacade;
import session.HistoryFacade;
import session.ReaderFacade;

/**
 *
 * @author Melnikov
 */
@WebServlet(name = "Library", urlPatterns = {"/newBook","/addBook","/newReader","/addReader",
    "/showBooks","/showReader","/library","/takeBook","/showTakeBook","/returnBook",
    "/deleteBook"})
public class Library extends HttpServlet {
    
@EJB BookFacade bookFacade;
@EJB ReaderFacade readerFacade;
@EJB HistoryFacade historyFacade;
    
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        request.setCharacterEncoding("UTF8");
        String path = request.getServletPath();
        if("/newBook".equals(path)){
            request.getRequestDispatcher("/WEB-INF/pages/newBook.jsp").forward(request, response);
        }else if("/addBook".equals(path)){
            String nameBook = request.getParameter("nameBook");
            String author = request.getParameter("author");
            String yearPublished = request.getParameter("yearPublished");
            String isbn = request.getParameter("isbn");
            Book book = new Book(nameBook, author, new Integer(yearPublished), isbn);
            bookFacade.create(book);
            request.setAttribute("book", book);
            request.getRequestDispatcher("/page2.jsp").forward(request, response);
        }else if("/newReader".equals(path)){
            request.getRequestDispatcher("/WEB-INF/pages/newReader.jsp").forward(request, response);
        }else if("/addReader".equals(path)){
            String name = request.getParameter("name");
            String surname = request.getParameter("surname");
            String phone = request.getParameter("phone");
            String city = request.getParameter("city");
            Reader reader = new Reader(name, surname, phone, city);
            readerFacade.create(reader);
            request.setAttribute("reader", reader);
            request.getRequestDispatcher("/page2.jsp").forward(request, response);
        }else if("/showBooks".equals(path)){
            List<Book> listBooks = bookFacade.findActived(true);
            request.setAttribute("listBooks", listBooks);
            request.getRequestDispatcher("/listBooks.jsp").forward(request, response);
        }else if("/showReader".equals(path)){
            List<Reader> listReader = readerFacade.findAll();
            request.setAttribute("listReader", listReader);
            request.getRequestDispatcher("/listReader.jsp").forward(request, response);
        }else if("/library".equals(path)){
            request.setAttribute("listBooks", bookFacade.findActived(true));
            request.setAttribute("listReader", readerFacade.findAll());
            request.getRequestDispatcher("/library.jsp").forward(request, response);
        }else if("/showTakeBook".equals(path)){
            List<History> takeBooks = historyFacade.findTakeBooks();
            request.setAttribute("takeBooks", takeBooks);
            request.getRequestDispatcher("/listTakeBooks.jsp").forward(request, response);
        }else if("/takeBook".equals(path)){
            String selectedBook = request.getParameter("selectedBook");
            String selectedReader = request.getParameter("selectedReader");
            Book book = bookFacade.find(new Long(selectedBook));
            Reader reader = readerFacade.find(new Long(selectedReader));
            Calendar c = new GregorianCalendar();
            History history = new History(book, reader, c.getTime(), null);
            historyFacade.create(history);
            List<History> takeBooks = historyFacade.findTakeBooks();
            request.setAttribute("takeBooks", takeBooks);
            request.getRequestDispatcher("/listTakeBooks.jsp").forward(request, response);
        }else if("/returnBook".equals(path)){
            String returnBookId = request.getParameter("returnBookId");
            History history = historyFacade.find(new Long(returnBookId));
            Calendar c = new GregorianCalendar();
            history.setDateReturn(c.getTime());
            historyFacade.edit(history);
            List<History> takeBooks = historyFacade.findTakeBooks();
            request.setAttribute("takeBooks", takeBooks);
            request.getRequestDispatcher("/listTakeBooks.jsp").forward(request, response);
        }else if("/deleteBook".equals(path)){
            String deleteBookId = request.getParameter("deleteBookId");
            Book book = bookFacade.find(new Long(deleteBookId));
            book.setActive(Boolean.FALSE);
            bookFacade.edit(book);
//            bookFacade.remove(book);
//            List<History>histories=historyFacade.fineByBook(book);
//            for (History history:histories){
//            historyFacade.remove(history);}
//            bookFacade.remove(book);
            List<Book> listBooks = bookFacade.findActived(true);
            request.setAttribute("listBooks", listBooks);
            request.getRequestDispatcher("/listBooks.jsp").forward(request, response);
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
