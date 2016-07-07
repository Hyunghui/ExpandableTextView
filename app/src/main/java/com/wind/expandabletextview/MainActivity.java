package com.wind.expandabletextview;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void onClick(View v){
        switch (v.getId()){
            case R.id.test1:
                Intent intent = new Intent(MainActivity.this, Ex1Activity.class);
                startActivity(intent);
                break;
            case R.id.test2:
                Intent intent2 = new Intent(MainActivity.this, Ex2Activity.class);
                startActivity(intent2);
                break;
            case R.id.test3:
                Intent intent3 = new Intent(MainActivity.this, Ex3Activity.class);
                startActivity(intent3);
                break;
            case R.id.test4:
                Intent intent4 = new Intent(MainActivity.this, Ex4Activity.class);
                startActivity(intent4);
                break;
            case R.id.test5:
                Intent intent5 = new Intent(MainActivity.this, Ex5Activity.class);
                startActivity(intent5);
                break;
        }
    }
}
