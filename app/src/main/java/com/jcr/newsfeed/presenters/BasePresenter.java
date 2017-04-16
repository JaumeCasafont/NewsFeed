package com.jcr.newsfeed.presenters;

import android.support.annotation.NonNull;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.jcr.newsfeed.views.interfaces.IBaseView;

/**
 * Base class for the presenters when implementing MVP.
 * This class takes care of keeping a reference to the view and freeing it on detachment.
 *
 * @param<V>Type that represents the view of the MVP.Could be an Activity,a Fragment,
 * a AppView(my preferred option)or anything alse(although the former options are the
 * ones that make more sense)
 */
public abstract class BasePresenter<V extends IBaseView> {


    protected FirebaseDatabase database = FirebaseDatabase.getInstance();
    protected DatabaseReference myRef = database.getReference();
    /**
     * The attached view.
     */
    private V view = null;

    /**
     * Attach the provided view to the presenter. If a previous view was attached, it will lose the
     * attachment.
     *
     * @param view object that represent the view on the MVP pattern.
     */
    public final void attach(@NonNull final V view) {
        this.view = view;
        onViewAttached();
    }

    /**
     * This method is called just after a view is attached to the presenter.
     */
    public void onViewAttached(){

    }

    /**
     * Detach the view from the presenter. No further interaction between them will exist.
     * The view detached MUST be the view attached, or an exception will be thrown.
     *
     * @param view the MVP view to be detached
     */
    public final void detach(@NonNull final V view) {
        if (this.view.equals(view)) {
            beforeViewDetached();
            this.view = null;
        } else {
            throw new IllegalArgumentException();
        }
    }

    /**
     * This method is called just before detaching the view from the presenter.
     */
    public void beforeViewDetached(){

    }

    /**
     * @return true if there is a view attached, false otherwise
     */
    protected boolean hasView() {
        return view != null;
    }

    /**
     * To avoid NPE, is a good practice to call {@link BasePresenter#hasView()} always before calling
     * this method.
     *
     * @return the attached view
     */
    protected V getView() {
        if( !hasView() ){
            return null;
        }
        return view;
    }
}