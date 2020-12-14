package com.farajzade.realEstateAgency;

import android.os.Bundle;
import android.util.Log;
import android.view.WindowManager;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

public class FragmentActivity extends AppCompatActivity implements  TopFragment.OnItemSelectedOnTopFragment {
    TopFragment topFragment;
    BottomFragment bottomFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_fragment);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        if(topFragment == null){
            topFragment =  (TopFragment) getSupportFragmentManager().findFragmentById(R.id.fragmentTop);
        }

        bottomFragment = new BottomFragment();
        Bundle args = new Bundle();
        args.putInt("position", 1);
        bottomFragment.setArguments(args);

        FragmentManager fragmentManager = this.getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.dynamicFragmentLayout, bottomFragment);
        fragmentTransaction.commit();
    }

    @Override
    public void ImageChage(int index) {
        Log.d("FRAGMENT","FragmentActivity ImageChage() position: " + index);

        if(bottomFragment !=null && bottomFragment.isVisible())
            bottomFragment.changeImage(index);
    }
}
