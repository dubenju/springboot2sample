package com.example.demo.controller;

import javax.servlet.http.HttpSession;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.config.SessionInfo;

@RestController
@RequestMapping("/session")
public class LoginSampleController {

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public String login(HttpSession httpSession, @RequestParam("userid") String userId) {
        SessionInfo sessionInfo = new SessionInfo();
        sessionInfo.setUserId(userId);
        httpSession.setAttribute("user", sessionInfo);
        return "login OK";
    }

    @RequestMapping(value = "/info", method = RequestMethod.GET)
    public String getInfo(HttpSession httpSession) {
        SessionInfo sessionInfo = (SessionInfo)httpSession.getAttribute("user");
        if (sessionInfo == null) {
            return "sessionInfo is null";
        }
        return "user is " + sessionInfo.getUserId();
    }

    @RequestMapping(value = "/logout", method = RequestMethod.DELETE)
    public String logout(HttpSession httpSession) {
        httpSession.invalidate();
        return "logout OK";
    }
}
