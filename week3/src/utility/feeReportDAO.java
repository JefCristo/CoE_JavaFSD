package utility;

import java.sql.*;

public class feeReportDAO {
	 public void fetchStudents() {
	        String query = "SELECT * FROM student"; 
	        try (Connection conn = DButil.getConnection();
	             PreparedStatement ps = conn.prepareStatement(query);
	             ResultSet rs = ps.executeQuery()) {

	            while (rs.next()) {
	               
	                int id = rs.getInt("id");
	                String name = rs.getString("name");
	                String email = rs.getString("email");
	                String course = rs.getString("course");
	                double fee =rs.getDouble("fee");
	                double paid = rs.getDouble("paid");
	                double due = rs.getDouble("due");
	                String address = rs.getString("address");
	                String phone = rs.getString("phone");
	                System.out.println("ID: " + id + ", Name: " + name+"Email: "+email+"Course: "+course+"Fee: "+fee+"Paid: "+paid+ "Due: "+due+"Address: "+address+"Phone Number: "+phone);
	            }
	        } catch (SQLException e) {
	            e.printStackTrace();
	        }
	    }

	    public void addStudent(String name,String email,String course,double fee,double paid , double due , String address,String phone) {
	        String query = "INSERT INTO student (name,email,course,fee,paid,due,address,phone) VALUES (?, ?,?,?,?,?,?,?)";
	        try (Connection conn = DButil.getConnection();
	             PreparedStatement stmt = conn.prepareStatement(query)) {

	            stmt.setString(1, name);
	            stmt.setString(2, email);
	            stmt.setString(3,course);
	            stmt.setDouble(4, fee);
	            stmt.setDouble(5, paid);
	            stmt.setDouble(6, due);
	            stmt.setString(7, address);
	            stmt.setString(8,phone);
	            
	            stmt.executeUpdate();
	            System.out.println("Student added successfully!");
	        } catch (SQLException e) {
	            e.printStackTrace();
	        }
	    }
	    
	    public void fetchDueStudents() {
	        String query = "SELECT * FROM student WHERE due > 0";  // Only fetch students with due > 0
	        try (Connection conn = DButil.getConnection();
	             PreparedStatement ps = conn.prepareStatement(query);
	             ResultSet rs = ps.executeQuery()) {

	            while (rs.next()) {
	                int id = rs.getInt("id");
	                String name = rs.getString("name");
	                String email = rs.getString("email");
	                String course = rs.getString("course");
	                double fee = rs.getDouble("fee");
	                double paid = rs.getDouble("paid");
	                double due = rs.getDouble("due");
	                String address = rs.getString("address");
	                String phone = rs.getString("phone");
	                System.out.println("ID: " + id + ", Name: " + name + ", Email: " + email +
	                                   ", Course: " + course + ", Fee: " + fee + ", Paid: " + paid +
	                                   ", Due: " + due + ", Address: " + address + ", Phone: " + phone);
	            }
	        } catch (SQLException e) {
	            e.printStackTrace();
	        }
	    }

}
