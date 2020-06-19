package com.example.demo.controller.web.TEST0001;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.example.demo.controller.bean.TEST0001.TEST0001S01Form;

import com.example.demo.model.bean.TEST0001.TEST0001S01IndexInputBean;
import com.example.demo.model.bean.TEST0001.TEST0001S01IndexOutputBean;
import com.example.demo.model.bean.TEST0001.TEST0001S01SearchInputBean;
import com.example.demo.model.bean.TEST0001.TEST0001S01SearchOutputBean;

@Controller
public class TEST0001S01Controller extends TEST0001Controller {
    protected String getWindowId() {
        return TEST0001S01_ID;
    }
    // http://192.168.11.2:8081/TEST0001/TEST0001S01/index
    @RequestMapping(value = TEST0001S01_URL + "index", method = RequestMethod.GET)
    public String index(Model model, TEST0001S01Form form) {
        String chkRet = initCommon(model, form, WindowType.LIST);
        if (chkRet != ACCESS_CHK_OK) {
            return chkRet;
        }
        model.addAttribute("form", form);
        return getSelfTransferURL();
    }
    @RequestMapping(value = TEST0001S01_URL + "search", method = RequestMethod.POST)
    public String search(Model model, TEST0001S01Form form) {
        String chkRet = initCommon(model, form, WindowType.LIST);
        if (chkRet != ACCESS_CHK_OK) {
            return chkRet;
        }
        model.addAttribute("form", form);
        return getSelfTransferURL();
    }
}
