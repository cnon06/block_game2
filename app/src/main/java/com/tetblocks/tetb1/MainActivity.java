package com.tetblocks.tetb1;
import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.os.Vibrator;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;


public class MainActivity extends AppCompatActivity {

    ActivityResultLauncher<Intent> activityResultLauncher = registerForActivityResult(

            new ActivityResultContracts.StartActivityForResult(),
            new ActivityResultCallback<ActivityResult>() {
                @Override
                public void onActivityResult(ActivityResult activityResult) {
                    int result= activityResult.getResultCode();
                    Intent data = activityResult.getData();

                    if(result == RESULT_OK)
                    {
                        String title = data.getStringExtra("title");
                        setTitle(title);
                        Toast.makeText(MainActivity.this, "Title Modified", Toast.LENGTH_LONG).show();
                    }
                    else
                    {
                        Toast.makeText(MainActivity.this, "Operation canceled", Toast.LENGTH_LONG).show();
                    }
                }
            }
    );



    ImageView start;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        getSupportActionBar().hide();

        setContentView(R.layout.activity_main);
        //  setContentView(R.layout.gameboard);

       start=(ImageView) findViewById(R.id.imageView2);
       // start= new ImageView(this,R.id.imageView);
       // start.setImageResource(R.id.imageView2);


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

                        Intent myIntent = new Intent(v.getContext(), gameboard.class);

                        activityResultLauncher.launch(myIntent);

                        start.setImageResource(R.drawable.start2);
                        return true;
                }
                return false;
            }
        });


    }

}

