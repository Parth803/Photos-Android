package com.example.android10;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import model.Model;

public class EditActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.edit);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }
}
