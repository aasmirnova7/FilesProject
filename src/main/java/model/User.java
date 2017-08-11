package model;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name="users")
public class User {
    @Id
    @Column(name = "id")
    //@GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Integer password;
    private String name;
    private String lastName;

    public User() {
        super();
    }
    public User(Long id, String name, String lastName, int password) {
        this.id = id;
        this.name = name;
        this.lastName = lastName;
        this.password = password;
    }
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public int getPassword() {
        return password;
    }

    public void setPassword(int password) {
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    @Override
    public String toString() {
        return "Person{" +
                "id=" + id +'\''+
                ", password="+password+'\''+
                ", name='" + name + '\'' +
                ", lasName=" + lastName+'\''+
                '}';
    }

}
