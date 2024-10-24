/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Spiva.demo.repository;

import Spiva.demo.models.Auction;
import Spiva.demo.models.User;
import java.time.LocalDateTime;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

/**
 *
 * @author miha2
 */
public interface AuctionRepostitory extends JpaRepository<Auction, Long> {
    Auction save (Auction auction);
    Auction findById(int id);
    void deleteById(int id);
    
    @Query("SELECT a FROM Auction a WHERE a.userHost = :user ORDER BY a.auctionData.sandMsg ASC, a.auctionData.startDate DESC")
    List<Auction> findByUserHost(@Param("user") User user);
    
    @Query("SELECT a FROM Auction a WHERE :user MEMBER OF a.auctionData.members ORDER BY a.auctionData.sandMsg ASC, a.auctionData.startDate DESC")
    List<Auction> findByUserMembers(@Param("user") User user);
    
    @Query("SELECT a FROM Auction a WHERE a.auctionData.startDate > :currentDate")
    List<Auction> findAllWithStartDateAfter(@Param("currentDate") LocalDateTime currentDate);
    
    @Query("SELECT a FROM Auction a WHERE a.auctionData.startDate < :currentDate AND a.auctionData.sandMsg = 0")
    List<Auction> findAllWithStartDateBeforeMsg0(@Param("currentDate") LocalDateTime currentDate);
    
    @Query("SELECT a FROM Auction a WHERE a.auctionData.startDate < :currentDate AND a.auctionData.sandMsg = 1")
    List<Auction> findAllWithStartDateBeforeMsg1(@Param("currentDate") LocalDateTime currentDate);
    
    
}
