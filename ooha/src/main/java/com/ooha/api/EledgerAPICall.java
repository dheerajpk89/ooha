package com.ooha.api;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.springframework.stereotype.Service;

@Service
public class EledgerAPICall {
	private HttpPost postRequest;

    private CloseableHttpClient httpClient;
    
    public String eledgerPost(String URL,String requestJson)
             {
            try {
                httpClient = HttpClientBuilder.create().build();
                postRequest = new HttpPost(URL);
                final StringEntity input = new StringEntity(requestJson);
                input.setContentType("application/json");
                postRequest.setEntity(input);
                final HttpResponse response = httpClient.execute(postRequest);

                if (response.getStatusLine().getStatusCode() != 200) {
                   System.out.println("Failed : HTTP error code : " + response.getStatusLine().getStatusCode());
                }
                final BufferedReader br = new BufferedReader(new InputStreamReader((response.getEntity().getContent())));
                String output = null;
                String val = "";
                while ((output = br.readLine()) != null) {
                    val += output;
                }
                System.out.println(">>>"+val);
                httpClient.close();
                return val;
            } catch (IOException e) {
                e.printStackTrace();
                return null;
            }

        }
    
}
