/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Spiva.demo.repository;

import Spiva.demo.models.Thematics;
import java.util.Collection;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 *
 * @author miha2
 */
public interface ThematicsRepository extends JpaRepository<Thematics, Long> {
    Thematics findByName(String name);
    Thematics save(Thematics thematics);
    List <Thematics> findAll();
    void delete(Thematics thematics);
    void deleteById(int id);
}
