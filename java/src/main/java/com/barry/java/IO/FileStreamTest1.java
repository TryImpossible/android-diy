package com.barry.java.IO;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public class FileStreamTest1 {
    public static void main(String args[]) {
        try {
            String path = String.format("C:%sUsers%stry%sDesktop%sfile%s1.txt", File.separator, File.separator, File.separator, File.separator, File.separator
            );
            File file = new File(path);

            byte bWrite[] = {11, 21, 3, 40, 5};
            OutputStream os = new FileOutputStream(file);
            for (int i = 0; i < bWrite.length; i++) {
                os.write(bWrite[i]);
            }
            os.close();

            InputStream is = new FileInputStream(file);
            int size = is.available();
            for (int j = 0; j < size; j++) {
                System.out.println((char)is.read() + " ");
            }
            is.close();
        } catch (IOException e) {
            System.out.println("Exception");
        }
    }
}
