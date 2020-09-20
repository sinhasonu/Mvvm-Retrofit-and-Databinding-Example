package com.sonu.karvytest;

import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.RecyclerView;

import com.sonu.karvytest.adapter.EmployeeListAdapter;
import com.sonu.karvytest.databinding.ActivityEmployeelistBinding;
import com.sonu.karvytest.view.EmployeeListView;
import com.sonu.karvytest.viewmodel.EmployeeListViewModel;

/**
 * Created by Sonu Sinha on 20/09/2020.
 */

public class EmployeeListActivity extends BaseActivity<EmployeeListViewModel> implements EmployeeListView {

    private EmployeeListAdapter employeeListAdapter;
    private ActivityEmployeelistBinding activityEmployeelistBinding;

    @Override
    protected EmployeeListViewModel getViewModel() {
        return ViewModelProviders.of(this).get(EmployeeListViewModel.class);
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_employeelist;
    }

    @Override
    public void setUpUI(@Nullable Bundle savedInstanceState) {
        mViewModel.setNavigator(this);
        setUpRecyclerView();
        addLiveDataObserver();
        adddListener();
    }

    private void adddListener() {
        activityEmployeelistBinding.editText.setOnEditorActionListener(new EditText.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if (actionId == EditorInfo.IME_ACTION_GO) {
                    mViewModel.requestEmployeeList();
                    return true;
                }
                return false;
            }
        });
    }

    @Override
    void setView() {
        activityEmployeelistBinding = DataBindingUtil.setContentView(EmployeeListActivity.this,
                getLayoutId());
        activityEmployeelistBinding.setEmployeelistviewmodel(mViewModel);
        activityEmployeelistBinding.executePendingBindings();
    }

    private void setUpRecyclerView() {
        employeeListAdapter = new EmployeeListAdapter(this, mViewModel);
        activityEmployeelistBinding.setMyAdapter(employeeListAdapter);
    }

    private void addLiveDataObserver() {
        mViewModel.getLiveEmployeeListResponse().observe(this, employeeListModel -> {
            if (employeeListModel.getData() != null) {
                employeeListAdapter.updateList();
                activityEmployeelistBinding.rvEmpList.setVisibility(View.VISIBLE);
                activityEmployeelistBinding.editText.setVisibility(View.GONE);
            }
        });
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
    public void onError(boolean isNetworkError) {
        if (isNetworkError)
            Toast.makeText(this, "Check your neetwork connection", Toast.LENGTH_SHORT).show();
        else
            Toast.makeText(this, "Something went wrong while fetching Employee list", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onListItemClicked(EmployeeListModel.DataBean employeeListModel) {
        Intent intent = new Intent(this, EmployeeDetailsActivity.class);
        intent.putExtra(EmployeeDetailsActivity.EMPOLYEE_DETAILS_DATA, employeeListModel);
        startActivity(intent);
    }
}