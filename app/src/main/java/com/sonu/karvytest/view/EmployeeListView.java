package com.sonu.karvytest.view;

import com.sonu.karvytest.EmployeeListModel;

public interface EmployeeListView extends BaseView {
    public void onEmployeeDataFetched(EmployeeListModel response);

    void onError(boolean isNetworkError);
}
