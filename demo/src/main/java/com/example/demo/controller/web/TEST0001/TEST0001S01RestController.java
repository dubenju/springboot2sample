package com.example.demo.controller.web.TEST0001;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.example.demo.controller.bean.TEST0001.TEST0001S01Form;

import com.example.demo.model.bean.TEST0001.TEST0001S01IndexInputBean;
import com.example.demo.model.bean.TEST0001.TEST0001S01IndexOutputBean;
import com.example.demo.model.bean.TEST0001.TEST0001S01SearchInputBean;
import com.example.demo.model.bean.TEST0001.TEST0001S01SearchOutputBean;

@RestController
public class TEST0001S01RestController extends TEST0001Controller {
    protected String getWindowId() {
        return TEST0001S01_ID;
    }
    @RequestMapping(value = TEST0001S01_URL + "decline", method = RequestMethod.POST)
    @ResponseBody
    public String decline(Model model, TEST0001S01Form form) {
        String chkRet = initCommon(model, form, WindowType.LIST);
        if (chkRet != ACCESS_CHK_OK) {
            return chkRet;
        }
        return getSelfTransferURL();
    }
}
