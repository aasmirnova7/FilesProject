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

import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Controller
public class ChangeFileController {
    @Autowired
    private FilesStoreService filesStoreService;
    @Autowired
    private UserService userService;
    private String oldName;

    private Pattern pattern = Pattern.compile("[-a-zA-Z0-9_]*");
    private boolean flag = false;

    @RequestMapping(value = "/change_file", method = RequestMethod.GET)
    public ModelAndView showChangeFilePage() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String id = auth.getName();
        ModelAndView model = new ModelAndView("change_file");
        if(filesStoreService.findAll(id).isEmpty()){
            model.addObject("strings","_");
        }
        else {
            model.addObject("strings",filesStoreService.findAll(id));
        }
        model.addObject("users",userService.findAll());
        return model;
    }

    @RequestMapping(value = "/change_file", method = RequestMethod.POST)
    public ModelAndView changeFile(@RequestParam String action,@RequestParam String theme,
                                   @RequestParam String newFileName, @RequestParam String newLevel,
                                   @RequestParam String personAdd, @RequestParam String addDelete) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String id = auth.getName();
        ModelAndView model = new ModelAndView("change_file");
        model.addObject("users",userService.findAll());
        if (theme.equals("_")) {
            model.addObject("error", " Upload files before change!");
            model.addObject("strings","_");
            return model;
        } else {
            FilesStore filesStore = filesStoreService.findWithFileNameAndUser(theme, id);
            if (action.equals("Choose")) {
                oldName = theme;
                flag = true;
            } else if (action.equals("Commit") && flag) {
                flag = false;
                filesStore = filesStoreService.findWithFileNameAndUser(oldName, id);
                String[] s = filesStore.getFileName().split("\\.");
                String type = s[s.length - 1];
                if (newLevel.equals("0") || newLevel.equals("1") || newLevel.equals("2")) {
                    filesStoreService.changeLevel(filesStore, Integer.parseInt(newLevel), id);
                    filesStore = filesStoreService.findWithFileNameAndUser(oldName, id);
                }
                if (newLevel.equals("2")) {
                    if (addDelete.equals("add")) {
                        filesStoreService.addIdAccessed(filesStore, personAdd);
                    } else if (addDelete.equals("delete")) {
                        if (personAdd.equals(id))
                            model.addObject("errorlevel", "You can not delete yourself");
                        else if (!filesStoreService.findAllInSpecialFilesWhereIIsOwner(id, oldName).contains(personAdd)) {
                            model.addObject("errorlevel", "This person haven't access to this file");
                        }
                        filesStoreService.deleteIdAccessed(filesStore, personAdd);
                    }
                }
                Matcher matcher1 = pattern.matcher(newFileName);
                if(!newFileName.equals("")){
                    if (matcher1.matches()) {
                        if(!filesStoreService.findAll(id).contains(newFileName+"." + type)){
                            filesStoreService.changeFileName(filesStore, newFileName + "." + type, id);
                            filesStore = filesStoreService.findWithFileNameAndUser(newFileName + "." + type, id);
                        }
                        else {
                            filesStore = filesStoreService.findWithFileNameAndUser(oldName, id);
                            model.addObject("errorFileName", "File with this name already exist!");
                        }

                    }
                    else {
                        filesStore = filesStoreService.findWithFileNameAndUser(oldName, id);
                        model.addObject("errorFileName", " Use just letters or numbers or \"_\"!");
                    }
                }
            } else {
                model.addObject("errorFileName", " Choose file before commit!");
            }
            model.addObject("fileName", filesStore.getFileName());
            model.addObject("level", filesStore.getPrivacy());
            model.addObject("strings", filesStoreService.findAll(id));
            return model;
        }


    }
}
