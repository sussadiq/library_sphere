package com.library;

import com.library.entities.Book;
import com.library.entities.User;
import com.library.entities.Transaction;
import com.library.services.BookService;
import com.library.services.UserService;
import com.library.services.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;

import java.time.LocalDate;
import java.util.List;

@ShellComponent
public class LibraryCLI {

    @Autowired
    private UserService userService;

    @Autowired
    private BookService bookService;

    @Autowired
    private TransactionService transactionService;
    @ShellMethod(value = "Add a user", key = "add-user")
    public String addUser(String name, String email, String role) {
        User user = new User();
        user.setName(name);
        user.setEmail(email);
        user.setRole(User.Role.valueOf(role.toUpperCase()));
        userService.createUser(user);
        return "User added successfully: " + user.getName();
    }

    @ShellMethod(value = "List all users", key = "list-users")
    public List<User> listUsers() {
        return userService.getAllUsers();
    }

    // Book Commands
    @ShellMethod(value = "Add a book", key = "add-book")
    public String addBook(String title, String author, String genre, int publicationYear, boolean available) {
        Book book = new Book();
        book.setTitle(title);
        book.setAuthor(author);
        book.setGenre(genre);
        book.setPublicationYear(publicationYear);
        book.setAvailable(available);
        bookService.addBook(book);
        return "Book added successfully: " + book.getTitle();
    }

    @ShellMethod(value = "List all books", key = "list-books")
    public List<Book> listBooks() {
        return bookService.getAllBooks();
    }

    // Transaction Commands
    @ShellMethod(value = "Issue a book", key = "issue-book")
    public String issueBook(Long userId, Long bookId, String issuedAt, String dueDate) {
        Transaction transaction = new Transaction();
        transaction.setUser(userService.getUserById(userId));
        transaction.setBook(bookService.getBookById(bookId));
        transaction.setIssuedAt(LocalDate.parse(issuedAt));
        transaction.setDueDate(LocalDate.parse(dueDate));
        transactionService.issueBook(transaction);
        return "Book issued successfully to user ID: " + userId;
    }

    @ShellMethod(value = "List all transactions", key = "list-transactions")
    public List<Transaction> listTransactions() {
        return transactionService.getAllTransactions();
    }
}
