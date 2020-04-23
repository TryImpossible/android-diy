package com.barry.java.IO;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class DataInputStreamTest {
    public static void main(String args[]) throws IOException {
        String path = String.format("C:%sUsers%stry%sDesktop%sfile%s1.txt", File.separator, File.separator, File.separator, File.separator, File.separator
        );
        String path2 = String.format("C:%sUsers%stry%sDesktop%sfile%s2.txt", File.separator, File.separator, File.separator, File.separator, File.separator
        );

        DataInputStream in = new DataInputStream(new FileInputStream(new File(path)));

        DataOutputStream out = new DataOutputStream(new FileOutputStream(new File(path2)));
        BufferedReader br = new BufferedReader(new InputStreamReader(in, "utf-8"));

        String count;
        while ((count = br.readLine()) != null) {
            String u = count.toUpperCase();
            System.out.println(u);
            out.writeUTF(u + "  ,");
        }
        br.close();
        out.close();
    }
}
