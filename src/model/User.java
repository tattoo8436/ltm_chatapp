
package model;

import java.io.Serializable;

public class User implements Serializable{
    private Long id;
    private String name;
    private boolean status;

    public User() {
    }

    public User(Long id, String name, boolean status) {
        this.id = id;
        this.name = name;
        this.status = status;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }
    @Override
    public String toString(){
        String stt = (this.status == true) ? "ON" : "OFF";
        return this.name + " - " + stt;
    }
}
