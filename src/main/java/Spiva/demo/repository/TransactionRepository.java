/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package Spiva.demo.repository;

import Spiva.demo.models.Auction;
import Spiva.demo.models.Transaction;
import Spiva.demo.models.User;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 *
 * @author miha2
 */
public interface TransactionRepository extends JpaRepository<Transaction, Long> {
    List<Transaction> findByTransactionAuctionOrderByTransactDateDesc(Auction transactionAuction);
    List<Transaction> findByTransactionUserOrderByTransactDateDesc(User transactionUser);
}
