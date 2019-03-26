package com.Download_Video_Youtube.util;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.LinkOption;
import java.nio.file.Path;
import java.nio.file.Paths;


public class Util {


    public static void saveFileFromUrlWithDL(String fileName,
                                             String fileUrl, String OS_name, String user_Name) {
        String download_path = "";
        if (OS_name.contains("Windows")) {
            download_path = "C:\\Download_Video_Youtube";
        } else {

        }
        String[] command =
                {
                        "cmd",
                };
        Process p;
        try {
            p = Runtime.getRuntime().exec(command);
            new Thread(new com.Download_Video_Youtube.util.SyncPipe(p.getErrorStream(), System.err)).start();
            new Thread(new com.Download_Video_Youtube.util.SyncPipe(p.getInputStream(), System.out)).start();
            PrintWriter stdin = new PrintWriter(p.getOutputStream());
            stdin.println("cd \"" + download_path + "\"");
            stdin.println("youtube-dl -U");
            stdin.println("youtube-dl " + fileUrl);
            stdin.close();
            p.waitFor();
        } catch (Exception e) {
            e.printStackTrace();
        }

        if (OS_name.contains("windows")) {
            Path path = Paths.get("C:\\Download_Video_Youtube\\youtube-dl.exe");
            Path path1 = Paths.get("C:\\Download_Video_Youtube\\youtube-dl.exe.new");
            //set hidden attribute
            try {
                Files.setAttribute(path, "dos:hidden", true, LinkOption.NOFOLLOW_LINKS);
                Files.setAttribute(path1, "dos:hidden", true, LinkOption.NOFOLLOW_LINKS);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }else{
            String userName = System.getProperty("user.name");
            Path path = Paths.get("/home/"+userName+"/Download_Video_Youtube/youtube-dl.exe");
            Path path1 = Paths.get("/home/"+userName+"/Download_Video_Youtube/youtube-dl.exe.new");
            //set hidden attribute
            try {
                Files.setAttribute(path, "dos:hidden", true, LinkOption.NOFOLLOW_LINKS);
                Files.setAttribute(path1, "dos:hidden", true, LinkOption.NOFOLLOW_LINKS);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        File file = new File(download_path);
        File[] files = file.listFiles();
        String userName = System.getProperty("user.name");
        for (File file1 : files) {
            if (!file1.getName().contains(".java") && !file1.getName().equals("static/youtube-dl.exe")) {
                if (OS_name.contains("Windows")) {
                    if (!file1.getName().contains("youtube-dl.exe")) {
                        file1.renameTo(new File("C:\\Download_Video_Youtube\\video\\" + file1.getName()));
                    }
                } else {
                    if (!new File("/home/" + userName + "/Download_Video_Youtube/video/" + file1.getName()).exists() && !file1.getName().contains("youtube-dl.exe")) {
                        file1.renameTo(new File("/home/" + user_Name + "/Download_Video_Youtube/video/" + file1.getName()));
                    }

                }
            }
        }
    }
}