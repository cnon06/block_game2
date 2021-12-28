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
import java.io.IOException;
import java.io.InputStreamReader;


public class MainActivity extends AppCompatActivity {

    ActivityResultLauncher<Intent> activityResultLauncher = registerForActivityResult(

            new ActivityResultContracts.StartActivityForResult(),
            new ActivityResultCallback<ActivityResult>() {
                @Override
                public void onActivityResult(ActivityResult activityResult) {


                  /*
                      int result= activityResult.getResultCode();
                    Intent data = activityResult.getData();

                    if(activityResult.getResultCode() == 78)

                    // if(result == Activity.RESULT_OK)
                    {

                        Intent data1 = activityResult.getData();

                        if(data1 != null)
                        {
                            String title = data.getStringExtra("ss1");


                            System.out.println("MEESAGE FROM MAINACTIVITY: "+title);

                        }


                    }
                    else
                    {
                     //   Toast.makeText(gameboard.this, "Operation canceled", Toast.LENGTH_LONG).show();
                    }
                   */


                }
            }
    );



    ImageView start, debris;


    TextView best_score, best_score2;



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
            best_score.setText(sb);
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


        best_score = findViewById(R.id.Best_Score);
        best_score.setText("BEST SCORE:");
        best_score.setTextSize(28.5f);
        best_score.setTypeface(null, Typeface.BOLD);
        best_score.setGravity(Gravity.CENTER);

        best_score = findViewById(R.id.Best_Score2);
        best_score.setText("10020");
        best_score.setTextSize(28.5f);
        best_score.setTypeface(null, Typeface.BOLD);
        best_score.setGravity(Gravity.CENTER);

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

