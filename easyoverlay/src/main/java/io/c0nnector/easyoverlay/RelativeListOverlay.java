package io.c0nnector.easyoverlay;

import android.content.Context;
import android.support.annotation.DrawableRes;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.view.View;

import com.easyrecyclerview.easyrecyclerview.views.EasyRecyclerView;

import io.c0nnector.easyoverlay.views.ViewEmpty;

/**
 * Makes it easier to add views in a recyclerview. Specially the empty view when the adapter has no items
 */
public class RelativeListOverlay extends RelativeOverlay implements EasyRecyclerView.AdapterChangeListener {

    private View customView;

    private ViewEmpty viewEmpty;

    /**
     * Can also be used for empty view...
     */
    private boolean hasEmptyViewEnabled = true;


    /**
     * If the container has a recycler view with id 'recyclerView' then we enable the default behaviour for empty view.
     * When the adapter has 0 items we'll show an error message
     */
    private EasyRecyclerView recyclerView;



    public RelativeListOverlay(Context context, AttributeSet attrs) {
        super(context, attrs);

        init();
    }

    public RelativeListOverlay(Context context) {
        super(context);

        init();
    }

    public void init(){
        recyclerView = (EasyRecyclerView) findViewById(R.id.recyclerView);
        setRecyclerViewOverlay(recyclerView);
    }

    /**
     * The recyclerview we want to overlay
     * @param easyRecyclerView
     */
    public void setRecyclerViewOverlay(EasyRecyclerView easyRecyclerView){

        if (easyRecyclerView !=null) {
            easyRecyclerView.setAdapterChangeListener(this);
        }
    }

    @Override
    public void onAdapterChange(RecyclerView.Adapter adapter, int i) {

        //no items - add empty view if set
        if (i == 0) {

            if (hasEmptyViewEnabled && viewEmpty !=null) {
                addOverlay(viewEmpty);
            }
        }

        //remove any previous overlays, we have adapter items
        else if (hasOverlays()) removeOverlays();
    }

    /*****************************************************
     * ---------------- * Views * --------------------
     *
     *
     *
     ****************************************************/

    /**
     * Here you can enable/disable the showing of an empty view for adapters with no items.
     * By default it's enabled
     * @param enable
     */
    public void setEmptyViewEnabled(boolean enable){
        this.hasEmptyViewEnabled = enable;
    }

    public void setEmptyView(String message){
        setEmptyView(message, -1);
    }

    public void setEmptyView(String message, @DrawableRes int iconId){

        viewEmpty = new ViewEmpty(getContext());

        viewEmpty.setErrorMessage(message);

        if (iconId > 0) viewEmpty.setErrorImage(iconId);
    }


    /**
     * Custom view setup
     * @param customView
     */
    public void setCustomView(View customView){
        this.customView = customView;
    }

    public void showCustomView(boolean show){

        if (customView !=null) {

            if (show) addOverlay(customView);

            else removeOverlays();
        }
    }

}
