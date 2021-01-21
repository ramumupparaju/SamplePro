package com.android.samplepro.activitys;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.android.samplepro.MainActivity;
import com.android.samplepro.R;
import com.android.samplepro.adapters.SearchResultAdapter;
import com.android.samplepro.filter.FilterActivity;

public class SearchResultActivity extends AppCompatActivity implements View.OnClickListener {
    RecyclerView rvListData;
    ImageView ivFilter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_result);
        rvListData = findViewById(R.id.rvListData);
        ivFilter = findViewById(R.id.ivFilter);
        ivFilter.setOnClickListener(this);
        SearchResultAdapter searchResultAdapter = new SearchResultAdapter();
        rvListData.setAdapter(searchResultAdapter);

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.ivFilter:
                Intent intentApartment = new Intent(getApplicationContext(), FilterActivity.class);
                intentApartment.putExtra("navigationCheck", "navigationCheck");
                startActivity(intentApartment);
               // finishAffinity();

                break;


        }

    }



    @Override
    public void onBackPressed() {
        //super.onBackPressed();
        if (getIntent().getStringExtra("navigationCheck") != null && !getIntent().getStringExtra("navigationCheck").isEmpty()) {
            Intent intent = new Intent(this, MainActivity.class);
            intent.putExtra("navigationCheck", "navigationCheck");
            startActivity(intent);
            finish();
        } else {
            finish();

        }


    }



}
