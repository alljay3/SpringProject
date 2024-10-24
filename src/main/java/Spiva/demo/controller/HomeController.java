
package Spiva.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 *
 * @author miha2
 */


@Controller
public class HomeController {
    
    
    @GetMapping("/")
    public String GetMapping()
    {
        //System.out.println(userService.getCurrentUser());
        //mySessionFactoryService.queryZapros();
        return "redirect:/auctions";
    }
    
}
