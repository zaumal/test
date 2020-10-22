package com.zaumal.download.xmla.bean;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.alibaba.fastjson.JSON;

import com.zaumal.download.xmla.bean.albuminfo.AlbumInfo;
import com.zaumal.download.xmla.bean.trackinfo.TrackListInfo;
import com.zaumal.download.xmla.bean.trackinfo.TracksAudioPlay;
import com.zaumal.download.xmla.util.WebUtil;

/**
 * 一页音轨列表的信息
 * 
 * @author Administrator
 *
 */
public class Ximalaya {
	private Logger logger = LoggerFactory.getLogger(Ximalaya.class);
	
	private String albumId;
	private int currentPage = 1;

	public String getAlbumId() {
		return albumId;
	}

	public void setAlbumId(String albumId) {
		this.albumId = albumId;
	}

	public int getCurrentPage() {
		return currentPage;
	}

	public void setCurrentPage(int currentPage) {
		this.currentPage = currentPage;
	}

	public Ximalaya(String albumId) {
		this.albumId = albumId;
	}

	/**
	 * 一页音轨列表信息
	 * 
	 * @return
	 */
	public TrackListInfo getTrackListInfo() {
//		String trackListInfoUrl = "https://www.ximalaya.com/revision/play/album?albumId=" + this.albumId + "&pageNum=" + this.currentPage + "&sort=0&pageSize=30";
		String trackListInfoUrl = "https://www.ximalaya.com/revision/play/v1/show?id=" + this.albumId + "&num=" + this.currentPage + "&sort=0&size=30&ptype=0";
		String trackListStr = WebUtil.sendGet(trackListInfoUrl);
		return JSON.parseObject(trackListStr, TrackListInfo.class);
	}

	/**
	 * 专辑信息
	 */
	public AlbumInfo getAlbumInfo() {
		String albumInfoUrl = "https://www.ximalaya.com/revision/album?albumId=" + this.albumId;
		AlbumInfo albumInfo = JSON.parseObject(WebUtil.sendGet(albumInfoUrl), AlbumInfo.class);
		
		//专辑名称
		String albumName = albumInfo.getData().getMainInfo().getAlbumTitle();
		//创作者ID
//		long anchorId = albumInfo.getData().getAnchorInfo().getAnchorId();
		//创作者名称
		String anchorName = albumInfo.getData().getAnchorInfo().getAnchorName();
		//总集数
		int trackTotalCount = albumInfo.getData().getTracksInfo().getTrackTotalCount();
		logger.info("专辑《{}》，创作者：{}，共{}集",albumName,anchorName,trackTotalCount);
		
		return albumInfo;
	}
	
	/**
	 * 获取整个专辑所有音频列表
	 */
	public List<TracksAudioPlay> getAudioPlayList(Ximalaya ximalaya){
		List<TracksAudioPlay> audioList = new ArrayList<>();
		while(true){
			//获取当前一页音频列表信息
			TrackListInfo trackListInfo = ximalaya.getTrackListInfo();
			//设置下一页的页码
			ximalaya.setCurrentPage(ximalaya.getCurrentPage() + 1);
			//获取一页的音频列表
			List<TracksAudioPlay> list = trackListInfo.getData().getTracksAudioPlay();
			audioList.addAll(list);
			
			if(trackListInfo.getData().getHasMore() != true){
				break;
			}
		}
		return audioList;
	}
}
