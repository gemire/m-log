package org.mspring.mlog.api.dbank.nsp;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import org.mspring.mlog.api.dbank.nsp.support.common.AssocArrayUtil;
import org.mspring.mlog.api.dbank.nsp.support.common.NSPException;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.lang.builder.ToStringBuilder;

/**
 * dbank-sdk nsp.VFS<br/>
 * Desc:针对nsp.vfs.*服务的封装<br/>
 * 
 * @author <a href="mailto: 61606619@qq.com">Bruce Shen</a> 2011 下午5:02:00
 */
public class VFS {

    private NSPClient userClient;

    /**
     * @param client : 
     *            NSP的客户端对象
     * @see NSPClient
     */
    public VFS(NSPClient client) {
        this.userClient = client;
    }

    /**
     * 列出目录下的文件
     * 
     * @param path
     *            文件夹路径
     * @param fields
     *            需要查询的文件属性数组，默认返回 ["name", "type", "url"]
     * @param options
     * 			  附加选项：<br/>
     * 	&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;version 文件夹的版本，用户在请求时可以带上文件夹版本请求，这样服务器可以根据用户提供的文件夹版本决定该版本不是最新的，从而决定是否向客户端发送数据。<br/>
     *  &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;type 类型(默认3 ：1－只列举文件；2－只列举文件夹；3－列举文件和文件夹)<br/>
     * 	&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;recursive 是否递归(默认0 ：0－递归；1－不递归)
     * @return nsp.VFS.LsResult
     * @throws lsdir(String path, String[] fields,int type, int recursive, Map<String, Object> options)
     */
    public LsResult lsdir(String path, String[] fields, Map<String, Object> options) throws NSPException  {
        Object obj = userClient.callService("nsp.vfs.lsdir", new Object[] { path, fields, options });
        return map2LsResult((Map<String, Object>) AssocArrayUtil.toObject(obj, false));//NSPWrapper.toBean(obj, LsResult.class);
    }
    
    /**
     * 复制文件和目录
     * 
     * @param <b>files</b> 文件路径数组。该接口支持拷贝文件以及文件夹，文件夹会递归拷贝内容。
     * @param <b>path</b> 拷贝目标文件夹（文件）路径。<br/>
     * @param attribute 默认为空，复制文件时的可选参数配置，这个以后可不断扩展。
     * @return nsp.VFS.Result
     * @throws NSPException
     */
    public Result copyfile(String[] files, String path, Map<String, Object> options) throws NSPException {
        Object obj = userClient.callService("nsp.vfs.copyfile", new Object[] { files, path, options });
        return map2Result((Map<String, Object>) AssocArrayUtil.toObject(obj, false));
        //return NSPWrapper.toBean(obj, Result.class);
    }

    /**
     * 移动文件或目录
     * 
     * @param <b>files</b> 文件路径数组。该接口支持移动文件以及文件夹，文件夹会递归移动文件夹内容。
     * @param <b>path</b> 移动目标文件夹（文件）路径。
     * @param attribute 默认为空。移动文件(夹)时的可选参数配置，这个以后可不断扩展。
     * @return nsp.VFS.Result
     * @throws NSPException
     */
    public Result movefile(String[] files, String path, Map<String, Object> attribute) throws NSPException {
        Object obj = userClient.callService("nsp.vfs.movefile", new Object[] { files, path, attribute });
        return map2Result((Map<String, Object>) AssocArrayUtil.toObject(obj, false));
        //return NSPWrapper.toBean(obj, Result.class);
    }

    /**
     * 删除文件或目录
     * 
     * @param <b>files</b> 文件路径数组
     * @param reserve 删除文件或文件夹时是否保留，默认为false，直接删除文件或文件夹。如果为true的话，将文件或文件夹移动到系统回收站进行暂时保留。
     * @param attribute 对文件删除操作附加一些属性控制。
     * @return nsp.VFS.Result
     * @throws NSPException
     */
    public Result rmfile(String[] files, Boolean reserve, Map<String, Object> attribute) throws NSPException {
        Object obj = userClient.callService("nsp.vfs.rmfile", new Object[] { files, reserve, attribute });
        return map2Result((Map<String, Object>) AssocArrayUtil.toObject(obj, false));
        //return NSPWrapper.toBean(obj, Result.class);
    }

    /**
     * 获取文件属性
     * 
     * @param <b>files</b> 文件路径数组。
     * @param fields 要查询的文件属性数组。“type,name”，两个属性，系统会自动返回，可以不用加入到列表中。
     * @return nsp.VFS.Result
     * @throws NSPException
     */
    public Result getattr(String[] files, String[] fields) throws NSPException {
        Object obj = userClient.callService("nsp.vfs.getattr", new Object[] { files, fields });
        //return NSPWrapper.toBean(obj, Result.class);
        return map2Result((Map<String, Object>) AssocArrayUtil.toObject(obj, false));
    }

    /**
     * 设置文件属性（主要是针对Dbank属性，文件的基本属性无法更改）
     * 
     * @param <b>files</b> 待设置的文件。
     * @return nsp.VFS.Result
     * @throws NSPException
     */
    public Result setattr(Map<String, Map<String, String>> files) throws NSPException {
        Object obj = userClient.callService("nsp.vfs.setattr", new Object[] { files });
        return map2Result((Map<String, Object>) AssocArrayUtil.toObject(obj, false));//return NSPWrapper.toBean(obj, Result.class);
    }

    /**
     * 获取上传权限
     * 
     * @param cname 机器名
     * @return nsp.VFS.UploadAuth
     * @throws NSPException
     * @see nsp.vfs.upauth
     */
    public UploadAuth upauth(String cname) throws NSPException {
        Object obj = userClient.callService("nsp.vfs.upauth", new Object[] { cname });
        //return NSPWrapper.toBean(obj, UploadAuth.class);
        return map2UploadAuth((Map<String, Object>) AssocArrayUtil.toObject(obj, false));
    }

    /**
     * 创建文件或目录
     * @param <b>files</b> 文件路径数组。
     * @param </b>path</b> 目标路径
     * @return nsp.VFS.Result
     * @throws NSPException
     * @see nsp.vfs.mkfile
     */
    public Result mkfile(File[] files, String path) throws NSPException {
        Object obj = userClient.callService("nsp.vfs.mkfile", new Object[] { files, path });
        return map2Result((Map<String, Object>) AssocArrayUtil.toObject(obj, false));//return NSPWrapper.toBean(obj, Result.class);
    }

    /**
     * dbank-sdk nsp.LsResult<br/>
     * Desc:调用nsp.vfs.lsdir服务返回的结果对象<br/>
     * 
     * @author <a href="mailto: 61606619@qq.com">Bruce Shen</a> 2011 上午9:45:17
     */
    public static class LsResult {

        /** 异常错误码 */
        private Integer errCode;
        /** 异常信息 */
        private String errMsg;
        /** 目录下的文件列表 */
        private File[] childList;
        /** 文件夹最新版本(可选) */
        private String version;

        public Integer getErrCode() {
            return errCode;
        }

        public void setErrCode(Integer errCode) {
            this.errCode = errCode;
        }

        public String getErrMsg() {
            return errMsg;
        }

        public void setErrMsg(String errMsg) {
            this.errMsg = errMsg;
        }

        public File[] getChildList() {
            return childList;
        }

        public void setChildList(File[] childList) {
            this.childList = childList;
        }

        public String getVersion() {
            return version;
        }

        public void setVersion(String version) {
            this.version = version;
        }

        public String toString() {
            return ToStringBuilder.reflectionToString(this);
        }

    }
    
    private LsResult map2LsResult(Map<String, Object> ret) {
    	if(ret == null || ret.size() == 0) {
    		return null;
    	}
    	LsResult result = new LsResult();
    	if(ret.containsKey("error")) {
    		result.setErrMsg(ret.get("error").toString());
    	} else {
    		List<VFS.File> files = new LinkedList<VFS.File>();
    		retriveVfsFile((List<Map>) ret.get("childList"), files);
    		result.setChildList(files.toArray(new VFS.File[]{}));
    	}
    	return result;
    }
    
    private void retriveVfsFile(List<Map> fileInfos, List<VFS.File> files) {
    	if(fileInfos == null) {
    		return;
    	}
    	for(Map m : fileInfos) {
    		VFS.File e = new File();
    		try {
				BeanUtils.copyProperties(e, m);
			} catch (Exception e1) {
				e1.printStackTrace();
			}
    		files.add(e);
    	}
    }
    
    private void retriveError(List<Map> fileInfos, List<Error> errors) {
    	if(fileInfos == null) {
    		return;
    	}
    	for(Map m : fileInfos) {
    		Error e = new Error();
    		try {
				BeanUtils.copyProperties(e, m);
			} catch (Exception e1) {
				e1.printStackTrace();
			}
    		errors.add(e);
    	}
    }

    /**
     * dbank-sdk nsp.File<br/>
     * Desc:NSP平台的文件对象<br/>
     * 
     * @author <a href="mailto: 61606619@qq.com">Bruce Shen</a> 2011 上午9:46:33
     */
    public static class File extends HashMap<String, Object> {

        private static final long serialVersionUID = -4770722209779375882L;

        private List<File> childList;

        /**
         * 获取目录到子文件
         * @return
         */
        public List<File> getChildList() {
            return childList;
        }

        public void setChildList(List<File> childList) {
            this.childList = childList;
        }

        public Map<String, File> getRevisions() {
            Object obj = get("revisions");
            return (Map<String, File>) obj;
        }

        public void setRevisions(Map<String, File> revisions) {
            put("revisions", revisions);
        }

        // ------------公用---------------------
        /** 获取文件类型 */
        public String getType() {
            return getStringProperty("type");
        }

        /** 设置文件类型 */
        public void setType(String type) {
            put("type", type);
        }

        /** 是否是普通的文件 */
        public boolean isFile() {
            if (null != getType() && "file".equalsIgnoreCase(getType())) {
                return true;
            }
            return false;
        }

        /** 是否是文件夹 */
        public boolean isDirectory() {
            return !isFile();
        }

        public void setName(String name) {
            put("name", name);
        }

        /** 获取文件的名称 */
        public String getName() {
            return (String)get("name");
        }

        public void setCreateTime(String createTime) {
            put("createTime", createTime);
        }

        /** 获取创建时间 */
        public String getCreateTime() {
            return (String)get("createTime");
        }

        public void setModifyTime(String modifyTime) {
            put("modifyTime", modifyTime);
        }

        /** 获取修改时间 */
        public String getModifyTime() {
            return getStringProperty("modifyTime");
        }

        public void setAccessTime(String accessTime) {
            put("accessTime", accessTime);
        }

        /** 获取最后访问时间 */
        public String getAccessTime() {
            return getStringProperty("accessTime");
        }

        // /////////// 文件特有 ////////////
        public void setSize(Long size) {
            put("size", size);
        }

        /** 获取文件大小 */
        public Long getSize() {
            if (containsKey("size"))
                return (Long) get("size");
            else
                return null;
        }

        public void setUrl(String url) {
            put("url", url);
        }

        /** 获取文件url地址 */
        public String getUrl() {
            return getStringProperty("url");
        }

        public void setMd5(String md5) {
            put("md5", md5);
        }

        /** 获取文件的MD5值 */
        public String getMd5() {
            return getStringProperty("md5");
        }

        // /////////// 文件夹特有 ////////////
        /** 获取文件夹大小 */
        public String getSpace() {
            return getStringProperty("space");
        }

        /** 获取文件夹里面的文件数目 */
        public String getFileCount() {
            return getStringProperty("fileCount");
        }

        /** 获取文件夹里面目录的数目 */
        public String getDirCount() {
            return getStringProperty("dirCount");
        }

        // /////////// Dbank 扩展 ////////////

        // ///////// 其他属性 ////////////
        /**
         * 获取其他的属性
         * 
         * @see 文件的属性
         */
        public String getProperties(String key) {
            if (containsKey(key)) {
                return this.getStringProperty(key);
            }
            return null;
        }

        public void setProperties(String key, Object value) {
            put(key, value);
        }

        private String getStringProperty(String key) {
            String ret = null;
            Object obj = get(key);
            if (obj != null) {
                if (obj instanceof String)
                    ret = (String) obj;
                else if (obj instanceof byte[])
                    ret = new String((byte[]) obj);
                else
                    ret = String.valueOf(obj);

                put(key, ret);
            }
            return ret;
        }

        public String toString() {
            return ToStringBuilder.reflectionToString(this);
        }
    }

    /**
     * dbank-sdk nsp.Result<br/>
     * Desc:文件操作的结果（nsp.vfs.copyfile、nsp.vfs.rmfile、nsp.vfs.movefile等）<br/>
     * 
     * @author <a href="mailto: 61606619@qq.com">Bruce Shen</a> 2011 上午9:51:30
     */
    public static class Result {
        private File[] successList;
        private Error[] failList;

        /**
         * 返回操作成功列表
         */
        public File[] getSuccessList() {
            return successList;
        }

        public void setSuccessList(File[] successList) {
            this.successList = successList;
        }

        /**
         * 返回操作成功列表
         */
        public Error[] getFailList() {
            return failList;
        }

        public void setFailList(Error[] failList) {
            this.failList = failList;
        }

        public String toString() {
            return ToStringBuilder.reflectionToString(this);
        }
    }
    
    private Result map2Result(Map<String, Object> ret) {
    	Result result = new Result();
    	
    	if(ret.containsKey("successList")) {
    		List<VFS.File> files = new LinkedList<VFS.File>();
    		retriveVfsFile((List<Map>) ret.get("successList"), files);
    		result.setSuccessList(files.toArray(new VFS.File[]{}));
    	}
    	
    	if(ret.containsKey("failList")) {
    		List<Error> errors = new LinkedList<Error>();
    		retriveError((List<Map>) ret.get("failList"), errors);
    		result.setFailList(errors.toArray(new Error[]{}));
    	}
    	return result;
    }

    /**
     * dbank-sdk nsp.Error<br/>
     * Desc:异常信息<br/>
     * 
     * @author <a href="mailto: 61606619@qq.com">Bruce Shen</a> 2011 上午9:53:29
     */
    public static class Error {
        private String name;
        private Integer errCode;
        private String errMsg;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public Integer getErrCode() {
            return errCode;
        }

        public void setErrCode(Integer errCode) {
            this.errCode = errCode;
        }

        public String getErrMsg() {
            return errMsg;
        }

        public void setErrMsg(String errMsg) {
            this.errMsg = errMsg;
        }

        public String toString() {
            return ToStringBuilder.reflectionToString(this);
        }

    }
    
    private Error map2Error(Map<String, Object> ret) {
    	Error error = new Error();
    	try {
			BeanUtils.copyProperties(error, ret);
		} catch (Exception e) {
			e.printStackTrace();
		}
    	return error;
    }

    /**
     * dbank-sdk nsp.UploadAuth<br/>
     * Desc:获取上传权限结果<br/>
     * 
     * @author <a href="mailto: 61606619@qq.com">Bruce Shen</a> 2011 上午9:53:39
     */
    public static class UploadAuth {
        /** 上传时需要的secret */
        private String secret;
        /** 上传时需要的临时app */
        private String nsp_tapp;
        /** 上传时需要的tstr */
        private String nsp_tstr;
        /** 上传时版本 */
        private String nsp_tver;
        /** 上传时服务器地址 */
        private String nsp_host;

        /**
         * 上传时需要的临时secret
         */
        public String getSecret() {
            return secret;
        }

        public void setSecret(String secret) {
            this.secret = secret;
        }

        /**
         * 上传时需要的临时app
         */
        public String getNsp_tapp() {
            return nsp_tapp;
        }

        public void setNsp_tapp(String nsp_tapp) {
            this.nsp_tapp = nsp_tapp;
        }

        /**
         * 上传时需要的tstr 
         */
        public String getNsp_tstr() {
            return nsp_tstr;
        }

        public void setNsp_tstr(String nsp_tstr) {
            this.nsp_tstr = nsp_tstr;
        }

        public String getNsp_tver() {
            return nsp_tver;
        }

        public void setNsp_tver(String nsp_tver) {
            this.nsp_tver = nsp_tver;
        }
        
        /**
         * 上传时服务器地址
         */
        public String getNsp_host() {
            return nsp_host;
        }

        public void setNsp_host(String nsp_host) {
            this.nsp_host = nsp_host;
        }

        public String toString() {
            return ToStringBuilder.reflectionToString(this);
        }
    }
    
    private UploadAuth map2UploadAuth(Map<String, Object> ret) {
    	UploadAuth uploadAuth = new UploadAuth();
    	try {
			BeanUtils.copyProperties(uploadAuth, ret);
		} catch (Exception e) {
			e.printStackTrace();
		}
    	return uploadAuth;
    }
    
}
