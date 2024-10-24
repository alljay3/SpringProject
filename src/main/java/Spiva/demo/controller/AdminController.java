
package Spiva.demo.controller;

import Spiva.demo.models.Auction;
import Spiva.demo.models.User;
import Spiva.demo.service.AuctionService;
import Spiva.demo.service.NotificationService;
import Spiva.demo.service.RoleService;
import Spiva.demo.service.UserService;
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
@RequestMapping("/adm")
public class AdminController {
    
    @Autowired
    UserService userService;
    
    @Autowired
    RoleService RoleService;
    
    @Autowired
    AuctionService auctionService;
    
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
    
    @GetMapping("/listUser")
    public String CreateAuction(Model model)
    {
        model.addAttribute("users",userService.findAll());
        return "adm/userlist";
    }
    
    @GetMapping("/listTransactUser/{login}")
    public String listTansactUser(@PathVariable("login") String login, Model model)
    {
        User user = userService.findByUsername(login);
        model.addAttribute("transactions", auctionService.getTransactionsUser(user));
        return "adm/listTransactions";
    }
    
    @GetMapping("/listTransactAuction/{id}")
    public String listTansactAuct(@PathVariable("id") int id, Model model)
    {
        Auction auction = auctionService.findById(id);
        
        model.addAttribute("transactions", auctionService.getTransactions(auction));
        return "adm/listTransactions";
    }
    
    
}
