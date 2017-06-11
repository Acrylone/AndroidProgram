package com.example.user.myapplication.Menu.Navigation;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.user.myapplication.Menu.MainMenuActivity;
import com.example.user.myapplication.R;

public class ToolsActivity extends AppCompatActivity {

    //    final ToggleButton sound_on = (ToggleButton) findViewById(R.id.sound_on);
//    final ToggleButton sound_off = (ToggleButton) findViewById(R.id.sound_off);
    SharedPreferences.Editor editor;
    private static final String USER_CONFIG = "USER_CONFIG";
    private static final String PREFS_NAME = "PREFS_NAME";

    SharedPreferences sharedPreferences;

    EditText usernickname;
    Button changeNickname;
    TextView viewusername;

    String nickname;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tools);

//        gotomenu.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent i = new Intent(getApplicationContext(), MainMenuActivity.class);
//                startActivity(i);
//            }
//
//
//        });

//        viewusername = (TextView) findViewById(show_nickname2);
        sharedPreferences = getSharedPreferences(USER_CONFIG, Context.MODE_PRIVATE);
        usernickname = (EditText) findViewById(R.id.nickname_change);
        changeNickname = (Button) findViewById(R.id.changeNickname);
        nickname = sharedPreferences.getString(PREFS_NAME, nickname);
        usernickname.setText(nickname);

        Toast.makeText(ToolsActivity.this, "Nickname : " + nickname, Toast.LENGTH_SHORT).show();

        changeNickname.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                nickname = usernickname.getText().toString();

                SharedPreferences.Editor editor = sharedPreferences.edit();

                editor.putString(PREFS_NAME, nickname);
                editor.apply();

                Toast.makeText(ToolsActivity.this, "Nickname Changed to " + nickname, Toast.LENGTH_SHORT).show();

                InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                imm.hideSoftInputFromWindow(usernickname.getWindowToken(), 0);

            }
        });
    }


    public void backMainMenu(View view) {
        Intent intent = new Intent(this, MainMenuActivity.class);
        startActivity(intent);
    }

//    public void facebookLogin(View view) {
//        Intent i = new Intent(ToolsActivity.this, FacebookLogin.class);
//        startActivity(i);
//    }
//
//    public void googleLogin(View view) {
//        Intent i = new Intent(ToolsActivity.this, GoogleLogin.class);
//        startActivity(i);
//    }

//        sound_on.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                sound_off.setEnabled(false);
//                sound_on.setEnabled(true);
//
//            }
//        });
//        sound_off.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                sound_on.setEnabled(false);
//                sound_off.setEnabled(true);
//                sound_off.setSoundEffectsEnabled(false);
//            }
//        });
//    }


}
