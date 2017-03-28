package co.com.compumovil.exampleretrofit;

import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import co.com.compumovil.exampleretrofit.POJO.Model;
import retrofit.RestAdapter;
import retrofit.RetrofitError;
import retrofit.client.Response;
import retrofit.Callback;

public class MainActivity extends AppCompatActivity {

    TextView city, status, humidity, pressure;
    String url = "http://api.openweathermap.org/data/2.5";
    Button updateButton;
    EditText ciudadEditText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        city = (TextView) findViewById(R.id.txt_city);
        status = (TextView) findViewById(R.id.txt_status);
        humidity = (TextView) findViewById(R.id.txt_humidity);
        pressure = (TextView) findViewById(R.id.txt_press);
        updateButton = (Button) findViewById(R.id.actualizar);
        //making object of RestAdapter
        RestAdapter adapter = new RestAdapter.Builder().setEndpoint(url).build();

        //Creating Rest Services
        final RestInterface restInterface = adapter.create(RestInterface.class);

        updateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Calling method to get whether report
                restInterface.getWheatherReport("Londres","0721dcca336646ec8198c467bf4a06c4",new Callback<Model>() {
                    @Override
                    public void success(Model model, Response response) {
                        city.setText("city :"+model.getName());
                        status.setText("Status :"+model.getWeather().get(0).getDescription());
                        humidity.setText("humidity :"+model.getMain().getHumidity().toString());
                        pressure.setText("pressure :"+model.getMain().getPressure().toString());
                    }

                    @Override
                    public void failure(RetrofitError error) {

                        String merror = error.getMessage();
                        Log.d("RETROFIT", "ENTROOOOOOOOO2");
                        Log.d("RETROFIT", merror);
                    }
                });
            }
        });


    }
}