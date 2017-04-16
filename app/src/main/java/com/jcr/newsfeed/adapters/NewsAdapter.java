package com.jcr.newsfeed.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.jcr.newsfeed.R;
import com.jcr.newsfeed.api.models.NewsModel;
import com.jcr.newsfeed.components.ImageUrl;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by jaucr on 08/04/2017.
 */

public class NewsAdapter extends RecyclerView.Adapter<NewsAdapter.NewViewHolder> {

    private final OnItemSelectedCallback callback;
    private List<NewsModel> items = new ArrayList();
    private LayoutInflater inflater;

    public NewsAdapter(final Context context,
                       final OnItemSelectedCallback callback) {

        this.callback = callback;
        inflater = LayoutInflater.from(context);
    }

    @Override
    public NewViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = inflater.inflate(R.layout.item_new, parent, false);
        NewViewHolder newViewHolder = new NewViewHolder(v);
        return newViewHolder;
    }

    @Override
    public void onBindViewHolder(NewViewHolder holder, int position) {
        NewsModel newsModel = items.get(position);
        holder.setItem(newsModel);
    }

    @Override
    public int getItemCount() {
        return items.size();
    }


    public interface OnItemSelectedCallback {

        void onClick(final NewsModel ride);

    }

    public void setItems(final List<NewsModel> items) {
        this.items = items;
        notifyDataSetChanged();
    }

    class NewViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        @BindView(R.id.tv_title_item) TextView title;
        @BindView(R.id.iv_image_item) ImageUrl image;
        @BindView(R.id.tv_text_item) TextView text;


        public NewViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            callback.onClick((NewsModel) view.getTag());
        }

        public void setItem(final NewsModel newModel) {
            itemView.setTag(newModel);
            title.setText(newModel.getTitle());
            image.setScale(5);
            image.setImageFromUrl(newModel.getThumbnail());

            ArrayList<String> tags = new ArrayList<>();
            tags = newModel.getTags();
            for (String tag: tags) {
                text.append("#" + tag);
            }
        }
    }
}
