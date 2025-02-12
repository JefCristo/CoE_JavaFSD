package utility;

import java.util.List;
import model.Accountant;

public class TestAccountant {
    public static void main(String[] args) {
        AccountantDAO accountantDAO = new AccountantDAO();
        
       
        Accountant acc1 = new Accountant(0, "Alice Brown", "alice.brown@example.com", "1234567890", "pass123");
        Accountant acc2 = new Accountant(0, "Bob Smith", "bob.smith@example.com", "0987654321", "pass456");
        Accountant acc3 = new Accountant(0, "Charlie Davis", "charlie.davis@example.com", "1122334455", "pass789");
        
        accountantDAO.addAccountant(acc1);
        accountantDAO.addAccountant(acc2);
        accountantDAO.addAccountant(acc3);
        
      
        System.out.println("Accountants after addition:");
        List<Accountant> accountants = accountantDAO.fetchAccountants();
        for (Accountant acc : accountants) {
            System.out.println(acc);
        }
        
  
        acc1.setPhone("1111111111");
        accountantDAO.updateAccountant(acc1);
        
        System.out.println("\nAccountants after update:");
        accountants = accountantDAO.fetchAccountants();
        for (Accountant acc : accountants) {
            System.out.println(acc);
        }
        
     
        accountantDAO.deleteAccountant(acc2.getId());
        
        System.out.println("\nAccountants after deletion:");
        accountants = accountantDAO.fetchAccountants();
        for (Accountant acc : accountants) {
            System.out.println(acc);
        }
    }
}
