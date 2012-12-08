/**
 * 
 */
package org.mspring.mlog.support.formater;

import java.io.UnsupportedEncodingException;
import java.text.ParseException;
import java.util.Locale;

import org.springframework.format.Formatter;

/**
 * @author Gao Youbo
 * @since 2012-8-1
 * @Description
 * @TODO
 */
public class EncodingFotmatter implements Formatter<String> {

    private String encoding;

    /**
     * @param encoding
     *            the encoding to set
     */
    public void setEncoding(String encoding) {
        this.encoding = encoding;
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.springframework.format.Printer#print(java.lang.Object,
     * java.util.Locale)
     */
    @Override
    public String print(String str, Locale locale) {
        // TODO Auto-generated method stub
        byte[] b = str.getBytes();
        try {
            str = new String(b, encoding);
        }
        catch (UnsupportedEncodingException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return str;
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.springframework.format.Parser#parse(java.lang.String,
     * java.util.Locale)
     */
    @Override
    public String parse(String str, Locale locale) throws ParseException {
        // TODO Auto-generated method stub
        return str;
    }

}
