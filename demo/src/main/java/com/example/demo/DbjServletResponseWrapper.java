package com.example.demo;

import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.util.Collection;

import javax.servlet.ServletOutputStream;
import javax.servlet.ServletResponseWrapper;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;

public class DbjServletResponseWrapper extends ServletResponseWrapper implements HttpServletResponse {
    private ServletOutputStream outputStream;
    private PrintWriter writer;
    private DoubleOutputStream dOutputStream;
    private HttpServletResponse response;

    public DbjServletResponseWrapper(HttpServletResponse response) {
        super(response);
        this.response = response;
    }

    public ServletOutputStream getOutputStream() throws IOException {
        if (this.writer == null) {
            
        }
        if (this.outputStream == null) {
            this.outputStream = this.getResponse().getOutputStream();
            this.dOutputStream = new DoubleOutputStream(this.outputStream);
        }
        return this.dOutputStream;
    }
    public void flushBuffer() throws IOException {
        if (this.writer != null) {
            this.writer.flush();
        } else if (this.outputStream != null) {
            this.dOutputStream.flush();
        }
    }
    public PrintWriter gerWriter() throws IOException {
        if (this.outputStream != null) {
            
        }
        if (this.writer == null) {
            this.dOutputStream = new DoubleOutputStream(this.getResponse().getOutputStream());
            this.writer = new PrintWriter(new OutputStreamWriter(this.dOutputStream, this.getResponse().getCharacterEncoding()));
        }
        return this.writer;
    }
    public byte[] getRes() {
        byte[] result = new byte[0];
        if (this.dOutputStream != null) {
            result = this.dOutputStream.getBytes();
            
        }
        return result;
    }

    @Override
    public void addCookie(Cookie cookie) {
        this.response.addCookie(cookie);
    }

    @Override
    public boolean containsHeader(String name) {
        return this.response.containsHeader(name);
    }

    @Override
    public String encodeURL(String url) {
        return this.response.encodeURL(url);
    }

    @Override
    public String encodeRedirectURL(String url) {
        return this.response.encodeRedirectURL(url);
    }

    @SuppressWarnings("deprecation")
    @Override
    public String encodeUrl(String url) {
        return this.response.encodeUrl(url);
    }

    @SuppressWarnings("deprecation")
    @Override
    public String encodeRedirectUrl(String url) {
        return this.response.encodeRedirectUrl(url);
    }

    @Override
    public void sendError(int sc, String msg) throws IOException {
        this.response.sendError(sc, msg);
    }

    @Override
    public void sendError(int sc) throws IOException {
        this.response.sendError(sc);
    }

    @Override
    public void sendRedirect(String location) throws IOException {
        this.response.sendRedirect(location);
    }

    @Override
    public void setDateHeader(String name, long date) {
        this.response.setDateHeader(name, date);
    }

    @Override
    public void addDateHeader(String name, long date) {
        this.response.addDateHeader(name, date);
    }

    @Override
    public void setHeader(String name, String value) {
        this.response.setHeader(name, value);
    }

    @Override
    public void addHeader(String name, String value) {
        this.response.addHeader(name, value);
    }

    @Override
    public void setIntHeader(String name, int value) {
        this.response.setIntHeader(name, value);
    }

    @Override
    public void addIntHeader(String name, int value) {
        this.response.addIntHeader(name, value);
    }

    @Override
    public void setStatus(int sc) {
        this.response.setStatus(sc);
    }

    @SuppressWarnings("deprecation")
    @Override
    public void setStatus(int sc, String sm) {
        this.response.setStatus(sc, sm);
    }

    @Override
    public int getStatus() {
        return this.response.getStatus();
    }

    @Override
    public String getHeader(String name) {
        return this.response.getHeader(name);
    }

    @Override
    public Collection<String> getHeaders(String name) {
        return this.response.getHeaders(name);
    }

    @Override
    public Collection<String> getHeaderNames() {
        return this.response.getHeaderNames();
    }
}
