MSpring.Album = {};

var _album = MSpring.Album

/**
 * 生成缩略图
 * @param {} image
 * @param {} toWidth
 * @param {} toHeight
 */
_album.getThumbImage = function(image, toWidth, toHeight){
	// 设置图片尺寸
	var imgWidth = image.width;
	var imgHeight = image.height;
	var ratio = 1;
	// 宽度设定
	if(imgWidth>toWidth){
		ratio = toWidth / imgWidth;
		imgWidth = toWidth;
		imgHeight = imgHeight * ratio;
	}
	// 高度设定
	if(imgHeight>toHeight){
		ratio = toHeight / imgHeight;
		imgWidth = imgWidth * ratio;
		imgHeight = toHeight;
	}
	image.width = imgWidth;
	image.height = imgHeight;
}