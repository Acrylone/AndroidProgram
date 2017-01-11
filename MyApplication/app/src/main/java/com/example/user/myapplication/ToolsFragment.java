package com.example.user.myapplication;


import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;


/**
 * A simple {@link Fragment} subclass.
 */
public class ToolsFragment extends Fragment {


    public ToolsFragment() {



        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {


//        Button desactiveSound = (Button) getView();
//        desactiveSound.setSoundEffectsEnabled(false);
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_tools, container, false);
    }

    public void closeMenuTools(View view) {
        Intent intent = new Intent(String.valueOf((ShowNicknameFragment.class)));
        startActivity(intent);
    }

}
