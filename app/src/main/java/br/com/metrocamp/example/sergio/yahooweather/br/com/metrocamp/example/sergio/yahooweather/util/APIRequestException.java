package br.com.metrocamp.example.sergio.yahooweather.br.com.metrocamp.example.sergio.yahooweather.util;

/**
 * Created by Sergio on 4/16/17.
 */

public class APIRequestException extends Exception {

    public APIRequestException(String message, Throwable cause)
    {
        super(message, cause);
    }


    public APIRequestException(String message) {
        super(message);
    }
}
