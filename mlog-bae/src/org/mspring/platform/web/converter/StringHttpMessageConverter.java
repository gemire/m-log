/**
 * 
 */
package org.mspring.platform.web.converter;

import java.io.IOException;
import java.io.OutputStreamWriter;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;

import org.springframework.http.HttpOutputMessage;
import org.springframework.http.MediaType;
import org.springframework.util.FileCopyUtils;

/**
 * @author Gao Youbo
 * @since 2012-8-1
 * @Description
 * @TODO 解决spring默认提供的StringHttpMessageConverter在@ResponseBody中文时乱码
 */
public class StringHttpMessageConverter extends org.springframework.http.converter.StringHttpMessageConverter {
    
    private Charset charset;

    /**
     *
     */
    public StringHttpMessageConverter(String charsetName) {
        // TODO Auto-generated constructor stub
        this.charset = Charset.forName(charsetName);
        List<MediaType> mediaTypeList = new ArrayList<MediaType>();
        mediaTypeList.add(new MediaType("text", "plain", this.charset));
        mediaTypeList.add(MediaType.ALL);
        super.setSupportedMediaTypes(mediaTypeList);
    }

    protected void writeInternal(String s, HttpOutputMessage outputMessage) throws IOException {
        FileCopyUtils.copy(s, new OutputStreamWriter(outputMessage.getBody(), this.charset));
    }
}
