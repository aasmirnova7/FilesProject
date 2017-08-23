package dao.daointerfaces;


import model.FilesStore;
import model.SpecialAccessFilesStore;

import java.util.List;

public interface SpecialAccessFilesStoreDao {
    void save(FilesStore filesStore, String idAccessed);
    void delete(SpecialAccessFilesStore filesStore);

}
