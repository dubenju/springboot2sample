package com.example.demo;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import javax.servlet.ReadListener;
import javax.servlet.ServletInputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;

public class DbjHttpServletRequestWrapper extends HttpServletRequestWrapper
{
    private final byte[] body;
    public DbjHttpServletRequestWrapper(HttpServletRequest request) throws IOException
    {
        super(request);
        body = toByteArray(request.getInputStream());
    }

//    private String getBodyAsString()
//    {
//        StringBuffer buff = new StringBuffer();
//        buff.append(" BODY_DATA START [ ");
//        char[] charArr = new char[getContentLength()];
//        try
//        {
//            BufferedReader reader = new BufferedReader(getReader());
//            reader.read(charArr, 0, charArr.length);
////            reader.close();
//        }
//        catch (IOException e)
//        {
//            e.printStackTrace();
//        }
//        buff.append(charArr);
//        buff.append(" ] BODY_DATA END ");
//        return buff.toString();
//    }
//
//    public String toString()
//    {
//        return getBodyAsString();
//    }
    private byte[] toByteArray(InputStream in) throws IOException {

        ByteArrayOutputStream out = new ByteArrayOutputStream();
        byte[] buffer = new byte[1024 * 4];
        int n = 0;
        while ((n = in.read(buffer)) != -1) {
            out.write(buffer, 0, n);
        }
        return out.toByteArray();
    }

    @Override
    public BufferedReader getReader() throws IOException {
        return new BufferedReader(new InputStreamReader(getInputStream()));
    }


    @Override
    public ServletInputStream getInputStream() throws IOException {
        final ByteArrayInputStream bais = new ByteArrayInputStream(body);
        return 
                new ServletInputStream() {
            @Override
            public int read() throws IOException {
                return bais.read();
            }

            @Override
            public boolean isFinished() {
                return false;
            }

            @Override
            public boolean isReady() {
                return true;
            }

            @Override
            public void setReadListener(ReadListener listener) {
            }
        };
    }
}
