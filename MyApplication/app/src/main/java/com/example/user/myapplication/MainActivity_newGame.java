package com.example.user.myapplication;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.logging.Handler;

public class MainActivity_newGame extends AppCompatActivity {

    private static final int NUM_OF_DICE = 5;
    private static final int NUM_OF_ALLOWED_THROWS = 3;
    private static final int NUM_OF_LAUNCHES_MAX = 15;
    private int alreadyThrown;

    private DieImageButton[] dice;
    private List<ScoreButton> scoreButtons;
    private CalculateDice calculateDice;

    private MediaPlayer dice_sound = null;
    private int sound_id;
    private Handler handler;    //Post message to start roll
    private Timer timer = new Timer();    //Used to implement feedback to user
    private boolean rolling = false;        //Is die rolling?


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_newgame);

        alreadyThrown = 0;


        dice = new DieImageButton[NUM_OF_DICE];
        dice[0] = (DieImageButton)
                findViewById(R.id.diceOne);

        dice[1] = (DieImageButton)
                findViewById(R.id.diceTwo);

        dice[2] = (DieImageButton)
                findViewById(R.id.diceThree);

        dice[3] = (DieImageButton)
                findViewById(R.id.diceFour);

        dice[4] = (DieImageButton)
                findViewById(R.id.diceFive);


        for (DieImageButton dib : dice) {
            dib.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    toggleActivation(view);
                }
            });
        }

        for (int i = 0; i < NUM_OF_DICE; i++)
        {
            dice[i].setValue(i + 1);
        }

        calculateDice = new CalculateDice(dice);

        scoreButtons = new ArrayList<>();
        scoreButtons.add((ScoreButton) findViewById(R.id.SCORE_ONE));
        scoreButtons.add((ScoreButton) findViewById(R.id.SCORE_TWO));
        scoreButtons.add((ScoreButton) findViewById(R.id.SCORE_THREE));
        scoreButtons.add((ScoreButton) findViewById(R.id.SCORE_FOUR));
        scoreButtons.add((ScoreButton) findViewById(R.id.SCORE_FIVE));
        scoreButtons.add((ScoreButton) findViewById(R.id.SCORE_SIX));
        scoreButtons.add((ScoreButton) findViewById(R.id.SCORE_TOTAL));
        scoreButtons.add((ScoreButton) findViewById(R.id.SCORE_BONUS));
        scoreButtons.add((ScoreButton) findViewById(R.id.PAIR));
        scoreButtons.add((ScoreButton) findViewById(R.id.TWOPAIRS));
        scoreButtons.add((ScoreButton) findViewById(R.id.THREEOFKIND));
    }

//    private void playSound(int resId) {
//        if(sound_id != null) {
//                dice_sound.stop();
//                dice_sound.release();
//            }
//            dice_sound = MediaPlayer.load(this, resId);
//            dice_sound.start();
//        }
//
//        sound_id = dice_sound.load(this, R.raw.shake_dice, 1);

    public void throwDice(View view) {
//      playSound(R.raw.shake_dice);
//        Label:
        {
            if (alreadyThrown < NUM_OF_ALLOWED_THROWS) {
                switch (view.getId()) {
                    case R.id.launch:
                        for (int i = 0; i < dice.length; i++) {
                            if (dice[i].isActive()) {

                                dice[i].throwSelfDie();

                            }
                        }
                        break;
                }

                alreadyThrown++;

                if (alreadyThrown == NUM_OF_ALLOWED_THROWS) {
                    Button throwButton = (Button) findViewById(R.id.launch);
                    throwButton.setEnabled(false);
                }
            }

            //find all score options
            for (int i = 0; i < scoreButtons.size(); i++) {
                switch (scoreButtons.get(i).getId()) {
                    case R.id.SCORE_ONE:
                        clickScoreButton1(scoreButtons.get(i));
                        break;
                    case R.id.SCORE_TWO:
                        clickScoreButton2(scoreButtons.get(i));
                        break;
                    case R.id.SCORE_THREE:
                        clickScoreButton3(scoreButtons.get(i));
                        break;
                    case R.id.SCORE_FOUR:
                        clickScoreButton4(scoreButtons.get(i));
                        break;
                    case R.id.SCORE_FIVE:
                        clickScoreButton5(scoreButtons.get(i));
                        break;
                    case R.id.SCORE_SIX:
                        clickScoreButton6(scoreButtons.get(i));
                        break;
                    case R.id.SCORE_TOTAL:
                        clickScoreButton7(scoreButtons.get(i)); //TODO - change to other function
                        break;
                    case R.id.PAIR:
                        clickScoreButton7(scoreButtons.get(i));
                        break;
//                    case R.id.TWOPAIRS:
//                        clickScoreButton9(scoreButtons.get(i));
//                        break;
                    case R.id.THREEOFKIND:
                        clickScoreButton8(scoreButtons.get(i));
                        break;
//                    case R.id.FOUROFKIND:
//                        clickScoreButton11(scoreButtons.get(i));
//                        break;
                }
            }


        }
    }


    public void toggleActivation(View view) {
        if (alreadyThrown != 0 && alreadyThrown != NUM_OF_ALLOWED_THROWS) {
            ((DieImageButton) view).toggleActivation();
        }
    }

    //Class of dice scores
    public void clickScoreButton1(ScoreButton view) {
        view.turnVisible(calculateDice.calculateScoreRepetition(1));
    }

    public void clickScoreButton2(ScoreButton view) {
        view.turnVisible(calculateDice.calculateScoreRepetition(2));
    }

    public void clickScoreButton3(ScoreButton view) {
        view.turnVisible(calculateDice.calculateScoreRepetition(3));
    }

    public void clickScoreButton4(ScoreButton view) {
        view.turnVisible(calculateDice.calculateScoreRepetition(4));
    }

    public void clickScoreButton5(ScoreButton view) {
        view.turnVisible(calculateDice.calculateScoreRepetition(5));
    }

    public void clickScoreButton6(ScoreButton view) {
        view.turnVisible(calculateDice.calculateScoreRepetition(6));
    }

    public void clickScoreButton7(ScoreButton view) {
        view.turnVisible(calculateDice.findHighestNRepititions(2));
    }
    public void clickScoreButton8(ScoreButton view) {
        view.turnVisible(calculateDice.findHighestNRepititions(3));
    }

//
//    public void clickScoreButton8(ScoreButton view) {
//        view.turnVisible(calculateDice.getRepetitionNumber(0));
//    }
//
//    public void clickScoreButton9(ScoreButton view) {
//        view.turnVisible(calculateDice.getRepetitionNumber(0));
//    }
//
//    public void clickScoreButton10(ScoreButton view) {
//        view.turnVisible(calculateDice.getRepetitionNumber(0));
//    }
//
//    public void clickScoreButton11(ScoreButton view) {
//        view.turnVisible(calculateDice.getRepetitionNumber(0));
//    }
//
//    public void clickScoreButton12(ScoreButton view) {
//        view.turnVisible(calculateDice.getRepetitionNumber(0));
//    }
//
//    public void clickScoreButton13(ScoreButton view) {
//        view.turnVisible(calculateDice.getRepetitionNumber(0));
//    }
}

