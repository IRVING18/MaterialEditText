package com.material.demo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final MaterialEditText materialEditText = findViewById(R.id.material);

        materialEditText.postDelayed(new Runnable() {
            @Override
            public void run() {
                materialEditText.setUseTopTagLabel(true);
            }
        },3000);
    }
}
