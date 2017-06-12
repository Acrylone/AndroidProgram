//package com.example.user.myapplication.DataBase;
//
//import java.util.Date;
//import android.screenshot.ContentValues;
//import android.screenshot.Context;
//import android.database.Cursor;
//import android.provider.BaseColumns;
//
//public class ScoreModel extends BaseModel {
//    public final static String TABLE_NAME = "Score";
//    public static final String KEY_VALUE = "value";
//    public static final String KEY_TIME = "time";
//
//    public ScoreModel(final Context context) {
//        super(context, TABLE_NAME);
//    }
//
//    public Cursor getAllOrdered() {
//        return database.query(TABLE_NAME, null, null, null, null, null, KEY_VALUE + " DESC");
//    }
//
//    public String getAllFormated(){
//        String result = "";
//        Cursor cursor = getAllOrdered();
//        for (int i = 0; i < cursor.getCount(); i++) {
//            if(!cursor.moveToPosition(i)){
//                break;
//            }
//            int value = cursor.getInt(cursor.getColumnIndex(ScoreModel.KEY_VALUE));
//            String time = cursor.getString(cursor.getColumnIndex(ScoreModel.KEY_TIME));
//            result+= (i+1) +". " + value + " " + time + "\n";
//        }
//        return result;
//    }
//
//    public void add(final int value) {
//        final ContentValues values = new ContentValues();
//        values.put(KEY_VALUE, value);
//        Date tmpDate = new Date();
//        String date = tmpDate.getDate() + "." + tmpDate.getMonth()+1;
//
//        values.put(KEY_TIME, date);
//        database.insert(TABLE_NAME, null, values);
//    }
//
//    @Override
//    public void remove(final int id) {
//        database.delete(TABLE_NAME, BaseColumns._ID + " = " + id, null);
//    }
//}
