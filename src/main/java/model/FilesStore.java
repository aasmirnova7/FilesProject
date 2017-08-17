package model;

import javax.persistence.*;

@Entity
@Table(name="store")
public class FilesStore {
    //Нужно объединить 2 поля в 1 primary key
    @Id
    @GeneratedValue(strategy=GenerationType.SEQUENCE, generator = "storeGenerator")
    @SequenceGenerator(name="storeGenerator", sequenceName="storeGenerator", allocationSize=1)
    Integer number;
    String fileName;
    Integer privacy; // 0 - all,1 - owner, 2 - for some users
    @ManyToOne
    @JoinColumn(name="id")
    private User user;
    public FilesStore(){}
    public FilesStore(String fileName, Integer privacy, User user){
        this.fileName = fileName;
        this.setUser(user);
        this.privacy = privacy;
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
    public User getUser() {
        return user;
    }
    public void setUser(User user) {
        this.user = user;
    }

    @Override
    public String toString() {
        return "Files{" +
                "fileName="+fileName+'\''+
                ", privacy" + privacy+'\''+
                '}';
    }
}
