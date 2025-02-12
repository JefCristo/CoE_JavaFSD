package utility;

import java.sql.*;
import java.util.*;
import model.Accountant;  

public class AccountantDAO {

    
    public void addAccountant(Accountant accountant) {
        String query = "INSERT INTO accountant (name, email, phone, password) VALUES (?, ?, ?, ?)";
        try (Connection conn = DButil.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {
             
            stmt.setString(1, accountant.getName());
            stmt.setString(2, accountant.getEmail());
            stmt.setString(3, accountant.getPhone());
            stmt.setString(4, accountant.getPassword());
            
            int rowsInserted = stmt.executeUpdate();
            if (rowsInserted > 0) {
                System.out.println("Accountant added successfully!");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Method to fetch all accountants
    public List<Accountant> fetchAccountants() {
        List<Accountant> accountants = new ArrayList<>();
        String query = "SELECT * FROM accountant";
        try (Connection conn = DButil.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query);
             ResultSet rs = stmt.executeQuery()) {
             
            while (rs.next()) {
                Accountant acc = new Accountant();
                acc.setId(rs.getInt("id"));
                acc.setName(rs.getString("name"));
                acc.setEmail(rs.getString("email"));
                acc.setPhone(rs.getString("phone"));
                acc.setPassword(rs.getString("password"));
                accountants.add(acc);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return accountants;
    }

    // Method to update an accountant's details
    public void updateAccountant(Accountant accountant) {
        String query = "UPDATE accountant SET name = ?, email = ?, phone = ?, password = ? WHERE id = ?";
        try (Connection conn = DButil.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {
             
            stmt.setString(1, accountant.getName());
            stmt.setString(2, accountant.getEmail());
            stmt.setString(3, accountant.getPhone());
            stmt.setString(4, accountant.getPassword());
            stmt.setInt(5, accountant.getId());
            
            int rowsUpdated = stmt.executeUpdate();
            if (rowsUpdated > 0) {
                System.out.println("Accountant updated successfully!");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Method to delete an accountant by id
    public void deleteAccountant(int id) {
        String query = "DELETE FROM accountant WHERE id = ?";
        try (Connection conn = DButil.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {
             
            stmt.setInt(1, id);
            
            int rowsDeleted = stmt.executeUpdate();
            if (rowsDeleted > 0) {
                System.out.println("Accountant deleted successfully!");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
