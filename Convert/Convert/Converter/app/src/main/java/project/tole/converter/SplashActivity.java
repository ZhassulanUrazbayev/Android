package project.tole.converter;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.MenuInflater;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.AnimationUtils;
import android.widget.TextView;

public class SplashActivity extends Activity {

    TextView textBottom,textTop;
    Animation scale;
    Animation translate;
    Animation animationBottom;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        textBottom = findViewById(R.id.textBottom);
        textTop = findViewById(R.id.textTop);

        scale = AnimationUtils.loadAnimation(this,R.anim.myscale);
        translate = AnimationUtils.loadAnimation(this,R.anim.mytrans);

        AnimationSet s = new AnimationSet(false);
        s.addAnimation(scale);
        s.addAnimation(translate);

        textTop.startAnimation(s);


        animationBottom = AnimationUtils.loadAnimation(this,R.anim.animation_for_text);
        textBottom.setAnimation(animationBottom);
        animationBottom.setDuration(2000);




        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent = new Intent(SplashActivity.this, MainActivity.class);
                startActivity(intent);
                finish();
            }
        },7*1000);
    }
}
