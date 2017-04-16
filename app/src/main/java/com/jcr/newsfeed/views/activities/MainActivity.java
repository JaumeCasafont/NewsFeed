package com.jcr.newsfeed.views.activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.jcr.newsfeed.R;
import com.jcr.newsfeed.views.fragments.NewsListFragment;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        NewsListFragment newsListFragment = new NewsListFragment();
        getSupportFragmentManager().beginTransaction()
                .add(R.id.fragment_container, newsListFragment).commit();
    }
}
