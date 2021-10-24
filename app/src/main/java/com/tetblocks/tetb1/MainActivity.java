package com.tetblocks.tetb1;
import androidx.appcompat.app.AppCompatActivity;
import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Bundle;
import android.os.Looper;
import android.os.Vibrator;
import android.util.Log;
import android.view.Gravity;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.LinearLayout;

import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;


public class MainActivity extends AppCompatActivity {

    LinearLayout ln1, lln [],controller, space_between_game_board_and_controller;
    LinearLayout.LayoutParams parms, controller_parms;
    ImageView iv [] [],right_arrow, left_arrow,  direction_arrow, down_arrow, double_down_arrow ;
    LinearLayout.LayoutParams lp1;

    boolean go_right=false, go_left=false;

    boolean timer_pause_start=true, timer_pause_start2=true, timer_pause_start3=true;

    int speed = 600, speed2=0;

    blocks blcks = new blocks();
  //  ghost1 ghst1 = new ghost1();


    Timer timer1;
    TimerTask task1;

    protected void onRestart() {

       /*
          timer_pause_start=false;
        finish();
        System.out.println("App is closing.");
        */

        super.onRestart();
    }

    protected void onDestroy () {

       /*
        timer_pause_start=false;
        finish();
        System.out.println("App is closing.");
        */

        super.onDestroy();
    }


    protected void onStop() {

       // finish();
        super.onStop();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        getSupportActionBar().hide();

        setContentView(R.layout.activity_main);

        // blcks.bar_d1();





        timer2();

         try {
            Random random = new Random();

            int x = random.nextInt(7);
            blcks.block_type=x+1;

        }
        catch (Exception e)
        {

        }



        try {
            blcks.calibrate_record_blocks();
            blcks.max_direction_list();
            blcks.setMax_direction();
            blcks.first_horizontal_position_list();

        }
        catch (Exception e)
        {
            System.out.println("Code-13: "+e);
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



        timer1();


            right_arrow.setOnTouchListener(new View.OnTouchListener() {
                @SuppressLint("ClickableViewAccessibility")
                @Override
                public boolean onTouch(View v, MotionEvent event)  {
                    switch(event.getAction()) {
                        case MotionEvent.ACTION_DOWN:

                            Vibrator vibe = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
                            vibe.vibrate(100);


                            right_arrow.setImageResource(R.drawable.red_right_arrow);

                            go_right=true;



                               try {
                                                blcks.right_control();
                                                blcks.direction();
                                                screen_refresh();
                            }
                            catch (Exception e)
                            {
                                System.out.println("Code-45r3e: "+e);
                            }





                            return true;
                        case MotionEvent.ACTION_UP:

                            right_arrow.setImageResource(R.drawable.right_arrow);
                            go_right=false;


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


                                 blcks.left_control();
                                 blcks.direction();
                                 screen_refresh();


                        }
                        catch (Exception e)
                        {
                            System.out.println("Code-423d: "+e);
                        }


                        go_left=true;

                        return true;
                    case MotionEvent.ACTION_UP:

                        left_arrow.setImageResource(R.drawable.left_arrow);
                        go_left=false;

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

                          blcks.direction++;
                          if(blcks.direction>blcks.max_direction) blcks.direction=1;
                          blcks.direction();


                           // blcks.direction_left_right_control();



                             if(! blcks.direction_up_down_control())

                               if( !blcks.direction_left_right_border_control()) blcks.direction_left_right_control2();



                          //  blcks.direction_up_down_control();

                            /*
                             if(!blcks.direction_left_right_border_control())
                               if(! blcks.direction_up_down_control()) blcks.direction_left_right_control2();
                             */



                            screen_refresh();


                        }
                        catch (Exception e)
                        {
                            System.out.println("Code-9: "+e);
                        }




                        return true;
                    case MotionEvent.ACTION_UP:

                        direction_arrow.setImageResource(R.drawable.repeat);




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


                        speed2=speed;
                        speed=100;
                        timer1.cancel();

                        timer1();

                  //   timer1.schedule(task1, 0,speed);

                        return true;
                    case MotionEvent.ACTION_UP:

                        down_arrow.setImageResource(R.drawable.down_arrow);

                        speed=speed2;
                        timer1.cancel();
                        timer1();
                     //   timer1.schedule(task1, 0,speed);

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


                        try
                        {

                            while (timer_pause_start3)
                            {
                                if(blcks.bottom_control()) {blcks.first_horizontal_position(); timer_pause_start3=false;}
                                if(blcks.restart_blocks()) timer_pause_start3=false;
                                blcks.vertical++;
                                blcks.direction();


                                screen_refresh();
                            }

                        }
                        catch (Exception ed)
                        {
                            System.out.println("Code-1fe1: "+ed);
                        }


                        timer_pause_start3=true;
                        return true;
                    case MotionEvent.ACTION_UP:

                        double_down_arrow.setImageResource(R.drawable.doublearrow);

                        return true;
                }
                return false;
            }
        });



    }





 public void screen_refresh() throws Exception
    {





  blcks.merge_blocks();



  /*
   for(int y=0;y< iv.length;y++) {

            for (int x = 0; x < iv[0].length; x++) {

                iv [y] [x].setImageResource(R.drawable.tet6);

            }
        }

   */




        /*
           for(int y=0;y<blcks.merge_blocks.length;y++)
            {

                for(int x=0;x<blcks.merge_blocks[0].length;x++)
                {
         */

        for(int y=0;y< blcks.merge_blocks.length;y++) {

            for (int x = 0; x < blcks.merge_blocks[0].length; x++) {


                    try {
                        if(blcks.merge_blocks[y][x]==false )  iv [y] [x].setImageResource(R.drawable.tet7);
                        else  iv [y] [x].setImageResource(R.drawable.tet6);
                        if(iv[y][x]==null) iv [x] [y].setImageResource(R.drawable.tet6);
                    }

                    catch (Exception eew)
                        {
                            System.out.println("Code-7: "+eew);
                        }
              //      if(blcks.coords[y][x]==false )  iv [y] [x].setImageResource(R.drawable.tet7);
                      // else if(blcks.ghost1[y][x]==false)  iv [y] [x].setImageResource(R.drawable.tet1);
            //  else if(blcks.record_blocks[y][x]==false )  iv [y] [x].setImageResource(R.drawable.tet7);


            }

            }




  for(int y=0;y< iv.length;y++) {

            for (int x = 0; x < iv[0].length; x++) {


                if(iv[y][x]==null) iv [x] [y].setImageResource(R.drawable.tet6);
               // iv [y] [x].setImageResource(R.drawable.tet6);

            }
        }





    }



    public void timer2()
    {


        /*
        new Thread()
       {
            @Override
            public void run() {
                try
                {

                    while (timer_pause_start)
                    {

                        blcks.vertical++;
                        blcks.ghost_vertical = blcks.vertical;
                        blcks.direction2();

                        screen_refresh();

                        sleep(600);
                        blcks.restart_blocks();
                        if(!blcks.dont_go_down()) blcks.first_horizontal_position();
                    }

                }
                catch (Exception ed)
                {
                    System.out.println(ed);
                }
            }
        }.start();
         */




        Timer timer = new Timer();
        TimerTask task = new TimerTask() {

            // run() method to carry out the action of the task



            public void run() {


                try
                {



                    if(go_left || go_right)
                    {


                        if(go_left)
                        {

                            blcks.left_control();
                            blcks.direction();
                            screen_refresh();
                        }



                        if(go_right)
                        {

                            blcks.right_control();
                            blcks.direction();
                            screen_refresh();
                        }
                    }


                }
                catch (Exception ed)
                {
                    System.out.println("Code-11"+ed);
                }



            };
        };



        timer.schedule(task, 0,150);



        /*
         Thread thread = new Thread(){
            synchronized public void run(){

                boolean jump=false, jump2=false;
                try
                {

                    while (timer_pause_start2)
                    {

                        if(go_left || go_right)
                        {
                            sleep(100);

                            if(go_left)
                            {

                                blcks.left_control();
                                blcks.direction();
                                screen_refresh();
                            }



                            if(go_right)
                            {

                                blcks.right_control();
                                blcks.direction();
                                screen_refresh();
                            }
                        }


                    }

                }
                catch (Exception ed)
                {
                    System.out.println("Code-554ds"+ed);
                }

            }
        };

        thread.start();

         */


    }


    public void timer1()
    {


        /*
        new Thread()
       {
            @Override
            public void run() {
                try
                {

                    while (timer_pause_start)
                    {

                        blcks.vertical++;
                        blcks.ghost_vertical = blcks.vertical;
                        blcks.direction2();

                        screen_refresh();

                        sleep(600);
                        blcks.restart_blocks();
                        if(!blcks.dont_go_down()) blcks.first_horizontal_position();
                    }

                }
                catch (Exception ed)
                {
                    System.out.println(ed);
                }
            }
        }.start();
         */





        /*
  Thread thread = new Thread(){
               synchronized public void run(){

                try
                {

                    while (timer_pause_start)
                    {
                        blcks.vertical++;
                        blcks.direction();
                        screen_refresh();
                        sleep(speed);
                        if(blcks.bottom_control()) blcks.first_horizontal_position();
                        blcks.restart_blocks();
                    }

                }
                catch (Exception ed)
                {
                    System.out.println("Code-11"+ed);
                }

            }
        };

        thread.start();
         */






         timer1 = new Timer();
      task1 = new TimerTask() {

            // run() method to carry out the action of the task



            public void run() {


                try
                {
                        blcks.restart_blocks();
                    if(blcks.bottom_control() && blcks.vertical>15) blcks.first_horizontal_position();

                   // blcks.direction();
                        blcks.vertical++;
                        blcks.direction();


                        screen_refresh();
                      //  sleep(speed);



                }
                catch (Exception ed)
                {
                    System.out.println("Code-11"+ed);
                }



            };
        };





        timer1.schedule(task1, 0,speed);





    }




    public void game_board(int Width, int Height) throws Exception
    {

        try {


            lln  = new LinearLayout[20];
            parms = new LinearLayout.LayoutParams(Width,Height);
            iv  = new ImageView[20] [10];



            for(int y=0;y<lln.length;y++)
            {

                lln [y] = new LinearLayout(this);
                ln1.addView(lln[y],lp1);

                lln [y].setOrientation(LinearLayout.HORIZONTAL);
                lln[y].setGravity(Gravity.CENTER);


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


                for(int x=0;x<iv[0].length;x++)
                {
                    iv [y] [x]= new ImageView(this);
                     iv [y] [x].setImageResource(R.drawable.tet6);
                    lln[y].addView(iv[y][x], lp1);
                    iv[y][x].setLayoutParams(parms);

                }


            }


        }
        catch (Exception e)
        {
            System.out.println(e);
        }


      //  timer1();


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

