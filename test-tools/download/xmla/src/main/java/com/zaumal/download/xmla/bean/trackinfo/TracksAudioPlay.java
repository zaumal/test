package com.zaumal.download.xmla.bean.trackinfo;

import com.alibaba.fastjson.JSON;
import com.zaumal.download.xmla.bean.albuminfo.AlbumInfo;
import com.zaumal.download.xmla.util.WebUtil;

public class TracksAudioPlay {

	private int index;
	private long trackId;
	private String trackName;
	private String trackUrl;
	private String trackCoverPath;
	private long albumId;
	private long sourceAlbumId;
	private String albumName;
	private String albumUrl;
	private long anchorId;
	private boolean canPlay;
	private boolean isBaiduMusic;
	private boolean isPaid;
	private int duration;
	private String src;
	private boolean hasBuy;
	private boolean albumIsSample;
	private int sampleDuration;
	private String updateTime;
	private String createTime;
	private boolean isLike;
	private boolean isCopyright;

	public void setIndex(int index) {
		this.index = index;
	}

	public int getIndex() {
		return index;
	}

	public void setTrackId(long trackId) {
		this.trackId = trackId;
	}

	public long getTrackId() {
		return trackId;
	}

	public void setTrackName(String trackName) {
		this.trackName = trackName;
	}

	public String getTrackName() {
		return trackName;
	}

	public void setTrackUrl(String trackUrl) {
		this.trackUrl = trackUrl;
	}

	public String getTrackUrl() {
		return trackUrl;
	}

	public void setTrackCoverPath(String trackCoverPath) {
		this.trackCoverPath = trackCoverPath;
	}

	public String getTrackCoverPath() {
		return trackCoverPath;
	}

	public void setAlbumId(long albumId) {
		this.albumId = albumId;
	}

	public long getAlbumId() {
		return albumId;
	}

	public void setSourceAlbumId(long sourceAlbumId) {
		this.sourceAlbumId = sourceAlbumId;
	}

	public long getSourceAlbumId() {
		return sourceAlbumId;
	}

	public void setAlbumName(String albumName) {
		this.albumName = albumName;
	}

	public String getAlbumName() {
		return albumName;
	}

	public void setAlbumUrl(String albumUrl) {
		this.albumUrl = albumUrl;
	}

	public String getAlbumUrl() {
		return albumUrl;
	}

	public void setAnchorId(long anchorId) {
		this.anchorId = anchorId;
	}

	public long getAnchorId() {
		return anchorId;
	}

	public void setCanPlay(boolean canPlay) {
		this.canPlay = canPlay;
	}

	public boolean getCanPlay() {
		return canPlay;
	}

	public void setIsBaiduMusic(boolean isBaiduMusic) {
		this.isBaiduMusic = isBaiduMusic;
	}

	public boolean getIsBaiduMusic() {
		return isBaiduMusic;
	}

	public void setIsPaid(boolean isPaid) {
		this.isPaid = isPaid;
	}

	public boolean getIsPaid() {
		return isPaid;
	}

	public void setDuration(int duration) {
		this.duration = duration;
	}

	public int getDuration() {
		return duration;
	}

	public void setSrc(String src) {
		this.src = src;
	}

	public String getSrc() {
		return src;
	}

	public void setHasBuy(boolean hasBuy) {
		this.hasBuy = hasBuy;
	}

	public boolean getHasBuy() {
		return hasBuy;
	}

	public void setAlbumIsSample(boolean albumIsSample) {
		this.albumIsSample = albumIsSample;
	}

	public boolean getAlbumIsSample() {
		return albumIsSample;
	}

	public void setSampleDuration(int sampleDuration) {
		this.sampleDuration = sampleDuration;
	}

	public int getSampleDuration() {
		return sampleDuration;
	}

	public void setUpdateTime(String updateTime) {
		this.updateTime = updateTime;
	}

	public String getUpdateTime() {
		return updateTime;
	}

	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}

	public String getCreateTime() {
		return createTime;
	}

	public void setIsLike(boolean isLike) {
		this.isLike = isLike;
	}

	public boolean getIsLike() {
		return isLike;
	}

	public void setIsCopyright(boolean isCopyright) {
		this.isCopyright = isCopyright;
	}

	public boolean getIsCopyright() {
		return isCopyright;
	}

	public void audioInfo() {
//		String trackListInfoUrl = "https://www.ximalaya.com/revision/play/album?albumId=" + this.albumId + "&pageNum=" + this.currentPage + "&sort=0&pageSize=30";
		String trackListInfoUrl = "https://www.ximalaya.com/revision/play/v1/audio?id=" + this.trackId + "&ptype=1";
		String trackListStr = WebUtil.sendGet(trackListInfoUrl);
		AudioInfo audioInfo = JSON.parseObject(trackListStr, AudioInfo.class);
		this.src = audioInfo.getData().getSrc();
	}
	
	public String genFilename(AlbumInfo albumInfo){
		//音频序列
		int index = getIndex();
		//音频名称
		String trackName = getTrackName();
		//音频路径
		String src = getSrc();
		//音频时长
//		int duration = getDuration();
		//在喜马拉雅FastDFS中的文件后缀名
		String fileSuffix = src.substring(src.lastIndexOf("."), src.length());
		//音频总数是几位数
		int trackAmountDigit = (albumInfo.getData().getTracksInfo().getTrackTotalCount() + "").length();
		//当前文件名的位数
		int currentDigit = (index + "").length();
		//总共要补的零的个数
		int zeroLength = trackAmountDigit - currentDigit;
		//补零
		String zero = "";
		for (int i = 0; i < zeroLength; i++) {
			zero += "0";
		}
		// 拼文件名
		return zero + index + "_" + trackName + fileSuffix;
	}
}