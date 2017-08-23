package dao.daointerfaces;


import model.FilesStore;
import model.SpecialAccessFilesStore;

import java.util.List;

public interface FilesStoreDao {
    void save(FilesStore filesStore, String ... idAccessed);
    void delete(String fileName, String myId);

    boolean findWithPrivacy(FilesStore filesStore,String login);
    List<FilesStore> find(String fileName,String login);
    void changeFileName(FilesStore fs, String name, String login);
    void changeLevel(FilesStore fs, Integer level, String login);
    void deleteIdAccessed(FilesStore fs, String login, String idAccessed);
    void addIdAccessed(FilesStore fs, String idAccessed, String login);
    List<SpecialAccessFilesStore> findSpecialFiles(FilesStore filesStore);
}
