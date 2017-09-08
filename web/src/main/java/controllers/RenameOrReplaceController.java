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
import services.UserService;

import javax.servlet.http.HttpServletRequest;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Controller
public class RenameOrReplaceController {
    @Autowired
    private FilesStoreService filesStoreService;
    private Pattern pattern = Pattern.compile("[-a-zA-Z0-9_]*");

    @RequestMapping(value = "/change_or_rename", method = RequestMethod.GET)
    public ModelAndView showChangeForm() {
        return new ModelAndView("change_or_rename");
    }

    @RequestMapping(value = "/change_or_rename", method = RequestMethod.POST)
    public ModelAndView fileChange(HttpServletRequest request, @RequestParam String action, @RequestParam String newName) throws Exception {
        ModelAndView model = new ModelAndView("change_or_rename");
        FilesStore file = (FilesStore) request.getSession().getAttribute("param");
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String id = auth.getName();
        if(action.equals("Replace content")){
            FilesStore fs = filesStoreService.findWithFileNameAndUser(file.getFileName(),id);
            filesStoreService.changeData(fs,file.getData(),id);
        }

        Matcher matcher = pattern.matcher(newName);
        String[] s = file.getFileName().split("\\.");
        String type = s[s.length - 1];
        if(action.equals("Rename")){
            if(!newName.equals("")) {
                if(matcher.matches()) {
                    if (!filesStoreService.findAll(id).contains(newName + "." + type)) {
                        filesStoreService.changeFileName(file, newName + "." + type, id);
                        filesStoreService.save(file);
                    } else {
                        model.addObject("error", " File with name \"" + newName + "\" already exist!");
                        return model;
                    }
                }
                else{
                    model.addObject("error", " Use just letters or numbers or \"_\"!");
                    return model;
                }

            }
            else {
                return model;
            }

        }
        return new ModelAndView("Success");
    }
}