package com.tetblocks.tetb1;
import androidx.appcompat.app.AppCompatActivity;
import android.annotation.SuppressLint;
import android.content.Context;
import android.content.pm.ActivityInfo;
import android.content.res.AssetFileDescriptor;
import android.media.MediaPlayer;
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
import android.widget.TextView;

import java.io.IOException;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;


public class MainActivity extends AppCompatActivity {

    LinearLayout ln1, lln [],controller, space_between_game_board_and_controller;
    LinearLayout.LayoutParams parms, controller_parms;
    ImageView right_arrow, left_arrow,  direction_arrow, down_arrow, play_pause, ghost1, mute1;
    LinearLayout.LayoutParams lp1;
    gameboard1 gameb1;



    boolean go_right=false, go_left=false, timer_pause_start=false,  timer2_play_pause=false, thread1=false, next_song=false;

    boolean  play_pause2=false;

    int  go_left_right_control=0, playlist=1;

  MediaPlayer mp;



  public void sound_listener()
  {
      mp.start();


              mp.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                  @Override
                  public void onCompletion(MediaPlayer mediaPlayer) {


                      playlist++;
                      if(playlist>4) playlist=1;


                      System.out.println("on Completed");

                      try {

                          switch (playlist) {
                              case 1:
                                  mp = MediaPlayer.create(MainActivity.this,R.raw.tetris);
                                  // mp.start();
                                  break;

                              case 2:
                                  mp = MediaPlayer.create(MainActivity.this,R.raw.katyusha);
                                  //  mp.start();
                                  break;

                              case 3:
                                  mp = MediaPlayer.create(MainActivity.this,R.raw.polyushka);
                                  // mp.start();
                                  break;

                              case 4:
                                  mp = MediaPlayer.create(MainActivity.this,R.raw.kalinka);
                                  // mp.start();
                                  break;

                              default:

                          }

                          mp.start();
                          sound_listener();

                      }
                      catch (Exception ty)
                      {
                          System.out.println("Code yr86: "+ty);
                      }




                  }
              });


  }



    protected void onRestart() {


        System.out.println("onRestart 12");
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


            timer2_play_pause=true;
       //pause();


        System.out.println("on stop 1");
        super.onStop();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRequestedOrientation (ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        getSupportActionBar().hide();

        setContentView(R.layout.activity_main);


       mp = MediaPlayer.create(this,R.raw.tetris);


       // sound_listener();

        timer2();
        Timer3();

         try {
            Random random = new Random();

            int x = random.nextInt(7);
            gameb1.block_type=x+1;

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
            space_between_game_board_and_controller(50);
            controller();
            space_between_game_board_and_controller(10);
            controller2();

        }
        catch (Exception e)
        {
            System.out.println(e);
        }






                //next_song=true;



               /*
                mp = MediaPlayer.create(MainActivity.this,R.raw.kalinka);

                */

        // mp.release();



sound_listener();

/*


         mp.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer mediaPlayer) {


        playlist++;
        if(playlist>4) playlist=1;


        System.out.println("on Completed");

        try {

            switch (playlist) {
                case 1:
                    mp = MediaPlayer.create(MainActivity.this,R.raw.tetris);
                    // mp.start();
                    break;

                case 2:
                    mp = MediaPlayer.create(MainActivity.this,R.raw.katyusha);
                    //  mp.start();
                    break;

                case 3:
                    mp = MediaPlayer.create(MainActivity.this,R.raw.polyushka);
                    // mp.start();
                    break;

                case 4:
                    mp = MediaPlayer.create(MainActivity.this,R.raw.kalinka);
                    // mp.start();
                    break;

                default:

            }

            mp.start();

        }
        catch (Exception ty)
        {
            System.out.println("Code yr86: "+ty);
        }




    }
});
         */




        mute1.setOnTouchListener(new View.OnTouchListener() {
            @SuppressLint("ClickableViewAccessibility")
            @Override
            public boolean onTouch(View v, MotionEvent event)  {
                switch(event.getAction()) {
                    case MotionEvent.ACTION_DOWN:



                        if(!play_pause2)
                        {
                            Vibrator vibe = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
                            vibe.vibrate(20);



                            if(gameb1.mute_true_false)
                            {

                                //play();
                                mute1.setImageResource(R.drawable.mute2);
                                gameb1.mute_true_false=false;

                            }
                            else
                            {
                                mute1.setImageResource(R.drawable.mute1);
                                gameb1.mute_true_false=true;
                                //  pause();

                            }





                        }


                        return true;
                    case MotionEvent.ACTION_UP:


                        /*
                          if(!play_pause2)
                        {



                        }
                         */



                        return true;
                }
                return false;
            }
        });



        ghost1.setOnTouchListener(new View.OnTouchListener() {
            @SuppressLint("ClickableViewAccessibility")
            @Override
            public boolean onTouch(View v, MotionEvent event)  {
                switch(event.getAction()) {
                    case MotionEvent.ACTION_DOWN:



                        if(!play_pause2)
                        {
                            Vibrator vibe = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
                            vibe.vibrate(20);



                            if(gameb1.ghost_true_false)
                            {

                                //play();
                                ghost1.setImageResource(R.drawable.ghost1);
                                gameb1.ghost_true_false=false;

                            }
                            else
                            {
                                ghost1.setImageResource(R.drawable.ghost2);
                                gameb1.ghost_true_false=true;
                              //  pause();

                            }





                        }


                        return true;
                    case MotionEvent.ACTION_UP:


                        /*
                          if(!play_pause2)
                        {



                        }
                         */



                        return true;
                }
                return false;
            }
        });



        play_pause.setOnTouchListener(new View.OnTouchListener() {
            @SuppressLint("ClickableViewAccessibility")
            @Override
            public boolean onTouch(View v, MotionEvent event)  {
                switch(event.getAction()) {
                    case MotionEvent.ACTION_DOWN:

                        Vibrator vibe = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
                        vibe.vibrate(20);



                            if(play_pause2)
                            {


                                play();


                            }
                            else
                            {


                                //mp.pause();

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
                public boolean onTouch(View v, MotionEvent event)  {
                    switch(event.getAction()) {
                        case MotionEvent.ACTION_DOWN:



                            if(!play_pause2)
                            {
                                Vibrator vibe = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
                                vibe.vibrate(20);

                                right_arrow.setImageResource(R.drawable.red_right_arrow);

                                go_right=true;


                                try {



                                    gameb1.right_control();
                                    gameb1.direction();
                                    gameb1.invalidate();
                                    gameb1.ghost_vertical();

                                }
                                catch (Exception e)
                                {
                                    System.out.println("Code-45r3e: "+e);
                                }
                            }


                            return true;
                        case MotionEvent.ACTION_UP:

                            if(!play_pause2)
                            {
                                right_arrow.setImageResource(R.drawable.right_arrow);
                                go_right=false;
                                go_left_right_control=0;
                                gameb1.fast_press=false;

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
                switch(event.getAction()) {
                    case MotionEvent.ACTION_DOWN:


                        if(!play_pause2)
                        {
                            Vibrator vibe = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
                            vibe.vibrate(20);

                            left_arrow.setImageResource(R.drawable.red_left_arrow);

                           // gameb1.break_loop=false;


                            try {



                                gameb1.left_control();
                                gameb1.direction();
                                gameb1.invalidate();
                                gameb1.ghost_vertical();
                            }
                            catch (Exception e)
                            {
                                System.out.println("Code-423d: "+e);
                            }


                            go_left=true;

                        }

                        return true;
                    case MotionEvent.ACTION_UP:


                        if(!play_pause2)
                        {
                            left_arrow.setImageResource(R.drawable.left_arrow);
                            go_left=false;
                            go_left_right_control=0;

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
                switch(event.getAction()) {
                    case MotionEvent.ACTION_DOWN:



                        if(!play_pause2)
                        {




                            Vibrator vibe = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
                            vibe.vibrate(20);

                            direction_arrow.setImageResource(R.drawable.red_repeat);

                            try {



                                /*
                                 for (int i=0;i<10;i++) {
                                    gameb1.ghost_vertical(gameb1.vertical);
                                }
                                 */



                                gameb1.direction++;
                                if(gameb1.direction>gameb1.max_direction) gameb1.direction=1;
                                gameb1.direction();


                                if(! gameb1.direction_up_down_control())

                                    if( !gameb1.direction_left_right_border_control()) gameb1.direction_left_right_control2();


                                gameb1.invalidate();
                                gameb1.ghost_vertical();

                            }
                            catch (Exception e)
                            {
                                System.out.println("Code-9: "+e);
                            }


                        }




                        return true;
                    case MotionEvent.ACTION_UP:


                        if(!play_pause2)
                        {
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
                switch(event.getAction()) {
                    case MotionEvent.ACTION_DOWN:




                         if(!play_pause2) {



                             Vibrator vibe = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
                             vibe.vibrate(20);

                             down_arrow.setImageResource(R.drawable.red_down_arrow);


                             gameb1.speed2=gameb1.speed;

                             gameb1.fast_press=true;


                        }




                            return true;
                            case MotionEvent.ACTION_UP:



                                if(!play_pause2) {

                                    down_arrow.setImageResource(R.drawable.down_arrow);
                                    gameb1.speed=gameb1.speed2;
                                    gameb1.fast_press=false;
                                  //  gameb1.speed_up_down();



                                    //   timer1.schedule(task1, 0,speed);
                                }



                        return true;
                }
                return false;
            }
        });


    }




    public void timer2()
    {



        new Thread()
       {
            @Override
            public void run() {
                try
                {

                    while (!timer_pause_start)
                    {






                            if(go_left || go_right)
                            {

                                go_left_right_control++;

                                if(go_left_right_control>=2)
                                {
                                    if(go_left)
                                    {

                                        gameb1.left_control();
                                        gameb1.direction();
                                        gameb1.ghost_vertical();
                                        gameb1.invalidate();


                                    }

                                    if(go_right)
                                    {

                                        gameb1.right_control();
                                        gameb1.direction();
                                        gameb1.ghost_vertical();
                                        gameb1.invalidate();


                                    }
                                }

                            }



                        sleep(100);

                    }

                }
                catch (Exception ed)
                {
                    System.out.println(ed);
                }
            }
        }.start();






    }





    public void game_board(int Width, int Height)
    {


      LinearLayout gb1 = new LinearLayout(this);
      gb1  = new LinearLayout(this);
      ln1.addView(gb1,lp1);
      gb1.setOrientation(LinearLayout.HORIZONTAL);
      gb1.setGravity(Gravity.CENTER);



        LinearLayout between_arrows  = new LinearLayout(this);
        LinearLayout.LayoutParams between_arrows_parms = new LinearLayout.LayoutParams(150,Height);
        gb1.addView(between_arrows,between_arrows_parms);
        between_arrows.setOrientation(LinearLayout.HORIZONTAL);



        gameb1 = new gameboard1(this);
        controller_parms = new LinearLayout.LayoutParams(715,1420);
        gameb1.setLayoutParams(controller_parms);

        ln1.setGravity(Gravity.CENTER);

        gb1.addView(gameb1);



        LinearLayout between_arrows3  = new LinearLayout(this);
        LinearLayout.LayoutParams between_arrows_parms3 = new LinearLayout.LayoutParams(150,1000);
        gb1.addView(between_arrows3,between_arrows_parms3);
        between_arrows3.setOrientation(LinearLayout.VERTICAL);


        LinearLayout between_arrows4  = new LinearLayout(this);
        LinearLayout.LayoutParams between_arrows_parms4 = new LinearLayout.LayoutParams(150,400);
        between_arrows3.addView(between_arrows4,between_arrows_parms4);
        between_arrows4.setOrientation(LinearLayout.VERTICAL);
        between_arrows4.setGravity(Gravity.CENTER);



        TextView blocks7= new TextView(this);
        blocks7.setText("Next");

        between_arrows4.addView(blocks7);
        controller_parms = new LinearLayout.LayoutParams(150,50);
        blocks7.setLayoutParams(controller_parms);
        blocks7.setGravity(Gravity.CENTER);


        gameb1.blocks2= new ImageView(this);
        gameb1.blocks2.setImageResource(R.drawable.block_bar);

        between_arrows4.addView(gameb1.blocks2);
        controller_parms = new LinearLayout.LayoutParams(300,300);
        gameb1.blocks2.setLayoutParams(controller_parms);
        gameb1.blocks2.setRotation(90);



        LinearLayout between_arrows2  = new LinearLayout(this);
        LinearLayout.LayoutParams between_arrows_parms2 = new LinearLayout.LayoutParams(150,600);
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
        mute1.setImageResource(R.drawable.mute2);
        between_arrows2.addView(mute1);
        controller_parms = new LinearLayout.LayoutParams(150,150);
        mute1.setLayoutParams(controller_parms);


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
        play_pause.setImageResource(R.drawable.pause2);
        play_pause2=false;

        gameb1.play_pause=false;
        if(!mp.isLooping())   mp.start();

    }

    public void pause()
    {
        play_pause.setImageResource(R.drawable.play2);
        play_pause2=true;
        gameb1.play_pause=true;
        //if(mp.isPlaying())  mp.pause();

        mp.pause();

    }



    public boolean Timer3()
    {

        new Thread() {

            // run() method to carry out the action of the task



            public void run() {




                try {
                    while (!thread1) {



                        if(timer2_play_pause)
                        {

                            pause();
                            timer2_play_pause=false;
                        }






                        /*
                             if(!play_pause2) {

                            if(!mp.isLooping())   mp.start();

                        }
                         */










                        Thread.sleep(10);

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






}

