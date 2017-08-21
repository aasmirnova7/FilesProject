package dao.daointerfaces;


import model.SpecialAccessFilesStore;

import java.util.List;

public interface SpecialAccessFilesStoreDao {
    void save(SpecialAccessFilesStore filesStore);
    void delete(SpecialAccessFilesStore filesStore);

    List<SpecialAccessFilesStore> find(SpecialAccessFilesStore filesStore);
    void changeIdAccessed(SpecialAccessFilesStore fs, Long idAccessed);

}
