package com.example.user.myapplication;

import android.util.Pair;
import android.widget.ImageButton;

/**
 * Created by Acrylon3 on 13/12/2016.
 */

public class CalculateDice {

    private final static int YATZY_POINTS = 50;

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

    public boolean isRepetitionNumber(int repetingNumber, int minRepeatTimes) {

        int howManyTimes = findHowManyTimesRepeat(repetingNumber);
        return minRepeatTimes <= howManyTimes;
    }

    public int findHighestNRepititions(int n) {
        int sum = 0;
        for (int i = 1; i <= DieImageButton.MAX_VALUE; i++) {
            if (isRepetitionNumber(i, n)) {
                sum = Math.max(n * i, sum);//Die Value maximum
            }
        }
        return sum;
    }

    public int findYatzy(){
        int repetingNumber = dice[0].getValue();
        for (DieImageButton dib : dice) {
            if (dib.getValue() != repetingNumber) {
                return 0;
            }
        }
        return YATZY_POINTS;
    }

    public int findStraight(int start , int end){
        int scoreCount = 0;
        for (int i = start ; i <= end ; i++) {
            if (findHowManyTimesRepeat(i) != 1) {
                return 0;
            }
            else {
                scoreCount += i;
            }
        }

        return scoreCount;

    }
//    public int fullHouse (int three, int two){
//        for (DieImageButton dib : dice) {
//            if (findHowManyTimesRepeat(i)) {
//                return 0;
//            }
//        }
//    }



//    public int totalSum(int sumTotal) {
//        int sum = 0;
//        for (int i = 0; i < dice.length; i++) {
//        }
//    }
}



