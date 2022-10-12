
package model;

import java.io.Serializable;

public class UserInput implements Serializable{
    private Long id;
    private String username;
    private String password;
    private String fullName;
    
    public UserInput(String username, String password) {
        this.username = username;
        this.password = password;
    }
    
    public UserInput(String username, String password, String name) {
        this.username = username;
        this.password = password;
        this.fullName = name;
    }

    public UserInput(Long id, String username, String password, String name) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.fullName = name;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getName() {
        return fullName;
    }

    public void setName(String name) {
        this.fullName = name;
    }
    
}
