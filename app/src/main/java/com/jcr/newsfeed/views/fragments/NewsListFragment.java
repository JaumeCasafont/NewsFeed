package com.jcr.newsfeed.views.fragments;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.jcr.newsfeed.R;
import com.jcr.newsfeed.adapters.NewsAdapter;
import com.jcr.newsfeed.api.models.NewsListModel;
import com.jcr.newsfeed.api.models.NewsModel;
import com.jcr.newsfeed.presenters.NewsListPresenter;
import com.jcr.newsfeed.views.activities.DetailActivity;
import com.jcr.newsfeed.views.interfaces.INewsListView;

import butterknife.BindView;

/**
 * Created by jaucr on 08/04/2017.
 */


public class NewsListFragment extends BaseFragment<NewsListPresenter> implements INewsListView, NewsAdapter.OnItemSelectedCallback {


    @BindView(R.id.recyclerview_news) RecyclerView mRecyclerView;

    @BindView(R.id.tv_error_message_display) TextView mErrorMessageDisplay;


    private NewsAdapter mNewsAdapter;
    private NewsListPresenter mPresenter;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public void onResume() {
        super.onResume();

        getNews();
    }


    @Override
    protected void initializeData(){
        mNewsAdapter = new NewsAdapter(getContext(), this);
        mRecyclerView.setAdapter(mNewsAdapter);

        int recyclerViewOrientation = LinearLayoutManager.VERTICAL;

        boolean shouldReverseLayout = false;
        LinearLayoutManager layoutManager
                = new LinearLayoutManager(getContext(), recyclerViewOrientation, shouldReverseLayout);
        mRecyclerView.setLayoutManager(layoutManager);
    }
    /**
     * Presenter implementation
     */

    @Override
    protected int getLayoutResourceId() {
        return R.layout.fragment_news_list;
    }

    @Override
    protected NewsListPresenter getPresenter() {
        if (mPresenter == null) {
            mPresenter = new NewsListPresenter();
        }

        return mPresenter;
    }


    /**
     * Interface implementation
     */

    @Override
    public void getNews() {

        if (mNewsAdapter.getItemCount() == 0) {
            mPresenter.getNews();
        }
    }

    @Override
    public void drawNews(NewsListModel newsModels) {
        mNewsAdapter.setItems(newsModels.getNewsArray());
    }

    /**
     * Adapter
     */

    @Override
    public void onClick(NewsModel newsModel) {
        Intent intent = new Intent();

        //bundle.putParcelable(getResources().getString(R.string.news_model_key), newsModel);
        intent.putExtra(getResources().getString(R.string.news_model_key),newsModel);
        intent.setClass(getActivity(), DetailActivity.class);

        startActivity(intent);
    }
}
