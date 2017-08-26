package dao;

import model.FilesStore;
import model.SpecialAccessFilesStore;

public interface SpecialAccessFilesStoreDao {
    void save(FilesStore filesStore, String idAccessed);
    void delete(SpecialAccessFilesStore filesStore);

}
