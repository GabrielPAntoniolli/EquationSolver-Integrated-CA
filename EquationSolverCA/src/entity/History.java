/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entity;

/**
 *
 * Entity class that mimics the database table "history", it has all the attributes that the original table has.
 * This class is the one that handles the data taken from the database by creating the history object
 */
public class History {
    
    private Integer history_id;
    private Integer user_id;
    private String first_equation;
    private String second_equation;
    private String third_equation;
    private Integer status;

    public History(Integer history_id,Integer user_id, String first_equation,String second_equation,Integer status) {
        this.history_id = history_id;
        this.user_id = user_id;
        this.first_equation = first_equation;
        this.second_equation = second_equation;
        this.status = status;
    }
    
    public History(Integer history_id,Integer user_id, String first_equation,String second_equation,String third_equation ,Integer status) {
        this.history_id = history_id;
        this.user_id = user_id;
        this.first_equation = first_equation;
        this.second_equation = second_equation;
        this.third_equation = third_equation;
        this.status = status;
    }
    public History(Integer user_id, String first_equation,String second_equation,String third_equation ,Integer status) {
        this.user_id = user_id;
        this.first_equation = first_equation;
        this.second_equation = second_equation;
        this.third_equation = third_equation;
        this.status = status;
    }
    public History(Integer user_id, String first_equation,String second_equation,Integer status) {
        this.user_id = user_id;
        this.first_equation = first_equation;
        this.second_equation = second_equation;
        this.third_equation = third_equation;
        this.status = status;
    }
    

    public Integer getHistory_id() {
        return history_id;
    }

    public void setHistory_id(Integer history_id) {
        this.history_id = history_id;
    }

    public Integer getUser_id() {
        return user_id;
    }

    public void setUser_id(Integer user_id) {
        this.user_id = user_id;
    }

    public String getFirst_equation() {
        return first_equation;
    }

    public void setFirst_equation(String first_equation) {
        this.first_equation = first_equation;
    }

    public String getSecond_equation() {
        return second_equation;
    }

    public void setSecond_equation(String second_equation) {
        this.second_equation = second_equation;
    }

    public String getThird_equation() {
        return third_equation;
    }

    public void setThird_equation(String third_equation) {
        this.third_equation = third_equation;
    }

    

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "History{" + "history_id=" + history_id + ", user_id=" + user_id + ", first_equation=" + first_equation + ", second_equation=" + second_equation + ", third_equation=" + third_equation + ", status=" + status + '}';
    }
}