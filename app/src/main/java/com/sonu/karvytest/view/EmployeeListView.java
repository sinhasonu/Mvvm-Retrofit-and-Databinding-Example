package com.sonu.karvytest.view;

import com.sonu.karvytest.EmployeeListModel;

public interface EmployeeListView extends BaseView {

    void onError(boolean isNetworkError);

    void onListItemClicked(EmployeeListModel.DataBean employeeListModel);
}
