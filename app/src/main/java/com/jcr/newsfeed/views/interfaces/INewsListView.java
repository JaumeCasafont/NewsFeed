package com.jcr.newsfeed.views.interfaces;

import com.jcr.newsfeed.api.models.NewsListModel;

/**
 * Created by jaucr on 08/04/2017.
 */

public interface INewsListView extends IBaseView {

    void getNews();

    void drawNews(NewsListModel newsListModel);
}
