package com.barry.java.IO;

import java.io.File;

public class DeleteFile {
    public static void main(String args[]) {
        // 这里修改为自己的测试目录
        String path = String.format("C:%sUsers%stry%sDesktop%sfile%s1.txt", File.separator, File.separator, File.separator, File.separator, File.separator
        );
        File file = new File(path);
        deleteFolder(file);
    }

    // 删除文件及目录
    public static void deleteFolder(File folder) {
        File[] files = folder.listFiles();
        if (files != null) {
            for (File f : files) {
                if (f.isDirectory()) {
                    deleteFolder(f);
                } else {
                    f.delete();
                }
            }
        }
        folder.delete();
    }
}
