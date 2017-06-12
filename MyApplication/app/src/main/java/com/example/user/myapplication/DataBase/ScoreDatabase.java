//package com.example.user.myapplication.DataBase;
//
//import android.screenshot.Context;
//import android.database.sqlite.SQLiteDatabase;
//import android.database.sqlite.SQLiteOpenHelper;
//import android.provider.BaseColumns;
//import android.util.Log;
//import at.bartinger.scoreexample.sql.model.ScoreModel;
//
//import static android.provider.BaseColumns._ID;
//
//public class ScoreDatabase extends SQLiteOpenHelper {
//    public ScoreDatabase(Context context) {
//        super(context, "score.db", null, 1);
//    }
//
//    @Override
//    public void onCreate(SQLiteDatabase db) {
//        // TODO Create your tables here
//        Log.i("ScoreExample", "Create database: " + ScoreModel.TABLE_NAME);
//        /*Creates the table in the database.
//        * id: to access a single entry; for example for deleting a row
//        * value: is the score which we want to save
//        */
//        db.execSQL("create table " + ScoreModel.TABLE_NAME + " ("
//                + BaseColumns.\_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
//                + ScoreModel.KEY\_VALUE + " INTEGER NOT NULL,"
//                + ScoreModel.KEY_TIME + " TEXT);");
//    }
//
//    @Override
//    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) { }
//
//}
