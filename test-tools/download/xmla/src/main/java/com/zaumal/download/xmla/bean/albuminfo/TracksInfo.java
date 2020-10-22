package com.zaumal.download.xmla.bean.albuminfo;

import java.util.List;

public class TracksInfo {

	private int trackTotalCount;
	private int sort;
	private List<Tracks> tracks;
	private int pageNum;
	private int pageSize;

	public void setTrackTotalCount(int trackTotalCount) {
		this.trackTotalCount = trackTotalCount;
	}

	public int getTrackTotalCount() {
		return trackTotalCount;
	}

	public void setSort(int sort) {
		this.sort = sort;
	}

	public int getSort() {
		return sort;
	}

	public void setTracks(List<Tracks> tracks) {
		this.tracks = tracks;
	}

	public List<Tracks> getTracks() {
		return tracks;
	}

	public void setPageNum(int pageNum) {
		this.pageNum = pageNum;
	}

	public int getPageNum() {
		return pageNum;
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}

	public int getPageSize() {
		return pageSize;
	}

}