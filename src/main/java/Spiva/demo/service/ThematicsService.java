/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Spiva.demo.service;

import Spiva.demo.exeptions.RoleRegisterExeption;
import Spiva.demo.exeptions.ThematicsSaveExeption;
import Spiva.demo.models.Thematics;
import Spiva.demo.repository.ThematicsRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author miha2
 */
@Service
public class ThematicsService
{
    @Autowired
    ThematicsRepository thematicsRepository;
    
    public void save(Thematics thematics)
    {
        if (thematicsRepository.findByName(thematics.getName()) != null)
                throw new ThematicsSaveExeption("Такая тематика уже существует");
        thematicsRepository.save(thematics);
    }
    
    public List<Thematics> findAll()
    {
        return thematicsRepository.findAll();
    }
    
    public void delete(Thematics thematics)
    {
        thematicsRepository.delete(thematics);
    }
    public void deleteById(int  id)
    {
        thematicsRepository.deleteById(id);
    }
    
}
