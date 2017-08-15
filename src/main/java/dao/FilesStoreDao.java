package dao;


import model.FilesStore;
import java.util.List;

public interface FilesStoreDao {
    void save(FilesStore filesStore);
    void delete(FilesStore filesStore);

    FilesStore findWithPrivacy(FilesStore filesStore);
    List<FilesStore> find(FilesStore filesStore);
    void changeFileName(FilesStore fs, String name);
    void changeLevel(FilesStore fs, Integer level);
}
