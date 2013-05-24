/**
 * 
 */
package org.mspring.platform.spring.converter;

import java.io.IOException;
import java.io.OutputStreamWriter;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;

import org.mspring.platform.utils.JSONUtils;
import org.mspring.platform.web.ResponseEntity;
import org.springframework.http.HttpInputMessage;
import org.springframework.http.HttpOutputMessage;
import org.springframework.http.MediaType;
import org.springframework.http.converter.AbstractHttpMessageConverter;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.http.converter.HttpMessageNotWritableException;
import org.springframework.util.FileCopyUtils;

/**
 * @author Gao Youbo
 * @since 2013-5-24
 * @description
 * @TODO
 */
public class ResponseEntityConverter extends AbstractHttpMessageConverter<Object> {

    private Charset charset;

    /**
    *
    */
    public ResponseEntityConverter(String charsetName) {
        // TODO Auto-generated constructor stub
        this.charset = Charset.forName(charsetName);
        List<MediaType> mediaTypeList = new ArrayList<MediaType>();
        mediaTypeList.add(new MediaType("text", "plain", this.charset));
        mediaTypeList.add(MediaType.ALL);
        super.setSupportedMediaTypes(mediaTypeList);
    }

    @Override
    protected boolean supports(Class<?> clazz) {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    protected Object readInternal(Class<? extends Object> clazz, HttpInputMessage inputMessage) throws IOException, HttpMessageNotReadableException {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    protected void writeInternal(Object t, HttpOutputMessage outputMessage) throws IOException, HttpMessageNotWritableException {
        // TODO Auto-generated method stub
        String JSON = JSONUtils.toJson(t);
        if (JSON != null) {
            // outputMessage.getBody().write(JSON.toString().getBytes());
            FileCopyUtils.copy(JSON, new OutputStreamWriter(outputMessage.getBody(), this.charset));
        }
    }

    @Override
    public boolean canWrite(Class<?> clazz, MediaType mediaType) {
        // TODO Auto-generated method stub
        if (clazz.equals(ResponseEntity.class)) {
            return true;
        }
        return super.canWrite(clazz, mediaType);
    }

}
