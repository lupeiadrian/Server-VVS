package org.codefromscratch.httpserver;

import org.codefromscratch.httpserver.config.Configuration;
import org.codefromscratch.httpserver.config.ConfigurationManager;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;

/**
 *
 * Clasa pentru HttpServer
 *
 */
public class HttpServer {
    public static void main(String[] args){
        System.out.println("Server starting...");

        ConfigurationManager.getInstace().loadConfigurationFile("src/main/resources/http.json");
        Configuration conf = ConfigurationManager.getInstace().getCurrentConfiguration();

        System.out.println("Using Port:"+ conf.getPort());
        System.out.println("Using webRoot:"+ conf.getWebroot());

        try {
            ServerSocket serverSocket = new ServerSocket(conf.getPort());
            Socket socket = serverSocket.accept();

            InputStream inputStream = socket.getInputStream();
            OutputStream outputStream = socket .getOutputStream();

            String html= "<html lang=\"en\"><head><meta name=\"description\" content=\"Webpage description goes here\" /><meta charset=\"utf-8\"><title>Change_me</title><meta name=\"viewport\" content=\"width=device-width, initial-scale=1\"><meta name=\"author\" content=\"\"><link rel=\"stylesheet\" href=\"css/style.css\"><script src=\"http://code.jquery.com/jquery-latest.min.js\"></script></head><body><div class=\"container\"></div><script></script></body></html>";

            final String CRLF = "\n\r"; //13, 10

            String response =
                    "HTTP/1.1 200 OK" + CRLF + //Status line : HTTP VERSION RESPONSE CODE RESPONSE MESSAGE
                    "Content-Lenght" + html.getBytes().length + CRLF + //Header
                            CRLF +
                            html +
                            CRLF + CRLF;
            outputStream.write(response.getBytes());
            inputStream.close();
            outputStream.close();
            socket.close();
            serverSocket.close();

        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
