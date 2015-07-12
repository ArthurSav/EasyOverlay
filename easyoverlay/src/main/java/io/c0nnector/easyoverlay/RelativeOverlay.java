package io.c0nnector.easyoverlay;

import android.content.Context;
import android.support.annotation.DrawableRes;
import android.transitions.everywhere.Fade;
import android.transitions.everywhere.Slide;
import android.transitions.everywhere.Transition;
import android.transitions.everywhere.TransitionManager;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.RelativeLayout;

import java.util.ArrayList;

import io.c0nnector.easyoverlay.views.ViewError;
import io.c0nnector.easyoverlay.views.ViewLoading;

/**
 * Makes it easier to add overlay views like 'loading, errors, custom' etc...
 */
public class RelativeOverlay extends RelativeLayout {

    /**
     * Animation used when swapping views. Default is fade animation
     */
    private Transition transition;

    /**
     * Holds views added inside the frame layout
     */
    private ArrayList<View> addedViews = new ArrayList<>();


    FrameLayout frameLayout;


    public RelativeOverlay(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public RelativeOverlay(Context context) {
        super(context);
    }

    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();

        getFrame();
    }

    /**
     * Adds a frame at the end of the view
     * <p/>
     * Will create & add the frame only if requested
     *
     * @return
     */
    private FrameLayout getFrame() {

        if (frameLayout == null) {

            frameLayout = new FrameLayout(getContext());

            FrameLayout.LayoutParams params = new FrameLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
            frameLayout.setLayoutParams(params);

            addView(frameLayout);
        }

        return frameLayout;
    }

    /**
     * Add a view in the frame and removes any previous one
     *
     * @param swapView
     */
    public void addOverlay(View swapView) {

        //remove any previous view
        removeOverlays();

        TransitionManager.beginDelayedTransition(getFrame(), getOverlayAnimation());
        getFrame().addView(swapView);

        addedViews.add(swapView);
    }

    /**
     * Removes any previous overlay added
     */
    public void removeOverlays() {

        if (getFrame().getChildCount() > 0 && addedViews.size() > 0) {

            //animation
            TransitionManager.beginDelayedTransition(getFrame(), getOverlayAnimation());

            getFrame().removeAllViews();
        }
    }

    /*****************************************************
     * ---------------- * Animations * --------------------
     ****************************************************/

    private Transition getOverlayAnimation() {

        //custom
        if (transition != null) return transition;

        //default
        return new Fade();
    }

    /**
     * Animation when changing views
     *
     * @param transition
     */
    public void setOverlayAnimation(Transition transition) {
        this.transition = transition;
    }


    /*****************************************************
     * ---------------- * Default views * --------------------
     *
     *
     *
     ****************************************************/

    /**
     * Display a simple error/info message with or without an icon
     * @param imageId
     * @param errorMessage
     */
    public void showErrorView(@DrawableRes int imageId, String errorMessage){

        //slide animation
        setOverlayAnimation(new Slide(Gravity.TOP));

        ViewError viewError = new ViewError(getContext());

        viewError.setErrorMessage(errorMessage);
        if (imageId != 0) viewError.setErrorImage(imageId);

        addOverlay(viewError);
    }

    public void showErrorView(String errorMessage){
        showErrorView(0, errorMessage);
    }

    /**
     * A view with a progressbar and with or without a message
     */
    public void showLoadingView(String message){

        //fade animation
        setOverlayAnimation(new Fade());

        ViewLoading viewLoading = new ViewLoading(getContext());

        viewLoading.setMessage(message);

        addOverlay(viewLoading);
    }

    public void showLoadinView(){
        showLoadingView("");
    }

}
