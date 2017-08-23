package dao.daointerfaces;


import model.FilesStore;
import model.SpecialAccessFilesStore;

import java.util.List;

public interface FilesStoreDao {
    void save(FilesStore filesStore);
    void delete(FilesStore filesStore);

    boolean findWithPrivacy(FilesStore filesStore,String login);
    List<FilesStore> find(String fileName,String login);
    void changeFileName(FilesStore fs, String name, String login);
    void changeLevel(FilesStore fs, Integer level, String login);
    void deleteIdAccessed(FilesStore fs, String idAccessed, String login);
    void addIdAccessed(FilesStore fs, String idAccessed, String login);
    List<SpecialAccessFilesStore> findSpecialFiles(FilesStore filesStore);
}
