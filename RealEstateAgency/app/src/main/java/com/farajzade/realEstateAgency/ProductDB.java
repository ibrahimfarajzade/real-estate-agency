package com.farajzade.realEstateAgency;

import android.content.ContentValues;
import android.database.Cursor;

import java.util.ArrayList;
import java.util.List;

public class ProductDB {
    public static final String TABLE_NAME="products";
    public static final String FIELD_ID = "id";
    public static final String FIELD_NAME = "name";
    public static final String FIELD_PRICE = "price";

    public static final String CREATE_TABLE_SQL = "CREATE TABLE " + TABLE_NAME + " (" + FIELD_ID + " number, " + FIELD_NAME + " text, "+ FIELD_PRICE + " number);";
    public static final String DROP_TABLE_SQL = "DROP TABLE if exists " + TABLE_NAME;

    public static List<Product> getAllProducts(DatabaseHelper db){
        Cursor cursor = db.getAllRecords(TABLE_NAME, null);

        List<Product> data = new ArrayList<>();
        Product pro = null;
        while (cursor.moveToNext()) {
            int id = cursor.getInt(0);
            String name = cursor.getString(1);
            double price = cursor.getDouble(2);

            pro = new Product(id, name, price);
            data.add(pro);
        }
        return data;
    }

    public static List<Product> findPro(DatabaseHelper db, String key){
        String where = FIELD_NAME +" like '%"+key+"%'";
        Cursor cursor = db.getSomeRecords(TABLE_NAME,null, where);
        List<Product> data = new ArrayList<>();
        Product pro = null;
        while (cursor.moveToNext()) {
            int id = cursor.getInt(0);
            String name = cursor.getString(1);
            double price = cursor.getDouble(2);

            pro = new Product(id, name, price);
            data.add(pro);
        }

        return data;
    }

    public static List<Product> findJsons(DatabaseHelper db, int key){
        String where = FIELD_ID +" = "+ key;
        Cursor cursor = db.getSomeRecords(TABLE_NAME,null, where);
        List<Product> data = new ArrayList<>();
        Product pro = null;
        while (cursor.moveToNext()) {
            int id = cursor.getInt(0);
            String name = cursor.getString(1);
            double price = cursor.getDouble(2);

            pro = new Product(id, name, price);
            data.add(pro);
        }
        return data;
    }

    public static long insertPro(DatabaseHelper db, int id, String name, double price) {
        ContentValues contentValues = new ContentValues();
        contentValues.put(FIELD_ID, id);
        contentValues.put(FIELD_NAME, name);
        contentValues.put(FIELD_PRICE, price);

        long res = db.insert(TABLE_NAME,contentValues);

        return res;
    }

    public static boolean updatePro(DatabaseHelper db, int id, String name, double price) {
        ContentValues contentValues = new ContentValues( );
        contentValues.put(FIELD_ID, id);
        contentValues.put(FIELD_NAME, name);
        contentValues.put(FIELD_PRICE, price);
        String where = FIELD_ID + " = " + id;

        boolean res = db.update(TABLE_NAME, contentValues, where);

        return res;
    }

    public static boolean deletePro(DatabaseHelper db, int id){
        String where = FIELD_ID + " = " + id;

        boolean res = db.delete(TABLE_NAME,where);
        return res;
    }
}
