package com.farajzade.realEstateAgency;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Dialog;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class SecondActivity extends AppCompatActivity {
    RecyclerView recyclerView;
    Dialog customDialog;

    SQLiteDatabase db;
    List<Product> data;
    DatabaseHelper dbHelper;
    EditText etProductId;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_second);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        dbHelper = new DatabaseHelper(this);
        customDialog = new Dialog(this );

        etProductId = findViewById(R.id.etProductId);
        data = ProductDB.getAllProducts(dbHelper);

        recyclerView = findViewById(R.id.recyclerView);

        LinearLayoutManager layoutManaget = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManaget);
    }

    public void onClick(View v){
        String key = etProductId.getText().toString();

        Commons.data = (ArrayList<Product>) ProductDB.findPro(dbHelper,key );

        if(Commons.data.isEmpty()) {
            Toast.makeText(getBaseContext(), "No product found!", Toast.LENGTH_SHORT).show();
        } else {
            MyRecyclerViewAdapter adapter = new MyRecyclerViewAdapter(this);
            recyclerView.setAdapter(adapter);
        }
    }

    public void displayDialog(final String msg, final String model){
        final TextView tv;
        ImageView img;
        Button btnClose;

        customDialog = new Dialog(this);

        customDialog.setContentView(R.layout.dialog);
        tv =  customDialog.findViewById(R.id.tvDialogName);

        btnClose = customDialog.findViewById(R.id.btnClose);
        tv.setText(msg+"");

        btnClose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                customDialog.dismiss();
            }
        });
        customDialog.show();
    }
}
