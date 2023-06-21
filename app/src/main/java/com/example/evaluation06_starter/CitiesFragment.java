package com.example.evaluation06_starter;

import android.content.Context;
import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import com.example.evaluation06_starter.databinding.FragmentCitiesBinding;
import java.util.ArrayList;



public class CitiesFragment extends Fragment {

    private FragmentCitiesBinding binding;
    ArrayList<DataServices.City> cities = DataServices.cities;
    ArrayAdapter<DataServices.City> adapter;

    public CitiesFragment() {
        // Required empty public constructor
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        adapter = new ArrayAdapter<>(getContext(), android.R.layout.simple_list_item_1, cities);
        binding.listView.setAdapter(adapter);

        binding.listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Bundle bundle = new Bundle();
                bundle.putSerializable("city", cities.get(position));
                AqiFragment aqiFragment = new AqiFragment();
                aqiFragment.setArguments(bundle);
                mListener.goToAqiFragment(aqiFragment);
            }
        });
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = FragmentCitiesBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    CityListener mListener;

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        mListener = (CityListener) context;
    }

    interface CityListener{
        void goToAqiFragment(AqiFragment aqiFragment);
    }
}

