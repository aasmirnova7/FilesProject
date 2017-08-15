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
    @JoinColumn
    Long idOwner;
    Integer privacy; // 0 - all,1 - owner, 2 - for some users

    public FilesStore(){}
    public FilesStore(String fileName, Long idOwner, Integer privacy){
        this.fileName = fileName;
        this.idOwner = idOwner;
        this.privacy = privacy;
    }

    public String getFileName() {
        return fileName;
    }
    public void setFileName(String fileName) {
        this.fileName = fileName;
    }
    public Long getIdOwner() {
        return idOwner;
    }
    public void setIdOwner(Long idOwner) {
        this.idOwner = idOwner;
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

    @Override
    public String toString() {
        return "Files{" +
                "fileName="+fileName+'\''+
                ", idOwner'" + idOwner + '\'' +
                ", privacy" + privacy+'\''+
                '}';
    }
}
