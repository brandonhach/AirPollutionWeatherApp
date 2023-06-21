package com.example.evaluation06_starter;


import java.util.ArrayList;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;

public class AirQuality {
    ArrayList<ListItem> list;

    static class ListItem {
        Main main;
        Components components;

        static class Main {
            int aqi;
        }

        static class Components {
            double co, no, no2, o3, so2;
        }
    }
}





