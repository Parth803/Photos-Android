package com.example.android10;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import model.Model;

public class SearchActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Model.init();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.search);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }
}
