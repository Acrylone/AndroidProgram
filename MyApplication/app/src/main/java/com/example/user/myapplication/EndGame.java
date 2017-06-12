package com.example.user.myapplication;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.user.myapplication.Game.FiveDiceGame;
import com.example.user.myapplication.Game.ScoreButton;
import com.example.user.myapplication.Menu.ChoiceGame;
import com.example.user.myapplication.Menu.MainMenuActivity;
import com.example.user.myapplication.Menu.Navigation.ToolsActivity;
import com.facebook.share.widget.ShareButton;

public class EndGame extends AppCompatActivity {

    protected FiveDiceGame FiveDiceGame;
    public ScoreButton scoreButton;
    private int number = 0;
    private TextView textNumber;
    private Handler handler;
    private boolean Running = true;


    public static int scoreTotal;

    private ShareButton shareButton;
    SharedPreferences sharedPreferences;
    SharedPreferences.Editor editor;

    private static final String PREFS_SCORE = "PREFS_SCORE";
    private static final String USER_CONFIG = "USER_CONFIG";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_end_game);



////////////////////////////////////////////////////////////////////////////////////////////////////


        final Button otherGame = (Button) this.findViewById(R.id.otherGame);
        otherGame.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(), ChoiceGame.class);
                startActivity(i);
            }

        });

        final Button backToMenu = (Button) this.findViewById(R.id.backToMenu);
        backToMenu.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(), MainMenuActivity.class);
                startActivity(i);
            }

        });

        textNumber = (TextView) findViewById(R.id.finalscore);

        final String sBScoreTotal = "";

        handler = new Handler();

        final Runnable runnable = new Runnable() {
            @Override
            public void run() {
                while (Running) {
                    try {
                        Thread.sleep(15);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    handler.post(new Runnable() {
                        @Override
                        public void run() {
                            textNumber.setText(String.valueOf(number));
                            number++;
                            if (number == scoreTotal) {
                                Running = false;
//                                Integer.valueOf(scoreButton.getText().toString())
                            }
                        }
                    });
                }
            }
        };

        new Thread(runnable).start();

//        SharedPreferences prefs = this.getSharedPreferences("myPrefsKey", Context.MODE_PRIVATE);
//        SharedPreferences.Editor editor = prefs.edit();
//        editor.putInt("key", scoreTotal);
//        editor.commit();

        Toast.makeText(EndGame.this, "Score Total " + scoreTotal, Toast.LENGTH_SHORT).show();

        TextView highScoreView = (TextView)findViewById(R.id.highscoreview);

        sharedPreferences = getSharedPreferences("GAME_DATA", Context.MODE_PRIVATE);
        int highScore = sharedPreferences.getInt("HIGH_SCORE", 0);
        editor = sharedPreferences.edit();
        editor.putInt("Score", scoreTotal);
        editor.commit();

        if (scoreTotal > highScore){
            highScoreView.setText("High Score : " + scoreTotal);

            //save
            editor = sharedPreferences.edit();
            editor.putInt("HIGH_SCORE", scoreTotal);
            editor.commit();

        }else{
            highScoreView.setText("High Score : " + highScore);
        }


//        shareButton = (ShareButton)
//
//                findViewById(R.id.facebook_share_button);
//
//        /**
//         * Dès que l'utilisateur clique sur notre ShareButton, cela
//         * lui affiche une vue qui lui permet de partager le lien
//         * qu'on a mis <img draggable="false" class="emoji" alt="🙂" src="https://s.w.org/images/core/emoji/2.2.1/svg/1f642.svg">
//         */
//        ShareLinkContent screenshot = new ShareLinkContent.Builder()
//                .setContentUrl(Uri.parse("http://www.android-pour-tous.com/"))
//                .build();
//
//        shareButton.setShareContent(screenshot);

    }

}
//    public void finalScore (int totalScore){
//        totalScore = FiveDiceGame.showScoreTotal();
//
//    }

