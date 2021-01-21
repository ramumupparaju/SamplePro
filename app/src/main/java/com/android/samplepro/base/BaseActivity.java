package com.android.samplepro.base;

import android.os.Bundle;
import android.util.Pair;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public abstract class BaseActivity extends AppCompatActivity implements BaseView {
    protected abstract void initializePresenter();
    protected BasePresenter presenter;
    protected abstract void onCreateView(Bundle saveInstanceState);

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initializePresenter();
        onCreateView(savedInstanceState);
    }
    public void setBasePresenter(BasePresenter basePresenter) {
        presenter = basePresenter;
        if (presenter != null) {
            this.presenter.initialize(null);
        }
    }

    @Override
    public void showProgress(String message) {

    }

    @Override
    public void hideProgress() {

    }

    @Override
    public void showErrorMessage(String errorMessage) {

    }

    @Override
    public void handleException(Pair<Integer, String> error) {

    }
}
