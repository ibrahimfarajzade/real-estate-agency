package com.farajzade.realEstateAgency;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GestureDetectorCompat;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.util.Log;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    ImageView imgView;

    IntentFilter mIntentFilter;
    DatabaseHelper dbHelper;

    EditText etId, etName, etPrice;
    TextView tvResult;

    ArrayList<Product> productList;

    private GestureDetectorCompat mDetector;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_main);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        imgView = (ImageView) findViewById(R.id.imgView);

        mDetector = new GestureDetectorCompat(this, new MyGestureListener());
        mDetector.setOnDoubleTapListener(new MyGestureListener());

        dbHelper = new DatabaseHelper(this);

        Log.d("DATABASE", "OK");
        etId = findViewById(R.id.etId);
        etName = findViewById(R.id.etName);
        etPrice = findViewById(R.id.etId);

        Intent intent = new Intent(this, ParseProductJSONService.class);
        intent.putExtra("filename", "product.json");
        startService(intent);

        Log.d("Here",":intentFilter" );
        mIntentFilter = new IntentFilter();
        mIntentFilter.addAction("PRODUCT_JSON_PARSE_ACTION");

        // Register the receiver
        registerReceiver(mIntentReceiver, mIntentFilter);

        //sound
        final MediaPlayer player = MediaPlayer.create(this, R.raw.song);
        player.start();
    }

    private BroadcastReceiver mIntentReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            // Storing the received data into a Bundle

            Log.d("Service","Broadcast message taken" );
            Bundle b = intent.getExtras();
            productList = b.getParcelableArrayList("pr");
            Product x;
            for(int i = 0; i < productList.size(); i++) {
                x = productList.get(i);
                Commons.data = (ArrayList<Product>) ProductDB.findJsons(dbHelper, x.getId());

                if(Commons.data.isEmpty())
                    ProductDB.insertPro(dbHelper, x.getId(), x.getName(), x.getPrice());
            }
        }
    };

    public void onClick(View view) {
        boolean res = false;

        int id = 0;

        if (!etId.getText().toString().equals(""))
            id = Integer.parseInt(etId.getText().toString());

        if (view.getId() == R.id.btnAdd) {
            String name = etName.getText().toString();
            double price = Double.parseDouble(etPrice.getText().toString());

            long resinser = ProductDB.insertPro(dbHelper, id, name, price);

            if (resinser > 0)
                displayToast("Product" + name + " is added");

        } else if (view.getId() == R.id.btnUpdate) {
            String name = etName.getText().toString();
            double price = Double.parseDouble(etPrice.getText().toString());

            res = ProductDB.updatePro(dbHelper, id, name, price);
            if (res)
                displayToast("Update is done");

        } else if (view.getId() == R.id.btnDelete) {
            res = ProductDB.deletePro(dbHelper, id);
            if (res)
                displayToast("Delete is done");
        }
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        mDetector.onTouchEvent(event);
        return super.onTouchEvent(event);
    }

    public class MyGestureListener extends GestureDetector.SimpleOnGestureListener {

        @Override
        public boolean onDown(MotionEvent event) {

            return true;
        }

        @Override
        public boolean onSingleTapConfirmed(MotionEvent e) {
            Intent intent = new Intent(MainActivity.this, SecondActivity.class);
            startActivity(intent);

            return true;
        }

        @Override
        public boolean onDoubleTapEvent(MotionEvent event) {
            Intent intent = new Intent(MainActivity.this, FragmentActivity.class);
            startActivity(intent);

            return true;
        }
    }

    //Toast message
    private void displayToast(String msg) {
        Toast.makeText(getBaseContext(), msg, Toast.LENGTH_SHORT).show();
    }
}
