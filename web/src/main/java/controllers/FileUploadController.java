package controllers;

import model.FilesStore;
import model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
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
    public ModelAndView showUploadForm() {
        return new ModelAndView("Upload");
    }

    @RequestMapping(value = "/Upload", method = RequestMethod.POST)
    public ModelAndView handleFileUpload(HttpServletRequest request, @RequestParam MultipartFile file) throws Exception {

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String id = auth.getName();
        User user = userService.find(id);
        ModelAndView model = new ModelAndView("Upload");
        if(file.getBytes().length==0){
            model.addObject("error"," ERROR! Choose file before upload! ");
            return model;
        }
        else {
            FilesStore uploadFile = new FilesStore();
            uploadFile.setFileName(file.getOriginalFilename());
            uploadFile.setData(file.getBytes());
            uploadFile.setUser(user);
            uploadFile.setPrivacy(1);
            if(filesStoreService.findWithFileNameAndUser(file.getOriginalFilename(),id)==null
                    &&filesStoreService.findWithDataAndUser(file.getBytes(),id)==null){

                filesStoreService.save(uploadFile);
                return new ModelAndView("Success");
            }
            else if(filesStoreService.findWithDataAndUser(file.getBytes(),id)!=null){
                model.addObject("error"," ERROR! File don't load! This file already exist in your files! With name: \""+filesStoreService.findWithDataAndUser(file.getBytes(),id).getFileName()+"\"");
                return model;
            }
            else{
                request.getSession().setAttribute("param",uploadFile);
                return new ModelAndView("change_or_rename");
            }

        }

    }
}