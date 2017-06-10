//*******The Entrance Video Activity Of the Application*********************************************
//**************************************************************************************************
package com.example.user.myapplication.Animation_SplashScreen;

import android.content.Intent;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.MotionEvent;
import android.widget.VideoView;

import com.example.user.myapplication.Menu.MainMenuActivity;
import com.example.user.myapplication.R;

public class SplashScreen_Entrance extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_entrance);



        try {
            VideoView videoHolder = (VideoView)findViewById(R.id.video);
            Uri video = Uri.parse("android.resource://" + getPackageName() + "/" + R.raw.yatzy_intro);
            videoHolder.setVideoURI(video);
            videoHolder.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                public void onCompletion(MediaPlayer mp) {
                    jump();
                }
            });
            videoHolder.start();

        } catch (Exception ex) {
            jump();
        }
    }
    

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        jump();
        return true;
    }



    private void jump() {
        if (isFinishing())
            return;
        startActivity(new Intent(this, MainMenuActivity.class));
        finish();
    }
}
