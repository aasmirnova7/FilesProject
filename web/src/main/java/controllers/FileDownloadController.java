package controllers;

import model.FilesStore;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import services.FilesStoreService;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.util.List;

@Controller

public class FileDownloadController {
    @Autowired
    private FilesStoreService filesStoreService;

    private ModelAndView updateModel(ModelAndView model){
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String id = auth.getName();
        List<String> list = filesStoreService.findAll(id);
        list.addAll(filesStoreService.findAllInSpecialFiles(id));
        if(list.isEmpty()){
            model.addObject("strings","_");
        }else{
            model.addObject("strings",list);
        }
        return model;
    }

    @RequestMapping(value = "/download", method = RequestMethod.GET)
    public ModelAndView showDownloadForm() {
        return updateModel(new ModelAndView("download"));
    }

    @RequestMapping(value = {"/download"}, method = RequestMethod.POST)
    public ModelAndView downloadFile(HttpServletResponse response, @RequestParam String theme) throws IOException {
        ModelAndView model = new ModelAndView("download");
        if (theme.equals("_")){
            model.addObject("error"," ERROR! NO FILES! BEFORE DOWNLOAD DO UPLOAD!");
        }
        else {
            Authentication auth = SecurityContextHolder.getContext().getAuthentication();
            String id = auth.getName();
            FilesStore filesStore = filesStoreService.find(theme, id).get(0);
            try {
                String[] s = filesStore.getFileName().split("\\.");
                String type = s[s.length - 1];
                response.setContentType(type);
                response.setHeader("Content-Disposition", "attachment; filename=\"" + filesStore.getFileName() + "\"");
                response.setContentLength(filesStore.getData().length);
                FileCopyUtils.copy(filesStore.getData(), response.getOutputStream());
                model.addObject("success", " File: \"" + filesStore.getFileName() + "\" download successfully");
            } catch (IOException e) {
                model.addObject("error", " ERROR! File do not download");
                throw new IOException("Exception");
            }
        }
        return updateModel(model);
    }
}
