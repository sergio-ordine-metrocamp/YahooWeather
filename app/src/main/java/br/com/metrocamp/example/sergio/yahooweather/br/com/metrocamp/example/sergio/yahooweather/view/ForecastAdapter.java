package br.com.metrocamp.example.sergio.yahooweather.br.com.metrocamp.example.sergio.yahooweather.view;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.PorterDuff;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;


import java.util.ArrayList;
import java.util.List;

import br.com.metrocamp.example.sergio.yahooweather.R;
import br.com.metrocamp.example.sergio.yahooweather.br.com.metrocamp.example.sergio.yahooweather.to.Forecast;
import br.com.metrocamp.example.sergio.yahooweather.br.com.metrocamp.example.sergio.yahooweather.to.ForecastType;

/**
 * Created by Sergio on 4/16/17.
 */

public class ForecastAdapter extends BaseAdapter {

    private Context mContext;
    private LayoutInflater mInflater;
    private List<Forecast> mDataSource;

    public ForecastAdapter(Context context, List<Forecast> items) {
        mContext = context;
        mDataSource = items;
        mInflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    //1
    @Override
    public int getCount() {
        return mDataSource.size();
    }

    //2
    @Override
    public Object getItem(int position) {
        return mDataSource.get(position);
    }

    //3
    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // Get view for row item
        View rowView = mInflater.inflate(R.layout.forecast_item, parent, false);

        ImageView iconView = (ImageView)rowView.findViewById(R.id.weather_icon_thumbnail);

        iconView.setImageResource(iconForType(mDataSource.get(position).getType()));

       // iconView.setImageResource(R.drawable.icon_rain);

        iconView.setColorFilter(iconView.getContext().getResources().getColor(R.color.colorIcon), PorterDuff.Mode.SRC_ATOP);

        ImageView temperatureView = (ImageView)rowView.findViewById(R.id.temperature_icon_thumbnail);
        temperatureView.setImageResource(R.drawable.icon_temperature);

        temperatureView.setColorFilter(iconView.getContext().getResources().getColor(R.color.colorTermometer), PorterDuff.Mode.SRC_ATOP);


        TextView dateView = (TextView)rowView.findViewById(R.id.forecast_item_date);
        dateView.setText(mDataSource.get(position).getDate());

        TextView dayView = (TextView)rowView.findViewById(R.id.forecast_item_day);
        dayView.setText(mDataSource.get(position).getDay());

        TextView highView = (TextView)rowView.findViewById(R.id.forecast_item_high);
        highView.setText(""+mDataSource.get(position).getHigh()+"℃");

        TextView lowView = (TextView)rowView.findViewById(R.id.forecast_item_low);
        lowView.setText(""+mDataSource.get(position).getLow()+"℃");

        if (position % 2 == 0) {
            rowView.setBackgroundResource(R.color.colorEvenRow);
        } else {
            rowView.setBackgroundResource(R.color.colorOddRow);
        }

        return rowView;
   }

    private int iconForType(ForecastType type) {



        switch (type) {
            case Cloudy:
            case MostlyCloudyDay:
            case MostlyCloudyNight:
                return R.drawable.icon_cloud;
            case PartlyCloudy:
            case PartlyCloudyDay:
            case PartlyCloudyNight:
                return R.drawable.icon_partly_cloud;
            case Sunny:
            case FairDay:
            case FairNight:
                return R.drawable.icon_sunny;
            case TropicalStorm:
            case ScatteredThuderstorms:
            case ScatteredThuderstorms2:
            case IsolatedThunderstorms:
            case SevereThunderstorms:
            case IsolatedThunderShowers:
                return R.drawable.icon_storm;
            case Showers:
            case Showers2:
                return R.drawable.icon_rain;
            default:
                Log.d("APP",""+type);
                return R.drawable.icon_snow;
        }

    }



}
