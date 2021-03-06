package model;

import org.hibernate.annotations.*;
import org.hibernate.annotations.CascadeType;

import javax.persistence.*;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.util.*;

@Entity
@Table(name="store")
public class FilesStore {
    @Id
    @GeneratedValue(strategy=GenerationType.SEQUENCE, generator = "storeGenerator")
    @SequenceGenerator(name="storeGenerator", sequenceName="storeGenerator", allocationSize=1)
    private Integer number;
    private String fileName;
    private Integer privacy;// 0 - all,1 - owner, 2 - for some users
    private byte[] data;
    @OneToMany(mappedBy = "filesStore")//,orphanRemoval = true)//,cascade = CascadeType.ALL/*, orphanRemoval = true*/)
    @Cascade(CascadeType.ALL)
    private Set<SpecialAccessFilesStore> specialAccessFilesStores;
    @ManyToOne
    @JoinColumn(name="id")
    private User user;

    public FilesStore(){}
    public FilesStore(String fileName, Integer privacy, User user, byte[] data){
        this.fileName = fileName;
        this.setUser(user);
        this.privacy = privacy;
        this.data = data;

    }

    public String getFileName() {
        return fileName;
    }
    public void setFileName(String fileName) {
        this.fileName = fileName;
    }
    public Integer getPrivacy() {
        return privacy;
    }
    public void setPrivacy(Integer privacy) {
        this.privacy = privacy;
    }
    public Integer getNumber() {
        return number;
    }
    public void setNumber(Integer number) {
        this.number = number;
    }
    public byte[] getData() {
        return data;
    }
    public void setData(byte[] data) {
        this.data = data;
    }
    public User getUser() {
        return user;
    }
    public void setUser(User user) {
        this.user = user;
    }
    public Set<SpecialAccessFilesStore> getSpecialAccessFilesStores() {
        return specialAccessFilesStores;
    }

    public void setSpecialAccessFilesStores(Set<SpecialAccessFilesStore> specialAccessFilesStores) {
        this.specialAccessFilesStores = specialAccessFilesStores;
    }

    @Override
    public String toString() {
        return "Files{" +
                "fileName="+fileName+'\''+
                ", privacy" + privacy+'\''+
                '}';
    }
}
