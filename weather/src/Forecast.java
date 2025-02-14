import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;

public class Forecast {


    private JSONArray forecastArray;
    private JSONObject forecastObject;
    private JSONObject dayElement;
    private JSONObject astroElement;


    /*
    this method will get a
    totalprecip_mm
    daily_will_it_rain
    sunrise
    sunset

     */


    Forecast(String city) throws IOException {
        forecastObject= new API(city).forecastJsonObject().getJSONObject("forecast");
        forecastArray=forecastObject.getJSONArray("forecastday");
        dayElement=forecastArray.getJSONObject(0).getJSONObject("day");//it will only get me today's object so i can get today's object info
        astroElement=forecastArray.getJSONObject(0).getJSONObject("astro");
    }


    public String getWillItRain(){

        //this method will return if there is rain then it'll return yes otherwise no
        int data= dayElement.getInt("daily_will_it_rain");

        if (data==1)
            return "Yes";

        return "No";


    }

    public double getPrecipation(){

        return dayElement.getDouble("totalprecip_mm");
    }

    public String getSunriseTime(){
        return astroElement.getString("sunrise");
    }

    public String getsunset(){
        return astroElement.getString("sunset");
    }

}
