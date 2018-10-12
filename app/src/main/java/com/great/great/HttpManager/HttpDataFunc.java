package com.great.great.HttpManager;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

/**
 * Http通讯相关数据处理类
 */
public class HttpDataFunc {

    public static String InputStreamToString(InputStream is)throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(is));
        StringBuilder sb = new StringBuilder();
        String line;
        while ((line = reader.readLine()) != null){
            sb.append(line);
            sb.append("\n");
        }
        return sb.toString();
    }

    public static String getTimestamp(){
        long time = System.currentTimeMillis();
        String t = String.valueOf(time/1000);
        return t;
    }

    public static JSONObject StringToJSONObject(String data) {
        JSONObject jo = null;
        try {
            jo = new JSONObject(data);
        }catch (Exception e){
            e.printStackTrace();
        }
        return jo;
    }

    public static JSONArray JSONObjectToJSONArray(JSONObject jo,String type) {
        JSONArray jA = null;
        try {
            jA = jo.getJSONArray(type);
        }catch (Exception e){
            e.printStackTrace();
        }
        return jA;
    }


}
