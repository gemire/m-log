
function fileQueueError(file, errorCode, message) {
	try {
		// \u6587\u4ef6\u4e0a\u4f20\u9519\u8bef 文件上传错误
		var imageName = "<font color='red' style='font-size: 12px;'>\u6587\u4ef6\u4e0a\u4f20\u9519\u8bef</font>";
		var errorName = "";
		if (errorCode === SWFUpload.errorCode_QUEUE_LIMIT_EXCEEDED) {
			errorName = "You have attempted to queue too many files.";
		}
		if (errorName !== "") {
			alert(errorName);
			return;
		}
		switch (errorCode) {
		  case SWFUpload.QUEUE_ERROR.ZERO_BYTE_FILE:
		  	//\u6587\u4ef6\u5927\u5c0f\u4e3a0 文件大小为0
			imageName = "<font color='red' style='font-size: 12px;'>\u6587\u4ef6\u5927\u5c0f\u4e3a0</font>";
			break;
		  case SWFUpload.QUEUE_ERROR.FILE_EXCEEDS_SIZE_LIMIT:
		  	// \u6587\u4ef6\u5927\u5c0f\u8d85\u8fc7\u9650\u5236 文件大小超过限制
			imageName = "<font color='red' style='font-size: 12px;'>\u6587\u4ef6\u5927\u5c0f\u8d85\u8fc7\u9650\u5236</font>";
			break;
		  case SWFUpload.QUEUE_ERROR.ZERO_BYTE_FILE:
		  case SWFUpload.QUEUE_ERROR.INVALID_FILETYPE:
		  default:
			alert(message);
			break;
		}
		//\u65e0\u6cd5\u4e0a\u4f20 无法上传
		addReadyFileInfo(file.id, file.name, imageName, "\u65e0\u6cd5\u4e0a\u4f20");
	}
	catch (ex) {
		this.debug(ex);
	}
}
/**
 * 当文件选择对话框关闭消失时，如果选择的文件成功加入上传队列，
 * 那么针对每个成功加入的文件都会触发一次该事件（N个文件成功加入队列，就触发N次此事件）。
 * @param {} file
 * id : string,			    // SWFUpload控制的文件的id,通过指定该id可启动此文件的上传、退出上传等
 * index : number,			// 文件在选定文件队列（包括出错、退出、排队的文件）中的索引，getFile可使用此索引
 * name : string,			// 文件名，不包括文件的路径。
 * size : number,			// 文件字节数
 * type : string,			// 客户端操作系统设置的文件类型
 * creationdate : Date,		// 文件的创建时间
 * modificationdate : Date,	// 文件的最后修改时间
 * filestatus : number		// 文件的当前状态，对应的状态代码可查看SWFUpload.FILE_STATUS }
 */
function fileQueued(file) {
	//u6210u529fu52a0u8f7du5230u4e0au4f20u961fu5217  成功加载到上传队列
	addReadyFileInfo(file.id, file.name, "\u6210\u529f\u52a0\u8f7d\u5230\u4e0a\u4f20\u961f\u5217");
}

//选择文件后触发时间
function fileDialogComplete(numFilesSelected, numFilesQueued) {
	try {
		if (numFilesQueued > 0) {
			document.getElementById("btnCancel").disabled = "";
			document.getElementById("infoTable").style.display = "inline";
			//this.startUpload();
		}
	}
	catch (ex) {
		this.debug(ex);
	}
}
//上传过程中触发
function uploadProgress(file, bytesLoaded) {
	try {
		var percent = Math.ceil((bytesLoaded / file.size) * 100);
		var progress = new FileProgress(file, this.customSettings.upload_target);
		progress.setProgress(percent);
		if (percent === 100) {
			progress.setStatus("");//正在创建缩略图...
			progress.toggleCancel(false, this);
		} else {
			//提示信息:正在上传...
			progress.setStatus("\u6b63\u5728\u4e0a\u4f20...");
			progress.toggleCancel(true, this);
		}
	}
	catch (ex) {
		this.debug(ex);
	}
}

//上传成功后触发
function uploadSuccess(file, serverData) {
	try {
		var progress = new FileProgress(file, this.customSettings.upload_target);
		addFileInfo(file.id, "\u6587\u4ef6\u4e0a\u4f20\u5b8c\u6210");//提示信息:文件上传完成
	}
	catch (ex) {
		this.debug(ex);
	}
}

//当文件上传成功或失败后,在相应table文件列表后添加提示信息
function addFileInfo(fileId, message) {
	var row = document.getElementById(fileId);
	row.cells[2].innerHTML = "<font color='green'>" + message + "</font>";
}

//当选择文件后添加提示信息
function addReadyFileInfo(fileid, fileName, message, status) {
	//用表格显示
	var infoTable = document.getElementById("infoTable");
	var row = infoTable.insertRow();
	row.id = fileid;
	var col1 = row.insertCell();
	var col2 = row.insertCell();
	var col3 = row.insertCell();
	var col4 = row.insertCell();
	col4.align = "right";
	col1.innerHTML = message + " : ";
	col2.innerHTML = fileName;
	if (status != null && status != "") {
		col3.innerHTML = "<font color='red' style='font-size: 12px;'>" + status + "</font>";
	} else {
		col3.innerHTML = "";
	}
	col4.innerHTML = "<a href='javascript:deleteFile(\"" + fileid + "\")'>\u5220\u9664</a>";
	col1.style.width = "150";
	col2.style.width = "250";
	col3.style.width = "80";
	col4.style.width = "50";
}

//取消上传
function cancelUpload() {
	var infoTable = document.getElementById("infoTable");
	var rows = infoTable.rows;
	var ids = new Array();
	var row;
	if (rows == null) {
		return false;
	}
	for (var i = 0; i < rows.length; i++) {
		ids[i] = rows[i].id;
	}
	for (var i = 0; i < ids.length; i++) {
		deleteFile(ids[i]);
	}
}

//按照fileId取消上传文件
function deleteFile(fileId) {
	//用表格显示
	var infoTable = document.getElementById("infoTable");
	var row = document.getElementById(fileId);
	infoTable.deleteRow(row.rowIndex);
	swfu.cancelUpload(fileId, false);
	
	/*
	if (numFilesQueued > 0) {
		document.getElementById("btnCancel").disabled = "";
		document.getElementById("infoTable").style.display = "inline";
	}
	*/
	
}

//上传成功之后触发
function uploadComplete(file) {
	try {
		/*  I want the next upload to continue automatically so I'll call startUpload here */
		if (this.getStats().files_queued > 0) {
			this.startUpload();
		} else {
			var progress = new FileProgress(file, this.customSettings.upload_target);
			progress.setComplete();
			//提示信息: 所有文件上传完毕
			progress.setStatus("<font color='red' style='font-size: 12px;'>\u6240\u6709\u6587\u4ef6\u4e0a\u4f20\u5b8c\u6bd5!</b></font>");
			progress.toggleCancel(false);
		}
	}
	catch (ex) {
		this.debug(ex);
	}
}

//上传错误之后触发
function uploadError(file, errorCode, message) {
	//提示信息: 文件上传出错!
	var imageName = "<font color='red' style='font-size: 12px;'>\u6587\u4ef6\u4e0a\u4f20\u51fa\u9519!</font>";
	var progress;
	try {
		switch (errorCode) {
		  case SWFUpload.UPLOAD_ERROR.FILE_CANCELLED:
			try {
				progress = new FileProgress(file, this.customSettings.upload_target);
				progress.setCancelled();
				//提示信息:取消上传!
				progress.setStatus("<font color='red' style='font-size: 12px;'>\u53d6\u6d88\u4e0a\u4f20!</font>");
				progress.toggleCancel(false);
			}
			catch (ex1) {
				this.debug(ex1);
			}
			break;
		  case SWFUpload.UPLOAD_ERROR.UPLOAD_STOPPED:
			try {
				progress = new FileProgress(file, this.customSettings.upload_target);
				progress.setCancelled();
				//提示信息:停止上传!
				progress.setStatus("<font color='red' style='font-size: 12px;'>\u505c\u6b62\u4e0a\u4f20!</font>");
				progress.toggleCancel(true);
			}
			catch (ex2) {
				this.debug(ex2);
			}
		  case SWFUpload.UPLOAD_ERROR.UPLOAD_LIMIT_EXCEEDED:
		  	//提示信息:文件大小超过限制!
			imageName = "<font color='red' style='font-size: 12px;'>\u6587\u4ef6\u5927\u5c0f\u8d85\u8fc7\u9650\u5236!</font>";
			break;
		  default:
			switch(message){
				case "500":
					//提示信息:服务器内部错误!
					imageName = "<font color='red' style='font-size: 12px;'>\u670D\u52A1\u5668\u5185\u90E8\u9519\u8BEF\!</font>";
				break;
				case "404":
					//提示信息:上传路径设置错误!
					imageName = "<font color='red' style='font-size: 12px;'>\u670D\u52A1\u5668\u5185\u90E8\u9519\u8BEF\!</font>";
				break;
				default:
					//提示信息:位置错误!
					imageName = "<font color='red' style='font-size: 12px;'>\u672A\u77E5\u9519\u8BEF\!</font>";
				break
			}
			break;
		}
		addFileInfo(file.id, imageName);
	}
	catch (ex3) {
		this.debug(ex3);
	}
}


function addImage(src) {
	var newImg = document.createElement("img");
	newImg.style.margin = "5px";
	document.getElementById("thumbnails").appendChild(newImg);
	if (newImg.filters) {
		try {
			newImg.filters.item("DXImageTransform.Microsoft.Alpha").opacity = 0;
		}
		catch (e) {
			// If it is not set initially, the browser will throw an error.  This will set it if it is not set yet.
			newImg.style.filter = "progid:DXImageTransform.Microsoft.Alpha(opacity=" + 0 + ")";
		}
	} else {
		newImg.style.opacity = 0;
	}
	newImg.onload = function () {
		fadeIn(newImg, 0);
	};
	newImg.src = src;
}
function fadeIn(element, opacity) {
	var reduceOpacityBy = 5;
	var rate = 30;	// 15 fps
	if (opacity < 100) {
		opacity += reduceOpacityBy;
		if (opacity > 100) {
			opacity = 100;
		}
		if (element.filters) {
			try {
				element.filters.item("DXImageTransform.Microsoft.Alpha").opacity = opacity;
			}
			catch (e) {
				// If it is not set initially, the browser will throw an error.  This will set it if it is not set yet.
				element.style.filter = "progid:DXImageTransform.Microsoft.Alpha(opacity=" + opacity + ")";
			}
		} else {
			element.style.opacity = opacity / 100;
		}
	}
	if (opacity < 100) {
		setTimeout(function () {
			fadeIn(element, opacity);
		}, rate);
	}
}
/* ******************************************
 *	FileProgress Object
 *	Control object for displaying file info
 * ****************************************** */
function FileProgress(file, targetID) {
	this.fileProgressID = "divFileProgress";
	this.fileProgressWrapper = document.getElementById(this.fileProgressID);
	if (!this.fileProgressWrapper) {
		this.fileProgressWrapper = document.createElement("div");
		this.fileProgressWrapper.className = "progressWrapper";
		this.fileProgressWrapper.id = this.fileProgressID;
		this.fileProgressElement = document.createElement("div");
		this.fileProgressElement.className = "progressContainer";
		var progressCancel = document.createElement("a");
		progressCancel.className = "progressCancel";
		progressCancel.href = "#";
		progressCancel.style.visibility = "hidden";
		progressCancel.appendChild(document.createTextNode(" "));
		var progressText = document.createElement("div");
		progressText.className = "progressName";
		progressText.appendChild(document.createTextNode("\u4e0a\u4f20\u6587\u4ef6: " + file.name));
		var progressBar = document.createElement("div");
		progressBar.className = "progressBarInProgress";
		var progressStatus = document.createElement("div");
		progressStatus.className = "progressBarStatus";
		progressStatus.innerHTML = "&nbsp;";
		this.fileProgressElement.appendChild(progressCancel);
		this.fileProgressElement.appendChild(progressText);
		this.fileProgressElement.appendChild(progressStatus);
		this.fileProgressElement.appendChild(progressBar);
		this.fileProgressWrapper.appendChild(this.fileProgressElement);
		document.getElementById(targetID).style.height = "75px";
		document.getElementById(targetID).appendChild(this.fileProgressWrapper);
		fadeIn(this.fileProgressWrapper, 0);
	} else {
		this.fileProgressElement = this.fileProgressWrapper.firstChild;
		this.fileProgressElement.childNodes[1].firstChild.nodeValue = "\u4e0a\u4f20\u6587\u4ef6: " + file.name;
	}
	this.height = this.fileProgressWrapper.offsetHeight;
}
FileProgress.prototype.setProgress = function (percentage) {
	this.fileProgressElement.className = "progressContainer green";
	this.fileProgressElement.childNodes[3].className = "progressBarInProgress";
	this.fileProgressElement.childNodes[3].style.width = percentage + "%";
};
FileProgress.prototype.setComplete = function () {
	this.fileProgressElement.className = "progressContainer blue";
	this.fileProgressElement.childNodes[3].className = "progressBarComplete";
	this.fileProgressElement.childNodes[3].style.width = "";
};
FileProgress.prototype.setError = function () {
	this.fileProgressElement.className = "progressContainer red";
	this.fileProgressElement.childNodes[3].className = "progressBarError";
	this.fileProgressElement.childNodes[3].style.width = "";
};
FileProgress.prototype.setCancelled = function () {
	this.fileProgressElement.className = "progressContainer";
	this.fileProgressElement.childNodes[3].className = "progressBarError";
	this.fileProgressElement.childNodes[3].style.width = "";
};
FileProgress.prototype.setStatus = function (status) {
	this.fileProgressElement.childNodes[2].innerHTML = status;
};
FileProgress.prototype.toggleCancel = function (show, swfuploadInstance) {
	this.fileProgressElement.childNodes[0].style.visibility = show ? "visible" : "hidden";
	if (swfuploadInstance) {
		var fileID = this.fileProgressID;
		this.fileProgressElement.childNodes[0].onclick = function () {
			swfuploadInstance.cancelUpload(fileID);
			return false;
		};
	}
};

