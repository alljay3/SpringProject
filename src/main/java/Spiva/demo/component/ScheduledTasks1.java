package Spiva.demo.component;


import Spiva.demo.models.Auction;
import Spiva.demo.models.Transaction;
import Spiva.demo.service.AuctionService;
import Spiva.demo.service.NotificationService;
import jakarta.transaction.Transactional;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class ScheduledTasks1 {
    
    @Autowired
    AuctionService auctionService;
    
    @Autowired
    NotificationService notificationService;
    
    @Transactional
    @Scheduled(fixedRate = 5000)
    public void executeTask() {
        List<Auction> auctionsMsg0 = auctionService.findAllWithStartDateBeforeMsg0();
        for (Auction auction : auctionsMsg0)
        {
            
            notificationService.sendMessageUsers(auction.getAuctionData().getMembers(), "Аукцион: '" + auction.getName() + "' начался");
            auction.getAuctionData().setSandMsg(1);
        }
        auctionService.saveAll(auctionsMsg0);
        
        List<Auction> auctionsMsg1 = auctionService.findAllWithStartDateBeforeMsg1();
        
        for (Auction auction : auctionsMsg1)
        {
            List<Transaction> transactions = auctionService.getTransactions(auction);
            if (transactions.isEmpty())
            {
                if (ChronoUnit.MINUTES.between(auction.getAuctionData().getStartDate(), LocalDateTime.now()) >= auction.getAuctionData().getTimeEndDelay())
                {
                    auction.getAuctionData().setActive(false);
                    notificationService.sendMessageUsers(auction.getAuctionData().getMembers(), "Аукцион: '" + auction.getName() + "' закончился. Победителя нет");
                    auction.getAuctionData().setSandMsg(2);
                }
            }
            else
            {
                if (ChronoUnit.MINUTES.between(transactions.get(0).getTransactDate(), LocalDateTime.now()) >= auction.getAuctionData().getTimeEndDelay())
                {
                    auction.getAuctionData().setActive(false);
                    notificationService.sendMessageUsers(auction.getAuctionData().getMembers(), "Аукцион: '" + auction.getName() + "' закончился. Победитель: " + transactions.get(0).getTransactionUser().getLogin());
                    auction.getAuctionData().setSandMsg(2);
                }
            }
        }
        auctionService.saveAll(auctionsMsg1);
        
    }
}