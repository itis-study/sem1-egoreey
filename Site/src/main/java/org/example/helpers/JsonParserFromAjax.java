package org.example.helpers;

import lombok.Getter;
import org.json.JSONException;
import org.json.JSONObject;

import javax.servlet.http.HttpServletRequest;
import java.io.BufferedReader;
import java.io.IOException;

public class JsonParserFromAjax {
    public static String getJson(HttpServletRequest request, String name) throws IOException {
        StringBuffer sb = new StringBuffer();
        BufferedReader br = request.getReader();
        String line;
        String email = "";
        while ((line = br.readLine()) != null){
            sb.append(line);
        }
        System.out.println(sb.toString());
        try {
            JSONObject jsonObject =  new JSONObject(sb.toString());
            email = jsonObject.getString(name);
            System.out.println(email);
        } catch (JSONException e) {
            //throw new IOException("Error parsing JSON request string");
        }
        return email;
    }
}
