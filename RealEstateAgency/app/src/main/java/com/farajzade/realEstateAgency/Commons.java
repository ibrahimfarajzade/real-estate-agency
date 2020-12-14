package com.farajzade.realEstateAgency;

import android.database.Cursor;

import java.util.ArrayList;

public class Commons {
    public static Product products;
    public static Cursor cursor;
    public static int curentItemIndex = 0;
    public static ArrayList<Product> data;

    public static ArrayList<Product> getData() {
        return data;
    }
    public static void setData(ArrayList<Product> data) {
        Commons.data = data;
    }
    public static Product getProduct() {
        return products;
    }
    public static void setProduct(Product pro) {
        Commons.products = pro;
    }
}
