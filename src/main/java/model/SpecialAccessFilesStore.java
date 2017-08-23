package model;

import org.hibernate.annotations.*;
import org.hibernate.annotations.CascadeType;

import javax.persistence.*;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name="specialstore")
public class SpecialAccessFilesStore {
    @Id
    @GeneratedValue(strategy=GenerationType.TABLE) // Для каждого user генерируем новый id
    Integer number;
    @ManyToOne
    @JoinColumn(name="fileName")
    private FilesStore filesStore;
    String idAccessed;


    public SpecialAccessFilesStore(){}
    public SpecialAccessFilesStore( String idAccessed, FilesStore filesStore){
        this.setFilesStore(filesStore);
        this.idAccessed = idAccessed;
    }
    public String getIdAccessed() {
        return idAccessed;
    }
    public void setIdAccessed(String idAccessed) {
        this.idAccessed = idAccessed;
    }
    public Integer getNumber() {
        return number;
    }
    public void setNumber(Integer number) {
        this.number = number;
    }
    public FilesStore getFilesStore() {
        return filesStore;
    }
    public void setFilesStore(FilesStore filesStore) {
        this.filesStore = filesStore;
    }
    @Override
    public String toString() {
        return "SpecialFiles{" +
                filesStore.toString()+
                ", id" + idAccessed+'\''+
                '}';
    }
}
