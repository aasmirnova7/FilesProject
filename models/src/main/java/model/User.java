package model;

import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;
import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name="users")
public class User {
    @Id
    @Column(name = "id")
    private String id;
    //поменяли integer - string
    private String password;
    private String name;
    private String lastName;
    @OneToMany(mappedBy = "user")//,orphanRemoval = true)//,cascade = CascadeType.ALL)
    @Cascade(CascadeType.ALL)
    private Set<FilesStore> filesStore;

    public User() {
        super();
    }
    public User(String id, String name, String lastName, String password) {
        this.id = id;
        this.name = name;
        this.lastName = lastName;
        this.password = password;
    }

    public String getId() {
        return id;
    }
    public void setId(String  id) {
        this.id = id;
    }
    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
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
    public Set<FilesStore> getFilesStore() {
        return filesStore;
    }
    public void setFilesStore(Set<FilesStore> filesStore) {
        this.filesStore = filesStore;
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
