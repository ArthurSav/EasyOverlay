package io.c0nnector.easyoverlay.views;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.ImageView;
import android.widget.TextView;

import io.c0nnector.easyoverlay.R;

/**
 * Same as error view but it doesn't have a background
 */
public class ViewEmpty extends ViewError {

    public ViewEmpty(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public ViewEmpty(Context context) {
        super(context);
    }

    @Override
    protected void init(Context context) {

        inflate(context, R.layout.layout_empty);

        txtError = (TextView) findViewById(R.id.txtError);
        imgError = (ImageView) findViewById(R.id.imgError);
    }
}
