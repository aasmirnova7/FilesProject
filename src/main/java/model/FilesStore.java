package model;

import javax.persistence.*;

@Entity
@Table(name="store")
public class FilesStore {
    @Id
    String fileName;
    @JoinColumn
    Long idOwner;
    Integer privacy; // 0 - all,1 - owner, 2 - for some users

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

}
