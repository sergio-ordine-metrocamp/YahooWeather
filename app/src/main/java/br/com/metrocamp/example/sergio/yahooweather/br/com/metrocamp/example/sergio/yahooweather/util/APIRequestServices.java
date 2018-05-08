package br.com.metrocamp.example.sergio.yahooweather.br.com.metrocamp.example.sergio.yahooweather.util;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.net.URLConnection;

import javax.net.ssl.HttpsURLConnection;

/**
 * Created by Sergio on 4/16/17.
 */

public class APIRequestServices {

    public static JSONObject requestAPI(String apiURL) throws APIRequestException {

        try {
            //Create URL and connection
            URL url = new URL(apiURL);
            HttpURLConnection connection = makeConnection(url);

            connection.connect();
            int responseCode = connection.getResponseCode();

            if (responseCode == HttpURLConnection.HTTP_OK) {

                InputStream stream = connection.getInputStream();
                String resultString = readStream(stream);

                //Convert Stream to JSON Object
                return new JSONObject(resultString);


            } else {
                throw new APIRequestException("Error accessing URL. Response Code:"+responseCode);
            }

        } catch (MalformedURLException mEx) {
           throw new APIRequestException("Invalid URL",mEx);
        } catch (IOException ioEx) {
            throw new APIRequestException("Error creating connection", ioEx);
        } catch (JSONException jsonEx) {
            throw new APIRequestException("Error parsing response", jsonEx);
        } catch (Exception ex) {
            throw new APIRequestException("Unknown error", ex);
        }


    }

    private static HttpURLConnection makeConnection(URL url) throws IOException {

        HttpURLConnection connection =  (HttpURLConnection) url.openConnection();
        // Timeout for reading InputStream arbitrarily set to 3000ms.
        connection.setReadTimeout(3000);
        // Timeout for connection.connect() arbitrarily set to 3000ms.
        connection.setConnectTimeout(3000);
        // For this use case, set HTTP method to GET.
        connection.setRequestMethod("GET");
        // Already true by default but setting just in case; needs to be true since this request
        // is carrying an input (response) body.
        connection.setDoInput(true);

        return connection;
    }

    private static String readStream(InputStream stream) throws APIRequestException {

        StringBuilder builder;
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(stream))) {
            builder = new StringBuilder(stream.available());
            String line = null;

            while ((line = reader.readLine()) != null) {
                builder.append(line).append('\n');
            }
        } catch (IOException ioEx) {
            throw new APIRequestException("Error processing result", ioEx);
        }

        return builder.toString();
    }

}
