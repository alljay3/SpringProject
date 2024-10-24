/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Spiva.demo.repository;

import Spiva.demo.models.User;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 *
 * @author miha2
 */
public interface UserRepository extends JpaRepository<User, Long> {
    User findByLogin(String username);
    User save(User user);
    User findById(int id);
    List<User> findAll();
}
