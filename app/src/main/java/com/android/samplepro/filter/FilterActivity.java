package com.android.samplepro.filter;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.LinearLayout;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.core.app.ActivityCompat;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.android.samplepro.LocationUtility;
import com.android.samplepro.R;
import com.android.samplepro.activitys.SearchResultActivity;
import com.android.samplepro.adapters.AvaliabilityAdapter;
import com.android.samplepro.adapters.BedroomsAdapter;
import com.android.samplepro.base.BaseActivity;
import com.android.samplepro.filter.model.SubLocationResponse;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.libraries.places.api.Places;
import com.google.android.libraries.places.api.model.Place;
import com.google.android.libraries.places.widget.Autocomplete;
import com.google.android.libraries.places.widget.model.AutocompleteActivityMode;
import com.jaredrummler.materialspinner.MaterialSpinner;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class FilterActivity extends BaseActivity implements /*AdapterView.OnItemSelectedListener,*/ View.OnClickListener, FilterView.View {
    //Spinner spinnerLocation;
    MaterialSpinner spinnerLocation;
    String[] city = {"Bangalore", "Hyderabad", "Chennai", "Delih", "Other"};
    RecyclerView rvBedRooms, rvAvaliability;
    TextView tvSearch, tvSeekBarValue, tvLocation;
    ArrayAdapter cityAdapter;
    SeekBar seekbarPrice;
    LinearLayout searchLocation;
    private int REQUEST_CODE_AUTOCOMPLETE = 111;
    private LocationUtility locationUtility;
    private double selected_lat, selected_lon;
    private String selected_pin = "";
    AutoCompleteTextView autocomplete;

    String[] arr = {"Paries,France", "PA,United States", "Parana,Brazil", "Padua,Italy", "Pasadena,CA,United States"};

    FilterPresenter filterPresenter;


    @Override
    protected void initializePresenter() {
        filterPresenter = new FilterPresenter();
        filterPresenter.setView(this);
        setBasePresenter(filterPresenter);

    }

    @Override
    protected void onCreateView(Bundle saveInstanceState) {
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_filter);
        autocomplete = (AutoCompleteTextView) findViewById(R.id.autoCompleteTextView1);

        spinnerLocation = findViewById(R.id.spinnerLocation);
        rvBedRooms = findViewById(R.id.rvBedRooms);
        rvAvaliability = findViewById(R.id.rvAvaliability);
        tvSearch = findViewById(R.id.tvSearch);
        tvSeekBarValue = findViewById(R.id.tvSeekBarValue);
        tvSeekBarValue.setText("Rent Range:" + getApplicationContext().getString(R.string.price_symbol) + " 0 to " + getApplicationContext().getString(R.string.price_symbol) + " 0");
        seekbarPrice = findViewById(R.id.seekbarPrice);
        searchLocation = findViewById(R.id.searchLocation);
        locationUtility = new LocationUtility(getApplicationContext());
        tvLocation = findViewById(R.id.tvLocation);
        tvSearch.setOnClickListener(this);
        searchLocation.setOnClickListener(this);
        seekbarPrice.setMax(50000);
        seekbarPrice.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            int pval = 0;

            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                pval = progress;
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
                //write custom code to on start progress
            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

                tvSeekBarValue.setText("Rent Range:" + getApplicationContext().getString(R.string.price_symbol)
                        + " 0 to " + getApplicationContext().getString(R.string.price_symbol) + " " + pval);


            }
        });
        filterPresenter.loadSubLocation();

       /* autocomplete = (AutoCompleteTextView) findViewById(R.id.autoCompleteTextView1);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.select_dialog_item, arr);
        autocomplete.setThreshold(2);
        autocomplete.setAdapter(adapter);*/

        BedroomsAdapter bedroomsAdapter = new BedroomsAdapter();
        rvBedRooms.setAdapter(bedroomsAdapter);
        GridLayoutManager gridLayoutManager = new GridLayoutManager(this, 3, LinearLayoutManager.VERTICAL, false);
        rvBedRooms.setLayoutManager(gridLayoutManager);
        rvBedRooms.setAdapter(bedroomsAdapter);

        AvaliabilityAdapter avaliabilityAdapter = new AvaliabilityAdapter();
        rvAvaliability.setAdapter(avaliabilityAdapter);
        GridLayoutManager layoutManager = new GridLayoutManager(this, 2, LinearLayoutManager.VERTICAL, false);
        rvAvaliability.setLayoutManager(layoutManager);
        rvAvaliability.setAdapter(avaliabilityAdapter);

        cityAdapter = new ArrayAdapter(this, android.R.layout.simple_spinner_item, city);
        cityAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerLocation.setAdapter(cityAdapter);


        spinnerLocation.setOnItemSelectedListener(new MaterialSpinner.OnItemSelectedListener<String>() {

            @Override
            public void onItemSelected(MaterialSpinner view, int position, long id, String item) {

                Toast.makeText(getApplicationContext(), city[position], Toast.LENGTH_LONG).show();


            }
        });
    }

  /*  @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int position, long l) {
        Toast.makeText(getApplicationContext(), country[position], Toast.LENGTH_LONG).show();
    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }*/


    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.tvSearch:
                Intent intentApartment = new Intent(getApplicationContext(), SearchResultActivity.class);
                startActivity(intentApartment);
                break;
            case R.id.searchLocation:
                openAutocompleteActivity();
                break;


        }

    }

    private void openAutocompleteActivity() {
        try {

            if (!Places.isInitialized()) {
                Places.initialize(getApplicationContext(), getResources().getString(R.string.google_maps_key));
            }
            List<Place.Field> fields = Arrays.asList(Place.Field.ID, Place.Field.NAME, Place.Field.LAT_LNG);
            Intent intent = new Autocomplete.IntentBuilder(AutocompleteActivityMode.FULLSCREEN, fields).build(this);
            startActivityForResult(intent, REQUEST_CODE_AUTOCOMPLETE);
        } catch (Exception e) {
            String message = "Google Play Services is not available: " + e.getLocalizedMessage();
            Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
        }
    }


    @Override
    public void onBackPressed() {
        //super.onBackPressed();
        if (getIntent().getStringExtra("navigationCheck") != null && !getIntent().getStringExtra("navigationCheck").isEmpty()) {
            Intent intent = new Intent(this, SearchResultActivity.class);
            intent.putExtra("navigationCheck", "navigationCheck");
            startActivity(intent);
            finish();
        } else {
            finish();

        }


    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        try {

            // Check that the result was from the autocomplete widget.
            if (requestCode == REQUEST_CODE_AUTOCOMPLETE) {
                Place place = Autocomplete.getPlaceFromIntent(data);
                Log.i("test", "Place: " + place.getName() + ", " + place.getId());
                LatLng latLong = place.getLatLng();
                tvLocation.setText(place.getAddress());
                double searchLattitude = latLong.latitude;
                double searchLongitude = latLong.longitude;
                updateLocation(searchLattitude, searchLongitude);
                //   markerForGeofence(latLong);
                CameraPosition cameraPosition = new CameraPosition.Builder()
                        .target(latLong).zoom(17f)/*.tilt(70)*/.build();

                if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
                    // TODO: Consider calling
                    //    ActivityCompat#requestPermissions
                    // here to request the missing permissions, and then overriding
                    //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
                    //                                          int[] grantResults)
                    // to handle the case where the user grants the permission. See the documentation
                    // for ActivityCompat#requestPermissions for more details.
                    return;
                }
                /*map.setMyLocationEnabled(true);
                //map.setMapType(GoogleMap.MAP_TYPE_SATELLITE);
                map.setPadding(0, 80, 0, 80);
                map.setBuildingsEnabled(true);
                map.animateCamera(CameraUpdateFactory.newCameraPosition(cameraPosition));*/

            } else if (resultCode == this.RESULT_CANCELED) {
                // Indicates that the activity closed before a selection was made. For example if
                // the user pressed the back button.
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void updateLocation(double searchLattitude, double searchLongitude) {
        try {
            String name = locationUtility.getCompleteAddressString(searchLattitude, searchLongitude);
            selected_lat = searchLattitude;
            selected_lon = searchLongitude;
            String[] split_address = name.split("--");
            tvLocation.setText(split_address[0]);
            selected_pin = split_address[1];
        } catch (Exception e) {
            Log.e("exp", e.toString());
        }

    }

    @Override
    public void subLocationResponse(List<SubLocationResponse> responses) {

        ArrayList<String> subLocation = new ArrayList<>();
        List<SubLocationResponse> subLocationResponses = responses;
        if (subLocationResponses.size() > 0) {
            for (int i = 0; i < subLocationResponses.size(); i++) {
                String subLocationStr = subLocationResponses.get(i).getSubname();
                subLocation.add(subLocationStr);
            }
            ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.select_dialog_item, subLocation);
            autocomplete.setThreshold(2);
            autocomplete.setAdapter(adapter);


        }
        System.out.println("");
        System.out.println("");

    }
}
