package com.sonu.karvytest.viewmodel;

import android.accounts.NetworkErrorException;

import com.sonu.karvytest.EmployeeListModel;
import com.sonu.karvytest.network.ApiService;
import com.sonu.karvytest.view.EmployeeListView;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.observers.DisposableSingleObserver;
import io.reactivex.schedulers.Schedulers;

public class EmployeeListViewModel extends BaseViewModel<EmployeeListView> {

    public void requestEmployeeList() {
        getNavigator().showProgress();
        DisposableSingleObserver request = ApiService.getInstance().getApiService().getEmployeeList()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(new DisposableSingleObserver<EmployeeListModel>() {
                    @Override
                    public void onSuccess(EmployeeListModel employeeListModel) {
                        getNavigator().dismissProgress();
                        if (employeeListModel != null)
                            getNavigator().onEmployeeDataFetched(employeeListModel);
                        else
                            getNavigator().onError(false);
                    }

                    @Override
                    public void onError(Throwable e) {
                        getNavigator().dismissProgress();
                        if (e instanceof NetworkErrorException)
                            getNavigator().onError(true);
                    }
                });
        addDisposable(request);
    }

}
