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

    private PrintWriter writer;
    private ByteArrayOutputStream content = new ByteArrayOutputStream();

    public DefaultHttpWidgetResponse(HttpServletResponse response) {
        super(response);
        //
    }

    @Override
    public ServletOutputStream getOutputStream() throws IOException {
        return new ResponseServletOutputStream();
    }

    @Override
    public PrintWriter getWriter() throws IOException {
        if (writer == null) {
            writer = new ResponsePrintWriter(getCharacterEncoding());
        }
        return new PrintWriter(content, true);
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
        return content.toString();
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.mspring.mlog.web.freemarker.widget.http.HttpWidgetResponse#
     * setResponseContent(java.lang.String)
     */
    @Override
    public void setResponseContent(String content) throws IOException {
        // TODO Auto-generated method stub
        this.content.write(content.getBytes());
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
