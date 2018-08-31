package com.quiz.models.db;


import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.List;


@Entity
@Table(name="userinfo")
public class User {

    public User() {
    }

    public User(int id) {
        this.id = id;
    }

    public User(String login) {
        this.login = login;
    }

    public User(String login, @NotNull String email, @NotNull String password) {
        this.login = login;
        this.email = email;
        this.password = password;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    @NotNull
    private int id;

    @Column(name = "login")
    private String login;

    @Column(name = "email")
    @NotNull
    private String email;

    @Column(name = "password")
    @NotNull
    private String password;

    @OneToMany(mappedBy="userinfo")
    private List<Quiz> Quizes;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public List<Quiz> getQuizes() {
        return Quizes;
    }

    public void setQuizes(List<Quiz> quizes) {
        Quizes = quizes;
    }
}
