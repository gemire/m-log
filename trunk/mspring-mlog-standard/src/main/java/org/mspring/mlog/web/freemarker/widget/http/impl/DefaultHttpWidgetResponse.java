/**
 * 
 */
package org.mspring.mlog.web.freemarker.widget.http.impl;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.io.Serializable;
import java.io.UnsupportedEncodingException;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpServletResponseWrapper;

import org.mspring.mlog.Application;
import org.mspring.mlog.web.freemarker.widget.http.HttpWidgetResponse;

/**
 * @author Gao Youbo
 * @since 2012-12-5
 * @Description
 * @TODO
 */
public class DefaultHttpWidgetResponse extends HttpServletResponseWrapper implements HttpWidgetResponse, Serializable {

    /**
     * 
     */
    private static final long serialVersionUID = 9031829936948335833L;
    private PrintWriter responsePrintWriter;
    private PrintWriter contentPrintWriter;
    private ByteArrayOutputStream content = new ByteArrayOutputStream();

    /**
     * @param response
     */
    public DefaultHttpWidgetResponse(HttpServletResponse response) {
        super(response);
        // TODO Auto-generated constructor stub
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.mspring.mlog.web.freemarker.widget.http.HttpWidgetResponse#
     * getResponseAsString()
     */
    @Override
    public String getResponseAsString() {
        // TODO Auto-generated method stub
        try {
            return content.toString(Application.getDefaultEncoding());
        }
        catch (UnsupportedEncodingException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            return content.toString();
        }
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * org.mspring.mlog.web.freemarker.widget.http.HttpWidgetResponse#append
     * (java.lang.String)
     */
    @Override
    public void append(String html) throws UnsupportedEncodingException {
        // TODO Auto-generated method stub
        if (responsePrintWriter == null) {
            responsePrintWriter = new ResponsePrintWriter(Application.getDefaultEncoding());
        }
        if (contentPrintWriter == null) {
            contentPrintWriter = new PrintWriter(content, true);
        }
        responsePrintWriter.append(html);
        contentPrintWriter.append(html);
    }

    /*
     * (non-Javadoc)
     * 
     * @see javax.servlet.ServletResponseWrapper#getOutputStream()
     */
    @Override
    public ServletOutputStream getOutputStream() throws IOException {
        // TODO Auto-generated method stub
        return new ResponseServletOutputStream();
    }

    /*
     * (non-Javadoc)
     * 
     * @see javax.servlet.ServletResponseWrapper#getWriter()
     */
    @Override
    public PrintWriter getWriter() throws IOException {
        // TODO Auto-generated method stub
        if (responsePrintWriter == null) {
            responsePrintWriter = new ResponsePrintWriter(Application.getDefaultEncoding());
        }
        if (contentPrintWriter == null) {
            contentPrintWriter = new PrintWriter(new OutputStreamWriter(content, Application.getDefaultEncoding()), true);
        }
        return contentPrintWriter;
    }

    private class ResponseServletOutputStream extends ServletOutputStream {
        @Override
        public void write(int b) throws IOException {
            content.write(b);
        }

        @Override
        public void write(byte[] b, int off, int len) throws IOException {
            content.write(b, off, len);
        }
    }

    private class ResponsePrintWriter extends PrintWriter {
        private ResponsePrintWriter(String characterEncoding) throws UnsupportedEncodingException {
            super(new OutputStreamWriter(content, characterEncoding));
        }

        @Override
        public void write(char buf[], int off, int len) {
            super.write(buf, off, len);
            super.flush();
        }

        @Override
        public void write(String s, int off, int len) {
            super.write(s, off, len);
            super.flush();
        }

        @Override
        public void write(int c) {
            super.write(c);
            super.flush();
        }
    }
}
