package com.sonu.karvytest;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.view.View;

import androidx.annotation.LayoutRes;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.LifecycleOwner;

import com.sonu.karvytest.viewmodel.BaseViewModel;

import butterknife.ButterKnife;


public abstract class BaseActivity<V extends BaseViewModel> extends AppCompatActivity implements LifecycleOwner {

    protected V mViewModel;
    private ProgressDialog mProgressDialog;
    private View childView;

    /**
     * Create the {@link androidx.lifecycle.ViewModel} corresponding to this activity.
     *
     * @return #ViewModel for the current activity.
     */
    protected abstract V getViewModel();

    /**
     * Return the layout file resourceId to initialize for this activity.
     *
     * @return Layout file resource id.
     */
    public abstract @LayoutRes
    int getLayoutId();

    /**
     * Use this to initialize all the UI views.
     */
    public abstract void setUpUI(@Nullable Bundle savedInstanceState);

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getLayoutId());
        //Initializing the Butter knife library.
        ButterKnife.bind(this);
        //Initializing the ViewModel in the current activity.
        mViewModel = getViewModel();
        setUpUI(savedInstanceState);
    }

    @Override
    protected void onStart() {
        super.onStart();
    }

    @Override
    protected void onStop() {
        super.onStop();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mViewModel != null) {
            mViewModel.getCompositeDisposable().dispose();
            mViewModel.setNavigator(null);
        }
    }

    protected void showProgressDialog() {
        if (mProgressDialog == null) {
            mProgressDialog = ProgressDialog.show(this, "", "Loading..", true);
            //you usually don't want the user to stop the current process, and this will make sure of that
            mProgressDialog.setCancelable(false);
        }
        mProgressDialog.show();
    }

    protected void dismissProgressDialog() {
        if (mProgressDialog != null) {
            mProgressDialog.dismiss();
        }
    }
}
