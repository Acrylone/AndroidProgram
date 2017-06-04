package com.example.user.myapplication.Game;

import android.os.Bundle;

import com.example.user.myapplication.R;

/**
 * Created by Acrylon3 on 11/04/2017.
 */

public class ComputerGame extends FiveDiceGame {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.five_dice_game);
    }
}
