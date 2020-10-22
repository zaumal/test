package com.zaumal.download.xmla.bean.trackinfo;

public class AudioData {
	private long trackId;
	private boolean canPlay;
	private boolean isPaid;
	private boolean hasBuy;
	private String src; //"https://fdfs.xmcdn.com/group56/M0B/1D/1D/wKgLgFxgIjfDXOU4ALMKfKDAlbY511.m4a"
	private boolean  albumIsSample;
	private int sampleDuration;
	private boolean isBaiduMusic;
	private boolean firstPlayStatus;
	
	public long getTrackId() {
		return trackId;
	}
	public void setTrackId(long trackId) {
		this.trackId = trackId;
	}
	public boolean isCanPlay() {
		return canPlay;
	}
	public void setCanPlay(boolean canPlay) {
		this.canPlay = canPlay;
	}
	public boolean isPaid() {
		return isPaid;
	}
	public void setPaid(boolean isPaid) {
		this.isPaid = isPaid;
	}
	public boolean isHasBuy() {
		return hasBuy;
	}
	public void setHasBuy(boolean hasBuy) {
		this.hasBuy = hasBuy;
	}
	public String getSrc() {
		return src;
	}
	public void setSrc(String src) {
		this.src = src;
	}
	public boolean isAlbumIsSample() {
		return albumIsSample;
	}
	public void setAlbumIsSample(boolean albumIsSample) {
		this.albumIsSample = albumIsSample;
	}
	public int getSampleDuration() {
		return sampleDuration;
	}
	public void setSampleDuration(int sampleDuration) {
		this.sampleDuration = sampleDuration;
	}
	public boolean isBaiduMusic() {
		return isBaiduMusic;
	}
	public void setBaiduMusic(boolean isBaiduMusic) {
		this.isBaiduMusic = isBaiduMusic;
	}
	public boolean isFirstPlayStatus() {
		return firstPlayStatus;
	}
	public void setFirstPlayStatus(boolean firstPlayStatus) {
		this.firstPlayStatus = firstPlayStatus;
	}
}
