package com.barry.java.Base64;

import java.io.UnsupportedEncodingException;
import java.util.Base64;
import java.util.UUID;

/**
 * 在Java 8中，Base64编码已经成为Java类库的标准。
 * <p>
 * Java 8 内置了 Base64 编码的编码器和解码器。
 */
public class Tester1 {


    public static void main(String args[]) {
        try {
            // 使用基本编码
            String base64encodedString = Base64.getEncoder().encodeToString("barry?java8".getBytes("utf-8"));
            System.out.println("Base64 编码字符串（基本）：" + base64encodedString);

            // 解码
            byte[] base64decodedBytes = Base64.getDecoder().decode(base64encodedString);
            System.out.println("原始字符串：" + new String(base64decodedBytes, "utf-8"));

            base64encodedString = Base64.getUrlEncoder().encodeToString("barry?java8".getBytes("utf-8"));
            System.out.println("Base64 编码字符串（URL）：" + base64encodedString);

            StringBuilder stringBuilder = new StringBuilder();
            for (int i = 0; i < 10; ++i) {
                stringBuilder.append(UUID.randomUUID().toString());
            }
            byte[] mimeBytes = stringBuilder.toString().getBytes();
            String mimeEncodedString = Base64.getMimeEncoder().encodeToString(mimeBytes);
            System.out.println("Base64 编码字符串（MIME）:" + mimeEncodedString);

        } catch (UnsupportedEncodingException e) {
            System.out.println("Error：" + e.getMessage());
        }
    }
}
