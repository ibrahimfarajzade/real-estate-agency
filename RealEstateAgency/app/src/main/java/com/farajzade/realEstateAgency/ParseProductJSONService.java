package com.farajzade.realEstateAgency;

import android.app.IntentService;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

public class ParseProductJSONService extends IntentService{
    JSONArray products;
    ArrayList<Product> productList;
    private static final String TAG_PRODUCT = "product";
    private static final String TAG_ID = "id";
    private static final String TAG_NAME = "model";
    private static final String TAG_PRICE = "price";

    public ParseProductJSONService(){
        super("MyServiceProduct");
    }

    @Override
    protected void onHandleIntent(Intent intent) {
        productList = new ArrayList<Product>();

        String filename = intent.getStringExtra("filename");

        Log.d ("What is filename", filename);
        String jsonfileContent = loadFileFromAsset(filename);

        Log.d("Response: ", "> " + jsonfileContent);

        if (jsonfileContent != null) {
            try {
                JSONObject jsonObj = new JSONObject(jsonfileContent);

                // Getting JSON Array node
                products = jsonObj.getJSONArray(TAG_PRODUCT);

                // looping through all Contacts
                for (int i = 0; i < products.length(); i++) {
                    JSONObject course = products.getJSONObject(i);

                    String id = course.getString(TAG_ID);
                    String name = course.getString(TAG_NAME);
                    String price = course.getString(TAG_PRICE);

                    Product a = new Product(Integer.parseInt(id), name, Double.parseDouble(price));
                    productList.add(a);
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }

        Intent broascastIntent = new Intent();
        Bundle b = new Bundle();
        b.putParcelableArrayList("pr", productList);
        broascastIntent.putExtras(b);

        broascastIntent.setAction("PRODUCT_JSON_PARSE_ACTION");

        getBaseContext().sendBroadcast(broascastIntent);

        Log.d("Service"," :service END" );


    }
    private String loadFileFromAsset(String fileName) {
        String jsonfileContent = null;
        try {

            InputStream is = getBaseContext().getAssets().open(fileName);
            int size = is.available();
            byte[] buffer = new byte[size];

            is.read(buffer);
            is.close();

            jsonfileContent = new String(buffer, "UTF-8");

        } catch (IOException ex) {
            ex.printStackTrace();
            return null;
        }

        return jsonfileContent;
    }

}
