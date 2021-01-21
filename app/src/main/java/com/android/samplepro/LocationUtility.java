package com.android.samplepro;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.widget.Toast;

import androidx.core.app.ActivityCompat;

import java.io.IOException;
import java.util.List;
import java.util.Locale;



public class LocationUtility implements LocationListener {
    Context context;
    Location mLocation;
    LocationManager locManager;
    boolean isGPSEnable = false, isNetworkEnable = false;

    public LocationUtility(Context context) {
        this.context = context;
        locManager = (LocationManager) context.getSystemService(Context.LOCATION_SERVICE);
        isGPSEnable = locManager.isProviderEnabled(LocationManager.GPS_PROVIDER);
        isNetworkEnable = locManager.isProviderEnabled(LocationManager.NETWORK_PROVIDER);
        if (isGPSEnable) {
            if (ActivityCompat.checkSelfPermission(context, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(context, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
                return;
            }
            locManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 0, 0, this);
        } else if (isNetworkEnable) {
            if (ActivityCompat.checkSelfPermission(context, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(context, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
                return;
            }
            locManager.requestLocationUpdates(LocationManager.NETWORK_PROVIDER, 0, 0, this);
        } else {
            Toast.makeText(context, "Location Proider Not Enable", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onLocationChanged(Location location) {
        this.mLocation = location;
        getLastLocation();
    }

    @Override
    public void onStatusChanged(String provider, int status, Bundle extras) {

    }

    @Override
    public void onProviderEnabled(String provider) {

    }

    @Override
    public void onProviderDisabled(String provider) {

    }

    public Location getLastLocation() {
        return mLocation;
    }

    public Address getAddressForLocation(Location location) throws IOException {

        if (location == null) {
            return null;
        }
        double latitude = location.getLatitude();
        double longitude = location.getLongitude();
        int maxResults = 1;

        Geocoder gc = new Geocoder(context, Locale.getDefault());
        List<Address> addresses = gc.getFromLocation(latitude, longitude, maxResults);

        if (addresses.size() == 1) {
            return addresses.get(0);
        } else {
            return null;
        }
    }

    public Location getLocationFromAddress(String strAddress) {

        Geocoder geocoder = new Geocoder(context);
        List<Address> address;
        Location p1 = null;

        try {
            // May throw an IOException
            address = geocoder.getFromLocationName(strAddress, 1);
            if (address == null) {
                return null;
            }
            if (address.size() > 0) {
                Address location = address.get(0);
                p1 = new Location("");
                p1.setLatitude(location.getLatitude());
                p1.setLongitude(location.getLongitude());
            }

        } catch (IOException ex) {

            ex.printStackTrace();
        }

        return p1;
    }

    public String getCompleteAddressString(double LATITUDE, double LONGITUDE) {
        String strAdd = "", pincode = "";
        Geocoder geocoder = new Geocoder(context, Locale.getDefault());
        try {
            List<Address> addresses = geocoder.getFromLocation(LATITUDE, LONGITUDE, 1);
            if (addresses != null) {
                Address returnedAddress = addresses.get(0);
                StringBuilder strReturnedAddress = new StringBuilder("");
                pincode = returnedAddress.getPostalCode();
                for (int i = 0; i <= returnedAddress.getMaxAddressLineIndex(); i++) {
                    strReturnedAddress.append(returnedAddress.getAddressLine(i)).append("\n");
                }
                strAdd = strReturnedAddress.toString() + "--" + pincode;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return strAdd;
    }
}
