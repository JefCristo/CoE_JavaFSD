import java.io.*;
import java.util.List;


public class LibraryManager extends LibrarySystem {
    public void addUser(User user){
        users.add(user);
    }

    public void addBook(Book book){
        books.add(book);
    }


   public void borrowBook(String ISBN,String userID) throws BookNotFoundException, UserNotFoundException, MaxBooksAllowedException{
        Book foundBook = null;

        foundBook = books.stream()
            .filter(book->book.getISBN().equals(ISBN))
            .findFirst()
            .orElse(null);
            if(foundBook==null){
                throw new BookNotFoundException("Book with ISBN " + ISBN + " not found.");
            }

        User foundUser = null;
        foundUser =  users.stream()
            .filter(user->user.getID().equals(userID))
            .findFirst()
            .orElse(null);
            if(foundUser==null){
                throw new UserNotFoundException("User with ID " + userID + " not found.");

            }

            if(foundUser.getBooks().size()>=5){
                throw new MaxBooksAllowedException("User has reached maximum number of books");
            }

            
            foundUser.addBorrowedBook(foundBook);

    }
   
public Book searchBookByISBN(String ISBN) {
    return books.stream()
                .filter(book -> book.getISBN().equals(ISBN))
                .findFirst()
                .orElse(null);
}


public User getUserByID(String userID) {
    return users.stream()
                .filter(user -> user.getID().equals(userID))
                .findFirst()
                .orElse(null);
}


public void borrowBookAndPrint(String ISBN, String userID) {
    try {
       
        borrowBook(ISBN, userID);
        
      
        Book borrowedBook = searchBookByISBN(ISBN);
        User borrower = getUserByID(userID);
        
        if (borrowedBook != null && borrower != null) {
            System.out.printf("%s borrowed \"%s\" (ISBN: %s).%n",
                              borrower.getName(), borrowedBook.getTitle(), borrowedBook.getISBN());
        }
    } catch(Exception e) {
        System.err.println("Error borrowing book: " + e.getMessage());
    }
}


    public void returnBook(String ISBN, String userID) throws BookNotFoundException, UserNotFoundException{
        User foundUser = null;

        foundUser=users.stream()
             .filter(user->user.getID().equals(userID))
             .findFirst()
             .orElse(null);

        if(foundUser==null){
            throw new UserNotFoundException("User with ID " + userID + " not found.");
        }

        Book foundBook = foundUser.getBooks().stream()
                                            .filter(book->book.getISBN().equals(ISBN))
                                            .findFirst()
                                            .orElse(null);
                                            if(foundBook==null){
                                                throw new BookNotFoundException("Book with ISBN " + ISBN + " not found.");
                                            }
                                        
        foundUser.removeBorrowedBook(foundBook);


        
    }


    public void reserveBook(String ISBN, String userID) throws BookNotFoundException, UserNotFoundException{
        Book foundBook = null;

        foundBook = books.stream()
            .filter(book->book.getISBN().equals(ISBN))
            .findFirst()
            .orElse(null);
            if(foundBook==null){
                throw new BookNotFoundException("Book with ISBN " + ISBN + " not found.");
            }

        User foundUser = null;
        foundUser =  users.stream()
            .filter(user->user.getID().equals(userID))
            .findFirst()
            .orElse(null);
            if(foundUser==null){
                throw new UserNotFoundException("User with ID " + userID + " not found.");

            }

            System.out.println("Book with ISBN " + ISBN + " has been reserved for user " + userID + ".");


    }

    public Book searchBook(String title){

        Book b = books.stream()
            .filter(book->book.getTitle().equalsIgnoreCase(title))
            .findFirst()
            .orElse(null);

        if(b==null){
            System.out.println("Book with title " + title + " not found.");
        }
        
        return b;
        
    }

    public void saveData(String fileName){
        try {
            FileOutputStream fout = new FileOutputStream(fileName);
            ObjectOutputStream oOut = new ObjectOutputStream(fout);
            oOut.writeObject(books);
            oOut.writeObject(users);
            System.out.println("Library data saved successfully.");
        }
        catch(IOException e){
            System.err.println("Error saving library data: " + e.getMessage());
            e.printStackTrace();

        }
    }

    public void loadData(String fileName){
        File file = new File(fileName);

        if (!file.exists()) {
            System.out.println("No saved data found. Starting with an empty library.");
            return;
        }

        try (FileInputStream fis = new FileInputStream(fileName);
             ObjectInputStream ois = new ObjectInputStream(fis)) {
             
           
            books = (List<Book>) ois.readObject();
            users = (List<User>) ois.readObject();
            System.out.println("Library data loaded successfully.");
            
        } catch (IOException | ClassNotFoundException e) {
            System.err.println("Error loading library data: " + e.getMessage());
            e.printStackTrace();
        }
    }
       
    }



