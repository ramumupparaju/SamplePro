package com.android.samplepro.filter;

import com.android.samplepro.base.BasePresenter;
import com.android.samplepro.filter.model.Location;
import com.android.samplepro.filter.model.SubLocationResponse;
import com.android.samplepro.services.Apiservices;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class FilterPresenter extends BasePresenter<FilterView.View> implements FilterView.Presenter {
    private Apiservices apiservices;

    public FilterPresenter() {
        if (this.apiservices == null) {
            this.apiservices = new Apiservices();
        }
    }

    @Override
    public void loadSubLocation() {


        apiservices.getBaseUrl().getSubLocation().enqueue(new Callback<List<SubLocationResponse>>() {
            @Override
            public void onResponse(Call<List<SubLocationResponse>> call, Response<List<SubLocationResponse>> response) {
                List<SubLocationResponse> data = response.body();
                if (data != null && data != null) {
                    getView().subLocationResponse(data);
                }
            }

            @Override
            public void onFailure(Call<List<SubLocationResponse>> call, Throwable t) {
                try {
                    throw new InterruptedException("Something went wrong!");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });
    }
}
