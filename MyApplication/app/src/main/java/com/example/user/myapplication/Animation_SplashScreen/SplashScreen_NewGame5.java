package com.example.user.myapplication.Animation_SplashScreen;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ProgressBar;

import com.example.user.myapplication.Game.FiveDiceGame;
import com.example.user.myapplication.R;

public class SplashScreen_NewGame5 extends AppCompatActivity {

    private int splashTime = 6500;
    private Thread thread;
    private ProgressBar mSpinner;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        // TODO Auto-generated method stub
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_splash_new_game);
//        mSpinner = (ProgressBar) findViewById(R.id.Splash_ProgressBar);
//        mSpinner.setIndeterminate(true);
        thread = new Thread(runable);
        thread.start();
    }

    public Runnable runable = new Runnable() {
        public void run() {
            try {
                Thread.sleep(splashTime);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            try {
                startActivity(new Intent(SplashScreen_NewGame5.this, FiveDiceGame.class));
                finish();
                overridePendingTransition(R.animator.open_transition, R.animator.close_scale);

            } catch (Exception e) {
                // TODO: handle exception
            }

        }
    };

    @Override
    protected void onPause() {
        super.onPause();
        //closing transition animations
        overridePendingTransition(R.animator.open_scale, R.animator.close_transition);
    }
}
