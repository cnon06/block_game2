package com.tetblocks.tetb1;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.graphics.Typeface;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Vibrator;
import android.util.Log;
import android.view.Gravity;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Timer;
import java.util.TimerTask;

public class gameboard extends AppCompatActivity
{


    ActivityResultLauncher<Intent> activityResultLauncher;


    LinearLayout ln1,controller, space_between_game_board_and_controller, ln2;
    LinearLayout.LayoutParams parms, controller_parms;
    ImageView right_arrow, left_arrow,  direction_arrow, down_arrow, play_pause, ghost1, mute1, vib1;
    LinearLayout.LayoutParams lp1;
    gameboard1 gameb1;
    //start_game start_game1;
    String text2;
    final Object pauseLock = new Object();



    boolean go_right=false, go_left=false, timer_pause_start=false,  pause=false, pause_control=false, vib_true_false=false;

    boolean  play_pause2=false, game_over2=true;

    int  go_left_right_control=0, mute_count=1, go_to=0, best_score=0, vibration=50;


    float volume1=1f;

    MediaPlayer mp;

    Thread timer2;
    Timer timer3, timer4;

    TextView best_score2, best_score3;
    ImageView debris,start;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.gameboard);
        setRequestedOrientation (ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        getSupportActionBar().hide();



debris=(ImageView) findViewById(R.id.imageView3);
        debris.setImageResource(R.drawable.game_over);

        start=(ImageView) findViewById(R.id.imageView2);



         best_score2 = findViewById(R.id.Best_Score);
        best_score2.setText("BEST SCORE:");
        best_score2.setTextSize(28.5f);
        best_score2.setTypeface(null, Typeface.BOLD);
        best_score2.setGravity(Gravity.CENTER);
        // best_score.setVisibility(View.INVISIBLE);

        best_score3 = findViewById(R.id.Best_Score2);
        best_score3.setText("10020");
        best_score3.setTextSize(28.5f);
        best_score3.setTypeface(null, Typeface.BOLD);
        best_score3.setGravity(Gravity.CENTER);

    //   invisible_objects();
        start.setEnabled(false);








        activityResultLauncher = registerForActivityResult(

                new ActivityResultContracts.StartActivityForResult(),
                new ActivityResultCallback<ActivityResult>() {
                    @Override
                    public void onActivityResult(ActivityResult activityResult) {



                    }
                }
        );



        Intent myIntent = getIntent();
      System.out.println("MESAJ ALINDI: "+myIntent.getStringExtra("ss1"));


        //activityResultLauncher.launch(myIntent);



        Timer3();

        mp = MediaPlayer.create(this,R.raw.musics);
        mp.start();
        mp.setVolume(volume1,volume1);
        completetion_listener();


        try {
            ln1 = findViewById(R.id.ln1);
            ln2 = findViewById(R.id.ln2);

            lp1 = new LinearLayout.LayoutParams(
                    LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT
            );

            ln1.setOrientation(LinearLayout.VERTICAL);

            gameb1 = new gameboard1(this);
            //start_game1 = new start_game(this);

            game_console();
        }
        catch (Exception e)
        {
            System.out.println(e);
        }


       // write_score_AsFile(100);

        read_file();



        //gameb1.random_blocks();


    }


    public  boolean Timer1()
    {
       // runOnUiThread(new Runnable()




      //  runOnUiThread(new Runnable()

         new Thread("Gameb-timer1")
        {
            @Override
            public void run() {

                try {
                    while (!gameb1.thread1) {

                        synchronized (gameb1.pauseLock)
                        {

                            if(gameb1.pause)
                            {
                                gameb1.pauseLock.wait();
                                gameb1.pause=false;
                            }

                             runOnUiThread(new Runnable() {
                                @Override
                                public void run() {


                                    try {



                                        if (gameb1.block_type_2 == 0) {
                                            gameb1.random_blocks();
                                        }

                                        if (gameb1.fast_press) {

                                            gameb1.count_press++;
                                            if (gameb1.count_press >= 3) {
                                                gameb1.speed = 30;
                                                gameb1.fast_press = false;
                                                gameb1.count_press = 0;
                                            } else {
                                                gameb1.speed = 100;
                                            }

                                            gameb1.lvl_up = 0;
                                        } else gameb1.lvl_up = gameb1.lvl * 100;

                                        gameb1.restart_blocks();
                                        if (gameb1.bottom_control() && gameb1.vertical > 15)
                                            gameb1.first_horizontal_position();

                                        gameb1.vertical++;

                                        gameb1.direction();

                                        gameb1.ghost_vertical();

                                        gameb1.invalidate();



                                    }
                                    catch (Exception ert)
                                    {
                                        Log.d("Error 5644rte: ",ert.toString());
                                    }




                                }
                            });

                            Thread.sleep(gameb1.speed);

                        }

                    }
                }
                catch (Exception tyu)
                {
                    System.out.println("Code 56-er: "+tyu);
                }


            }
        }.start();





        return false;
    }

    public void game_console()
    {
        try {

            //setContentView(ln1);


            game_board(70,70);
            space_between_game_board_and_controller(50);
            controller();
            space_between_game_board_and_controller(10);
            controller2();


            if (!mp.isLooping()) {
                mp.start();
                mp.setVolume(volume1,volume1);
                completetion_listener();
            }

            timers();

            buttons();



        }
        catch (Exception rty)
        {}

    }



    public void timers()
    {
        Timer1();
        //gameb1.Timer1();
       timer2();

    }




    public void buttons()
    {



        start.setOnTouchListener(new View.OnTouchListener() {
            @SuppressLint("ClickableViewAccessibility")
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                switch (event.getAction()) {
                    case MotionEvent.ACTION_DOWN:

                        Vibrator vibe = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
                        vibe.vibrate(20);

                        start.setImageResource(R.drawable.start1);

                        return true;
                    case MotionEvent.ACTION_UP:

                        start.setImageResource(R.drawable.start2);


                      //  Intent myIntent = new Intent(gameboard.this, MainActivity.class);
                      //  myIntent.putExtra("ss1","Message from gameboard");
                      //  myIntent.putExtra("ss2",false);
                      //  setResult(78, myIntent);

                      //  activityResultLauncher.launch(myIntent);
                        activityResultLauncher.launch(getIntent());





                        return true;
                }
                return false;
            }
        });



        vib1.setOnTouchListener(new View.OnTouchListener() {
            @SuppressLint("ClickableViewAccessibility")
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                switch (event.getAction()) {
                    case MotionEvent.ACTION_DOWN:



                        /*
                            Intent myIntent = new Intent(v.getContext(), gameboard.class);

                        activityResultLauncher.launch(myIntent);
                         */



                        if (!play_pause2) {

                            if (!vib_true_false) {
                                Vibrator vibe = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
                                vibe.vibrate(vibration);
                            }


                            if (vib_true_false) {


                                vib1.setImageResource(R.drawable.vib1);
                                vib_true_false = false;

                            } else {
                                vib1.setImageResource(R.drawable.vib2);
                                vib_true_false = true;


                            }

                        }


                        return true;
                    case MotionEvent.ACTION_UP:


                        return true;
                }
                return false;
            }
        });


        mute1.setOnTouchListener(new View.OnTouchListener() {
            @SuppressLint("ClickableViewAccessibility")
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                switch (event.getAction()) {
                    case MotionEvent.ACTION_DOWN:


                        if (!play_pause2) {


                            if (!vib_true_false) {
                                Vibrator vibe = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
                                vibe.vibrate(vibration);
                            }


                            mute_count++;
                            if (mute_count > 3) mute_count = 1;


                            switch (mute_count) {

                                case 1:
                                    volume1 = 1f;
                                    mp.setVolume(volume1, volume1);


                                    gameb1.volume1 = 1f;
                                    gameb1.volume2 = 1f;
                                    gameb1.mp.setVolume(gameb1.volume1, gameb1.volume1);
                                    gameb1.mp2.setVolume(gameb1.volume2, gameb1.volume2);
                                    gameb1.mp3.setVolume(gameb1.volume2, gameb1.volume2);

                                    mute1.setImageResource(R.drawable.mute3);

                                    break;


                                case 2:

                                    volume1 = 0;
                                    mp.setVolume(volume1, volume1);


                                    gameb1.volume1 = 1f;
                                    gameb1.volume2 = 1f;
                                    gameb1.mp.setVolume(gameb1.volume1, gameb1.volume1);
                                    gameb1.mp2.setVolume(gameb1.volume2, gameb1.volume2);
                                    gameb1.mp3.setVolume(gameb1.volume2, gameb1.volume2);


                                    // mp.setVolume(0,0);
                                    mute1.setImageResource(R.drawable.mute2);

                                    break;

                                case 3:

                                    volume1 = 0;
                                    mp.setVolume(volume1, volume1);

                                    gameb1.volume1 = 0;
                                    gameb1.volume2 = 0;
                                    gameb1.mp.setVolume(gameb1.volume1, gameb1.volume1);
                                    gameb1.mp2.setVolume(gameb1.volume2, gameb1.volume2);
                                    gameb1.mp3.setVolume(gameb1.volume2, gameb1.volume2);

                                    //   mp.setVolume(0,0);
                                    mute1.setImageResource(R.drawable.mute1);
                                    break;


                                default:

                            }


                        }


                        return true;
                    case MotionEvent.ACTION_UP:


                        return true;
                }
                return false;
            }
        });


        ghost1.setOnTouchListener(new View.OnTouchListener() {
            @SuppressLint("ClickableViewAccessibility")
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                switch (event.getAction()) {
                    case MotionEvent.ACTION_DOWN:


                        if (!play_pause2) {


                            if (!vib_true_false) {
                                Vibrator vibe = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
                                vibe.vibrate(vibration);
                            }


                            if (gameb1.ghost_true_false) {
                                ghost1.setImageResource(R.drawable.ghost1);
                                gameb1.ghost_true_false = false;
                            } else {
                                ghost1.setImageResource(R.drawable.ghost2);
                                gameb1.ghost_true_false = true;
                            }


                        }


                        return true;
                    case MotionEvent.ACTION_UP:


                        return true;
                }
                return false;
            }
        });


        play_pause.setOnTouchListener(new View.OnTouchListener() {
            @SuppressLint("ClickableViewAccessibility")
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                switch (event.getAction()) {
                    case MotionEvent.ACTION_DOWN:


                        if (!vib_true_false) {
                            Vibrator vibe = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
                            vibe.vibrate(vibration);
                        }


                        if (play_pause2) {
                            play();
                        } else {
                            pause();
                        }

                        return true;
                    case MotionEvent.ACTION_UP:


                        return true;
                }
                return false;
            }
        });


        right_arrow.setOnTouchListener(new View.OnTouchListener() {
            @SuppressLint("ClickableViewAccessibility")
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                switch (event.getAction()) {
                    case MotionEvent.ACTION_DOWN:


                        if (!play_pause2) {

                            if (!vib_true_false) {
                                Vibrator vibe = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
                                vibe.vibrate(vibration);
                            }




                            right_arrow.setImageResource(R.drawable.red_right_arrow);
                            go_right = true;

                            try {

                                gameb1.right_control();
                                gameb1.direction();
                                gameb1.invalidate();



                                gameb1.ghost_vertical();


                            } catch (Exception e) {
                                System.out.println("Code-45r3e: " + e);
                            }
                        }


                        return true;
                    case MotionEvent.ACTION_UP:

                        if (!play_pause2) {
                            right_arrow.setImageResource(R.drawable.right_arrow);
                            go_right = false;
                            go_left_right_control = 0;
                            gameb1.fast_press = false;
                          // timer5.cancel();

                        }
                        return true;
                }
                return false;
            }
        });


        left_arrow.setOnTouchListener(new View.OnTouchListener() {
            @SuppressLint("ClickableViewAccessibility")
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                switch (event.getAction()) {
                    case MotionEvent.ACTION_DOWN:


                        if (!play_pause2) {
                            if (!vib_true_false) {
                                Vibrator vibe = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
                                vibe.vibrate(vibration);
                            }
                            left_arrow.setImageResource(R.drawable.red_left_arrow);

                            try {
                                gameb1.left_control();
                                gameb1.direction();
                                gameb1.invalidate();
                                gameb1.ghost_vertical();
                            } catch (Exception e) {
                                System.out.println("Code-423d: " + e);
                            }
                            go_left = true;
                        }

                        return true;
                    case MotionEvent.ACTION_UP:


                        if (!play_pause2) {
                            left_arrow.setImageResource(R.drawable.left_arrow);
                            go_left = false;
                            go_left_right_control = 0;

                        }

                        return true;
                }
                return false;
            }
        });


        direction_arrow.setOnTouchListener(new View.OnTouchListener() {
            @SuppressLint("ClickableViewAccessibility")
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                switch (event.getAction()) {
                    case MotionEvent.ACTION_DOWN:

                        if (!play_pause2) {

                            if (!vib_true_false) {
                                Vibrator vibe = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
                                vibe.vibrate(vibration);
                            }


                            direction_arrow.setImageResource(R.drawable.red_repeat);

                            try {

                                gameb1.direction++;
                                if (gameb1.direction > gameb1.max_direction)
                                    gameb1.direction = 1;
                                gameb1.direction();


                                if (!gameb1.direction_up_down_control())

                                    if (!gameb1.direction_left_right_border_control())
                                        gameb1.direction_left_right_control2();


                                gameb1.invalidate();
                                gameb1.ghost_vertical();

                            } catch (Exception e) {
                                System.out.println("Code-9: " + e);
                            }
                        }

                        return true;
                    case MotionEvent.ACTION_UP:


                        if (!play_pause2) {
                            direction_arrow.setImageResource(R.drawable.repeat);
                        }


                        return true;
                }
                return false;
            }
        });


        down_arrow.setOnTouchListener(new View.OnTouchListener() {
            @SuppressLint("ClickableViewAccessibility")
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                switch (event.getAction()) {
                    case MotionEvent.ACTION_DOWN:

                        if (!play_pause2) {


                            if (!vib_true_false) {
                                Vibrator vibe = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
                                vibe.vibrate(vibration);
                            }


                            down_arrow.setImageResource(R.drawable.red_down_arrow);

                            gameb1.speed2 = gameb1.speed;

                            gameb1.speed = 100;

                            gameb1.fast_press = true;

                        }

                        return true;
                    case MotionEvent.ACTION_UP:

                        if (!play_pause2) {

                            down_arrow.setImageResource(R.drawable.down_arrow);
                            gameb1.speed = gameb1.speed2;

                            gameb1.fast_press = false;
                        }
                        return true;
                }
                return false;
            }
        });

    }
    
    
    public synchronized void onPause() {
        super.onPause();
        /*
         try {
               mp.stop();
              mp.release();
             // write_score_AsFile(55);
        }
        catch (Exception rty)
        {

        }
         */


    // finish();


        try {
            mp.pause();
        }
        catch (Exception ertgd)
        {
            Log.d("Code 345f2: ",ertgd+"");
        }

            //completetion_listener();


           pause();
            //pause();






    }


    public  void play2()
    {


        synchronized (pauseLock)
        {
            pauseLock.notifyAll();
        }
    }

    public void timer2()
    {



          new Thread() {


            public void run() {
                try {

                    while (!timer_pause_start) {


                        synchronized (pauseLock) {


                            if (pause) {
                                pauseLock.wait();
                                pause = false;
                            }



                            runOnUiThread(new Runnable() {
                                @Override
                                public void run() {

                                    try {
                                        if (go_left || go_right) {

                                            go_left_right_control++;

                                            if (go_left_right_control >= 2) {
                                                if (go_left) {

                                                    gameb1.left_control();
                                                    gameb1.direction();
                                                    gameb1.ghost_vertical();
                                                    gameb1.invalidate();


                                                }

                                                if (go_right) {

                                                    gameb1.right_control();
                                                    gameb1.direction();
                                                    gameb1.ghost_vertical();
                                                    gameb1.invalidate();


                                                }
                                            }

                                        }


                                    }
                                    catch (Exception hjr)
                                    {
                                        Log.d("Error 674ty: ",hjr.toString());
                                    }


                                }
                            });




                            sleep(100);


                        }
                    }
                }


                catch(Exception ed)
                {
                    System.out.println(ed);
                }

            }
        }.start();



     //   timer2.start();

    }

    public void game_board(int Width, int Height)
    {


        LinearLayout gb2 = new LinearLayout(this);


        LinearLayout.LayoutParams between_arrows_parms123 = new LinearLayout.LayoutParams(1000,100);
         ln1.addView(gb2,between_arrows_parms123 );

        gb2.setOrientation(LinearLayout.HORIZONTAL);
        gb2.setGravity(Gravity.CENTER);

        LinearLayout.LayoutParams between_arrows_parms1234 = new LinearLayout.LayoutParams(1000,100);
        gb2.addView(gameb1.score_board,between_arrows_parms1234 );
        // you will write the code of scoreboard here in gb2

        LinearLayout gb1 = new LinearLayout(this);

         ln1.addView(gb1,lp1);
        gb1.setOrientation(LinearLayout.HORIZONTAL);
        gb1.setGravity(Gravity.CENTER);

        LinearLayout between_arrows  = new LinearLayout(this);
        LinearLayout.LayoutParams between_arrows_parms = new LinearLayout.LayoutParams(150,150);
        gb1.addView(between_arrows,between_arrows_parms);
        between_arrows.setOrientation(LinearLayout.HORIZONTAL);

        controller_parms = new LinearLayout.LayoutParams(715,1420);
        gameb1.setLayoutParams(controller_parms);

        ln1.setGravity(Gravity.CENTER);

        gb1.addView(gameb1);

        LinearLayout between_arrows3  = new LinearLayout(this);
        LinearLayout.LayoutParams between_arrows_parms3 = new LinearLayout.LayoutParams(150,1400);
        gb1.addView(between_arrows3,between_arrows_parms3);
        between_arrows3.setOrientation(LinearLayout.VERTICAL);

        LinearLayout between_arrows4  = new LinearLayout(this);
        LinearLayout.LayoutParams between_arrows_parms4 = new LinearLayout.LayoutParams(150,600);
        between_arrows3.addView(between_arrows4,between_arrows_parms4);
        between_arrows4.setOrientation(LinearLayout.VERTICAL);
        between_arrows4.setGravity(Gravity.CENTER);

        TextView lvl= new TextView(this);
        lvl.setText("LVL:");
        lvl.setTextSize(20.5f);
        lvl.setTypeface(null, Typeface.BOLD);
        between_arrows4.addView(lvl);

        controller_parms = new LinearLayout.LayoutParams(150,70);
        lvl.setLayoutParams(controller_parms);
        lvl.setGravity(Gravity.CENTER);

       /*
        TextView lvl2= new TextView(this);
        lvl2.setText("0");
        lvl2.setTextSize(20.5f);
        lvl2.setTypeface(null, Typeface.BOLD);

         controller_parms = new LinearLayout.LayoutParams(150,70);
        lvl2.setLayoutParams(controller_parms);


        */




        between_arrows4.addView(gameb1.lvl2);

        LinearLayout between_arrows11  = new LinearLayout(this);
        LinearLayout.LayoutParams between_arrows_parms11 = new LinearLayout.LayoutParams(150,30);
        between_arrows4.addView(between_arrows11,between_arrows_parms11);
        between_arrows11.setOrientation(LinearLayout.HORIZONTAL);



        TextView blocks7= new TextView(this);
        blocks7.setText("Next");
        blocks7.setTextSize(20f);
        blocks7.setTypeface(null, Typeface.BOLD);
        between_arrows4.addView(blocks7);

        controller_parms = new LinearLayout.LayoutParams(150,70);
        blocks7.setLayoutParams(controller_parms);
        blocks7.setGravity(Gravity.CENTER);

        gameb1.blocks2= new ImageView(this);
        gameb1.blocks2.setImageResource(R.drawable.block_bar);

        between_arrows4.addView(gameb1.blocks2);
        controller_parms = new LinearLayout.LayoutParams(300,300);
        gameb1.blocks2.setLayoutParams(controller_parms);
        gameb1.blocks2.setRotation(90);

        LinearLayout between_arrows12  = new LinearLayout(this);
        LinearLayout.LayoutParams between_arrows_parms12 = new LinearLayout.LayoutParams(150,30);
        between_arrows4.addView(between_arrows12,between_arrows_parms12);
        between_arrows12.setOrientation(LinearLayout.HORIZONTAL);


        LinearLayout between_arrows2  = new LinearLayout(this);
        LinearLayout.LayoutParams between_arrows_parms2 = new LinearLayout.LayoutParams(150,700);
        between_arrows3.addView(between_arrows2,between_arrows_parms2);
        between_arrows2.setOrientation(LinearLayout.VERTICAL);


        play_pause= new ImageView(this);
        play_pause.setImageResource(R.drawable.pause2);
        between_arrows2.addView(play_pause);
        controller_parms = new LinearLayout.LayoutParams(150,150);
        play_pause.setLayoutParams(controller_parms);


        LinearLayout between_arrows5  = new LinearLayout(this);
        LinearLayout.LayoutParams between_arrows_parms5 = new LinearLayout.LayoutParams(150,30);
        between_arrows2.addView(between_arrows5,between_arrows_parms5);
        between_arrows5.setOrientation(LinearLayout.HORIZONTAL);


        ghost1= new ImageView(this);
        ghost1.setImageResource(R.drawable.ghost1);
        between_arrows2.addView(ghost1);
        controller_parms = new LinearLayout.LayoutParams(150,150);
        ghost1.setLayoutParams(controller_parms);



        LinearLayout between_arrows6  = new LinearLayout(this);
        LinearLayout.LayoutParams between_arrows_parms6 = new LinearLayout.LayoutParams(150,30);
        between_arrows2.addView(between_arrows6,between_arrows_parms5);
        between_arrows5.setOrientation(LinearLayout.HORIZONTAL);


        mute1= new ImageView(this);
        mute1.setImageResource(R.drawable.mute3);
        between_arrows2.addView(mute1);
        controller_parms = new LinearLayout.LayoutParams(150,150);
        mute1.setLayoutParams(controller_parms);



        LinearLayout between_arrows7  = new LinearLayout(this);
        LinearLayout.LayoutParams between_arrows_parms7 = new LinearLayout.LayoutParams(150,30);
        between_arrows2.addView(between_arrows7,between_arrows_parms5);
        between_arrows5.setOrientation(LinearLayout.HORIZONTAL);


        vib1= new ImageView(this);
        vib1.setImageResource(R.drawable.vib1);
        between_arrows2.addView(vib1);
        controller_parms = new LinearLayout.LayoutParams(150,150);
        vib1.setLayoutParams(controller_parms);


    }



    public void completetion_listener()
    {

        mp.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            public void onCompletion(MediaPlayer mp) {

                if (!mp.isLooping()) {
                    mp.start();
                    mp.setVolume(volume1,volume1);
                }

                completetion_listener();
                // finish(); // finish current activity
            }
        });

    }



    public void controller() throws Exception
    {
        controller  = new LinearLayout(this);
        ln1.addView(controller,lp1);
        controller.setOrientation(LinearLayout.HORIZONTAL);
        controller.setGravity(Gravity.CENTER);

        left_arrow();
        between_arrows(75,100);
        right_arrow();
        between_arrows(300,100);
        direction_arrow();


    }

    public void controller2() throws Exception
    {
        controller  = new LinearLayout(this);
        ln1.addView(controller,lp1);
        controller.setOrientation(LinearLayout.HORIZONTAL);
        controller.setGravity(Gravity.CENTER);

        down_arrow();
        between_arrows(300,100);
        double_down_arrow();

    }



    public void space_between_game_board_and_controller(int Height) throws Exception
    {

        space_between_game_board_and_controller  = new LinearLayout(this);
        LinearLayout.LayoutParams space_between_game_board_and_controller_parms = new LinearLayout.LayoutParams(2500,Height);
        ln1.addView(space_between_game_board_and_controller,space_between_game_board_and_controller_parms);
        space_between_game_board_and_controller.setOrientation(LinearLayout.HORIZONTAL);
    }

    public void between_arrows(int Width,int Height) throws Exception
    {

        LinearLayout between_arrows  = new LinearLayout(this);
        LinearLayout.LayoutParams between_arrows_parms = new LinearLayout.LayoutParams(Width,Height);
        controller.addView(between_arrows,between_arrows_parms);
        between_arrows.setOrientation(LinearLayout.HORIZONTAL);


    }


    public void left_arrow() throws Exception
    {
        left_arrow= new ImageView(this);
        left_arrow.setImageResource(R.drawable.left_arrow);
        controller.addView(left_arrow, lp1);
        controller_parms = new LinearLayout.LayoutParams(150,150);
        left_arrow.setLayoutParams(controller_parms);
    }

    public void direction_arrow() throws Exception
    {
        direction_arrow= new ImageView(this);
        direction_arrow.setImageResource(R.drawable.repeat);
        controller.addView(direction_arrow, lp1);
        controller_parms = new LinearLayout.LayoutParams(150,150);
        direction_arrow.setLayoutParams(controller_parms);
    }

    public void right_arrow() throws Exception
    {
        right_arrow= new ImageView(this);
        right_arrow.setImageResource(R.drawable.right_arrow);
        controller.addView(right_arrow, lp1);
        controller_parms = new LinearLayout.LayoutParams(150,150);
        right_arrow.setLayoutParams(controller_parms);
    }



    public void down_arrow() throws Exception
    {

        down_arrow= new ImageView(this);
        down_arrow.setImageResource(R.drawable.down_arrow);
        controller.addView(down_arrow, lp1);
        controller_parms = new LinearLayout.LayoutParams(150,150);
        down_arrow.setLayoutParams(controller_parms);
    }

    public void double_down_arrow() throws Exception
    {

        LinearLayout between_arrows  = new LinearLayout(this);
        LinearLayout.LayoutParams between_arrows_parms = new LinearLayout.LayoutParams(150,100);
        controller.addView(between_arrows,between_arrows_parms);
        between_arrows.setOrientation(LinearLayout.HORIZONTAL);

    }


    public void play()
    {
      //  is_on_pause=false;

        play_pause.setImageResource(R.drawable.pause2);


        play_pause2=false;

        //  gameb1.play_pause=false;

        /*
        mp = MediaPlayer.create(this,R.raw.musics);
        //mp.start();
        mp.seekTo(go_to);
        mp.start();
        mp.setVolume(volume1,volume1);

         */
        mp.start();
        completetion_listener();


          if(!mp.isLooping())

        {
            mp.start();
            mp.setVolume(volume1,volume1);

        }



       // timer2();
        gameb1.play();
        play2();
      Timer3();


    }

    public void pause()
    {


        play_pause.setImageResource(R.drawable.play2);

try {
    mp.pause();
}
catch (Exception ttr)
{
    Log.d("Code rty654: ", ttr.toString());
}

      //  mp.start();



        /*
          try {
            if(mp !=null)
            {
                mp.stop();
                go_to = mp.getCurrentPosition();

                mp.release();
            }

        }
        catch (Exception ert)
        {
            System.out.println("code 563rf: "+ert);
        }
         */




       // timer_pause_start=true;

        gameb1.pause=true;
        pause=true;
        play_pause2=true;
        pause_control=true;
       timer3.cancel();


    }



    public void read_file() {

        String FILE_NAME = "high_score.txt";


        FileInputStream fis = null;
        try {
            fis = openFileInput(FILE_NAME);
            InputStreamReader isr = new InputStreamReader(fis);
            BufferedReader br = new BufferedReader(isr);
            StringBuilder sb = new StringBuilder();
            String text;
            while ((text = br.readLine()) != null) {
                sb.append(text).append("\n");
            }



            text2 =sb.toString();
            best_score=(int)Integer.parseInt(text2.trim());

           // System.out.println("File was read: "+gameb1.best_score);
//            gameb1.best_score = best_score;

            //sb;
            //  msg_box(sb.toString());

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (fis != null) {
                try {
                    fis.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public  void  write_score_AsFile(int score) {


        String FILE_NAME = "high_score.txt";

        String text = score+"";
        FileOutputStream fos = null;
        try {
            fos = openFileOutput(FILE_NAME, MODE_PRIVATE);
            fos.write(text.getBytes());

            System.out.println("Saved to " + getFilesDir() + "/" + FILE_NAME);

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (fos != null) {
                try {
                    fos.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

    }


  public  boolean Timer3()
    {

        timer3 = new Timer();
        TimerTask task3 = new TimerTask() {

            public void run() {

                try {




                //    System.out.println(best_score+"");




                        if(!mp.isPlaying())
                    {
                        mp.start();
                        mp.setVolume(volume1,volume1);
                        completetion_listener();
                    }




                   // Log.d("tttt","vvvv");



                      if(gameb1.game_over() )
                    {
                        Log.d("GAME OVER","GAME OVER 2 ");


                        ln1.setVisibility(View.INVISIBLE);
                        ln2.setVisibility(View.INVISIBLE);


                       // visible_objects();
                      //  invisible_objects();



                        if(best_score<gameb1.score2)  write_score_AsFile(gameb1.score2);


                        mp.stop();

                        gameb1.thread1=true;
                        timer_pause_start=true;
                        start.setEnabled(true);
                       cancel();
                        }
                }
                catch (Exception e)
                {
                    Log.d("Error 754ggh: ",e.toString());
                }

            }

        };

        timer3.schedule(task3, 0,100);

        return false;
    }


}
