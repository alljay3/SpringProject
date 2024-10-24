/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Spiva.demo.service;

import Spiva.demo.models.Notification;
import Spiva.demo.models.User;
import Spiva.demo.repository.NotificationRepository;
import java.time.LocalDateTime;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author miha2
 */

@Service
public class NotificationService {
    @Autowired
    NotificationRepository notificationRepository;
    
    
    public void sendMessageUser(User user , String message)
    {
        Notification notification = new Notification();
        notification.setMessage(message);
        notification.setPostDate(LocalDateTime.now());
        notification.setRecipient(user);
        notification.setReaded(false);
        notificationRepository.save(notification);
    }
    
    public void sendMessageUsers(List<User> users , String message)
    {
        if (users == null || users.isEmpty())
            return;
        Iterator iterator = users.iterator();
        while (iterator.hasNext()) 
        {
            User user = (User) iterator.next();
            Notification notification = new Notification();
            notification.setMessage(message);
            notification.setPostDate(LocalDateTime.now());
            notification.setRecipient(user);
            notification.setReaded(false);
            notificationRepository.save(notification);
        }
    }
    
    public List<Notification> getUserNotifications(User user)
    {
        return notificationRepository.findByRecipientIdOrderByPostDateDesc(user.getId());
    }
    
    public List<Notification> getUserNotificationsAndRead(User user)
    {
        List<Notification> notifications = notificationRepository.findByRecipientIdOrderByPostDateDesc(user.getId());
        for (Notification notification : notifications)
            notification.setReaded(true);
        notificationRepository.saveAll(notifications);
        return notifications;
    }
    
    
    
    public boolean hasNotReadedNotifications(User user)
    {
        List<Notification> notifications = notificationRepository.findByRecipientId(user.getId());
        for (Notification notification : notifications)
            if (notification.isReaded() == false)
                return true;
        return false;
    }
    
    public void deleteById(int id)
    {
        notificationRepository.deleteById(id);
    }
    
    public Notification findById(int id)
    {
        return notificationRepository.findById(id);
    }
    
    public void deleteAll(List <Notification> notifications)
    {
        notificationRepository.deleteAll(notifications);
    }
}
