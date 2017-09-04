package controllers;

import model.FilesStore;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import services.FilesStoreService;

import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.net.URLConnection;
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

    @RequestMapping(value = {"/download"}, method = RequestMethod.POST)
    public ModelAndView downloadFile(HttpServletResponse response, @RequestParam String theme) throws IOException {
        ModelAndView model = new ModelAndView("download");
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String id = auth.getName();
        FilesStore filesStore = filesStoreService.find(theme,id).get(0);
        try {
            String[] s = filesStore.getFileName().split("\\.");
            String type = s[s.length - 1];
            response.setContentType(type);
            response.setHeader("Content-Disposition", "attachment; filename=\"" + filesStore.getFileName() +"\"");
            response.setContentLength(filesStore.getData().length);
            FileCopyUtils.copy(filesStore.getData(), response.getOutputStream());
            model.addObject("success"," File: \""+filesStore.getFileName()+"\" download successfully");
        }
        catch (IOException e){
            model.addObject("error"," ERROR! File do not download");
            throw new IOException("Exception");
        }
        List<String> list = filesStoreService.findAll(id);
        list.addAll(filesStoreService.findAllInSpecialFiles(id));
        model.addObject("strings",list);
        return model;

    }
}
