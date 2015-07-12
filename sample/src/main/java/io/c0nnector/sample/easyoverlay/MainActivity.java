package io.c0nnector.sample.easyoverlay;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;
import io.c0nnector.easyoverlay.RelativeOverlay;

public class MainActivity extends AppCompatActivity {

    @InjectView(R.id.container)
    RelativeOverlay relativeOverlay;

    @OnClick(R.id.btnError)
    public void onBtnError(){

        relativeOverlay.showErrorView(R.drawable.ic_announcement_red_400_48dp, "Something something error!");
    }

    @OnClick(R.id.btnLoading)
    public void onBtnLoading(){

        relativeOverlay.showLoadingView("Hey, i'm loading something!");
    }

    @OnClick(R.id.btnClear)
    public void onClear(){

        relativeOverlay.removeOverlays();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.inject(this);
    }


}
