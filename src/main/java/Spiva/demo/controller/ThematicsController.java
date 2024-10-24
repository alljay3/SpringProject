
package Spiva.demo.controller;

import Spiva.demo.exeptions.ThematicsSaveExeption;
import Spiva.demo.models.Thematics;
import Spiva.demo.models.User;
import Spiva.demo.service.NotificationService;
import Spiva.demo.service.RoleService;
import Spiva.demo.service.ThematicsService;
import Spiva.demo.service.UserService;
import jakarta.transaction.Transactional;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 *
 * @author miha2
 */
@Controller
@RequestMapping("/thematics")
@Transactional
public class ThematicsController {
    
    @Autowired
    UserService userService;
    
    @Autowired
    RoleService RoleService;
    
    @ModelAttribute("getUser")
    public User currentUser()
    {
        return userService.getCurrentUser();
    }
    
    @ModelAttribute("isAdmin")
    public boolean isAdmin()
    {
        return RoleService.isAdmin();
    }
    
    @Autowired
    NotificationService notificationService;
    
    @ModelAttribute("hasNotification")
    public boolean hasNotification()
    {
        return notificationService.hasNotReadedNotifications(currentUser());
    }

    @Autowired
    private ThematicsService thematicsService;
    
    
    @GetMapping("")
    public String showListTem(Model model) {
        List them = thematicsService.findAll();
        model.addAttribute("thematicsList", them);
        return "thematics/list";
    }

    @GetMapping("/create")
    public String showCreateForm(Model model) {
        Thematics thematics = new Thematics();
        model.addAttribute("thematics", thematics);
        return "thematics/create";
    }

    @PostMapping("/create")
    public String createThematics(@ModelAttribute("thematics") Thematics thematics, Model model) {
        try
        {
            thematicsService.save(thematics);
        }
        catch (ThematicsSaveExeption e)
        {
            model.addAttribute("ErrorMessage",e.getMessage());
            return "thematics/create";
        }
        return "redirect:/thematics";
    }
    
    @PostMapping("/delete/{id}")
    public String deleteThematic(@PathVariable("id") Integer id) {
        thematicsService.deleteById(id);
        return "redirect:/thematics";
    }
}
