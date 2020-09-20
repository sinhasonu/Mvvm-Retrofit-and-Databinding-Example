package com.sonu.karvytest;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProviders;

import com.sonu.karvytest.databinding.ActivityEmdetailsBinding;
import com.sonu.karvytest.view.BaseView;
import com.sonu.karvytest.viewmodel.EmployeeDetailsViewModel;

/**
 * Created by Sonu Sinha on 20/09/2020.
 */

public class EmployeeDetailsActivity extends BaseActivity<EmployeeDetailsViewModel> implements BaseView {

    private ActivityEmdetailsBinding activityEmdetailsBinding;
    public static String EMPOLYEE_DETAILS_DATA = "EMPOLYEE_DETAILS_DATA";

    @Override
    protected EmployeeDetailsViewModel getViewModel() {
        return ViewModelProviders.of(this).get(EmployeeDetailsViewModel.class);
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_emdetails;
    }

    @Override
    public void setUpUI(@Nullable Bundle savedInstanceState) {
        mViewModel.setNavigator(this);
        fetchIntent();
        activityEmdetailsBinding.setViewModel(mViewModel);
        activityEmdetailsBinding.executePendingBindings();
    }

    private void fetchIntent() {
        if (getIntent() != null && getIntent().hasExtra(EMPOLYEE_DETAILS_DATA)) {
            EmployeeListModel.DataBean detailsData = getIntent().getParcelableExtra(EMPOLYEE_DETAILS_DATA);
            mViewModel.setDetailsDataLivedata(detailsData);
        }
    }

    @Override
    void setView() {
        activityEmdetailsBinding = DataBindingUtil.setContentView(this, getLayoutId());
    }

    @Override
    public void showProgress() {
        showProgressDialog();
    }

    @Override
    public void dismissProgress() {
        dismissProgressDialog();
    }
}