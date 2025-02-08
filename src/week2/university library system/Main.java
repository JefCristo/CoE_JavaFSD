import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
       
        LibraryManager libManager = new LibraryManager();
        libManager.loadData("libraryData.dat");

        Scanner scanner = new Scanner(System.in);
        boolean exit = false;

        while (!exit) {
            
            System.out.println("\n--- Library System Menu ---");
            System.out.println("1. Add Book");
            System.out.println("2. Add User");
            System.out.println("3. Borrow Book");
            System.out.println("4. Return Book");
            System.out.println("5. Reserve Book");
            System.out.println("6. Search Book");
            System.out.println("7. Exit and Save Data");
            System.out.print("Enter your choice: ");
            
            int choice;
            try {
                choice = Integer.parseInt(scanner.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter a number from 1 to 7.");
                continue;
            }
            
            switch (choice) {
                case 1:
                    
                    System.out.print("Enter Book Title: ");
                    String title = scanner.nextLine();
                    System.out.print("Enter Book Author: ");
                    String author = scanner.nextLine();
                    System.out.print("Enter Book ISBN: ");
                    String isbn = scanner.nextLine();
                    
                    Book newBook = new Book(title, author, isbn);
                    libManager.addBook(newBook);
                    System.out.println("Book added successfully.");
                    break;
                    
                case 2:
                
                    System.out.print("Enter User Name: ");
                    String userName = scanner.nextLine();
                    System.out.print("Enter User ID: ");
                    String userId = scanner.nextLine();
                    
                    User newUser = new User(userName, userId);
                    libManager.addUser(newUser);
                    System.out.println("User added successfully.");
                    break;
                    
                case 3:
                    
                    System.out.print("Enter Book ISBN to borrow: ");
                    String borrowIsbn = scanner.nextLine();
                    System.out.print("Enter User ID: ");
                    String borrowUserId = scanner.nextLine();
                    
                    Thread borrowThread = new Thread(() -> {
                        try {
                            libManager.borrowBook(borrowIsbn, borrowUserId);
                            Book borrowedBook = libManager.searchBookByISBN(borrowIsbn);
                            
                            System.out.printf("User %s borrowed \"%s\".%n", borrowUserId, borrowedBook.getTitle());
                        } catch (Exception e) {
                            System.err.println("Borrow error: " + e.getMessage());
                        }
                    });
                    borrowThread.start();
                    try {
                        borrowThread.join();
                    } catch (InterruptedException e) {
                        System.err.println("Thread interrupted: " + e.getMessage());
                    }
                    break;
                    
                case 4:
                    
                    System.out.print("Enter Book ISBN to return: ");
                    String returnIsbn = scanner.nextLine();
                    System.out.print("Enter User ID: ");
                    String returnUserId = scanner.nextLine();
                    
                    Thread returnThread = new Thread(() -> {
                        try {
                            libManager.returnBook(returnIsbn, returnUserId);
                            System.out.printf("User %s returned the book with ISBN %s.%n", returnUserId, returnIsbn);
                        } catch (Exception e) {
                            System.err.println("Return error: " + e.getMessage());
                        }
                    });
                    returnThread.start();
                    try {
                        returnThread.join();
                    } catch (InterruptedException e) {
                        System.err.println("Thread interrupted: " + e.getMessage());
                    }
                    break;
                    
                case 5:
                    
                    System.out.print("Enter Book ISBN to reserve: ");
                    String reserveIsbn = scanner.nextLine();
                    System.out.print("Enter User ID: ");
                    String reserveUserId = scanner.nextLine();
                    
                    Thread reserveThread = new Thread(() -> {
                        try {
                            libManager.reserveBook(reserveIsbn, reserveUserId);
                            System.out.printf("User %s reserved the book with ISBN %s.%n", reserveUserId, reserveIsbn);
                        } catch (Exception e) {
                            System.err.println("Reservation error: " + e.getMessage());
                        }
                    });
                    reserveThread.start();
                    try {
                        reserveThread.join();
                    } catch (InterruptedException e) {
                        System.err.println("Thread interrupted: " + e.getMessage());
                    }
                    break;
                    
                case 6:
                    
                    System.out.print("Enter Book Title to search: ");
                    String searchTitle = scanner.nextLine();
                    Book foundBook = libManager.searchBook(searchTitle);
                    if (foundBook != null) {
                        System.out.printf("Book found: \"%s\" by %s (ISBN: %s)%n", 
                                foundBook.getTitle(), foundBook.getAuthor(), foundBook.getISBN());
                    } else {
                        System.out.println("Book not found.");
                    }
                    break;
                    
                case 7:
                    
                    libManager.saveData("libraryData.dat");
                    System.out.println("Data saved. Exiting the system.");
                    exit = true;
                    break;
                    
                default:
                    System.out.println("Invalid choice. Please select an option from 1 to 7.");
                    break;
            }
        }
        scanner.close();
    }
}

