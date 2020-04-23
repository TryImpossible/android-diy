package com.barry.java.File;

import java.io.File;
import java.io.IOException;

public class Test1 {
    public static void main(String args[]) throws IOException {
        String path = String.format("C:%sUsers%stry%sDesktop%sfile%s1.txt", File.separator, File.separator, File.separator, File.separator, File.separator
        );
        String path2 = String.format("C:%sUsers%stry%sDesktop%sfile%s2.txt", File.separator, File.separator, File.separator, File.separator, File.separator
        );

        File file = new File(path);
        // 创建父级目录
        if (!file.getParentFile().exists()) {
            file.getParentFile().mkdirs();
        }
        // 创建文件
        if (!file.exists()) {
            file.createNewFile();
        }
        System.out.println("当前文件或目录的名称：" + file.getName());
        System.out.println("父目录名称：" + file.getParent());
        System.out.println("父目录的File对象：" + file.getParentFile());
        System.out.println("当前文件或目录的路径：" + file.getPath());
        System.out.println("当前文件或目录是否是绝对路径：" + file.isAbsolute());
        System.out.println("当前文件或目录的绝对路径：" + file.getAbsolutePath());
        System.out.println("当前文件或目录是否可读：" + file.canRead());
        System.out.println("当前文件或目录是否可写：" + file.canWrite());
        System.out.println("当前文件或目录是否可执行：" + file.canExecute());
        System.out.println("当前路径是否目录：" + file.isDirectory());
        System.out.println("当前路径是否文件：" + file.isFile());
        System.out.println("当前文件或目录的最后一次修改时间：" + file.lastModified());
        System.out.println("当前文件的长度：" + file.length());
//        System.out.println("删除当前文件或目录：" + file.delete());
        System.out.println("表示的目录中的文件和目录的名称所组成字符串数组：" + file.getParentFile().list());
        System.out.println("表示的目录中的文件和目录的名称所组成File数组：" + file.getParentFile().listFiles());
//        System.out.println("重新命名当前文件或目录：" + file.renameTo(new File(path2)));
    }
}
