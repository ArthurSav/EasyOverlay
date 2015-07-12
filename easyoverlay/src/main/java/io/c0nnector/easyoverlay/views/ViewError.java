package io.c0nnector.easyoverlay.views;

import android.content.Context;
import android.support.annotation.DrawableRes;
import android.util.AttributeSet;
import android.widget.ImageView;
import android.widget.TextView;

import io.c0nnector.easyoverlay.BaseOverlayView;
import io.c0nnector.easyoverlay.R;

/**
 * Created by arthur on 7/11/15. asd
 */
public class ViewError extends BaseOverlayView {

    TextView txtError;

    ImageView imgError;


    public ViewError(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public ViewError(Context context) {
        super(context);
    }

    @Override
    protected void init(Context context) {
        inflate(context, R.layout.layout_error);

        txtError = (TextView) findViewById(R.id.txtError);
        imgError = (ImageView) findViewById(R.id.imgError);
    }



    /**
     * Error image
     *
     * @param drawableId
     */
    public void setErrorImage(@DrawableRes int drawableId) {
        imgError.setImageResource(drawableId);
    }

    /**
     * Error message
     *
     * @param message
     */
    public void setErrorMessage(String message) {
        txtError.setText(message);
    }


    public void clearErrorImage() {
        imgError.setImageDrawable(null);
    }

}
