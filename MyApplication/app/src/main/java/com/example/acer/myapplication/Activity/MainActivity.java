package com.example.acer.myapplication.Activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.example.acer.myapplication.R;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void showlistview(View view){
        Intent intent = new Intent(getApplicationContext(),ListView.class);
        startActivity(intent);
    }

    public void showgridview(View view){
        Intent intent2 = new Intent(getApplicationContext(),Gridview.class);
        startActivity(intent2);

    }


}
