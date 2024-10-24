/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Spiva.demo.service;

import Spiva.demo.exeptions.RoleRegisterExeption;
import Spiva.demo.models.Role;
import Spiva.demo.repository.RoleRepository;
import jakarta.transaction.Transactional;
import java.util.Collection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

/**
 *
 * @author miha2
 */
@Service
@Transactional
public class RoleService {
    @Autowired
    RoleRepository roleRepository;
    
    public void save(Role role)
    {
        if (roleRepository.findByName(role.getName()) != null)
                throw new RoleRegisterExeption("Данная роль уже существует");
        roleRepository.save(role);
    }
    
    public Role findByName(String name)
    {
        Role role = roleRepository.findByName(name);
        return role;
    }
    
    public boolean isAdmin()
    {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        Collection<? extends GrantedAuthority> authorities = authentication.getAuthorities();
        boolean isAdmin = authorities.stream()
                            .anyMatch(auth -> auth.getAuthority().equals("ROLE_ADMIN"));
        return isAdmin;
    }
    
}
