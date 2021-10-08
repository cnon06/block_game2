package com.tetblocks.tetb1;

import android.util.Log;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import java.util.NavigableMap;
import java.util.Random;
import java.util.Set;
import java.util.TreeMap;



public class blocks {

   protected int horizontal = 3, vertical = -1, max_direction = 5, block_type =1 ,direction=1, ghost_direction=1,
           block_width=0, block_height=0, ghost_block_width=0, ghost_block_height=0,ghost_horizontal = 3, ghost_vertical = -1, gogo_x=0, gogo_y=0, gogo_y2=0;




    boolean coords[][] = new boolean[20][10], record_blocks [][] = new boolean[20][10], merge_blocks [][] = new boolean[20][10], ghost1 [][] = new boolean[20][10],
            ghost6=false, ghost7=false, ghost8=false;

    ArrayList  xlist = new ArrayList<>(); ArrayList  xlist2 = new ArrayList<>();





    NavigableMap<String,Integer> max_direction2=new TreeMap();
   NavigableMap<String,Integer> first_horizontal_position= new TreeMap();




    blocks() {


    }



    public boolean down_control()
    {

        int xmax=0, xmin=50, width=0, ymin=50, ymax=0;

        boolean dont_go_down=false;

        for(int y=0;y<ghost1.length;y++) {
            for (int x = 0; x < ghost1[0].length; x++) {
                if(!ghost1[y][x]) {
                    if(x>xmax)xmax=x;
                    if(x<xmin)xmin=x;
                    if(y>ymax)ymax=y;
                    if(y<ymin)ymin=y;
                }

            }
        }

        int bbheight = (ymax-ymin+1);

        if(ghost_block_height != bbheight) dont_go_down=true;



        /*
           for(int y=0;y<ghost1.length;y++) {
            for (int x = 0; x < ghost1[0].length; x++) {
                if(!ghost1[y][x] && !record_blocks[y][x]) {
                    dont_go_down=true;
                }

            }
        }
         */


      //  boolean rty=false;





       // System.out.println("ymax: "+ymax);

        if(dont_go_down) ghost_vertical=vertical;

        return dont_go_down;


    }



    public boolean left_right_control()
   {

       int xmax=0, xmin=50, width=0, ymin=50, ymax=0;

       boolean dont_go_right_left=false;

       for(int y=0;y<ghost1.length;y++) {
           for (int x = 0; x < ghost1[0].length; x++) {
               if(!ghost1[y][x]) {
                   if(x>xmax)xmax=x;
                   if(x<xmin)xmin=x;
                   if(y>ymax)ymax=y;
                   if(y<ymin)ymin=y;
               }

           }
       }

       int bbwidth = (xmax-xmin+1);


     //  System.out.println("bbwidth: "+bbwidth +" ghost_block_width: "+ghost_block_width);

       if(ghost_block_width != bbwidth) dont_go_right_left=true;


       for(int y=0;y<ghost1.length;y++) {
           for (int x = 0; x < ghost1[0].length; x++) {
               if(!ghost1[y][x] && !record_blocks[y][x]) {
                   dont_go_right_left=true;
               }

           }
       }


       if(dont_go_right_left) ghost_horizontal=horizontal;



       return dont_go_right_left;





   }


    /*
    public boolean ghost_down() throws Exception {




        int xmax=0, xmin=50, width=0, ymin=50, ymax=0;

        for(int y=0;y<ghost1.length;y++) {
            for (int x = 0; x < ghost1[0].length; x++) {
                if(!ghost1[y][x]) {
                    if(x>xmax)xmax=x;
                    if(x<xmin)xmin=x;
                    if(y>ymax)ymax=y;
                    if(y<ymin)ymin=y;
                }

            }
        }


        int bbheight = ghost_block_height-(ymax-ymin+1);




        boolean dontgo2=false;




        for(int y=ymin-bbheight;y<=ymin;y++) {
            for (int x = 0; x < ghost1[0].length; x++) {
                if(!ghost1[y][x] && !record_blocks[y-bbheight][x]) {

                   if(ymax==19) dontgo2=true;

                }

            }
        }



        return  dontgo2;

    }





    public boolean ghost_left2() throws Exception {




        int xmax=0, xmin=50, width=0, ymin=50, ymax=0;

        for(int y=0;y<ghost1.length;y++) {
            for (int x = 0; x < ghost1[0].length; x++) {
                if(!ghost1[y][x]) {
                    if(x>xmax)xmax=x;
                    if(x<xmin)xmin=x;
                    if(y>ymax)ymax=y;
                    if(y<ymin)ymin=y;
                }

            }
        }


        int bbwidth = ghost_block_width-(xmax-xmin+1);




        boolean dontgo2=false;

        for(int y=0;y<ghost1.length;y++) {
            for (int x = 0; x < ghost1[0].length; x++) {
              if(x<ghost1[0].length-1)
              {

                // if(x<4) if(!ghost1 [y][x] && !record_blocks [y][xmax+bbwidth])
                  if(x<4) if(!ghost1 [y][x] && !record_blocks [y][x+bbwidth])
                  {

                     dontgo2=true;
                  }
              }



            }
        }



        if(xmin-bbwidth>-1) dontgo2=false;

        return  dontgo2;

    }



    public boolean ghost_left() throws Exception {


        for(int y=0;y<ghost1.length;y++) {
            for (int x = 0; x < ghost1[0].length; x++) {
                if(!ghost1[y][x] && record_blocks [y][x])  {

                       ListIterator itr = xlist.listIterator();

                       boolean fg2=false;

                    while (itr.hasNext())
                    {
                        if((int)itr.next()==x) fg2=true;
                    }


                   if(!fg2) xlist.add(x);
                   fg2=false;


                }

            }
        }


        Collections.sort(xlist);

        ListIterator itr = xlist.listIterator();

        Integer prv=-10, next2=0, count=0;


        while (itr.hasNext())
        {



          next2= (int)itr.next();

            String fgr = " next: "+next2;

            if(next2-prv!=2)
            {

                count++;
            }

           prv=next2;


        }


        int balance = ghost_block_width-count;

        count=0;



     xlist.clear();


        int xmax=0, xmin=50, width=0, ymin=50, ymax=0;

        for(int y=0;y<ghost1.length;y++) {
            for (int x = 0; x < ghost1[0].length; x++) {
                if(!ghost1[y][x]) {
                    if(x>xmax)xmax=x;
                    if(x<xmin)xmin=x;
                    if(y>ymax)ymax=y;
                    if(y<ymin)ymin=y;
                }

            }
        }


        boolean dontgo2=false;

        for(int y=0;y<ghost1.length;y++) {
            for (int x = 0; x < ghost1[0].length; x++) {
                if(!ghost1[y][x] && !record_blocks [y][x])  {

                dontgo2=true;

                }

            }
        }


        boolean dontgo=false;

        if(xmin-balance<0 && dontgo2) dontgo=true;

        return  dontgo;

    }



    public boolean ghost2() throws Exception
    {
        int xmax=0, xmin=50, width=0, ymin=50, ymax=0;

        int gogox=0, gogoy=0;
        boolean dont_go=false;


        if(ghost_block_width>ghost_block_height) gogox = gogo_x;
        else gogoy= gogo_y;


        for(int y=0;y<ghost1.length;y++) {

            for (int x = 0; x < ghost1[0].length; x++) {

                try {

                  if (!ghost1[y][x] && !record_blocks[y+gogoy][x+gogox])
                  {
                      dont_go = true;
                  }

                }
                catch (Exception er)
                {
                    System.out.println("Code-01: "+er);
                }

            }

        }




        for(int y=0;y<ghost1.length;y++) {
            for (int x = 0; x < ghost1[0].length; x++) {
                if(!ghost1[y][x]) {
                    if(x>xmax)xmax=x;
                    if(x<xmin)xmin=x;
                    if(y>ymax)ymax=y;
                    if(y<ymin)ymin=y;
                }

            }
        }



        if((xmax+gogox)>9) dont_go = true;



        return dont_go;
    }

     */


    protected void merge_blocks() throws Exception
    {
        for(int y=0;y<merge_blocks.length;y++) {



            for (int x = 0; x < merge_blocks[0].length; x++) {

                try {

                    merge_blocks[y][x] = coords [y][x];

                    if(!record_blocks[y][x])  merge_blocks[y][x] = record_blocks [y][x];
                }
                catch (Exception er)
                {
                    System.out.println("Code-02: "+er);
                }


            }

        }
    }


    public boolean restart_blocks() throws Exception
    {

        boolean rty=false;

            for(int y=0;y<coords.length;y++) {


                for (int x = 0; x < coords[0].length; x++) {

                    try {
                        if(!coords[y][x] && !record_blocks[y+1][x]) {

                            rty=true;


                        }
                    }
                    catch (Exception er)
                    {
                       System.out.println("Code-03: "+er);
                    }


                }

            }


             if(rty)
            {
                first_horizontal_position();


            }



     return rty;

    }




/*
 public void ghost_max_min2x2() throws Exception
    {

        int xmax=0, xmin=50, width=0, ymin=50, ymax=0;
        int x_intersection=0, y_intersection=0, gogo_x=0, gogo_y=0;

        boolean intersection= false, go_right=false, go_down=false, go_one_step_x=false, go_one_step_y=false;


        for(int y=0;y<ghost1.length;y++) {
            for (int x = 0; x < ghost1[0].length; x++) {
                if(!ghost1[y][x]) {
                    if(x>xmax)xmax=x;
                    if(x<xmin)xmin=x;
                    if(y>ymax)ymax=y;
                    if(y<ymin)ymin=y;
                }

            }
        }

      gogo_x = ghost_block_width-(xmax-xmin+1);


        ghost6=false; ghost7=false;


        if(xmax==9)

        {

            for(int y=0;y<ghost1.length;y++) {
                for (int x = 0; x < ghost1[0].length; x++) {
                    if(!ghost1[y][x] && !record_blocks[y][x-gogo_x]) {
                      ghost6=true;
                    }

                }
            }

          if(!ghost6)  ghost_horizontal -=gogo_x;

        }


        if(xmin==0) ghost_horizontal +=gogo_x;





        //  horizontal=ghost_horizontal;


    }


    public void ghost_max_min2x3() throws Exception
    {

        gogo_y2=0;

        int xmax=0, xmin=50, width=0, ymin=50, ymax=0;
        int x_intersection=0, y_intersection=0, gogo_x=0, gogo_y=0;

        boolean intersection= false, go_right=false, go_down=false, go_one_step_x=false, go_one_step_y=false;


        for(int y=0;y<ghost1.length;y++) {
            for (int x = 0; x < ghost1[0].length; x++) {
                if(!ghost1[y][x]) {
                    if(x>xmax)xmax=x;
                    if(x<xmin)xmin=x;
                    if(y>ymax)ymax=y;
                    if(y<ymin)ymin=y;
                }

            }
        }

       // gogo_x = ghost_block_width-(xmax-xmin+1);

        gogo_y2 = ghost_block_height-(ymax-ymin+1);


          if(ymax==19)

        {

                ghost_vertical -=gogo_y2;

              //  vertical=ghost_vertical;

        }


    }






    public void ghost_max_min2x() throws Exception
    {

        int xmax=0, xmin=50, width=0, ymin=50, ymax=0;
      //  int x_intersection=0, y_intersection=0, gogo_x=0, gogo_y=0;

        boolean intersection= false, go_right=false, go_down=false, go_one_step_x=false, go_one_step_y=false;


        gogo_x=0; gogo_y=0;

        ghost_horizontal=horizontal;
        ghost_vertical=vertical;


        for(int y=0;y<ghost1.length;y++) {
            for (int x = 0; x < ghost1[0].length; x++) {
                if(!ghost1[y][x]) {
                    if(x>xmax)xmax=x;
                    if(x<xmin)xmin=x;
                    if(y>ymax)ymax=y;
                    if(y<ymin)ymin=y;
                }

            }
        }

        for(int y=0;y<ghost1.length;y++) {
            for (int x = 0; x < ghost1[0].length; x++) {

                 if(!record_blocks[y][x] && !ghost1[y][x])
                {

                    if(xmin==x)
                    {
                        go_right=true;
                        gogo_x++;
                    }

                }
            if(go_right) break;
            }
        }

        for(int y=0;y<ghost1.length;y++) {
            for (int x = 0; x < ghost1[0].length; x++) {

                if(!record_blocks[y][x] && !ghost1[y][x])
                {

                    if(ymin==y)
                    {
                        go_down =true;
                        gogo_y++;
                    }
                }
            }
        }

        if(!go_right)
        {
            for(int y=0;y<ghost1.length;y++) {
                for (int x = 0; x < ghost1[0].length; x++) {
                    if(!record_blocks[y][x] && !ghost1[y][x])
                    {

                        if(xmax==x)
                        {
                            gogo_x++;
                            go_one_step_x=true;
                        }

                    }
                    if(go_one_step_x) break;
                }
            }

            for(int y=0;y<ghost1.length;y++) {
                for (int x = 0; x < ghost1[0].length; x++) {
                    if(!record_blocks[y][x] && !ghost1[y][x])
                    {
                        if(xmax-1==x)
                        {
                            if(go_one_step_x) gogo_x++; else gogo_x+=2;
                        }
                    }
                }
            }

        }

        if(!go_down)
        {
            for(int y=0;y<ghost1.length;y++) {
                for (int x = 0; x < ghost1[0].length; x++) {
                    if(!record_blocks[y][x] && !ghost1[y][x])
                    {

                        if(ymax==y)
                        {
                            gogo_y++;
                            go_one_step_y=true;
                        }

                    }
                    if(go_one_step_y) break;
                }

            }

            for(int y=0;y<ghost1.length;y++) {
                for (int x = 0; x < ghost1[0].length; x++) {
                    if(!record_blocks[y][x] && !ghost1[y][x])
                    {

                        if(ymax-1==y)
                        {

                            if(go_one_step_y) gogo_y++; else gogo_y+=2;
                        }

                    }
                }
            }

        }


        if(!go_right) gogo_x*=-1;
        if(!go_down) gogo_y*=-1;




                 if(!ghost2())
             {

                 if(ghost_block_width>ghost_block_height) ghost_horizontal += gogo_x;
                 else ghost_vertical += gogo_y;


               //  vertical=ghost_vertical;


             }



    }

 */




    public void calibrate_record_blocks() throws Exception
    {

        for(int y=0;y<coords.length;y++) {

            for (int x = 0; x < coords[0].length; x++) {

                record_blocks [y][x] = true ;

            }

        }
    }


    public void record_blocks() throws Exception
    {

      for(int y=0;y<coords.length;y++) {

            for (int x = 0; x < coords[0].length; x++) {


                 if(!coords[y][x])
                {
                    record_blocks [y][x] = coords[y][x] ;
                }
                  }

            }
    }



    public void setMax_direction() throws Exception
    {
        switch (block_type)
        {
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


    public void max_direction_list()  throws Exception
    {

        max_direction2.put("bar",2);
        max_direction2.put("regular_z",2);
        max_direction2.put("reverse_z",2);
        max_direction2.put("regular_L",4);
        max_direction2.put("reverse_L",4);
        max_direction2.put("T",4);
        max_direction2.put("square",1);


    }


    public void first_horizontal_position_list() throws Exception
    {

        first_horizontal_position.put("bar",3);
        first_horizontal_position.put("regular_z",3);
        first_horizontal_position.put("reverse_z",3);
        first_horizontal_position.put("regular_L",3);
        first_horizontal_position.put("reverse_L",3);
        first_horizontal_position.put("T",3);
        first_horizontal_position.put("square",4);


    }


    public void first_horizontal_position() throws Exception
    {




     record_blocks();

        switch (block_type)
        {
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


          vertical=-1;  direction=1;
        ghost_vertical=vertical+1;  ghost_direction=1;


          /*
            ghost_horizontal=horizontal;
          ghost_vertical=vertical;
           */



       // block_type=5;

        Random random = new Random();

        int x = random.nextInt(7);
        block_type=x+1;
       // System.out.println("Random: "+x);

        setMax_direction();



        /*
          Random random = new Random();

        int x = random.nextInt(6);
        block_type=x+1;
        System.out.println("Random: "+x);
         */





    }


    public boolean dont_go_down() throws Exception
    {

        int y1=0;

        for(int y=0;y<coords.length;y++) {



            for (int x = 0; x < coords[0].length; x++) {


                if(!coords[y][x]) y1=y;



            }

        }


        if(y1>=19) return false;
        else  return true;

    }

    public boolean dont_go_left() throws Exception
    {

        int x1=100;


        for(int y=0;y<coords.length;y++) {



            for (int x = 0; x < coords[0].length; x++) {


                if(!coords[y][x]) { if(x<x1)x1=x; //add1++;

                }


            }

        }


        if(x1<=0) return false;
        else  return true;

    }


    public boolean dont_go_left2() throws Exception
    {


        boolean thr=true;

        try {
            for(int y=0;y<coords.length;y++) {


                for (int x = 0; x < coords[0].length; x++) {


                    if(!record_blocks[y][x] && !coords[y][x+1])
                    {
                        thr = false;

                    }

                }
            }
        }
        catch (Exception e)
        {
            System.out.println("Code-04: "+e);
        }


        return thr;

    }

    public boolean dont_go_right2() throws Exception
    {


         boolean thr=true;

         /*
          try {
            for(int y=0;y<right1.length;y++) {


                for (int x = 0; x < right1[0].length; x++) {


                    right1[y][x]=true;


                }

            }

        }
        catch (Exception e)
        {
            System.out.println("Code-10: "+ e);
        }



        try {
            for(int y=0;y<right1.length;y++) {


                for (int x = 0; x < right1[0].length; x++) {


                    right1[y][x]=coords[y][x];


                }

            }

        }
        catch (Exception e)
        {
            System.out.println("Code-11: "+ e);
        }
          */




         try {
             for(int y=0;y<coords.length;y++) {


                 for (int x = 0; x < coords[0].length; x++) {

                     if(!coords[y][x] && !record_blocks[y][x+1])
                     //if(!right1[y][x] && !record_blocks[y][x+1])
                     {
                         thr = false;


                     }


                 }

             }

         }
            catch (Exception e)
            {
                System.out.println("Code-05: "+ e);
            }


        return thr;

    }

    public boolean dont_go_right() throws Exception
    {

        int x1=0;

        boolean thr=false;

        for(int y=0;y<coords.length;y++) {


            for (int x = 0; x < coords[0].length; x++) {


             //   if(!record_blocks[y][x-1] && !coords[y][x]) thr = true;

                if(!coords[y][x]) { if(x>x1)x1=x;

                }

            }

        }



        if(x1>=9) thr= false;
        else  thr= true;

        return thr;

    }





        public void direction() throws Exception
        {

        try {
            switch (direction)
            {
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



        }
        catch (Exception e)
        {
            System.out.println("Code-06: "+e);
        }

        }


    public void ghost_direction() throws Exception
    {

        try {
            switch (ghost_direction)
            {
                case 1:
                    ghost_block_type_d1();
                    //horizontal

                    break;

                case 2:
                    ghost_block_type_d2();
                    //vertical

                    break;

                case 3:
                    ghost_block_type_d3();
                    //horizontal

                    break;

                case 4:
                    ghost_block_type_d4();
                    //vertical

                    break;


            }

        }
        catch (Exception e)
        {
            System.out.println("Code-14: "+e);
        }

    }


    public void ghost_block_type_d1() throws Exception
    {
        switch (block_type)
        {
            case 1:
                ghost_bar_d1();

                break;

            case 2:
                ghost_regular_z_d1();

                break;

            case 3:
                ghost_reverse_z_d1();

                break;

            case 4:
                ghost_regular_L_d1();

                break;

            case 5:
                ghost_reverse_L_d1();

                break;

            case 6:
                ghost_T_d1();

                break;

            case 7:
                ghost_square();

                break;

        }
    }


    public void ghost_block_type_d2() throws Exception
    {
        switch (block_type)
        {
            case 1:
                ghost_bar_d2();

                break;

            case 2:
                ghost_regular_z_d2();

                break;

            case 3:
                ghost_reverse_z_d2();

                break;

            case 4:
                ghost_regular_L_d2();

                break;


            case 5:
                ghost_reverse_L_d2();

                break;

            case 6:
                ghost_T_d2();

                break;

        }
    }


    public void ghost_block_type_d3() throws Exception
    {
        switch (block_type)
        {

            case 4:
                ghost_regular_L_d3();

                break;

            case 5:
                ghost_reverse_L_d3();

                break;

            case 6:
                ghost_T_d3();

                break;
        }
    }

    public void ghost_block_type_d4() throws Exception
    {
        switch (block_type)
        {

            case 4:
                ghost_regular_L_d4();

                break;

            case 5:
                ghost_reverse_L_d4();

                break;

            case 6:
                ghost_T_d4();

                break;

        }
    }


    public void block_type_d1() throws Exception
    {
        switch (block_type)
        {
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


    public void block_type_d2() throws Exception
    {
        switch (block_type)
        {
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


    public void block_type_d3() throws Exception
    {
        switch (block_type)
        {

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

    public void block_type_d4() throws Exception
    {
        switch (block_type)
        {

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



    public  void T_d1() throws Exception
    {

        block_width=3;
        block_height=2;

        for(int y=0;y<coords.length;y++) {

            for (int x = 0; x < coords[0].length; x++) {

                if(x>=horizontal && x<=horizontal+2 && y>=vertical &&  y<= vertical) coords[y][x]=false;
                else if(x>=horizontal+1 && x<=horizontal+1 && y>=vertical+1 &&  y<= vertical+1) coords[y][x]=false;
                else  coords[y][x]=true;
            }

        }

    }


    public  void T_d2() throws Exception
    {


        block_width=2;
        block_height=3;

        for(int y=0;y<coords.length;y++) {

            for (int x = 0; x < coords[0].length; x++) {


                if(x>=horizontal+1 && x<=horizontal+1 && y>=vertical-1 &&  y<= vertical+1) coords[y][x]=false;
                else if(x>=horizontal && x<=horizontal && y>=vertical &&  y<= vertical) coords[y][x]=false;
                else  coords[y][x]=true;
            }

        }

    }

    public  void T_d3() throws Exception
    {

        block_width=3;
        block_height=2;

        for(int y=0;y<coords.length;y++) {

            for (int x = 0; x < coords[0].length; x++) {


                if(x>=horizontal+1 && x<=horizontal+1 && y>=vertical-1 &&  y<= vertical-1) coords[y][x]=false;
                else if(x>=horizontal && x<=horizontal+2 && y>=vertical &&  y<= vertical) coords[y][x]=false;
                else  coords[y][x]=true;


            }

        }

    }


    public  void T_d4() throws Exception
    {
        block_width=2;
        block_height=3;



        for(int y=0;y<coords.length;y++) {

            for (int x = 0; x < coords[0].length; x++) {

                if(x>=horizontal+2 && x<=horizontal+2 && y>=vertical &&  y<= vertical) coords[y][x]=false;
                else if(x>=horizontal+1 && x<=horizontal+1 && y>=vertical-1 &&  y<= vertical+1) coords[y][x]=false;
                else  coords[y][x]=true;
            }

        }

    }


    public  void reverse_L_d1() throws Exception
    {

        block_width = 3;
        block_height=2;


        for(int y=0;y<coords.length;y++) {

            for (int x = 0; x < coords[0].length; x++) {


                if(x>=horizontal && x<=horizontal+2 && y>=vertical &&  y<= vertical) coords[y][x]=false;
                else if(x>=horizontal && x<=horizontal && y>=vertical+1 &&  y<= vertical+1) coords[y][x]=false;
                else  coords[y][x]=true;
            }

        }

    }

    public  void reverse_L_d2() throws Exception
    {

        block_width = 2;
        block_height=3;

        for(int y=0;y<coords.length;y++) {

            for (int x = 0; x < coords[0].length; x++) {


                if(x>=horizontal+1 && x<=horizontal+1 && y>=vertical-1 &&  y<= vertical+1) coords[y][x]=false;
                else if(x>=horizontal && x<=horizontal && y>=vertical-1 &&  y<= vertical-1) coords[y][x]=false;
                else  coords[y][x]=true;
            }

        }

    }


    public  void reverse_L_d3() throws Exception
    {

        block_width = 3;
        block_height=2;

        for(int y=0;y<coords.length;y++) {

            for (int x = 0; x < coords[0].length; x++) {


                if(x>=horizontal+2 && x<=horizontal+2 && y>=vertical-1 &&  y<= vertical-1) coords[y][x]=false;
                else if(x>=horizontal && x<=horizontal+2 && y>=vertical &&  y<= vertical) coords[y][x]=false;
                else  coords[y][x]=true;
            }

        }

    }

    public  void reverse_L_d4() throws Exception
    {

        block_width = 2;
        block_height=3;

        for(int y=0;y<coords.length;y++) {

            for (int x = 0; x < coords[0].length; x++) {


                if(x>=horizontal+2 && x<=horizontal+2 && y>=vertical+1 &&  y<= vertical+1) coords[y][x]=false;
                else if(x>=horizontal+1 && x<=horizontal+1 && y>=vertical-1 &&  y<= vertical+1) coords[y][x]=false;
                else  coords[y][x]=true;
            }

        }

    }



    public  void regular_L_d1() throws Exception
    {


        block_width = 3;
        block_height=2;


        for(int y=0;y<coords.length;y++) {

            for (int x = 0; x < coords[0].length; x++) {

                if(x>=horizontal && x<=horizontal+2 && y>=vertical &&  y<= vertical) coords[y][x]=false;
                else if(x>=horizontal+2 && x<=horizontal+2 && y>=vertical+1 &&  y<= vertical+1) coords[y][x]=false;
                else  coords[y][x]=true;
            }

        }

    }


    public  void regular_L_d2() throws Exception
    {

        block_width = 2;
        block_height=3;

        for(int y=0;y<coords.length;y++) {

            for (int x = 0; x < coords[0].length; x++) {


                if(x>=horizontal+1 && x<=horizontal+1 && y>=vertical-1 &&  y<= vertical+1) coords[y][x]=false;
                else if(x>=horizontal && x<=horizontal && y>=vertical+1 &&  y<= vertical+1) coords[y][x]=false;
                else  coords[y][x]=true;
            }

        }

    }


    public  void regular_L_d3() throws Exception
    {

        block_width = 3;
        block_height=2;

        for(int y=0;y<coords.length;y++) {

            for (int x = 0; x < coords[0].length; x++) {


                if(x>=horizontal && x<=horizontal && y>=vertical-1 &&  y<= vertical-1) coords[y][x]=false;
                else if(x>=horizontal && x<=horizontal+2 && y>=vertical &&  y<= vertical) coords[y][x]=false;
                else  coords[y][x]=true;
            }

        }

    }

    public  void regular_L_d4() throws Exception
    {

        block_width = 2;
        block_height=3;


        for(int y=0;y<coords.length;y++) {

            for (int x = 0; x < coords[0].length; x++) {


                if(x>=horizontal+2 && x<=horizontal+2 && y>=vertical-1 &&  y<= vertical-1) coords[y][x]=false;
                else if(x>=horizontal+1 && x<=horizontal+1 && y>=vertical-1 &&  y<= vertical+1) coords[y][x]=false;
                else  coords[y][x]=true;
            }

        }

    }



    public  void reverse_z_d1() throws Exception
    {
        block_width=3;
        block_height=2;


        for(int y=0;y<coords.length;y++) {

            for (int x = 0; x < coords[0].length; x++) {


                if(x>=horizontal+1 && x<=horizontal+2 && y>=vertical &&  y<= vertical) coords[y][x]=false;
                else if(x>=horizontal && x<=horizontal+1 && y>=vertical+1 &&  y<= vertical+1) coords[y][x]=false;
                else  coords[y][x]=true;
            }

        }

    }

    public  void reverse_z_d2() throws Exception
    {
        block_width=2;
        block_height=3;


        for(int y=0;y<coords.length;y++) {

            for (int x = 0; x < coords[0].length; x++) {



                if(x>=horizontal && x<=horizontal && y>=vertical &&  y<= vertical+1) coords[y][x]=false;
                else if(x>=horizontal+1 && x<=horizontal+1 && y>=vertical+1 &&  y<= vertical+2) coords[y][x]=false;
                else  coords[y][x]=true;
            }
        }


    }


    public  void regular_z_d1() throws Exception
    {

        block_width=3;
        block_height=2;



        for(int y=0;y<coords.length;y++) {

            for (int x = 0; x < coords[0].length; x++) {


                if(x>=horizontal && x<=horizontal+1 && y>=vertical &&  y<= vertical) coords[y][x]=false;
                else if(x>=horizontal+1 && x<=horizontal+2 && y>=vertical+1 &&  y<= vertical+1) coords[y][x]=false;
                else  coords[y][x]=true;
            }
        }

    }

    public  void regular_z_d2() throws Exception
    {

        block_width=2;
        block_height=3;

        for(int y=0;y<coords.length;y++) {

            for (int x = 0; x < coords[0].length; x++) {


                if(x>=horizontal && x<=horizontal && y>=vertical+1 &&  y<= vertical+2) coords[y][x]=false;
                else if(x>=horizontal+1 && x<=horizontal+1 && y>=vertical &&  y<= vertical+1) coords[y][x]=false;
                else  coords[y][x]=true;
            }
        }

    }


    public  void square() throws  Exception
    {

        block_width=2;
        block_height=2;

        for(int y=0;y<coords.length;y++) {

            for (int x = 0; x < coords[0].length; x++) {

                if(x>=horizontal && x<=horizontal+1 && y>=vertical &&  y<= vertical+1) coords[y][x]=false;
                else  coords[y][x]=true;
            }

        }

    }


    public  void bar_d1() throws Exception
    {

        block_width=4;
        block_height=1;

        for(int y=0;y<coords.length;y++) {

            for (int x = 0; x < coords[0].length; x++) {

                if(x>=ghost_horizontal && x<=ghost_horizontal+3 && y>=ghost_vertical &&  y<= ghost_vertical) coords[y][x]=false;
                else  coords[y][x]=true;
            }

        }


    }



    public  void bar_d2() throws Exception
    {

        block_width=1;
        block_height=4;


        for(int y=0;y<coords.length;y++) {

            for (int x = 0; x < coords[0].length; x++) {



                if(x>=ghost_horizontal+1 && x<=ghost_horizontal+1 && y>=ghost_vertical-1 &&  y<= ghost_vertical+3-1) coords[y][x]=false;
                else  coords[y][x]=true;
            }

        }

    }


    public  void ghost_T_d1() throws Exception
    {

        ghost_block_width=3;
        ghost_block_height=2;

        for(int y=0;y<ghost1.length;y++) {

            for (int x = 0; x < ghost1[0].length; x++) {

                if(x>=ghost_horizontal && x<=ghost_horizontal+2 && y>=ghost_vertical &&  y<= ghost_vertical) ghost1[y][x]=false;
                else if(x>=ghost_horizontal+1 && x<=ghost_horizontal+1 && y>=ghost_vertical+1 &&  y<= ghost_vertical+1) ghost1[y][x]=false;
                else  ghost1[y][x]=true;
            }

        }

    }


    public  void ghost_T_d2() throws Exception
    {


        ghost_block_width=2;
        ghost_block_height=3;

        for(int y=0;y<ghost1.length;y++) {

            for (int x = 0; x < ghost1[0].length; x++) {


                if(x>=ghost_horizontal+1 && x<=ghost_horizontal+1 && y>=ghost_vertical-1 &&  y<= ghost_vertical+1) ghost1[y][x]=false;
                else if(x>=ghost_horizontal && x<=ghost_horizontal && y>=ghost_vertical &&  y<= ghost_vertical) ghost1[y][x]=false;
                else  ghost1[y][x]=true;
            }

        }

    }

    public  void ghost_T_d3() throws Exception
    {

        ghost_block_width=3;
        ghost_block_height=2;

        for(int y=0;y<ghost1.length;y++) {

            for (int x = 0; x < ghost1[0].length; x++) {



                if(x>=ghost_horizontal+1 && x<=ghost_horizontal+1 && y>=ghost_vertical-1 &&  y<= ghost_vertical-1) ghost1[y][x]=false;
                else if(x>=ghost_horizontal && x<=ghost_horizontal+2 && y>=ghost_vertical &&  y<= ghost_vertical) ghost1[y][x]=false;
                else  ghost1[y][x]=true;
            }

        }

    }


    public  void ghost_T_d4() throws Exception
    {
        ghost_block_width=2;
        ghost_block_height=3;



        for(int y=0;y<ghost1.length;y++) {

            for (int x = 0; x < ghost1[0].length; x++) {

                if(x>=ghost_horizontal+2 && x<=ghost_horizontal+2 && y>=ghost_vertical &&  y<= ghost_vertical) ghost1[y][x]=false;
                else if(x>=ghost_horizontal+1 && x<=ghost_horizontal+1 && y>=ghost_vertical-1 &&  y<= ghost_vertical+1) ghost1[y][x]=false;
                else  ghost1[y][x]=true;
            }

        }

    }


    public  void ghost_reverse_L_d1() throws Exception
    {

        ghost_block_width = 3;
        ghost_block_height=2;


        for(int y=0;y<ghost1.length;y++) {

            for (int x = 0; x < ghost1[0].length; x++) {


                if(x>=ghost_horizontal && x<=ghost_horizontal+2 && y>=ghost_vertical &&  y<= ghost_vertical) ghost1[y][x]=false;
                else if(x>=ghost_horizontal && x<=ghost_horizontal && y>=ghost_vertical+1 &&  y<= ghost_vertical+1) ghost1[y][x]=false;
                else  ghost1[y][x]=true;
            }

        }

    }

    public  void ghost_reverse_L_d2() throws Exception
    {

        ghost_block_width = 2;
        ghost_block_height=3;

        for(int y=0;y<ghost1.length;y++) {

            for (int x = 0; x < ghost1[0].length; x++) {


                if(x>=ghost_horizontal+1 && x<=ghost_horizontal+1 && y>=ghost_vertical-1 &&  y<= ghost_vertical+1) ghost1[y][x]=false;
                else if(x>=ghost_horizontal && x<=ghost_horizontal && y>=ghost_vertical-1 &&  y<= ghost_vertical-1) ghost1[y][x]=false;
                else  ghost1[y][x]=true;
            }

        }

    }


    public  void ghost_reverse_L_d3() throws Exception
    {

        ghost_block_width = 3;
        ghost_block_height=2;

        for(int y=0;y<ghost1.length;y++) {

            for (int x = 0; x < ghost1[0].length; x++) {


                if(x>=ghost_horizontal+2 && x<=ghost_horizontal+2 && y>=ghost_vertical-1 &&  y<= ghost_vertical-1) ghost1[y][x]=false;
                else if(x>=ghost_horizontal && x<=ghost_horizontal+2 && y>=ghost_vertical &&  y<= ghost_vertical) ghost1[y][x]=false;
                else  ghost1[y][x]=true;
            }

        }

    }

    public  void ghost_reverse_L_d4() throws Exception
    {

        ghost_block_width = 2;
        ghost_block_height=3;

        for(int y=0;y<ghost1.length;y++) {

            for (int x = 0; x < ghost1[0].length; x++) {


                if(x>=ghost_horizontal+2 && x<=ghost_horizontal+2 && y>=ghost_vertical+1 &&  y<= ghost_vertical+1) ghost1[y][x]=false;
                else if(x>=ghost_horizontal+1 && x<=ghost_horizontal+1 && y>=ghost_vertical-1 &&  y<= ghost_vertical+1) ghost1[y][x]=false;
                else  ghost1[y][x]=true;
            }

        }

    }



    public  void ghost_regular_L_d1() throws Exception
    {


        ghost_block_width = 3;
        ghost_block_height=2;


        for(int y=0;y<ghost1.length;y++) {

            for (int x = 0; x < ghost1[0].length; x++) {

                if(x>=ghost_horizontal && x<=ghost_horizontal+2 && y>=ghost_vertical &&  y<= ghost_vertical) ghost1[y][x]=false;
                else if(x>=ghost_horizontal+2 && x<=ghost_horizontal+2 && y>=ghost_vertical+1 &&  y<= ghost_vertical+1) ghost1[y][x]=false;
                else  ghost1[y][x]=true;
            }

        }

    }


    public  void ghost_regular_L_d2() throws Exception
    {

        ghost_block_width = 2;
        ghost_block_height=3;

        for(int y=0;y<ghost1.length;y++) {

            for (int x = 0; x < ghost1[0].length; x++) {


                if(x>=ghost_horizontal+1 && x<=ghost_horizontal+1 && y>=ghost_vertical-1 &&  y<= ghost_vertical+1) ghost1[y][x]=false;
                else if(x>=ghost_horizontal && x<=ghost_horizontal && y>=ghost_vertical+1 &&  y<= ghost_vertical+1) ghost1[y][x]=false;
                else  ghost1[y][x]=true;
            }

        }

    }





    public  void ghost_regular_L_d3() throws Exception
    {

        ghost_block_width = 3;
        ghost_block_height=2;

        for(int y=0;y<ghost1.length;y++) {

            for (int x = 0; x < ghost1[0].length; x++) {


                if(x>=ghost_horizontal && x<=ghost_horizontal && y>=ghost_vertical-1 &&  y<= ghost_vertical-1) ghost1[y][x]=false;
                else if(x>=ghost_horizontal && x<=ghost_horizontal+2 && y>=ghost_vertical &&  y<= ghost_vertical) ghost1[y][x]=false;
                else  ghost1[y][x]=true;
            }

        }

    }

    public  void ghost_regular_L_d4() throws Exception
    {

        ghost_block_width = 2;
        ghost_block_height=3;


        for(int y=0;y<ghost1.length;y++) {

            for (int x = 0; x < ghost1[0].length; x++) {


                if(x>=ghost_horizontal+2 && x<=ghost_horizontal+2 && y>=ghost_vertical-1 &&  y<= ghost_vertical-1) ghost1[y][x]=false;
                else if(x>=ghost_horizontal+1 && x<=ghost_horizontal+1 && y>=ghost_vertical-1 &&  y<= ghost_vertical+1) ghost1[y][x]=false;
                else  ghost1[y][x]=true;
            }

        }

    }





    public  void ghost_reverse_z_d1() throws Exception
    {
        ghost_block_width=3;
        ghost_block_height=2;


        for(int y=0;y<ghost1.length;y++) {

            for (int x = 0; x < ghost1[0].length; x++) {


                if(x>=ghost_horizontal+1 && x<=ghost_horizontal+2 && y>=ghost_vertical &&  y<= ghost_vertical) ghost1[y][x]=false;
                else if(x>=ghost_horizontal && x<=ghost_horizontal+1 && y>=ghost_vertical+1 &&  y<= ghost_vertical+1) ghost1[y][x]=false;
                else  ghost1[y][x]=true;
            }

        }

    }

    public  void ghost_reverse_z_d2() throws Exception
    {
        ghost_block_width=2;
        ghost_block_height=3;


        for(int y=0;y<ghost1.length;y++) {

            for (int x = 0; x < ghost1[0].length; x++) {



                if(x>=ghost_horizontal && x<=ghost_horizontal && y>=ghost_vertical &&  y<= ghost_vertical+1) ghost1[y][x]=false;
                else if(x>=ghost_horizontal+1 && x<=ghost_horizontal+1 && y>=ghost_vertical+1 &&  y<= ghost_vertical+2) ghost1[y][x]=false;
                else  ghost1[y][x]=true;
            }
        }




    }


    public  void ghost_regular_z_d1() throws Exception
    {

        ghost_block_width=3;
        ghost_block_height=2;



        for(int y=0;y<ghost1.length;y++) {

            for (int x = 0; x < ghost1[0].length; x++) {


                if(x>=ghost_horizontal && x<=ghost_horizontal+1 && y>=ghost_vertical &&  y<= ghost_vertical) ghost1[y][x]=false;
                else if(x>=ghost_horizontal+1 && x<=ghost_horizontal+2 && y>=ghost_vertical+1 &&  y<= ghost_vertical+1) ghost1[y][x]=false;
                else  ghost1[y][x]=true;
            }
        }

    }

    public  void ghost_regular_z_d2() throws Exception
    {

        ghost_block_width=2;
        ghost_block_height=3;



        for(int y=0;y<ghost1.length;y++) {

            for (int x = 0; x < ghost1[0].length; x++) {


                if(x>=ghost_horizontal && x<=ghost_horizontal && y>=ghost_vertical+1 &&  y<= ghost_vertical+2) ghost1[y][x]=false;
                else if(x>=ghost_horizontal+1 && x<=ghost_horizontal+1 && y>=ghost_vertical &&  y<= ghost_vertical+1) ghost1[y][x]=false;
                else  ghost1[y][x]=true;
            }
        }

    }


    public  void ghost_square() throws  Exception
    {


        ghost_block_width=2;
        ghost_block_height=2;


        for(int y=0;y<ghost1.length;y++) {

            for (int x = 0; x < ghost1[0].length; x++) {

                if(x>=ghost_horizontal && x<=ghost_horizontal+1 && y>=ghost_vertical &&  y<= ghost_vertical+1) ghost1[y][x]=false;
                else  ghost1[y][x]=true;
            }

        }

    }


    public  void ghost_bar_d1() throws Exception
    {


        ghost_block_width=4;
        ghost_block_height=1;

        for(int y=0;y<ghost1.length;y++) {

            for (int x = 0; x < ghost1[0].length; x++) {


                if(x>=ghost_horizontal && x<=ghost_horizontal+3 && y>=ghost_vertical &&  y<= ghost_vertical) ghost1[y][x]=false;
                else  ghost1[y][x]=true;
            }

        }


    }

    public  void ghost_bar_d2() throws Exception
    {

        ghost_block_width=1;
        ghost_block_height=4;


        for(int y=0;y<ghost1.length;y++) {

            for (int x = 0; x < ghost1[0].length; x++) {



                if(x>=ghost_horizontal+1 && x<=ghost_horizontal+1 && y>=ghost_vertical-1 &&  y<= ghost_vertical+3-1) ghost1[y][x]=false;
                else  ghost1[y][x]=true;
            }

        }

    }

}
