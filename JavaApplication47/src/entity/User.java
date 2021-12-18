/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entity;
/*
 *
 * Entity class that mimics the database table "user", it has all the attributes that the original table has.
 * This class is the one that handles the data taken from the database by creating the user object
 */
public class User{
    
    private int id;
    private String name;
    private String surname;
    private int age;
    private String username;
    private String password;
    private boolean isAdmin;
    
    public User(String name, String surname,int age,String username,String password, boolean isAdmin){
        this.name = name;
        this.surname = surname;
        this.age = age;
        this.username = username;
        this.password = password;
        this.isAdmin = isAdmin;
    }
    
    public User(int id,String name, String surname, int age,String username,String password, boolean isAdmin){
        this.id = id;
        this.name = name;
        this.surname = surname;
        this.age = age;
        this.username = username;
        this.password = password;
        this.isAdmin = isAdmin;
    }

    public int getId() {
        return id;
    }
   
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
    
    public String getPassword(){
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public boolean getIsAdmin() {
        return isAdmin;
    }

    @Override
    public String toString() {
        return "User{" + "id=" + id + ", name=" + name + ", surname=" + surname + ", age=" + age + ", username=" + username + ", password=" + password + ", isadmin=" + isAdmin + '}';
    }
    
    
    
}