package com.portpolio.aragada;

import android.os.AsyncTask;
import android.os.Build;
import android.util.Log;
import android.widget.TextView;

import androidx.annotation.RequiresApi;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;

public class PapagoTranslate{


    public String getTranslation(String word, String source, String target) {
        String clientId = "kB2Qx0mmIAKw0DyBGPIm";//애플리케이션 클라이언트 아이디값";
        String clientSecret = "SssvFEWF_C";//애플리케이션 클라이언트 시크릿값";

        try {
            String wordSource, wordTarget;
            String text = URLEncoder.encode(word, "UTF-8");//word
            wordSource = URLEncoder.encode(source, "UTF-8");
            wordTarget = URLEncoder.encode(target, "UTF-8");

            String apiURL = "https://openapi.naver.com/v1/papago/n2mt";
            URL url = new URL(apiURL);
            HttpURLConnection con = (HttpURLConnection) url.openConnection();
            con.setRequestMethod("POST");
            con.setRequestProperty("X-Naver-Client-Id", clientId);
            con.setRequestProperty("X-Naver-Client-Secret", clientSecret);
            String postParams = "source="+wordSource+"&target="+wordTarget+"&text=" + text;
            con.setDoOutput(true);
            DataOutputStream wr = new DataOutputStream(con.getOutputStream());
            wr.writeBytes(postParams);
            wr.flush();
            wr.close();
            int responseCode = con.getResponseCode();
            BufferedReader br;
            if (responseCode == 200) { // 정상 호출
                br = new BufferedReader(new InputStreamReader(con.getInputStream()));
            } else{ // 에러 발생
                br = new BufferedReader(new InputStreamReader(con.getErrorStream()));
            }
            String inputLine;
            StringBuffer response = new StringBuffer();
            while ((inputLine = br.readLine()) != null) {
                response.append(inputLine);
            }
            br.close();
//System.out.println(response.toString());
            String s = response.toString();
            s = s.split("\"")[15];
            Log.d("suc", "getTranslation: " + response.toString());
            return s;
        } catch (Exception e) {
            e.printStackTrace();
        }
        Log.d("fa", "getTranslation: ");
        return "0";
    }
}
