package io.c0nnector.easyoverlay.views;

import android.content.Context;
import android.support.annotation.DrawableRes;
import android.util.AttributeSet;
import android.widget.ImageView;
import android.widget.TextView;

import io.c0nnector.easyoverlay.BaseOverlayView;
import io.c0nnector.easyoverlay.R;

/**
 * Same as error view but it doesn't have a background
 */
public class ViewEmpty extends BaseOverlayView {

    TextView txtError;

    ImageView imgError;

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

    /**
     * Error image
     *
     * @param drawableId
     */
    public void setImage(@DrawableRes int drawableId) {
        imgError.setImageResource(drawableId);
    }

    /**
     * Error message
     *
     * @param message
     */
    public void setEmptyMessage(String message) {
        txtError.setText(message);
    }


    public void clearImage() {
        imgError.setImageDrawable(null);
    }
}
