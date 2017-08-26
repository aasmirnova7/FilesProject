package services.impl;

import dao.FilesStoreDao;
import dao.SpecialAccessFilesStoreDao;
import model.FilesStore;
import model.SpecialAccessFilesStore;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import services.FilesStoreService;
import java.util.ArrayList;
import java.util.List;

@Service
public class FilesStoreServiceImpl implements FilesStoreService{
    @Autowired
    private FilesStoreDao filesStoreDao;

    @Autowired
    private SpecialAccessFilesStoreDao safsd;

    @Override
    public void save(FilesStore filesStore,String ... idAccessed){
        List<FilesStore> list = filesStoreDao.findWithFileNameAndUser(filesStore);
        if (list.isEmpty()) {
            filesStoreDao.save(filesStore);
            if (filesStore.getPrivacy() == 2) {
                safsd.save(filesStore,filesStore.getUser().getId());
                for (String id : idAccessed) {
                    safsd.save(filesStore, id);
                }
            }
        } else {
            list.get(0).setPrivacy(filesStore.getPrivacy());
        }
    }
    @Override
    public void delete(String fileName, String myId) {
        List<FilesStore> list = filesStoreDao.findWithFileName(fileName);
        for(FilesStore fs: list){
            if(fs.getUser().getId().equals(myId)){
                filesStoreDao.delete(fs);
                break;
            }
        }
    }
    @Override
    public List<FilesStore> find(String fileName, String login) {
        Integer privacy;
        List<FilesStore> listFilesYouCanSee = new ArrayList<>();
        for (FilesStore fs: filesStoreDao.findWithFileName(fileName)) {
            privacy = fs.getPrivacy();
            switch (privacy){
                case 0:
                    listFilesYouCanSee.add(fs);
                    break;
                case 1:
                    if (login.equals(fs.getUser().getId())){
                        listFilesYouCanSee.add(fs);
                    }
                    break;
                case 2:
                    if (findWithPrivacy(fs,login)){
                        listFilesYouCanSee.add(fs);
                    }
                    break;
            }

        }
        return listFilesYouCanSee;
    }

    //находим файл, c проверкой доступа
    private boolean findWithPrivacy(FilesStore filesStore,String login) {
        List<SpecialAccessFilesStore> accessFilesStoreList = filesStoreDao.findSpecialFiles(filesStore);
        boolean flag = false;
        for (SpecialAccessFilesStore safs : accessFilesStoreList){
            if (safs.getIdAccessed().equals(login)){
                flag = true;
                break;
            }
        }
        return flag;
    }
    @Override
    public void changeFileName(FilesStore fs, String name,String login) {
        if(fs.getUser().getId().equals(login)){
            fs.setFileName(name);
            filesStoreDao.mergeFileStore(fs);
        }

    }
    @Override
    public void changeLevel(FilesStore fs, Integer level, String login) {
        if(fs.getUser().getId().equals(login)&&0<=level&&level<3) {
            fs.setPrivacy(level);
            filesStoreDao.mergeFileStore(fs);
        }
    }
    @Override
    public void deleteIdAccessed(FilesStore fs, String login, String idAccessed){
        if(fs.getUser().getId().equals(login)){
            for (SpecialAccessFilesStore safs: filesStoreDao.findSpecialFiles(fs)){
                if (safs.getIdAccessed().equals(idAccessed)){
                    safsd.delete(safs);
                    break;
                }
            }
        }
    }
    @Override
    public void addIdAccessed(FilesStore fs,  String login, String idAccessed){
        if (fs.getUser().getId().equals(login)) {
            safsd.save(fs, idAccessed);
        }
    }
}
