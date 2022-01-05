package com.tetblocks.tetb1;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.graphics.Typeface;
import android.os.Bundle;
import android.os.Vibrator;
import android.view.Gravity;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;
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


public class MainActivity extends AppCompatActivity {

    ActivityResultLauncher<Intent> activityResultLauncher = registerForActivityResult(

            new ActivityResultContracts.StartActivityForResult(),
            new ActivityResultCallback<ActivityResult>() {
                @Override
                public void onActivityResult(ActivityResult activityResult) {




                }
            }
    );


    Timer timer3;

    ImageView start, debris;


    int count=0;

    TextView best_score, best_score2;

    public  void  write_game_over_AsFile() {

        String FILE_NAME = "game_over.txt";
        String text = false+"";
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


    public boolean read_file_game_over() {

        boolean ggre=false;

                String FILE_NAME = "game_over.txt";


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


            System.out.println("File was read: "+sb);

            String gfr=sb.toString();
            ggre= Boolean.parseBoolean(gfr.trim());

//            best_score.setText(sb);
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


        return ggre;

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


            System.out.println("File was read: "+sb);
            best_score2.setText(sb);
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



    protected void onStop() {
        super.onStop();


        System.out.println("Mainactivity onstop");
    }




    public boolean Timer3()
    {

        timer3 = new Timer();
        TimerTask task3 = new TimerTask() {

            public void run() {

                try {

                count++;
                if(count>=2)
                {
                    write_game_over_AsFile();
                    count=0;
                    cancel();
                }


                }
                catch (Exception e)
                {

                }

            }

        };

        timer3.schedule(task3, 0,500);

        return false;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        getSupportActionBar().hide();

        setContentView(R.layout.activity_main);




        Intent myIntent = getIntent();

        boolean gg =myIntent.getBooleanExtra("ss2",true);

        System.out.println("MESAJ ALINDI: "+myIntent.getStringExtra("ss1")+", "+myIntent.getBooleanExtra("ss2",true));

       debris=(ImageView) findViewById(R.id.imageView3);
       start=(ImageView) findViewById(R.id.imageView2);



         if(gg)   debris.setImageResource(R.drawable.debris);
        else
            debris.setImageResource(R.drawable.game_over);




     //   write_game_over_AsFile();

        /*
          if(!read_file_game_over())   debris.setImageResource(R.drawable.debris);
        else
        {
            debris.setImageResource(R.drawable.game_over);

        }
        Timer3();
         */




     //    write_game_over_AsFile();


        best_score = findViewById(R.id.Best_Score);
        best_score.setText("BEST SCORE:");
        best_score.setTextSize(28.5f);
        best_score.setTypeface(null, Typeface.BOLD);
        best_score.setGravity(Gravity.CENTER);
      // best_score.setVisibility(View.INVISIBLE);

        best_score2 = findViewById(R.id.Best_Score2);
        best_score2.setText("10020");
        best_score2.setTextSize(28.5f);
        best_score2.setTypeface(null, Typeface.BOLD);
        best_score2.setGravity(Gravity.CENTER);

        read_file();


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

                         Intent myIntent = new Intent(MainActivity.this, gameboard.class);

                        myIntent.putExtra("ss1","Message from Mainactivity");
                        setResult(78, myIntent);
                        activityResultLauncher.launch(myIntent);


                        return true;
                }
                return false;
            }
        });


    }

}

