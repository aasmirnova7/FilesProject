package dao.impl;

import dao.FileUploadDAO;
import model.UploadFile;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

@Repository
public class FileUploadDAOImpl implements FileUploadDAO {
    @PersistenceContext //injection of entityManager
    private EntityManager entityManager;

    public FileUploadDAOImpl() {
    }

    @Override
    @Transactional
    public void save(UploadFile uploadFile) {
        entityManager.persist(uploadFile);
    }

}
