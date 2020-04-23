package com.barry.java.IO;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class BRRead {
    public static void main(String args[]) throws IOException {
        char c;
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        System.out.println("输入字符，按下 ‘q’ 退出");
        do {
            c = (char) br.read();
            System.out.println(c);
        } while (c != 'q');
    }
}
