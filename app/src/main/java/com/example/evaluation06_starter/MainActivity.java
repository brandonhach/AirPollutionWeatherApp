package com.example.evaluation06_starter;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

public class MainActivity extends AppCompatActivity implements CitiesFragment.CityListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction().add(R.id.rootView, new CitiesFragment()).commit();
        }
    }

    @Override
    public void goToAqiFragment(AqiFragment fragment) {
                getSupportFragmentManager().beginTransaction()
                .replace(R.id.rootView, fragment)
                .commit();
    }
}