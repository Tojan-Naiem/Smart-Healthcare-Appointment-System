package org.example.smarthealthcareappointmentsystem.entity;

import jakarta.persistence.*;

import java.time.LocalDate;

/**
 * Represents a user entity in the mySql database
 * *
 */

@Entity
@Table(name="users")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "user_type")
public class User  {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name="name",length = 50,nullable = false,unique = false)
    private String name;
    /**
     * The username for the user
     * This field cannot be null and should be unique
     */
    @Column(nullable = false,unique = true)
    private String username;
    /**
     * The email for the user
     * This field cannot be null and should be unique
     */
    @Column(nullable = false,unique = true)
    private String email;
    /**
     * The password for the user
     * This field cannot be null
     */
    @Column(nullable = false)
    private String password;
    /**
     * The birthday can't be null
     */
    @Column(nullable = false)
    private LocalDate birthday;
    /**
     * The Role for the user
     * This field cannot be null
     */
    @ManyToOne(fetch = FetchType.LAZY,cascade = CascadeType.ALL)
    @JoinColumn(name="role_id")
    private Role role;




    public User(){

    }


    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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

    public LocalDate getBirthday() {
        return birthday;
    }

    public void setBirthday(LocalDate birthday) {
        this.birthday = birthday;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    @Override
    public String toString() {
        return "User{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}
