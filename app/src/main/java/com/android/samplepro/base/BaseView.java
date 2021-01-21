package com.android.samplepro.base;

import android.util.Pair;

public interface BaseView {


    void showProgress(String message);

    void hideProgress();

    void showErrorMessage(String errorMessage);

    void handleException(Pair<Integer, String> error);

}
