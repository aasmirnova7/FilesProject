package dao;

import model.FilesStore;
import model.SpecialAccessFilesStore;
import model.User;

import java.util.List;

public interface FilesStoreDao {
    List<FilesStore> findWithFileNameAndUser(FilesStore filesStore);
    List<FilesStore> findWithFileName(String fileName);
    void save(FilesStore filesStore);
    void delete(FilesStore file);
    void mergeFileStore(FilesStore filesStore);
    List<SpecialAccessFilesStore> findSpecialFiles(FilesStore filesStore);
    List<FilesStore> findWithUser(User user);
    List<SpecialAccessFilesStore> findAllInSpecialFiles(String login);
    List<FilesStore> findWithLevel0();
    List<SpecialAccessFilesStore> findWithFileNameInSAFS(FilesStore fs);
}