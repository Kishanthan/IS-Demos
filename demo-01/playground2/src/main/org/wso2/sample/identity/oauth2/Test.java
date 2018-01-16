/*
 *  Copyright (c) 2018, WSO2 Inc. (http://www.wso2.org) All Rights Reserved.
 *
 *  WSO2 Inc. licenses this file to you under the Apache License,
 *  Version 2.0 (the "License"); you may not use this file except
 *  in compliance with the License.
 *  You may obtain a copy of the License at
 *
 *  http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing,
 *  software distributed under the License is distributed on an
 *  "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 *  KIND, either express or implied.  See the License for the
 *  specific language governing permissions and limitations
 *  under the License.
 */
package org.wso2.sample.identity.oauth2;

import com.google.gson.Gson;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

public class Test {
    public static void main(String[] args) throws Exception {
//        URL url = new URL("http://localhost:8290/booking/1.0.0/view/AA-202");
//        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
//        conn.setRequestMethod("GET");
//        conn.setRequestProperty("Accept", "application/json");
//        conn.setRequestProperty("Authorization", "Bearer " + "6cb784d4-f147-3538-a20e-348d5c8a14c9");
//
//        if (conn.getResponseCode() != 200) {
//            throw new RuntimeException("Failed : HTTP error code : "
//                    + conn.getResponseCode());
//        }
//
//        StringBuilder sb = new StringBuilder();
//
//        BufferedReader br = new BufferedReader(new InputStreamReader((conn.getInputStream())));
//
//        String output;
//        System.out.println("Output from Server .... \n");
//        while ((output = br.readLine()) != null) {
//            System.out.println(output);
//            sb.append(output);
//        }
//        conn.disconnect();
////        JSONObject json = new JSONObject(sb.toString());
//
//        Gson gson = new Gson();
//        BookingInfo bo = gson.fromJson(sb.toString(), BookingInfo.class);

        URL website = new URL("http://localhost:8080/flight-booking/view/AA-102");
        URLConnection connection = website.openConnection();
        connection.setRequestProperty("Authorization", "Bearer " + "6cb784d4-f147-3538-a20e-348d5c8a14c9");
        BufferedReader in = new BufferedReader(
                new InputStreamReader(
                        connection.getInputStream()));

        StringBuilder response = new StringBuilder();
        String inputLine;

        while ((inputLine = in.readLine()) != null)
            response.append(inputLine);

        in.close();

        response.toString();
    }
}
