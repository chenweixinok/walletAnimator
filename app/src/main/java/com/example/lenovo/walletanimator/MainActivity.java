package com.example.lenovo.walletanimator;

import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.AnimationUtils;
import android.view.animation.LinearInterpolator;
import android.widget.Button;
import android.widget.ImageView;


public class MainActivity extends ActionBarActivity {


    private ImageView Icoin;
    private ImageView mWallet;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initViews();
        setAnimation();
    }



    private void initViews() {

        Icoin = (ImageView) findViewById(R.id.coin_iv);
        mWallet = (ImageView) findViewById(R.id.wallet_iv);

        Button startBtn = (Button) findViewById(R.id.start_btn);
        assert startBtn != null;
        startBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setAnimation();
            }
        });
    }

    private void setAnimation() {

        startCoin();
        setWallet();

    }

    private void setWallet() {

        final ValueAnimator valueAnimator = ValueAnimator.ofFloat(0, 1);
        valueAnimator.setDuration(800);
        valueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {

                float fraction = animation.getAnimatedFraction();
                if (fraction >= 0.75) {
                    valueAnimator.cancel();
                    startWallet();
                }

            }
        });

        valueAnimator.start();

    }

    private void startWallet() {
        ObjectAnimator objectAnimator1 = ObjectAnimator.ofFloat(mWallet, "scaleX", 1, 1.1f, 0.9f, 1);
        objectAnimator1.setDuration(600);
        ObjectAnimator objectAnimator2 = ObjectAnimator.ofFloat(mWallet, "scaleY", 1, 0.75f, 1.25f, 1);
        objectAnimator2.setDuration(600);

        AnimatorSet animatorSet = new AnimatorSet();
        animatorSet.setInterpolator(new LinearInterpolator());
        animatorSet.playTogether(objectAnimator1, objectAnimator2);
        animatorSet.start();
    }

    private void startCoin() {

        Animation animationTranslate = AnimationUtils.loadAnimation(this, R.anim.anim_top_in);
        ThreeDRotateAnimation animation3D = new ThreeDRotateAnimation();
        animation3D.setRepeatCount(10);
        AnimationSet animationSet = new AnimationSet(true);
        animationSet.setDuration(800);
        animationSet.addAnimation(animation3D);
        animationSet.addAnimation(animationTranslate);
        Icoin.startAnimation(animationSet);

    }


}
