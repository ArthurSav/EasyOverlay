package io.c0nnector.easyoverlay;

import android.content.Context;
import android.support.annotation.LayoutRes;
import android.util.AttributeSet;
import android.view.View;
import android.widget.RelativeLayout;


public abstract class BaseOverlayView extends RelativeLayout {

    public BaseOverlayView(Context context) {
        super(context);

        init(context);
    }

    public BaseOverlayView(Context context, AttributeSet attrs) {
        super(context, attrs);

        init(context);
    }

    protected void init(Context context){};


    public View inflate(Context context, @LayoutRes int layoutId){

        View view = inflate(context, layoutId, this);

        return view;
    }

}
