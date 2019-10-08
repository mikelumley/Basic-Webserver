package controller;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;

public class TestHttpClient {

    public static HttpResponse sendRequest(URL url, String type, String message) {
        try {
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod(type);
            connection.setRequestProperty("Content-Type", "text/plain");
            int responseCode = 200;
            if (!type.equals("GET")) {
                connection.setDoOutput(true);
                OutputStream requestBodyStream = connection.getOutputStream();
                requestBodyStream.write(message.getBytes());
                requestBodyStream.close();
                responseCode = connection.getResponseCode();
            }

            InputStream responseStream;
            if (responseCode < 400)
                responseStream = connection.getInputStream();
            else
                responseStream = connection.getErrorStream();
            InputStreamReader responseBodyStream = new InputStreamReader(responseStream);
            BufferedReader responseBodyReader = new BufferedReader(responseBodyStream);

            return new HttpResponse(null, responseCode, responseBodyReader.readLine());
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
