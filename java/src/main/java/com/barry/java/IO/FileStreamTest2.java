package com.barry.java.IO;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class FileStreamTest2 {
    public static void main(String args[]) {
        try {
            String path = String.format("C:%sUsers%stry%sDesktop%sfile%s1.txt", File.separator, File.separator, File.separator, File.separator, File.separator
            );
            File file = new File(path);
            // 构建FileOutputStream对象，文件不存在会自动创建
            FileOutputStream fop = new FileOutputStream(file);

            // 构建OutputStreamWriter对象，参数可以指定编码，默认为操作系统默认编码，windows上是gbk
            OutputStreamWriter writer = new OutputStreamWriter(fop, "utf-8");

            // 写入缓冲区
            writer.append("中文输入");

            // 换行
            writer.append("\r\n");

            // 刷新缓存冲，写入到文件，如果下面已经没有写入的内容了，直接close也会写入
            writer.append("English");

            // 关闭写入流，同时也会把缓冲区内容写入文件，所以上面的注释
            writer.close();

            // 关闭输出流，释放系统资源
            fop.close();

            // 构建FileInputStream对象
            FileInputStream fip = new FileInputStream(file);

            // 构建InputStreamReader对象，编码写入相同
            InputStreamReader reader = new InputStreamReader(fip, "utf-8");

            StringBuffer sb = new StringBuffer();
            while (reader.ready()) {
                sb.append((char) reader.read());
            }
            System.out.println(sb.toString());

            // 关闭读取流
            reader.close();

            // 关闭输入流，释放系统资源
            fip.close();
        } catch (IOException e) {

        }
    }
}
