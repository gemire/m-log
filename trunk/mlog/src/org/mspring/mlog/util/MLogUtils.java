/**
 * @since 2011-7-5
 * www.mspring.org
 * @author Gao Youbo
 */
package org.mspring.mlog.util;

import java.io.File;
import java.util.Calendar;

import org.mspring.platform.utils.StringUtils;


/**
 * 
 * @author Gao Youbo
 */
public class MLogUtils {
    
    /**
     * 生成图片上传的相对路径(upload_folder/year/month/day/filename.extendName)
     * @param upload_folder 图片保存的文件夹
     * @param extendName 图片的后缀名
     * @return
     */
	public static String getAbstractImageUrl(String upload_folder, String extendName){
	    Calendar calendar = Calendar.getInstance();
	    int year = calendar.get(Calendar.YEAR);
	    int month = calendar.get(Calendar.MONTH) + 1;
	    int day = calendar.get(Calendar.DAY_OF_MONTH);
	    
	    if (!extendName.startsWith(".")) {
            extendName = "." + extendName;
        }
	    
	    String fileName = upload_folder + File.separator + year + File.separator + month + File.separator + day + File.separator + StringUtils.getFileName() + extendName;
	    return fileName;
	}
	
	/**
	 * 生成缩略图的相对路径(原始图片路径/previewFolder/原始图片名称_preview.后缀名)
	 * @param imageAbstractUrl 原始图片的相对路径
	 * @param previewFolder 缩略图保存的文件夹
	 * @return
	 */
	public static String getAbstractPreviewImageUrl(String imageAbstractUrl, String previewFolder){
	    String original_path = imageAbstractUrl.substring(0, imageAbstractUrl.lastIndexOf(File.separator));
	    String file_name = imageAbstractUrl.substring(imageAbstractUrl.lastIndexOf(File.separator) + 1, imageAbstractUrl.lastIndexOf("."));
	    String extendName = StringUtils.getFileExtend(imageAbstractUrl);
	    return original_path + File.separator + previewFolder + File.separator + file_name + "_preview." + extendName;
	}
	
	public static void main(String[] args) {
        System.out.println(MLogUtils.getAbstractPreviewImageUrl("uploads\\2012\\1\\2\\xxxx.jpg", "preview"));
    }
}
