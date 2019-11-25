package com.zaumal.test.io;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.channels.FileChannel;
import java.nio.charset.Charset;
import java.nio.charset.CharsetDecoder;

public class TestNIO {
	public static void main(String[] args) {
		readFile();
	}
	public static void readFile() {
		
		InputStream is;
		
		RandomAccessFile rfile = null;
		FileChannel channel = null;
		try {
			// 获取文件
			rfile = new RandomAccessFile("C:\\Users\\zaumal\\Desktop\\test.txt", "rw");
			// 获取文件通道
			channel = rfile.getChannel();
			// 设置缓冲区
			ByteBuffer bbf = ByteBuffer.allocate(1024);
			CharBuffer cbf = CharBuffer.allocate(1024);
			//字符编码
			Charset charset = Charset.forName("utf-8");
			CharsetDecoder cd = charset.newDecoder();
			// 读取缓冲区的数据
			int data = channel.read(bbf);
			while (data != -1) {
				// 通过flip()方法将Buffer从写模式切换到读模式。在读模式下，可以读取之前写入到buffer的所有数据。
				bbf.flip();
				
				cd.decode(bbf, cbf, true);

				char[] cs = new char[cbf.length()];
				
				while (cbf.hasRemaining()) {
					cbf.get(cs);
					System.out.print(new String(cs));
				}
				/**
				 * 有两种方式能清空缓冲区：调用clear()或compact()方法。
				 * clear()方法会清空整个缓冲区。compact()方法只会清除已经读过的数据。
				 * 任何未读的数据都被移到缓冲区的起始处，新写入的数据将放到缓冲区未读数据的后面。
				 */
				bbf.clear();
				cbf.clear();
				data = channel.read(bbf);
			}
			
		} catch (IOException e) {
			e.printStackTrace();
		}finally {
			if(null != channel) {
				try {
					channel.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			if(null != rfile) {
				try {
					rfile.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		
	}
}
