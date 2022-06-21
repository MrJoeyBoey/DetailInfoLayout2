package com.j.detailinfolayout2.utils;

public class FileUtil {

    public static String getFileExtension(String fileName) {
        return fileName.substring(fileName.lastIndexOf("."));
    }

}
