package com.example.cashregister.security;

import javax.json.Json;
import javax.json.JsonObject;
import javax.json.JsonReader;
import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
/**
 * Class for reCaptcha
 * */

public class Captcha {

    public static final String SITE_VERIFY_URL = "https://www.google.com/recaptcha/api/siteverify";

    public static boolean verify(String gRecaptchaResponse) {
        if (gRecaptchaResponse == null || gRecaptchaResponse.length() == 0) {
            return false;
        }

        try {
            URL verifyUrl = new URL(SITE_VERIFY_URL);
            HttpURLConnection conn = (HttpURLConnection) verifyUrl.openConnection();
            conn.setRequestMethod("POST");
            conn.setRequestProperty("User-Agent", "Mozilla/5.0");
            conn.setRequestProperty("Accept-Language", "en-US,en;q=0.5");
            String postParams = "secret=" + "6LdAPwwhAAAAANc9uJNakzU5I_XyYMM0MiwtJoT5"
                    + "&response=" + gRecaptchaResponse;

            conn.setDoOutput(true);
            OutputStream outStream = conn.getOutputStream();
            outStream.write(postParams.getBytes());
            outStream.flush();
            outStream.close();

            BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            String inputLine;
            StringBuffer response = new StringBuffer();
            while ((inputLine= in.readLine()) != null) {
                response.append(inputLine);
            }
            in.close();
            JsonReader jsonReader = Json.createReader(new StringReader(response.toString()));
            JsonObject jsonObject = jsonReader.readObject();
            jsonReader.close();

            return jsonObject.getBoolean("success");
        }catch(Exception e){
            e.printStackTrace();
            return false;
        }
    }
}