package com.example.demo.common.aspect;

import java.io.BufferedReader;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Enumeration;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.MDC;
import org.springframework.stereotype.Component;
import org.springframework.util.StopWatch;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

@Aspect
@Component
public class LogAspect {
    private static final Logger LOGGER = LoggerFactory.getLogger("ControllerLog");

    /*
     * AspectJ类型匹配的通配符：
     *   1、 *：匹配任何数量字符；
     *   2、..：匹配任何数量字符的重复，如在类型模式中匹配任何数量子包；而在方法参数模式中匹配任何数量参数。
     *   3、+：匹配指定类型的子类型；仅能作为后缀放在类型模式后边。
     */
    @Around("execution(* com.example.demo.controller..*(..))")
    public Object logging(ProceedingJoinPoint pjp) throws Throwable {
        Object result;
        StopWatch stopWatch = new StopWatch();
        HttpServletRequest req = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        Calendar cl = Calendar.getInstance();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss.SSS");
        StringBuilder buf = new StringBuilder();
        buf.append("\nStart Time : ");
        buf.append(sdf.format(cl.getTime()));
        buf.append("\n");

        buf.append("UUID : ");
        String uuid = MDC.get("sequence");
        buf.append(uuid);
        buf.append("\n");
        buf.append("HttpServletRequest--+---------+---------*---------+---------+---------+---------\n");

        buf.append("Request Sent Server :");
        buf.append(req.getServerName());
        buf.append(":");
        buf.append(req.getServerPort());
        buf.append("[ServerName:ServerPort]\n");

        buf.append("Request Remote Client :");
        buf.append(req.getRemoteHost());
        buf.append("(");
        buf.append(req.getRemoteAddr());
        buf.append(")");
        buf.append(req.getRemoteUser());
        buf.append("[RemoteHost(RemoteAddr)RemoteUser]\n");

        buf.append(req.getMethod());
        buf.append(" ");
        buf.append(req.getRequestURL());
        buf.append(" ");
        buf.append(req.getProtocol());
        buf.append("[Method RequestURL Protocol]\n");

        buf.append("HTTP Header:\n");

        Enumeration<String> qhns = req.getHeaderNames();
        while(qhns.hasMoreElements()) {
            String name = qhns.nextElement();
            buf.append("  ");
            buf.append(name);
            buf.append(":");
            buf.append(req.getHeader(name));
            buf.append("\n");
        }

        buf.append("Scheme:");
        buf.append(req.getScheme());
        buf.append("\n");

        buf.append("ContentType:");
        buf.append(req.getContentType());
        buf.append("\n");

        int contentLength = req.getContentLength();
        buf.append("ContentLength:");
        buf.append(contentLength);
        buf.append("\n");

        buf.append("Prameter:\n");

        Enumeration<String> qparamns = req.getParameterNames();
        while(qparamns.hasMoreElements()) {
            String name = qparamns.nextElement();
            buf.append("  ");
            buf.append(name);
            buf.append(":");
            buf.append(req.getParameter(name));
            buf.append("\n");
        }

        buf.append("Attribute:\n");

        Enumeration<String> qans = req.getAttributeNames();
        while(qans.hasMoreElements()) {
            String name = qans.nextElement();
            if ("org.springframework.core.convert.ConversionService".equals(name)) {
                buf.append("  ");
                buf.append(name);
                buf.append(": IS SKIPED!!!");
                buf.append("\n");
                continue;
            }
            buf.append("  ");
            buf.append(name);
            buf.append(":");
            buf.append(req.getAttribute(name));
            buf.append("\n");
        }

        String sessionId = null;
        HttpSession session = req.getSession();
        if (session != null) {
            sessionId = session.getId();
        }
        buf.append("SessionId:");
        buf.append(sessionId);
        buf.append("[ServletRequest.Session.Id]\n");

        buf.append("SessionId:");
        buf.append(req.getRequestedSessionId());
        buf.append("[RequestedSessionId]\n");

        buf.append(req.getRequestURI());
        buf.append("[RequestURI]\n");

        String encode = req.getCharacterEncoding();
        buf.append("CharacterEncoding:");
        buf.append(encode);
        buf.append("\n");

        if (contentLength > 0) {
            BufferedReader br = req.getReader();
            if (br.markSupported()) {
                br.mark(contentLength + 1);
                buf.append("mark Supported\n");
            } else {
                buf.append("mark/reset not Supported\n");
            }
            char[] buffer = new char[contentLength];
            br.read(buffer);
            String line = new String(buffer);
            buf.append(line);
            br.reset();
            buf.append("reset Supported\n");
        }
        
        buf.append("Cookie:\n");
        Cookie[] cookies = req.getCookies();
        if (cookies != null) {
            for(Cookie cookie : cookies) {
                buf.append(cookie.getName());
                buf.append(":");
                buf.append(cookie.getValue());
                buf.append("[Domain:");
                buf.append(cookie.getDomain());
                buf.append(",Path:");
                buf.append(cookie.getPath());
                buf.append(",MaxAge:");
                buf.append(cookie.getMaxAge());
                buf.append(",Version:");
                buf.append(cookie.getVersion());
                buf.append(",Comment:");
                buf.append(cookie.getComment());
                buf.append("]\n");
            }
        }
        buf.append("----------+---------+---------+---------*---------+---------+-HttpServletRequest\n");
        LOGGER.info(buf.toString());
        buf = null;

        stopWatch.start();
        try {
            result = pjp.proceed();
        } finally {
            stopWatch.stop();
            buf = new StringBuilder();
            buf.append("\ntime:");
            buf.append(stopWatch.getTotalTimeMillis());
            buf.append("[msec]\n");
            HttpServletResponse response = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getResponse();
            /* cookie中有旧的可能行， 同一端末多画面交替执行时，NG。*/
            Cookie cookie=new Cookie("PrevUUID",uuid);
            cookie.setMaxAge(-1); // 负数-1，表示此cookie只是存储在浏览器内存里，只要关闭浏览器，此cookie就会消失。
            response.addCookie(cookie);
            buf.append("HttpServletResponse-+---------+---------*---------+---------+---------+---------\n");
            buf.append(response.isCommitted());
            buf.append("[ServletResponse.isCommitted]\n");

            String characterEncoding = response.getCharacterEncoding();
            buf.append("CharacterEncoding:");
            buf.append(characterEncoding);
            buf.append("\n");

            int bufferSize = response.getBufferSize();
            buf.append("BufferSize:");
            buf.append(bufferSize);
            buf.append("\n");

            buf.append("ContentType:");
            buf.append(response.getContentType());
            buf.append("\n");

            buf.append("Locale:" + response.getLocale());
            buf.append("\n");

            buf.append("End Time : ");
            buf.append(sdf.format(cl.getTime()));
            buf.append("\n");
            buf.append("----------+---------+---------+---------*---------+---------+HttpServletResponsen\n");
            buf.append("********************************************************************************\n\n");
            LOGGER.info(buf.toString());
            buf = null;
        }
        return result;
    }
}
