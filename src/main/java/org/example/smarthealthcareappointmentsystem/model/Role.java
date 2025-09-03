package org.example.smarthealthcareappointmentsystem.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name="role")
public class Role {
    @Id
    @GeneratedValue
    private int id;
    private String name;
    @OneToMany(mappedBy = "role")
    private List<User>users;

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
