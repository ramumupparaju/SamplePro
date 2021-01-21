package com.android.samplepro.filter;


import com.android.samplepro.base.BaseView;
import com.android.samplepro.filter.model.SubLocationResponse;

import java.util.List;

public interface FilterView {

    interface View extends BaseView {
        void subLocationResponse(List<SubLocationResponse> subLocationResponse);
    }
    interface Presenter {
        void loadSubLocation();

    }
}
