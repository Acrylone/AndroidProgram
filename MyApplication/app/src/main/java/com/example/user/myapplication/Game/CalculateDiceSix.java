package com.example.user.myapplication.Game;

import java.util.HashSet;

/**
 * Created by Acrylon3 on 28/03/2017.
 */

public class CalculateDiceSix extends CalculateDiceFive {
    public CalculateDiceSix(DieImageButton[] dice) {
        super(dice);
    }

    public final static int YATZY_ARCADE_POINTS = 100;

    //****Class for 3 pairs*****************************************************************************
    public int findThreePairs() {
        HashSet<Integer> numbersSet = new HashSet<>();

        for (DieImageButton dib : dice) {
            numbersSet.add(new Integer(dib.getValue()));
        }

        if (numbersSet.size() == 3) {

            //find if really 3 pairs of 2
            int repetitions = findHowManyTimesRepeat(dice[0].getValue());
            if ((repetitions == 2)) {
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

    //****Function for 3+3******************************************************************************
    public int findthreeandthree() {
        HashSet<Integer> numbersSet = new HashSet<>();

        for (DieImageButton dib : dice) {
            numbersSet.add(new Integer(dib.getValue()));
        }

        if (numbersSet.size() == 2) {

            //find if really 3 and 3
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
    //****Function for 4+2******************************************************************************
    public int findfourandtwo() {
        HashSet<Integer> numbersSet = new HashSet<>();

        for (DieImageButton dib : dice) {
            numbersSet.add(new Integer(dib.getValue()));
        }

        if (numbersSet.size() == 2) {

            //find if really 4+2
            int repetitions = findHowManyTimesRepeat(dice[0].getValue());
            if ((repetitions == 4) || (repetitions == 2)) {
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
    public int findChanceArcade() {
        int count0 = dice[0].getValue();
        int count1 = dice[1].getValue();
        int count2 = dice[2].getValue();
        int count3 = dice[3].getValue();
        int count4 = dice[4].getValue();
        int count5 = dice[5].getValue();
        int count = count0 + count1 + count2 + count3 + count4 + count5;

        return count;
    }

    //****Functions for YatzY - and return 100 points if did*************************************************
    public int findYatzyArcade() {
        int repetingNumber = dice[0].getValue();
        for (DieImageButton dib : dice) {
            if (dib.getValue() != repetingNumber) {
                return 0;
            }
        }
        return YATZY_ARCADE_POINTS;

    }

}
