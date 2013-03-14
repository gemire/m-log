package org.mspring.mlog.api.dbank.nsp;

import java.util.HashMap;
import java.util.Map;

import org.mspring.mlog.api.dbank.nsp.VFS.File;
import org.mspring.mlog.api.dbank.nsp.VFS.Result;
import org.mspring.mlog.api.dbank.nsp.VFS.UploadAuth;
import org.mspring.mlog.api.dbank.nsp.support.common.NSPException;

import org.codehaus.jackson.map.ObjectMapper;

/**
 * dbank-sdk nsp.VFSExt<br/>
 * Desc:对VFS的扩展，针对上传文件<br/>
 * 
 * @author <a href="mailto: 61606619@qq.com">Bruce Shen</a> 2011 上午9:53:39
 */
public class VFSExt {
	
	/*下面到static属性为备忘一下*/
	private static final String DBANK_STATUS = "dbank_status";
	private static final String DBANK_IS_SHARED = "dbank_isShared";
	private static final String DBANK_SHARE_PRIVILEGE = "dbank_sharePrivilege";
	private static final String DBANK_SHARE_TYPE = "dbank_shareType";
	private static final String DBANK_DEST_IDS = "dbank_destIds";
	private static final String DBANK_BEFORE_PARENT_ID = "dbank_beforeParentId";
	private static final String DBANK_SRC_PATH = "dbank_srcpath";
	private static final String DBANK_SRC_NAME = "dbank_srcname";
	private static final String DBANK_POST_IP = "dbank_postip";
	private static final String DBANK_SYSTEM_TYPE = "dbank_systemType";
	private static final String DBANK_IS_PASS = "dbank_isPass";
	private static final String DBANK_UPER = "dbank_uper";
	
	/**默认的属性*/
	private static final String[] DEFAULT_PROPERTIES = new String[] { "name", "type", "url"};
	
	private VFS vfs;
	
	public VFSExt(VFS vfs) {
		this.vfs = vfs;
	}
	
	/**
	 * 普通上传文件
	 * 
	 * @param <b>path</b> 服务器上存放路径
	 * @param <b>file</b> 待上传的文件（java.io.File）
	 * @return nsp.VFS.Result
	 * @throws NSPException 
	 */
	public Result uploadFile(String path, java.io.File file, String cname) throws NSPException {
		// upauth
		UploadAuth auth = this.vfs.upauth(cname);
		// upload
		File[] upFiles = uploadeFile(auth, file);
		// mkfile
		return this.vfs.mkfile(upFiles, path);
	}
	
	private File[] uploadeFile(UploadAuth uploadAuth, java.io.File file) throws NSPException {
		NSPClient appClientForUpload = new NSPClient(uploadAuth.getNsp_tapp(), uploadAuth.getSecret());
		
		String data = appClientForUpload.uploadFile(uploadAuth.getNsp_host(), uploadAuth.getNsp_tstr(), file);
		Map<String, Object> uploadResult = null;
		try {
			uploadResult = new ObjectMapper().readValue(data, Map.class);
		} catch (Exception e) {
			throw new NSPException(2,"uploadAuth读取返回内容异常");
		}
		if (uploadResult.get("success") == null || !Boolean.parseBoolean((String) uploadResult.get("success"))) {
			throw new NSPException(2,"文件上传失败:"+uploadResult.toString());
		}
		// get File uploadReuslt
		Map<String, Object> fileUploadReulst = null;
		for (Object temp : uploadResult.values()) {
			if (temp instanceof Map) {
				fileUploadReulst = (Map<String, Object>) temp;
				break;
			}
		}
		
		Map<String, String> params = new HashMap<String, String>();
		String fileName = fileUploadReulst.get("name").toString();
		String fileUrl = fileUploadReulst.get("path").toString();

		File needMkFile = new File();
		needMkFile.setType("File");
		needMkFile.setName(fileName);
		needMkFile.setSize(Long.parseLong(fileUploadReulst.get("size").toString()));
		needMkFile.setUrl(fileUrl);
		needMkFile.setMd5(fileUploadReulst.get("nsp_fid").toString());
		needMkFile.setProperties("sig", fileUploadReulst.get("sig").toString());
		needMkFile.setProperties("ts", fileUploadReulst.get("ts").toString());
		return new File[]{needMkFile};
	}
}
