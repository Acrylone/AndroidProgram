package com.example.user.myapplication.Game;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;

import com.example.user.myapplication.IsoundMaker;

/**
 * Created by Acrylon3 on 13/12/2016.
 */

public class ScoreButton extends Button implements ConstructorOverrider , IsoundMaker {


    public ScoreButton(Context context) {
        super(context);
        ctorStuff();
    }

    public ScoreButton(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        ctorStuff();
    }

    public ScoreButton(Context context, AttributeSet attrs) {
        super(context, attrs);
        ctorStuff();
    }

    public ScoreButton(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        ctorStuff();
    }

//****Set the layout panel of dice and cells ScoreButton********************************************
    @Override
    public void ctorStuff(){
//        turnInvisible();
        this.setPadding(0,0,0,0);
        LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(6,6);
        setLayoutParams(lp);
    }

    private void turnInvisible(View view){
        setVisibility(view.INVISIBLE);
    }

    public void turnVisible(int num){
        setVisibility(View.VISIBLE);
        this.setText(""+num);
    }



    @Override
    public void makeSound() {
        //TODO
    }

}
//**************************************************************************************************
