package com.barry.java.DateTime;

import java.time.ZoneId;
import java.time.ZonedDateTime;

public class Tester2 {
    public void testZonedDateTime() {
        // 获取当前时间日期
        ZonedDateTime date1 = ZonedDateTime.parse("2015-12-03T10:15:30+05:30[Asia/Shanghai]");
        System.out.println("date1：" + date1);

        ZoneId id = ZoneId.of("Europe/Paris");
        System.out.println("ZoneId：" + id);

        ZoneId currentZone = ZoneId.systemDefault();
        System.out.println("当前时区：" + currentZone);
    }

    public static void main(String args[]) {
        Tester2 tester2 = new Tester2();
        tester2.testZonedDateTime();
    }
}
