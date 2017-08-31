package controllers;

import model.FilesStore;
import model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import services.FilesStoreService;
import services.UserService;

import javax.servlet.http.HttpServletRequest;

@Controller
public class FileUploadController {
    @Autowired
    private FilesStoreService filesStoreService;
    @Autowired
    private UserService userService;

    @RequestMapping(value = "/Upload", method = RequestMethod.GET)
    public String showUploadForm(HttpServletRequest request) {
        return "Upload";
    }

    @RequestMapping(value = "/Upload", method = RequestMethod.POST)
    public String handleFileUpload(@RequestParam MultipartFile file) throws Exception {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String name = auth.getName();
        User user = userService.find(name);
        FilesStore uploadFile = new FilesStore();
        uploadFile.setFileName(file.getOriginalFilename());
        uploadFile.setData(file.getBytes());
        uploadFile.setUser(user);
        uploadFile.setPrivacy(0);//надо обрабатывать
        filesStoreService.save(uploadFile); //Если сохраняем один и тот же файл, тоже плохо
        return "Success";
    }
}