package com.test;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
public class TestController {
    private static final Logger log = LoggerFactory.getLogger(TestController.class);
    @Autowired private UserService userService;
    @GetMapping("/login")
    public void login() {}

//    @PostMapping("/user/login")
//    public String post_login(){
//
//    }

    @GetMapping("/home")
    public void home(){}

    @GetMapping("/admin")
    public String admin(
            Model model
    ){
        List<StoreDTO> stores = userService.getStoreList();
        model.addAttribute("stores", stores);
        return "admin";
    }

    @ResponseBody
    @PostMapping("/admin")
    public String admin(@RequestBody StoreDTO store){
        StoreDTO sto = userService.getStore(store);
        if(sto == null){
            userService.insertStore(store);
        }
        userService.updateLike(store);
        log.info(store.toString());
        return "redirect:/admin";
    }
}
