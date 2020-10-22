package com.zaumal.ting89.utils;

import org.springframework.stereotype.Component;

import java.io.*;

@Component
public class FileUtil {
    public static String TMP = "tmp";
    private String dir;

    public String getDir() {
        File tmp = new File(TMP);
        if(!tmp.exists()){
            tmp.mkdir();
        }
        String dir = TMP + File.separator + this.dir;
        File dirFile = new File(dir);
        if(!dirFile.exists()){
            dirFile.mkdir();
        }
        return dir;
    }

    public void setDir(String dir) {
        this.dir = dir;
    }

    public String getFilePathName(String fileName){
        return getDir() + File.separator + fileName;
    }

    public static void main(String[] args) throws IOException {
        String dir = "test";
        String file = "t1.txt";
        String html = "fasdfsafs中国";

        FileUtil fileUtil = new FileUtil();
        fileUtil.setDir(dir);
        fileUtil.crtFile(file,html);
    }

    public void crtFile(String fileName, String html) throws IOException {
        String filePath = getFilePathName(fileName);

        File file = new File(filePath);
        if(file.exists()) {
            file.delete();
        }
        file.createNewFile();

        BufferedWriter out = null;
        OutputStreamWriter osw = null;
        FileOutputStream fos = null;
        try {
            fos = new FileOutputStream(file, true);
            osw = new OutputStreamWriter(fos);
            out = new BufferedWriter(osw);
//            out.write(new String(html.getBytes("GBK"),"UTF-8"));
            out.write(html);
            out.flush();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if(null != fos){
                try{
                    fos.close();
                }catch (Exception e){}
            }
            if(null != osw){
                try {
                    osw.close();
                }catch (Exception e){}
            }
            if(null != out){
                try {
                    out.close();
                }catch (Exception e){}
            }
        }
    }
}