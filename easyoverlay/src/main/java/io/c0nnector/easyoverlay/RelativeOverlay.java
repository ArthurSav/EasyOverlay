package io.c0nnector.easyoverlay;

import android.content.Context;
import android.support.annotation.DrawableRes;
import android.support.annotation.LayoutRes;

import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.RelativeLayout;

import com.transitionseverywhere.Fade;
import com.transitionseverywhere.Transition;
import com.transitionseverywhere.TransitionManager;

import java.util.ArrayList;

import io.c0nnector.easyoverlay.views.ViewError;
import io.c0nnector.easyoverlay.views.ViewLoading;

/**
 * Makes it easier to add overlay views like 'loading, errors, custom' etc...
 */
public class RelativeOverlay extends RelativeLayout {

    private boolean isAnimationRunning = false;

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
    public synchronized View addOverlay(View swapView) {

        beforeAnimation();

        //remove any previous view
        removeOverlays();

        //todo - Fix this, someday. Sometimes it will throw an exception 'You must call removeView() on the child's parent first'
        try {
            getFrame().addView(swapView);
        } catch (IllegalStateException e) {

        }
        addedViews.add(swapView);

        afterAnimation();

        return swapView;
    }

    public synchronized View addOverlay(@LayoutRes int layoutResId){

        View v = LayoutInflater.from(getContext()).inflate(layoutResId, this, false);

        addOverlay(v);

        return v;
    }

    /**
     * Removes any previous overlay added
     */
    public void removeOverlays() {

        if (getFrame().getChildCount() > 0) {

            if (!isAnimationRunning()) beforeAnimation();

            //clear
            getFrame().removeAllViews();

            addedViews.clear();

            afterAnimation();
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

    private void beforeAnimation(){
        this.isAnimationRunning = true;

        TransitionManager.beginDelayedTransition(this, new Fade());
    }

    private void afterAnimation(){
        this.isAnimationRunning = false;
    }

    private boolean isAnimationRunning(){
        return isAnimationRunning;
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

        ViewLoading viewLoading = new ViewLoading(getContext());

        viewLoading.setMessage(message);

        addOverlay(viewLoading);
    }

    public void showLoadinView(){
        showLoadingView("");
    }

    /**
     * @return true, if there's an overlay showing
     */
    public boolean hasOverlays(){
        return addedViews.size() > 0;
    }

    /**
     * True if the loading view is visible
     * @return
     */
    public boolean isShowingLoadingView(){

        if (addedViews.size() >0) {

            for (View v: addedViews) {
                if (v instanceof ViewLoading) return true;
            }
        }

        return false;
    }


    /**
     * True is the error view is visible
     * @return
     */
    public boolean isShowingErrorView(){

        if (addedViews.size() >0) {

            for (View v: addedViews) {
                if (v instanceof ViewLoading) return true;
            }
        }
        return false;
    }

    public boolean containsView(Class viewClass) {

        if (addedViews.size() >0) {

            for (View v: addedViews) {
                if (viewClass.isInstance(v)) return true;
            }
        }
        return false;
    }

    /**
     * True if a custom view is visible
     * @return
     */
    public boolean isShowingCustomView(){
        return !isShowingLoadingView() && !isShowingErrorView() && addedViews.size() > 0;
    }


}
