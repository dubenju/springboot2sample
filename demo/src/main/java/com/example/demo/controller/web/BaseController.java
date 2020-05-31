package com.example.demo.controller.web;

import java.util.Calendar;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.ui.Model;

import com.example.demo.controller.bean.BaseForm;

abstract public class BaseController implements BaseControllerPathInfo {

    @Value("${apl.session.timeout}")
    private int aplSessionTimeout;
    
    protected static final String ACCESS_CHK_OK = "";
    
    @Autowired
    protected HttpServletRequest requst;
    
    public static enum WindowType {
        REGIST
        , EDITSHOW
        , LIST
        , LISTEDIT
    }
    
    public BaseController() {
        
    }
    abstract protected String getWindowId();
    abstract protected String getFnctionId();
    protected String initCommon(Model model, BaseForm form, WindowType wType) {
        model.addAttribute("fnctionId", getFnctionId());
        model.addAttribute("windowId", getWindowId());
        
        Calendar now = Calendar.getInstance();
        model.addAttribute("SYS_DATE_YEAR", now.get(Calendar.YEAR));
        model.addAttribute("SYS_DATE_MONTH", now.get(Calendar.MONTH) + 1);
        model.addAttribute("SYS_DATE_DAY", now.get(Calendar.DAY_OF_MONTH));
        
        form.setWindowId(getWindowId());
        
        switch(wType) {
        case REGIST:
            break;
        case EDITSHOW:
            break;
        case LISTEDIT:
            break;
        case LIST:
            break;
        default:
            break;
        }
        return ACCESS_CHK_OK;
    }
    protected String getSelfTransferURL() {
        return getFnctionId() + "/" + getWindowId();
    }
}
