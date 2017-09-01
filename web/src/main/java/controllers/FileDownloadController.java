package controllers;

import model.FilesStore;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import services.FilesStoreService;
import java.io.File;
import java.io.FileOutputStream;

@Controller
public class FileDownloadController {
    @Autowired
    private FilesStoreService filesStoreService;


    @RequestMapping(value = "/download", method = RequestMethod.GET)
    public ModelAndView showDownloadForm() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String name = auth.getName();
        ModelAndView model = new ModelAndView("download");
        model.addObject("strings",filesStoreService.findAll(name));
        return model;
    }

    @RequestMapping(value = "/download", method = RequestMethod.POST)
    public ModelAndView handleDownloadPage(@RequestParam String theme) throws Exception {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String name = auth.getName();
        ModelAndView model = new ModelAndView("download");

        if(!filesStoreService.find(theme,name).isEmpty()){
            FilesStore filesStore = filesStoreService.find(theme,name).get(0);
            FileOutputStream fos = new FileOutputStream(new File("C:\\Users\\Acer1212\\Desktop\\For_project\\"+filesStore.getFileName()));
            fos.write(filesStore.getData());
            fos.close();
            model.addObject("success"," File: \""+filesStore.getFileName()+"\" download successfully");
        }else {
            model.addObject("error"," ERROR! File do not download");
        }
        model.addObject("strings",filesStoreService.findAll(name));
        return model;
    }
}
