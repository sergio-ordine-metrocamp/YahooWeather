package br.com.metrocamp.example.sergio.yahooweather.br.com.metrocamp.example.sergio.yahooweather.presenter;

import android.support.annotation.NonNull;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import br.com.metrocamp.example.sergio.yahooweather.br.com.metrocamp.example.sergio.yahooweather.to.Forecast;
import br.com.metrocamp.example.sergio.yahooweather.br.com.metrocamp.example.sergio.yahooweather.to.ForecastType;
import br.com.metrocamp.example.sergio.yahooweather.br.com.metrocamp.example.sergio.yahooweather.util.APIRequestException;
import br.com.metrocamp.example.sergio.yahooweather.br.com.metrocamp.example.sergio.yahooweather.util.APIRequestServices;

/**
 * Created by Sergio on 4/16/17.
 */

public class WeatherPresenter {

    public static void getForecast(String local, ForecastDelegate delegate) {

        String url = "https://query.yahooapis.com/v1/public/yql?q=select%20*%20from%20weather.forecast%20where%20woeid%20in%20(select%20woeid%20from%20geo.places(1)%20where%20text%3D%22" + local + "%22)%20%20and%20u%20%3D%20'c'&format=json&env=store%3A%2F%2Fdatatables.org%2Falltableswithkeys";
        List<Forecast> forecasts = new ArrayList<Forecast>();


        JSONObject resultObject = null;
        try {
            resultObject = APIRequestServices.requestAPI(url);


            forecasts = parseResult(resultObject);

            delegate.forecast(forecasts);


        } catch (APIRequestException e) {
           //Tratar erro
        } catch (JSONException e) {
            //Tratar erro
        }
    }


    private static List<Forecast> parseResult(JSONObject result) throws JSONException {

        List<Forecast> forecasts = new ArrayList<Forecast>();

        JSONArray jsonForecast = result.getJSONObject("query").getJSONObject("results").getJSONObject("channel").getJSONObject("item").getJSONArray("forecast");

        if (jsonForecast != null) {

            for (int i = 0; i < jsonForecast.length(); i++) {

                JSONObject dayForecast = jsonForecast.getJSONObject(i);

                Iterator<String> keys = dayForecast.keys();

                while (keys.hasNext()) {
                    String cityName = keys.next();
                    String id = dayForecast.getString(cityName);
                    //City city = new City();
                    //city.setName(cityName);
                    //city.setId(id);
                }


                Forecast forecast = getForecast(dayForecast);

                forecasts.add(forecast);

            }
        }

        return forecasts;

    }

    @NonNull
    private static Forecast getForecast(JSONObject dayForecast) throws JSONException {
        Forecast forecast = new Forecast();

        String jsonCode = dayForecast.getString("code");
        String date = dayForecast.getString("date");
        String day = dayForecast.getString("day");
        String jsonHigh = dayForecast.getString("high");
        String jsonLow = dayForecast.getString("low");

        int code = Integer.parseInt(jsonCode);
        int high = Integer.parseInt(jsonHigh);
        int low = Integer.parseInt(jsonLow);

        forecast.setType(ForecastType.getValue(code));
        forecast.setDate(date);
        forecast.setDay(day);
        forecast.setHigh(high);
        forecast.setLow(low);
        return forecast;
    }


}
