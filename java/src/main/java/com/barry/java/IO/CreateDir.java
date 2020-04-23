package com.barry.java.IO;

import java.io.File;

public class CreateDir {
    public static void main(String args[]) {
        String dirname = "C:\\Users\\try\\Desktop\\temp";
        File d = new File(dirname);
        d.mkdirs();
    }
}
