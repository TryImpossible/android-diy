package com.barry.java.IO;

import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;

public class RandomAccessFileTest {
    public static void main(String args[]) throws IOException {
        String path = String.format("C:%sUsers%stry%sDesktop%sfile%s3.txt", File.separator, File.separator, File.separator, File.separator, File.separator
        );
        File file = new File(path);

        RandomAccessFile raf = new RandomAccessFile(file, "rw");
        raf.writeUTF("随机访问文件输出UTF-8");
        raf.seek(0);
        String str = raf.readUTF();

        raf.seek(8);
        byte[] bytes = new byte[12];
        raf.read(bytes);
        String s = new String(bytes);
        System.out.println(s);

        raf.seek(0);
        raf.writeUTF("访问文件RandomAccessFile");

        raf.seek(0);
        byte[] bytes2 = new byte[14];
        raf.read(bytes2);
        String s2 = new String(bytes2);
        System.out.println(s2);

        raf.seek(2);
        byte[] bytes3 = new byte[12];
        raf.read(bytes3);
        String s3 = new String(bytes3);
        System.out.println(s3);
        raf.close();
    }
}
