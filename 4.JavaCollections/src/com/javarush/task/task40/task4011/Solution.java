package com.javarush.task.task40.task4011;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

/* 
Свойства URL
*/

public class Solution {
    public static void main(String[] args) throws IOException {
        decodeURLString("https://www.amrood.com/index.htm?language=en#j2se");
    }

    public static void decodeURLString(String s) throws MalformedURLException {
        try {
            URL url = new URL(s);
            System.out.format(
                    "%s%n%s%n%s%n%s%n%s%n%s%n%s%n%s%n%s%n",
                    url.getProtocol(),
                    url.getAuthority(),
                    url.getFile(),
                    url.getHost(),
                    url.getPath(),
                    url.getPort(),
                    url.getDefaultPort(),
                    url.getQuery(),
                    url.getRef()
            );
        } catch (Exception e){
            System.out.format("Parameter %s is not a valid URL.", s);
        }
    }
}

