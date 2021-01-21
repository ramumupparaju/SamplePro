package com.android.samplepro.services;

import com.android.samplepro.filter.model.SubLocationResponse;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface Apis {

    //https://eastface.in:8443/eastface-api-1.0.0/eastface-api/sublocations
    @GET("eastface-api/sublocations")
    Call<List<SubLocationResponse>> getSubLocation();


}
