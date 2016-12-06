package com.example.user.myapplication;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity_newGame extends AppCompatActivity {

    private static final int NUM_OF_DICE = 6;
    private static final int NUM_OF_ALLOWED_THROWS = 3;
    private int alreadyThrown;

    private Button[] dice;
    private boolean[] diceActivations;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_newgame);

        alreadyThrown = 0;

        dice = new Button[NUM_OF_DICE];
        dice[0] = (Button) findViewById(R.id.diceOne);
        dice[1] = (Button) findViewById(R.id.diceTwo);
        dice[2] = (Button) findViewById(R.id.diceThree);
        dice[3] = (Button) findViewById(R.id.diceFour);
        dice[4] = (Button) findViewById(R.id.diceFive);
        dice[5] = (Button) findViewById(R.id.diceSix);

        diceActivations = new boolean[NUM_OF_DICE];
        for(int i = 0 ; i< diceActivations.length ; i++) {
            diceActivations[i] = true;
        }
    }

    public int getRandomDieNum() {
        return (int) (Math.random() * 6 + 1);
    }

    public void throwDice(View view) {

        if(alreadyThrown < NUM_OF_ALLOWED_THROWS) {
            switch (view.getId()) {
                case R.id.launch:
                    for (int i = 0 ; i < dice.length ; i++) {
                        if(diceActivations[i]) {
                            dice[i].setText(String.valueOf(getRandomDieNum()));
                        }
                    }
                    break;
            }

            alreadyThrown++;

            if(alreadyThrown == NUM_OF_ALLOWED_THROWS) {
                Button throwButton = (Button)findViewById(R.id.launch);
                throwButton.setEnabled(false);
            }
        }
    }

    public void dieActivation(View view) {
        Context context = getApplicationContext();
        String text = "Hello toast!";
        int duration = Toast.LENGTH_SHORT;

        Button b = (Button) view;

//        b.setAlpha((float)0.5);

        for(int i = 0 ; i < dice.length ; i++) {
            if((Button)view == dice[i]) {
                diceActivations[i] = !diceActivations[i];

                if(diceActivations[i]) {
                    b.setAlpha(1);
                }
                else {
                    b.setAlpha((float)0.5);
                }
                break;
            }
        }

//        switch (view.getId()) {
//            case R.id.diceOne:
//                text += "1";
//                break;
//            case R.id.diceTwo:
//                text += "2";
//                break;
//            case R.id.diceThree:
//                text += "3";
//                break;
//            case R.id.diceFour:
//                text += "4";
//                break;
//            case R.id.diceFive:
//                text += "5";
//                break;
//            case R.id.diceSix:
//                text += "6";
//                break;
//            default:
//                break;
//
//
//        }


//
//        Toast toast = Toast.makeText(context, text, duration);
//        toast.show();
    }
}


