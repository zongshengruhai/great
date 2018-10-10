package com.great.great.HttpManager;

import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * HttpURL类HTTP通讯方法
 */
public class HttpUrlFunc {

    /**
     * HttpUrlGET 方法
     */
    public static String HttpUrlGET(String url){
        String s = null;
        try {
            //HTTP链接
            HttpURLConnection conn = (HttpURLConnection)new URL(url).openConnection();
            //设置链接请求方式
            conn.setRequestMethod("GET");
            //设置链接超时时间
            conn.setConnectTimeout(5000);
            if (conn.getResponseCode() != 200){
                return null;
            }
            InputStream in = conn.getInputStream();
            s = HttpDataFunc.InputStreamToString(in);
            in.close();
        }catch (Exception e){
            e.printStackTrace();
        }
        return s;
    }


}
