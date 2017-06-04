//*****Main Game Activity***************************************************************************

package com.example.user.myapplication.Game;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.example.user.myapplication.EndGame;
import com.example.user.myapplication.InterstitialGoogle;
import com.example.user.myapplication.Menu.Navigation.Rules.Rules;
import com.example.user.myapplication.R;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdSize;
import com.google.android.gms.ads.AdView;

import java.util.ArrayList;
import java.util.List;

public class FiveDiceGame extends AppCompatActivity implements View.OnClickListener {


    private static final int NUM_OF_THROWING_MAX = 15;
    private static final int NUM_OF_DICE = 5;
    private static final int NUM_OF_ALLOWED_THROWS = 3;

    private int alreadyThrown;

    private boolean bonusGone = false;
    private boolean isBonus = false;


    private int num_of_launches = 0;

    private DieImageButton[] dice; //Array for the dice
    private List<ScoreButton> scoreButtons; //Array list of the ScoreButton
    private List<ScoreButton> scoreButtonsLeft; //Array list of the ScoreButton
    private CalculateDiceFive calculateDiceFive; //Call the class relevant
    private ProgressBar progressBar;
    int clickcount = 0; //Counter for how many times clicking on the desactive Button -> message
    private int counterTotal = 0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.five_dice_game);

        //******//ProgressBar function**********************************************************************
        progressBar = (ProgressBar) findViewById(R.id.progressbarfivedice);
        progressBar.setMax(105);
        ////////////////////////////////////////////////////////////////////////////////////////////////////

        RelativeLayout adContainer = (RelativeLayout)
                findViewById(R.id.adMobView);

        AdView mAdView = new AdView(this);
        mAdView.setAdSize(AdSize.SMART_BANNER);
        mAdView.setAdUnitId(getString(R.string.banner_ad_unit_id));
        adContainer.addView(mAdView);
        AdRequest adRequest = new AdRequest.Builder().build();
        mAdView.loadAd(adRequest);

////////OnClickListener for the scoreNumbers////////////////////////////////////////////////////////
        final Button pen_one = (Button) findViewById(R.id.SCORE_ONE);
        pen_one.setOnClickListener(this);
        final Button pen_two = (Button) findViewById(R.id.SCORE_TWO);
        pen_two.setOnClickListener(this);
        final Button pen_three = (Button) findViewById(R.id.SCORE_THREE);
        pen_three.setOnClickListener(this);
        final Button pen_four = (Button) findViewById(R.id.SCORE_FOUR);
        pen_four.setOnClickListener(this);
        final Button pen_five = (Button) findViewById(R.id.SCORE_FIVE);
        pen_five.setOnClickListener(this);
        final Button pen_six = (Button) findViewById(R.id.SCORE_SIX);
        pen_six.setOnClickListener(this);
        final Button pen_pair = (Button) findViewById(R.id.PAIR);
        pen_pair.setOnClickListener(this);
        final Button pen_2pairs = (Button) findViewById(R.id.TWOPAIRS);
        pen_2pairs.setOnClickListener(this);
        final Button pen_3pairs = (Button) findViewById(R.id.THREEOFKIND);
        pen_3pairs.setOnClickListener(this);
        final Button pen_4pairs = (Button) findViewById(R.id.FOUROFKIND);
        pen_4pairs.setOnClickListener(this);
        final Button pen_lowS = (Button) findViewById(R.id.STRAIGHTLOW);
        pen_lowS.setOnClickListener(this);
        final Button pen_highS = (Button) findViewById(R.id.STRAIGHTHIGH);
        pen_highS.setOnClickListener(this);
        final Button pen_full = (Button) findViewById(R.id.FULLHOUSE);
        pen_full.setOnClickListener(this);
        final Button pen_chance = (Button) findViewById(R.id.CHANCE);
        pen_chance.setOnClickListener(this);
        final Button pen_yatzy = (Button) findViewById(R.id.YATZY);
        pen_yatzy.setOnClickListener(this);
////////////////////////////////////////////////////////////////////////////////////////////////////

        alreadyThrown = 0;

        //Create array with 5 cells - and adapt each cell with the appropriate die
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

//**************************************************************************************************

        //Loop to active/de-active the dice
        for (DieImageButton dib : dice) {
            dib.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    toggleActivation(view);
                }
            });
        }

        //Reset all the dice
        initializeDice();

        calculateDiceFive = new CalculateDiceFive(dice);


//******//Launch animation fireworks Yatzy//**************************UNDER CONSTRUCTION************

        final LayoutInflater inflater = getLayoutInflater();
        final View layout = inflater.inflate(R.layout.animation_yatzy,
                (ViewGroup) findViewById(R.id.animation_custom_container));

        final MediaPlayer mplayer = MediaPlayer.create(this, R.raw.great_sound);
        final Button activeAnimationYatzY = (Button) this.findViewById(R.id.YATZY);

        activeAnimationYatzY.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {
                clickScoreButton(activeAnimationYatzY);
                progressBar.setProgress(progressBar.getProgress() + 7);
                if (Integer.valueOf(activeAnimationYatzY.getText().toString()).equals(CalculateDiceFive.YATZY_CLASSIC_POINTS)) {
                    mplayer.start();
                    Toast toast = new Toast(getApplicationContext());
                    toast.setGravity(Gravity.CENTER, 0, 0);
                    toast.setDuration(Toast.LENGTH_LONG);
                    toast.setView(layout);
                    toast.show();

                }
            }
        });


//******//Sound Dice Rolling//**********************************************************************
        final MediaPlayer mp = MediaPlayer.create(this, R.raw.shake_dice);

        final Button play_button = (Button) this.findViewById(R.id.rollingdice);
        play_button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                mp.start();
                throwDice(play_button);

            }
        });


//******// Floating Button Help//*******************************************************************
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.helpbonus);
        fab.setImageResource(R.drawable.ic_help_24dp);
        fab.setOnClickListener(new View.OnClickListener() {

            LayoutInflater inflater = getLayoutInflater();
            View layout = inflater.inflate(R.layout.help_bonus,
                    (ViewGroup) findViewById(R.id.help_bonus_container));

            @Override
            public void onClick(View view) {
                Intent i = new Intent(getApplicationContext(), Rules.class);
                startActivity(i);
//                Toast toast = new Toast(getApplicationContext());
//                toast.setGravity(Gravity.NO_GRAVITY, 550, 400);
//                toast.setDuration(Toast.LENGTH_LONG);
//                toast.setView(layout);
//                toast.show();

            }
        });

//**************************************************************************************************
//**************************************************************************************************
//**************************************************************************************************
//******//Array List Of All the ScoreButtons//******************************************************

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
        scoreButtons.add((ScoreButton) findViewById(R.id.FULLHOUSE));
        scoreButtons.add((ScoreButton) findViewById(R.id.CHANCE));
        scoreButtons.add((ScoreButton) findViewById(R.id.YATZY));

        scoreButtonsLeft = new ArrayList<>();
        scoreButtonsLeft.add((ScoreButton) findViewById(R.id.SCORE_ONE));
        scoreButtonsLeft.add((ScoreButton) findViewById(R.id.SCORE_TWO));
        scoreButtonsLeft.add((ScoreButton) findViewById(R.id.SCORE_THREE));
        scoreButtonsLeft.add((ScoreButton) findViewById(R.id.SCORE_FOUR));
        scoreButtonsLeft.add((ScoreButton) findViewById(R.id.SCORE_FIVE));
        scoreButtonsLeft.add((ScoreButton) findViewById(R.id.SCORE_SIX));


    }
//*****End of OnCreate******************************************************************************
//**************************************************************************************************
//**************************************************************************************************


    //*****On Click Void to make sound on Score Button**************************************************
    @Override
    public void onClick(View v) {
        final MediaPlayer mp = MediaPlayer.create(this, R.raw.pen);
        final MediaPlayer mp2 = MediaPlayer.create(this, R.raw.pen2);
        final MediaPlayer mp3 = MediaPlayer.create(this, R.raw.pen3);
        final MediaPlayer mp4 = MediaPlayer.create(this, R.raw.pen4);
        final MediaPlayer mp5 = MediaPlayer.create(this, R.raw.pen5);
        final MediaPlayer mp6 = MediaPlayer.create(this, R.raw.pen6);


        switch (v.getId()) {

            case R.id.SCORE_ONE:
                mp.start();
                progressBar.setProgress(progressBar.getProgress() + 7);
                clickScoreButton(v);
                break;
            case R.id.SCORE_TWO:
                mp2.start();
                progressBar.setProgress(progressBar.getProgress() + 7);
                clickScoreButton(v);
                break;
            case R.id.SCORE_THREE:
                mp3.start();
                progressBar.setProgress(progressBar.getProgress() + 7);
                clickScoreButton(v);
                break;
            case R.id.SCORE_FOUR:
                mp4.start();
                progressBar.setProgress(progressBar.getProgress() + 7);
                clickScoreButton(v);
                break;
            case R.id.SCORE_FIVE:
                mp5.start();
                progressBar.setProgress(progressBar.getProgress() + 7);
                clickScoreButton(v);
                break;
            case R.id.SCORE_SIX:
                mp6.start();
                progressBar.setProgress(progressBar.getProgress() + 7);
                clickScoreButton(v);
                break;
            case R.id.PAIR:
                mp.start();
                progressBar.setProgress(progressBar.getProgress() + 7);
                clickScoreButton(v);
                break;
            case R.id.TWOPAIRS:
                mp2.start();
                progressBar.setProgress(progressBar.getProgress() + 7);
                clickScoreButton(v);
                break;
            case R.id.THREEOFKIND:
                mp3.start();
                progressBar.setProgress(progressBar.getProgress() + 7);
                clickScoreButton(v);
                break;
            case R.id.FOUROFKIND:
                mp4.start();
                progressBar.setProgress(progressBar.getProgress() + 7);
                clickScoreButton(v);
                break;
            case R.id.STRAIGHTLOW:
                mp5.start();
                progressBar.setProgress(progressBar.getProgress() + 7);
                clickScoreButton(v);
                break;
            case R.id.STRAIGHTHIGH:
                mp6.start();
                progressBar.setProgress(progressBar.getProgress() + 7);
                clickScoreButton(v);
                break;
            case R.id.FULLHOUSE:
                mp2.start();
                progressBar.setProgress(progressBar.getProgress() + 7);
                clickScoreButton(v);
                break;
            case R.id.CHANCE:
                mp3.start();
                progressBar.setProgress(progressBar.getProgress() + 7);
                clickScoreButton(v);
                break;
            default:
                break;
        }

    }

    //*****Initialize the dice, active them and update the image dice corresponding*********************
    public void initializeDice() {
        for (int i = 0; i < NUM_OF_DICE; i++) {
            dice[i].setValue(i + 1);
            dice[i].setIsActive(true);
        }
    }


    //**************************************************************************************************
//*****Update the cell with the relevant ScoreNumber************************************************
    public void updateScores() {
        //Find all score options
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
                    case R.id.PAIR:
                        showScorePair(scoreButtons.get(i));
                        break;
                    case R.id.TWOPAIRS:
                        clickScoreButtonTwoPairs(scoreButtons.get(i));
                        break;
                    case R.id.THREEOFKIND:
                        showScoreThreeOfKind(scoreButtons.get(i));
                        break;
                    case R.id.FOUROFKIND:
                        showScoreFourOfKind(scoreButtons.get(i));
                        break;
                    case R.id.STRAIGHTLOW:
                        showScoreStraightLow(scoreButtons.get(i));
                        break;
                    case R.id.STRAIGHTHIGH:
                        showScoreStraightHigh(scoreButtons.get(i));
                        break;
                    case R.id.FULLHOUSE:
                        showScoreFullHouse(scoreButtons.get(i));
                        break;
                    case R.id.CHANCE:
                        showScoreChance(scoreButtons.get(i));
                        break;
                    case R.id.YATZY:
                        showScoreYatzy(scoreButtons.get(i));
                        break;
                    case R.id.SCORE_BONUS:
                        showScoreBonus(scoreButtons.get(i));
                        break;
                    case R.id.SCORE_TOTAL:
                        showScoreTotal(scoreButtons.get(i));
                        break;
                }
            }
        }
    }

    //*****Function to active update and limit the rolling dice and showing result dice*****************
    public void throwDice(View view) {

        if (alreadyThrown < NUM_OF_ALLOWED_THROWS) {

            switch (view.getId()) {
                case R.id.rollingdice:
                    for (int i = 0; i < dice.length; i++) {
                        if (dice[i].isActive()) {

                            dice[i].throwSelfDie();

                        }
                    }
                    break;
            }

            updateScores();
            alreadyThrown++;

//*****At the end of 3 throwing, deactive the button***********************************************
            if (alreadyThrown == NUM_OF_ALLOWED_THROWS) {

                Button activeButton = (Button) findViewById(R.id.rollingdice);
                activeButton.setEnabled(false);

                Button desactiveButton = (Button) findViewById(R.id.rollingdiceoff);
                desactiveButton.setEnabled(true);

            }
        }

    }


    //Make a toast message if the player click too many times in the inactive Button "Rolling Dice"
    public void count(View view) {
        clickcount++;
        if (clickcount == 5) {
            Toast.makeText(this, "Please click on one of the available boxes",
                    Toast.LENGTH_SHORT).show();
        }
    }


    //*****Make active and visible the dice during launching********************************************
    public void toggleActivation(View view) {
        if (alreadyThrown != 0 && alreadyThrown != NUM_OF_ALLOWED_THROWS) {
            ((DieImageButton) view).toggleActivation();
        }
    }

    //*****Functions of Dice Scores***********************************************************************
    public void showScore1(ScoreButton view) {
        view.turnVisible(calculateDiceFive.calculateScoreRepetition(1));
    }

    public void showScore2(ScoreButton view) {
        view.turnVisible(calculateDiceFive.calculateScoreRepetition(2));
    }

    public void showScore3(ScoreButton view) {
        view.turnVisible(calculateDiceFive.calculateScoreRepetition(3));
    }

    public void showScore4(ScoreButton view) {
        view.turnVisible(calculateDiceFive.calculateScoreRepetition(4));
    }

    public void showScore5(ScoreButton view) {
        view.turnVisible(calculateDiceFive.calculateScoreRepetition(5));
    }

    public void showScore6(ScoreButton view) {
        view.turnVisible(calculateDiceFive.calculateScoreRepetition(6));
    }

    public void showScorePair(ScoreButton view) {
        view.turnVisible(calculateDiceFive.findHighestNRepititions(2));
    }

    public void clickScoreButtonTwoPairs(ScoreButton view) {
        view.turnVisible(calculateDiceFive.findTwoPairs());
    }

    public void showScoreThreeOfKind(ScoreButton view) {
        view.turnVisible(calculateDiceFive.findHighestNRepititions(3));

    }

    public void showScoreFourOfKind(ScoreButton view) {
        view.turnVisible(calculateDiceFive.findHighestNRepititions(4));
    }

    public void showScoreStraightLow(ScoreButton view) {
        view.turnVisible(calculateDiceFive.findStraight(1, 5));
    }

    public void showScoreStraightHigh(ScoreButton view) {
        view.turnVisible(calculateDiceFive.findStraight(2, 6));
    }

    public void showScoreFullHouse(ScoreButton view) {
        view.turnVisible(calculateDiceFive.findfullHouse());
    }

    public void showScoreChance(ScoreButton view) {
        view.turnVisible(calculateDiceFive.findChanceClassic());
    }

    public void showScoreYatzy(ScoreButton view) {
        view.turnVisible(calculateDiceFive.findYatzyClassic());
    }

    public void showScoreTotal(ScoreButton view) {

        int scoreTotal = 0;
        for (ScoreButton sb : scoreButtons) {
            String s = "";
            if (!sb.isEnabled())
                try {
                    s = sb.getText().toString();
//                    SCORETOTAL -= SCORETOTAL;
                } catch (NumberFormatException e) {
                }


            if (s != "") {
                scoreTotal += Integer.valueOf(s);

            }
        }
        view.turnVisible(scoreTotal);
    }


    public void showScoreBonus(ScoreButton view) {

        int checkSbEnabled = 0;
        int scoreTotal = 0;
        int scoreBonus = -63;
        for (ScoreButton sb : scoreButtonsLeft) {
            String s = "";
            if (!sb.isEnabled()) {
                try {
                    s = sb.getText().toString();
                    scoreBonus -= scoreTotal;
                } catch (NumberFormatException e) {
                }


                if (s != "") {
                    scoreTotal += Integer.valueOf(s);
                    scoreBonus += scoreTotal;
                    if (scoreBonus >= 0 && !bonusGone) {
                        final LayoutInflater inflater = getLayoutInflater();
                        final View layout = inflater.inflate(R.layout.animation_bonus,
                                (ViewGroup) findViewById(R.id.animation_custom_container));

                        final MediaPlayer mplayer = MediaPlayer.create(this, R.raw.cheer);

                        mplayer.start();
                        Toast toast = new Toast(getApplicationContext());
                        toast.setGravity(Gravity.CENTER, 0, 0);
                        toast.setDuration(Toast.LENGTH_LONG);
                        toast.setView(layout);
                        toast.show();

                        isBonus = true;
                        bonusGone = true;
                        scoreBonus = 50;
                        view.setEnabled(false);
                    }
                }
            }
            view.turnVisible(scoreBonus);
        }
        showScoreTotal((ScoreButton) findViewById(R.id.SCORE_TOTAL));

        for (ScoreButton scoreButton : scoreButtonsLeft){
            if(scoreButton.isEnabled()==false){
                checkSbEnabled+=1;
                if(checkSbEnabled == 6){
                    view.setEnabled(false);
                }
            }
        }
    }


//**************************************************************************************************


    //*****Class of operations when the player clicked on one of the cell of scores ********************
    public void clickScoreButton(View view) {

        if (alreadyThrown == 0) {
            return;
        }


        //Make cell selected disable
        view.setEnabled(false);


        //Reset the number of throws
        alreadyThrown = 0;
        //Reset the number of click on the desactive Button
        clickcount = 0;

//******/Determinate the end of the game************************************************************
        num_of_launches++;
        if (num_of_launches == NUM_OF_THROWING_MAX) {

            //update the next activity with the score
            String finalScoreStr = ((ScoreButton)findViewById(R.id.SCORE_TOTAL)).getText().toString();
            EndGame.scoreTotal = Integer.valueOf(finalScoreStr);

            startActivity(new Intent(this, EndGame.class));
        }


//******//Reactive the deactive Button image (grey rolling dice) to the active Button image********
        Button activeButton = (Button) findViewById(R.id.rollingdice);
        activeButton.setEnabled(true);

        Button desactiveButton = (Button) findViewById(R.id.rollingdiceoff);
        desactiveButton.setEnabled(false);

//**************************************************************************************************

        initializeDice();

        //Do automatically dice Throw
        throwDice(activeButton);
    }
}

//***End of the Activity****************************************************************************






