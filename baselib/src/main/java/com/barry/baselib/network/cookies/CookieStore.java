package com.barry.baselib.network.cookies;

import android.content.Context;
import android.content.SharedPreferences;
import android.text.TextUtils;
import android.util.Log;

import com.barry.baselib.utils.LogUtils;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import okhttp3.Cookie;
import okhttp3.HttpUrl;

public class CookieStore {
    private static final String COOKIE_SHARED_PREFERENCES = "Cookie_Shared_Preferences";

    private static volatile CookieStore cookieStore;
    private final Map<String, ConcurrentHashMap<String, Cookie>> cookies;
    private final SharedPreferences cookieSharedPreferences;

    public static void init(Context context) {
        if (cookieStore == null) {
            synchronized (CookieStore.class) {
                if (cookieStore == null) {
                    cookieStore = new CookieStore(context);
                }
            }
        }
    }

    public static CookieStore getInstance() {
        return cookieStore;
    }

    private CookieStore(Context context) {
        cookies = new HashMap<>();
        cookieSharedPreferences = context.getSharedPreferences(COOKIE_SHARED_PREFERENCES, Context.MODE_PRIVATE);

        // 将持久化的cookies缓存到内存中
        Map<String, ?> prefsMap = cookieSharedPreferences.getAll();
        for (Map.Entry<String, ?> entry : cookies.entrySet()) {
            String[] cookieNames = TextUtils.split((String) entry.getValue(), ",");
            for (String name : cookieNames) {
                String encodedCookie = cookieSharedPreferences.getString(name, null);
                if (encodedCookie != null) {
                    Cookie decodedCookie = decodedCookie(encodedCookie);
                    if (decodedCookie != null) {
                        if (!cookies.containsKey(entry.getKey())) {
                            cookies.put(entry.getKey(), new ConcurrentHashMap<String, Cookie>());
                        }
                        cookies.get(entry.getKey()).put(name, decodedCookie);
                    }
                }
            }
        }
    }

    private String getCookieToken(Cookie cookie) {
        return cookie.name() + "@" + cookie.domain();
    }

    public void add(HttpUrl url, Cookie cookie) {
        String name = getCookieToken(cookie);
        // 将cookies缓存到内存中，如何缓存过期则重置此cookie
        if (!cookie.persistent()) {
            if (!cookies.containsKey(url.host())) {
                cookies.put(url.host(), new ConcurrentHashMap<String, Cookie>(10));
            }
        } else {
            if (cookies.containsKey(url.host())) {
                cookies.get(url.host()).remove(name);
            }
        }

        // 将cookies持久化到本地
        SharedPreferences.Editor editor = cookieSharedPreferences.edit();
        editor.putString(url.host(), TextUtils.join(",", cookies.get(url.host()).keySet()));
        editor.putString(name, encodeCookie(new OkHttpCookies(cookie)));
        editor.apply();
    }

    public boolean remove(HttpUrl url, Cookie cookie) {
        String name = getCookieToken(cookie);
        if (cookies.containsKey(url.host()) && cookies.get(url.host()).contains(name)) {
            cookies.get(url.host()).remove(name);
            SharedPreferences.Editor editor = cookieSharedPreferences.edit();
            if (cookieSharedPreferences.contains(name)) {
                editor.remove(name);
            }
            editor.putString(url.host(), TextUtils.join(",", cookies.get(url.host()).keySet()));
            editor.apply();
            return true;
        } else {
            return false;
        }
    }

    public List<Cookie> get(HttpUrl url) {
        ArrayList<Cookie> ret = new ArrayList<>();
        if (cookies.containsKey(url.host())) {
            ret.addAll(cookies.get(url.host()).values());
        }
        return ret;
    }

    public List<Cookie> getCookies() {
        ArrayList<Cookie> ret = new ArrayList<>();
        for (String key : cookies.keySet()) {
            ret.addAll(cookies.get(key).values());
        }
        return ret;
    }

    public void removeAll() {
        SharedPreferences.Editor editor = cookieSharedPreferences.edit();
        editor.clear();
        editor.apply();
        cookies.clear();
    }

    /**
     * cookies 序列化成 string
     *
     * @param cookie 要序列化的cookie
     * @return 序列化之后的string
     */
    private String encodeCookie(OkHttpCookies cookie) {
        if (cookie == null) {
            return null;
        }
        ByteArrayOutputStream os = new ByteArrayOutputStream();
        try {
            ObjectOutputStream outputStream = new ObjectOutputStream(os);
            outputStream.writeObject(cookie);
        } catch (IOException e) {
            LogUtils.e("IOException in encodeCookie", e);
            return null;
        }
        return byteArrayToHexString(os.toByteArray());
    }

    /**
     * 将字符串反序列化成cookies
     *
     * @param cookieString cookies string
     * @return cookie object
     */
    private Cookie decodedCookie(String cookieString) {
        byte[] bytes = hexStringToByteArray(cookieString);
        ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(bytes);
        Cookie cookie = null;
        try {
            ObjectInputStream objectInputStream = new ObjectInputStream(byteArrayInputStream);
            cookie = ((OkHttpCookies) objectInputStream.readObject()).getCookies();
        } catch (IOException e) {
            LogUtils.e("IOException in decodeCookie", e);
        } catch (ClassNotFoundException e) {
            LogUtils.e("ClassNotFoundException in decodeCookie", e);
        }
        return cookie;
    }

    /**
     * 二进制数组转十六进制字符串
     *
     * @param bytes byte array to be converted
     * @return string containing hex values
     */
    private String byteArrayToHexString(byte[] bytes) {
        StringBuilder sb = new StringBuilder(bytes.length * 2);
        for (byte element : bytes) {
            int v = element & 0xff;
            if (v < 16) {
                sb.append('0');
            }
            sb.append(Integer.toHexString(v));
        }
        return sb.toString().toUpperCase(Locale.US);
    }

    /**
     * 十六进制字符串转二进制数组
     *
     * @param hexString string of hex-encoded values
     * @return decoded byte array
     */
    private byte[] hexStringToByteArray(String hexString) {
        int len = hexString.length();
        byte[] data = new byte[len / 2];
        for (int i = 0; i < len; i += 2) {
            data[i / 2] = (byte) ((Character.digit(hexString.charAt(i), 16) << 4) + Character.digit(hexString.charAt(i + 1), 16));
        }
        return data;
    }
}
