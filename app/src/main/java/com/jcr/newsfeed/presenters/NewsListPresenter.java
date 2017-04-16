package com.jcr.newsfeed.presenters;

import android.util.Log;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.ValueEventListener;
import com.jcr.newsfeed.api.models.NewsListModel;
import com.jcr.newsfeed.api.models.NewsModel;
import com.jcr.newsfeed.views.interfaces.INewsListView;

import java.util.ArrayList;

import static android.content.ContentValues.TAG;

/**
 * Created by jaucr on 08/04/2017.
 */

public class NewsListPresenter extends BasePresenter<INewsListView> {

    public void getNews() {

        final NewsListModel newsListModel = new NewsListModel();

        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                // This method is called once with the initial value and again
                // whenever data at this location is updated.
                ArrayList<NewsModel> posts = new ArrayList<>();

                for (DataSnapshot postSnapshot: dataSnapshot.getChildren()) {
                    NewsModel post = postSnapshot.getValue(NewsModel.class);
                    posts.add(post);
                }

                newsListModel.setNewsArray(posts);
                getView().drawNews(newsListModel);
            }

            @Override
            public void onCancelled(DatabaseError error) {
                // Failed to read value
                Log.w(TAG, "Failed to read value.", error.toException());
            }
        });
    }
}
