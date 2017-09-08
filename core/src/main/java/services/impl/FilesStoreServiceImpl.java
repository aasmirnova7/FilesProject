package services.impl;

import dao.FilesStoreDao;
import dao.SpecialAccessFilesStoreDao;
import model.FilesStore;
import model.SpecialAccessFilesStore;
import model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import services.FilesStoreService;
import services.UserService;
import java.util.ArrayList;
import java.util.List;

@Service
public class FilesStoreServiceImpl implements FilesStoreService{
    @Autowired
    private FilesStoreDao filesStoreDao;
    @Autowired
    private UserService userService;
    @Autowired
    private SpecialAccessFilesStoreDao safsd;

    @Override
    public void save(FilesStore filesStore,String ... idAccessed){
        List<FilesStore> list = filesStoreDao.findWithFileNameAndUser(filesStore.getFileName(),filesStore.getUser());
        if (list.isEmpty()) {
            filesStoreDao.save(filesStore);
            if (filesStore.getPrivacy() == 2) {
                safsd.save(filesStore,filesStore.getUser().getId());
                for (String id : idAccessed) {
                    safsd.save(filesStore, id);
                }
            }
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
    public void changeData(FilesStore fs, byte[] data, String login) {
        if(fs.getUser().getId().equals(login)){
            fs.setData(data);
            filesStoreDao.mergeFileStore(fs);
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
            if(fs.getPrivacy() == 2 && level !=2){
                for (SpecialAccessFilesStore file: filesStoreDao.findSpecialFiles(fs)){
                    safsd.delete(file);
                }
            }
            fs.setPrivacy(level);
            filesStoreDao.mergeFileStore(fs);
            if(level==2){
                safsd.save(fs,login);
            }
        }
    }
    @Override
    public void deleteIdAccessed(FilesStore fs, String idAccessed){
        for (SpecialAccessFilesStore safs: filesStoreDao.findSpecialFiles(fs)){
            if (safs.getIdAccessed().equals(idAccessed)&&!safs.getFilesStore().getUser().getId().equals(idAccessed)){
                safsd.delete(safs);
                break;
            }
        }
    }
    @Override
    public void addIdAccessed(FilesStore fs,   String idAccessed){
        safsd.save(fs, idAccessed);
    }
    //Находим файлы, которые принадлежать этому user
    @Override
    public List<String> findAll(String login){
        User user = userService.find(login);
        List<String> names = new ArrayList<>();
        for (FilesStore filesStore: filesStoreDao.findWithUser(user)){
            names.add(filesStore.getFileName());
        }
        return names;
    }

    @Override
    public List<String> findAllInSpecialFiles(String login) {
        List<String> list = new ArrayList<>();
        for (SpecialAccessFilesStore sa: filesStoreDao.findAllInSpecialFiles(login)){
            if(!sa.getFilesStore().getUser().getId().equals(login))
                list.add(sa.getFilesStore().getFileName());
        }
        for (FilesStore fs : filesStoreDao.findWithLevel0()){
            if(!fs.getUser().getId().equals(login))
                list.add(fs.getFileName());
        }
        return list;
    }
    @Override
    public List<String> findAllInSpecialFilesWhereIIsOwner(String login, String fileName){
        List<String> list = new ArrayList<>();
        FilesStore filesStore = new FilesStore();
        for (FilesStore fs: filesStoreDao.findWithFileName(fileName)){
            if(fs.getUser().getId().equals(login)) {
                filesStore = fs;
                break;
            }
        }
        for(SpecialAccessFilesStore fs: filesStoreDao.findWithFileNameInSAFS(filesStore)){
            list.add(fs.getIdAccessed());

        }
        return list;
    }

    @Override
    public FilesStore findWithFileNameAndUser(String fileName, String login) {
        if(filesStoreDao.findWithFileNameAndUser(fileName,userService.find(login)).isEmpty()){
            return null;
        }
        else return filesStoreDao.findWithFileNameAndUser(fileName,userService.find(login)).get(0);
    }

    @Override
    public FilesStore findWithDataAndUser(byte[] data, String login) {
        if(filesStoreDao.findWithDataAndUser(data,userService.find(login)).isEmpty()){
            return null;
        }
        return filesStoreDao.findWithDataAndUser(data,userService.find(login)).get(0);
    }
}
