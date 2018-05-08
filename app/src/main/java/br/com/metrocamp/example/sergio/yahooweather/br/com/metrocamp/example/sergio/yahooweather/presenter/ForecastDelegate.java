package br.com.metrocamp.example.sergio.yahooweather.br.com.metrocamp.example.sergio.yahooweather.presenter;

import java.util.List;

import br.com.metrocamp.example.sergio.yahooweather.br.com.metrocamp.example.sergio.yahooweather.to.Forecast;

/**
 * Created by Sergio on 4/16/17.
 */

public interface ForecastDelegate {

    void forecast(List<Forecast> forecasts);

}