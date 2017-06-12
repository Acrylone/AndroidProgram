package com.example.user.myapplication.Menu;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.LinearInterpolator;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.user.myapplication.FacebookLogin;
import com.example.user.myapplication.GoogleLogin;
import com.example.user.myapplication.Menu.Navigation.ExploitsActivity;
import com.example.user.myapplication.Menu.Navigation.MainFragment;
import com.example.user.myapplication.Menu.Navigation.RecordsActivity;
import com.example.user.myapplication.Menu.Navigation.Rules.Rules;
import com.example.user.myapplication.Menu.Navigation.Send_Activity;
import com.example.user.myapplication.Menu.Navigation.ShareFragment;
import com.example.user.myapplication.Menu.Navigation.ToolsActivity;
import com.example.user.myapplication.R;
import com.facebook.login.widget.LoginButton;

import static com.example.user.myapplication.R.id.nickname_edit;
import static com.example.user.myapplication.R.id.show_nickname;

public class MainMenuActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    NavigationView navigationView = null;
    Toolbar toolbar = null;
//    SharedPreferences sharedPref;
    SharedPreferences.Editor editor;
    private static final String PREFS = "PREFS";
    private static final String PREFS_NAME = "PREFS_NAME";
    private static final String USER_CONFIG = "USER_CONFIG";
    SharedPreferences sharedPreferences;

    EditText usernickname;
    TextView viewusername;
    String nickname;
    final String EXTRA_LOGIN = "user_login";
    LoginButton login_button;



//    MediaPlayer mediaPlayer=null;


    //Button launches to the NewGame Activity
//    public void newGame(View view) {
//        Intent intent = new Intent(this, FiveDiceGame.class);
//        startActivity(intent);
//
//    }


    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_start);

/////////////////////////////////////////////////////////////////////////////////////////////////////
//            DisplayMetrics dm = new DisplayMetrics();                                         ////
//            getWindowManager().getDefaultDisplay().getMetrics(dm);                            ////
//                                                                                              ////
//            int width = dm.widthPixels;                                                       ////
//            int height = dm.heightPixels;                                                     ////
//                                                                                              ////
//            getWindow().setLayout((int) (width * .8), (int) (height * .6));                   ////
////////////////////////////////////////////////////////////////////////////////////////////////////


        //load saved preferences
//        editor = (SharedPreferences.Editor) getPreferences(MODE_PRIVATE);
        viewusername = (TextView) findViewById(show_nickname);
        sharedPreferences = getSharedPreferences("GAME_DATA", MODE_PRIVATE);
        editor = sharedPreferences.edit();

        //**********Make a blink button New Game************************************************************
        final Animation animation = new AlphaAnimation(1, 0); // Change alpha from fully visible to invisible
        final Button btn = (Button) findViewById(R.id.newgame);
        final MediaPlayer mediaPlayer = MediaPlayer.create(this, R.raw.newgame);

        animation.setDuration(750); // duration - half a second
        animation.setInterpolator(new

                LinearInterpolator()

        ); // do not alter animation rate
        animation.setRepeatCount(Animation.INFINITE); // Repeat animation infinitely
        animation.setRepeatMode(Animation.REVERSE); // Reverse animation at the end so the button will fade back in
        btn.startAnimation(animation);// Start blink animation

        btn.setOnClickListener(new View.OnClickListener()

                               {

                                   public void onClick(View view) {
                                       view.clearAnimation();
//        mediaPlayer.start();
                                       Intent i = new Intent(getApplicationContext(), ChoiceGame.class);
                                       startActivity(i);
                                   }

                               }

        );

//**********Show the nickname in the top of the Menu************************************************
        ShowNicknameFragment fragment = new ShowNicknameFragment();
        FragmentTransaction fragmentTransaction =
                getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.fragment_container, fragment);
        fragmentTransaction.commit();

        toolbar = (Toolbar)

                findViewById(R.id.toolbar);

        setSupportActionBar(toolbar);

        //LayoutInflater
        LayoutInflater inflater = LayoutInflater.from(this);

        final View view = inflater.inflate(R.layout.notification_nickname, null);

        //AlertDialog - Window Open in the start Menu Activity**************************************

        //objectif : sauvegarder 1 seule fois le nom et l'age de l'utilisateur

        //pour cela, on commence par regarder si on a déjà des éléments sauvegardés
        Log.i("sharedPreferences" , "saved: " + sharedPreferences.getString(PREFS_NAME , null));
        if (sharedPreferences.contains(PREFS_NAME)) {

            Log.i("sharedPreferences" , "found!!!: " + sharedPreferences.getString(PREFS_NAME , ""));

            nickname = sharedPreferences.getString(PREFS_NAME, "");

            Toast.makeText(this, " name: " + nickname, Toast.LENGTH_LONG).show();
            viewusername.setText("   Welcome Back " + nickname);
//            viewusername.setText("Welcome " + nickname);

        } else {
            Log.i("sharedPreferences" , "not found!!!");

            AlertDialog.Builder builderAlert = new AlertDialog.Builder(this);
            builderAlert.setTitle("Enter a Nickname");
            builderAlert.setIcon(R.drawable.dice3d);
            builderAlert.setView(view);

            builderAlert.setPositiveButton("FACEBOOK", new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int id) {
                    Intent i=new Intent(MainMenuActivity.this, FacebookLogin.class);
                    startActivity(i);
                }
            });
            builderAlert.setNegativeButton("GOOGLE",new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int id) {
                    Intent i=new Intent(MainMenuActivity.this, GoogleLogin.class);
                    startActivity(i);
                }
            });

            builderAlert.setNeutralButton("OK", new DialogInterface.OnClickListener()     {
                public void onClick(DialogInterface dialog, int id) {
                    Log.i("clicked" , "OK button");
                    usernickname = (EditText) view.findViewById(nickname_edit);

                    nickname = usernickname.getText().toString();
                    editor.putString(PREFS_NAME, nickname);
                    editor.apply();
                    Log.i("sharedPreferences" , "saved: " + sharedPreferences.getString(PREFS_NAME , null));
                    viewusername.setText("Welcome " + nickname);
                }
            });
            AlertDialog alert = builderAlert.create();
            alert.show();


//            Toast.makeText(this, "SAVE! You can change in the tools menu", Toast.LENGTH_SHORT).show();
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        navigationView = (NavigationView)

                findViewById(R.id.nav_view);

        navigationView.setNavigationItemSelectedListener(this);

    }


    //**************************************************************************************************
//**************************************************************************************************
    //*******Button launches to the Rules Activity******************************************************

    public void goRules(View view) {
        Intent intent = new Intent(this, Rules.class);
        startActivity(intent);
    }

    //*******Navigation Slide Menu**********************************************************************
    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    //*******Navigation Slide Menu**********************************************************************
    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
//        proper();
        boolean ret = false;
        int id = item.getItemId();

//*******Options Navigation Slide Menu******************************UNDER CONSTRUCTION**************
        if (id == R.id.nav_view) { //Main Menu Shows
            MainFragment fragment = new MainFragment();
            android.support.v4.app.FragmentTransaction fragmentTransaction =
                    getSupportFragmentManager().beginTransaction();
            fragmentTransaction.replace(R.id.fragment_container, fragment);
            fragmentTransaction.commit();
        } else if (id == R.id.nav_records) { //Records Menu
            Intent intent = new Intent(this, RecordsActivity.class);
            startActivity(intent);
        } else if (id == R.id.nav_exploits) { //Exploits Menu
            Intent intent = new Intent(this, ExploitsActivity.class);
            startActivity(intent);

        } else if (id == R.id.nav_tools) { //Tools Menu
            Intent intent = new Intent(this, ToolsActivity.class);
            startActivity(intent);

        } else if (id == R.id.nav_share) { //Share Menu
            ShareFragment fragment = new ShareFragment();
            android.support.v4.app.FragmentTransaction fragmentTransaction =
                    getSupportFragmentManager().beginTransaction();
            fragmentTransaction.replace(R.id.fragment_container, fragment);
            fragmentTransaction.commit();

        } else if (id == R.id.nav_send) { //Send Menu
            Intent intent = new Intent(this, Send_Activity.class);
            startActivity(intent);
//            Send_Activity activity = new Send_Activity();
//            android.support.v4.app.FragmentTransaction fragmentTransaction =
//                    getSupportFragmentManager().beginTransaction();
//            Activity(R.id.fragment_container, activity);
//            fragmentTransaction.commit();
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return ret;
    }


//*******End of Slide Menu Activity*****************************************************************
}

