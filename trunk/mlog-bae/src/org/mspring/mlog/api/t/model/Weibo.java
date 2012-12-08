/**
 * 
 */
package org.mspring.mlog.api.t.model;

/**
 * @author Gao Youbo
 * @since 2012-8-31
 * @Description
 * @TODO <a href=
 *       "http://wiki.open.t.qq.com/index.php/%E5%BE%AE%E5%8D%9A%E7%9B%B8%E5%85%B3/%E6%A0%B9%E6%8D%AE%E5%BE%AE%E5%8D%9Aid%E6%89%B9%E9%87%8F%E8%8E%B7%E5%8F%96%E5%BE%AE%E5%8D%9A%E5%86%85%E5%AE%B9"
 *       >一条微博对象</a>
 */
public class Weibo {
    private String text; // 微博内容,
    private String origtext; // 原始内容,
    private String count; // 微博被转次数,
    private String mcount; // 点评次数,
    private String from; // 来源,
    private String fromurl; // 来源url,
    private String id; // 微博唯一id,
    private String image; // 图片url列表,
    private String name; // 发表人帐户名,
    private String openid; // 用户唯一id，与name相对应,
    private String nick; // 发表人昵称,
    private String self; // 是否自已发的的微博，0-不是，1-是,
    private String timestamp; // 发表时间,
    private String type; // 微博类型，1-原创发表，2-转载，3-私信，4-回复，5-空回，6-提及，7-评论,
    private String head; // 发表者头像url,
    private String location; // 发表者所在地,
    private String country_code; // 国家码（与地区发表时间线一样）,
    private String province_code; // 省份码（与地区发表时间线一样）,
    private String city_code; // 城市码（与地区发表时间线一样）,
    private String isvip; // 是否微博认证用户，0-不是，1-是,
    private String geo; // 发表者地理信息,
    private String status; // 微博状态，0-正常，1-系统删除，2-审核中，3-用户删除，4-根删除,
    private String emotionurl; // 心情图片url,
    private String emotiontype; // 心情类型,
    private String isrealname; // 是否实名认证，0-老用户，1-已实名认证，2-未实名认证,
    private String longitude; // 经度,
    private String latitude; // 纬度,
    private String source; // 当type=2时，source即为源tweet
    private Video video;
    private Music music;

    /**
     * @return the text
     */
    public String getText() {
        return text;
    }

    /**
     * @param text
     *            the text to set
     */
    public void setText(String text) {
        this.text = text;
    }

    /**
     * @return the origtext
     */
    public String getOrigtext() {
        return origtext;
    }

    /**
     * @param origtext
     *            the origtext to set
     */
    public void setOrigtext(String origtext) {
        this.origtext = origtext;
    }

    /**
     * @return the count
     */
    public String getCount() {
        return count;
    }

    /**
     * @param count
     *            the count to set
     */
    public void setCount(String count) {
        this.count = count;
    }

    /**
     * @return the mcount
     */
    public String getMcount() {
        return mcount;
    }

    /**
     * @param mcount
     *            the mcount to set
     */
    public void setMcount(String mcount) {
        this.mcount = mcount;
    }

    /**
     * @return the from
     */
    public String getFrom() {
        return from;
    }

    /**
     * @param from
     *            the from to set
     */
    public void setFrom(String from) {
        this.from = from;
    }

    /**
     * @return the fromurl
     */
    public String getFromurl() {
        return fromurl;
    }

    /**
     * @param fromurl
     *            the fromurl to set
     */
    public void setFromurl(String fromurl) {
        this.fromurl = fromurl;
    }

    /**
     * @return the id
     */
    public String getId() {
        return id;
    }

    /**
     * @param id
     *            the id to set
     */
    public void setId(String id) {
        this.id = id;
    }

    /**
     * @return the image
     */
    public String getImage() {
        return image;
    }

    /**
     * @param image
     *            the image to set
     */
    public void setImage(String image) {
        this.image = image;
    }

    /**
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * @param name
     *            the name to set
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * @return the openid
     */
    public String getOpenid() {
        return openid;
    }

    /**
     * @param openid
     *            the openid to set
     */
    public void setOpenid(String openid) {
        this.openid = openid;
    }

    /**
     * @return the nick
     */
    public String getNick() {
        return nick;
    }

    /**
     * @param nick
     *            the nick to set
     */
    public void setNick(String nick) {
        this.nick = nick;
    }

    /**
     * @return the self
     */
    public String getSelf() {
        return self;
    }

    /**
     * @param self
     *            the self to set
     */
    public void setSelf(String self) {
        this.self = self;
    }

    /**
     * @return the timestamp
     */
    public String getTimestamp() {
        return timestamp;
    }

    /**
     * @param timestamp
     *            the timestamp to set
     */
    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }

    /**
     * @return the type
     */
    public String getType() {
        return type;
    }

    /**
     * @param type
     *            the type to set
     */
    public void setType(String type) {
        this.type = type;
    }

    /**
     * @return the head
     */
    public String getHead() {
        return head;
    }

    /**
     * @param head
     *            the head to set
     */
    public void setHead(String head) {
        this.head = head;
    }

    /**
     * @return the location
     */
    public String getLocation() {
        return location;
    }

    /**
     * @param location
     *            the location to set
     */
    public void setLocation(String location) {
        this.location = location;
    }

    /**
     * @return the country_code
     */
    public String getCountry_code() {
        return country_code;
    }

    /**
     * @param country_code
     *            the country_code to set
     */
    public void setCountry_code(String country_code) {
        this.country_code = country_code;
    }

    /**
     * @return the province_code
     */
    public String getProvince_code() {
        return province_code;
    }

    /**
     * @param province_code
     *            the province_code to set
     */
    public void setProvince_code(String province_code) {
        this.province_code = province_code;
    }

    /**
     * @return the city_code
     */
    public String getCity_code() {
        return city_code;
    }

    /**
     * @param city_code
     *            the city_code to set
     */
    public void setCity_code(String city_code) {
        this.city_code = city_code;
    }

    /**
     * @return the isvip
     */
    public String getIsvip() {
        return isvip;
    }

    /**
     * @param isvip
     *            the isvip to set
     */
    public void setIsvip(String isvip) {
        this.isvip = isvip;
    }

    /**
     * @return the geo
     */
    public String getGeo() {
        return geo;
    }

    /**
     * @param geo
     *            the geo to set
     */
    public void setGeo(String geo) {
        this.geo = geo;
    }

    /**
     * @return the status
     */
    public String getStatus() {
        return status;
    }

    /**
     * @param status
     *            the status to set
     */
    public void setStatus(String status) {
        this.status = status;
    }

    /**
     * @return the emotionurl
     */
    public String getEmotionurl() {
        return emotionurl;
    }

    /**
     * @param emotionurl
     *            the emotionurl to set
     */
    public void setEmotionurl(String emotionurl) {
        this.emotionurl = emotionurl;
    }

    /**
     * @return the emotiontype
     */
    public String getEmotiontype() {
        return emotiontype;
    }

    /**
     * @param emotiontype
     *            the emotiontype to set
     */
    public void setEmotiontype(String emotiontype) {
        this.emotiontype = emotiontype;
    }

    /**
     * @return the isrealname
     */
    public String getIsrealname() {
        return isrealname;
    }

    /**
     * @param isrealname
     *            the isrealname to set
     */
    public void setIsrealname(String isrealname) {
        this.isrealname = isrealname;
    }

    /**
     * @return the longitude
     */
    public String getLongitude() {
        return longitude;
    }

    /**
     * @param longitude
     *            the longitude to set
     */
    public void setLongitude(String longitude) {
        this.longitude = longitude;
    }

    /**
     * @return the latitude
     */
    public String getLatitude() {
        return latitude;
    }

    /**
     * @param latitude
     *            the latitude to set
     */
    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }

    /**
     * @return the source
     */
    public String getSource() {
        return source;
    }

    /**
     * @param source
     *            the source to set
     */
    public void setSource(String source) {
        this.source = source;
    }

    /**
     * @return the video
     */
    public Video getVideo() {
        return video;
    }

    /**
     * @param video
     *            the video to set
     */
    public void setVideo(Video video) {
        this.video = video;
    }

    /**
     * @return the music
     */
    public Music getMusic() {
        return music;
    }

    /**
     * @param music
     *            the music to set
     */
    public void setMusic(Music music) {
        this.music = music;
    }

}

/**
 * 视频信息
 * 
 * @author Gao Youbo
 * @since 2012-8-31
 * @Description
 * @TODO
 */
class Video {
    private String picurl; // 缩略图,
    private String player; // 播放器地址,
    private String realurl; // 视频原地址,
    private String shorturl; // 视频的短url,
    private String title; // 视频标题

    /**
     * @return the picurl
     */
    public String getPicurl() {
        return picurl;
    }

    /**
     * @param picurl
     *            the picurl to set
     */
    public void setPicurl(String picurl) {
        this.picurl = picurl;
    }

    /**
     * @return the player
     */
    public String getPlayer() {
        return player;
    }

    /**
     * @param player
     *            the player to set
     */
    public void setPlayer(String player) {
        this.player = player;
    }

    /**
     * @return the realurl
     */
    public String getRealurl() {
        return realurl;
    }

    /**
     * @param realurl
     *            the realurl to set
     */
    public void setRealurl(String realurl) {
        this.realurl = realurl;
    }

    /**
     * @return the shorturl
     */
    public String getShorturl() {
        return shorturl;
    }

    /**
     * @param shorturl
     *            the shorturl to set
     */
    public void setShorturl(String shorturl) {
        this.shorturl = shorturl;
    }

    /**
     * @return the title
     */
    public String getTitle() {
        return title;
    }

    /**
     * @param title
     *            the title to set
     */
    public void setTitle(String title) {
        this.title = title;
    }

}

/**
 * 音频信息
 * 
 * @author Gao Youbo
 * @since 2012-8-31
 * @Description
 * @TODO
 */
class Music {
    private String author; // 演唱者,
    private String url; // 音频地址,
    private String title; // 音频名字，歌名

    /**
     * @return the author
     */
    public String getAuthor() {
        return author;
    }

    /**
     * @param author
     *            the author to set
     */
    public void setAuthor(String author) {
        this.author = author;
    }

    /**
     * @return the url
     */
    public String getUrl() {
        return url;
    }

    /**
     * @param url
     *            the url to set
     */
    public void setUrl(String url) {
        this.url = url;
    }

    /**
     * @return the title
     */
    public String getTitle() {
        return title;
    }

    /**
     * @param title
     *            the title to set
     */
    public void setTitle(String title) {
        this.title = title;
    }

}
