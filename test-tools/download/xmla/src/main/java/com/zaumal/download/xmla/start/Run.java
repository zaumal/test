package com.zaumal.download.xmla.start;

import java.io.File;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.zaumal.download.xmla.bean.albuminfo.AlbumInfo;
import com.zaumal.download.xmla.bean.trackinfo.TracksAudioPlay;
import com.zaumal.download.xmla.util.DownloadRunnable;
import com.zaumal.download.xmla.bean.Ximalaya;

public class Run {
	private Logger logger = LoggerFactory.getLogger(Run.class);
	
	public void start(String albumId, String savePath, Object notifyObj,List<File> result) {
		File folder = new File(savePath);
		if (folder.exists() == false) {
			folder.mkdirs();
		}
		
		Ximalaya ximalaya = new Ximalaya(albumId);
		//获取专辑信息
		AlbumInfo albumInfo = ximalaya.getAlbumInfo();
		//总音频数
		int trackTotalCount = albumInfo.getData().getTracksInfo().getTrackTotalCount();
		//获取专辑中所有音频信息
		List<TracksAudioPlay> audioList = ximalaya.getAudioPlayList(ximalaya);
		
		// 多线程下载
		ExecutorService executorService = Executors.newFixedThreadPool(10);
		for (TracksAudioPlay audio : audioList) {
			audio.audioInfo();
			String url = audio.getSrc();
			String filename = audio.genFilename(albumInfo);
			//音频序列
			int index = audio.getIndex();
			//时长
			int duration = audio.getDuration();
			
			logger.info("音频名称：{}，音频时长：{}分，第{}({})个音频，文件地址：{}",
					filename,String.format("%.2f",duration*1.0/60),index + "/" + trackTotalCount,
					String.format("%.2f%% ", index * 1.0 / trackTotalCount * 100),url);
			
			executorService.execute(new DownloadRunnable(url, savePath, filename, trackTotalCount, notifyObj));
			// 添加到本地文件列表中
			result.add(new File(savePath + File.separator + filename));
		}
		executorService.shutdown();
	}
}
