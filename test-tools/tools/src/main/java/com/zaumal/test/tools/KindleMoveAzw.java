package com.zaumal.test.tools;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public class KindleMoveAzw {
	public static void main(String[] args) {
		t1();
	}
	
	//把kindle下载的azw格式的文件整理复制到destPath路径下
	public static void t1() {
		String sourcePath = "D:\\Documents\\My Kindle Content";
		String destPath = "E:\\tmp\\kindle书籍\\azw";
		
		File file = new File(sourcePath);
		
		File[] files = file.listFiles();
		
		for(int i = 0; i < files.length; i++) {
			File f = files[i];
			if(f.isDirectory()) {
				File ff = getDir(f);
				if(null != ff) {
					String filePath = ff.getPath();
					String fileName = ff.getName();
					System.out.println(filePath);
					copy(ff, new File(destPath + File.separator + fileName));
				}
			}
		}
	}
	
	public static File getDir(File file) {
		File result = null;
		
		File[] files = file.listFiles();
		
		for(int i = 0; i < files.length; i++) {
			String fileName = null;
			File f = files[i];
			if(!f.isDirectory()) {
				fileName = f.getName();
				if(fileName.endsWith("azw")) {
					result = f;
					break;
				}
			}
		}
		return result;
	}
	
	public static void copy(File source, File dest) {
	    InputStream in = null;
	    OutputStream out = null;
	    try {
	        in = new FileInputStream(source);
	        out = new FileOutputStream(dest);

	        byte[] buffer = new byte[1024];
	        int len;

	        while ((len = in.read(buffer)) > 0) {
	            out.write(buffer, 0, len);
	        }
	    } catch (Exception e) {
	    	e.printStackTrace();
	    } finally {
	    	close(out);
	    	close(in);
	    }
	}
	
	public static void close(InputStream in) {
		if(null != in) {
    		try {
				in.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
    	}
	}
	
	public static void close(OutputStream out) {
		if(null != out) {
			try {
				out.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
}
