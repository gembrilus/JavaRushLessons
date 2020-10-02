package com.javarush.task.task40.task4002;

import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.utils.URLEncodedUtils;
import org.apache.http.impl.client.HttpClientBuilder;

import java.nio.charset.Charset;
import java.util.List;

/* 
Опять POST, а не GET
*/

public class Solution {
    public static void main(String[] args) throws Exception {
        Solution solution = new Solution();
        solution.sendPost("http://requestb.in/1h4qhvv1", "name=zapp&mood=good&locale=&id=777");
    }

    public void sendPost(String url, String urlParameters) throws Exception {
        HttpClient client = getHttpClient();
        HttpPost post = new HttpPost(url);

        post.addHeader("User-Agent", "Mozilla/5.0");

        List<NameValuePair> params = URLEncodedUtils.parse(urlParameters, Charset.defaultCharset());
        post.setEntity(new UrlEncodedFormEntity(params));

        HttpResponse response = client.execute(post);
        System.out.println("Response Code: " + response.getStatusLine().getStatusCode());
    }

    protected HttpClient getHttpClient() {
        return HttpClientBuilder.create().build();
    }
}
