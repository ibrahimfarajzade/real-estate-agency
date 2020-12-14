package com.farajzade.realEstateAgency;


import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;


/**
 * A simple {@link Fragment} subclass.
 */
public class TopFragment extends Fragment {
    Spinner spinnerPlaces;
    Button btnOk;
    EditText etText;

    interface OnItemSelectedOnTopFragment{
        public void ImageChage(int index);
    }
    OnItemSelectedOnTopFragment listener;

    public TopFragment() {
        Log.d("FRAGMENTTOP","TopFragment()" );
        // Required empty public constructor
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        Log.d("FRAGMENTTOP","onAttach()" );
        if(context instanceof OnItemSelectedOnTopFragment)
            listener = (OnItemSelectedOnTopFragment) context;
        else
            throw new ClassCastException(context.toString()+"must be implemented");
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        Log.d("FRAGMENTTOP","onCreateView()" );


        return inflater.inflate(R.layout.fragment_top, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        Log.d("FRAGMENTTOP","onViewCreated()" );


        spinnerPlaces = view.findViewById(R.id.spinnerCities);

        spinnerPlaces.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                Log.d("FRAGMENT","TopFragment setOnItemSelectedListener() position: "+position);
                listener.ImageChage(position);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
      }

    @Override
    public void onDetach() {
        super.onDetach();
        Log.d("FRAGMENTTOP","onDetach" );

    }
}
