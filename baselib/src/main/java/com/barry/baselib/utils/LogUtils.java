/*
 *
 * *******************************************************************
 *   @项目名称: BHex Android
 *   @文件名称: DebugLog.java
 *   @Date: 11/29/18 3:21 PM
 *   @Author: chenjun
 *   @Copyright（C）: 2018 BlueHelix Inc.   All rights reserved.
 *   注意：本内容仅限于内部传阅，禁止外泄以及用于其他的商业目的.
 *  *******************************************************************
 *
 */

package com.barry.baselib.utils;

import android.util.Log;


/**
 * ================================================
 * @description 封装一个Log的类，简化log的处理
 * @author barry
 * create at 2021/3/15 12:37
 * ================================================
 */
public class LogUtils {

    /**
     * 类名
     */
    private static String className;

    /**
     * 方法名
     */
    private static String methodName;

    /**
     * 行数
     */
    private static int lineNumber;

    private static boolean isDebuggable = false;

    private LogUtils() {
    }

    /**
     * 使用前先打开调试开关
     *
     * @param debuggable
     */
    public static void setDebuggable(boolean debuggable) {
        isDebuggable = debuggable;
    }

    public static boolean isDebuggable() {
        return isDebuggable;
    }

    private static String createLog(String log) {

        StringBuffer buffer = new StringBuffer();
        buffer.append("[");
        buffer.append(methodName);
        buffer.append(":");
        buffer.append(lineNumber);
        buffer.append("]");
        buffer.append(log);

        return buffer.toString();
    }

    private static void getMethodNames(StackTraceElement[] sElements) {
        className = sElements[3].getFileName();
        methodName = sElements[3].getMethodName();
        lineNumber = sElements[3].getLineNumber();
    }

    public static void e(String message) {

        if (!isDebuggable())
            return;
        getMethodNames(Thread.currentThread().getStackTrace());
        Log.e(className, createLog(message));
    }

    public static void e(Throwable e) {
        if (!isDebuggable())
            return;

        getMethodNames(Thread.currentThread().getStackTrace());
        Log.e(className, createLog(String.format("Exception: %s. Caused by %s. Detail message: %s", e.toString(), e.getCause(), e.getMessage())));
    }

    public static void i(String message) {
        if (!isDebuggable())
            return;

        getMethodNames(Thread.currentThread().getStackTrace());
        Log.i(className, createLog(message));
    }

    public static void d(String message) {
        if (!isDebuggable())
            return;

        getMethodNames(Thread.currentThread().getStackTrace());
        Log.d(className, createLog(message));
    }

    public static void v(String message) {
        if (!isDebuggable())
            return;
        getMethodNames(Thread.currentThread().getStackTrace());
        Log.v(className, createLog(message));
    }

    public static void w(String message) {
        if (!isDebuggable())
            return;

        getMethodNames(Thread.currentThread().getStackTrace());
        Log.w(className, createLog(message));
    }

    public static void wtf(String message) {
        if (!isDebuggable())
            return;

        getMethodNames(Thread.currentThread().getStackTrace());
        Log.wtf(className, createLog(message));
    }

    public static void e(String message, Throwable tr) {
        if (!isDebuggable())
            return;
        getMethodNames(Thread.currentThread().getStackTrace());
        Log.e(className, createLog(message), tr);
    }

    public static void wtf(String message, Throwable tr) {
        if (!isDebuggable())
            return;
        getMethodNames(Thread.currentThread().getStackTrace());
        Log.wtf(className, createLog(message), tr);
    }

    /********************
     * 一般调试
     *********************/
    public static void v(String tag, String msg) {
        if (!isDebuggable() || msg == null)
            return;
        Log.v(tag, msg);

    }

    public static void d(String tag, String msg) {
        if (!isDebuggable() || msg == null)
            return;
        Log.d(tag, msg);

    }

    public static void i(String tag, String msg) {
        if (!isDebuggable() || msg == null)
            return;
        Log.i(tag, msg);

    }

    public static void w(String tag, String msg) {
        if (!isDebuggable() || msg == null)
            return;
        Log.w(tag, msg);

    }

    public static void e(String tag, String msg) {
        if (!isDebuggable() || msg == null)
            return;
        Log.e(tag, msg);

    }

    public static void e(String tag, String msg, Throwable tr) {
        if (!isDebuggable() || msg == null)
            return;
        Log.e(tag, msg, tr);
    }

    public static void wtf(String tag, String msg, Throwable tr) {
        if (!isDebuggable() || msg == null)
            Log.wtf(tag, msg, tr);
    }
    /******************** end *********************/
}

