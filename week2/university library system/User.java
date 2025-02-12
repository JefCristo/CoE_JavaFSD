import java.io.*;
import java.util.*;


public class User implements Serializable{
    private String name;
    private String userID;
    private List<Book> borrowedBooks;

    User(String name,String userID){
        this.name=name;
        this.userID=userID;
        borrowedBooks= new ArrayList<Book>();
    }

    public void setUser(String name,String userID){
        this.name=name;
        this.userID=userID;
    }

    public String getName(){
        return this.name;
    }

    public String getID(){
        return this.userID;
    }

    public List<Book> getBooks(){
        return borrowedBooks;
    }

    public void addBorrowedBook(Book b){
        borrowedBooks.add(b);
    }

    public void removeBorrowedBook(Book b){
        borrowedBooks.remove(b);
    }

   

}
