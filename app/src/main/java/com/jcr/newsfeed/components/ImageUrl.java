package com.jcr.newsfeed.components;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.util.AttributeSet;
import android.widget.ImageView;

import com.jcr.newsfeed.views.activities.MainActivity;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created by jaucr on 10/04/2017.
 */

public class ImageUrl extends ImageView {

    private float scale = 1;
    private Bitmap image;

    public ImageUrl(Context context) {
        super(context);
    }

    public ImageUrl(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public ImageUrl(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public ImageUrl(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }

    public void setImageFromUrl(String url) {
        new FetchImageTask().execute(url);
    }

    public void setScale(float scale) {
        this.scale = scale;
    }

    private void scaleImage() {
        if (image != null) {
            image = Bitmap.createScaledBitmap(image, (int) (image.getWidth() * scale), (int) (image.getHeight() * scale), true);
        }
    }

    private void setBitmap() {
        scaleImage();
        this.setImageBitmap(image);
    }

    public class FetchImageTask extends AsyncTask<String, Void, Bitmap> {

        @Override
        protected Bitmap doInBackground(String... params) {

            if (params.length == 0) {
                return null;
            }

            String imageUrl = params[0];

            Bitmap bmp = null;
            try {

                //XXX: Resolve shortened URL
                HttpURLConnection connection = (HttpURLConnection) new URL(imageUrl).openConnection();
                connection.setInstanceFollowRedirects(false);
                while (connection.getResponseCode() / 100 == 3) {
                    imageUrl = connection.getHeaderField("location");
                    connection = (HttpURLConnection) new URL(imageUrl).openConnection();
                }

                bmp = BitmapFactory.decodeStream(connection.getInputStream());
                return bmp;
            } catch (IOException e) {
                e.printStackTrace();
                return null;
            }
        }

        @Override
        protected void onPostExecute(Bitmap imageResponse) {
            image = imageResponse;
            setBitmap();
        }
    }
}
