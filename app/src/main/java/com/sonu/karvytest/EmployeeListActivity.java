package com.sonu.karvytest;

import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.lifecycle.ViewModelProviders;

import com.sonu.karvytest.view.EmployeeListView;
import com.sonu.karvytest.viewmodel.EmployeeListViewModel;

public class EmployeeListActivity extends BaseActivity<EmployeeListViewModel> implements EmployeeListView {

    @Override
    protected EmployeeListViewModel getViewModel() {
        return ViewModelProviders.of(this).get(EmployeeListViewModel.class);
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_main;
    }

    @Override
    public void setUpUI(@Nullable Bundle savedInstanceState) {
        mViewModel.setNavigator(this);
    }

    @Override
    public void showProgress() {
        showProgressDialog();
    }

    @Override
    public void dismissProgress() {
        dismissProgressDialog();
    }

    @Override
    public void onEmployeeDataFetched(EmployeeListModel response) {

    }

    @Override
    public void onError(boolean isNetworkError) {
        if (isNetworkError)
            Toast.makeText(this, "Check your neetwork connection", Toast.LENGTH_SHORT).show();
        else
            Toast.makeText(this, "Something went wrong while fetching Employee list", Toast.LENGTH_SHORT).show();
    }

    public void fetchData(View view) {
        mViewModel.requestEmployeeList();
    }
}