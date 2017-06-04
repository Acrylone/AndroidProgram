package com.example.user.myapplication.Menu.Navigation.Rules;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ExpandableListView;

import com.example.user.myapplication.R;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Rules extends AppCompatActivity {

    ExpandableListAdapter listAdapter;
    ExpandableListView expListView;
    List<String> listDataHeader;
    HashMap<String, List<String>> listDataChild;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rules);

        // get the listview
        expListView = (ExpandableListView) findViewById(R.id.lvExp);

        // preparing list data
        prepareListData();

        listAdapter = new ExpandableListAdapter(this, listDataHeader, listDataChild);

        // setting list adapter
        expListView.setAdapter(listAdapter);
    }

    /*
     * Preparing the list data
     */
    private void prepareListData() {
        listDataHeader = new ArrayList<String>();
        listDataChild = new HashMap<String, List<String>>();

        // Adding child data
        listDataHeader.add( "Objective of the Game");
        listDataHeader.add("Rolling the Dice");
        listDataHeader.add("Scoring");
        listDataHeader.add("Left Section");
        listDataHeader.add("Right Section");
        listDataHeader.add("YatzY");
        listDataHeader.add("Bonus");

        // Adding child data
        List<String> Objective = new ArrayList<String>();
        Objective.add( "YatzY can be played in solitary or multiplayer.\n"+
                       "The multiplayer version simply consists of a number of players playing the solitary version simultaneously, "+
                       "with the highest score winning. \nThe game consists of 15 rounds. In each round, you roll the dice and then score the roll in one of 15 categories (19 categories in 6 dice).\n"+
                       "You must score once in each category which means that towards the end of the game you may have to settle for scoring zero in some categories."+
                       "The score is determined by a different rule for each category; see the section on Scoring below.");

        List<String> RollingTheDice = new ArrayList<String>();
        RollingTheDice.add("You have five or six dice which you can roll, represented by the die faces at the bottom of the applet window." +
                           " To start with, you roll all dice by clicking on the 'Rolling The Dice'.\nAfter you roll all dice, you can either score the current roll," +
                           " or re-roll any or all of the five or six dice.\nTo re-roll some of the dice, click on the toggle button underneath the die face you want to re-roll," +
                           " then click on the Re-roll button. This will re-roll the selected dice, leaving the unselected ones unchanged." +
                           " You can roll the dice a total of three times, the initial roll (in which you must roll all the dice) plus 2 re-rolls of any or all dice." +
                           " After rolling three times, you must score the roll. Once you've scored the roll, you roll all the dice again and repeat the process." +
                           " You continue until all 15 categories have been filled, at which time the game is over.");

        List<String> Scoring = new ArrayList<String>();
        Scoring.add("Once you have the dice face combination you want to score, you score the roll in one of the 15 categories.\n" +
                    "You do this by clicking on one of the radio buttons in either the Upper Scores or Lower Scores box.\n" +
                    "Once a category has been scored, it is closed out for the rest of the game you cannot change a category's score once it's been set.\n" +
                    "Each category defines its own scoring rules, as described below.");

        List<String> LeftScores  = new ArrayList<String>();
        LeftScores.add("In the left section there are six boxes. The score in each of these boxes is determined by adding the total number of dice matching that box.\n" +
                       "For Example :\n" +
                       "*Ones* : [ 1 ] [ 1 ] [ 1 ] [ 3 ] [ 4 ] \nSum of dice with number 1 - Score : 3\n" +
                       "*Twos* : [ 2 ] [ 2 ] [ 2 ] [ 5 ] [ 6 ] \nSum of dice with number 2 - Score : 6\n" +
                       "*Threes* : [ 3 ] [ 3 ] [ 3 ] [ 3 ] [ 5 ] \nSum of dice with number 3 - Score : 12\n" +
                       "*Fours* : [ 1 ] [ 4 ] [ 1 ] [ 4 ] [ 6 ] \nSum of dice with number 4 - Score : 8\n" +
                       "*Fives* : [ 5 ] [ 1 ] [ 5 ] [ 5 ] [ 6 ] \nSum of dice with number 5 - Score : 15\n" +
                       "*Sixes* : [ 4 ] [ 6 ] [ 6 ] [ 6 ] [ 6 ] \nSum of dice with number 6 - Score : 24\n");
//
        List<String> RightScores = new ArrayList<String>();
        RightScores.add("*Pairs* : [ 6 ] [ 6 ] [ 1 ] [ 3 ] [ 4 ] \nSum of 2 dice with the same number - Score : 12\n\n" +
                        "*2 Pairs* : [ 5 ] [ 5 ] [ 6 ] [ 6 ] [ 4 ] \nSum of 2 dice twice with the same number - Score : 22\n\n" +
                        "*[6 DICES] 3 Pairs* : [ 2 ] [ 2 ] [ 4 ] [ 4 ] [ 6 ] [ 6 ] \nSum of 3 dice 3 times with the same number - Score : 24\n\n" +
                        "*3 of Kind* : [ 4 ] [ 4 ] [ 4 ] [ 3 ] [ 6 ] \nAt least 3 dice the same - Score : 12\n\n" +
                        "*4 of Kind* : [ 2 ] [ 2 ] [ 2 ] [ 2 ] [ 6 ] \nAt least 4 dice the same - Score : 8\n\n" +
                        "*[6 DICES] 5 of Kind* : [ 5 ] [ 5 ] [ 5 ] [ 5 ] [ 5 ] [ 6 ] \nAt least 5 dice the same - Score : 25\n\n" +
                        "*Low Straight* : [ 1 ] [ 2 ] [ 3 ] [ 4 ] [ 5 ] \n5 sequential dice between 1 - 5 - Score : 15\n\n" +
                        "*High Straight* : [ 2 ] [ 3 ] [ 4 ] [ 5 ] [ 6 ] \n5 sequential dice between 2 - 6 - Score : 20\n\n" +
                        "*[6 DICES] Full Straight* : [ 1 ] [ 2 ] [ 3 ] [ 4 ] [ 5 ] [ 6 ] \n6 sequential dice between 1 - 6 - Score : 21\n\n" +
                        "*[6 DICES] 3+3* : [ 3 ] [ 3 ] [ 3 ] [ 6 ] [ 6 ] [ 6 ] \nSum of 3 of kind twice - Score : 27\n\n" +
                        "*[6 DICES] 4+2* : [ 4 ] [ 4 ] [ 4 ] [ 4 ] [ 5 ] [ 5 ] \nSum of 4 of kind and pairs - Score : 26\n\n" +
                        "*Full House* : [ 2 ] [ 2 ] [ 5 ] [ 5 ] [ 5 ] \n3 of one number and 2 of another - Score : 19\n\n" +
                        "*Chance* : [ 3 ] [ 6 ] [ 4 ] [ 5 ] [ 1 ] \nThe sum of all dice - Score : 19\n\n");

        List<String> YatzY = new ArrayList<String>();
        YatzY.add("A YatzY is a 5 of a Kind (all the die faces are the same) or 6 of a Kind for Arcade Game,\n" +
                   "It scores 50 points in Classic Game or 100 points in Arcade Game\n" +
                   "Yatzy roll, provided that you have already scored\n" +
                   "a 50 in the Yatzy category.\n" +
                   "If you have not scored in the Yatzy category,\n" +
                   "you will not receive a bonus.\n" +
                   "If you have scored a zero in the Yatzy category,\n" +
                   "you cannot receive any bonuses during the current game.\n" +
                   "You can also use subsequent Yatzy's as jokers in the lower scores section,\n" +
                   "provided the following criteria have been satisfied");

        List<String> Bonus = new ArrayList<String>();
        Bonus.add("With the left section, the goal is to reach 63 points with the sum of lower scores, or 84 points with the 6 dices.\n" +
                  "If you achieved 63 points or 84 points or more, you receive 50 points bonus in Classic Game, or 100 points in Arcade Game. ");
//
//        List<String> Scoring = new ArrayList<String>();
//        Scoring.add("" +

        listDataChild.put(listDataHeader.get(0), Objective); // Header, Child data
        listDataChild.put(listDataHeader.get(1), RollingTheDice);
        listDataChild.put(listDataHeader.get(2), Scoring);
        listDataChild.put(listDataHeader.get(3), LeftScores);
        listDataChild.put(listDataHeader.get(4), RightScores);
        listDataChild.put(listDataHeader.get(5), YatzY);
        listDataChild.put(listDataHeader.get(6), Bonus);
//        listDataChild.put(listDataHeader.get(7), RollingTheDice);
    }
}
