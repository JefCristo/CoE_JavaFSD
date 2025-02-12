package utility;

import java.util.List;
import java.util.Scanner;
import model.Accountant;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int choice = 0;
        while(true){
            System.out.println("\nWelcome to Fee Report Software");
            System.out.println("1. Admin Login");
            System.out.println("2. Accountant Login");
            System.out.println("3. Exit");
            System.out.print("Enter your choice: ");
            choice = Integer.parseInt(scanner.nextLine());
            switch(choice){
                case 1:
                    adminLogin(scanner);
                    break;
                case 2:
                    accountantLogin(scanner);
                    break;
                case 3:
                    System.out.println("Exiting...");
                    scanner.close();
                    System.exit(0);
                    break;
                default:
                    System.out.println("Invalid choice.");
            }
        }
    }
    
    // Admin login (using hardcoded credentials for simplicity)
    private static void adminLogin(Scanner scanner) {
        System.out.print("Enter admin username: ");
        String username = scanner.nextLine();
        System.out.print("Enter admin password: ");
        String password = scanner.nextLine();
        
        
        if(username.equals("admin") && password.equals("admin123")){
            System.out.println("Admin login successful.");
            adminMenu(scanner);
        } else {
            System.out.println("Invalid admin credentials.");
        }
    }
    
    
    private static void adminMenu(Scanner scanner) {
        AccountantDAO accDao = new AccountantDAO();
        int choice = 0;
        while(true){
            System.out.println("\n--- Admin Menu ---");
            System.out.println("1. Add Accountant");
            System.out.println("2. View Accountants");
            System.out.println("3. Update Accountant");
            System.out.println("4. Delete Accountant");
            System.out.println("5. Logout");
            System.out.print("Enter your choice: ");
            choice = Integer.parseInt(scanner.nextLine());
            switch(choice){
                case 1:
                    System.out.print("Enter Accountant Name: ");
                    String name = scanner.nextLine();
                    System.out.print("Enter Accountant Email: ");
                    String email = scanner.nextLine();
                    System.out.print("Enter Accountant Phone: ");
                    String phone = scanner.nextLine();
                    System.out.print("Enter Accountant Password: ");
                    String accPassword = scanner.nextLine();
                    Accountant acc = new Accountant(0, name, email, phone, accPassword);
                    accDao.addAccountant(acc);
                    break;
                case 2:
                    List<Accountant> accountants = accDao.fetchAccountants();
                    System.out.println("--- Accountants List ---");
                    for(Accountant a : accountants){
                        System.out.println(a);
                    }
                    break;
                case 3:
                    System.out.print("Enter Accountant ID to update: ");
                    int updateId = Integer.parseInt(scanner.nextLine());
                    System.out.print("Enter New Accountant Name: ");
                    String newName = scanner.nextLine();
                    System.out.print("Enter New Accountant Email: ");
                    String newEmail = scanner.nextLine();
                    System.out.print("Enter New Accountant Phone: ");
                    String newPhone = scanner.nextLine();
                    System.out.print("Enter New Accountant Password: ");
                    String newPass = scanner.nextLine();
                    Accountant updAcc = new Accountant(updateId, newName, newEmail, newPhone, newPass);
                    accDao.updateAccountant(updAcc);
                    break;
                case 4:
                    System.out.print("Enter Accountant ID to delete: ");
                    int deleteId = Integer.parseInt(scanner.nextLine());
                    accDao.deleteAccountant(deleteId);
                    break;
                case 5:
                    System.out.println("Admin logged out.");
                    return;
                default:
                    System.out.println("Invalid choice.");
            }
        }
    }
    
    
    private static void accountantLogin(Scanner scanner) {
        System.out.print("Enter Accountant Email: ");
        String email = scanner.nextLine();
        System.out.print("Enter Accountant Password: ");
        String password = scanner.nextLine();
   
        System.out.println("Accountant login successful.");
        accountantMenu(scanner);
    }
    
   
    private static void accountantMenu(Scanner scanner) {
        feeReportDAO studentDao = new feeReportDAO();
        int choice = 0;
        while(true){
            System.out.println("\n--- Accountant Menu ---");
            System.out.println("1. Add Student");
            System.out.println("2. View Students");
            System.out.println("3. Check Due Fee (List Students with Due > 0)");
            System.out.println("4. Logout");
            System.out.print("Enter your choice: ");
            choice = Integer.parseInt(scanner.nextLine());
            switch(choice){
                case 1:
                    System.out.print("Enter Student Name: ");
                    String name = scanner.nextLine();
                    System.out.print("Enter Student Email: ");
                    String email = scanner.nextLine();
                    System.out.print("Enter Course: ");
                    String course = scanner.nextLine();
                    System.out.print("Enter Fee: ");
                    double fee = Double.parseDouble(scanner.nextLine());
                    System.out.print("Enter Paid Amount: ");
                    double paid = Double.parseDouble(scanner.nextLine());
                    double due = fee - paid;
                    System.out.print("Enter Address: ");
                    String address = scanner.nextLine();
                    System.out.print("Enter Phone: ");
                    String phone = scanner.nextLine();
                    studentDao.addStudent(name, email, course, fee, paid, due, address, phone);
                    break;
                case 2:
                    System.out.println("--- Students List ---");
                    studentDao.fetchStudents();
                    break;
                case 3:
                    System.out.println("--- Students with Due Fee ---");
                    
                    System.out.println("Note: Please check the list above for students with due fee > 0.");
                    studentDao.fetchDueStudents();
                    break;
                case 4:
                    System.out.println("Accountant logged out.");
                    return;
                default:
                    System.out.println("Invalid choice.");
            }
        }
    }
}
