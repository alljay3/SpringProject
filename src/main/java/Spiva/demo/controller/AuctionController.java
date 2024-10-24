
package Spiva.demo.controller;

import Spiva.demo.exeptions.ActualDateExeption;
import Spiva.demo.exeptions.DateIsNull;
import Spiva.demo.exeptions.RoleRegisterExeption;
import Spiva.demo.models.Auction;
import Spiva.demo.models.AuctionData;
import Spiva.demo.models.AuctionSearch;
import Spiva.demo.models.Thematics;
import Spiva.demo.models.Transaction;
import Spiva.demo.models.User;
import Spiva.demo.service.AuctionService;
import Spiva.demo.service.NotificationService;
import Spiva.demo.service.RoleService;
import Spiva.demo.service.ThematicsService;
import Spiva.demo.service.UserService;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.util.LinkedList;
import java.util.List;
import java.util.UUID;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

/**
 *
 * @author miha2
 */

@Controller
@RequestMapping("/auctions") 

public class AuctionController {
    
    private static String UPLOADED_FOLDER = "Aimages/";
    
    @Autowired
    UserService userService;
    
    @Autowired
    RoleService RoleService;
    
    @Autowired
    NotificationService notificationService;
    
    @Autowired
    AuctionService auctionService;
    
    @Autowired
    ThematicsService thematicsService;
    
    @ModelAttribute("hasNotification")
    public boolean hasNotification()
    {
        return notificationService.hasNotReadedNotifications(currentUser());
    }
    
    
    
    @ModelAttribute("getUser")
    public User currentUser()
    {
        return userService.getCurrentUser();
    }
    
    @ModelAttribute("isAdmin")
    public boolean isAdmin()
    {
        return RoleService.isAdmin();
    }
    
    @ModelAttribute("listThematics")
    public List<Thematics> getThematics()
    {
        return thematicsService.findAll();
    }
    

    @GetMapping("")
    public String GetMapping(Model model)
    {
        model.addAttribute("auctions", auctionService.getActualyAuction());
        model.addAttribute("auctionsSearch", new AuctionSearch());
        return "home";
    }
    
    @GetMapping("/create")
    public String CreateAuction(Model model)
    {
        Auction auction = new Auction();
        auction.setAuctionData(new AuctionData());
        auction.getAuctionData().setStartCost(100);
        auction.getAuctionData().setTimeEndDelay(1);
        model.addAttribute("auction", auction);
        return "auction/auctionCteate";
    }
    
    
    @PostMapping("/create")
    public String createAuction(@Valid @ModelAttribute("auction") Auction auction, BindingResult result , Model model, RedirectAttributes redirectAttributes) {
        if(result.hasErrors()){
            return "auction/auctionCteate";
        }
        try{
            auction.setUserHost(currentUser());
            auction.getAuctionData().setMembers(new LinkedList<User>());
            auction.getAuctionData().setActive(true);
            auctionService.createAuction(auction);
        }
        catch (ActualDateExeption e)
        {
            model.addAttribute("ErrorMessage",e.getMessage());
            return "auction/auctionCteate";
        }
        catch (DateIsNull e)
        {
            model.addAttribute("ErrorMessageData",e.getMessage());
            return "auction/auctionCteate";
        }
        redirectAttributes.addFlashAttribute("succesfull", "Аукцион создан");
        return "redirect:/auctions";
    }
    
    
    @GetMapping("/{id}")
    public String getInfo(@PathVariable("id") int id, Model model) {
        Auction auction = auctionService.findById(id);
        List<Transaction> transactions = auctionService.getTransactions(auction);
        if (auction == null)
            return "redirect:/auctions";
        model.addAttribute("isMember", auctionService.isMember(auction, currentUser()));
        model.addAttribute("status",auctionService.status(auction));
        model.addAttribute("transactions", transactions);
        model.addAttribute("auction", auction);
        if (auctionService.status(auction) > 0)
            return "auction/auctionInfo";
        
        if (auction.getUserHost().equals(currentUser()) || isAdmin())
        {
            return "auction/auctionChange";
        }
        return "auction/auctionInfo";
    }
    
    @PostMapping("/update")
    public String changeAuction(@Valid @ModelAttribute("auction") Auction auction, BindingResult result , RedirectAttributes redirectAttributes) {
        System.out.println(result.getAllErrors());
        if(result.hasErrors()){
            
            return "auction/auctionChange";
        }
        
        Auction myAuction = auctionService.findById(auction.getId());
        myAuction.setName(auction.getName());
        myAuction.getAuctionData().setDescription(auction.getAuctionData().getDescription());
        myAuction.getAuctionData().setStartCost(auction.getAuctionData().getStartCost());
        myAuction.getAuctionData().setTimeEndDelay(auction.getAuctionData().getTimeEndDelay());
        
        if (!(myAuction.getUserHost().equals(currentUser()) || isAdmin()) || auctionService.auctionIsStarted(myAuction) || auctionService.status(myAuction) > 0)
        {
            return "redirect:/auctions";
        }
        auctionService.save(myAuction);
        redirectAttributes.addFlashAttribute("succesfull", "Данные изменены");
        return "redirect:/auctions/" + myAuction.getId();
    }
    
    @Transactional
    @PostMapping("/delete")
    public String deleteAuction(@ModelAttribute("auction") Auction auction, RedirectAttributes redirectAttributes)
    {
        Auction MyAuction = auctionService.findById(auction.getId());
        if (!(MyAuction.getUserHost().equals(currentUser()) || isAdmin()) || auctionService.auctionIsStarted(MyAuction))
        {
            return "redirect:/auctions";
        }
        auctionService.deleteById(MyAuction.getId());
        redirectAttributes.addFlashAttribute("succesfull", "Аукцион удален");
        return "redirect:/auctions";
    }
    
    
    @PostMapping("/loadfile")
    public String singleFileUpload(@RequestParam("file") MultipartFile file,
                                   RedirectAttributes redirectAttributes, @ModelAttribute("auction") Auction auction) {
        
        
        if (file.isEmpty()) {
            redirectAttributes.addFlashAttribute("fileUploadNo", "Пожалуйста, выберите файл для загрузки");
            return "redirect:/auctions/" + auction.getId();
        }

        try {
            Auction myauction = auctionService.findById(auction.getId());
            if (!(myauction.getUserHost().equals(currentUser()) || isAdmin()) || auctionService.auctionIsStarted(myauction))
                return "redirect:/auctions";
            
            
            String originalFilename = file.getOriginalFilename();
            String uniqueFilename = UUID.randomUUID().toString() + "_" + originalFilename;
            byte[] bytes = file.getBytes();
            
            Path path = Paths.get(UPLOADED_FOLDER + uniqueFilename);
            Files.write(path, bytes);
            
            myauction.getAuctionData().setPhoto(uniqueFilename);
            auctionService.save(myauction);
            redirectAttributes.addFlashAttribute("fileUploadYes",
                    "Вы успешно загрузили '" + file.getOriginalFilename() + "'");
            

        } catch (IOException e) {
            e.printStackTrace();
        }
        
        
        
        
        return "redirect:/auctions/" + auction.getId();
    }
    
    
    @PostMapping("/findpost")
    public String postFind(Model model, @ModelAttribute("auctionSearch") AuctionSearch auctionSearch) {
        List<Auction> auctions = auctionService.getSeatchAuction(auctionSearch);
        model.addAttribute("auctions", auctions);
        model.addAttribute("auctionsSearch", auctionSearch);
        return "home";
    }
    
    @PostMapping("/joinAuction")
    public String postJoin(@ModelAttribute("auction") Auction auction, Model model) {
        Auction myAuction = auctionService.findById(auction.getId());
        if (myAuction.getUserHost().equals(currentUser()) || isAdmin() || auctionService.auctionIsStarted(myAuction) || auctionService.isMember(myAuction, currentUser()))
        {
            return "redirect:/auctions";
        }
        myAuction.getAuctionData().getMembers().add(currentUser());
        auctionService.save(myAuction);
        notificationService.sendMessageUser(currentUser(), "Вы присоединились к аукциону : " + myAuction.getName());
        return "redirect:/auctions/" + auction.getId();
    }
    
    @PostMapping("/leaveAuction")
    public String postLeave(@ModelAttribute("auction") Auction auction, Model model) {
        Auction myAuction = auctionService.findById(auction.getId());
        if (myAuction.getUserHost().equals(currentUser()) || isAdmin() || auctionService.auctionIsStarted(myAuction) || !auctionService.isMember(myAuction, currentUser()))
        {
            return "redirect:/auctions";
        }
        myAuction.getAuctionData().getMembers().remove(currentUser());
        auctionService.save(myAuction);
        notificationService.sendMessageUser(currentUser(), "Вы вышли из аукциона: " + myAuction.getName());
        return "redirect:/auctions/" + auction.getId();
    }
    
    @PostMapping("/upCost")
    public String upCost(@RequestParam("cost") int cost, @ModelAttribute("auction") Auction auction, RedirectAttributes redirectAttributes)
    {
        Auction myAuction = auctionService.findById(auction.getId());
        System.out.println(cost);
        if (myAuction.getUserHost().equals(currentUser()) || isAdmin() || auctionService.status(myAuction) != 1 || !auctionService.isMember(myAuction, currentUser()) || cost <= 0)
        {
            return "redirect:/auctions";
        }
        
        List<Transaction> transactions = auctionService.getTransactions(myAuction);
        if (currentUser().getUserData().getDeposit() < cost)
        {
            redirectAttributes.addFlashAttribute("UpCostError","У вас нет столько денег");
            return "redirect:/auctions/" + auction.getId();
        }
        
        
        if (transactions.isEmpty())
        {
            if (cost < myAuction.getAuctionData().getStartCost())
            {
                redirectAttributes.addFlashAttribute("UpCostError","Ваша ставка ниже стартовой цены");
                return "redirect:/auctions/" + auction.getId();
            }
                
            Transaction transaction = new Transaction();
            transaction.setTransactDate(LocalDateTime.now());
            transaction.setTransactionAuction(myAuction);
            transaction.setTransactionUser(currentUser());
            transaction.setCost(cost);
            transactions.add(transaction);
        }
        else
        {
            System.out.println(transactions.get(0).getCost());
            if (transactions.get(0).getCost() >= cost)
            {
                redirectAttributes.addFlashAttribute("UpCostError","Ваша ставка ниже, либо равна текущей");
                return "redirect:/auctions/" + auction.getId();
            }
            Transaction transaction = new Transaction();
            transaction.setTransactDate(LocalDateTime.now());
            transaction.setTransactionAuction(myAuction);
            transaction.setTransactionUser(currentUser());
            transaction.setCost(cost);
            
            notificationService.sendMessageUser(transactions.get(0).getTransactionUser(), "Ваша ставка была повышена! Аукцион: '" + myAuction.getName() + "'"  );
            User lastUser = transactions.get(0).getTransactionUser();
            lastUser.getUserData().setDeposit(lastUser.getUserData().getDeposit() + transactions.get(0).getCost());
            userService.saveNoPassword(lastUser);
            transactions.add(transaction);
                
        }
        
        currentUser().getUserData().setDeposit(currentUser().getUserData().getDeposit() - cost);
        auctionService.saveTransactions(transactions);
        userService.saveNoPassword(currentUser());
        redirectAttributes.addFlashAttribute("UpCostSuccessfull","Ставка повышена");
        return "redirect:/auctions/" + auction.getId();
    }
    
    @GetMapping("/myAuctionList")
    public String myAuctionList(Model model)
    {
        model.addAttribute("auctions", auctionService.findByUserMembers(currentUser()));
        return "myauction";
    }
    
    @GetMapping("/createdAuctionList")
    public String createdAuctionList(Model model)
    {
        model.addAttribute("auctions", auctionService.findByUserHost(currentUser()));
        return "myauction";
    }
    
    
}
