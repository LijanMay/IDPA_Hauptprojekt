/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ch.bbbaden.idpa_hauptprojekt;

/**
 *
 * @author denni
 */
public class User {
    private String username;
    private String email;
    private String password;
    private int status;

    public User(String username, String email, String password, int status) {
        this.username = username;
        this.email = email;
        this.password = password;
        this.status = status;
    }

    public String getUsername() {
        return username;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public int getStatus() {
        return status;
    }
    
    
    
    
}
