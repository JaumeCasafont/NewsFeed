package com.jcr.newsfeed.views.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import com.jcr.newsfeed.R;
import com.jcr.newsfeed.api.models.NewsModel;
import com.jcr.newsfeed.components.ImageUrl;
import com.jcr.newsfeed.presenters.BasePresenter;

import butterknife.BindView;

/**
 * Created by jaucr on 15/04/2017.
 */

public class NewsDetailFragment extends BaseFragment {

    @BindView(R.id.tv_detail_title) TextView newTitle;

    @BindView(R.id.iv_detail_image) ImageUrl imageUrl;

    @BindView(R.id.tv_detail_text) TextView longText;

    private NewsModel newModel;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Intent intent = getActivity().getIntent();
        if (intent != null)
            newModel = intent.getParcelableExtra(getResources().getString(R.string.news_model_key));
    }

    @Override
    protected void initializeData(){
        newTitle.setText(newModel.getTitle());
        imageUrl.setImageFromUrl(newModel.getPicture());
        longText.setText(newModel.getText());
    }

    @Override
    protected int getLayoutResourceId() {
        return R.layout.fragment_news_detail;
    }

    @Override
    protected BasePresenter getPresenter() {
        return null;
    }
}
