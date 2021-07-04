package com.tetblocks.tetb1;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Vibrator;
import android.util.Log;
import android.view.Gravity;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.Toast;


public class MainActivity extends AppCompatActivity {

    LinearLayout ln1, lln [],controller, space_between_game_board_and_controller;
    LinearLayout.LayoutParams parms, controller_parms;
    ImageView iv [] [],right_arrow, left_arrow,  turn_arrow, down_arrow, double_down_arrow ;
    LinearLayout.LayoutParams lp1;
    int direction=1, speed=1000, lvl=1;

    boolean timer_pause_start=true;

    blocks blcks = new blocks();




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        getSupportActionBar().hide();

        setContentView(R.layout.activity_main);

        blcks.horizontal_calibrate_blocks();



        switch (lvl)
        {
            case 1:
                speed=800;
                break;

            case 2:
                speed=500;
                break;

            case 3:
                speed=200;
                break;

        }

        ln1 = findViewById(R.id.ln1);

       lp1 = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT
        );

        ln1.setOrientation(LinearLayout.VERTICAL);


        // Butonların ve gameboardın yerleri aşağıdaki methodlar ile yapılmıştır

        game_board(70,70);
        space_between_game_board_and_controller(100);
        controller();
        space_between_game_board_and_controller(100);
        controller2();
        screen_refresh();



            right_arrow.setOnTouchListener(new View.OnTouchListener() {
                @SuppressLint("ClickableViewAccessibility")
                @Override
                public boolean onTouch(View v, MotionEvent event) {
                    switch(event.getAction()) {
                        case MotionEvent.ACTION_DOWN:

                            Vibrator vibe = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
                            vibe.vibrate(100);

                            right_arrow.setImageResource(R.drawable.red_right_arrow);
                            blcks.right_left_block_control(false);
                            screen_refresh();


                            return true;
                        case MotionEvent.ACTION_UP:

                            right_arrow.setImageResource(R.drawable.right_arrow);

                            return true;
                    }
                    return false;
                }
            });





        left_arrow.setOnTouchListener(new View.OnTouchListener() {
            @SuppressLint("ClickableViewAccessibility")
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                switch(event.getAction()) {
                    case MotionEvent.ACTION_DOWN:

                        Vibrator vibe = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
                        vibe.vibrate(100);

                        left_arrow.setImageResource(R.drawable.red_left_arrow);
                        blcks.right_left_block_control(true);
                        screen_refresh();
                        return true;
                    case MotionEvent.ACTION_UP:

                        left_arrow.setImageResource(R.drawable.left_arrow);

                        return true;
                }
                return false;
            }
        });



        turn_arrow.setOnTouchListener(new View.OnTouchListener() {
            @SuppressLint("ClickableViewAccessibility")
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                switch(event.getAction()) {
                    case MotionEvent.ACTION_DOWN:

                        Vibrator vibe = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
                        vibe.vibrate(100);

                        turn_arrow.setImageResource(R.drawable.red_repeat);


                        direction++;
                        if(direction>=blcks.max_direction) direction=1;
                        blcks.up_down_block_control();
                        blcks.direction_right_left_block_control();

                        screen_refresh();
                        return true;
                    case MotionEvent.ACTION_UP:

                        turn_arrow.setImageResource(R.drawable.repeat);

                        return true;
                }
                return false;
            }
        });




        down_arrow.setOnTouchListener(new View.OnTouchListener() {
            @SuppressLint("ClickableViewAccessibility")
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                switch(event.getAction()) {
                    case MotionEvent.ACTION_DOWN:

                        Vibrator vibe = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
                        vibe.vibrate(100);

                        down_arrow.setImageResource(R.drawable.red_down_arrow);

                    //    Toast.makeText(getApplicationContext(),blcks.width()+"",Toast.LENGTH_SHORT).show();
                        return true;
                    case MotionEvent.ACTION_UP:


                        down_arrow.setImageResource(R.drawable.down_arrow);

                        return true;
                }
                return false;
            }
        });




        double_down_arrow.setOnTouchListener(new View.OnTouchListener() {
            @SuppressLint("ClickableViewAccessibility")
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                switch(event.getAction()) {
                    case MotionEvent.ACTION_DOWN:

                        Vibrator vibe = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
                        vibe.vibrate(100);

                        double_down_arrow.setImageResource(R.drawable.red_doublearrow);
                        return true;
                    case MotionEvent.ACTION_UP:

                        double_down_arrow.setImageResource(R.drawable.doublearrow);

                        return true;
                }
                return false;
            }
        });



    timer1();
    }





 public void screen_refresh()
    {

        switch (direction)
        {
            case 1:
                blcks.block_type_d1();

            break;



               case 2:
                blcks.block_type_d2();

                break;


                case 3:
                    blcks.block_type_d3();


                break;


            case 4:
                blcks.block_type_d4();

                break;



        }




        for(int y=0;y<blcks.coords.length;y++)
        {

            for(int x=0;x<blcks.coords[0].length;x++)
            {

                if(blcks.coords[y][x]==true)  iv [x] [y].setImageResource(R.drawable.tet2);
                else  iv [x] [y].setImageResource(R.drawable.tet1);

            }

        }
    }


         public void msg_box(String msg)
            {

                AlertDialog alertDialog = new AlertDialog.Builder(MainActivity.this).create();
                alertDialog.setTitle("Alert");
                alertDialog.setMessage(msg);
                alertDialog.setButton(AlertDialog.BUTTON_NEUTRAL, "OK",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.dismiss();
                            }
                        });


                alertDialog.show();

            }





    public void timer1()
    {



        new Thread(){

            @Override
            public void run() {



                try
                {


                        while (timer_pause_start)
                    {
                        sleep(speed);



                        for(int y=0;y<blcks.coords.length;y++) {

                            for (int x = 0; x < blcks.coords[0].length; x++) {


                                if(blcks.coords[y][x]==false && y>=19)
                                {

                                    blcks.block_type++;
                                    direction=1;

                                    blcks.horizontal_calibrate_blocks();

                                  //  Toast.makeText(getApplicationContext(),"Hello Javatpoint",Toast.LENGTH_SHORT).show();


                                }

                            }

                        }




                        blcks.vertical++;

                      screen_refresh();


                    }

                }
                catch (Exception ed)
                {
                    System.out.println("Error code 10:"+ed);
                }
            }
        }.start();

    }




    public void game_board(int Width, int Height)
    {
        lln  = new LinearLayout[20];
        parms = new LinearLayout.LayoutParams(Width,Height);
        iv  = new ImageView[10] [20];



        for(int i=0;i<lln.length;i++)
        {

            lln [i] = new LinearLayout(this);
            ln1.addView(lln[i],lp1);

            lln [i].setOrientation(LinearLayout.HORIZONTAL);
            lln[i].setGravity(Gravity.CENTER);


            /* game boardı merkeze almak için lln[i].setGravity(Gravity.CENTER); kullanılıyor

            lp1 = new LinearLayout.LayoutParams(
                    LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT
            );

            olmalı

             lp1 = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT
        );

            değil.
             */


               for(int y=0;y<iv.length;y++)
            {
                iv [y] [i]= new ImageView(this);
                iv [y] [i].setImageResource(R.drawable.tet2);
                lln[i].addView(iv[y][i], lp1);
                iv[y][i].setLayoutParams(parms);

            }


        }




    }


    public void controller()
    {
        controller  = new LinearLayout(this);
        ln1.addView(controller,lp1);
        controller.setOrientation(LinearLayout.HORIZONTAL);
        controller.setGravity(Gravity.CENTER);

        left_arrow();
        between_arrows(200,200);
        turn_arrow();
        between_arrows(200,200);
        right_arrow();

    }

    public void controller2()
    {
        controller  = new LinearLayout(this);
        ln1.addView(controller,lp1);
        controller.setOrientation(LinearLayout.HORIZONTAL);
        controller.setGravity(Gravity.CENTER);

        down_arrow();
        between_arrows(200,200);
        double_down_arrow();



    }



    public void space_between_game_board_and_controller(int Height)
    {

                space_between_game_board_and_controller  = new LinearLayout(this);
        LinearLayout.LayoutParams space_between_game_board_and_controller_parms = new LinearLayout.LayoutParams(2500,Height);
        ln1.addView(space_between_game_board_and_controller,space_between_game_board_and_controller_parms);
        space_between_game_board_and_controller.setOrientation(LinearLayout.HORIZONTAL);
    }

    public void between_arrows(int Width,int Height)
    {

        LinearLayout between_arrows  = new LinearLayout(this);
        LinearLayout.LayoutParams between_arrows_parms = new LinearLayout.LayoutParams(Width,Height);
        controller.addView(between_arrows,between_arrows_parms);
        between_arrows.setOrientation(LinearLayout.HORIZONTAL);


    }


    public void left_arrow()
    {
        left_arrow= new ImageView(this);
        left_arrow.setImageResource(R.drawable.left_arrow);
        controller.addView(left_arrow, lp1);
        controller_parms = new LinearLayout.LayoutParams(200,200);
        left_arrow.setLayoutParams(controller_parms);
    }

    public void turn_arrow()
    {
        turn_arrow= new ImageView(this);
        turn_arrow.setImageResource(R.drawable.repeat);
        controller.addView(turn_arrow, lp1);
        controller_parms = new LinearLayout.LayoutParams(200,200);
        turn_arrow.setLayoutParams(controller_parms);
    }

    public void right_arrow()
    {
        right_arrow= new ImageView(this);
        right_arrow.setImageResource(R.drawable.right_arrow);
        controller.addView(right_arrow, lp1);
        controller_parms = new LinearLayout.LayoutParams(200,200);
        right_arrow.setLayoutParams(controller_parms);
    }



      public void down_arrow()
    {
        down_arrow= new ImageView(this);
        down_arrow.setImageResource(R.drawable.down_arrow);
        controller.addView(down_arrow, lp1);
        controller_parms = new LinearLayout.LayoutParams(200,200);
        down_arrow.setLayoutParams(controller_parms);
    }

    public void double_down_arrow()
    {
        double_down_arrow= new ImageView(this);
        double_down_arrow.setImageResource(R.drawable.doublearrow);
        controller.addView(double_down_arrow, lp1);
        controller_parms = new LinearLayout.LayoutParams(200,200);
        double_down_arrow.setLayoutParams(controller_parms);
    }





}

