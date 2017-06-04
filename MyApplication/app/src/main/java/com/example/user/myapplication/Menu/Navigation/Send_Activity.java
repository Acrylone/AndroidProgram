package com.example.user.myapplication.Menu.Navigation;

import android.provider.ContactsContract;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.QuickContactBadge;

import com.example.user.myapplication.R;

public class Send_Activity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_send);

        QuickContactBadge badgeSmall = (QuickContactBadge) findViewById(R.id.badge_small);
        badgeSmall.assignContactFromEmail("any@gmail.com", true);
        badgeSmall.setMode(ContactsContract.QuickContact.MODE_SMALL);
    }
}
