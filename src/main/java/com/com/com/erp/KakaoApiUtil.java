package com.com.com.erp;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.stereotype.Service;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class KakaoApiUtil {
	private static final String REST_API_KEY = "ef3ca41398cd7425a003013716d178c9";
	
	public String[] getAddressPoint(String address) {
	    String apiUrl = "https://dapi.kakao.com/v2/local/search/address.json?query=" + address;
	    String auth = "KakaoAK " + REST_API_KEY;
	    HttpURLConnection conn = null;
	    BufferedReader reader = null;
	    StringBuilder response = new StringBuilder();
	    
	    try {
	        URL url = new URL(apiUrl);
	        conn = (HttpURLConnection) url.openConnection();
	        conn.setRequestMethod("GET");
	        conn.setRequestProperty("Authorization", auth);

	        // 응답 코드 확인
	        int responseCode = conn.getResponseCode();
	        if (responseCode == 200) { 
	            reader = new BufferedReader(new InputStreamReader(conn.getInputStream()));
	            String line;
	            while ((line = reader.readLine()) != null) {
	                response.append(line);
	            }

	            // JSON 파싱
	            JSONObject jsonResponse = new JSONObject(response.toString());
	            JSONArray documents = jsonResponse.getJSONArray("documents");

	            // documents 배열이 비어 있는지 확인
	            if (documents.length() == 0) {
	            	log.info("널에걸림");
	                return null; // 오류 처리
	            }

	            JSONObject addressInfo = documents.getJSONObject(0).getJSONObject("address");
	            String lng = addressInfo.getString("x");
	            String lat = addressInfo.getString("y");
	            log.info(lat,lng);
	            // 좌표를 배열로 반환
	            return new String[]{lat, lng};

	        } else {
	            return null; // 오류 처리
	        }

	    } catch (Exception e) {
	        e.printStackTrace();
	        return null; // 오류 처리
	    } finally {
	        try {
	            if (reader != null) {
	                reader.close();
	            }
	            if (conn != null) {
	                conn.disconnect();
	            }
	        } catch (Exception ex) {
	            ex.printStackTrace();
	        }
	    }
	}
}
