package io.c0nnector.easyoverlay.views;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.TextView;

import io.c0nnector.easyoverlay.BaseOverlayView;
import io.c0nnector.easyoverlay.R;

/**
 * Simple view with a progressbar and a message
 */
public class ViewLoading extends BaseOverlayView {

    TextView txtMessage;


    public ViewLoading(Context context) {
        super(context);
    }

    public ViewLoading(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    protected void init(Context context) {
        inflate(context, R.layout.layout_loading);

        txtMessage = (TextView) findViewById(R.id.txtMessage);
    }


    public void setMessage(String message){
        txtMessage.setText(message);
    }
}
