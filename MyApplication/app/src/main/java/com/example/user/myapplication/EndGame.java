package com.example.user.myapplication;

import android.content.Intent;
import android.net.Uri;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.user.myapplication.Game.CalculateDiceFive;
import com.example.user.myapplication.Game.FiveDiceGame;
import com.example.user.myapplication.Game.ScoreButton;
import com.example.user.myapplication.Menu.ChoiceGame;
import com.example.user.myapplication.Menu.MainActivity_Menu;
import com.example.user.myapplication.Menu.Navigation.Rules.Rules;
import com.facebook.share.model.ShareLinkContent;
import com.facebook.share.widget.ShareButton;
import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.InterstitialAd;

import java.util.Timer;

public class EndGame extends AppCompatActivity {

    protected FiveDiceGame FiveDiceGame;
    public ScoreButton scoreButton;
    private int number = 0;
    private TextView textNumber;
    private Handler handler;
    private boolean Running = true;


    public static int scoreTotal;

    private ShareButton shareButton;


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
                Intent i = new Intent(getApplicationContext(), MainActivity_Menu.class);
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
                        Thread.sleep(20);
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


//        shareButton = (ShareButton)
//
//                findViewById(R.id.facebook_share_button);
//
//        /**
//         * Dès que l'utilisateur clique sur notre ShareButton, cela
//         * lui affiche une vue qui lui permet de partager le lien
//         * qu'on a mis <img draggable="false" class="emoji" alt="🙂" src="https://s.w.org/images/core/emoji/2.2.1/svg/1f642.svg">
//         */
//        ShareLinkContent content = new ShareLinkContent.Builder()
//                .setContentUrl(Uri.parse("http://www.android-pour-tous.com/"))
//                .build();
//
//        shareButton.setShareContent(content);

    }

}
//    public void finalScore (int totalScore){
//        totalScore = FiveDiceGame.showScoreTotal();
//
//    }

