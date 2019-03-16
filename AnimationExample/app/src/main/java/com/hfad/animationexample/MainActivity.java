package com.hfad.animationexample;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity {
    ImageView target;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        target = findViewById(R.id.target_image);
    }

    public void onAnimate(View v){
        switch (v.getId()){
            case R.id.alpha:
                Animation alpha = AnimationUtils.loadAnimation(this,R.anim.alpha);
                target.startAnimation(alpha);
                break;
            case R.id.move:
                Animation move = AnimationUtils.loadAnimation(this,R.anim.move);
                target.startAnimation(move);
                move.setFillAfter(true);
                break;
            case R.id.rotate:
                Animation rotate = AnimationUtils.loadAnimation(this,R.anim.rotate);
                target.startAnimation(rotate);
                break;
            case R.id.scale:
                Animation scale = AnimationUtils.loadAnimation(this,R.anim.scale);
                target.startAnimation(scale);
                scale.setFillAfter(true);
                break;
        }
    }
}
