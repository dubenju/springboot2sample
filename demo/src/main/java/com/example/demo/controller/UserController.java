package com.example.demo.controller;

import java.util.List;

import javax.annotation.Resource;
import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.example.demo.controller.bean.UserForm;
import com.example.demo.service.IUserService;

@Controller
public class UserController {

    @Resource
    IUserService userService;

    @RequestMapping("/")
    public String index() {
        return "redirect:/list";
    }

    @RequestMapping("/list")
    public String list(Model model) {
        List<UserForm> users = userService.getUserList();
        model.addAttribute("users", users);
        return "user/list";
    }

    @RequestMapping("/toAdd")
    public String toAdd(Model model) {
    	model.addAttribute("user", new UserForm());
        return "user/userAdd";
    }

    @RequestMapping(path="/add", method=RequestMethod.POST)
    public String add(ModelAndView mav, @Valid @ModelAttribute(value = "user") UserForm user, BindingResult result) {
System.out.println(user);
    if (result.hasFieldErrors()) {
        mav.addObject("errors", result.getFieldErrors());
        mav.addObject("user", user);
        return "user/userAdd";
    }
mav.addObject("user",user);
        userService.save(user);
        return "redirect:/list";
    }

    @RequestMapping("/toEdit")
    public String toEdit(Model model, Long id) {
        UserForm user = userService.findUserById(id);
        model.addAttribute("user", user);
        return "user/userEdit";
    }

    @RequestMapping(path="/edit", method=RequestMethod.POST)
    public String edit(UserForm user) {
        userService.edit(user);
        return "redirect:/list";
    }


    @RequestMapping("/delete")
    public String delete(UserForm id) {
        userService.delete(id);
        return "redirect:/list";
    }
}
