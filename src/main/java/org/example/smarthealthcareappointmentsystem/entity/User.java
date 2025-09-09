package org.example.smarthealthcareappointmentsystem.entity;

import jakarta.persistence.*;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import java.time.LocalDate;

/**
 * Represents a user entity in the mySql database
 * *
 */

@Entity
@Table(name="users")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "user_type")
//@org.hibernate.annotations.Cache(usage = CacheConcurrencyStrategy.READ_ONLY)

public class User  {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name="name",length = 50,nullable = false,unique = false)
    private String fullName;
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
    @ManyToOne()
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

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
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
                ", fullName='" + fullName + '\'' +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}
