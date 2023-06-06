package com.spg2018184028.sprinter;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class EndActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_end);
    }

    public void onBtnRestartGame(View view) {
        Intent intent = new Intent(this, TitleActivity.class);
        startActivity(intent);
    }
}