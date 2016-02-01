package com.wind.utils;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpServletResponseWrapper;

public class MyHttpResponseWrapper extends HttpServletResponseWrapper {
    private ByteArrayOutputStream buffer = null;
    private ServletOutputStream out = null;
    private PrintWriter writer = null;

    public MyHttpResponseWrapper(HttpServletResponse resp) throws IOException {
        super(resp);
        buffer = new ByteArrayOutputStream();//真正存储数据的流      
        out = new WapperedOutputStream(buffer);
        writer = new PrintWriter(new OutputStreamWriter(buffer, this.getCharacterEncoding()));
    }

    //重载父类获取outputstream的方法      
    @Override
    public ServletOutputStream getOutputStream() throws IOException {
        return out;
    }

    //重载父类获取writer的方法      
    @Override
    public PrintWriter getWriter() throws UnsupportedEncodingException {
        return writer;
    }

    //重载父类获取flushBuffer的方法      
    @Override
    public void flushBuffer() throws IOException {
        if (out != null) {
            out.flush();
        }
        if (writer != null) {
            writer.flush();
        }
    }

    @Override
    public void reset() {
        buffer.reset();
    }

    public String getContent() throws IOException {
        flushBuffer();//将out、writer中的数据强制输出到WapperedResponse的buffer里面，否则取不到数据      
        String s = new String(buffer.toByteArray());
        String s1 = new String(s.getBytes(), "utf-8");
        String s2 = new String(s.getBytes(), "gbk");
        System.out.println("  *"+s);
        System.out.println(" **"+s1);
        System.out.println("***"+s2);
        return s;
    }

    //内部类，对ServletOutputStream进行包装      
    private class WapperedOutputStream extends ServletOutputStream {
        private ByteArrayOutputStream bos = null;

        public WapperedOutputStream(ByteArrayOutputStream stream) throws IOException {
            bos = stream;
        }

        @Override
        public void write(int b) throws IOException {
            bos.write(b);
        }
    }
}