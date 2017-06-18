package com.example.user.myapplication.Menu;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.DisplayMetrics;
import android.view.View;
import android.widget.Button;

import com.example.user.myapplication.Animation_SplashScreen.SplashScreen_NewGame5;
import com.example.user.myapplication.Animation_SplashScreen.SplashScreen_NewGame6;
import com.example.user.myapplication.Game.SixDiceGame;
import com.example.user.myapplication.R;

/**
 * Created by Acrylon3 on 02/03/2017.
 */

public class ChoiceGame extends Activity {
    boolean whichGame = true;
    SharedPreferences sharedPreferences;
    SharedPreferences.Editor editor;
    private static final String WHICH_GAME = "WHICH_GAME";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.choice_game);

        final DisplayMetrics dm = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(dm);

        int width = dm.widthPixels;
        int height = dm.heightPixels;

        getWindow().setLayout((int) (width * .8), (int) (height * .6));

        final MediaPlayer mplayer = MediaPlayer.create(this, R.raw.newgame);

        Button fiveDiceTraining = (Button) findViewById(R.id.fiveDiceTraining);

        fiveDiceTraining.setOnClickListener(new View.OnClickListener()

        {

            public void onClick(View view) {
                mplayer.start();
                sharedPreferences = getSharedPreferences("GAME_DATA", Context.MODE_PRIVATE);
                Boolean whichGame = sharedPreferences.edit().putBoolean("WHICH_GAME", true).commit();
                Intent i = new Intent(getApplicationContext(), SplashScreen_NewGame5.class);
                startActivity(i);

            }

        });
        Button sixDiceTraining = (Button) findViewById(R.id.sixDiceTraining);

        sixDiceTraining.setOnClickListener(new View.OnClickListener()

        {

            public void onClick(View view) {
                mplayer.start();
                sharedPreferences = getSharedPreferences("GAME_DATA", Context.MODE_PRIVATE);
                Boolean statusLocked = sharedPreferences.edit().putBoolean("WHICH_GAME", false).commit();
                Intent i = new Intent(getApplicationContext(), SplashScreen_NewGame6.class);
                startActivity(i);

            }

        });
    }
}
