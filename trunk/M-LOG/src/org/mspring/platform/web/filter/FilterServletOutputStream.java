package org.mspring.platform.web.filter;

import java.io.IOException;
import java.io.OutputStream;

import javax.servlet.ServletOutputStream;

public class FilterServletOutputStream extends ServletOutputStream {
    private OutputStream stream;

    public FilterServletOutputStream(OutputStream stream) {
        this.stream = stream;
    }

    public void write(int b) throws IOException {
        this.stream.write(b);
    }

    public void write(byte[] b) throws IOException {
        this.stream.write(b);
    }

    public void write(byte[] b, int off, int len) throws IOException {
        this.stream.write(b, off, len);
    }
}