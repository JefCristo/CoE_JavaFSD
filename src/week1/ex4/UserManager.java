package week1.ex4;

import java.io.*;
import java.util.*;

class User {
    String name;
    String email;

    public User(String name, String email) {
        this.name = name;
        this.email = email;
    }
}

class UserManager {
    private List<User> users = new ArrayList<>();

    public void addUser(String name, String email) {
        users.add(new User(name, email));
    }

    public void saveUsersToFile(String filename) {
        try (ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(filename))) {
            out.writeObject(users);
        } catch (IOException e) {
            System.out.println("Error saving users: " + e.getMessage());
        }
    }

    public void loadUsersFromFile(String filename) {
        try (ObjectInputStream in = new ObjectInputStream(new FileInputStream(filename))) {
            users = (List<User>) in.readObject();
        } catch (IOException | ClassNotFoundException e) {
            System.out.println("Error loading users: " + e.getMessage());
        }
    }

    public static void main(String[] args) {
        UserManager manager = new UserManager();
        manager.addUser("Loki ", "loki@mcu.com");
        manager.addUser("Mighty Thor", "thor@mcu.com");

        String filename = "users.dat";
        manager.saveUsersToFile(filename);
        System.out.println("Users saved.");

        manager.loadUsersFromFile(filename);
        System.out.println("Users loaded:");
        for (User user : manager.users) {
            System.out.println(user.name + " - " + user.email);
        }
    }
}
