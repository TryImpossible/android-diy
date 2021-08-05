package com.barry.java.Thread;

import java.io.IOException;
import java.io.PipedReader;
import java.io.PipedWriter;

public class TestPip {
    public static void main(String[] args) throws IOException {
        PipedWriter writer = new PipedWriter();
        PipedReader reader = new PipedReader();
        // 使用connect方法将输入流和输出流连接起来
        writer.connect(reader);

        Thread printThread = new Thread(new Print(reader), "PrintThread");
        // 启动线程printThread
        printThread.start();

        int receive = 0;
        try {
            // 读取输入的内容
            while ((receive = System.in.read())!= -1) {
                writer.write(receive);
            }
        } finally {
             writer.close();
        }
    }

    private static class Print implements Runnable {
        private PipedReader reader;

        public Print(PipedReader reader) {
            this.reader = reader;
        }

        @Override
        public void run() {
            int receive = 0;
            while (true) {
                try {
                    if ((receive = reader.read()) != -1) {
                        // 字符转换
                        System.out.print((char) receive);
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
