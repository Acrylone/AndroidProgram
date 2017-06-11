package com.example.user.myapplication.Menu.Navigation;

import android.content.Context;
import android.content.SharedPreferences;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.user.myapplication.EndGame;
import com.example.user.myapplication.R;

public class RecordsActivity extends AppCompatActivity {

    SharedPreferences sharedPreferences;
    SharedPreferences.Editor editor;
    private static final String USER_CONFIG = "USER_CONFIG";
    private static final String PREFS_NAME = "PREFS_NAME";
    private static final String PREFS_SCORE = "PREFS_SCORE";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_records);

        String nickname;
        sharedPreferences = getSharedPreferences(USER_CONFIG, Context.MODE_PRIVATE);
        TextView shownickname = (TextView) findViewById(R.id.nicknameOne);
        nickname = shownickname.getText().toString();
        nickname = sharedPreferences.getString(PREFS_NAME, nickname);
        shownickname.setText(nickname);

        SharedPreferences prefs = this.getSharedPreferences("myPrefsKey", Context.MODE_PRIVATE);
        int scoreOne = prefs.getInt("key", 0);
        TextView showscore = (TextView) findViewById(R.id.ScoreOne);
//        showscore.setText();
        Toast.makeText(RecordsActivity.this, "Score Total " + scoreOne, Toast.LENGTH_SHORT).show();



//        SharedPreferences sharedPref = this.getPreferences(Context.MODE_PRIVATE);
//        int defaultValue = getResources().getInteger(R.string.saved_high_score_default);
//        long highScore = sharedPref.getInt(getString(R.string.saved_high_score), defaultValue);
//        int scoreOne;
//        sharedPreferences = this.getSharedPreferences(USER_CONFIG, Context.MODE_PRIVATE);
//        scoreOne = sharedPreferences.getInt(PREFS_SCORE, 0);
//        TextView showscore = (TextView) findViewById(R.id.ScoreOne);
//        SharedPreferences.Editor editor = sharedPreferences.edit();
//        editor.putInt(PREFS_SCORE, scoreOne);
//        editor.commit();
//        showscore.setText(scoreOne);


        FloatingActionButton ScoreOnePlus1 = (FloatingActionButton) findViewById(R.id.ScoreOnePlus1);

    }
}
