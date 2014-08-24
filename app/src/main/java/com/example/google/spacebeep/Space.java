package com.example.google.spacebeep;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.support.wearable.view.WatchViewStub;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import java.util.*;

public class Space extends Activity {

    private TextView instructionsTV;
    private Button b1, b2, b3, b4;
    private ArrayList sequence = new ArrayList();
    private boolean user_playing = false;
    private boolean turn; //true == user, false == computer //validate user doesn't click while showing sequence
    private int click_counter = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_space);
        final WatchViewStub stub = (WatchViewStub) findViewById(R.id.watch_view_stub);
        stub.setOnLayoutInflatedListener(new WatchViewStub.OnLayoutInflatedListener() {
            @Override
            public void onLayoutInflated(WatchViewStub stub) {
                b1 = (Button) stub.findViewById(R.id.top_left);
                b2 = (Button) stub.findViewById(R.id.top_right);
                b3 = (Button) stub.findViewById(R.id.bottom_left);
                b4 = (Button) stub.findViewById(R.id.bottom_right);
                instructionsTV = (TextView) stub.findViewById(R.id.instructions);
            }
        });

    }


    private void gameStart(){
        instructionsTV.setText("");
        turn = false;
        //Delay 1 second so user can get the first sequence without missing anything
        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            public void run() {
                addRandom();
                recursion(0);
            }
        }, 1000);
        turn = true;
        click_counter = 0;
    }

    private void gameOver(){
        instructionsTV.setText("You lost! Tap to restart.");
        user_playing = false;
        sequence.clear();
        turn = false;
        click_counter = 0;
    }

    private void showSequence(){
        addRandom();
        recursion(0);
    }

    public void button1Clicked(View target) {

        if (!user_playing){
            user_playing = true;
            gameStart();
        }
        else{
            if(turn){
                if(Integer.parseInt(sequence.get(click_counter).toString())!=1){
                    gameOver();
                }else{
                    if(sequence.size()-1 == click_counter){
                        turn = false;
                        gameStart();
                    }
                    else{
                        click_counter++;
                    }
                }

            }
        }

    }
    public void button2Clicked(View target) {

        if (!user_playing){
            user_playing = true;
            gameStart();
        }
        else{
            if(turn){
                if(Integer.parseInt(sequence.get(click_counter).toString())!=2){
                    gameOver();
                }else{
                    if(sequence.size()-1 == click_counter){
                        turn = false;
                        gameStart();
                    }
                    else{
                        click_counter++;
                    }
                }

            }
        }

    }
    public void button3Clicked(View target) {

        if (!user_playing){
            user_playing = true;
            gameStart();
        }
        else{
            if(turn){
                if(Integer.parseInt(sequence.get(click_counter).toString())!=3){
                    gameOver();
                }else{
                    if(sequence.size()-1 == click_counter){
                        turn = false;
                        gameStart();
                    }
                    else{
                        click_counter++;
                    }
                }

            }
        }


    }
    public void button4Clicked(View target) {

        if (!user_playing){
            user_playing = true;
            gameStart();
        }
        else{
            if(turn){
                if(Integer.parseInt(sequence.get(click_counter).toString())!=4){
                    gameOver();
                }else{
                    if(sequence.size()-1 == click_counter){
                        turn = false;
                        gameStart();
                    }
                    else{
                        click_counter++;
                    }
                }

            }
        }

    }

    private void addRandom(){
        Random rand = new Random();
        sequence.add(rand.nextInt(4)+1);
        System.out.println("the next button is: "+sequence.get(sequence.size()-1));
    }

    private void recursion(int i){
        if(i < sequence.size()){
            int button_number = Integer.parseInt(sequence.get(i).toString());

            final int text = i;

            if(button_number == 1){
                b1.setText(Integer.toString(i));
            }else if(button_number == 2){
                b2.setText(Integer.toString(i));
            }else if(button_number == 3){
                b3.setText(Integer.toString(i));
            }else{
                b4.setText(Integer.toString(i));
            }

            Handler handler = new Handler();
            handler.postDelayed(new Runnable() {
                public void run() {
                    b1.setText("");
                    b2.setText("");
                    b3.setText("");
                    b4.setText("");
                    recursion(text + 1);
                }
            }, 800);
        }
    }
}
