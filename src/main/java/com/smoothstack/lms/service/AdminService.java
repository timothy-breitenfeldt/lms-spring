package com.smoothstack.lms.service;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.smoothstack.lms.dao.AuthorDAO;
import com.smoothstack.lms.dao.BookDAO;
import com.smoothstack.lms.dao.PublisherDAO;
import com.smoothstack.lms.entity.Author;
import com.smoothstack.lms.entity.Publisher;

public class AdminService {

    public List<Author> getAuthors() throws SQLException {
        List<Author> authors = new ArrayList<>();
        Connection connection = null;

        try {
            connection = DatabaseUtil.getConnection();
            AuthorDAO adao = new AuthorDAO(connection);
            authors = adao.getAuthors();
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
            System.out.println("Reading authors faiiled");
        } finally {
            if (connection != null) {
                connection.close();
            }
        }

        return authors;
    }

    public String addBook(String title, int publisherId) throws SQLException {
        Connection connection = null;
        
        try {
            connection = DatabaseUtil.getConnection();
            BookDAO bookDAO = new BookDAO(connection);
            PublisherDAO publisherDAO = new PublisherDAO(connection);
            Publisher publisher = publisherDAO.getPublisher(publisherId);

            if (publisher == null) {
                return "Invalid publisher ID.";
            }
            
            bookDAO.insertBook(title, publisherId);
            
            connection.commit();
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
            System.out.println("Adding Book failed");
            
            if (connection != null) {
                connection.rollback();
            }
        } finally {
            if (connection != null) {
                connection.close();
            }
        }
        
        return "Book added successfully";
    }
    
    public String deleteBook(int bookId) throws SQLException {
        Connection connection = null;
        
        try {
            connection = DatabaseUtil.getConnection();
            BookDAO bookDAO = new BookDAO(connection);
            bookDAO.deleteBook(bookId);
            connection.commit();
    } catch(ClassNotFoundException | SQLException e) {
        e.printStackTrace();
        System.out.println("Adding Book failed");
        
        if (connection != null) {
            connection.rollback();
        }
    } finally {
        if (connection != null) {
            connection.close();
        }
    }
        
        return "Successfully deleted book.";
    }
    
    public String updateBook(int bookId, String title, int publisherId) throws SQLException {
        Connection connection = null;
        
        try {
            connection = DatabaseUtil.getConnection();
            BookDAO bookDAO = new BookDAO(connection);
            PublisherDAO publisherDAO = new PublisherDAO(connection);
            Publisher publisher = publisherDAO.getPublisher(publisherId);

            if (publisher == null) {
                return "Invalid publisher ID.";
            }
            
            bookDAO.updateBook(bookId, title, publisherId);
            connection.commit();
    } catch(ClassNotFoundException | SQLException e) {
        e.printStackTrace();
        System.out.println("Adding Book failed");
        
        if (connection != null) {
            connection.rollback();
        }
    } finally {
        if (connection != null) {
            connection.close();
        }
    }
        
        return "Successfully updated book.";
    }
    
    public String addAuthor(String authorName) {
        return "";
    }
    
    public String updateAuthor(String authorId, String authorName) {
        return "";
    }

    public String deleteAuthor(int authorId) {
        return "";
    }
    
    public String addPublisher(String name, String address, String phone) {
        return "";
    }
    
    public String updatePublisher(int publisherId, String name, String address, String phone) {
        return "";
    }
    
    public String deletePublisher(int publisherId) {
        return "";
    }
    
    public String addLibraryBranch(String name, String address) {
        return "";
    }
    
    public String updateLibraryBranch(int branchId, String name, String address) {
        return "";
    }
    
    public String deleteLibraryBranch(int branchId) {
        return "";
    }
    
    public String addBorrower(String name, String address, String phone) {
        return "";
    }
    
    public String updateBorrower(int cardNumber, String name, String address, String phone) {
        return "";
    }
    
    public String deleteBorrower(int cardNumber) {
        return "";
    }
    
    public String overrideDueDate(int cardNumber, int bookId, int libraryBranchId, String dueDate) {
        return "";
    }
    
}

