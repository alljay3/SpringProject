
package Spiva.demo.controller;

import Spiva.demo.models.Notification;
import Spiva.demo.models.User;
import Spiva.demo.service.NotificationService;
import Spiva.demo.service.RoleService;
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


@Controller
@RequestMapping("/mess")
public class NotificationController {
    
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
    
    @GetMapping("/my")
    public String GetMapping(Model model)
    {
        model.addAttribute("notifications", notificationService.getUserNotificationsAndRead(currentUser()));
        return "notification/notifications";
    }
    
    @Transactional
    @PostMapping("/delete/{id}")
    public String deleteNotification(@PathVariable("id") int id) {
        Notification notification = notificationService.findById(id);
        if (notification.getRecipient().equals(currentUser()))
            notificationService.deleteById(id);
        return "redirect:/mess/my";
    }
    
    @Transactional
    @PostMapping("/deleteall")
    public String deleteNotificationsAll() {
        List<Notification> notifications  = notificationService.getUserNotifications(currentUser());
        notificationService.deleteAll(notifications);
        return "redirect:/mess/my";
    }
    
}
