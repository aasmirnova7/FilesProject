package controllers;

import dao.daointerfaces.FileUploadDAO;
import model.UploadFile;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;

@Controller
public class HomeController {
    @Autowired
    private FileUploadDAO fileUploadDao;

    @RequestMapping(value = "/Upload", method = RequestMethod.GET)
    public String showUploadForm(HttpServletRequest request) {
        return "Upload";
    }

    @RequestMapping(value = "/Upload", method = RequestMethod.POST, consumes = "multipart/form-data")
    public String handleFileUpload( @RequestParam MultipartFile file) throws Exception {
        UploadFile uploadFile = new UploadFile();
        uploadFile.setFileName(file.getOriginalFilename());
        uploadFile.setData(file.getBytes());
        fileUploadDao.save(uploadFile);
        return "Success";
    }
}
