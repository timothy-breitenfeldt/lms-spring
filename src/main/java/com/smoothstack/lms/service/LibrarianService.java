package com.smoothstack.lms.service;

import java.util.List;

import com.smoothstack.lms.entity.Book;
import com.smoothstack.lms.entity.BookCopy;
import com.smoothstack.lms.entity.LibraryBranch;

public class LibrarianService {
    
    public String updateBookCopy(int bookId, int libraryBranchId, int amount) {
        return "";
    }
    
    public String addBookCopy(int bookId, int libraryBranchId, int amount) {
        return "";
    }
    
    public String updateLibraryBranch(int id, String name, String address) {
        return "";
    }
    
    public List<Book> getBooks() {
        return null;
    }
    
    public List<LibraryBranch> getLibraryBranches() {
        return null;
    }
    
    public List<BookCopy> getBookCopies(int branchId) {
        return null;
    }
    
}
