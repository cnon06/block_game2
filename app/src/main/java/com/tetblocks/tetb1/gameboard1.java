package com.tetblocks.tetb1;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.view.View;

import androidx.recyclerview.widget.RecyclerView;

import java.util.NavigableMap;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;
import java.util.TreeMap;

public class gameboard1 extends View
{
    boolean down_arrow_cont=false;
    boolean cont2=false;


    protected int horizontal = 3, vertical = -1, max_direction = 4, block_type = 4, direction = 1,
            block_width = 0, block_height = 0, ghost_horizontal = 3, speed = 500, down_arrow_count=0, speed2=0, first_column_count=0, score1=0;


    boolean coords[][] = new boolean[20][10], record_blocks[][] = new boolean[20][10], merge_blocks[][] = new boolean[20][10],
            record_blocks2[][] = new boolean[20][10], record_blocks3[][] = new boolean[20][10],  record_blocks4[][] = new boolean[20][10], record_blocks5[][] = new boolean[20][10]
            ,first_colomn=false;


    NavigableMap<String, Integer> max_direction2 = new TreeMap();
    NavigableMap<String, Integer> first_horizontal_position = new TreeMap();

 //blocks blcks = new blocks();
    Bitmap ic_launcher = BitmapFactory.decodeResource(this.getResources(),R.drawable.tet6);
    Bitmap ic_launcher2 = BitmapFactory.decodeResource(this.getResources(),R.drawable.tet7);
    Bitmap ic_launcher3=Bitmap.createScaledBitmap(ic_launcher,65,65,false);
    Bitmap ic_launcher4=Bitmap.createScaledBitmap(ic_launcher2,65,65,false);

    Timer timer1, timer3,timer4;
    TimerTask task1,task4;






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



    public void speed_up_down ()
    {
        try {
            speed = speed2;
            timer1.cancel();
            Timer1();
        } catch (Exception rt)
        {
            timer1.cancel();
            Timer1();
        }

    }



    public void Timer4()
    {


        timer4 = new Timer();

        task4 = new TimerTask() {

            // run() method to carry out the action of the task



            public void run() {




                    try {
                        direction();
                    }
                    catch (Exception ty)
                    {
                        System.out.println("Code 563er: "+ty);
                    }



                    invalidate();

                }





            };




        timer4.schedule(task4, 0,1);





    }


    public boolean Timer1()
    {
        cont2= false;

        timer1 = new Timer();

        task1 = new TimerTask() {

            // run() method to carry out the action of the task



            public void run() {


       cont2= true;


                    if(first_colomn)
                    {

                        if(first_column_count==0)
                        {
                            speed = 1000;
                            timer1.cancel();
                            Timer1();
                            first_column_count++;
                        }
                        else
                        {
                            first_column_count=0;
                            first_colomn=false;
                            speed_up_down();
                        }

                    }

                    else
                    {

                        try {
                            restart_blocks();
                        } catch (Exception ety)
                        {
                            System.out.println("Code-57mb: "+ety);
                        }




                        if(bottom_control() && vertical>15) first_horizontal_position();


                        if(down_arrow_cont)
                        {
                            down_arrow_count++;
                        }


                        if(down_arrow_count>3)
                        {
                            down_arrow_cont=false;
                            speed = 20;
                            down_arrow_count=0;

                          try {
                              timer1.cancel();
                              Timer1();
                          }
                          catch (Exception yu)
                          {
                              timer1.cancel();
                              Timer1();
                          }


                        }

                        vertical++;


                        try {
                            direction();
                        }
                        catch (Exception ty)
                        {
                            System.out.println("Code 563er: "+ty);
                        }



                        invalidate();

                    }


                  //  if(vertical==0 && speed2!=0) speed_up_down();


                    // screen_refresh();
                    //  sleep(speed);







            };
        };




        boolean timerstate=false;
         Timer t1;


// some code goes here to do whatever you want






     timer1.schedule(task1, 0,speed);



        if(cont2)
     {
         timer1.cancel();
         timer1.purge();
         Timer1();
     }




        return false;
    }




    public void Timer3()
    {


        timer3 = new Timer();
        TimerTask task3 = new TimerTask() {

            // run() method to carry out the action of the task



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
                       // score1=0;
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



        try {
            calibrate_record_blocks();
            max_direction_list();
            setMax_direction();
            first_horizontal_position_list();

        }
        catch (Exception e)
        {
            System.out.println("Code-13: "+e);
        }

        Timer1();
        Timer4();


    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        Paint paint = new Paint();


        /*
         int x = getWidth();
        int y = getHeight();
        int radius=100;
         */


        paint.setStyle(Paint.Style.FILL);
        paint.setColor(Color.WHITE);
        canvas.drawPaint(paint);

        /*
        if(dsw)
        {
            canvas.drawBitmap(ic_launcher3,100,100,null);

            //  canvas.drawBitmap(tet2, 300, 300, null);
            dsw=false;

        }
        else
        {
            canvas.drawBitmap(ic_launcher4,100,100,null);
            // canvas.drawBitmap(tet4, 300, 300, null);
            dsw=true;
        }
         */




        try {





            merge_blocks();

            direction();


            for(int y=0; y<merge_blocks.length; y++)
            {
                for(int x=0; x<merge_blocks[0].length; x++)
                {


                    if(merge_blocks[y][x]==false ) canvas.drawBitmap(ic_launcher4,10+(x*70),10+(y*70),null); // iv [y] [x].setImageResource(R.drawable.tet7);
                    else  canvas.drawBitmap(ic_launcher3,10+(x*70),10+(y*70),null);   //iv [y] [x].setImageResource(R.drawable.tet6);
                  //  if(iv[y][x]==null) iv [x] [y].setImageResource(R.drawable.tet6);


                }
            }


         //   System.out.println("vertical: "+vertical);

        }
        catch (Exception er)
        {
            System.out.println("Code-5543: "+er);
        }




        /*
          fdf++;
        System.out.println("game.board: "+fdf);
         */







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
               // record_blocks5[y][x] = record_blocks2[y][x];

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
            Timer3();

            /*
             for (int y = 0; y < record_blocks.length; y++) {
                for (int x = 0; x < record_blocks[0].length; x++) {

                    record_blocks[y][x] = record_blocks2[y][x];

                }
            }
             */

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
        //    direction_left_right_control();


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

        // int bbheight=y_intersection-ymin-1;
        int bbheight= ymax-y_intersection+1;


        int vertical2 = vertical;
        // if(y_intersection!=0 && block_width<block_height) vertical-=bbheight;
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

            /*
             direction++;
        if(direction>max_direction) direction=1;

        try
        {
            direction();
        }
        catch (Exception e)
        {
            System.out.println("Code-43d3: "+e);
        }
             */


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

        //  if(back_direction() && bbwidth!=0)
        {
            horizontal-=bbwidth;

            direction--;
            if(direction<1) direction=max_direction;
            dont_go=true;
        }

        if (xmax==9 && back_direction())
        //  if(back_direction() && bbwidth!=0)
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


        try {
            direction();
        }
        catch (Exception e)
        {

        }


        if(ymax>18) return true;
        else return false;




/*
   boolean rty = false;

        for (int y = 0; y < coords.length; y++) {


            for (int x = 0; x < coords[0].length; x++) {

                try {
                    if (!coords[y][x] && !record_blocks[y + 1][x]) {

                        rty = true;


                    }
                } catch (Exception er) {
                    System.out.println("Code-03: " + er);
                }


            }

        }


        if (rty) {
            first_horizontal_position();


        }


        return rty;
 */



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



        if (xmin>=1)
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


        if (xmax<=8)
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





        //    System.out.println("xmax : "+xmax);

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
                    System.out.println("Code-03: " + er);
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


    public void first_horizontal_position_list() {

        first_horizontal_position.put("bar", 3);
        first_horizontal_position.put("regular_z", 3);
        first_horizontal_position.put("reverse_z", 3);
        first_horizontal_position.put("regular_L", 3);
        first_horizontal_position.put("reverse_L", 3);
        first_horizontal_position.put("T", 3);
        first_horizontal_position.put("square", 4);


    }


    public void first_horizontal_position() {


        record_blocks();


        switch (block_type) {
            case 1:
                horizontal = first_horizontal_position.get("bar");
                ghost_horizontal = first_horizontal_position.get("bar");
                break;

            case 2:
                horizontal = first_horizontal_position.get("regular_z");
                ghost_horizontal = first_horizontal_position.get("regular_z");
                break;

            case 3:
                horizontal = first_horizontal_position.get("reverse_z");
                ghost_horizontal = first_horizontal_position.get("reverse_z");
                break;

            case 4:
                horizontal = first_horizontal_position.get("regular_L");
                ghost_horizontal = first_horizontal_position.get("regular_L");

                break;

            case 5:
                horizontal = first_horizontal_position.get("reverse_L");
                ghost_horizontal = first_horizontal_position.get("reverse_L");
                break;
            case 6:
                horizontal = first_horizontal_position.get("T");
                ghost_horizontal = first_horizontal_position.get("T");
                break;

            case 7:
                horizontal = first_horizontal_position.get("square");
                ghost_horizontal = first_horizontal_position.get("square");

                break;

        }


        vertical = -1;
        direction = 1;


        try {
            Random random = new Random();

            int x = random.nextInt(7);
            block_type=x+1;

        }
        catch (Exception e)
        {

        }

        setMax_direction();





        try {
            direction();
        }
        catch (Exception rt)
        {
            System.out.println("code-23w: "+rt);
        }



        remove_rows_blocks();


        if(speed2!=0)
        {


            speed_up_down();
            vertical=-2;
        }



        first_colomn=true;





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






}
