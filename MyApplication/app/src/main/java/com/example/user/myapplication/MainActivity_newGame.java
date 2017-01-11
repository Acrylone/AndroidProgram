package com.example.user.myapplication;

import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.logging.Handler;

public class MainActivity_newGame extends AppCompatActivity {

    private static int NUM_OF_LAUNCHES_START = 0;
    private static final int NUM_OF_LAUNCHES_MAX = 15;
    private static final int NUM_OF_DICE = 5;
    private static final int NUM_OF_ALLOWED_THROWS = 3;
    private int alreadyThrown;

    private DieImageButton[] dice;
    private List<ScoreButton> scoreButtons;
    private CalculateDice calculateDice;
    //    public ProgressBar progressGame = null;
//    public Button launchButton;
    private int i = 0;

    //    private MediaPlayer dice_sound = null;
//    private int sound_id;

    private int progressStatus = 0;
    private Handler handler;    //Post message to start roll
    private Timer timer = new Timer();    //Used to implement feedback to user
    private boolean rolling = false;        //Is die rolling?


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_newgame);


//        launchButton = (Button) findViewById(R.id.launch);
//        launchButton.setOnClickListener(new ButtonListener());


//    @Override
//    protected void onPostCreate(@Nullable Bundle savedInstanceState) {
//        super.onPostCreate(savedInstanceState);
//        setContentView(R.layout.activity_main_newgame);

//
//    class ButtonListener implements View.OnClickListener {
//        @Override
//        public void onClick(View v) {
//            if (i == NUM_OF_LAUNCHES_START || i == NUM_OF_LAUNCHES_MAX) {
//                //make the progress bar visible
//                progressGame.setVisibility(View.VISIBLE);
//                progressGame.setMax(NUM_OF_LAUNCHES_MAX);
//            } else if (i < progressGame.getMax()) {
//                //Set first progress bar value
//                progressGame.setProgress(i);
//            } else {
//                progressGame.setProgress(0);
//                i = 0;
//                progressGame.setVisibility(View.GONE);
//            }
//            i = i + 1;


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

        initializeDice();

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
        scoreButtons.add((ScoreButton) findViewById(R.id.FOUROFKIND));
        scoreButtons.add((ScoreButton) findViewById(R.id.STRAIGHTLOW));
        scoreButtons.add((ScoreButton) findViewById(R.id.STRAIGHTHIGH));
        scoreButtons.add((ScoreButton) findViewById(R.id.YATZY));

    }

    public void initializeDice() {
        for (int i = 0; i < NUM_OF_DICE; i++) {
            dice[i].setValue(i + 1);
            dice[i].setIsActive(true);
        }
    }

    public void updateScores() {
        //find all score options
        for (int i = 0; i < scoreButtons.size(); i++) {
            if (scoreButtons.get(i).isEnabled()) {
                switch (scoreButtons.get(i).getId()) {
                    case R.id.SCORE_ONE:
                        showScore1(scoreButtons.get(i));
                        break;
                    case R.id.SCORE_TWO:
                        showScore2(scoreButtons.get(i));
                        break;
                    case R.id.SCORE_THREE:
                        showScore3(scoreButtons.get(i));
                        break;
                    case R.id.SCORE_FOUR:
                        showScore4(scoreButtons.get(i));
                        break;
                    case R.id.SCORE_FIVE:
                        showScore5(scoreButtons.get(i));
                        break;
                    case R.id.SCORE_SIX:
                        showScore6(scoreButtons.get(i));
                        break;
//                    case R.id.SCORE_TOTAL:
//                        clickScoreButtonTotal(scoreButtons.get(i)); //TODO - change to other function
//                        break;
                    case R.id.PAIR:
                        showScorePair(scoreButtons.get(i));
                        break;
//                    case R.id.TWOPAIRS:
//                        clickScoreButtonTwoPairs(scoreButtons.get(i));
//                        break;
                    case R.id.THREEOFKIND:
                        showScoreThreeOfKind(scoreButtons.get(i));
                        break;
                    case R.id.FOUROFKIND:
                        showScoreFourOfKind(scoreButtons.get(i));
                        break;
                    case R.id.STRAIGHTLOW:
                        clickScoreButtonStraightLow(scoreButtons.get(i));
                        break;
                    case R.id.STRAIGHTHIGH:
                        clickScoreButtonStraightHigh(scoreButtons.get(i));
                        break;
                    case R.id.YATZY:
                        showScoreYatzy(scoreButtons.get(i));
                        break;
                }
            }
        }
    }

    public void throwDice(View view) {
//        final MediaPlayer mp = MediaPlayer.create(this, R.raw.shake_dice);
//        Button playSoundice = (Button) this.findViewById(R.id.launch);
//        playSoundice.setOnClickListener(new View.OnClickListener(){
//
//            @Override
//            public void onClick(View view) {
//                mp.start();}
//        });
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

            updateScores();

            alreadyThrown++;

            if (alreadyThrown == NUM_OF_ALLOWED_THROWS) {
                Button throwButton = (Button) findViewById(R.id.launch);
                throwButton.setEnabled(false);
            }
        }
    }


    public void toggleActivation(View view) {
        if (alreadyThrown != 0 && alreadyThrown != NUM_OF_ALLOWED_THROWS) {
            ((DieImageButton) view).toggleActivation();
        }
    }

    //Class of dice scores
    public void showScore1(ScoreButton view) {
        view.turnVisible(calculateDice.calculateScoreRepetition(1));
    }

    public void showScore2(ScoreButton view) {
        view.turnVisible(calculateDice.calculateScoreRepetition(2));
    }

    public void showScore3(ScoreButton view) {
        view.turnVisible(calculateDice.calculateScoreRepetition(3));
    }

    public void showScore4(ScoreButton view) {
        view.turnVisible(calculateDice.calculateScoreRepetition(4));
    }

    public void showScore5(ScoreButton view) {
        view.turnVisible(calculateDice.calculateScoreRepetition(5));
    }

    public void showScore6(ScoreButton view) {
        view.turnVisible(calculateDice.calculateScoreRepetition(6));
    }

    //
////    public void clickScoreButtonTotal(ScoreButton view) {
////        view.turnVisible(calculateDice.findHighestNRepititions(2));
//    }
    public void showScorePair(ScoreButton view) {
        view.turnVisible(calculateDice.findHighestNRepititions(2));
    }

////    public void clickScoreButtonTwoPairs(ScoreButton view) {
////        view.turnVisible(calculateDice.findHighestNRepititions(3));
//    }

    public void showScoreThreeOfKind(ScoreButton view) {
        view.turnVisible(calculateDice.findHighestNRepititions(3));
    }

    public void showScoreFourOfKind(ScoreButton view) {
        view.turnVisible(calculateDice.findHighestNRepititions(4));
    }

    public void clickScoreButtonStraightLow(ScoreButton view) {
        view.turnVisible(calculateDice.findStraight(1, 5));
    }

    public void clickScoreButtonStraightHigh(ScoreButton view) {
        view.turnVisible(calculateDice.findStraight(2, 6));
    }

//    public void clickScoreButton12(ScoreButton view) {
//        view.turnVisible(calculateDice.getRepetitionNumber(0));
//    }
//
//    public void clickScoreButton13(ScoreButton view) {
//        view.turnVisible(calculateDice.getRepetitionNumber(0));
//    }

    public void showScoreYatzy(ScoreButton view) {
        view.turnVisible(calculateDice.findYatzy());
    }

//    public void download() {
//        final ProgressBar ProgressGame = (ProgressBar) findViewById(R.id.progressBar);
//        final int totalProgressTime = NUM_OF_LAUNCHES_MAX;
//        final Thread t = new Thread() {
//            @Override
//            public void run() {
//                int jumpTime = 0;
//
//                while (jumpTime < totalProgressTime) {
//                    try {
//                        sleep(200);
//                        jumpTime += NUM_OF_LAUNCHES_START;
//                        ProgressGame.setProgress(jumpTime);
//                    } catch (InterruptedException e) {
//                        // TODO Auto-generated catch block
//                        e.printStackTrace();
//                    }
//                }
//            }
//        };
//        t.start();
//    }



        public void clickScoreButton(View view) {
            if (alreadyThrown == 0) {
                NUM_OF_LAUNCHES_START++;
//            ProgressBar progressGame = (ProgressBar) findViewById(R.id.progressBar);
//            progressGame.setProgress(NUM_OF_LAUNCHES_START);
//            progressGame.setMax(NUM_OF_LAUNCHES_MAX);
                return;
            }
            view.setEnabled(false);

            alreadyThrown = 0;
            Button throwButton = (Button) findViewById(R.id.launch);
            throwButton.setEnabled(true);


            initializeDice();
//            download();


            //do automatically dice Throw
            throwDice(throwButton);

        }


    }








