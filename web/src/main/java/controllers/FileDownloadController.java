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
import java.util.List;

@Controller
public class FileDownloadController {
    @Autowired
    private FilesStoreService filesStoreService;


    @RequestMapping(value = "/download", method = RequestMethod.GET)
    public ModelAndView showDownloadForm() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String id = auth.getName();
        ModelAndView model = new ModelAndView("download");
        List<String> list = filesStoreService.findAll(id);
        list.addAll(filesStoreService.findAllInSpecialFiles(id));
        model.addObject("strings",list);
        return model;
    }

    @RequestMapping(value = "/download", method = RequestMethod.POST)
    public ModelAndView handleDownloadPage(@RequestParam String theme) throws Exception {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String id = auth.getName();
        ModelAndView model = new ModelAndView("download");

        if(filesStoreService.findAll(id).contains(theme) || filesStoreService.findAllInSpecialFiles(id).contains(theme)){
            FilesStore filesStore = filesStoreService.find(theme,id).get(0);
            FileOutputStream fos = new FileOutputStream(new File("C:\\Users\\Acer1212\\Desktop\\For_project\\"+filesStore.getFileName()));
            fos.write(filesStore.getData());
            fos.close();
            model.addObject("success"," File: \""+filesStore.getFileName()+"\" download successfully");
        } else {
            model.addObject("error"," ERROR! File do not download");
        }
        List<String> list = filesStoreService.findAll(id);
        list.addAll(filesStoreService.findAllInSpecialFiles(id));
        model.addObject("strings",list);
        return model;
    }
}
