package com.test;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class TestController {
    private static final Logger log = LoggerFactory.getLogger(TestController.class);
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

    @PostMapping("/login")
    public String login(@RequestParam String username, HttpSession session) {
        // 간단한 아이디 검증
        if ("admin".equals(username)) {
            session.setAttribute("user", username);
            return "redirect:/admin"; // 관리자 페이지로 이동
        } else {
            session.setAttribute("user", username);
            return "redirect:/home"; // 홈 페이지로 이동
        }
    }

    @GetMapping("/login")
    public void login() {}

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
        log.info(store.toString());
        return ResponseEntity.ok("{\"message\":\"성공적으로 처리되었습니다.\"}");
    }
}
