package org.mspring.platform.web.wrapper;

import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.logging.Logger;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpServletResponseWrapper;

import org.mspring.platform.web.filter.FilterServletOutputStream;

public class GenericResponseWrapper extends HttpServletResponseWrapper implements Serializable {
    private static final long serialVersionUID = -5976708169031065498L;
    private static final Logger LOG = Logger.getLogger(GenericResponseWrapper.class.getName());
    private int statusCode = 200;
    private int contentLength;
    private String contentType;
    private final List headers = new ArrayList();
    private final List cookies = new ArrayList();
    private ServletOutputStream outstr;
    private PrintWriter writer;

    public GenericResponseWrapper(HttpServletResponse response, OutputStream outstr) {
        super(response);
        this.outstr = new FilterServletOutputStream(outstr);
    }

    public ServletOutputStream getOutputStream() {
        return this.outstr;
    }

    public void setStatus(int code) {
        this.statusCode = code;
        super.setStatus(code);
    }

    public void sendError(int i, String string) throws IOException {
        this.statusCode = i;
        super.sendError(i, string);
    }

    public void sendError(int i) throws IOException {
        this.statusCode = i;
        super.sendError(i);
    }

    public void sendRedirect(String string) throws IOException {
        this.statusCode = 302;
        super.sendRedirect(string);
    }

    public void setStatus(int code, String msg) {
        this.statusCode = code;
        LOG.warning("Discarding message because this method is deprecated.");
        super.setStatus(code);
    }

    public int getStatus() {
        return this.statusCode;
    }

    public void setContentLength(int length) {
        this.contentLength = length;
        super.setContentLength(length);
    }

    public int getContentLength() {
        return this.contentLength;
    }

    public void setContentType(String type) {
        this.contentType = type;
        super.setContentType(type);
    }

    public String getContentType() {
        return this.contentType;
    }

    public PrintWriter getWriter() throws IOException {
        if (this.writer == null) {
            this.writer = new PrintWriter(new OutputStreamWriter(this.outstr, getCharacterEncoding()), true);
        }
        return this.writer;
    }

    public void addHeader(String name, String value) {
        String[] header = { name, value };
        this.headers.add(header);
        super.addHeader(name, value);
    }

    public void setHeader(String name, String value) {
        addHeader(name, value);
    }

    public Collection getHeaders() {
        return this.headers;
    }

    public void addCookie(Cookie cookie) {
        this.cookies.add(cookie);
        super.addCookie(cookie);
    }

    public Collection getCookies() {
        return this.cookies;
    }

    public void flushBuffer() throws IOException {
        flush();
        super.flushBuffer();
    }

    public void reset() {
        super.reset();
        this.cookies.clear();
        this.headers.clear();
        this.statusCode = 200;
        this.contentType = null;
        this.contentLength = 0;
    }

    public void resetBuffer() {
        super.resetBuffer();
    }

    public void flush() throws IOException {
        if (this.writer != null) {
            this.writer.flush();
        }
        this.outstr.flush();
    }
}