import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;

public class API {


    private String baseURl="http://api.weatherapi.com/v1";
    private String forecastEndpoint="/forecast.json";
    private String weatherEndpoint="/current.json";
    private String key="?key=d830c321e10a48ab9bd155656250902";
    private String q="&q=";
    private final String FORECASTFULLURL;
    private final String WEATHERFULLURL;
    private HttpURLConnection forecastConnection;
    private HttpURLConnection weatherConnection;

    API(String city){
        //we will create full url within the constructor for both the forcast and the weather api
        city = city.trim().replace(" ", "%20"); // URL encode spaces
        q=q+city.trim();
        FORECASTFULLURL =baseURl+forecastEndpoint+key+q+"&days=7";
        WEATHERFULLURL =baseURl+weatherEndpoint+key+q;
    }



    public HttpURLConnection getForcastConnection(){//getter method
        return forecastConnection;
    }

    public HttpURLConnection getWeatherConnection(){//getter method
        return weatherConnection;
    }


//these two functions will create a url object
    private URL forecastUrl(){
//1
        try {
            return new URL(FORECASTFULLURL);

        } catch (MalformedURLException e) {
            throw new RuntimeException(e);
        }
    }



    private URL weatherUrl(){
//2
        try{
            return new URL (WEATHERFULLURL);
        }
        catch(MalformedURLException e){
            throw new RuntimeException(e);
        }

    }

    //these two methods will open the connection between my app and the server (and create a connection object)

    private void createForecastConnection() throws IOException {
    //1
        try{

            forecastConnection= (HttpURLConnection) forecastUrl().openConnection();

        }
        catch(IOException e){

            throw new IOException();
        }
    }


    private void createWeatherConnection() throws IOException {
   //2
        try{

            weatherConnection= (HttpURLConnection) weatherUrl().openConnection();
        }
        catch (IOException e){
            throw new IOException();
        }
    }

    //we will specify what we will do with the thing we requested in this case we want to get the response
    private void setForecastRequestMethod() throws ProtocolException {
        getForcastConnection().setRequestMethod("GET");
    }

    private void setWeatherRequestMethod() throws ProtocolException {
        getWeatherConnection().setRequestMethod("GET");
    }

    //to check if the response has gotten through or not

    private boolean checkForecastHttpRequest() throws IOException {

        int responseCode= getForcastConnection().getResponseCode();

        if (responseCode==HttpURLConnection.HTTP_OK){

            return true;
        }

        else
            return false;
    }

    private boolean checkWeatherHttpRequest() throws IOException {

        int responseCode = weatherConnection.getResponseCode();

        if (responseCode==HttpURLConnection.HTTP_OK){

            return true;
        }
        else
            return false;


    }


//    codes to read the response


    private BufferedReader getForecastResponseReader () throws IOException {
        //we have created a bufferreader to read the response
        return new BufferedReader(new InputStreamReader(getForcastConnection().getInputStream()));
    }

    private BufferedReader getWeatherResponseReader() throws IOException {
        return new BufferedReader(new InputStreamReader(getWeatherConnection().getInputStream()));
    }


    private StringBuilder forecastResponse() throws IOException {
        BufferedReader reader;

        if(checkForecastHttpRequest()){

            reader=getForecastResponseReader();
            StringBuilder lines= new StringBuilder();//this will store the response
            String line;//this will read each line to check if it's null or not so we can store it in a stringBuilder

            while((line=reader.readLine())!=null){
                lines.append(line);//we will store the json formatted data
            }
            return lines;
        }
        //if the http request gave us an error it will print out this statement, and then it will return null given that the response haven't reached

            System.out.println("error occurred");
            return null;
    }

    private StringBuilder weatherResponse() throws IOException {
        BufferedReader reader;

        if (checkWeatherHttpRequest()){
            reader = getWeatherResponseReader();
            StringBuilder lines= new StringBuilder();
            String line;

            while((line=reader.readLine())!=null){
                lines.append(line);
            }
            return lines;

        }

            System.out.println("error occurred");
            return null;

    }


    //created a json object to return the data in json format

    public JSONObject forecastJsonObject() throws IOException {
// when api is invoked we invoke this method with it to create the jsonObject
        return preparingTheForecastJsonObject();
    }



    private JSONObject preparingTheForecastJsonObject() throws IOException {
        //this method will bring everything together
        createForecastConnection();//this method will create the forecast connection object
        setForecastRequestMethod();//this method will set the forecast request to get
        return  new JSONObject(forecastResponse().toString());


    }

    public JSONObject weatherJSONObject() throws IOException {
                return preparingTheWeatherJsonObject();
    }

    private JSONObject preparingTheWeatherJsonObject() throws IOException {
        //similarly this method will prepare everything basically then return the object ready for more abstraction
        createWeatherConnection();//this will create a connection object and initialize the weatherConnection ref variable
        setWeatherRequestMethod();
        return new JSONObject(weatherResponse().toString());

    }





}
