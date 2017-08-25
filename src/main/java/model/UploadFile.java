package model;

import javax.persistence.*;

@Entity
@Table(name = "uploadFile")
public class UploadFile {
    @Id
    @GeneratedValue
    @Column(name = "FILE_ID")
    private long id;
    private String fileName;
    private byte[] data;

    public UploadFile(){}
    public UploadFile(String fileName, byte[] data){
        this.fileName = fileName;
        this.data = data;
    }

    public long getId() {
        return id;
    }
    public void setId(long id) {
        this.id = id;
    }
    public String getFileName() {
        return fileName;
    }
    public void setFileName(String fileName) {
        this.fileName = fileName;
    }
    public byte[] getData() {
        return data;
    }
    public void setData(byte[] data) {
        this.data = data;
    }

}
