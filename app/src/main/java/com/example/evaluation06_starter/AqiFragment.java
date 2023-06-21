package com.example.evaluation06_starter;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.example.evaluation06_starter.databinding.FragmentAqiBinding;
import com.google.gson.Gson;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class AqiFragment extends Fragment {
    FragmentAqiBinding binding;
    OkHttpClient client = new OkHttpClient();
    String apiKey = "INSERT API KEY HERE FROM openweathermap.org";

    public AqiFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentAqiBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        DataServices.City city = (DataServices.City) getArguments().getSerializable("city");

        // Reformat Url to include these required field
        String url = String.format("https://api.openweathermap.org/data/2.5/air_pollution?lat=%s&lon=%s&appid=%s",
        city.getLat(), city.getLon(), apiKey);
        Request request = new Request.Builder().url(url).build();

        // Use the client to send the request
        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(@NonNull Call call, @NonNull IOException e) {
                e.printStackTrace();
            }

            @Override
            public void onResponse(@NonNull Call call, @NonNull Response response) throws IOException {
                // use Gson to convert a JSON string to a Java object
                Gson gson = new Gson();
                AirQuality airQuality = gson.fromJson(response.body().string(), AirQuality.class);

                // Get first item from List
                AirQuality.ListItem listItem = airQuality.list.get(0);

                // Update UI Element
                getActivity().runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        binding.textViewCityName.setText(city.toString());
                        binding.textViewTemp.setText(String.valueOf(listItem.main.aqi));
                        binding.textViewTempMax.setText(String.valueOf(listItem.components.co));
                        binding.textViewTempMin.setText(String.valueOf(listItem.components.no));
                        binding.textViewDesc.setText(String.valueOf(listItem.components.no2));
                        binding.textViewWindSpeed.setText(String.valueOf(listItem.components.o3));
                        binding.textViewWindDegree.setText(String.valueOf(listItem.components.so2));
                    }
                });
            }
        });
    }
}