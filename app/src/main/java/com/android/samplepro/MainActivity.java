package com.android.samplepro;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;

import androidx.appcompat.app.AppCompatActivity;

import com.android.samplepro.activitys.SearchResultActivity;
import com.android.samplepro.filter.FilterActivity;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    LinearLayout layoutApartments, layoutLands, layoutVillas, layout_search;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        layoutApartments = findViewById(R.id.layoutApartments);
        layoutLands = findViewById(R.id.layoutLands);
        layoutVillas = findViewById(R.id.layoutVillas);
        layout_search = findViewById(R.id.layout_search);
        layoutApartments.setOnClickListener(this);
        layoutLands.setOnClickListener(this);
        layoutVillas.setOnClickListener(this);
        layout_search.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.layoutApartments:
                Intent intentApartment = new Intent(getApplicationContext(), SearchResultActivity.class);
                startActivity(intentApartment);

                break;
            case R.id.layoutLands:
                Intent intentLand = new Intent(getApplicationContext(), SearchResultActivity.class);
                startActivity(intentLand);
                break;
            case R.id.layoutVillas:
                Intent intentVilla = new Intent(getApplicationContext(), SearchResultActivity.class);
                startActivity(intentVilla);
                break;
            case R.id.layout_search:
                Intent intentFilter= new Intent(getApplicationContext(), FilterActivity.class);
                startActivity(intentFilter);
                break;


        }
    }
}