package com.tetblocks.tetb1;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Color;
import android.graphics.Paint;
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
    ImageView iv [] [],right_arrow, left_arrow,  direction_arrow, down_arrow, double_down_arrow ;
    LinearLayout.LayoutParams lp1;
    int speed=1000, lvl=1;
    boolean go_go_go=false;

    boolean timer_pause_start=true;

    blocks blcks = new blocks();
  //  ghost1 ghst1 = new ghost1();




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        getSupportActionBar().hide();

        setContentView(R.layout.activity_main);

        // blcks.bar_d1();

        try {
            blcks.calibrate_record_blocks();
            blcks.max_direction_list();
            blcks.setMax_direction();
            blcks.first_horizontal_position_list();
        }
        catch (Exception e)
        {

        }





        try {
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

        }
        catch (Exception e)
        {
            System.out.println(e);
        }








            right_arrow.setOnTouchListener(new View.OnTouchListener() {
                @SuppressLint("ClickableViewAccessibility")
                @Override
                public boolean onTouch(View v, MotionEvent event)  {
                    switch(event.getAction()) {
                        case MotionEvent.ACTION_DOWN:

                            Vibrator vibe = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
                            vibe.vibrate(100);


                            right_arrow.setImageResource(R.drawable.red_right_arrow);

                            try {


                                if(blcks.vertical==-1) blcks.vertical=0;
                             blcks.ghost_vertical=blcks.vertical;



                                    if(blcks.dont_go_right())
                                    {


                                        if(blcks.dont_go_right2())
                                        {

                                            blcks.horizontal++;
                                            blcks.ghost_horizontal=blcks.horizontal;
                                         blcks.direction2();



                                            screen_refresh();


                                        }




                                    }


                            }
                            catch (Exception e)
                            {

                            }

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


                        try {
                            if(blcks.vertical==-1) blcks.vertical=0;
                          blcks.ghost_vertical=blcks.vertical;
                            if(blcks.dont_go_left())
                            {


                                if(blcks.dont_go_left2())
                                {

                                    blcks.horizontal--;
                                 blcks.ghost_horizontal=blcks.horizontal;

                                  blcks.direction2();




                                    screen_refresh();


                                }

                            }

                        }
                        catch (Exception e)
                        {

                        }

                        return true;
                    case MotionEvent.ACTION_UP:

                        left_arrow.setImageResource(R.drawable.left_arrow);

                        return true;
                }
                return false;
            }
        });



        direction_arrow.setOnTouchListener(new View.OnTouchListener() {
            @SuppressLint("ClickableViewAccessibility")
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                switch(event.getAction()) {
                    case MotionEvent.ACTION_DOWN:

                        Vibrator vibe = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
                        vibe.vibrate(100);

                        direction_arrow.setImageResource(R.drawable.red_repeat);



                        try {
                             blcks.ghost_max_min2x();
                        }
                        catch (Exception e)
                        {
                            System.out.println(e);
                        }


                        try {
                            blcks.ghost_max_min2x2();
                        }
                        catch (Exception e)
                        {
                            System.out.println(e);
                        }


                          try {
                          blcks.ghost_max_min2x3();
                        }
                        catch (Exception e)
                        {
                            System.out.println(e);
                        }



                        try {


                         if(!blcks.ghost2() && !blcks.ghost6 )blcks.direction++;

                            if(blcks.direction>blcks.max_direction) blcks.direction=1;


                          //  go_go_go=true;

                            blcks.direction2();

                            screen_refresh();


                        }
                        catch (Exception e)
                        {

                        }


                        /*
                         try {
                            blcks.max_min2();
                        }
                        catch (Exception e)
                        {

                        }

                        try {
                            screen_refresh();
                        }
                        catch (Exception e)
                        {
                            System.out.println(e);
                        }
                         */



                        return true;
                    case MotionEvent.ACTION_UP:

                        direction_arrow.setImageResource(R.drawable.repeat);

                            try {
                                blcks.direction2();
                                screen_refresh();
                            }
                            catch (Exception e)
                            {

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
                switch(event.getAction()) {
                    case MotionEvent.ACTION_DOWN:

                        Vibrator vibe = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
                        vibe.vibrate(100);

                        down_arrow.setImageResource(R.drawable.red_down_arrow);


                      //  blcks.max_direction_list();

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
    //    timer2();
    }





 public void screen_refresh() throws Exception
    {

  blcks.merge_blocks();
    //ghst1.direction();
        //ghst1.bar_d2();



        try {

            for(int y=0;y<blcks.merge_blocks.length;y++)
            {

                for(int x=0;x<blcks.merge_blocks[0].length;x++)
                {



                    if(blcks.merge_blocks[y][x]==false )  iv [x] [y].setImageResource(R.drawable.tet7);
               else   if(blcks.ghost1[y][x]==false)  iv [x] [y].setImageResource(R.drawable.tet1);
                    else  iv [x] [y].setImageResource(R.drawable.tet6);


                    /*
                      if(blcks.merge_blocks[y][x]==true )  iv [x] [y].setImageResource(R.drawable.tet6);



                 if(ghst1.ghost_blocks1[y][x]==false)  iv [x] [y].setImageResource(R.drawable.tet1);

                    else  iv [x] [y].setImageResource(R.drawable.tet7);
                     */




                }

            }


        }
        catch (Exception e)
        {
            System.out.println(e);
        }





    }





    public void timer1()
    {




        Thread thread = new Thread(){
            public void run(){
              //  System.out.println("Thread Running");

                try
                {

                    while (timer_pause_start)
                    {




                        blcks.vertical++;
                        blcks.ghost_vertical = blcks.vertical;
                       blcks.direction2();


                        screen_refresh();
                        sleep(800);

                        //screen_refresh();
                        blcks.restart_blocks();

                        if(!blcks.dont_go_down()) blcks.first_horizontal_position();

                        screen_refresh();


                    }

                }
                catch (Exception ed)
                {
                    System.out.println("Error code 10:"+ed);
                }

            }
        };

        thread.start();


    }




    public void game_board(int Width, int Height) throws Exception
    {

        try {


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
                     iv [y] [i].setImageResource(R.drawable.tet6);
                    lln[i].addView(iv[y][i], lp1);
                    iv[y][i].setLayoutParams(parms);

                }


            }


        }
        catch (Exception e)
        {
            System.out.println(e);
        }





    }


    public void controller() throws Exception
    {
        controller  = new LinearLayout(this);
        ln1.addView(controller,lp1);
        controller.setOrientation(LinearLayout.HORIZONTAL);
        controller.setGravity(Gravity.CENTER);

        left_arrow();
        between_arrows(200,200);
        direction_arrow();
        between_arrows(200,200);
        right_arrow();

    }

    public void controller2() throws Exception
    {
        controller  = new LinearLayout(this);
        ln1.addView(controller,lp1);
        controller.setOrientation(LinearLayout.HORIZONTAL);
        controller.setGravity(Gravity.CENTER);

        down_arrow();
        between_arrows(200,200);
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
        controller_parms = new LinearLayout.LayoutParams(200,200);
        left_arrow.setLayoutParams(controller_parms);
    }

    public void direction_arrow() throws Exception
    {
        direction_arrow= new ImageView(this);
        direction_arrow.setImageResource(R.drawable.repeat);
        controller.addView(direction_arrow, lp1);
        controller_parms = new LinearLayout.LayoutParams(200,200);
        direction_arrow.setLayoutParams(controller_parms);
    }

    public void right_arrow() throws Exception
    {
        right_arrow= new ImageView(this);
        right_arrow.setImageResource(R.drawable.right_arrow);
        controller.addView(right_arrow, lp1);
        controller_parms = new LinearLayout.LayoutParams(200,200);
        right_arrow.setLayoutParams(controller_parms);
    }



      public void down_arrow() throws Exception
    {
        down_arrow= new ImageView(this);
        down_arrow.setImageResource(R.drawable.down_arrow);
        controller.addView(down_arrow, lp1);
        controller_parms = new LinearLayout.LayoutParams(200,200);
        down_arrow.setLayoutParams(controller_parms);
    }

    public void double_down_arrow() throws Exception
    {
        double_down_arrow= new ImageView(this);
        double_down_arrow.setImageResource(R.drawable.doublearrow);
        controller.addView(double_down_arrow, lp1);
        controller_parms = new LinearLayout.LayoutParams(200,200);
        double_down_arrow.setLayoutParams(controller_parms);
    }





}

