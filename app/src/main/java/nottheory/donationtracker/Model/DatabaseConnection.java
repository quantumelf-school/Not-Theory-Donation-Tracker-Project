package nottheory.donationtracker.Model;

import java.io.*;
import java.net.*;
import java.util.*;


class DatabaseConnection {

    public static String sendRawSQL(String SQL) throws Exception {
        URL url = new URL("http://nottheoryapi.pythonanywhere.com/");
        Map<String,Object> params = new LinkedHashMap<>();
        params.put("sql", SQL);

        StringBuilder postData = new StringBuilder(); //uses builder design pattern
        for (Map.Entry<String,Object> param : params.entrySet()) {
            if (postData.length() != 0) postData.append('&');
            postData.append(URLEncoder.encode(param.getKey(), "UTF-8"));
            postData.append('=');
            postData.append(URLEncoder.encode(String.valueOf(param.getValue()), "UTF-8"));
        }
        byte[] postDataBytes = postData.toString().getBytes("UTF-8");

        HttpURLConnection conn = (HttpURLConnection)url.openConnection();
        conn.setRequestMethod("POST");
        conn.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
        conn.setRequestProperty("Content-Length", String.valueOf(postDataBytes.length));
        conn.setDoOutput(true);
        conn.getOutputStream().write(postDataBytes);

        try {
            Reader in = new BufferedReader(new InputStreamReader(conn.getInputStream(), "UTF-8"));

            StringBuilder sb = new StringBuilder();
            for (int c; (c = in.read()) >= 0;) {
                sb.append((char)c);
            }
            String response = sb.toString();

            return response;
        } catch (Exception e) {}
        return "";
    }
}

