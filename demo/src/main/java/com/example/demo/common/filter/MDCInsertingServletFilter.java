package com.example.demo.common.filter;

import java.io.IOException;
import java.util.UUID;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

import org.jboss.logging.MDC;
import org.springframework.stereotype.Component;

@Component
public class MDCInsertingServletFilter implements Filter {

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {
        MDC.put("sequence", UUID.randomUUID().toString().toUpperCase());
        try {
            System.out.println(MDC.get("sequence"));
            chain.doFilter(request, response);
        } finally {
            MDC.remove("sequence");
        }
    }
}
