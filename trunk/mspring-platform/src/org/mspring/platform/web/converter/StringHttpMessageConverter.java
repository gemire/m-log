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

    /**
     *
     */
    public StringHttpMessageConverter(Charset defaultCharset) {
        // TODO Auto-generated constructor stub
        List<MediaType> mediaTypeList = new ArrayList<MediaType>();
        mediaTypeList.add(new MediaType("text", "plain", defaultCharset));
        mediaTypeList.add(MediaType.ALL);
        super.setSupportedMediaTypes(mediaTypeList);
    }

    protected void writeInternal(String s, HttpOutputMessage outputMessage) throws IOException {
        Charset charset = Charset.forName("UTF-8");
        FileCopyUtils.copy(s, new OutputStreamWriter(outputMessage.getBody(), charset));
    }
}
