package services;

import model.FilesStore;
import java.util.List;

public interface FilesStoreService {
    void save(FilesStore filesStore, String ... idAccessed);
    void delete(String fileName, String myId);
    List<FilesStore> find(String fileName, String login);
    void changeFileName(FilesStore fs, String name,String login);
    void changeLevel(FilesStore fs, Integer level, String login);
    void deleteIdAccessed(FilesStore fs, String login, String idAccessed);
    void addIdAccessed(FilesStore fs, String login, String idAccessed);
    List<String> findAll(String login);

}
