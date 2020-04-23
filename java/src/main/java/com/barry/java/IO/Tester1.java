package com.barry.java.IO;

import java.io.File;

public class Tester1 {
    public static void main(String args[]) {
        String path = String.format("C:%sUsers%stry%sDesktop%sfile%s1.txt", File.separator, File.separator, File.separator, File.separator, File.separator
        );
        File file = new File(path);
    }
}
