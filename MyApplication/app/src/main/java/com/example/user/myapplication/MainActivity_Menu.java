package com.example.user.myapplication;

import android.app.ActionBar;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.TextView;

import static com.example.user.myapplication.R.id.nickname;
import static com.example.user.myapplication.R.id.show_nickname;

public class MainActivity_Menu extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    NavigationView navigationView = null;
    Toolbar toolbar = null;

    //Bouton qui envoie vers le jeu principal
    public void newGame(View view) {
        Intent intent = new Intent(this, MainActivity_newGame.class);
        startActivity(intent);
    }


    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_start);

        //
        ShowNicknameFragment fragment = new ShowNicknameFragment();
        android.support.v4.app.FragmentTransaction fragmentTransaction =
                getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.fragment_container, fragment);
        fragmentTransaction.commit();

        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        AlertDialog.Builder builderAlert = new AlertDialog.Builder(this);
        builderAlert.setTitle("Please enter a nickname");
        //LayoutInflater
        LayoutInflater inflater = LayoutInflater.from(this);

        ActionBar.LayoutParams params = new ActionBar.LayoutParams(
                ActionBar.LayoutParams.MATCH_PARENT,
                ActionBar.LayoutParams.MATCH_PARENT, Gravity.CENTER);

        final View view = inflater.inflate(R.layout.notification_nickname, null);
        //AlertDialog
        builderAlert.setView(view);
        builderAlert.setPositiveButton("CONFIRM", new DialogInterface.OnClickListener() {

            public void onClick(DialogInterface dialog, int idx) {
                EditText txt = (EditText) view.findViewById(nickname);
                String nickname = txt.getText().toString();
//                Toast.makeText(MainActivity_Menu.this,"Welcome " + txt.getText(),Toast.LENGTH_LONG).show();
                TextView v = (TextView) findViewById(show_nickname);
                v.setText("Welcome " + nickname);
//                getSupportActionBar().getCustomView(nickname)(v, params);

            }
        });

        AlertDialog alertDialog = builderAlert.create();
        alertDialog.show();


//        LayoutInflater.from(this).inflate(R.layout.app_bar_main_activity_start,
//                null);


        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
    }

    public void goRules(View view) {
        Intent intent = new Intent(this, Rules.class);
        startActivity(intent);
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }


    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
//        proper();
        boolean ret = false;
        int id = item.getItemId();

        if (id == R.id.nav_view) {
            MainFragment fragment = new MainFragment();
            android.support.v4.app.FragmentTransaction fragmentTransaction =
                    getSupportFragmentManager().beginTransaction();
            fragmentTransaction.replace(R.id.fragment_container, fragment);
            fragmentTransaction.commit();
        } else if (id == R.id.nav_records) {
            RecordsFragment fragment = new RecordsFragment();
            android.support.v4.app.FragmentTransaction fragmentTransaction =
                    getSupportFragmentManager().beginTransaction();
            fragmentTransaction.replace(R.id.fragment_container, fragment);
            fragmentTransaction.commit();
        } else if (id == R.id.nav_exploits) {
            ExploitsFragment fragment = new ExploitsFragment();
            android.support.v4.app.FragmentTransaction fragmentTransaction =
                    getSupportFragmentManager().beginTransaction();
            fragmentTransaction.replace(R.id.fragment_container, fragment);
            fragmentTransaction.commit();

        } else if (id == R.id.nav_tools) {
            ToolsFragment fragment = new ToolsFragment();
            android.support.v4.app.FragmentTransaction fragmentTransaction =
                    getSupportFragmentManager().beginTransaction();
            fragmentTransaction.replace(R.id.fragment_container, fragment);
            fragmentTransaction.commit();

        } else if (id == R.id.nav_share) {
            ShareFragment fragment = new ShareFragment();
            android.support.v4.app.FragmentTransaction fragmentTransaction =
                    getSupportFragmentManager().beginTransaction();
            fragmentTransaction.replace(R.id.fragment_container, fragment);
            fragmentTransaction.commit();

        } else if (id == R.id.nav_send) {
            SendFragment fragment = new SendFragment();
            android.support.v4.app.FragmentTransaction fragmentTransaction =
                    getSupportFragmentManager().beginTransaction();
            fragmentTransaction.replace(R.id.fragment_container, fragment);
            fragmentTransaction.commit();
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return ret;
    }


}

