package com.example.user.myapplication;

import android.util.Pair;
import android.widget.ImageButton;

/**
 * Created by Acrylon3 on 13/12/2016.
 */

public class CalculateDice {

    private DieImageButton[] dice;

    public CalculateDice(DieImageButton[] dice) {
        this.dice = dice;
    }


    public int calculateScoreRepetition(int repetingNumber) {
        int timesRepeated = findHowManyTimesRepeat(repetingNumber);
        return timesRepeated * repetingNumber;
    }

    private int findHowManyTimesRepeat(int repetingNumber) {
        int rep = 0;
        for (DieImageButton dib : dice) {
            if (dib.getValue() == repetingNumber) {
                rep++;

            }
        }
        return rep;
    }

    /**
     *
     * @param n - when n=2 find highest pair
     * @return
     */
    public int findHighestNRepititions(int n) {
        int sum = 0;
        for(int i = 1 ; i <= DieImageButton.MAX_VALUE ; i++) {
            if(isRepetitionNumber(i , n)) {
                sum = Math.max(n*i , sum);
            }
        }
        return sum;
    }

    public boolean isRepetitionNumber(int repetingNumber , int minRepeatTimes) {

        int howManyTimes = findHowManyTimesRepeat(repetingNumber);
        return minRepeatTimes <= howManyTimes;
    }






}



