package com.example.demo;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStream;

import javax.servlet.ServletOutputStream;
import javax.servlet.WriteListener;

public class DoubleOutputStream extends ServletOutputStream {
    private OutputStream outputStream;
    private ByteArrayOutputStream copy;
    
    public DoubleOutputStream(OutputStream outputStream) {
        this.outputStream = outputStream;
        this.copy = new ByteArrayOutputStream(1024);
    }

    @Override
    public boolean isReady() {
        return true;
    }

    @Override
    public void setWriteListener(WriteListener listener) {
    }

    @Override
    public void write(int b) throws IOException {
        this.outputStream.write(b);
        this.copy.write(b);
    }
    
    public byte[] getBytes() {
        return this.copy.toByteArray();
    }
}
