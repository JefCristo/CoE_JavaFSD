import java.io.*;

public class Book implements Serializable {
    private String title;
    private String author;
    private String ISBN;
 
   

    Book(String title,String author,String ISBN){
        this.title=title;
        this.author=author;
        this.ISBN=ISBN;
    }

    Book(){}

    public void setBook(String title,String author,String ISBN){
        this.title=title;
        this.author=author;
        this.ISBN=ISBN;
    }

    public String getAuthor(){
        return author;
       }

    public String getTitle(){
        return title;
       }

       public String getISBN(){
        return ISBN;
       }
 }