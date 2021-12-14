package com.tetblocks.tetb1;
import androidx.appcompat.app.AppCompatActivity;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.pm.ActivityInfo;
import android.content.res.AssetFileDescriptor;
import android.content.res.Resources;
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
import java.util.concurrent.ExecutionException;


public class MainActivity extends AppCompatActivity {

    LinearLayout ln1, lln [],controller, space_between_game_board_and_controller;
    LinearLayout.LayoutParams parms, controller_parms;
    ImageView right_arrow, left_arrow,  direction_arrow, down_arrow, play_pause, ghost1, mute1, vib1;
    LinearLayout.LayoutParams lp1;
    gameboard1 gameb1;
   start_game start_game1;

    final Object pauseLock = new Object();


    boolean go_right=false, go_left=false, timer_pause_start=false,  pause=false, thread1=false, pause_control=false, vib_true_false=false;

boolean  play_pause2=false, start_game=true;

    int  go_left_right_control=0, mute_count=1;


    float volume1=1f;

  MediaPlayer mp;

  Thread timer2, timer3;

public void timers()
{
    gameb1.Timer1();
    timer2();
    Timer3();
}

  public void buttons()
  {
      vib1.setOnTouchListener(new View.OnTouchListener() {
          @SuppressLint("ClickableViewAccessibility")
          @Override
          public boolean onTouch(View v, MotionEvent event) {
              switch (event.getAction()) {
                  case MotionEvent.ACTION_DOWN:


                      if (!play_pause2) {

                          if (!vib_true_false) {
                              Vibrator vibe = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
                              vibe.vibrate(20);
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
                              vibe.vibrate(20);
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

                                  mute1.setImageResource(R.drawable.mute3);

                                  break;


                              case 2:

                                  volume1 = 0;
                                  mp.setVolume(volume1, volume1);


                                  gameb1.volume1 = 1f;
                                  gameb1.volume2 = 1f;
                                  gameb1.mp.setVolume(gameb1.volume1, gameb1.volume1);
                                  gameb1.mp2.setVolume(gameb1.volume2, gameb1.volume2);


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
                              vibe.vibrate(20);
                          }


                          if (gameb1.ghost_true_false) {

                              //play();
                              ghost1.setImageResource(R.drawable.ghost1);
                              gameb1.ghost_true_false = false;

                          } else {
                              ghost1.setImageResource(R.drawable.ghost2);
                              gameb1.ghost_true_false = true;
                              //  pause();

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
                          vibe.vibrate(20);
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
                              vibe.vibrate(20);
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
                              vibe.vibrate(20);
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
                              vibe.vibrate(20);
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
                              vibe.vibrate(20);
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


    protected void onRestart() {


       // System.out.println("onRestart 12");
        super.onRestart();
    }



   public void onBackPressed()  {

        mp.pause();
      pause();
      System.out.println("onBackPressed");


    }

    protected void onDestroy () {
        mp.pause();
        pause();
        super.onDestroy();
    }



    protected void onPause() {
        mp.pause();
       pause();
       System.out.println("onPause");

        super.onPause();
    }

    protected void onStop() {
        mp.pause();
        pause();
        super.onStop();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRequestedOrientation (ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        getSupportActionBar().hide();

        setContentView(R.layout.activity_main);





       mp = MediaPlayer.create(this,R.raw.musics);






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



          if(start_game)
          {
              start_game();
              mp.pause();
              pause();
          }


          else
          {


              /*
                 if(!mp.isPlaying())
            {
                mp.start();
                mp.setVolume(volume1,volume1);

            }
               */





              game_board(70,70);
              space_between_game_board_and_controller(50);
              controller();
              space_between_game_board_and_controller(10);
              controller2();
          }







        }
        catch (Exception e)
        {
            System.out.println(e);
        }




        if(!start_game)
        {
            buttons();
          timers();
        }













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



       timer2 =  new Thread() {


            public void run() {
                try {

                    while (!timer_pause_start) {


                        synchronized (pauseLock) {


                            if (pause) {
                                pauseLock.wait();
                                pause = false;
                            }


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


                            sleep(100);


                        }
                    }
                    }


                catch(Exception ed)
                    {
                        System.out.println(ed);
                    }

            }
        };

        timer2.start();

    }




    public void start_game() {


        LinearLayout gb1 = new LinearLayout(this);
        gb1  = new LinearLayout(this);
        ln1.addView(gb1,lp1);
        gb1.setOrientation(LinearLayout.HORIZONTAL);
        gb1.setGravity(Gravity.CENTER);


        start_game1 = new start_game(this);

        int width  = Resources.getSystem().getDisplayMetrics().widthPixels;
        int height = Resources.getSystem().getDisplayMetrics().heightPixels;

        controller_parms = new LinearLayout.LayoutParams(width,height);
        start_game1.setLayoutParams(controller_parms);

        ln1.setGravity(Gravity.CENTER);

        gb1.addView(start_game1);


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
        LinearLayout.LayoutParams between_arrows_parms3 = new LinearLayout.LayoutParams(150,1200);
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



        if (!mp.isLooping()) {
            mp.start();
            mp.setVolume(volume1,volume1);
            completetion_listener();
        }


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
        play_pause.setImageResource(R.drawable.pause2);


        play_pause2=false;

      //  gameb1.play_pause=false;

        if(!mp.isLooping())

        {
            mp.start();
            mp.setVolume(volume1,volume1);

        }

        gameb1.play();
       play2();

    }

    public void pause()
    {

        play_pause.setImageResource(R.drawable.play2);
        mp.pause();



        try {

            gameb1.pause=true;
            pause=true;

          //  gameb1.play_pause=true;
            play_pause2=true;
            pause_control=true;

        }
        catch (Exception er)
        {
            System.out.println("Code 45k: "+er);
        }

    }



    public boolean Timer3()
    {

       timer3 = new Thread("Timer 3"){

            // run() method to carry out the action of the task



            public void run() {


                try {
                    while (!thread1) {

                        synchronized (pauseLock) {


                             if (pause) {
                                pauseLock.wait();
                                pause = false;
                            }



                             /*
                               if (!play_pause2) {

                                if (!mp.isLooping()) {
                                    mp.start();
                                    mp.setVolume(volume1,volume1);

                                }

                            } else pause();
                              */



                            Thread.sleep(1);

                        }
                    }
                }
                catch (Exception tyu)
                {
                    System.out.println("Code 56-er: "+tyu);
                }





            }
        };

       timer3.start();



        return false;
    }






}

