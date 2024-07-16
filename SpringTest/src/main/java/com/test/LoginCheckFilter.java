//package com.test;
//
//import jakarta.servlet.*;
//import jakarta.servlet.http.HttpServletRequest;
//import jakarta.servlet.http.HttpServletResponse;
//import jakarta.servlet.http.HttpSession;
//import org.springframework.stereotype.Component;
//
//import java.io.IOException;
//
//@Component
//public class LoginCheckFilter implements Filter {
//    @Override
//    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
//            throws IOException, ServletException {
//        HttpServletRequest req = (HttpServletRequest) request;
//        HttpServletResponse res = (HttpServletResponse) response;
//
//        HttpSession session = req.getSession(false);
//        boolean loggedIn = (session != null && session.getAttribute("user") != null);
//        String loginURI = req.getContextPath() + "/login";
//
//        if (!loggedIn && !req.getRequestURI().equals(loginURI)) {
//            res.sendRedirect(loginURI); // 로그인 페이지로 리다이렉트
//        } else {
//            chain.doFilter(request, response);
//        }
//    }
//}

