
package Spiva.demo.controller;

import Spiva.demo.models.User;
import Spiva.demo.service.NotificationService;
import Spiva.demo.service.RoleService;
import Spiva.demo.service.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

/**
 *
 * @author miha2
 */

@Controller
@RequestMapping("/user") 
public class UserController {
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
    
    @GetMapping("/{username}")
    public String getInfo(@PathVariable("username") String username, Model model) {
        User user = userService.findByUsername(username);
        model.addAttribute("user", user);
        if (currentUser().equals(user) || isAdmin())
        {
            return "user/userChange";
        }
        return "user/userInfo";
    }
    
    @PostMapping("/change")
    public String changeUser(@Valid @ModelAttribute("user") User user, BindingResult result, Model model,RedirectAttributes redirectAttributes)
    {
        if ( !((user.getId() == currentUser().getId()) || isAdmin()) )
            return  "redirect:/";
        if(result.hasErrors()){
            return "user/userChange";
        }
        User usernew = userService.findById(user.getId());
        usernew.setPassword(user.getPassword());
        usernew.getUserData().setEmail(user.getUserData().getEmail());
        usernew.getUserData().setNumberPhone(user.getUserData().getNumberPhone());
        userService.save(usernew);
        
        redirectAttributes.addFlashAttribute("succesfull", "Данные изменены");
        return "redirect:/user/" + user.getLogin();
    }
    
    @GetMapping("/addmoney")
    public String addMoney(Model model) {
        User user = currentUser();
        model.addAttribute("user", user);
        return "user/addMoney";
    }
    
    @PostMapping("/addmoney")
    public String addMoneyPost(@RequestParam("addAmount") Integer addAmount) {
        User user = currentUser();
        user.getUserData().setDeposit(user.getUserData().getDeposit() + addAmount);
        userService.saveNoPassword(user);
        return "redirect:/";
    }
    
}
