
import java.io.IOException;
import java.util.Scanner;

public class Main {
    static Scanner scanner = new Scanner(System.in);
    static String city;
    public static void main(String[] args) throws IOException {

        String status="notNull";//initial value

        while(status!=null) {
            status = mainMenu();
            if (status==null)break;//just in case the first time was null and the person didn't choose a city
            Weather weather = new Weather(status);
            Forecast forecast = new Forecast(status);
            //1- we have created both object api to start providing the info regarding the city



            System.out.println("City: "+status);
            System.out.println("Temperature: "+weather.getTemp_c()+"C");
            System.out.println("Temperature: "+weather.getTemp_f()+"F");
            System.out.println("What it feels lke in celsius: "+weather.getFeelsLike_c());
            System.out.println("What it feels like in fahrenheit: "+weather.getFeelsLike_f());
            System.out.println("Wind: "+weather.getWind_mph()+" mph");
            System.out.println("Humidity: "+weather.getHumidity()+"%");
            System.out.println("Total precipitation: "+forecast.getPrecipation());
            System.out.println("is there a possibilities that it might rain: "+forecast.getWillItRain());
            System.out.println("Sunrise: "+forecast.getSunriseTime());
            System.out.println("Sunset: "+forecast.getsunset());
        }



    }





    public static String mainMenu(){


        System.out.println("----------------------------------------------------------------");
        System.out.println("---------------------Welcome to the weather app-----------------");
        System.out.println("----------------------------------------------------------------");
        System.out.println("If you want to exit write exit");
        System.out.println("----------------------------------------------------------------");
        System.out.println(" what city would you like to know its weather condition?...");
        city = scanner.next();
        System.out.println("----------------------------------------------------------------");
        System.out.println("info:");
        if(city.equalsIgnoreCase("exit"))
            return null;
        return city;
    }



}