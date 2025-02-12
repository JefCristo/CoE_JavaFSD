package model;

import java.io.*;

public class Student implements Serializable {
    private int id;
    private String name;
    private String email;
    private String course;
    private double fee;
    private double paid;
    private double due;
    private String address;
    private String phone;

    // Constructors
    public Student() {
    }

    public Student(int id, String name, String email, String course, double fee,
                   double paid, double due, String address, String phone) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.course = course;
        this.fee = fee;
        this.paid = paid;
        this.due = due;
        this.address = address;
        this.phone = phone;
    }

    // Getters and Setters
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    public String getCourse() {
        return course;
    }
    public void setCourse(String course) {
        this.course = course;
    }
    public double getFee() {
        return fee;
    }
    public void setFee(double fee) {
        this.fee = fee;
    }
    public double getPaid() {
        return paid;
    }
    public void setPaid(double paid) {
        this.paid = paid;
    }
    public double getDue() {
        return due;
    }
    public void setDue(double due) {
        this.due = due;
    }
    public String getAddress() {
        return address;
    }
    public void setAddress(String address) {
        this.address = address;
    }
    public String getPhone() {
        return phone;
    }
    public void setPhone(String phone) {
        this.phone = phone;
    }

   
    public String toString() {
        return "Student [id=" + id + ", name=" + name + ", email=" + email + ", course=" + course 
                + ", fee=" + fee + ", paid=" + paid + ", due=" + due + "]";
    }
}
