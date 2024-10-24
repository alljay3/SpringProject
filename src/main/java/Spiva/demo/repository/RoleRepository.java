/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Spiva.demo.repository;

import Spiva.demo.models.Role;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 *
 * @author miha2
 */
public interface RoleRepository extends JpaRepository<Role, Long> {
    Role save (Role role);
    Role findByName(String name);
}
