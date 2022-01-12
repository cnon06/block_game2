package com.debris.debris;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Typeface;
import android.media.MediaPlayer;
import android.view.Gravity;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.NavigableMap;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;
import java.util.TreeMap;


public class gameboard1 extends View
{

    TextView score_board, lvl2;

    boolean    fast_press=false, ghost_true_false=false,   thread1=false, pause=false, only_once=false;

    final Object pauseLock = new Object();

    ImageView blocks2;

    gameboard gb1;

   int horizontal = 3, vertical = -1, max_direction = 4, block_type = 1, block_type_2=0, direction = 1,
            block_width = 0, block_height = 0, ghost_horizontal = 3, speed = 500,  speed2=0, score1=0,
           ghost_vertical=0, count_press=0, score2=0, removed_lines=0, lvl=1, lvl_up=0, best_score=0,score3=0, text_color=0;

    float volume1=1f,volume2=1f;


    String score_board2="";

    MediaPlayer mp, mp2,mp3,mp4;





    boolean coords[][] = new boolean[20][10], record_blocks[][] = new boolean[20][10], merge_blocks[][] = new boolean[20][10],
            record_blocks2[][] = new boolean[20][10], record_blocks3[][] = new boolean[20][10],  record_blocks4[][] = new boolean[20][10]
            , record_blocks5[][] = new boolean[20][10], ghost_coords[][] = new boolean[20][10];


    NavigableMap<String, Integer> max_direction2 = new TreeMap();



    Bitmap ic_launcher = BitmapFactory.decodeResource(this.getResources(),R.drawable.tet6);
    Bitmap ic_launcher2 = BitmapFactory.decodeResource(this.getResources(),R.drawable.tet7);
    Bitmap ic_launcher6 = BitmapFactory.decodeResource(this.getResources(),R.drawable.tet5);
    Bitmap ic_launcher3=Bitmap.createScaledBitmap(ic_launcher,65,65,false);
    Bitmap ic_launcher4=Bitmap.createScaledBitmap(ic_launcher2,65,65,false);
    Bitmap ic_launcher5=Bitmap.createScaledBitmap(ic_launcher6,65,65,false);

    Timer  timer3;



    public void random_blocks()
    {

       // block_type = block_type_2;
        try {
            Random random = new Random();

            int x = random.nextInt(7);

            block_type_2=x+1;

            switch (block_type_2) {
                case 1:

                    blocks2.setImageResource(R.drawable.block_bar);

                    break;

                case 2:

                    blocks2.setImageResource(R.drawable.block_z);

                    break;

                case 3:
                    blocks2.setImageResource(R.drawable.block_z2);

                    break;

                case 4:

                    blocks2.setImageResource(R.drawable.block_l2);

                    break;

                case 5:

                    blocks2.setImageResource(R.drawable.block_l);

                    break;

                case 6:

                    blocks2.setImageResource(R.drawable.block_t);

                    break;

                case 7:

                    blocks2.setImageResource(R.drawable.block_square);

                    break;
            }

        }
        catch (Exception e)
        {

        }
    }



    public boolean game_over()
    {
        int xmax = 0, xmin = 50, width = 0, ymin = 50, ymax = 0;

        boolean return1=false;


        for (int y = 0; y < record_blocks.length; y++) {
            for (int x = 0; x < record_blocks[0].length; x++) {
                if (!record_blocks[y][x]) {
                    if (x > xmax) xmax = x;
                    if (x < xmin) xmin = x;
                    if (y > ymax) ymax = y;
                    if (y < ymin) ymin = y;
                }
            }
        }
       // System.out.println("Ymin: "+ymin);


        if(ymin<=0)   return1=true;

        return return1;

    }


    public void ghost_vertical()
    {



        try {


            ghost_vertical=vertical;
            direction();
            boolean nt1=true;




            for(int z=0;z<20;z++)
            {
                ghost_vertical++;
                direction();

                for (int y = 0; y < ghost_coords.length; y++)
                {

                    for (int x = 0; x < ghost_coords[0].length; x++)
                    {
                        if (!ghost_coords[y][x] && !record_blocks[y+1][x])
                        {
                            nt1=false;
                          //  System.out.print("y: "+y+" x: "+x+ " ; ");
                        }
                    }
                    System.out.println("");

                }


                if(!nt1) break;

            }

        }
        catch (Exception rt)
        {
           // System.out.println("Code rt34: "+rt);
        }



    }




    public void setBest_score()
    {
       if(score2>best_score && !only_once)
       {
           score_board2=score_board.getText().toString();
           text_color=score_board.getCurrentTextColor();
           score_board.setText("!! HIGH SCORE !!");
           score_board.setTextColor(Color.RED);
           mp4.start();


       best_score_timer();
       only_once=true;
       }

    }

    public void remove_blocks_flicker()
    {

        for(int y=0; y<record_blocks3.length; y++) {

            for (int x = 0; x < record_blocks3[0].length; x++) {

                record_blocks3[y][x]=true;
            }
        }


        boolean eew=false;

        for(int y=0; y<record_blocks.length; y++) {

            for (int x = 0; x < record_blocks[0].length; x++) {

                if(record_blocks[y][x]) eew=true;
            }


            if(eew)
            {
                for (int x = 0; x < record_blocks[0].length; x++) {

                   record_blocks3[y][x] = record_blocks[y][x];
                }
            }
            else
            {
                for (int x = 0; x < record_blocks[0].length; x++) {

                    record_blocks3[y][x] = true;
                }
            }


        }

    }



    public void timer_content()
    {
        try {
            restart_blocks();
            if (bottom_control() && vertical > 15) first_horizontal_position();
            vertical++;
            direction();

            ghost_vertical();

            invalidate();
        }
        catch (Exception rtyu)
        {
            System.out.println("Code 89ty: "+rtyu);
        }

    }


    public  void play()
    {
            synchronized (pauseLock)
            {
                pauseLock.notifyAll();
            }
    }





/*
 public  boolean Timer1()
    {

        new Thread("Gameb-timer1") {


            public void run() {

                try {
                    while (!thread1) {

                          synchronized (pauseLock)
                        {

                            if(pause)
                            {
                                pauseLock.wait();
                                pause=false;
                            }

                            if(block_type_2==0)
                            {
                                random_blocks();
                            }

                            if(fast_press)
                            {

                                count_press++;
                                if(count_press>=3)
                                {
                                    speed=30;
                                    fast_press=false;
                                    count_press=0;
                                }
                                else
                                {
                                    speed=100;
                                }

                                lvl_up=0;
                            }
                            else lvl_up=lvl*100;

                            restart_blocks();
                            if (bottom_control() && vertical > 15) first_horizontal_position();

                            vertical++;

                            direction();

                            ghost_vertical();

                            invalidate();

                            Thread.sleep(speed);

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

 */





    public void best_score_timer()
    {


        Timer timer3 = new Timer();
        TimerTask task3 = new TimerTask() {



            public void run() {


                try
                {

                    score3++;

                    if(score3>3)
                    {
                        score_board.setText(score_board2);
                        score_board.setTextColor(text_color);
                        cancel();
                    }

                  //  invalidate();

                }
                catch (Exception ed)
                {
                    System.out.println("Code-11"+ed);
                }

            };
        };


        timer3.schedule(task3, 0,700);
    }



    public void Timer3()
    {


        timer3 = new Timer();
        TimerTask task3 = new TimerTask() {



            public void run() {


                try
                {

                    score1++;

                    if(score1==1)
                    {

                        for(int y=0; y<record_blocks4.length; y++) {

                            for (int x = 0; x < record_blocks4[0].length; x++) {

                                record_blocks[y][x]=record_blocks3[y][x];
                            }
                        }
                    }

                    if(score1==2)
                    {


                         for(int y=0; y<record_blocks4.length; y++) {

                            for (int x = 0; x < record_blocks4[0].length; x++) {

                                record_blocks[y][x]=record_blocks4[y][x];
                            }
                        }


                    }

                    if(score1==3)
                    {

                        for(int y=0; y<record_blocks4.length; y++) {

                            for (int x = 0; x < record_blocks4[0].length; x++) {

                                record_blocks[y][x]=record_blocks3[y][x];
                            }
                        }
                    }

                    if(score1==4)
                    {

                        for(int y=0; y<record_blocks4.length; y++) {

                            for (int x = 0; x < record_blocks4[0].length; x++) {

                                record_blocks[y][x]=record_blocks4[y][x];
                            }
                        }
                    }

                    if(score1>=5)
                    {
                        for (int y = 0; y < record_blocks.length; y++) {
                            for (int x = 0; x < record_blocks[0].length; x++) {

                                record_blocks[y][x] = record_blocks2[y][x];
                            }}

                        timer3.cancel();

                    }

                        invalidate();

                }
                catch (Exception ed)
                {
                    System.out.println("Code-11"+ed);
                }

            };
        };


        timer3.schedule(task3, 0,50);
    }

    public gameboard1(Context context) {
        super(context);


        gb1 = new gameboard();

      //  gb1.visible_objects();

        mp = MediaPlayer.create(context,R.raw.removeb1);
        mp2 = MediaPlayer.create(context,R.raw.removeb2);
        mp3 = MediaPlayer.create(context,R.raw.level_up);
        mp4 = MediaPlayer.create(context,R.raw.best_score);

        score_board= new TextView(context);
        score_board.setText("SCORE: 0");
       score_board.setTextSize(28.5f);
        score_board.setTypeface(null, Typeface.BOLD);
       score_board.setGravity(Gravity.CENTER);




        lvl2= new TextView(context);
        lvl2.setText("1");
        lvl2.setTextSize(25.5f);
        lvl2.setTypeface(null, Typeface.BOLD);
        lvl2.setGravity(Gravity.CENTER);


        speed2 = speed;





        try {
            Random random = new Random();

            int x = random.nextInt(7);
           block_type=x+1;

        }
        catch (Exception e)
        {

        }




        try {
            calibrate_record_blocks();
            max_direction_list();
            setMax_direction();
          //  first_horizontal_position_list();


            for(int y=0; y<ghost_coords.length; y++)
            {
                for(int x=0; x<ghost_coords[0].length; x++)
                {

                   ghost_coords[y][x]=true;
                }
            }


          //  ghost_regular_z_d2();

         //   random_blocks();
        }
        catch (Exception e)
        {
            System.out.println("Code-13: "+e);
        }


    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        Paint paint = new Paint();

        paint.setStyle(Paint.Style.FILL);
        paint.setColor(Color.WHITE);
        canvas.drawPaint(paint);


        try {


            merge_blocks();

            direction();


            for(int y=0; y<merge_blocks.length; y++)
            {
                for(int x=0; x<merge_blocks[0].length; x++)
                {

                    if(merge_blocks[y][x]==true )  canvas.drawBitmap(ic_launcher3,10+(x*70),10+(y*70),null);

                }
            }


            if(!ghost_true_false)
            {
                for(int y=0; y<merge_blocks.length; y++)
                {
                    for(int x=0; x<merge_blocks[0].length; x++)
                    {

                        if(ghost_coords[y][x]==false ) canvas.drawBitmap(ic_launcher5,10+(x*70),10+(y*70),null);

                    }
                }

            }


            for(int y=0; y<merge_blocks.length; y++)
            {
                for(int x=0; x<merge_blocks[0].length; x++)
                {

                    if(merge_blocks[y][x]==false ) canvas.drawBitmap(ic_launcher4,10+(x*70),10+(y*70),null);

                }
            }

        }
        catch (Exception er)
        {
            System.out.println("Code-5543: "+er);
        }


    }


    public void remove_rows_blocks()

    {


        for(int y=0; y<record_blocks4.length; y++) {

            for (int x = 0; x < record_blocks4[0].length; x++) {

                record_blocks4[y][x]=record_blocks[y][x];
            }
        }


        for (int y = 0; y < record_blocks2.length; y++) {
            for (int x = 0; x < record_blocks2[0].length; x++) {

                record_blocks2[y][x]=true;

            }
        }


        for (int y = 0; y < record_blocks.length; y++)

        {

            boolean tee= false;

            for (int x = 0; x < record_blocks[0].length; x++) {

                if(record_blocks[y][x]) tee=true;

            }


            if(!tee)
            {
                for (int x = 0; x < record_blocks[0].length; x++) {

                    record_blocks[y][x]=true;

                }
            }


        }


        int ygf=19;



        for (int y = record_blocks.length-1 ; y >=0 ; y--) {

            boolean gff=false;


            for (int x = 0; x < record_blocks[0].length; x++) {

                if(!record_blocks[y][x]) gff=true;

            }



            if(gff)
            {
                for (int x = 0; x < record_blocks[0].length; x++) {

                    try {
                        record_blocks2[ygf][x] = record_blocks[y][x];
                    }
                    catch (Exception re)
                    {

                    }

                }

                ygf--;
            }

        }

        for (int y = 0; y < record_blocks.length; y++) {
            for (int x = 0; x < record_blocks[0].length; x++) {

                record_blocks5[y][x] = record_blocks2[y][x];
            }
        }

        remove_blocks_flicker();


        int xmax = 0, xmin = 50, width = 0, ymin = 50, ymax = 0;


          for (int y = 0; y < record_blocks4.length; y++) {
            for (int x = 0; x < record_blocks4[0].length; x++) {
                if (!record_blocks4[y][x]) {
                    if (x > xmax) xmax = x;
                    if (x < xmin) xmin = x;
                    if (y > ymax) ymax = y;
                    if (y < ymin) ymin = y;
                }

            }
        }


        int xmax2 = 0, xmin2 = 50, ymin2 = 50, ymax2 = 0;


        for (int y = 0; y < record_blocks5.length; y++) {
            for (int x = 0; x < record_blocks5[0].length; x++) {
                if (!record_blocks5[y][x]) {
                    if (x > xmax2) xmax2 = x;
                    if (x < xmin2) xmin2 = x;
                    if (y > ymax2) ymax2 = y;
                    if (y < ymin2) ymin2 = y;
                }

            }
        }


        if(ymin!=ymin2)
        {
            score1=0;

           int number_of_removed_blocks=ymin2-ymin;

           removed_lines +=number_of_removed_blocks;

           if(removed_lines>=10)

           {
               lvl++;
               removed_lines=0;

               mp3.start();
               mp3.setVolume(volume2,volume2);
            //   System.out.println("level up speed: "+speed);


               speed2-=30;
               if(speed2<25) speed2=30;
               //speed2=200;


             //  System.out.println("level up speed: "+speed);
           }




           if(number_of_removed_blocks<4)mp.start(); else mp2.start();

            mp.setVolume(volume1,volume1);
            mp2.setVolume(volume2,volume2);




            int bonus = 0;

            switch (number_of_removed_blocks)

            {
                case 2:
                    bonus=7;
                    break;

                case 3:
                    bonus=14;
                    break;

                case 4:
                    bonus=33;
                    break;

            }



            int ghost_score=0;
          if(ghost_true_false) ghost_score=13;

          //  remove_sound1=true;

            Timer3();

            score2+=(number_of_removed_blocks*15)+bonus+ghost_score;
            lvl2.setText(lvl+"");

            score_board.setText("SCORE: "+score2);

        }

    }


    public boolean back_direction()
    {
        boolean dont_go=false;

        for (int y = 0; y < coords.length; y++) {
            for (int x = 0; x < coords[0].length; x++) {
                if (!coords[y][x] && !record_blocks[y][x]) {

                    dont_go=true;
                }

            }
        }

        return dont_go;
    }



    public boolean direction_left_right_control2()
    {
        int xmax = 0, xmin = 50, width = 0, ymin = 50, ymax = 0;



        for (int y = 0; y < coords.length; y++) {
            for (int x = 0; x < coords[0].length; x++) {
                if (!coords[y][x]) {
                    if (x > xmax) xmax = x;
                    if (x < xmin) xmin = x;
                    if (y > ymax) ymax = y;
                    if (y < ymin) ymin = y;
                }

            }
        }

        if(xmin!=0 && xmax!=9) direction_left_right_control();



        return false;
    }


    public boolean direction_left_right_control()
    {


        int xmax = 0, xmin = 50, width = 0, ymin = 50, ymax = 0;



        for (int y = 0; y < coords.length; y++) {
            for (int x = 0; x < coords[0].length; x++) {
                if (!coords[y][x]) {
                    if (x > xmax) xmax = x;
                    if (x < xmin) xmin = x;
                    if (y > ymax) ymax = y;
                    if (y < ymin) ymin = y;
                }

            }
        }

        int x_intersection=0;

        for (int y = 0; y < coords.length; y++) {
            for (int x = 0; x < coords[0].length; x++) {
                if (!coords[y][x] && !record_blocks[y][x]) {
                    x_intersection=x;
                }

            }
        }


        int bbwidth=(x_intersection-xmin+1);



        boolean dont_go=false;

        if(x_intersection==xmin && block_width>block_height)

        {
            horizontal+=bbwidth;

            try
            {
                direction();
            }
            catch (Exception e)
            {
                System.out.println("Code-43d3: "+e);
            }


            if(back_direction() && block_width>block_height )
            {

                horizontal-=bbwidth;
                direction--;
                if(direction<1) direction=max_direction;
                dont_go=true;

            }


        }

        try
        {
            direction();
        }
        catch (Exception e)
        {
            System.out.println("Code-43d3: "+e);
        }



        bbwidth=(xmax-x_intersection+1);

        if((x_intersection==xmax || (x_intersection==xmax-1 && block_type==1)) && block_width>block_height)

        {
            horizontal-=bbwidth;

            try
            {
                direction();
            }
            catch (Exception e)
            {
                System.out.println("Code-43d3: "+e);
            }


            if(back_direction() && block_width>block_height )
            {

                horizontal+=bbwidth;
                direction--;
                if(direction<1) direction=max_direction;
                dont_go=true;

            }
        }

        try
        {
            direction();
        }
        catch (Exception e)
        {
            System.out.println("Code-43d3: "+e);
        }

        return dont_go;

    }

    public boolean direction_up_down_control()
    {


        int xmax = 0, xmin = 50,  ymin = 50, ymax = 0;



        for (int y = 0; y < coords.length; y++) {
            for (int x = 0; x < coords[0].length; x++) {
                if (!coords[y][x]) {
                    if (x > xmax) xmax = x;
                    if (x < xmin) xmin = x;
                    if (y > ymax) ymax = y;
                    if (y < ymin) ymin = y;
                }

            }
        }

        int y_intersection=0; int count=0;

        for (int y = 0; y < coords.length; y++) {
            for (int x = 0; x < coords[0].length; x++) {
                if (!coords[y][x] && !record_blocks[y][x]) {

                    if(y_intersection!=y ) count++;
                    y_intersection=y;
                }

            }
        }


        int vertical2 = vertical;

        if(y_intersection!=0 && block_width<block_height && y_intersection==ymax) vertical-=count;
        if(y_intersection!=0 && block_width<block_height && y_intersection==ymax-1 && block_type==1) vertical-=2;


        try
        {
            direction();
        }
        catch (Exception e)
        {
            System.out.println("Code-43d3: "+e);
        }


        boolean dont_go=false;


        for (int y = 0; y < coords.length; y++) {
            for (int x = 0; x < coords[0].length; x++) {
                if (!coords[y][x]) {
                    if (x > xmax) xmax = x;
                    if (x < xmin) xmin = x;
                    if (y > ymax) ymax = y;
                    if (y < ymin) ymin = y;
                }

            }
        }


        for (int y = 0; y < coords.length; y++) {
            for (int x = 0; x < coords[0].length; x++) {
                if (!coords[y][x] && !record_blocks[y][x]) {
                    y_intersection=y;
                }

            }
        }


        if((ymin == y_intersection)  && (ymin!=0) && block_width<block_height )
        {

            vertical=vertical2;
            direction--;
            if(direction<1) direction=max_direction;
            dont_go=true;
            System.out.println("dontgo up down");
        }


        try
        {
            direction();
        }
        catch (Exception e)
        {
            System.out.println("Code-43d3: "+e);
        }
        // System.out.println("y_intersection: "+y_intersection+" ymax: "+ymax);
        return dont_go;

    }



    public boolean direction_left_right_border_control()
    {


        int xmax = 0, xmin = 50, width = 0, ymin = 50, ymax = 0;

        boolean dont_go_right_left = false;

        for (int y = 0; y < coords.length; y++) {
            for (int x = 0; x < coords[0].length; x++) {
                if (!coords[y][x]) {
                    if (x > xmax) xmax = x;
                    if (x < xmin) xmin = x;
                    if (y > ymax) ymax = y;
                    if (y < ymin) ymin = y;
                }

            }
        }

        int  bbwidth=block_width-(xmax-xmin+1);
        int bbheight= block_height-(ymax-ymin+1);


        if(xmin==0) horizontal+=bbwidth;
        if(xmax==9) horizontal-=bbwidth;

        try
        {
            direction();
        }
        catch (Exception e)
        {
            System.out.println("Code-43d3: "+e);
        }




        boolean dont_go=false;



        if(xmin==0 && back_direction())


        {
            horizontal-=bbwidth;

            direction--;
            if(direction<1) direction=max_direction;
            dont_go=true;
        }

        if (xmax==9 && back_direction())

        {

            horizontal+=bbwidth;
            direction--;
            if(direction<1) direction=max_direction;
            dont_go=true;

        }

        try
        {
            direction();
        }
        catch (Exception e)
        {
            System.out.println("Code-43d3: "+e);
        }



        if(ymax==19) vertical-=bbheight;

        try
        {
            direction();
        }
        catch (Exception e)
        {
            System.out.println("Code-43d3: "+e);
        }



        if(back_direction() && ymax==19)
        {
            // if(xmin==0) horizontal-=bbwidth;
            if(ymax==19) vertical+=bbheight;
            direction--;
            if(direction<1) direction=max_direction;
            dont_go=true;

        }


        try
        {
            direction();
        }
        catch (Exception e)
        {
            System.out.println("Code-43d3: "+e);
        }




        return dont_go;

    }



    public boolean bottom_control() {


        int xmax = 0, xmin = 50, ymin = 50, ymax = 0;


        for (int y = 0; y < coords.length; y++) {
            for (int x = 0; x < coords[0].length; x++) {
                if (!coords[y][x]) {
                    if (x > xmax) xmax = x;
                    if (x < xmin) xmin = x;
                    if (y > ymax) ymax = y;
                    if (y < ymin) ymin = y;
                }

            }
        }


        try {
            direction();
        }
        catch (Exception e)
        {

        }


        if(ymax>18) return true;
        else return false;



    }



    public boolean left_control() {

        int xmax = 0, xmin = 50, width = 0, ymin = 50, ymax = 0;

        boolean dont_go_right_left = false;

        for (int y = 0; y < coords.length; y++) {
            for (int x = 0; x < coords[0].length; x++) {
                if (!coords[y][x]) {
                    if (x > xmax) xmax = x;
                    if (x < xmin) xmin = x;
                    if (y > ymax) ymax = y;
                    if (y < ymin) ymin = y;
                }

            }
        }


        int tiriviri=1;

        if(block_type==5 && direction==2 && vertical==0)  tiriviri=2;


        if (xmin>=tiriviri)
        {
            boolean dont_go=false;

            for (int y = 0; y < coords.length; y++) {
                for (int x = 0; x < coords[0].length; x++) {
                    if (!coords[y][x] && !record_blocks[y][x-1]) {

                        dont_go=true;
                    }

                }
            }


            if(!dont_go) horizontal --;

        }


        return dont_go_right_left;


    }


    public boolean right_control() {

        int xmax = 0, xmin = 50, width = 0, ymin = 50, ymax = 0;

        boolean dont_go_right_left = false;

        for (int y = 0; y < coords.length; y++) {
            for (int x = 0; x < coords[0].length; x++) {
                if (!coords[y][x]) {
                    if (x > xmax) xmax = x;
                    if (x < xmin) xmin = x;
                    if (y > ymax) ymax = y;
                    if (y < ymin) ymin = y;
                }

            }
        }



        int tiriviri=8;

        if(block_type==4 && direction==4 && vertical==0)  tiriviri=7;


        if (xmax<=tiriviri)

       // if (xmax<=8)
        {
            boolean dont_go=false;

            for (int y = 0; y < coords.length; y++) {
                for (int x = 0; x < coords[0].length; x++) {
                    if (!coords[y][x] && !record_blocks[y][x+1]) {

                        dont_go=true;
                    }

                }
            }


            if(!dont_go) horizontal ++;

        }

        return dont_go_right_left;


    }



    protected void merge_blocks() throws Exception {
        for (int y = 0; y < merge_blocks.length; y++) {


            for (int x = 0; x < merge_blocks[0].length; x++) {

                try {

                    merge_blocks[y][x] = coords[y][x];

                  if (!record_blocks[y][x]) merge_blocks[y][x] = record_blocks[y][x];
                } catch (Exception er) {
                    System.out.println("Code-02: " + er);
                }


            }

        }
    }


    public boolean restart_blocks() throws Exception {

        boolean rty = false;

        for (int y = 0; y < coords.length; y++) {


            for (int x = 0; x < coords[0].length; x++) {

                try {
                    if (!coords[y][x] && !record_blocks[y + 1][x]) {

                        rty = true;


                    }
                } catch (Exception er) {
                  //  System.out.println("Code-03: " + er);
                }


            }
        }

        if (rty) {
            first_horizontal_position();

        }

        return rty;

    }



    public void calibrate_record_blocks() throws Exception {

        for (int y = 0; y < coords.length; y++) {

            for (int x = 0; x < coords[0].length; x++) {

                record_blocks[y][x] = true;

            }

        }
    }


    public void record_blocks() {

        for (int y = 0; y < coords.length; y++) {

            for (int x = 0; x < coords[0].length; x++) {


                if (!coords[y][x]) {
                    record_blocks[y][x] = coords[y][x];
                }
            }

        }
    }


    public void setMax_direction()  {
        switch (block_type) {
            case 1:
                max_direction = max_direction2.get("bar");
                break;

            case 2:
                max_direction = max_direction2.get("regular_z");
                break;

            case 3:
                max_direction = max_direction2.get("reverse_z");
                break;

            case 4:
                max_direction = max_direction2.get("regular_L");
                break;

            case 5:
                max_direction = max_direction2.get("reverse_L");
                break;
            case 6:
                max_direction = max_direction2.get("T");
                break;

            case 7:
                max_direction = max_direction2.get("square");
                break;

        }

    }


    public void max_direction_list() throws Exception {

        max_direction2.put("bar", 2);
        max_direction2.put("regular_z", 2);
        max_direction2.put("reverse_z", 2);
        max_direction2.put("regular_L", 4);
        max_direction2.put("reverse_L", 4);
        max_direction2.put("T", 4);
        max_direction2.put("square", 1);


    }


    public void first_horizontal_position_horizontal_position() {

        switch (block_type) {
            case 1:

                horizontal =3;
                ghost_horizontal =3;

                // horizontal = first_horizontal_position.get("bar");
                // ghost_horizontal = first_horizontal_position.get("bar");
                break;

            case 2:

                horizontal = 3;
                ghost_horizontal = 3;

                //    horizontal = first_horizontal_position.get("regular_z");
                //    ghost_horizontal = first_horizontal_position.get("regular_z");
                break;

            case 3:

                horizontal = 3;
                ghost_horizontal = 3;

                // horizontal = first_horizontal_position.get("reverse_z");
                // ghost_horizontal = first_horizontal_position.get("reverse_z");
                break;

            case 4:
                horizontal = 3;
                ghost_horizontal = 3;

                // horizontal = first_horizontal_position.get("regular_L");
                // ghost_horizontal = first_horizontal_position.get("regular_L");


                break;

            case 5:

                horizontal = 3;
                ghost_horizontal = 3;


                //   horizontal = first_horizontal_position.get("reverse_L");
                //   ghost_horizontal = first_horizontal_position.get("reverse_L");
                break;
            case 6:

                horizontal = 3;
                ghost_horizontal = 3;

                // horizontal = first_horizontal_position.get("T");
                // ghost_horizontal = first_horizontal_position.get("T");
                break;

            case 7:

                horizontal = 4;
                ghost_horizontal = 4;

                //   horizontal = first_horizontal_position.get("square");
                //   ghost_horizontal = first_horizontal_position.get("square");

                break;

        }



        /*
         first_horizontal_position.put("bar", 3);
        first_horizontal_position.put("regular_z", 3);
        first_horizontal_position.put("reverse_z", 3);
        first_horizontal_position.put("regular_L", 3);
        first_horizontal_position.put("reverse_L", 3);
        first_horizontal_position.put("T", 3);
        first_horizontal_position.put("square", 5);
         */



    }


    public void first_horizontal_position() {

        record_blocks();

        vertical = -1;
        direction = 1;

        block_type = block_type_2;

        random_blocks();

        first_horizontal_position_horizontal_position();

        setMax_direction();

        try {
            direction();
        }
        catch (Exception rt)
        {
            System.out.println("code-23w: "+rt);
        }

        remove_rows_blocks();

        if(speed2!=0) speed=speed2;



       if(game_over())
       {

           thread1=true;

       }




        setBest_score();




    }


    public void direction() throws Exception {

        try {
            switch (direction) {
                case 1:
                    block_type_d1();
                    //horizontal

                    break;

                case 2:
                    block_type_d2();
                    //vertical

                    break;

                case 3:
                    block_type_d3();
                    //horizontal

                    break;

                case 4:
                    block_type_d4();
                    //vertical

                    break;

            }


        } catch (Exception e) {
            System.out.println("Code-06: " + e);
        }


    }


    public void block_type_d1() throws Exception {
        switch (block_type) {
            case 1:
                bar_d1();

                break;

            case 2:
                regular_z_d1();

                break;

            case 3:
                reverse_z_d1();

                break;

            case 4:
                regular_L_d1();

                break;

            case 5:
                reverse_L_d1();

                break;

            case 6:
                T_d1();

                break;

            case 7:
                square();

                break;

        }
    }


    public void block_type_d2() throws Exception {
        switch (block_type) {
            case 1:
                bar_d2();

                break;

            case 2:
                regular_z_d2();

                break;

            case 3:
                reverse_z_d2();

                break;

            case 4:
                regular_L_d2();

                break;


            case 5:
                reverse_L_d2();

                break;

            case 6:
                T_d2();

                break;

        }
    }


    public void block_type_d3() throws Exception {
        switch (block_type) {

            case 4:
                regular_L_d3();

                break;

            case 5:
                reverse_L_d3();

                break;

            case 6:
                T_d3();

                break;
        }
    }

    public void block_type_d4() throws Exception {
        switch (block_type) {

            case 4:
                regular_L_d4();

                break;

            case 5:
                reverse_L_d4();

                break;

            case 6:
                T_d4();

                break;

        }
    }


    public void T_d1() throws Exception {

        ghost_T_d1();


        block_width = 3;
        block_height = 2;



        for (int y = 0; y < coords.length; y++) {

            for (int x = 0; x < coords[0].length; x++) {

                if (x >= horizontal && x <= horizontal + 2 && y >= vertical && y <= vertical)
                    coords[y][x] = false;
                else if (x >= horizontal + 1 && x <= horizontal + 1 && y >= vertical + 1 && y <= vertical + 1)
                    coords[y][x] = false;
                else coords[y][x] = true;
            }

        }

    }


    public void T_d2() throws Exception {


        ghost_T_d2();

        block_width = 2;
        block_height = 3;

        for (int y = 0; y < coords.length; y++) {

            for (int x = 0; x < coords[0].length; x++) {


                if (x >= horizontal + 1 && x <= horizontal + 1 && y >= vertical - 1 && y <= vertical + 1)
                    coords[y][x] = false;
                else if (x >= horizontal && x <= horizontal && y >= vertical && y <= vertical)
                    coords[y][x] = false;
                else coords[y][x] = true;
            }

        }

    }

    public void T_d3() throws Exception {

        ghost_T_d3();

        block_width = 3;
        block_height = 2;

        for (int y = 0; y < coords.length; y++) {

            for (int x = 0; x < coords[0].length; x++) {


                if (x >= horizontal + 1 && x <= horizontal + 1 && y >= vertical - 1 && y <= vertical - 1)
                    coords[y][x] = false;
                else if (x >= horizontal && x <= horizontal + 2 && y >= vertical && y <= vertical)
                    coords[y][x] = false;
                else coords[y][x] = true;


            }

        }

    }


    public void T_d4() throws Exception {

        ghost_T_d4();


        block_width = 2;
        block_height = 3;


        for (int y = 0; y < coords.length; y++) {

            for (int x = 0; x < coords[0].length; x++) {

                if (x >= horizontal + 2 && x <= horizontal + 2 && y >= vertical && y <= vertical)
                    coords[y][x] = false;
                else if (x >= horizontal + 1 && x <= horizontal + 1 && y >= vertical - 1 && y <= vertical + 1)
                    coords[y][x] = false;
                else coords[y][x] = true;
            }

        }

    }


    public void reverse_L_d1() throws Exception {


        ghost_reverse_L_d1();

        block_width = 3;
        block_height = 2;


        for (int y = 0; y < coords.length; y++) {

            for (int x = 0; x < coords[0].length; x++) {


                if (x >= horizontal && x <= horizontal + 2 && y >= vertical && y <= vertical)
                    coords[y][x] = false;
                else if (x >= horizontal && x <= horizontal && y >= vertical + 1 && y <= vertical + 1)
                    coords[y][x] = false;
                else coords[y][x] = true;
            }

        }

    }

    public void reverse_L_d2() throws Exception {


        ghost_reverse_L_d2();

        block_width = 2;
        block_height = 3;

        for (int y = 0; y < coords.length; y++) {

            for (int x = 0; x < coords[0].length; x++) {


                if (x >= horizontal + 1 && x <= horizontal + 1 && y >= vertical - 1 && y <= vertical + 1)
                    coords[y][x] = false;
                else if (x >= horizontal && x <= horizontal && y >= vertical - 1 && y <= vertical - 1)
                    coords[y][x] = false;
                else coords[y][x] = true;
            }

        }

    }


    public void reverse_L_d3() throws Exception {


        ghost_reverse_L_d3();

        block_width = 3;
        block_height = 2;

        for (int y = 0; y < coords.length; y++) {

            for (int x = 0; x < coords[0].length; x++) {


                if (x >= horizontal + 2 && x <= horizontal + 2 && y >= vertical - 1 && y <= vertical - 1)
                    coords[y][x] = false;
                else if (x >= horizontal && x <= horizontal + 2 && y >= vertical && y <= vertical)
                    coords[y][x] = false;
                else coords[y][x] = true;
            }

        }

    }

    public void reverse_L_d4() throws Exception {


        ghost_reverse_L_d4();

        block_width = 2;
        block_height = 3;

        for (int y = 0; y < coords.length; y++) {

            for (int x = 0; x < coords[0].length; x++) {


                if (x >= horizontal + 2 && x <= horizontal + 2 && y >= vertical + 1 && y <= vertical + 1)
                    coords[y][x] = false;
                else if (x >= horizontal + 1 && x <= horizontal + 1 && y >= vertical - 1 && y <= vertical + 1)
                    coords[y][x] = false;
                else coords[y][x] = true;
            }

        }

    }


    public void regular_L_d1() throws Exception {

        ghost_regular_L_d1();

        block_width = 3;
        block_height = 2;


        for (int y = 0; y < coords.length; y++) {

            for (int x = 0; x < coords[0].length; x++) {

                if (x >= horizontal && x <= horizontal + 2 && y >= vertical && y <= vertical)
                    coords[y][x] = false;
                else if (x >= horizontal + 2 && x <= horizontal + 2 && y >= vertical + 1 && y <= vertical + 1)
                    coords[y][x] = false;
                else coords[y][x] = true;
            }

        }

    }


    public void regular_L_d2() throws Exception {


        ghost_regular_L_d2();

        block_width = 2;
        block_height = 3;

        for (int y = 0; y < coords.length; y++) {

            for (int x = 0; x < coords[0].length; x++) {


                if (x >= horizontal + 1 && x <= horizontal + 1 && y >= vertical - 1 && y <= vertical + 1)
                    coords[y][x] = false;
                else if (x >= horizontal && x <= horizontal && y >= vertical + 1 && y <= vertical + 1)
                    coords[y][x] = false;
                else coords[y][x] = true;
            }

        }

    }


    public void regular_L_d3() throws Exception {


        ghost_regular_L_d3();

        block_width = 3;
        block_height = 2;

        for (int y = 0; y < coords.length; y++) {

            for (int x = 0; x < coords[0].length; x++) {


                if (x >= horizontal && x <= horizontal && y >= vertical - 1 && y <= vertical - 1)
                    coords[y][x] = false;
                else if (x >= horizontal && x <= horizontal + 2 && y >= vertical && y <= vertical)
                    coords[y][x] = false;
                else coords[y][x] = true;
            }

        }

    }

    public void regular_L_d4() throws Exception {

        ghost_regular_L_d4();

        block_width = 2;
        block_height = 3;


        for (int y = 0; y < coords.length; y++) {

            for (int x = 0; x < coords[0].length; x++) {


                if (x >= horizontal + 2 && x <= horizontal + 2 && y >= vertical - 1 && y <= vertical - 1)
                    coords[y][x] = false;
                else if (x >= horizontal + 1 && x <= horizontal + 1 && y >= vertical - 1 && y <= vertical + 1)
                    coords[y][x] = false;
                else coords[y][x] = true;
            }

        }

    }


    public void reverse_z_d1() throws Exception {

        ghost_reverse_z_d1();

        block_width = 3;
        block_height = 2;


        for (int y = 0; y < coords.length; y++) {

            for (int x = 0; x < coords[0].length; x++) {


                if (x >= horizontal + 1 && x <= horizontal + 2 && y >= vertical && y <= vertical)
                    coords[y][x] = false;
                else if (x >= horizontal && x <= horizontal + 1 && y >= vertical + 1 && y <= vertical + 1)
                    coords[y][x] = false;
                else coords[y][x] = true;
            }

        }

    }

    public void reverse_z_d2() throws Exception {


        ghost_reverse_z_d2();

        block_width = 2;
        block_height = 3;


        for (int y = 0; y < coords.length; y++) {

            for (int x = 0; x < coords[0].length; x++) {


                if (x >= horizontal && x <= horizontal && y >= vertical && y <= vertical + 1)
                    coords[y][x] = false;
                else if (x >= horizontal + 1 && x <= horizontal + 1 && y >= vertical + 1 && y <= vertical + 2)
                    coords[y][x] = false;
                else coords[y][x] = true;
            }
        }


    }


    public void regular_z_d1() throws Exception {


        ghost_regular_z_d1();

        block_width = 3;
        block_height = 2;


        for (int y = 0; y < coords.length; y++) {

            for (int x = 0; x < coords[0].length; x++) {


                if (x >= horizontal && x <= horizontal + 1 && y >= vertical && y <= vertical)
                    coords[y][x] = false;
                else if (x >= horizontal + 1 && x <= horizontal + 2 && y >= vertical + 1 && y <= vertical + 1)
                    coords[y][x] = false;
                else coords[y][x] = true;
            }
        }

    }

    public void regular_z_d2() throws Exception {


        ghost_regular_z_d2();

        block_width = 2;
        block_height = 3;

        for (int y = 0; y < coords.length; y++) {

            for (int x = 0; x < coords[0].length; x++) {


                if (x >= horizontal && x <= horizontal && y >= vertical + 1 && y <= vertical + 2)
                    coords[y][x] = false;
                else if (x >= horizontal + 1 && x <= horizontal + 1 && y >= vertical && y <= vertical + 1)
                    coords[y][x] = false;
                else coords[y][x] = true;
            }
        }

    }


    public void square() throws Exception {

        ghost_square();


        block_width = 2;
        block_height = 2;

        for (int y = 0; y < coords.length; y++) {

            for (int x = 0; x < coords[0].length; x++) {

                if (x >= horizontal && x <= horizontal + 1 && y >= vertical && y <= vertical + 1)
                    coords[y][x] = false;
                else coords[y][x] = true;
            }

        }

    }


    public void bar_d1() throws Exception {


        ghost_bar_d1();

        block_width = 4;
        block_height = 1;

        for (int y = 0; y < coords.length; y++) {

            for (int x = 0; x < coords[0].length; x++) {

                if (x >= horizontal && x <= horizontal + 3 && y >= vertical && y <= vertical)
                    coords[y][x] = false;
                else coords[y][x] = true;
            }

        }


    }


    public void bar_d2() throws Exception {

        ghost_bar_d2();

        block_width = 1;
        block_height = 4;


        for (int y = 0; y < coords.length; y++) {

            for (int x = 0; x < coords[0].length; x++) {


                if (x >= horizontal + 1 && x <= horizontal + 1 && y >= vertical - 1 && y <= vertical + 3 - 1)
                    coords[y][x] = false;
                else coords[y][x] = true;
            }

        }

    }





    public void ghost_T_d1() throws Exception {


        for (int y = 0; y < ghost_coords.length; y++) {

            for (int x = 0; x < ghost_coords[0].length; x++) {

                if (x >= horizontal && x <= horizontal + 2 && y >= ghost_vertical && y <= ghost_vertical)
                    ghost_coords[y][x] = false;
                else if (x >= horizontal + 1 && x <= horizontal + 1 && y >= ghost_vertical + 1 && y <= ghost_vertical + 1)
                    ghost_coords[y][x] = false;
                else ghost_coords[y][x] = true;
            }

        }

    }


    public void ghost_T_d2() throws Exception {


        for (int y = 0; y < ghost_coords.length; y++) {

            for (int x = 0; x < ghost_coords[0].length; x++) {


                if (x >= horizontal + 1 && x <= horizontal + 1 && y >= ghost_vertical - 1 && y <= ghost_vertical + 1)
                    ghost_coords[y][x] = false;
                else if (x >= horizontal && x <= horizontal && y >= ghost_vertical && y <= ghost_vertical)
                    ghost_coords[y][x] = false;
                else ghost_coords[y][x] = true;
            }

        }

    }

    public void ghost_T_d3() throws Exception {


        for (int y = 0; y < ghost_coords.length; y++) {

            for (int x = 0; x < ghost_coords[0].length; x++) {


                if (x >= horizontal + 1 && x <= horizontal + 1 && y >= ghost_vertical - 1 && y <= ghost_vertical - 1)
                    ghost_coords[y][x] = false;
                else if (x >= horizontal && x <= horizontal + 2 && y >= ghost_vertical && y <= ghost_vertical)
                    ghost_coords[y][x] = false;
                else ghost_coords[y][x] = true;


            }

        }

    }


    public void ghost_T_d4() throws Exception {


        for (int y = 0; y < ghost_coords.length; y++) {

            for (int x = 0; x < ghost_coords[0].length; x++) {

                if (x >= horizontal + 2 && x <= horizontal + 2 && y >= ghost_vertical && y <= ghost_vertical)
                    ghost_coords[y][x] = false;
                else if (x >= horizontal + 1 && x <= horizontal + 1 && y >= ghost_vertical - 1 && y <= ghost_vertical + 1)
                    ghost_coords[y][x] = false;
                else ghost_coords[y][x] = true;
            }

        }

    }


    public void ghost_reverse_L_d1() throws Exception {


        for (int y = 0; y < ghost_coords.length; y++) {

            for (int x = 0; x < ghost_coords[0].length; x++) {


                if (x >= horizontal && x <= horizontal + 2 && y >= ghost_vertical && y <= ghost_vertical)
                    ghost_coords[y][x] = false;
                else if (x >= horizontal && x <= horizontal && y >= ghost_vertical + 1 && y <= ghost_vertical + 1)
                    ghost_coords[y][x] = false;
                else ghost_coords[y][x] = true;
            }

        }

    }

    public void ghost_reverse_L_d2() throws Exception {


        for (int y = 0; y < ghost_coords.length; y++) {

            for (int x = 0; x < ghost_coords[0].length; x++) {


                if (x >= horizontal + 1 && x <= horizontal + 1 && y >= ghost_vertical - 1 && y <= ghost_vertical + 1)
                    ghost_coords[y][x] = false;
                else if (x >= horizontal && x <= horizontal && y >= ghost_vertical - 1 && y <= ghost_vertical - 1)
                    ghost_coords[y][x] = false;
                else ghost_coords[y][x] = true;
            }

        }

    }


    public void ghost_reverse_L_d3() throws Exception {


        for (int y = 0; y < ghost_coords.length; y++) {

            for (int x = 0; x < ghost_coords[0].length; x++) {


                if (x >= horizontal + 2 && x <= horizontal + 2 && y >= ghost_vertical - 1 && y <= ghost_vertical - 1)
                    ghost_coords[y][x] = false;
                else if (x >= horizontal && x <= horizontal + 2 && y >= ghost_vertical && y <= ghost_vertical)
                    ghost_coords[y][x] = false;
                else ghost_coords[y][x] = true;
            }

        }

    }

    public void ghost_reverse_L_d4() throws Exception {


        for (int y = 0; y < ghost_coords.length; y++) {

            for (int x = 0; x < ghost_coords[0].length; x++) {


                if (x >= horizontal + 2 && x <= horizontal + 2 && y >= ghost_vertical + 1 && y <= ghost_vertical + 1)
                    ghost_coords[y][x] = false;
                else if (x >= horizontal + 1 && x <= horizontal + 1 && y >= ghost_vertical - 1 && y <= ghost_vertical + 1)
                    ghost_coords[y][x] = false;
                else ghost_coords[y][x] = true;
            }

        }

    }


    public void ghost_regular_L_d1() throws Exception {

        for (int y = 0; y < ghost_coords.length; y++) {

            for (int x = 0; x < ghost_coords[0].length; x++) {

                if (x >= horizontal && x <= horizontal + 2 && y >= ghost_vertical && y <= ghost_vertical)
                    ghost_coords[y][x] = false;
                else if (x >= horizontal + 2 && x <= horizontal + 2 && y >= ghost_vertical + 1 && y <= ghost_vertical + 1)
                    ghost_coords[y][x] = false;
                else ghost_coords[y][x] = true;
            }

        }

    }


    public void ghost_regular_L_d2() throws Exception {

        for (int y = 0; y < ghost_coords.length; y++) {

            for (int x = 0; x < ghost_coords[0].length; x++) {


                if (x >= horizontal + 1 && x <= horizontal + 1 && y >= ghost_vertical - 1 && y <= ghost_vertical + 1)
                    ghost_coords[y][x] = false;
                else if (x >= horizontal && x <= horizontal && y >= ghost_vertical + 1 && y <= ghost_vertical + 1)
                    ghost_coords[y][x] = false;
                else ghost_coords[y][x] = true;
            }

        }

    }


    public void ghost_regular_L_d3() throws Exception {


        for (int y = 0; y < ghost_coords.length; y++) {

            for (int x = 0; x < ghost_coords[0].length; x++) {


                if (x >= horizontal && x <= horizontal && y >= ghost_vertical - 1 && y <= ghost_vertical - 1)
                    ghost_coords[y][x] = false;
                else if (x >= horizontal && x <= horizontal + 2 && y >= ghost_vertical && y <= ghost_vertical)
                    ghost_coords[y][x] = false;
                else ghost_coords[y][x] = true;
            }

        }

    }

    public void ghost_regular_L_d4() throws Exception {


        for (int y = 0; y < ghost_coords.length; y++) {

            for (int x = 0; x < ghost_coords[0].length; x++) {


                if (x >= horizontal + 2 && x <= horizontal + 2 && y >= ghost_vertical - 1 && y <= ghost_vertical - 1)
                    ghost_coords[y][x] = false;
                else if (x >= horizontal + 1 && x <= horizontal + 1 && y >= ghost_vertical - 1 && y <= ghost_vertical + 1)
                    ghost_coords[y][x] = false;
                else ghost_coords[y][x] = true;
            }

        }

    }


    public void ghost_reverse_z_d1() throws Exception {

        for (int y = 0; y < ghost_coords.length; y++) {

            for (int x = 0; x < ghost_coords[0].length; x++) {


                if (x >= horizontal + 1 && x <= horizontal + 2 && y >= ghost_vertical && y <= ghost_vertical)
                    ghost_coords[y][x] = false;
                else if (x >= horizontal && x <= horizontal + 1 && y >= ghost_vertical + 1 && y <= ghost_vertical + 1)
                    ghost_coords[y][x] = false;
                else ghost_coords[y][x] = true;
            }

        }

    }

    public void ghost_reverse_z_d2() throws Exception {

        for (int y = 0; y < ghost_coords.length; y++) {

            for (int x = 0; x < ghost_coords[0].length; x++) {

                if (x >= horizontal && x <= horizontal && y >= ghost_vertical && y <= ghost_vertical + 1)
                    ghost_coords[y][x] = false;
                else if (x >= horizontal + 1 && x <= horizontal + 1 && y >= ghost_vertical + 1 && y <= ghost_vertical + 2)
                    ghost_coords[y][x] = false;
                else ghost_coords[y][x] = true;
            }
        }

    }

    public void ghost_regular_z_d1() throws Exception {

        for (int y = 0; y < ghost_coords.length; y++) {

            for (int x = 0; x < ghost_coords[0].length; x++) {


                if (x >= horizontal && x <= horizontal + 1 && y >= ghost_vertical && y <= ghost_vertical)
                    ghost_coords[y][x] = false;
                else if (x >= horizontal + 1 && x <= horizontal + 2 && y >= ghost_vertical + 1 && y <= ghost_vertical + 1)
                    ghost_coords[y][x] = false;
                else ghost_coords[y][x] = true;
            }
        }

    }

    public void ghost_regular_z_d2() throws Exception {


        for (int y = 0; y < ghost_coords.length; y++) {

            for (int x = 0; x < ghost_coords[0].length; x++) {


                if (x >= horizontal && x <= horizontal && y >= ghost_vertical + 1 && y <= ghost_vertical + 2)
                    ghost_coords[y][x] = false;
                else if (x >= horizontal + 1 && x <= horizontal + 1 && y >= ghost_vertical && y <= ghost_vertical + 1)
                    ghost_coords[y][x] = false;
                else ghost_coords[y][x] = true;
            }
        }

    }


    public void ghost_square() throws Exception {

        for (int y = 0; y < ghost_coords.length; y++) {

            for (int x = 0; x < ghost_coords[0].length; x++) {

                if (x >= horizontal && x <= horizontal + 1 && y >= ghost_vertical && y <= ghost_vertical + 1)
                    ghost_coords[y][x] = false;
                else ghost_coords[y][x] = true;
            }

        }

    }


    public void ghost_bar_d1() throws Exception {


        for (int y = 0; y < ghost_coords.length; y++) {

            for (int x = 0; x < ghost_coords[0].length; x++) {

                if (x >= horizontal && x <= horizontal + 3 && y >= ghost_vertical && y <= ghost_vertical)
                    ghost_coords[y][x] = false;
                else ghost_coords[y][x] = true;
            }
        }
    }

    public void ghost_bar_d2() throws Exception {


        for (int y = 0; y < ghost_coords.length; y++) {

            for (int x = 0; x < ghost_coords[0].length; x++) {


                if (x >= horizontal + 1 && x <= horizontal + 1 && y >= ghost_vertical - 1 && y <= ghost_vertical + 2)
                    ghost_coords[y][x] = false;
                else ghost_coords[y][x] = true;
            }

        }

    }

}
