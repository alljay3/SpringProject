/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Spiva.demo.service;

import Spiva.demo.exeptions.ActualDateExeption;
import Spiva.demo.exeptions.DateIsNull;
import Spiva.demo.models.Auction;
import Spiva.demo.models.AuctionSearch;
import Spiva.demo.models.Transaction;
import Spiva.demo.models.User;
import Spiva.demo.repository.AuctionRepostitory;
import Spiva.demo.repository.TransactionRepository;
import java.time.LocalDateTime;
import java.util.LinkedList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author miha2
 */
@Service
public class AuctionService {
    @Autowired
    AuctionRepostitory auctionRepostitory;
    
    @Autowired
    TransactionRepository transactionRepository;
    
    public void createAuction(Auction auction)
    {
        if (auction.getAuctionData().getStartDate() == null)
            throw  new DateIsNull("Дата не может быть пустой");
        if (auction.getAuctionData().getStartDate().compareTo(LocalDateTime.now()) <= 0)
            throw new ActualDateExeption("Введите актуальную дату");
        auctionRepostitory.save(auction);
    }
    public Auction findById(int id)
    {
        return auctionRepostitory.findById(id);
    }
    public void save (Auction auction)
    {
        auctionRepostitory.save(auction);
    }
    
    public void deleteById(int id)
    {
        auctionRepostitory.deleteById(id);
    }
    
    public boolean auctionIsStarted(Auction auction)
    {   
        if (auction.getAuctionData().getStartDate().compareTo(LocalDateTime.now()) < 0)
            return true;
        else
            return false;
    }
    
    //Первая стадия аукциона
    public boolean auctIsNotStart(Auction auction)
    {
        if (!auctionIsStarted(auction))
            return true;
        return false;
    }
    
    
    //Вторая стадия аукциона
    public boolean auctIsStartAndNotEnd(Auction auction)
    {
        if (auctionIsStarted(auction) && auction.getAuctionData().isActive())
            return true;
        return false;
    }
    
    
    //Третья стадия аукциона
    public boolean auctIsStartAndEnd(Auction auction)
    {
        if (auctionIsStarted(auction) && !auction.getAuctionData().isActive())
            return true;
        return false;
    }
    
    public int status(Auction auction)
    {
        if (auctIsNotStart(auction))
            return 0;
        if (auctIsStartAndNotEnd(auction))
            return 1;
        if (auctIsStartAndEnd(auction))
            return 2;
        return 3;
    }
    
    public List<Auction> getActualyAuction()
    {
        return auctionRepostitory.findAllWithStartDateAfter(LocalDateTime.now());
    }
    
    public List<Auction> getSeatchAuction(AuctionSearch auctionSearch)
    {
        List<Auction> auctions = getActualyAuction();
        if (auctionSearch.getMaxCost() != null)
        {
            auctions = auctions.stream().filter(auction -> auctionSearch.getMaxCost() >= auction.getAuctionData().getStartCost()).toList();
        }
        if (auctionSearch.getMinCost() != null)
        {
            auctions = auctions.stream().filter(auction -> auctionSearch.getMinCost() <= auction.getAuctionData().getStartCost()).toList();
        }
        if (auctionSearch.getName().length() != 0)
        {
            auctions = auctions.stream().filter(auction -> auction.getName().startsWith(auctionSearch.getName())).toList();
        }
        if (auctionSearch.getThematics() != null)
        {
            auctions = auctions.stream().filter(auction -> auction.getAuctionData().getThematics().equals(auctionSearch.getThematics())).toList();
        }
        return auctions;
    }
    
    public boolean isMember(Auction auction, User member)
    {
       return auction.getAuctionData().getMembers().contains(member);
    }
    
    public List<Auction> findAllWithStartDateBeforeMsg0()
    {
        return auctionRepostitory.findAllWithStartDateBeforeMsg0(LocalDateTime.now());
    }
    
    public List<Auction> findAllWithStartDateBeforeMsg1()
    {
        return auctionRepostitory.findAllWithStartDateBeforeMsg1(LocalDateTime.now());
    }
    
    public void saveAll(List <Auction> auctions)
    {
        auctionRepostitory.saveAll(auctions);
    }
    
    public List<Transaction> getTransactions(Auction auction)
    {
        return transactionRepository.findByTransactionAuctionOrderByTransactDateDesc(auction);
    }
    
    public void saveTransactions(List<Transaction> transactions)
    {
        transactionRepository.saveAll(transactions);
    }
    
    public List<Auction> findByUserHost(User user)
    {
        return auctionRepostitory.findByUserHost(user);
    }
    public List<Auction> findByUserMembers(User user)
    {
        return auctionRepostitory.findByUserMembers(user);
    }
    
    public  List<Transaction> getTransactionsUser(User user)
    {
        return transactionRepository.findByTransactionUserOrderByTransactDateDesc(user);
    }
    
    
}
