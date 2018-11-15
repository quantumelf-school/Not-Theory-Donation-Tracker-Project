package nottheory.donationtracker.Model;

import android.util.Log;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.Reader;


import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.LinkedHashMap;
import java.util.Map;


class DatabaseConnection {

    public static String sendRawSQL(String SQL) throws Exception {
        URL url = new URL("http://nottheoryapi.pythonanywhere.com/");
        Map<String,Object> params = new LinkedHashMap<>();
        params.put("sql", SQL);

        StringBuilder postData = new StringBuilder(); //uses builder design pattern
        for (Map.Entry<String,Object> param : params.entrySet()) {
            if (postData.length() != 0) { postData.append('&'); }
            postData.append(URLEncoder.encode(param.getKey(), "UTF-8"));
            postData.append('=');
            postData.append(URLEncoder.encode(String.valueOf(param.getValue()), "UTF-8"));
        }
        String post = postData.toString();
        byte[] postDataBytes = post.getBytes("UTF-8");

        HttpURLConnection conn = (HttpURLConnection)url.openConnection();
        conn.setRequestMethod("POST");
        conn.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
        conn.setRequestProperty("Content-Length", String.valueOf(postDataBytes.length));
        conn.setDoOutput(true);
        OutputStream output = conn.getOutputStream();
        output.write(postDataBytes);

        try {
            Reader in = new BufferedReader(new InputStreamReader(conn.getInputStream(), "UTF-8"));

            StringBuilder sb = new StringBuilder();
            for (int c = in.read(); c >= 0; c = in.read()) {
                sb.append((char)c);
            }

            return sb.toString();
        } catch (Exception e) {
            return "";
        }
    }
}

