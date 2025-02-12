 import java.util.*;


 interface ILibrary {
    void borrowBook(String ISBN, String userID) throws BookNotFoundException, UserNotFoundException, MaxBooksAllowedException ;
    void returnBook(String ISBN, String userID) throws BookNotFoundException, UserNotFoundException;
    void reserveBook(String ISBN, String userID) throws BookNotFoundException, UserNotFoundException;
    Book searchBook(String title);
} 

abstract class LibrarySystem implements ILibrary {
    List<Book> books=new ArrayList<>();;
    List<User> users=new ArrayList<>();;

    public abstract void addBook(Book book);
    public abstract void addUser(User user);
}

