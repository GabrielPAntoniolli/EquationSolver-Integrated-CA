/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dbRepository;


import entity.History;
import entity.User;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

/**
 * 
 * @author Gabriel Antoniolli
 * 
 * This Class establishes the connection between the application and the database
 */
public class DbConnector {

    /**
     * 
     * @return A open Connection with the database.
     * @throws ClassNotFoundException
     * @throws SQLException 
     */
    private Connection openConnection() throws ClassNotFoundException, SQLException{
            Class.forName("com.mysql.cj.jdbc.Driver");
            String username = "root";
            String password = "root"; // YOU MIGHT WANNA CHANGE THESE IN CASE YOURS IS DIFFERENT
            String dbServer = "jdbc:mysql://localhost:3306/equationsolver";
            Connection conn = DriverManager.getConnection(dbServer, username, password);
            return conn;
    }
    /**
     * Return the user that has this certain Id
     * @param id
     * @return User
     */
    public User getUserById(int id) {
        boolean is_admin = false;
        try {
            User idUser = null;
            String query = "SELECT * FROM user WHERE user_id= ?";

            Connection conn = openConnection();
            PreparedStatement stmt = conn.prepareStatement(query);

            stmt.setString(1, String.valueOf(id));
            ResultSet rs = stmt.executeQuery();
            rs.next();

            if (rs.getString("is_admin").equals("1")) {
                is_admin = true;
            }

            idUser = new User(Integer.parseInt(rs.getString("user_id")),
                    rs.getString("name"), rs.getString("surname"), Integer.parseInt(rs.getString("age")), rs.getString("username"),
                    rs.getString("password"), is_admin);

            stmt.close();
            conn.close();
            return idUser;
        } catch (SQLException se) {
            System.out.println("SQL Exception:");
            while (se != null) {
                System.out.println("State  : " + se.getSQLState());
                System.out.println("Message: " + se.getMessage());
                System.out.println("Error  : " + se.getErrorCode());
                se = se.getNextException();

            }
        } catch (Exception e) {
            System.out.println(e);
        }

        return null;
    }
    
    /**
     * Gets user that has this certain username
     * @param username
     * @return User
     */
    public User getUserByUsername(String username) {
        boolean is_admin = false;
        try {
            User currUser = null;
            String query = "SELECT * FROM user WHERE username= ?";
            Connection conn = openConnection();
            PreparedStatement stmt = conn.prepareStatement(query);

            stmt.setString(1, username);
            ResultSet rs = stmt.executeQuery();
            rs.next();

            if (rs.getString("is_admin").equals("1")) {
                is_admin = true;
            }

            currUser = new User(Integer.parseInt(rs.getString("user_id")),
                    rs.getString("name"), rs.getString("surname"), Integer.parseInt(rs.getString("age")), rs.getString("username"),
                    rs.getString("password"), is_admin);

            stmt.close();
            conn.close();
            return currUser;
        } catch (SQLException se) {
            System.out.println("SQL Exception:");
            while (se != null) {
                System.out.println("State  : " + se.getSQLState());
                System.out.println("Message: " + se.getMessage());
                System.out.println("Error  : " + se.getErrorCode());
                se = se.getNextException();

            }
        } catch (Exception e) {
            System.out.println(e);
        }

        return null;
    }

    /**
     * Get a list of all users in the database
     * @return ArrayList of all users.
     */
    public ArrayList<User> getAllUser() {
        boolean is_admin = false;
        ArrayList<User> userList = new ArrayList<>();
        try {
            String query = "SELECT * FROM user";

            Connection conn = openConnection();
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(query);

            while (rs.next()) {
                if (rs.getString("is_admin").equals("1")) {
                    is_admin = true;
                }
                userList.add(new User(Integer.parseInt(rs.getString("user_id")),
                        rs.getString("name"), rs.getString("surname"), Integer.parseInt(rs.getString("age")),
                        rs.getString("username"), rs.getString("password"), is_admin));
                is_admin = false;
            }
            rs.close();
            stmt.close();
            conn.close();
        } catch (SQLException se) {
            System.out.println("SQL Exception:");
            while (se != null) {
                System.out.println("State  : " + se.getSQLState());
                System.out.println("Message: " + se.getMessage());
                System.out.println("Error  : " + se.getErrorCode());
                se = se.getNextException();
            }
        } catch (Exception e) {
            System.out.println(e);
        }
        return userList;
    }

    /**
     * Save a user to a database
     * @param userEntity 
     */
    public void saveUser(User userEntity) {

        try {
            String query = "insert into equationsolver.user"
                    + "(name,surname,age,username,password,is_admin)"
                    + "values (?,?,?,?,?,?)";

            Connection conn = openConnection();
            PreparedStatement stmt = conn.prepareStatement(query);

            stmt.setString(1, userEntity.getName());
            stmt.setString(2, userEntity.getSurname());
            stmt.setString(3, String.valueOf(userEntity.getAge()));
            stmt.setString(4, userEntity.getUsername());
            stmt.setString(5, userEntity.getPassword());
            if (userEntity.getIsAdmin() == false) {
                stmt.setString(6, String.valueOf(0));
            }
            stmt.execute();
            stmt.close();
            System.out.println("user successfully saved");
            conn.close();

        } catch (SQLException se) {
            System.out.println("SQL Exception:");
            while (se != null) {
                System.out.println("State  : " + se.getSQLState());
                System.out.println("Message: " + se.getMessage());
                System.out.println("Error  : " + se.getErrorCode());
                se = se.getNextException();
            }
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    /**
     * Update the data of a certain user
     * 
     * @param id
     * @param newName
     * @param newSurname
     * @param newAge
     * @param newUsername
     * @param newPassword 
     */
    public void updateUser(int id, String newName, String newSurname, int newAge, String newUsername, String newPassword) {

        try {
            String query = "UPDATE user SET name=?, surname=?, age=?,username=?,password=? WHERE user_id=?";

            Connection conn = openConnection();
            PreparedStatement stmt = conn.prepareStatement(query);

            stmt.setString(1, newName);
            stmt.setString(2, newSurname);
            stmt.setString(3, String.valueOf(newAge));
            stmt.setString(4, newUsername);
            stmt.setString(5, newPassword);
            stmt.setString(6, String.valueOf(id));

            stmt.execute();
            stmt.close();
            System.out.println("user successfully updated");
            conn.close();

        } catch (SQLException se) {
            System.out.println("SQL Exception:");
            while (se != null) {
                System.out.println("State  : " + se.getSQLState());
                System.out.println("Message: " + se.getMessage());
                System.out.println("Error  : " + se.getErrorCode());
                se = se.getNextException();
            }
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    /**
     * give or take to a user admin status.
     * @param id
     * @param isAdmin 
     */
    public void updateAccess(int id, boolean isAdmin) {

        try {
            
            String query = "UPDATE user SET is_admin=? WHERE user_id=?";

            Connection conn = openConnection();
            PreparedStatement stmt = conn.prepareStatement(query);

            stmt.setString(1, String.valueOf(isAdmin));
            stmt.setString(2, String.valueOf(id));

            stmt.execute();
            stmt.close();
            System.out.println("user successfully updated");
            conn.close();

        } catch (SQLException se) {
            System.out.println("SQL Exception:");
            while (se != null) {
                System.out.println("State  : " + se.getSQLState());
                System.out.println("Message: " + se.getMessage());
                System.out.println("Error  : " + se.getErrorCode());
                se = se.getNextException();
            }
        } catch (Exception e) {
            System.out.println(e);
        }

    }

    /**
     * Remove from the database a user that has this id.
     * @param id 
     */
    public void removeUserById(int id) {

        try {
            
            String query = "delete from user where user_id =?";

            Connection conn = openConnection();
            PreparedStatement stmt = conn.prepareStatement(query);

            stmt.setString(1, String.valueOf(id));
            stmt.execute();

            stmt.close();
            conn.close();
        } catch (SQLException se) {
            System.out.println("SQL Exception:");
            while (se != null) {
                System.out.println("State  : " + se.getSQLState());
                System.out.println("Message: " + se.getMessage());
                System.out.println("Error  : " + se.getErrorCode());
                se = se.getNextException();

            }
        } catch (Exception e) {
            System.out.println(e);
        }

    }

    /**
     * Get all the old operations that the user with this id did.
     * @param id
     * @return 
     */
    public ArrayList<History> getAllHistoryByUserId(int id) {
        ArrayList<History> historyList = new ArrayList<>();
        try {
            String query = "SELECT * FROM history WHERE user_id=?";

            Connection conn = openConnection();
            PreparedStatement stmt = conn.prepareStatement(query);
            
            stmt.setString(1, String.valueOf(id));
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                historyList.add(new History(Integer.parseInt(rs.getString("history_id")), Integer.parseInt(rs.getString("user_id")),
                        rs.getString("first_equation"), rs.getString("second_equation"), 
                        rs.getString("third_equation"),Integer.parseInt(rs.getString("status_id"))));
            }
            rs.close();
            stmt.close();
            conn.close();
        } catch (SQLException se) {
            System.out.println("SQL Exception:");
            while (se != null) {
                System.out.println("State  : " + se.getSQLState());
                System.out.println("Message: " + se.getMessage());
                System.out.println("Error  : " + se.getErrorCode());
                se = se.getNextException();
            }
        } catch (Exception e) {
            System.out.println(e);
        }
        return historyList;
    }

    /**
     * Save a operation 2x2 to the database.
     * @param historyEntity 
     */
    public void saveHistory2x2(History historyEntity) {

        try {
            String query = "insert into equationsolver.history"
                    + "(user_id,first_equation,second_equation,status_id)"
                    + "values (?,?,?,?)";

            Connection conn = openConnection();
            PreparedStatement stmt = conn.prepareStatement(query);

            stmt.setString(1, String.valueOf(historyEntity.getUser_id()));
            stmt.setString(2, historyEntity.getFirst_equation());
            stmt.setString(3, historyEntity.getSecond_equation());
            stmt.setString(4, String.valueOf(historyEntity.getStatus()));

            stmt.execute();
            stmt.close();
            System.out.println("history successfully saved");
            conn.close();

        } catch (SQLException se) {
            System.out.println("SQL Exception:");
            while (se != null) {
                System.out.println("State  : " + se.getSQLState());
                System.out.println("Message: " + se.getMessage());
                System.out.println("Error  : " + se.getErrorCode());
                se = se.getNextException();
            }
        } catch (Exception e) {
            System.out.println(e);
        }

    }

    /**
     * Save an operation 3x3 to the database.
     * @param historyEntity 
     */
    public void saveHistory3x3(History historyEntity) {

        try {
            String query = "insert into equationsolver.history"
                    + "(user_id,first_equation,second_equation,third_equation,status_id)"
                    + "values (?,?,?,?,?)";

            Connection conn = openConnection();
            PreparedStatement stmt = conn.prepareStatement(query);

            stmt.setString(1, String.valueOf(historyEntity.getUser_id()));
            stmt.setString(2, historyEntity.getFirst_equation());
            stmt.setString(3, historyEntity.getSecond_equation());
            stmt.setString(4, historyEntity.getThird_equation());
            stmt.setString(5, String.valueOf(historyEntity.getStatus()));

            stmt.execute();
            stmt.close();
            System.out.println("history successfully saved");
            conn.close();

        } catch (SQLException se) {
            System.out.println("SQL Exception:");
            while (se != null) {
                System.out.println("State  : " + se.getSQLState());
                System.out.println("Message: " + se.getMessage());
                System.out.println("Error  : " + se.getErrorCode());
                se = se.getNextException();
            }
        } catch (Exception e) {
            System.out.println(e);
        }

    }

    /**
     * remove operation from the database by its id.
     * @param id 
     */
    public void removeHistoryById(int id) {

        try {
            String query = "delete from history where history_id =?";

            Connection conn = openConnection();
            PreparedStatement stmt = conn.prepareStatement(query);

            stmt.setString(1, String.valueOf(id));
            stmt.execute();

            stmt.close();
            conn.close();
        } catch (SQLException se) {
            System.out.println("SQL Exception:");
            while (se != null) {
                System.out.println("State  : " + se.getSQLState());
                System.out.println("Message: " + se.getMessage());
                System.out.println("Error  : " + se.getErrorCode());
                se = se.getNextException();

            }
        } catch (Exception e) {
            System.out.println(e);
        }

    }
}

    
    

