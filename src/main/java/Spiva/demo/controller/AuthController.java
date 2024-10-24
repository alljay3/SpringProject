
package Spiva.demo.controller;

import Spiva.demo.models.User;
import Spiva.demo.exeptions.UserRegisterExeption;
import Spiva.demo.models.Role;
import Spiva.demo.service.NotificationService;
import Spiva.demo.service.RoleService;
import Spiva.demo.service.UserService;
import jakarta.validation.Valid;
import java.util.HashSet;
import java.util.Set;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

/**
 *
 * @author miha2
 */
@Controller
public class AuthController {

    @Autowired
    private UserService userService;
    
    @Autowired
    private RoleService roleService;
    
    
    
    @GetMapping("/register")
    public String showRegistrationForm(Model model) {
        model.addAttribute("user", new User());
        return "register";
    }

    @PostMapping("/register")
    public String registerUser(@Valid @ModelAttribute("user") User user, BindingResult result, Model model, RedirectAttributes redirectAttributes) {
        if(result.hasErrors()){
            return "register";
        }
        try{
            Role roleUser = roleService.findByName("ROLE_USER");
            Set <Role> roles = new HashSet<Role>();
            roles.add(roleUser);
            user.setRoles(roles);
            user.getUserData().setDeposit(0);
            userService.registerUser(user);
        }
        catch (UserRegisterExeption e)
        {
            model.addAttribute("ErrorMessage",e.getMessage());
            return "register";
        }
        redirectAttributes.addFlashAttribute("succesfull", "Успешно зарегестрирован");
        

        
        return "redirect:/login";
    }

    @GetMapping("/login")
    public String showLoginForm(@RequestParam(name = "error", required = false) String error, Model model) {
        if ("true".equals(error)) {
            model.addAttribute("ErrorMessage", "Неверный логин или пароль");
        }
        return "login";
    }
    
    
}
