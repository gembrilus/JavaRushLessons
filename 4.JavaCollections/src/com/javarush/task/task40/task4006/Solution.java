package com.javarush.task.task40.task4006;

import java.io.*;
import java.net.Socket;
import java.net.URL;

/* 
Отправка GET-запроса через сокет
*/

public class Solution {
    public static void main(String[] args) throws Exception {
        getSite(new URL("http://javarush.ru/social.html"));
    }

    public static void getSite(URL url) {

        String host = url.getHost();
        int port = url.getDefaultPort();

            try(Socket socket = new Socket(host, port); OutputStream os = socket.getOutputStream();
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(socket.getInputStream()))) {

                String request = String.format("GET %s HTTP/1.1\r\nHost: %s\r\n\r\n", url.getFile(), url.getHost());

                os.write(request.getBytes());
                os.flush();

                String responseLine;
                while ((responseLine = bufferedReader.readLine()) != null) {
                    System.out.println(responseLine);
                }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}