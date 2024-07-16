package com.test;

import jakarta.servlet.http.HttpSession;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Log4j2
@Controller
public class TestController {

    @Autowired private UserService userService;
    @GetMapping("/**")
    public String start(HttpSession session){
        if(session.getAttribute("user") == null){
            return "redirect:/login";
        }
        if(session.getAttribute("user") == "admin"){
            return "redirect:/admin";
        }
        return "redirect:/home";
    }

    @GetMapping("/login")
    public void login() {}

    @PostMapping("/login")
    public String login(@RequestParam String username, HttpSession session) {
        if ("admin".equals(username)) {
            session.setAttribute("user", username);
            return "redirect:/admin";
        } else {
            session.setAttribute("user", username);
            return "redirect:/home";
        }
    }

    @GetMapping("/home")
    public void home(){}

    @GetMapping("/admin")
    public String admin(
            @RequestParam(required = false) String keyword,
            HttpSession session,
            Model model
    ){
        String username = (String) session.getAttribute("user");
        if (!"admin".equals(username)) {
            return "redirect:/home";
        }
        List<StoreDTO> stores = userService.getStoreList(keyword);
        model.addAttribute("stores", stores);
        model.addAttribute("keyword", keyword);
        return "admin";
    }

    @ResponseBody
    @PostMapping("/admin")
    public ResponseEntity<String> admin(@RequestBody StoreDTO store){
        StoreDTO sto = userService.getStore(store);
        if(sto == null){
            System.out.println(sto);
            userService.insertStore(store);
        }else{
            userService.updateLike(sto);
        }
        return ResponseEntity.ok("{\"message\":\"성공적으로 처리되었습니다.\"}");
    }
}
