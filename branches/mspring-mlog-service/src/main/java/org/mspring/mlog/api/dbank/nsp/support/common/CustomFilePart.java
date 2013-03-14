package org.mspring.mlog.api.dbank.nsp.support.common;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.OutputStream;

import org.apache.commons.httpclient.methods.multipart.FilePart;
import org.apache.commons.httpclient.util.EncodingUtil;

/**
 * dbank-sdk
 * nsp.CustomFilePart<br/>
 * Desc:解决上传中文文件名乱码<br/>
 * @author <a href="mailto: 61606619@qq.com">Bruce Shen</a>
 * 2011 下午5:04:45
 */
public class CustomFilePart extends FilePart {
	public CustomFilePart(String filename, File file) throws FileNotFoundException {
		super(filename, file);
	}

	protected void sendDispositionHeader(OutputStream out) throws IOException {
		super.sendDispositionHeader(out);
		String filename = getSource().getFileName();
		if (filename != null) {
			out.write(EncodingUtil.getAsciiBytes(FILE_NAME));
			out.write(QUOTE_BYTES);
			out.write(EncodingUtil.getBytes(filename, "utf-8"));
			out.write(QUOTE_BYTES);
		}
	}
}