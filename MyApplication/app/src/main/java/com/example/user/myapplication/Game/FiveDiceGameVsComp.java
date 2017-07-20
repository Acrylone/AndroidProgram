package com.example.user.myapplication.Game;

import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TableRow;

import com.example.user.myapplication.R;

import java.util.ArrayList;
import java.util.List;

public class FiveDiceGameVsComp extends FiveDiceGame {

    private List<ScoreButton> scoreButtonsComputer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        setContentView(R.layout.five_dice_game);
        getSupportActionBar().hide();

        //column1
        TableRow[] leftCol = {
                (TableRow) findViewById(R.id.col0row0),
                (TableRow) findViewById(R.id.col0row1),
                (TableRow) findViewById(R.id.col0row2),
                (TableRow) findViewById(R.id.col0row3),
                (TableRow) findViewById(R.id.col0row4),
                (TableRow) findViewById(R.id.col0row5),
                (TableRow) findViewById(R.id.col0row6),
                (TableRow) findViewById(R.id.col0row7)
        };

        scoreButtonsComputer = new ArrayList<>();
        scoreButtonsComputer.add((ScoreButton) findViewById(R.id.p2_SCORE_ONE));
        scoreButtonsComputer.add((ScoreButton) findViewById(R.id.p2_SCORE_TWO));
        scoreButtonsComputer.add((ScoreButton) findViewById(R.id.p2_SCORE_THREE));
        scoreButtonsComputer.add((ScoreButton) findViewById(R.id.p2_SCORE_FOUR));
        scoreButtonsComputer.add((ScoreButton) findViewById(R.id.p2_SCORE_FIVE));
        scoreButtonsComputer.add((ScoreButton) findViewById(R.id.p2_SCORE_SIX));
        scoreButtonsComputer.add((ScoreButton) findViewById(R.id.p2_SCORE_TOTAL));
        scoreButtonsComputer.add((ScoreButton) findViewById(R.id.p2_SCORE_BONUS));
//        scoreButtonsComputer.add((ScoreButton) findViewById(R.id.PAIR));
//        scoreButtonsComputer.add((ScoreButton) findViewById(R.id.TWOPAIRS));
//        scoreButtonsComputer.add((ScoreButton) findViewById(R.id.THREEOFKIND));
//        scoreButtonsComputer.add((ScoreButton) findViewById(R.id.FOUROFKIND));
//        scoreButtonsComputer.add((ScoreButton) findViewById(R.id.STRAIGHTLOW));
//        scoreButtonsComputer.add((ScoreButton) findViewById(R.id.STRAIGHTHIGH));
//        scoreButtonsComputer.add((ScoreButton) findViewById(R.id.FULLHOUSE));
//        scoreButtonsComputer.add((ScoreButton) findViewById(R.id.CHANCE));
//        scoreButtonsComputer.add((ScoreButton) findViewById(R.id.YATZY));


        for(ScoreButton sb: scoreButtonsComputer){
            sb.setVisibility(View.VISIBLE);
        }

        for(TableRow tr : leftCol) {
            ScoreButton sb = new ScoreButton(this);
//            sb.setBackgroundColor(Color.TRANSPARENT);
            sb.setVisibility(View.VISIBLE);
            tr.addView(sb);
            tr.invalidate();
            tr.postInvalidate();
            /*
            android:layout_marginRight="20dp"
                        android:background="@android:color/transparent"
                        android:onClick="clickScoreButton"
                        android:textSize="25dp"
             */
        }


        LinearLayout li = (LinearLayout)findViewById(R.id.linearLayout5dice);
        li.setBackgroundColor(getResources().getColor(R.color.transparent));
        li.requestLayout();
        li.invalidate();
        li.postInvalidate();

        RelativeLayout rl = (RelativeLayout)findViewById(R.id.activity_main_newgame);
        rl.invalidate();
    }


}
