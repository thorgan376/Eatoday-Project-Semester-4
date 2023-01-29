package eatoday.com.model;

public class User {
    private String userName;
    private int idUser;
    private String password;
    private String email;

    public User(){

    }
    public User(String userName, int idUser, String password, String email) {
        this.userName = userName;
        this.idUser = idUser;
        this.password = password;
        this.email = email;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public int getIdUser() {
        return idUser;
    }

    public void setIdUser(int idUser) {
        this.idUser = idUser;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
