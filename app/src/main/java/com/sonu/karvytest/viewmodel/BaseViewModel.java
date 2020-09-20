package com.sonu.karvytest.viewmodel;


import androidx.annotation.Nullable;
import androidx.lifecycle.ViewModel;

import com.sonu.karvytest.network.ApiService;
import com.sonu.karvytest.network.ServicesAPI;
import com.sonu.karvytest.view.BaseView;

import org.jetbrains.annotations.NotNull;

import java.lang.ref.WeakReference;

import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;

/**
 * Created by Sonu Sinha on 20/09/2020.
 */

public abstract class BaseViewModel<N extends BaseView> extends ViewModel {


    private CompositeDisposable mCompositeDisposable;

    private WeakReference<N> mNavigator;

    public ServicesAPI apiService;

    public BaseViewModel() {
        this.mCompositeDisposable = new CompositeDisposable();
        this.apiService = ApiService.getInstance().getApiService();
    }

    public @Nullable
    N getNavigator() {
        if (mNavigator != null) {
            return mNavigator.get();
        }
        return null;
    }

    public void setNavigator(@NotNull N navigator) {
        this.mNavigator = new WeakReference<>(navigator);
    }

    public CompositeDisposable getCompositeDisposable() {
        return mCompositeDisposable;
    }

    public void addDisposable(Disposable disposable) {
        this.mCompositeDisposable.add(disposable);
    }

    @Override
    protected void onCleared() {
        super.onCleared();
        if (mNavigator != null) {
            mNavigator = null;
        }
    }

}
