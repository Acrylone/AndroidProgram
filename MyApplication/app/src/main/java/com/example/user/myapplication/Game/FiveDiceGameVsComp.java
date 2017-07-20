package com.example.user.myapplication.Game;

import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.example.user.myapplication.R;

public class FiveDiceGameVsComp extends FiveDiceGame {


    protected boolean isCompTurn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        isGameWithComp = true;
        setCompButtonsVisible();

        //arbitrary
        isCompTurn = false;
    }

    protected void setCompButtonsVisible() {
        for(ScoreButton s : scoreButtonsComputer) {
            s.setVisibility(View.VISIBLE);
        }
    }

    @Override
    public void clickScoreButton(View v) {
        clickScoreButtonComp(v , false);
    }

    public void clickScoreButtonComp(View v , boolean isClickedByComp) {
        if(isCompTurn && !isClickedByComp) {    //if player clicked on comp turn
            return;
        }

        super.clickScoreButton(v);
        Log.i("turn_end" , "ended turn for " + (isCompTurn? "computer" : "player"));
        isCompTurn = !isCompTurn;

        if(isCompTurn) {
            doCompPlay();
        }
    }

    @Override
    public void throwDice(View view) {
        throwDiceComp(view , false);
    }
    public void throwDiceComp(View view , boolean isClickedByComp) {
        if(isCompTurn && !isClickedByComp) {    //if player clicked on comp turn
            return;
        }

        if(!isCompTurn) {
            super.throwDice(view);
        }
        else {
            Log.i("throw_dice" , "thrown by comp");
            alreadyThrown++;

            for (int i = 0; i < scoreButtonsComputer.size(); i++) {
                ScoreButton currentScore = scoreButtonsComputer.get(i);
                if (currentScore.isEnabled()) {
                    switch (currentScore.getId()) {
                        case R.id.p2_SCORE_ONE:
                            showScore1(currentScore);
                            break;
                        case R.id.p2_SCORE_TWO:
                            showScore2(currentScore);
                            break;
                        case R.id.p2_SCORE_THREE:
                            showScore3(currentScore);
                            break;
                        case R.id.p2_SCORE_FOUR:
                            showScore4(currentScore);
                            break;
                        case R.id.p2_SCORE_FIVE:
                            showScore5(currentScore);
                            break;
                        case R.id.p2_SCORE_SIX:
                            showScore6(currentScore);
                            break;
                        case R.id.p2_PAIR:
                            showScorePair(currentScore);
                            break;
                        case R.id.p2_TWOPAIRS:
                            clickScoreButtonTwoPairs(currentScore);
                            break;
                        case R.id.p2_THREEOFKIND:
                            showScoreThreeOfKind(currentScore);
                            break;
                        case R.id.p2_FOUROFKIND:
                            showScoreFourOfKind(currentScore);
                            break;
                        case R.id.p2_STRAIGHTLOW:
                            showScoreStraightLow(currentScore);
                            break;
                        case R.id.p2_STRAIGHTHIGH:
                            showScoreStraightHigh(currentScore);
                            break;
                        case R.id.p2_FULLHOUSE:
                            showScoreFullHouse(currentScore);
                            break;
                        case R.id.p2_CHANCE:
                            showScoreChance(currentScore);
                            break;
                        case R.id.p2_YATZY:
                            showScoreYatzy(currentScore);
                            break;
                        case R.id.p2_SCORE_BONUS:
                            showScoreBonus(currentScore);
                            break;
                        case R.id.p2_SCORE_TOTAL:
                            showScoreTotal(currentScore);
                            break;
                    }
                }
            }
        }
    }



    protected void doCompPlay() {
        ScoreButton chosenButtonToPlay = scoreButtonsPlayableComputer.get(0);
        scoreButtonsPlayableComputer.remove(chosenButtonToPlay);
        clickScoreButtonComp(chosenButtonToPlay , true);
    }
}
