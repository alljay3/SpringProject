package Spiva.demo.component;

import Spiva.demo.exeptions.RoleRegisterExeption;
import Spiva.demo.exeptions.UserRegisterExeption;
import Spiva.demo.models.Auction;
import Spiva.demo.models.AuctionData;
import Spiva.demo.models.Role;
import Spiva.demo.models.Thematics;
import Spiva.demo.models.User;
import Spiva.demo.models.UserData;
import Spiva.demo.service.AuctionService;
import Spiva.demo.service.NotificationService;
import Spiva.demo.service.RoleService;
import Spiva.demo.service.ThematicsService;
import Spiva.demo.service.UserService;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

/**
 *
 * @author miha2
 */
@Component
public class DatabaseInitializer implements CommandLineRunner {

    @Autowired
    private UserService userService;
    
    @Autowired
    private RoleService roleService;
    
    @Autowired
    private NotificationService notificationService;
    
    @Autowired
    private ThematicsService thematicsService;
    
    @Autowired
    private AuctionService auctionService;

    @Override
    public void run(String... args) throws Exception {
        
       //init();
       
    }
    
    
    void init()
    {
        //Добавляем роль
        
        Role roleAmd = new Role();
        Role roleUser = new Role();
        roleAmd.setName("ROLE_ADMIN");
        roleUser.setName("ROLE_USER");
        try
        {
            roleService.save(roleAmd);
        }
        catch (RoleRegisterExeption e)
        {
            System.out.println(e.getMessage());
        }
        
        try
        {
            roleService.save(roleUser);
        }
        catch (RoleRegisterExeption e)
        {
            System.out.println(e.getMessage());
        }
        
        
        // Добавляем админа
        User admUser = new User();
        admUser.setUserData(new UserData());
        admUser.setRoles(new HashSet<Role>());
        admUser.setLogin("alljay");
        admUser.setPassword("2319");
        admUser.getUserData().setDeposit(100000);
        admUser.getUserData().setEmail("mihaspiva@yandex.ru");
        admUser.getUserData().setNumberPhone("89500947595");
        admUser.getRoles().add(roleAmd);
        admUser.getRoles().add(roleUser);
        try
        {
            userService.registerUser(admUser);
        }
        catch (UserRegisterExeption e)
        {
            System.out.println(e.getMessage());
        }
        
        
        // Добавляем user1
        
        User user1 = new User();
        user1 .setUserData(new UserData());
        user1 .setRoles(new HashSet<Role>());
        user1 .setLogin("1234");
        user1 .setPassword("1234");
        user1 .getUserData().setDeposit(100);
        user1 .getUserData().setEmail("mihaspiva2@yandex.ru");
        user1 .getUserData().setNumberPhone("89500947596");
        user1 .getRoles().add(roleUser);
        try
        {
            userService.registerUser(user1 );
        }
        catch (UserRegisterExeption e)
        {
            System.out.println(e.getMessage());
        }
        
        // Добавляем user2
        
        User user2 = new User();
        user2 .setUserData(new UserData());
        user2 .setRoles(new HashSet<Role>());
        user2 .setLogin("mama");
        user2 .setPassword("mama");
        user2 .getUserData().setDeposit(200);
        user2 .getUserData().setEmail("mihaspiva3@yandex.ru");
        user2 .getUserData().setNumberPhone("89500947597");
        user2 .getRoles().add(roleUser);
        try
        {
            userService.registerUser(user2 );
        }
        catch (UserRegisterExeption e)
        {
            System.out.println(e.getMessage());
        }
        
         // Добавляем user3
        
        User user3 = new User();
        user3 .setUserData(new UserData());
        user3 .setRoles(new HashSet<Role>());
        user3 .setLogin("papa");
        user3 .setPassword("papa");
        user3 .getUserData().setDeposit(300);
        user3 .getUserData().setEmail("mihaspiva4@yandex.ru");
        user3 .getUserData().setNumberPhone("89500947588");
        user3 .getRoles().add(roleUser);
        try
        {
            userService.registerUser(user3 );
        }
        catch (UserRegisterExeption e)
        {
            System.out.println(e.getMessage());
        }
        
         // Добавляем user4
        
        User user4 = new User();
        user4 .setUserData(new UserData());
        user4 .setRoles(new HashSet<Role>());
        user4 .setLogin("mylord");
        user4 .setPassword("mylord");
        user4 .getUserData().setDeposit(400);
        user4 .getUserData().setEmail("mihaspiva4@yandex.ru");
        user4 .getUserData().setNumberPhone("89900947599");
        user4 .getRoles().add(roleUser);
        try
        {
            userService.registerUser(user4 );
        }
        catch (UserRegisterExeption e)
        {
            System.out.println(e.getMessage());
        }
        
         
         List <User> users = new LinkedList<User>();
         users.add(admUser);
         users.add(user1);
         users.add(user3);
         
         Thematics tem1 = new Thematics();
         tem1.setName("Коровки");
         thematicsService.save(tem1);
         
         Thematics tem2 = new Thematics();
         tem2.setName("Кошечки");
         thematicsService.save(tem2);
         
         Thematics tem3 = new Thematics();
         tem3.setName("Собачки");
         thematicsService.save(tem3);
         
         
         Auction auction1 = new Auction();
         auction1.setName("Кисики");
         auction1.setUserHost(user2);
         auction1.setAuctionData(new AuctionData());
         auction1.getAuctionData().setActive(true);
         auction1.getAuctionData().setDescription("мама мыла раму");
         auction1.getAuctionData().setMembers(users);
         auction1.getAuctionData().setStartCost(5);
         auction1.getAuctionData().setTimeEndDelay(1);
         auction1.getAuctionData().setThematics(tem3);
         //auction1.getAuctionData().setStartDate(LocalDateTime.now().plusMinutes(1));
         auction1.getAuctionData().setStartDate(LocalDateTime.now());
         
         Auction auction2 = new Auction();
         auction2.setName("Ежики");
         auction2.setUserHost(user2);
         auction2.setAuctionData(new AuctionData());
         auction2.getAuctionData().setActive(true);
         auction2.getAuctionData().setDescription("мама мыла ежика ");
         auction2.getAuctionData().setMembers(users);
         auction2.getAuctionData().setStartCost(1);
         auction2.getAuctionData().setTimeEndDelay(1);
         auction2.getAuctionData().setThematics(tem1);
         auction2.getAuctionData().setStartDate(LocalDateTime.now().plusMinutes(50));
         auctionService.save(auction1);
         auctionService.save(auction2);
    }
}
