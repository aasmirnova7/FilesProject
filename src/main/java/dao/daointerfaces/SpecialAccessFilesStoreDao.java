package dao.daointerfaces;


import model.FilesStore;
import model.SpecialAccessFilesStore;

import java.util.List;

public interface SpecialAccessFilesStoreDao {
    void save(SpecialAccessFilesStore filesStore);
    void delete(SpecialAccessFilesStore filesStore);

}
