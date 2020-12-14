package com.farajzade.realEstateAgency;


import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;


/**
 * A simple {@link Fragment} subclass.
 */
public class BottomFragment extends Fragment {

    //int[] logos ={android.R.drawable.arrow_down_float, android.R.drawable.btn_star, android.R.drawable.ic_menu_directions};
    int logos[] = new int[]{R.drawable.bilkent1, R.drawable.bilkent2};

    ImageView imgLogo;
    public BottomFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_bottom, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        int pos = getArguments().getInt("position", 0);
        Log.d("FRAGMENT","BottomFragment onViewCreated() position: "+pos);

        imgLogo = view.findViewById(R.id.imgLogo);
        imgLogo.setImageResource(logos[pos]);

    }

    public void changeImage(int index) {
        imgLogo.setImageResource(logos[index]);
    }
}
