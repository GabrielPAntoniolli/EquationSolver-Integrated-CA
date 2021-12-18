/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dbRepository;

import entity.User;
import java.util.ArrayList;

/**
 *
 * This class Was made to make the validation of usernames and password when someone is trying to log in the application.
 */
public class UserRepositoryService {
    
    /**
     * Checks if the Username is already in the database or not 
     */
    
    public boolean validateUsername(String username){
    
        DbConnector connector = new DbConnector();
        ArrayList<User> userList = connector.getAllUser();
        
        for(User curr : userList){
        
            if(curr.getUsername().equals(username)){
                
                return true;
            }
        }
        return false;
    }
    
    /**
     * Checks if the Password is already in the database or not 
     */
    public boolean validatePassword(String password){
    
        DbConnector connector = new DbConnector();
        ArrayList<User> userList = connector.getAllUser();
        
        for(User curr : userList){
        
            if(curr.getPassword().equals(password)){
                
                return true;
            }
        }
        return false;
    }
    
    /**
     * Checks if both username and password are in the database.
     * @param username
     * @param password
     * @return 
     */
    public boolean validateUser(String username, String password){
    
        DbConnector connector = new DbConnector();
        ArrayList<User> userList = connector.getAllUser();
        
        for(User curr : userList){
        
            if(curr.getUsername().equals(username) && curr.getPassword().equals(password)){
                
                return true;
            }
        }
        return false;
    }
    
}