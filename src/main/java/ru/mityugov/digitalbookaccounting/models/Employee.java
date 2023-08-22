package ru.mityugov.digitalbookaccounting.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;

@Entity
@Table(name = "employee")
public class Employee {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "username")
    @NotEmpty(message = "Username must not be empty")
    @Size(min = 5, max = 100, message = "Username must not be less than 5 and more than 100 characters")
    private String username;

    @Column(name = "password")
    @NotEmpty(message = "Password must not be empty")
    @Size(min = 8, message = "Username must not be less than 8 characters")
    private String password;

    /*@ManyToOne
    @JoinColumn(name = "branch_id", referencedColumnName = "id")
    private Branch branch;*/

    public int getId() {
        return id;
    }

    public void setId(int id) {
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

    /*public Branch getBranch() {
        return branch;
    }

    public void setBranch(Branch branch) {
        this.branch = branch;
    }*/
}
