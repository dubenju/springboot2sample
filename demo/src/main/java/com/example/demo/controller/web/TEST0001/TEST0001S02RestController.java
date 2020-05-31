package com.example.demo.controller.web.TEST0001;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.example.demo.controller.bean.TEST0001.TEST0001S02Form;

import com.example.demo.model.bean.TEST0001.TEST0001S02IndexInputBean;
import com.example.demo.model.bean.TEST0001.TEST0001S02IndexOutputBean;
import com.example.demo.model.bean.TEST0001.TEST0001S02UpdateInputBean;
import com.example.demo.model.bean.TEST0001.TEST0001S02UpdateOutputBean;

@RestController
public class TEST0001S02RestController extends TEST0001Controller {
    protected String getWindowId() {
        return TEST0001S02_ID;
    }
    @RequestMapping(value = TEST0001S02_URL + "index", method = RequestMethod.POST)
    @ResponseBody
    public String index(Model model, TEST0001S02Form form) {
        String chkRet = initCommon(model, form, WindowType.LIST);
        if (chkRet != ACCESS_CHK_OK) {
            return chkRet;
        }
        model.addAttribute("form", form);
        return getSelfTransferURL();
    }
    @RequestMapping(value = TEST0001S02_URL + "update", method = RequestMethod.POST)
    @ResponseBody
    public String update(Model model, TEST0001S02Form form) {
        String chkRet = initCommon(model, form, WindowType.LIST);
        if (chkRet != ACCESS_CHK_OK) {
            return chkRet;
        }
        model.addAttribute("form", form);
        return getSelfTransferURL();
    }
}
