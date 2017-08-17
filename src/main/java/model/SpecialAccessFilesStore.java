package model;

import javax.persistence.*;

@Entity
@Table(name="specialstore")
public class SpecialAccessFilesStore {
    @Id
    @GeneratedValue(strategy=GenerationType.TABLE) // Для каждого user генерируем новый id
    Integer number;
    @ManyToOne
    @JoinColumn(name="fileName")
    private FilesStore filesStore;
    Long idAccessed;


    public SpecialAccessFilesStore(){}
    public SpecialAccessFilesStore( Long idAccessed, FilesStore filesStore){
        this.setFilesStore(filesStore);
        this.idAccessed = idAccessed;
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
