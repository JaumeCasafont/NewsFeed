package com.jcr.newsfeed.views.interfaces;

import android.app.Activity;
import android.content.Context;
import android.support.annotation.StringRes;

/**
 * All views should extend from this interface.
 */
public interface IBaseView {

    Context getContext();

    Activity getActivity();

}
