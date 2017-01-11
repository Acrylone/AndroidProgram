package com.example.user.myapplication;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;

/**
 * Created by Acrylon3 on 13/12/2016.
 */

public class ScoreButton extends Button{

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

    private void ctorStuff(){
        turnInvisible();
        this.setPadding(0,0,0,0);
        LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(6,6);
        setLayoutParams(lp);
    }

    private void turnInvisible(){
        setVisibility(View.INVISIBLE);
    }
    public void turnVisible(int num){
        setVisibility(View.VISIBLE);
        this.setText(""+num);
    }

}
