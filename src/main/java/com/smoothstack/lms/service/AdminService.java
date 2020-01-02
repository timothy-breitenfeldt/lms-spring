package com.smoothstack.lms.service;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import com.smoothstack.lms.dao.AuthorDAO;
import com.smoothstack.lms.dao.BookDAO;
import com.smoothstack.lms.dao.GenreDAO;
import com.smoothstack.lms.dao.LibraryBranchDAO;
import com.smoothstack.lms.dao.PublisherDAO;
import com.smoothstack.lms.entity.Author;
import com.smoothstack.lms.entity.Book;
import com.smoothstack.lms.entity.BookLoan;
import com.smoothstack.lms.entity.Borrower;
import com.smoothstack.lms.entity.Genre;
import com.smoothstack.lms.entity.LibraryBranch;
import com.smoothstack.lms.entity.Publisher;

public class AdminService {
    
    
    @Autowired
    private BookDAO bookDAO;
    
    @Autowired
    private PublisherDAO publisherDAO;
    
    @Autowired
    private AuthorDAO authorDAO;
    
    @Autowired
    private GenreDAO genreDAO;
    
    @Autowired
    private LibraryBranchDAO libraryBranchDAO;

    public List<Author> getAuthors() {
        List<Author> authors = new ArrayList<>();

        try {
            authors = authorDAO.getAuthors();
            
            for (Author author : authors) {
                author.setBooks(this.bookDAO.getBooksByAuthorId(author.getAuthorId()));
            }
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
            System.out.println("Reading authors faiiled");
        }

        return authors;
    }

    @Transactional
    public String addBook(Book book) throws SQLException {
        try {
            Publisher publisher = this.publisherDAO.getPublisher(book.getPublisherId());
            int bookId = 0;
            
            if (publisher == null) {
                return "Invalid publisher ID.";
            }
            
            bookId = this.bookDAO.createBook(book.getTitle(), book.getPublisherId());
            
            if (book.getAuthors() != null) {
                for (Author author: book.getAuthors()) {
                    this.bookDAO.createBookAuthor(bookId, author.getAuthorId());
                }
            }
            
            if (book.getGenres() != null) {
                for (Genre genre: book.getGenres()) {
                    this.bookDAO.createBookGenre(bookId, genre.getGenreId());
                }
            }
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
            System.out.println("Adding Book failed");
        }
        
        return "Book added successfully";
    }
    
    @Transactional
    public String deleteBook(int bookId) throws SQLException {
        
        try {
            this.bookDAO.deleteBook(bookId);
    } catch(ClassNotFoundException | SQLException e) {
        e.printStackTrace();
        System.out.println("Adding Book failed");
    }
        
        return "Successfully deleted book.";
    }
    
    @Transactional
    public String updateBook(Book book) throws SQLException {
        try {
            Publisher publisher = this.publisherDAO.getPublisher(book.getPublisherId());

            if (publisher == null) {
                return "Invalid publisher ID.";
            }
            
            this.bookDAO.updateBook(book.getBookId(), book.getTitle(), book.getPublisherId());
            
            if (book.getAuthors() != null) {
                for (Author author: book.getAuthors()) {
                    this.bookDAO.updateBookAuthor(book.getBookId(), author.getAuthorId());
                }
            }
            
            if (book.getGenres() != null) {
                for (Genre genre: book.getGenres()) {
                    this.bookDAO.updateBookGenre(book.getBookId(), genre.getGenreId());
                }
            }
    } catch(ClassNotFoundException | SQLException e) {
        e.printStackTrace();
        System.out.println("Adding Book failed");
    }
        
        return "Successfully updated book.";
    }
    
    @Transactional
    public String addAuthor(Author author) {
        return "";
    }
    
    @Transactional
    public String updateAuthor(Author author) {
        return "";
    }

    @Transactional
    public String deleteAuthor(int authorId) {
        return "";
    }
    
    @Transactional
    public String addPublisher(Publisher publisher) {
        return "";
    }
    
    @Transactional
    public String updatePublisher(Publisher publisher) {
        return "";
    }
    
    @Transactional
    public String deletePublisher(int publisherId) {
        return "";
    }
    
    @Transactional
    public String addLibraryBranch(LibraryBranch libraryBranch) {
        return "";
    }
    
    @Transactional
    public String updateLibraryBranch(LibraryBranch libraryBranch) {
        return "";
    }
    
    @Transactional
    public String deleteLibraryBranch(int branchId) {
        return "";
    }
    
    @Transactional
    public String addBorrower(Borrower borrower) {
        return "";
    }
    
    @Transactional
    public String updateBorrower(Borrower borrower) {
        return "";
    }
    
    @Transactional
    public String deleteBorrower(int cardNumber) {
        return "";
    }
    
    @Transactional
    public String overrideDueDate(BookLoan bookLoan) {
        return "";
    }
    
}

