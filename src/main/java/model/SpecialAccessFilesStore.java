package model;

import javax.persistence.*;

@Entity
@Table(name="specialstore")
public class SpecialAccessFilesStore {
    //Нужно объединить 2 поля в 1 primary key
    @Id
    @GeneratedValue(strategy=GenerationType.TABLE) // Для каждого user генерируем новый id
    Integer number;
    String fileName;
    @JoinColumn
    Long idAccessed;

    public SpecialAccessFilesStore(){}
    public SpecialAccessFilesStore(String fileName, Long idAccessed){
        this.fileName = fileName;
        this.idAccessed = idAccessed;
    }

    public String getFileName() {
        return fileName;
    }
    public void setFileName(String fileName) {
        this.fileName = fileName;
    }
    public Long getIdAccessed() {
        return idAccessed;
    }
    public void setIdAccessed(Long idAccessed) {
        this.idAccessed = idAccessed;
    }
    public Integer getNumber() {
        return number;
    }

    public void setNumber(Integer number) {
        this.number = number;
    }
}
