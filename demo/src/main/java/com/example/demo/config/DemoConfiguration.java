package com.example.demo.config;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.Enumeration;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.catalina.filters.RemoteIpFilter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.MDC;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.example.demo.DbjServletResponseWrapper;
import com.example.demo.utils.UBytes;

@Configuration
public class DemoConfiguration {
    private static final Logger LOGGER = LoggerFactory.getLogger("FilterLog");
    @Bean
    public RemoteIpFilter remoteIpFilter() {
        return new RemoteIpFilter();
    }

    @Bean
    public FilterRegistrationBean<Filter> testFilterRegistration() {

        FilterRegistrationBean<Filter> registration = new FilterRegistrationBean<Filter>();
        registration.setFilter(new MyFilter());
        registration.addUrlPatterns("/*");
        registration.addInitParameter("paramName", "paramValue");
        registration.setName("MyFilter");
        registration.setOrder(1);
        return registration;
    }
    
    public class MyFilter implements Filter {

        @Override
        public void init(FilterConfig filterConfig) throws ServletException {
            System.out.println(this.getClass().getName() + ".init");
            LOGGER.info(this.getClass().getName() + "初始化过滤器");
        }

        @Override
        public void doFilter(ServletRequest srequest, ServletResponse sresponse, FilterChain filterChain)
                throws IOException, ServletException {
            HttpServletRequest req = (HttpServletRequest) srequest;
            LOGGER.info("过滤器处理规则");
            LOGGER.info("********************************************************************************\n\n");
            System.out.println(MDC.get("sequence"));
            System.out.println("HttpServletRequest--+---------+---------*---------+---------+---------+---------");
            System.out.println("Request Sent Server :" + req.getServerName() + ":" + req.getServerPort() + "[ServerName:ServerPort]");
            System.out.println("Request Remote Client :" + req.getRemoteHost() + "(" + req.getRemoteAddr() + ")" + req.getRemoteUser() + "[RemoteHost(RemoteAddr)RemoteUser]");
            System.out.println(req.getMethod() + " " + req.getRequestURL() + " " + req.getProtocol() + "[Method RequestURL Protocol]");
            System.out.println("HTTP Header:");
            Enumeration<String> qhns = req.getHeaderNames();
            while(qhns.hasMoreElements()) {
                String name = qhns.nextElement();
                System.out.println("  " + name + ":" + req.getHeader(name));
            }

            System.out.println("Scheme:" + req.getScheme());
            System.out.println("ContentType:" + req.getContentType());
            int contentLength = req.getContentLength();
            System.out.println("ContentLength:" + contentLength);
            System.out.println("Prameter:");
            Enumeration<String> qparamns = req.getParameterNames();
            while(qparamns.hasMoreElements()) {
                String name = qparamns.nextElement();
                System.out.println("  " + name + ":" + req.getParameter(name));
            }

            System.out.println("Attribute:");
            Enumeration<String> qans = req.getAttributeNames();
            while(qans.hasMoreElements()) {
                String name = qans.nextElement();
                System.out.println("  " + name + ":" + req.getAttribute(name));
            }

            String sessionId = null;
            HttpSession session = req.getSession();
            if (session != null) {
                sessionId = session.getId();
            }
            System.out.println("SessionId:" + sessionId + "[ServletRequest.Session.Id]");
            System.out.println("SessionId:" + req.getRequestedSessionId() + "[RequestedSessionId]");
            System.out.println(req.getRequestURI() + "[RequestURI]");

            String encode = req.getCharacterEncoding();
            System.out.println("CharacterEncoding:" + encode);
            if (contentLength > 0) {
                BufferedReader br = req.getReader();
                if (br.markSupported()) {
                    br.mark(contentLength + 1);
                    System.out.println("mark Supported");
                } else {
                    System.out.println("mark/reset not Supported");
                }
                char[] buf = new char[contentLength];
                br.read(buf);
                String line = new String(buf);
                System.out.println(line);
                br.reset();
                System.out.println("reset Supported");
            }
            
            System.out.println("Cookie:");
            Cookie[] cookies = req.getCookies();
            if (cookies != null) {
                for(Cookie cookie : cookies) {
                    System.out.println(cookie.getName() +":" + cookie.getValue() + 
                        "[Domain:" + cookie.getDomain() + ",Path:" + cookie.getPath() + 
                        ",MaxAge:" + cookie.getMaxAge() + ",Version:" + cookie.getVersion() +
                        ",Comment:" + cookie.getComment() + "]");
                }
            }
            System.out.println("----------+---------+---------+---------*---------+---------+-HttpServletRequest");

            HttpServletResponse res = (HttpServletResponse) sresponse;
            DbjServletResponseWrapper resw = new DbjServletResponseWrapper(res);

            try {
                filterChain.doFilter(req, resw);
            } finally {
                System.out.println(MDC.get("sequence"));
                System.out.println("HttpServletResponse-+---------+---------*---------+---------+---------+---------");
                System.out.println(sresponse.isCommitted() + "[ServletResponse.isCommitted]");
                String characterEncoding = resw.getCharacterEncoding();
                System.out.println("CharacterEncoding:" + characterEncoding);
                int bufferSize = res.getBufferSize();
                System.out.println("BufferSize:" + bufferSize);
                System.out.println("ContentType:" + res.getContentType());
                System.out.println("Locale:" + res.getLocale());
    
                if (!req.getRequestURI().toLowerCase().endsWith(".js") && 
                    !req.getRequestURI().toLowerCase().endsWith(".ico")) {
                    byte[] dat = resw.getRes();
                    System.out.println("Hex:" + UBytes.toHexString(dat));
                    System.out.println("Text:" + new String(dat, characterEncoding));
                }
                System.out.println("----------+---------+---------+---------*---------+---------+HttpServletResponse");
            }
        }

        @Override
        public void destroy() {
            System.out.println(this.getClass().getName() + ".destroy");
            LOGGER.info(this.getClass().getName() + "销毁过滤器");
        }

    }
}
