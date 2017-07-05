//*****Main Game Activity***************************************************************************

package com.example.user.myapplication.Game;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.Typeface;
import android.graphics.drawable.ColorDrawable;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.util.Base64;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.LinearInterpolator;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.user.myapplication.EndGame;
import com.example.user.myapplication.Menu.ChoiceGame;
import com.example.user.myapplication.Menu.Navigation.Rules.Rules;
import com.example.user.myapplication.R;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdSize;
import com.google.android.gms.ads.AdView;

import java.io.ByteArrayOutputStream;
import java.util.ArrayList;
import java.util.List;

//import com.google.android.gms.games.internal.game.Screenshot;

public class FiveDiceGame extends AppCompatActivity implements View.OnClickListener {

    private static final int NUM_OF_THROWING_MAX = 15;
    private static final int NUM_OF_DICE = 5;
    private static final int NUM_OF_ALLOWED_THROWS = 3;

    private static final int INC_PROGRESS_BAR = 7;

    private static int COUNTDOWN = NUM_OF_ALLOWED_THROWS -1;

    private int alreadyThrown;

    protected boolean isAbleToClickScoreButton;

    private boolean bonusGone = false;
    private boolean isBonus = false;

    private View main;
    private ImageView imageView;
    String selectedImagePath;
    public static final String PRODUCT_PHOTO = "photo";
    public static Bitmap product_image;

    SharedPreferences sharedPreferences;
    SharedPreferences.Editor editor;

    private int num_of_launches = 0;

    private DieImageButton[] dice; //Array for the dice
    private List<ScoreButton> scoreButtons; //Array list of the ScoreButton
    private List<ScoreButton> scoreButtonsLeft; //Array list of the ScoreButton
    private CalculateDiceFive calculateDiceFive; //Call the class relevant
    private ProgressBar progressBar;
    int clickcount = 0; //Counter for how many times clicking on the desactive Button -> message
    private int counterTotal = 0;

    List<MediaPlayer> mediaPlayers;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.five_dice_game);


        //********//Countdown ont rightside**************************************************************




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
        TextView pairs = (TextView) findViewById(R.id.pairview);
        pairs.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showPopupPairs(view);
            }
        });
        TextView twopairs = (TextView) findViewById(R.id.twopairview);
        twopairs.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showPopupTwoPairs(view);
            }
        });
        TextView threeofkind = (TextView) findViewById(R.id.threeofkindview);
        threeofkind.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showPopupThreeOfKind(view);
            }
        });
        TextView fourofkind = (TextView) findViewById(R.id.fourofkindview);
        fourofkind.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showPopupFourOfKind(view);
            }
        });
        TextView lowstraight = (TextView) findViewById(R.id.lowstraightview);
        lowstraight.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showPopupLowStraight(view);
            }
        });
        TextView highstraight = (TextView) findViewById(R.id.highstraightview);
        highstraight.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showPopupHighStraight(view);
            }
        });
        TextView fullhouse = (TextView) findViewById(R.id.fullhouseview);
        fullhouse.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showPopupFullHouse(view);
            }
        });
        TextView chance = (TextView) findViewById(R.id.chanceview);
        chance.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showPopupChance(view);
            }
        });
        TextView yatzy = (TextView) findViewById(R.id.yatzyview);
        yatzy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showPopupYatzy(view);
            }
        });
        TextView bonus = (TextView) findViewById(R.id.bonusview);
        bonus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showPopupBonus(view);
            }
        });
////////////////////////////////////////////////////////////////////////////////////////////////////

        alreadyThrown = 0;
        isAbleToClickScoreButton = false;

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
//        final MediaPlayer shakeMp = MediaPlayer.create(this, R.raw.shake_dice);
//
//        final Button play_button = (Button) this.findViewById(R.id.rollingdice);
//        play_button.setOnClickListener(new View.OnClickListener() {
//            public void onClick(View v) {
//                shakeMp.start();
//                throwDice(play_button);
//
//            }
//        });

//**********Make a blink button New Game************************************************************
        final Animation animation = new AlphaAnimation(1, 0); // Change alpha from fully visible to invisible
        final Button btn = (Button) findViewById(R.id.rollingdice);
        final MediaPlayer shakeMp = MediaPlayer.create(this, R.raw.shake_dice);

        animation.setDuration(350); // duration - half a second
        animation.setInterpolator(new

                LinearInterpolator()

        ); // do not alter animation rate
        animation.setRepeatCount(Animation.INFINITE); // Repeat animation infinitely
        animation.setRepeatMode(Animation.REVERSE); // Reverse animation at the end so the button will fade back in
        btn.startAnimation(animation);// Start blink animation

        btn.setOnClickListener(new View.OnClickListener()

                               {

                                   public void onClick(View view) {
                                       shakeMp.start();
                                       throwDice(btn);
                                       view.clearAnimation();
//        mediaPlayer.start();

                                   }

                               }

        );


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

        mediaPlayers = new ArrayList<>();
        final MediaPlayer mp = MediaPlayer.create(this, R.raw.pen);
        mediaPlayers.add(mp);
        final MediaPlayer mp2 = MediaPlayer.create(this, R.raw.pen2);
        mediaPlayers.add(mp2);
        final MediaPlayer mp3 = MediaPlayer.create(this, R.raw.pen3);
        mediaPlayers.add(mp3);
        final MediaPlayer mp4 = MediaPlayer.create(this, R.raw.pen4);
        mediaPlayers.add(mp4);
        final MediaPlayer mp5 = MediaPlayer.create(this, R.raw.pen5);
        mediaPlayers.add(mp5);
        final MediaPlayer mp6 = MediaPlayer.create(this, R.raw.pen6);
        mediaPlayers.add(mp6);


    }
//*****End of OnCreate******************************************************************************
//**************************************************************************************************
//***Show PopUp Window to help the user to understand each box**************************************

    public void showPopupPairs(final View anchorView) {
        String s = "*Pairs* : ⚅ ⚅ ⚀ ⚂ ⚃ \nSum of 2 dice with the same number - Score : 12";
        showPopupGeneral(anchorView, s);
    }

    public void showPopupTwoPairs(final View anchorView) {
        String s = "*2 Pairs* : ⚄ ⚄ ⚅ ⚅ ⚃ \nSum of 2 dice twice with the same number - Score : 22";
        showPopupGeneral(anchorView, s);
    }

    public void showPopupThreeOfKind(final View anchorView) {
        String s = "*3 of Kind* : ⚃ ⚃ ⚃ ⚂ ⚅ \nAt least 3 dice the same - Score : 12";
        showPopupGeneral(anchorView, s);
    }

    public void showPopupFourOfKind(final View anchorView) {
        String s = "*4 of Kind* : ⚁ ⚁ ⚁ ⚁ ⚅ \nAt least 4 dice the same - Score : 8";
        showPopupGeneral(anchorView, s);
    }

    public void showPopupLowStraight(final View anchorView) {
        String s = "*Low Straight* : ⚀ ⚁ ⚂ ⚃ ⚄ \n5 sequential dice between 1 - 5 - Score : 15";
        showPopupGeneral(anchorView, s);
    }

    public void showPopupHighStraight(final View anchorView) {
        String s = "*High Straight* : ⚁ ⚂ ⚃ ⚄ ⚅ \n5 sequential dice between 2 - 6 - Score : 20";
        showPopupGeneral(anchorView, s);
    }

    public void showPopupFullHouse(final View anchorView) {
        String s = "*Full House* : ⚁ ⚁ ⚄ ⚄ ⚄ \n3 of one number and 2 of another - Score : 19";
        showPopupGeneral(anchorView, s);
    }

    public void showPopupChance(final View anchorView) {
        String s = "*Chance* : ⚂ ⚅ ⚃ ⚄ ⚀ \nThe sum of all dice - Score : 19";
        showPopupGeneral(anchorView, s);
    }

    public void showPopupYatzy(final View anchorView) {
        String s = "*Yatzy* : ⚅ ⚅ ⚅ ⚅ ⚅ \nSum of 5 dice with the same number - Score : 50";
        showPopupGeneral(anchorView, s);
    }

    public void showPopupBonus(final View anchorView) {
        String s = "*BONUS* :\n Try to reach 63 points or more, and you receive a bonus of 50 points bonus!";
        showPopupGeneral(anchorView, s);
    }

    public void showPopupGeneral(final View anchorView, String showOnScreen) {

        View popupView = getLayoutInflater().inflate(R.layout.popup_layout, null);

        PopupWindow popupWindow = new PopupWindow(popupView,
                LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);

        TextView tv = (TextView) popupView.findViewById(R.id.tv);

        tv.setText(showOnScreen);
//        tv.setAllCaps(true);
        tv.setTextColor(Color.BLUE);

        popupWindow.setFocusable(true);
        popupView.setBackgroundColor(Color.WHITE);

        // If you need the PopupWindow to dismiss when when touched outside
        popupWindow.setBackgroundDrawable(new ColorDrawable());

        int location[] = new int[2];

        // Get the View's(the one that was clicked in the Fragment) location
        anchorView.getLocationOnScreen(location);

        // Using location, the PopupWindow will be displayed right under anchorView
        popupWindow.showAtLocation(anchorView, Gravity.NO_GRAVITY,
                location[0], location[1] + anchorView.getHeight());

    }


    //*****On Click Score Button - play a move**************************************************
    @Override
    public void onClick(View v) {
        if (!isAbleToClickScoreButton) {
            Toast.makeText(this, "Throw dice first!", Toast.LENGTH_SHORT).show();
            return;
        }

        //randomize sound effect
        int rand = (int) (mediaPlayers.size() * Math.random());
        mediaPlayers.get(rand).start();

        progressBar.setProgress(progressBar.getProgress() + INC_PROGRESS_BAR);
//        zeroAllUnpressedScoreButtons((ScoreButton) v);

        clickScoreButton(v);

//        isAbleToClickScoreButton = false;
    }

    //*****Initialize the dice, active them and update the image dice corresponding*********************
    public void initializeDice() {
        for (int i = 0; i < NUM_OF_DICE; i++) {
            dice[i].setValue(i + 1);
            dice[i].setIsActive(true);
        }
    }

    public void zeroAllUnpressedScoreButtons(ScoreButton v) {
        for (ScoreButton b : scoreButtons) {
            if (b.isEnabled()
                    && b.getId() != (R.id.SCORE_TOTAL)
                    && b.getId() != (R.id.SCORE_BONUS)
                    && !b.equals(v)) { //can press it later
                b.setText("0");
            }
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
        isAbleToClickScoreButton = true;
        TextView tx = (TextView) findViewById(R.id.countdown);
        Typeface custom_font = Typeface.createFromAsset(getAssets(), "fonts/DIGITALDREAM.ttf");
        tx.setTypeface(custom_font);

        tx.setText("" + COUNTDOWN);
        COUNTDOWN--;

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

        for (ScoreButton scoreButton : scoreButtonsLeft) {
            if (scoreButton.isEnabled() == false) {
                checkSbEnabled += 1;
                if (checkSbEnabled == 6) {
                    view.setEnabled(false);
                }
            }
        }
    }


    //**************************************************************************************************

    //    public void saveBitmap(Bitmap bitmap) {
//        File imagePath = new File(Environment.getExternalStorageDirectory() + "/screenshot.png");
//        FileOutputStream fos;
//        try {
//            fos = new FileOutputStream(imagePath);
//            bitmap.compress(Bitmap.CompressFormat.JPEG, 100, fos);
//            fos.flush();
//            fos.close();
//        } catch (FileNotFoundException e) {
//            Log.e("GREC", e.getMessage(), e);
//        } catch (IOException e) {
//            Log.e("GREC", e.getMessage(), e);
//        }
//    }
//
//    public Bitmap getScreenShot(View view) {
//        View screenView = view.getRootView();
//        screenView.setDrawingCacheEnabled(true);
//        Bitmap bitmap = Bitmap.createBitmap(screenView.getDrawingCache());
//        screenView.setDrawingCacheEnabled(false);
//        sharedPreferences = getSharedPreferences("GAME_DATA", Context.MODE_PRIVATE);
//        editor = sharedPreferences.edit();
//        editor.putString("photo", selectedImagePath); // Store selectedImagePath with key "ImagePath". This key will be then used to retrieve data.
//        editor.commit();
//        imageView.setImageBitmap(bitmap);
//        return bitmap;
//    }
//
//    public static void store(Bitmap bm, String fileName){
//        final String dirPath = Environment.getExternalStorageDirectory().getAbsolutePath() + "/Screenshots";
//        File dir = new File(dirPath);
//        if(!dir.exists())
//            dir.mkdirs();
//        File file = new File(dirPath, fileName);
//        try {
//            FileOutputStream fOut = new FileOutputStream(file);
//            bm.compress(Bitmap.CompressFormat.PNG, 85, fOut);
//            fOut.flush();
//            fOut.close();
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//    }

//    private void takeScreenshot() {
//
//        try {
//            // image naming and path  to include sd card  appending name you choose for file
//            String mPath = Environment.getExternalStorageDirectory().toString() + "/" + ".jpg";
//
//            // create bitmap screen capture
//            View v1 = getWindow().getDecorView().getRootView();
//            v1.setDrawingCacheEnabled(true);
//            Bitmap bitmap = Bitmap.createBitmap(v1.getDrawingCache());
//            v1.setDrawingCacheEnabled(false);
//
//            File imageFile = new File(mPath);
//
//            FileOutputStream outputStream = new FileOutputStream(imageFile);
//
//            ImageView imageView = (ImageView)findViewById(R.id.imageView);
//            Bitmap screenshot = Screenshot.takescreenshotOfRootView(imageView);
//            imageView.setImageBitmap(screenshot);
//
//            int quality = 100;
//            bitmap.compress(Bitmap.CompressFormat.JPEG, quality, outputStream);
//            outputStream.flush();
//            outputStream.close();
//
//        } catch (Throwable e) {
//            // Several error may come out with file handling or OOM
//            e.printStackTrace();
//        }
//    }


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
        //Reset the countdown
        COUNTDOWN = NUM_OF_ALLOWED_THROWS -1;


//******/Determinate the end of the game************************************************************

        num_of_launches++;
        if (num_of_launches == NUM_OF_THROWING_MAX) {

            //update the next activity with the score
            String finalScoreStr = ((ScoreButton) findViewById(R.id.SCORE_TOTAL)).getText().toString();
            EndGame.scoreTotal = Integer.valueOf(finalScoreStr);

//            ImageView imageView = (ImageView) findViewById(R.id.imageView);
//            Bitmap screenshot = Screenshot.takescreenshotOfRootView(imageView);
//            imageView.setImageBitmap(screenshot);

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







