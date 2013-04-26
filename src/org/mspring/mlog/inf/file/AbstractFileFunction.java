/**
 * 
 */
package org.mspring.mlog.inf.file;

import java.io.File;
import java.io.InputStream;

/**
 * @author Gao Youbo
 * @since 2013-1-7
 * @Description 
 * @TODO 
 */
public abstract class AbstractFileFunction {
    public abstract String save(String fileName, File file);
    public abstract String save(String fileName, InputStream inputStream, long contentLength);
    public abstract String saveBase64(String fileName, String base64);
    public abstract void delete(String filePath);
}
