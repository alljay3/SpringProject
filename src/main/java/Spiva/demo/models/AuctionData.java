/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Spiva.demo.models;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

/**
 *
 * @author miha2
 */
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
@Table(name = "auction_data")
public class AuctionData {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Integer id;
    
    @Size(min = 1, message = "Описание не может быть пустым")
    @NotNull(message = "Описание не может быть пустым")
    String description;
    
    String Photo;
    
    Integer startCost;
    
    
    @NotNull(message = "Выберите тематику")
    @ManyToOne
    @JoinColumn(name = "thematics_id",  referencedColumnName = "id")
    Thematics thematics;
    
    @DateTimeFormat(pattern = "YYYY-MM-DD HH:MM:SS")
    @Column(name = "start_date")
    LocalDateTime  startDate;
    
    @ManyToMany
    List <User> members;
    
    Integer timeEndDelay;
    
    @Column(name = "is_active")
    boolean Active;
    @Column(name = "sand_msg")
    int sandMsg = 0;
    
    
   
}
