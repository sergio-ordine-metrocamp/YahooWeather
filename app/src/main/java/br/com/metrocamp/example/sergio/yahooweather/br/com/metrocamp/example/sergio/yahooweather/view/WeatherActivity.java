package br.com.metrocamp.example.sergio.yahooweather.br.com.metrocamp.example.sergio.yahooweather.view;

import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListView;


import java.util.List;

import br.com.metrocamp.example.sergio.yahooweather.R;
import br.com.metrocamp.example.sergio.yahooweather.br.com.metrocamp.example.sergio.yahooweather.presenter.ForecastDelegate;
import br.com.metrocamp.example.sergio.yahooweather.br.com.metrocamp.example.sergio.yahooweather.presenter.WeatherPresenter;
import br.com.metrocamp.example.sergio.yahooweather.br.com.metrocamp.example.sergio.yahooweather.to.Forecast;


class ForecastTask extends AsyncTask<String, Object, Void> {

    private ForecastDelegate delegate;

    public ForecastTask(ForecastDelegate delegate) {

            this.delegate = delegate;

    }

    protected Void doInBackground(String... parameters) {

        Log.d("APP","Executando doInBackground");

        int count = parameters.length;

        if (count >= 1 ) {

            String parameter = parameters[count -1 ];
            WeatherPresenter.getForecast(parameter, delegate);
            Log.d("APP","Finalizando doInBackground");
        }

        return null;
    }


}

public class WeatherActivity extends AppCompatActivity implements ForecastDelegate {

    private ListView forecastListView;

    private Handler handler;
    private List<Forecast> forecasts;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_weather);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        forecastListView = (ListView) findViewById(R.id.weather_list);



        handler = new Handler() {
            @Override
            public void handleMessage(Message msg) {
                // Update user interface
                ForecastAdapter adapter = new ForecastAdapter(WeatherActivity.this.getApplicationContext(), forecasts);
                forecastListView.setAdapter(adapter);
            }
        };



        Runnable run = new Runnable() {
            public void run() {
                Log.d("APP","Iniciando execução");
                       WeatherPresenter.getForecast("Campinas, SP", WeatherActivity.this);
            Log.d("APP","Finalizando execução");
            }
        };

        Thread myThread = new Thread(run);
        myThread.start();


        //new ForecastTask(this).execute("Campinas,SP");
        Log.d("APP","Saindo do onCreate");

    }

    public void forecast(List<Forecast> forecasts) {
        //Load list
        this.forecasts = forecasts;

        //Send message
        handler.sendEmptyMessage(0);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_weather, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }



}
