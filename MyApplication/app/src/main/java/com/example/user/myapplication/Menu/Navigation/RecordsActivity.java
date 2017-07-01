package com.example.user.myapplication.Menu.Navigation;

import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.media.Image;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.util.Base64;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.TextView;
import android.widget.Toast;

import com.example.user.myapplication.Game.FiveDiceGame;
import com.example.user.myapplication.R;

import java.io.File;

import static com.example.user.myapplication.R.id.Screenshot;
import static com.example.user.myapplication.R.id.imageView;

public class RecordsActivity extends AppCompatActivity {

    ImageView imageview;
    FiveDiceGame fiveDiceGame;
    boolean whichGame;
    SharedPreferences sharedPreferences;
    SharedPreferences.Editor editor;
    private static final String PREFS_NAME = "PREFS_NAME";
    private static final String PREFS_SCORE = "PREFS_SCORE";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_records);

        String nickname;
        sharedPreferences = getSharedPreferences("GAME_DATA", Context.MODE_PRIVATE);
        TextView shownickname = (TextView) findViewById(R.id.nicknameOne);
        nickname = shownickname.getText().toString();
        nickname = sharedPreferences.getString(PREFS_NAME, nickname);
        shownickname.setText(nickname);


        sharedPreferences = this.getSharedPreferences("GAME_DATA", Context.MODE_PRIVATE);
        editor = sharedPreferences.edit();
        int scoreOne = sharedPreferences.getInt("PREFS_SCORE", 0);
        editor.putInt("PREFS_SCORE", scoreOne);
        editor.apply();
        TextView showscore = (TextView) findViewById(R.id.ScoreOne);
        showscore.setText("" + scoreOne);
        Toast.makeText(RecordsActivity.this, "Score Total " + scoreOne, Toast.LENGTH_SHORT).show();

        whichGame = sharedPreferences.getBoolean("WHICH_GAME", true);
        if (whichGame) {
            TextView whichGame = (TextView) findViewById(R.id.TitleScore1);
            whichGame.setText("5 Game");
        } else {
            TextView whichGame = (TextView) findViewById(R.id.TitleScore1);
            whichGame.setText("6 Game");
        }

        FloatingActionButton ScoreOnePlus1 = (FloatingActionButton) findViewById(R.id.ScoreOnePlus1);
        ScoreOnePlus1.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                final LayoutInflater inflater = getLayoutInflater();
                final View layout = inflater.inflate(R.layout.screenshot,
                        (ViewGroup) findViewById(R.id.Screenshot));
                Toast toast = new Toast(getApplicationContext());
                toast.setGravity(Gravity.CENTER, 0, 0);
                toast.setDuration(Toast.LENGTH_LONG);
                toast.setView(layout);
                toast.show();
            }
        });
    }


// Intent i = new Intent(getApplicationContext(), ChoiceGame.class);
//                startActivity(i);

    public Bitmap decodeBase64(String input) {
        byte[] decodedByte = Base64.decode(input, 0);
        return BitmapFactory.decodeByteArray(decodedByte, 0, decodedByte.length);
    }


    private void openScreenshot(File imageFile) {
        Intent intent = new Intent();
        intent.setAction(Intent.ACTION_VIEW);
        Uri uri = Uri.fromFile(imageFile);
        intent.setDataAndType(uri, "image/*");
        startActivity(intent);
    }
}


