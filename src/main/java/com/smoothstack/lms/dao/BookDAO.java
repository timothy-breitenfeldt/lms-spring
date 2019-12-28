package com.smoothstack.lms.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.PreparedStatementCreatorFactory;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;

import com.smoothstack.lms.entity.Book;

public class BookDAO extends BaseDAO<Book> {
    
    public Integer createBook(String title, int publisherId) throws ClassNotFoundException, SQLException {
        PreparedStatementCreatorFactory pscFactory = new PreparedStatementCreatorFactory("INSERT INTO tbl_book (title, pubId) VALUES (?, ?);");
        PreparedStatementCreator psc = pscFactory.newPreparedStatementCreator(new Object[] {title, publisherId});
        KeyHolder keyHolder = new GeneratedKeyHolder();
        super.mySQLTemplate.update(psc, keyHolder);
        return (Integer)keyHolder.getKey();
    }
    
    public Map<String, Object> createBookAuthor(int bookId, int authorId) throws ClassNotFoundException, SQLException {
        String sql = "INSERT INTO tbl_book_authors (bookId, authorId) VALUES (?, ?);";
        Object[] parameters - new Object[] {bookId, authorId};
        PreparedStatementCreatorFactory pscFactory = new PreparedStatementCreatorFactory(sql);
        PreparedStatementCreator psc = pscFactory.newPreparedStatementCreator(parameters);
        KeyHolder keyHolder = new GeneratedKeyHolder();
        super.mySQLTemplate.update(psc, keyHolder);
        return keyHolder.getKeys();
    }
    
    public List<Book> getBooks() throws ClassNotFoundException, SQLException {
        return super.mySQLTemplate.query("SELECT * FROM tbl_book;", this);
    }
    
    public List<Book> getAvailableBooksNotCheckedOut(int branchId, int cardNumber) throws SQLException {
        return super.mySQLTemplate.query("SELECT bookId, title, pubId FROM tbl_book NATURAL JOIN tbl_book_copies WHERE branchId = ? AND cardNumber = ? AND noOfCopies > 0 AND dateIn != NULL;",
                new Object[] {branchId, cardNumber}, this);
    }

    public Book getBook(int bookId) throws ClassNotFoundException, SQLException {
        Book book = null;
        List<Book> books = super.mySQLTemplate.query("SELECT * FROM tbl_book WHERE bookId = ?;",
                new Object[] {bookId}, this);
        
        if (books.size() == 1) {
            book = books.get(0);
        }
        
        return book;
    }
    
    public void updateBook(int bookId, String title, int publisherId) throws ClassNotFoundException, SQLException {
        super.mySQLTemplate.update("UPDATE tbl_book SET title=?, pubId=? WHERE bookId = ?",
                new Object[] {title, publisherId, bookId});
    }
    
    public void deleteBook(int bookId) throws ClassNotFoundException, SQLException {
        super.mySQLTemplate.update("DELETE FROM tbl_book WHERE bookId = ?;",
                new Object[] {bookId});
    }

    @Override
    public List<Book> extractData(ResultSet rs) throws SQLException {
        List<Book> books = new ArrayList<>();
        
        while (rs.next()) {
            Book book = new Book();
            book.setBookId(rs.getInt("bookId"));
            book.setTitle(rs.getString("title"));
                    list.add(book);
        }
        
        return books;
    }

}
