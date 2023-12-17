package org.example;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;

public class WeatherWidgetGenerator {
    private static final String API_URL = "https://api.openweathermap.org/data/2.5/weather?q=Paris,fr&appid=1672feac0f69057cbf2a71e905f741d2";

    public static void main(String[] args) {
        String city = "Paris";
        String apiKey = "1672feac0f69057cbf2a71e905f741d2";

        try {
            String json = getJsonData(city, apiKey);
            WeatherData weatherData = parseJson(json);
            generateHtmlWidget(weatherData);
            System.out.println("HTML виджет с погодой создан успешно.");
        } catch (IOException e) {
            System.out.println("Произошла ошибка при получении или обработке данных погоды: " + e.getMessage());
        }
    }

    private static String getJsonData(String city, String apiKey) throws IOException {
        String apiUrl = String.format(API_URL, city, apiKey);

        URL url = new URL(apiUrl);
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setRequestMethod("GET");

        try (InputStream inputStream = connection.getInputStream()) {
            byte[] bytes = inputStream.readAllBytes();
            return new String(bytes, StandardCharsets.UTF_8);
        }
    }

    private static WeatherData parseJson(String json) {
        JsonObject jsonObject = JsonParser.parseString(json).getAsJsonObject();
        Gson gson = new Gson();
        return gson.fromJson(jsonObject, WeatherData.class);
    }

    private static void generateHtmlWidget(WeatherData weatherData) throws IOException {
        String htmlContent = "<html>\n" +
                "<head> <meta charset=\"utf-8\"></head>\n" +
                "<body>\n" +
                "<h1>Погода в городе " + weatherData.getName() + ", " + weatherData.getSys().getCountry() + "</h1>\n" +
                "<p>Текущая погода: " + weatherData.getWeather()[0].getDescription() + "</p>\n" +
                "<p>Краткое описание погоды: " + weatherData.getWeather()[0].getMain() + "</p>\n" +
                "<p>Температура: " + weatherData.getMain().getTemp() + " °C</p>\n" +
                "<p>Давление: " + weatherData.getMain().getPressure() + " мм рт. ст.</p>\n" +
                "<p>Влажность: " + weatherData.getMain().getHumidity() + " %</p>\n" +
                "<p>Минимальная температура: " + weatherData.getMain().getTempMin() + " °C</p>\n" +
                "<p>Максимальная температура: " + weatherData.getMain().getTempMax() + " °C</p>\n" +
                "<p>Скорость ветра: " + weatherData.getWind().getSpeed() + " м/с</p>\n" +
                "<p>Направление ветра: " + weatherData.getWind().getDeg() + "°</p>\n" +
                "<p>Облачность: " + weatherData.getClouds().getAll() + " %</p>\n" +
                "</body>\n" +
                "</html>";

        try (BufferedWriter writer = new BufferedWriter(new FileWriter("weather_widget.html"))) {
            writer.write(htmlContent);
        }
    }
}

class Sys {
        private String country;
        public String getCountry() {
            return country;
        }
    }

class WeatherData {
    private String name;
    private Sys sys;
    private Weather[] weather;
    private Main main;
    private Wind wind;
    private Clouds clouds;

    public String getName() {
        return name;
    }

    public Sys getSys() {
        return sys;
    }

    public Weather[] getWeather() {
        return weather;
    }

    public Main getMain() {
        return main;
    }

    public Wind getWind() {
        return wind;
    }

    public Clouds getClouds() {
        return clouds;
    }
}

class Weather {
    private String main;
    private String description;

    public String getMain() {
        return main;
    }

    public String getDescription() {
        return description;
    }
}

class Main {
    private double temp;
    private int pressure;
    private int humidity;
    private double tempMin;
    private double tempMax;

    public double getTemp() {
        return temp;
    }

    public int getPressure() {
        return pressure;
    }

    public int getHumidity() {
        return humidity;
    }

    public double getTempMin() {
        return tempMin;
    }

    public double getTempMax() {
        return tempMax;
    }
}

class Wind {
    private double speed;
    private int deg;

    public double getSpeed() {
        return speed;
    }

    public int getDeg() {
        return deg;
    }
}

class Clouds {
    private int all;

    public int getAll() {
        return all;
    }
}