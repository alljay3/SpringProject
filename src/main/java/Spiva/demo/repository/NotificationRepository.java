/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package Spiva.demo.repository;

import Spiva.demo.models.Notification;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 *
 * @author miha2
 */
public interface NotificationRepository extends JpaRepository<Notification, Long> {
    Notification save(Notification notification);
    List<Notification> findByRecipientId(Integer recipientId);
    List<Notification> findByRecipientIdOrderByPostDateDesc(Integer recipientId);
    void deleteById(int id);
    Notification findById(int id);
}
