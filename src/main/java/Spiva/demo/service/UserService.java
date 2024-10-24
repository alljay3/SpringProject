/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Spiva.demo.service;

import Spiva.demo.exeptions.UserRegisterExeption;
import Spiva.demo.models.User;
import Spiva.demo.repository.UserRepository;
import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

/**
 *
 * @author miha2
 */

@Service
@Transactional
public class UserService implements UserDetailsService {
    

    
    @Autowired
    UserRepository userRepository;
    
    @Autowired
    NotificationService notificationService;
    
    public void registerUser(User user)
    {
        user.setPassword(new BCryptPasswordEncoder().encode(user.getPassword()));
        if (userRepository.findByLogin(user.getLogin()) != null)
            throw new UserRegisterExeption("Данный логин уже используется");
        userRepository.save(user);
        notificationService.sendMessageUser(user, "Добро пожаловать на наш сайт! приятного пользования!");
    }


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByLogin(username);
        if (user == null) {
            throw new UsernameNotFoundException("User not found");
        }
        return user;
    }
    
    public String getCurrentUsername() {
      Authentication auth = SecurityContextHolder.getContext().getAuthentication();
      return auth.getName();
    }
    
    public User getCurrentUser()
    {
        User user = userRepository.findByLogin(getCurrentUsername());
        if (user == null) {
            throw new UsernameNotFoundException("User not found");
        }
        return user;
    }
    
    public User findById(int id)
    {
        User user = userRepository.findById(id);
        return user;
    }
    
    public User findByUsername(String username)
    {
        return userRepository.findByLogin(username);
    }
    
    public void save(User user)
    {
        user.setPassword(new BCryptPasswordEncoder().encode(user.getPassword()));
        userRepository.save(user);
    }
    public void saveNoPassword(User user)
    {
        userRepository.save(user);
    }
    public List<User> findAll()
    {
        return userRepository.findAll();
    }
}
