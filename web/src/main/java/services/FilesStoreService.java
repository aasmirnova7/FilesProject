package services;

import model.FilesStore;
import model.User;

import java.util.List;

public interface FilesStoreService {
    void save(FilesStore filesStore, String ... idAccessed);
    void delete(String fileName, String myId);
    List<FilesStore> find(String fileName, String login);
    void changeFileName(FilesStore fs, String name,String login);
    void changeLevel(FilesStore fs, Integer level, String login);
    void deleteIdAccessed(FilesStore fs, String idAccessed);
    void addIdAccessed(FilesStore fs,  String idAccessed);
    List<String> findAll(String login);
    List<String> findAllInSpecialFiles(String login);
    List<String> findAllInSpecialFilesWhereIIsOwner(String login,String fileName);
    List<FilesStore> findWithFileNameAndUser(String fileName, String login);
    List<FilesStore> findWithDataAndUser(byte[] data, String login);


}
