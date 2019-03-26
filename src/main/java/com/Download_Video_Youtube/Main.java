package com.Download_Video_Youtube;


import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.LinkOption;
import java.nio.file.Path;
import java.nio.file.Paths;


public class Main {

    public static void main(String[] args) throws IOException {
        String OS_name = System.getProperty("os.name");
        String userName = System.getProperty("user.name");
        if(OS_name.contains("Windows")){
            File youtubeDl = new File("C:\\Users\\"+userName+"\\Desktop\\Download_Video_Youtube\\youtube-dl.exe");
            File original_yotubeDl = new File("C:\\Download_Video_Youtube\\youtube-dl.exe");
            if(!original_yotubeDl.exists() && youtubeDl.exists()) {
                FileUtils.moveFile(youtubeDl, original_yotubeDl);
            }
            Path path = Paths.get("C:\\Download_Video_Youtube\\youtube-dl.exe");
            //set hidden attribute
            Files.setAttribute(path, "dos:hidden", true, LinkOption.NOFOLLOW_LINKS);
        }else{
            File youtubeDl = new File("/home/"+userName+"/Desktop/Download_Video_Youtube/youtube-dl.exe");
            File original_yotubeDl = new File("/home/"+userName+"/Download_Video_Youtube/youtube-dl.exe");
            if(!original_yotubeDl.exists()) {
                FileUtils.moveFile(youtubeDl, original_yotubeDl);
            }
            Path path = Paths.get("/home/"+userName+"/Download_Video_Youtube/youtube-dl.exe");
            //set hidden attribute
            Files.setAttribute(path, "dos:hidden", true, LinkOption.NOFOLLOW_LINKS);
        }
        new com.Download_Video_Youtube.view.App();

    }



        public static   void main(String args) {
            int i = 5, j = 2;
            System.out.println( i % j );
        }


}
