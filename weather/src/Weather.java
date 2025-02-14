
import org.json.JSONObject;

import java.io.IOException;

public class Weather {

    /*
    this class will include what information regarding the weather

    temp_c
    temp_f
    feelslike_c
    feelslike_f
    wind_mph
    humidity


     */


     private API weatherAPI;
     private JSONObject weatherObject;

    Weather(String city){
        weatherAPI= new API(city);

    }


    public JSONObject getCurrentObject() throws IOException {
        //this will fetch the current object that has these values under it
        return weatherObject=weatherAPI.weatherJSONObject().getJSONObject("current");
    }

    public double getTemp_c() throws IOException {
        return getCurrentObject().getDouble("temp_c");
    }

    public double getTemp_f() throws IOException {
        return getCurrentObject().getDouble("temp_f");
    }

    public double getFeelsLike_c() throws IOException {
        return getCurrentObject().getDouble("feelslike_c");
    }

    public double getFeelsLike_f() throws IOException {
        return getCurrentObject().getDouble("feelslike_f");
    }

    public double getWind_mph() throws IOException {
            return getCurrentObject().getDouble("wind_mph");
    }

    public int getHumidity() throws IOException {
        return getCurrentObject().getInt("humidity");
    }





}
