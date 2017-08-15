package dao;


import model.SpecialAccessFilesStore;

public interface SpecialAccessFilesStoreDao {
    void save(SpecialAccessFilesStore filesStore);
    void delete(SpecialAccessFilesStore filesStore);

    SpecialAccessFilesStore find(SpecialAccessFilesStore filesStore);
    void changeFileName(SpecialAccessFilesStore fs, String name);
    void changeIdAccessed(SpecialAccessFilesStore fs, Long idAccessed);

}
