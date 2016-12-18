package com.example.user.myapplication;

import android.content.Context;
import android.graphics.Color;
import android.os.Build;
import android.util.AttributeSet;
import android.util.Log;
import android.widget.ImageButton;

/**
 * Created by Acrylon3 on 13/12/2016.
 */

public class DieImageButton extends ImageButton {

    public static final int MAX_VALUE = 6;

    private int dieNum;
    private boolean isActive;

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

//    public DieImageButton(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
//        super(context, attrs, defStyleAttr, defStyleRes);
    //  ctorStuff();
//    }

    private void ctorStuff() {
        this.setIsActive(true);
        this.setBackgroundColor( Color.TRANSPARENT);
    }

    public void throwSelfDie() {
        this.setValue((int) (Math.random() * MAX_VALUE + 1));
    }

    private void updateImage() {
        switch (dieNum) {
            case 1:
//                    setBackground(getResources().getDrawable(R.drawable.one));
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

    public void setValue(int num) {
        this.dieNum = num;
        updateImage();
    }

    public int getValue() {
        return this.dieNum;
    }

    public void setIsActive(boolean isActive) {
        this.isActive = isActive;

        if (isActive) {
            setAlpha((float) 1);
        } else {
            setAlpha((float) 0.5);
        }

        postInvalidate();
    }

    public void toggleActivation() {
        setIsActive(!isActive);
    }


    public boolean isActive() {
        return this.isActive;
    }

}
