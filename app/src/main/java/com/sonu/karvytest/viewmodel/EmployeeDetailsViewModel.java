package com.sonu.karvytest.viewmodel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.sonu.karvytest.EmployeeListModel;

/**
 * Created by Sonu Sinha on 20/09/2020.
 */

public class EmployeeDetailsViewModel extends BaseViewModel {
    private MutableLiveData<EmployeeListModel.DataBean> detailsDataLivedata = new MutableLiveData<>();

    public LiveData<EmployeeListModel.DataBean> getDetailsDataLivedata() {
        return detailsDataLivedata;
    }

    public void setDetailsDataLivedata(EmployeeListModel.DataBean detailsDataLivedata) {
        this.detailsDataLivedata.setValue(detailsDataLivedata);
    }

}
