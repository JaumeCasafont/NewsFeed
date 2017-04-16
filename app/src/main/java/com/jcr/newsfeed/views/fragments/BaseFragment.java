package com.jcr.newsfeed.views.fragments;

import android.os.Bundle;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.StringRes;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.jcr.newsfeed.R;
import com.jcr.newsfeed.presenters.BasePresenter;
import com.jcr.newsfeed.views.interfaces.IBaseView;

import butterknife.ButterKnife;

/**
 * For this MVP implementation all Fragment should extend these base classes so the presenter is attached and detached when needed.
 *
 */
public abstract class BaseFragment<P extends BasePresenter> extends Fragment implements IBaseView {


    protected View mRootView;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for fragments
        mRootView =  inflater.inflate(getLayoutResourceId(), container, false);

        ButterKnife.bind(this, mRootView);

        return mRootView;
    }

    @Override
    public void onStart() {
        super.onStart();

        initializeData();
        if (getPresenter() != null) {
            getPresenter().attach(this);
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (getPresenter() != null) {
            getPresenter().detach(this);
        }
    }


    protected void initializeData() {

    }

    /**
     * implement to specify layout view
     *
     * @return
     */
    protected abstract
    @LayoutRes
    int getLayoutResourceId();

    /**
     * implement to specify presenter instance
     *
     * @return
     */
    protected abstract P getPresenter();
}
