package com.bluedeserts.sqlitetest;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by jitu on 9/21/2017.
 */

public class database extends SQLiteOpenHelper {
    private static final int DATABASE_VERSION = 1;
    //database name
    private static final String DATABASE_NAME = "cart";
    //table name
    private static final String TABLE_NAME = "cartuser";
    // column name
    private static final String KEY_COLUMN_ID = "id";
    private static final String KEY_COLUMN_PRODUCTID = "productid";
    private static final String KEY_COLUMN_PRODUCTSKU = "sku";
    private static final String KEY_COLUMN_TOTALITEMS = "totalitems";
    private static final String KEY_COLUMN_TOTALPRICE = "totalprice";
    private static final String KEY_COLUMN_IMAGEICON = "imageicon";
    private static final String KEY_COLUMN_PRODUCTNAME = "productname";
    private static final String KEY_COLUMN_WEIGHT = "weight";
    private static final String KEY_COLUMN_MESSAGE = "message";
    private static final String KEY_COLUMN_TYPE = "type";
    private static final String KEY_COLUMN_DATE = "date";
    private static final String KEY_COLUMN_TIME = "time";

    public database(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String CREATE_LOGIN_TABLE = "CREATE TABLE " + TABLE_NAME + "(" + KEY_COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT," +
                KEY_COLUMN_PRODUCTID + " TEXT," + KEY_COLUMN_PRODUCTSKU + " TEXT," + KEY_COLUMN_TOTALITEMS + " INTEGER," + KEY_COLUMN_TOTALPRICE + " INTEGER," +
                KEY_COLUMN_IMAGEICON + " TEXT," + KEY_COLUMN_PRODUCTNAME + " TEXT," + KEY_COLUMN_WEIGHT + " TEXT," +
                KEY_COLUMN_MESSAGE + " TEXT," + KEY_COLUMN_TYPE + " TEXT," +
                KEY_COLUMN_DATE + " TEXT," + KEY_COLUMN_TIME + " TEXT" + ")";
        sqLiteDatabase.execSQL(CREATE_LOGIN_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        // Create tables again
        onCreate(sqLiteDatabase);
    }

    public void addCartRow(String productid, String sku, int totalitems, int totalprice, String imageicon,
                           String productname, String totalWeight, String message, String type,
                           String date, String time) {


        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(KEY_COLUMN_PRODUCTID, productid);
        values.put(KEY_COLUMN_PRODUCTSKU, sku);
        values.put(KEY_COLUMN_TOTALITEMS, totalitems);
        values.put(KEY_COLUMN_TOTALPRICE, totalprice);
        values.put(KEY_COLUMN_IMAGEICON, imageicon);
        values.put(KEY_COLUMN_PRODUCTNAME, productname);
        values.put(KEY_COLUMN_WEIGHT, totalWeight);
        values.put(KEY_COLUMN_MESSAGE, message);
        values.put(KEY_COLUMN_TYPE, type);
        values.put(KEY_COLUMN_DATE, date);
        values.put(KEY_COLUMN_TIME, time);


        // Inserting Row
        long id = db.insert(TABLE_NAME, null, values);
        //System.out.println("data inserted successfully");
        db.close(); // Closing database connection
    }


    public Map<String,String> getAllID(String id) {
        Map<String,String> map = new HashMap<>();

        //hp = new HashMap();
        SQLiteDatabase db = this.getReadableDatabase();

        String[] tablenames = {KEY_COLUMN_ID, KEY_COLUMN_IMAGEICON, KEY_COLUMN_DATE, KEY_COLUMN_MESSAGE, KEY_COLUMN_PRODUCTNAME};
        String whereClause = KEY_COLUMN_ID + "=?";
        String[] whereArgs = {id};
        Cursor res1 = db.query(TABLE_NAME, tablenames, whereClause, whereArgs, null, null, null, null);


        //Cursor res = db.rawQuery("SELECT  * FROM " + TABLE_NAME+"=?"+KEY_COLUMN_IMAGEICON, whereArgs);

        res1.moveToFirst();

        while (res1.isAfterLast() == false) {
            map.put("id",res1.getString(res1.getColumnIndex(KEY_COLUMN_ID)));
            map.put("message",res1.getString(res1.getColumnIndex(KEY_COLUMN_MESSAGE)));
            map.put("productname",res1.getString(res1.getColumnIndex(KEY_COLUMN_PRODUCTNAME)));
            res1.moveToNext();
        }
        return map;
    }


    public ArrayList<String> getAllID1() {
        ArrayList<String> array_list = new ArrayList<String>();

        SQLiteDatabase db = this.getReadableDatabase();



        String[] tablenames1 = {KEY_COLUMN_ID};
        Cursor res2 = db.query(TABLE_NAME, tablenames1, null, null, null, null, null, null);

        res2.moveToFirst();

        while (res2.isAfterLast() == false) {
            array_list.add(res2.getString(res2.getColumnIndex(KEY_COLUMN_ID)));
            res2.moveToNext();
        }
        return array_list;
    }



    public void deleteRecord(){
        SQLiteDatabase db = this.getWritableDatabase();
        String whereClause = KEY_COLUMN_ID + "=?";
        String[] whereArgs = {"2"};
        // Cursor res1 = db.query(TABLE_NAME, tablenames, whereClause, whereArgs, null, null, null, null);

        db.delete(TABLE_NAME,whereClause,whereArgs);
    }
}
