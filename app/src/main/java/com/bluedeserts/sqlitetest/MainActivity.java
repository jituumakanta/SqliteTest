package com.bluedeserts.sqlitetest;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        database database=new database(this);
        //database.addCartRow("999","789",9,900,"hhh","hj","hj","hhh","hhh","jjj","hhh");

        database.deleteRecord();
        for(String obj:database.getAllID1()){
           /* for(String obj1:database.getAllID(obj)){

                System.out.println("allidd"+obj1);
            }*/
            System.out.println("allid"+database.getAllID(obj));
            String s = database.getAllID(obj).get("message");
            System.out.println("fff"+s);
        }


    }
}
