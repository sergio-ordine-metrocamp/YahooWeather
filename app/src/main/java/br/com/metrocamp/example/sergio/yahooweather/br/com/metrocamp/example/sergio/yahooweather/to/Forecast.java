package br.com.metrocamp.example.sergio.yahooweather.br.com.metrocamp.example.sergio.yahooweather.to;

/**
 * Created by Sergio on 4/16/17.
 */

public class Forecast {

    private ForecastType type;
    private String date;
    private String day;
    private int high;
    private int low;

    public ForecastType getType() {
        return type;
    }

    public void setType(ForecastType type) {
        this.type = type;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getDay() {
        return day;
    }

    public void setDay(String day) {
        this.day = day;
    }

    public int getHigh() {
        return high;
    }

    public void setHigh(int high) {
        this.high = high;
    }

    public int getLow() {
        return low;
    }

    public void setLow(int low) {
        this.low = low;
    }


    @Override
    public String toString() {

        return (date+" "+day+":"+type +"("+low+":"+high+")");

    }
}


