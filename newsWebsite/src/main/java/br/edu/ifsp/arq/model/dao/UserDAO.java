package br.edu.ifsp.arq.model.dao;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import br.edu.ifsp.arq.model.entity.User;

public class UserDAO {

	private static UserDAO instance;
    private final String fileCSV = "data/usersData.csv";
    private Long counter = 0L;

    private UserDAO() {}

    public static UserDAO getInstance() {
        if (instance == null) {
            instance = new UserDAO();
        }
        return instance;
    }
    
    public void addUser(User user) {
    	try {    		
    		File f = new File(fileCSV);
			FileWriter fw = new FileWriter(f, true);
			System.out.println(f.getAbsolutePath());
			PrintWriter pw = new PrintWriter(fw);
			var id = counter + 1;
			user.setId(id);
			pw.println(user);
			pw.close();
			fw.close();
		}  catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    public List<User> getUsers() {
        List<User> users = new ArrayList<>();
        
        try (BufferedReader reader = new BufferedReader(new FileReader(new File(fileCSV)))) {
            String row;
            while ((row = reader.readLine()) != null) {
                String[] parts = row.split(";");
                if (parts.length == 4) {
                    Long id = Long.parseLong(parts[0]);
                    String username = parts[1];
                    String email = parts[2];
                    String password = parts[3];
                    users.add(new User(id, username, email, password));
                }
            }
            reader.close();            
        } catch (IOException e) {
            e.printStackTrace();
        }
        return users;
    }
    
    private void deleteFile() {
    	try {
			FileWriter fw = new FileWriter(fileCSV, false);
			PrintWriter pw = new PrintWriter(fw);			
			pw.print("");
			pw.close();
			fw.close();
		}  catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    public User getUserByEmail(String email) {
        List<User> users = getUsers();
        for (User u : users) {
            if (u.getEmail().compareTo(email) == 0) {
                return u;
            }
        }        
        return null;
    }
    
    public User getUserById(Long id) {
        List<User> users = getUsers();
        for (User u : users) {
            if (u.getId() == id) {
                return u;
            }
        }        
        return null;
    }

    
    public void editUser(User userEdited) {
        List<User> users = getUsers();
        deleteFile();

        for (User u: users) {
            if (u.getId() == userEdited.getId()) {
                u.setUsername(userEdited.getUsername());
                u.setEmail(userEdited.getEmail());
                u.setPassword(userEdited.getPassword());
            }
            addUser(u);
        }
    }

    public void deleteUser(User userToDelete) {
        List<User> users = getUsers();
        deleteFile();

        for (User u : users) {
            if (u.getId() != userToDelete.getId()) {
                addUser(u);
            }
        }    
    }
    
}
