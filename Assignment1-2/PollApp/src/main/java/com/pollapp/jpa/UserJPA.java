package com.pollapp.jpa;
import java.io.Serializable;
import javax.persistence.*;

@Entity
@Table(name="users")
public class UserJPA implements Serializable {
    @Id
    @Column(name = "UserId", nullable = false)
    private Long id;

    @Column(name="UserName")
    private String userName;

    @Column(name="Email")
    private String email;

    @Column(name="UserPassword")
    private String password;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }
    public void setUserName(String userName) {
        this.userName = userName;
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
}
