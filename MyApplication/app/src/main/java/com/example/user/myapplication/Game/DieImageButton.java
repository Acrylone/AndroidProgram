package com.example.user.myapplication.Game;

import android.content.Context;
import android.graphics.Color;
import android.util.AttributeSet;
import android.util.Log;
import android.widget.ImageButton;

import com.example.user.myapplication.R;

/**
 * Created by Acrylon3 on 13/12/2016.
 */

public class DieImageButton extends ImageButton {

    public static final int MAX_VALUE = 6; //Number of dice (number of face dice on the context)

    private int dieNum; //The number into dice
    private boolean isActive;

//****Classes of constructor************************************************************************
    public DieImageButton(Context context) {
        super(context);
        ctorStuff();
    }

    public DieImageButton(Context context, AttributeSet attrs) {
        super(context, attrs);
        ctorStuff();
    }

    public DieImageButton(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        ctorStuff();
    }

//    public YatzyAnimation(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
//        super(context, attrs, defStyleAttr, defStyleRes);
//      ctorStuff();
//    }

    private void ctorStuff() {
        this.setIsActive(true);
        this.setBackgroundColor( Color.TRANSPARENT);
    }

//****Create a random number of die*****************************************************************
    public void throwSelfDie() {
        this.setValue((int) (Math.random() * MAX_VALUE + 1));
    }


//****Create the image dice and update them in function of the number die***************************
    private void updateImage() {
        switch (dieNum) {
            case 1:
                setImageResource(R.drawable.one);
                break;
            case 2:
                setImageResource(R.drawable.two);
                break;
            case 3:
                setImageResource(R.drawable.three);
                break;
            case 4:
                setImageResource(R.drawable.four);
                break;
            case 5:
                setImageResource(R.drawable.five);
                break;
            case 6:
                setImageResource(R.drawable.six);
                break;
            default:
                Log.i("ERROR", "VALUE IS NOT 1-6");
        }
        postInvalidate();

    }


//****Put the value of the relevant number of dice into constant int********************************
    public void setValue(int num) {
        this.dieNum = num;
        updateImage();
    }


//****Return the value of die number****************************************************************
    public int getValue() {
        return this.dieNum;
    }


//****On clicking dice make active or desactive it**************************************************
    public void setIsActive(boolean isActive) {
        this.isActive = isActive;

        if (isActive) {
            setAlpha((float) 1);
        } else {
            setAlpha((float) 0.5);
        }

        postInvalidate();
    }


//****Boolean active dice***************************************************************************
    public void toggleActivation() {
        setIsActive(!isActive);
    }

    public boolean isActive() {
        return this.isActive;
    }

}
