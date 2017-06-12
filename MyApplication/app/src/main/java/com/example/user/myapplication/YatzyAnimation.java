//package com.example.user.myapplication;
//
//import android.screenshot.Context;
//import android.graphics.Color;
//import android.util.AttributeSet;
//import android.util.Log;
//
///**
// * Created by Acrylon3 on 13/01/2017.
// */
//
//public class YatzyAnimation {
//
//    private int dieYatzy;
//    private boolean isActive;
//
//    public YatzyAnimation(Context context) {
//        super(context);
//        ctorStuff();
//    }
//
//    public YatzyAnimation(Context context, AttributeSet attrs) {
//        super(context, attrs);
//        ctorStuff();
//    }
//
//    public YatzyAnimation(Context context, AttributeSet attrs, int defStyleAttr) {
//        super(context, attrs, defStyleAttr);
//        ctorStuff();
//    }
//
////    public YatzyAnimation(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
////        super(context, attrs, defStyleAttr, defStyleRes);
////  ctorStuff();
////    }
//
//    private void ctorStuff() {
//        this.setIsActive(true);
//    }
//
//
//    private void updateImage() {
//        switch (dieYatzy) {
//            case 1:
//                setImageResource(R.drawable.yatzybleu);
//                break;
//            case 2:
//                setImageResource(R.drawable.yatzyorange);
//                break;
//            case 3:
//                setImageResource(R.drawable.yatzyrouge);
//                break;
//            case 4:
//                setImageResource(R.drawable.yatzyfeu);
//                break;
////            case 5:
////                setImageResource(R.drawable.five);
////                break;
////            default:
////                Log.i("ERROR", "VALUE IS NOT 1-6");
//        }
//
//    }
//
//    private void setImageResource(int yatzybleu) }
//
//    }
//
//    public void setValue(int num) {
//            this.dieYatzy = num;
//            updateImage();
//    }
//
//    public int getValue() {
//        return this.dieYatzy;
//    }
//
//    public void setIsActive(boolean isActive) {
//        this.isActive = isActive;
//
//        if (isActive) {
//            setA((float) 1);
//        } else {
//            setAlpha((float) 0.5);
//        }
//
//    }
//
//    public void toggleActivation() {
//        setIsActive(!isActive);
//    }
//
//
//    public boolean isActive() {
//        return this.isActive;
//    }
//
//
//
//
//
//}
