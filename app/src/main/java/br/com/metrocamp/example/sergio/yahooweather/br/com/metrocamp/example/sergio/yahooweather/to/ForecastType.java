package br.com.metrocamp.example.sergio.yahooweather.br.com.metrocamp.example.sergio.yahooweather.to;

/**
 * Created by Sergio on 4/16/17.
 */

public enum ForecastType {


    Tornado(0),
    TropicalStorm(1),
    Hurricane(2),
    SevereThunderstorms(3),
    Thunderstorms(4),
    MixedRainAndSnow(5),
    MixedRainAndSleet(6),
    MixedSnowAndSleet(7),
    FreezingDrizzle(8),
    Drizzle(9),
    FreezingRain(10),
    Showers(11),
    Showers2(12),
    SnowFlurries(13),
    LighSnowShowers(14),
    BlowingSnow(15),
    Snow(16),
    Hail(17),
    Sleet(18),
    Dust(19),
    Foggy(20),
    Haze(21),
    Smoky(22),
    Blustery(23),
    Windy(24),
    Cold(25),
    Cloudy(26),
    MostlyCloudyNight(27),
    MostlyCloudyDay(28),
    PartlyCloudyNight(29),
    PartlyCloudyDay(30),
    ClearNight(31),
    Sunny(32),
    FairNight(33),
    FairDay(34),
    MixedRainAndHail(35),
    Hot(36),
    IsolatedThunderstorms(37),
    ScatteredThuderstorms2(38),
    ScatteredThuderstorms(39),
    ScatteredShowers(40),
    HeavySnow(41),
    ScatteredSnowShowers(42),
    HeavySnow2(43),
    PartlyCloudy(44),
    ThunderShowers(45),
    SnowShowers(46),
    IsolatedThunderShowers(47),
    None(3200);

    int id;

    private ForecastType(int i) {
        id = i;
    }

    public boolean Compare(int i) {
        return id == i;
    }

    public static ForecastType getValue(int _id) {
        ForecastType[] Forecasts = ForecastType.values();
        for (int i = 0; i < Forecasts.length; i++) {
            if (Forecasts[i].Compare(_id))
                return Forecasts[i];
        }
        return ForecastType.None;
    }

}

