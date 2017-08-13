package dao;


import model.FilesStore;

public interface FilesStoreDao {
    void save(FilesStore filesStore);
    void delete(FilesStore filesStore);

    FilesStore find(FilesStore filesStore);
    void changeFileName(FilesStore fs, String name);
    void changeLevel(FilesStore fs, Integer level);
}
