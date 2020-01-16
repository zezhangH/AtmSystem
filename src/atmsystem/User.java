package atmsystem;

import java.io.Serializable;

public class User implements Serializable {
    private static final long serialVersionUID = 425834974605298933L;
    private String name;
    private String password;
    private Float ablance;

    public User() {
    }

    public User(String name, String password, Float ablance) {
        this.name = name;
        this.password = password;
        this.ablance = ablance;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Float getAblance() {
        return ablance;
    }

    public void setAblance(Float ablance) {
        this.ablance = ablance;
    }

}
