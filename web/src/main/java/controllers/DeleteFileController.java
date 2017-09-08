package controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import services.FilesStoreService;
import java.util.List;

@Controller
public class DeleteFileController {
    @Autowired
    FilesStoreService filesStoreService;

    private ModelAndView updateModel(ModelAndView model){
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String id = auth.getName();
        List<String> list = filesStoreService.findAll(id);
        if(list.isEmpty()){
            model.addObject("strings","_");
        }else{
            model.addObject("strings",list);
        }
        return model;
    }

    @RequestMapping(value = "/delete_file", method = RequestMethod.GET)
    public ModelAndView showDeleteForm() {
        return updateModel(new ModelAndView("delete_file"));
    }

    @RequestMapping(value = {"/delete_file"}, method = RequestMethod.POST)
    public ModelAndView deleteFile(@RequestParam String theme){
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String id = auth.getName();
        ModelAndView model = new ModelAndView("delete_file");
        if (theme.equals("_")) {
            model.addObject("error", " You can't delete nothing!");
            model.addObject("strings","_");
        } else{
            filesStoreService.delete(theme,id);
            model = updateModel(model);
            model.addObject("success", "File with name: " + theme + " was delete successfully!");
        }
        return model;
    }
}
