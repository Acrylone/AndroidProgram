package com.example.user.myapplication.Game;

import java.util.HashSet;

/**
 * Created by Acrylon3 on 13/12/2016.
 */

public class CalculateDiceFive {

    public final static int YATZY_CLASSIC_POINTS = 50;

    protected DieImageButton[] dice;//The array dice from Button type

    public CalculateDiceFive(DieImageButton[] dice) {
        this.dice = dice;
    }

    //******Return the result of the number repeated ***************************************************
    public int calculateScoreRepetition(int repetingNumber) {
        int timesRepeated = findHowManyTimesRepeat(repetingNumber);
        return timesRepeated * repetingNumber;
    }


    //***Check how many times the relevant number is repeating******************************************
    protected int findHowManyTimesRepeat(int repetingNumber) {
        int rep = 0;
        for (DieImageButton dib : dice) {
            if (dib.getValue() == repetingNumber) {
                rep++;
            }
        }
        return rep;
    }


    //***Check if the number is repeating or not********************************************************
    protected boolean isNumberOccuring(int numToSearch){
        return findHowManyTimesRepeat(numToSearch) != 0;
    }
    public boolean isRepetitionNumber(int repetingNumber, int minRepeatTimes) {

        int howManyTimes = findHowManyTimesRepeat(repetingNumber);
        return minRepeatTimes <= howManyTimes;
    }


    //***Return the max relevant repetition number like 2, 3 or 4 same numbers**************************
    public int findHighestNRepititions(int n) {
        int sum = 0;
        for (int i = 1; i <= DieImageButton.MAX_VALUE; i++) {
            if (isRepetitionNumber(i, n)) {
                sum = Math.max(n * i, sum);//Die Value maximum
            }

        }
        return sum;

    }


    //****Class for 2 pairs*****************************************************************************
    public int findTwoPairs() {

        int[] valueRepetitions = new int[DieImageButton.MAX_VALUE + 1];

        for (DieImageButton dib : dice) {
            valueRepetitions[dib.getValue()]++;
        }

        int pairs = 0;
        int firstpair = 0;
        for (int i = valueRepetitions.length - 1; i >= 0; i--) {
            if (valueRepetitions[i] >= 2) {
                pairs++;
                if (pairs == 1) {
                    firstpair = i;
                }
            }

            if (pairs == 2) {
                return firstpair * 2 + i * 2;
            }
        }
        return 0;
    }


    //****Functions for low straight and high straight******************************************************
    public int findStraight(int start, int end) {
        int scoreCount = 0;
        for (int i = start; i <= end; i++) {
            if (findHowManyTimesRepeat(i) != 1) {
                return 0;
            } else {
                scoreCount += i;
            }
        }

        return scoreCount;
    }


    //****Function for Full House**************************************************************************
    public int findfullHouse() {
        HashSet<Integer> numbersSet = new HashSet<>();

        for (DieImageButton dib : dice) {
            numbersSet.add(new Integer(dib.getValue()));
        }

        if (numbersSet.size() == 2) {

            //find if really full house
            int repetitions = findHowManyTimesRepeat(dice[0].getValue());
            if ((repetitions == 2) || (repetitions == 3)) {
                int counter = 0;
                for (DieImageButton dib : dice) {
                    counter += dib.getValue();

                }
                return counter;

            } else {
                return 0;
            }

        }
        return 0;
    }


    //****Functions for calculate "Chance" - All the dice is included***************************************
    public int findChanceClassic() {
        int count0 = dice[0].getValue();
        int count1 = dice[1].getValue();
        int count2 = dice[2].getValue();
        int count3 = dice[3].getValue();
        int count4 = dice[4].getValue();
        int count = count0 + count1 + count2 + count3 + count4;

        return count;
    }


    //****Functions for YatzY - and return 50 points if did*************************************************
    public int findYatzyClassic() {
        int repetingNumber = dice[0].getValue();
        for (DieImageButton dib : dice) {
            if (dib.getValue() != repetingNumber) {
                return 0;
            }
        }
        return YATZY_CLASSIC_POINTS;

    }


}


//****End of the Activity***************************************************************************

