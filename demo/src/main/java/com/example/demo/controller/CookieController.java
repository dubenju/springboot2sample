package com.example.demo.controller;

import java.util.Arrays;
import java.util.stream.Collectors;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * http://localhost:8081/getCookies
 * http://localhost:8081/testCookieValue
 * http://localhost:8081/setCookies
 * http://localhost:8081/getCookies
 * http://localhost:8081/testCookieValue
 * http://localhost:8081/delCookies
 * http://localhost:8081/getCookies
 * http://localhost:8081/testCookieValue
 * 
 */
@RestController
public class CookieController {

    @RequestMapping(value = "/setCookies",method = RequestMethod.GET)
    public  String setCookies(HttpServletResponse response){
        //HttpServerletRequest 装请求信息类
        //HttpServerletRespionse 装相应信息的类
        Cookie cookie=new Cookie("sessionId","CookieTestInfo");
        // Cookie cookie = new Cookie("username", "Jovan");
        cookie.setMaxAge(7 * 24 * 60 * 60); // 7天过期
//        cookie.setSecure(true);  //Https 安全cookie
//        cookie.setHttpOnly(true);  //不能被js访问的Cookie
        response.addCookie(cookie);
        return "添加cookies信息成功";
    }
    @RequestMapping(value = "/getCookies",method = RequestMethod.GET)
    public  String getCookies(HttpServletRequest request){
        //HttpServletRequest 装请求信息类
        //HttpServletRespionse 装相应信息的类
     //   Cookie cookie=new Cookie("sessionId","CookieTestInfo");
        Cookie[] cookies =  request.getCookies();
        if(cookies != null){
            for(Cookie cookie : cookies){
                if(cookie.getName().equals("sessionId")){
                    return cookie.getValue();
                }
            }
//            return Arrays.stream(cookies)
//                    .map(c -> c.getName() + "=" + c.getValue())
//                    .collect(Collectors.joining(", "));

        }
     
        return "No cookies";
    }
    @RequestMapping("/testCookieValue")
    public String testCookieValue(@CookieValue(value = "sessionId", 
            defaultValue = "None") String sessionId ) {
       //前提是已经创建了或者已经存在cookie了，那么下面这个就直接把对应的key值拿出来了。
        return("testCookieValue,sessionId="+sessionId);
      
       
//        return "SUCCESS";
    }
    @RequestMapping(value = "/delCookies",method = RequestMethod.GET)
    public  String delCookies(HttpServletResponse response){
        //HttpServerletRequest 装请求信息类
        //HttpServerletRespionse 装相应信息的类
        // 将Cookie的值设置为null
        Cookie cookie=new Cookie("sessionId",null);
        // Cookie cookie = new Cookie("username", null);
        //将`Max-Age`设置为0
        cookie.setMaxAge(0);
//        cookie.setSecure(true);  //Https 安全cookie
//        cookie.setHttpOnly(true);  //不能被js访问的Cookie
        response.addCookie(cookie);
        return "删除cookies信息成功";
    }
}
