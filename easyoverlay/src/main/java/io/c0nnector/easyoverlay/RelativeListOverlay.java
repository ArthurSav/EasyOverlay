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

    /**
     * Any view to be injected and called with showCustomView()
     */
    private View customView;

    /**
     * Shows when there are not items in the adapter
     */
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

    /**
     * Called when the recyclerview adapter changes items
     * @param adapter
     * @param i
     */
    @Override
    public synchronized void onAdapterChange(RecyclerView.Adapter adapter, int i) {

        //remove any previous overlays
        if (hasOverlays()) removeOverlays();

        //no adapter items - add empty view, if set
        if (i == 0) {

            if (hasEmptyViewEnabled && viewEmpty !=null) {

                addOverlay(viewEmpty);
            }
        }
    }

    /*****************************************************
     * ---------------- * Views * --------------------
     *
     *
     *
     ****************************************************/

    /**
     * Here you can enable/disable the showing of an empty view for adapters with no items.
     * By default it's enabled and it shows when there are 0 items
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

        viewEmpty.setEmptyMessage(message);

        if (iconId !=-1 ) viewEmpty.setImage(iconId);
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
