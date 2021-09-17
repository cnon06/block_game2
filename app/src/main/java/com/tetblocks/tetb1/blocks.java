package com.tetblocks.tetb1;

import android.util.Log;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.NavigableMap;
import java.util.TreeMap;

public class blocks {

   protected int horizontal = 3, vertical = -1, max_direction = 3, block_type =5 ,direction=1, block_width=0, block_height=0, ghost_block_width=0, ghost_block_height=0;




    boolean coords[][] = new boolean[20][10], record_blocks [][] = new boolean[20][10], merge_blocks [][] = new boolean[20][10], ghost1 [][] = new boolean[20][10];

    List <Integer> intersection_list_x = new ArrayList<>();
    List <Integer> intersection_list_y = new ArrayList<>();


    NavigableMap<String,Integer> max_direction2=new TreeMap();
   NavigableMap<String,Integer> first_horizontal_position= new TreeMap();
    String is_vertical_horizontal="";

 //  ghost1 ghst1 = new ghost1();

    blocks() {

     //   ghst1 = new ghost1();
    }



    public boolean check_merge_blocks() throws Exception
    {

        boolean check=false;


        for (int x = 0; x < merge_blocks[0].length; x++) {



           if(!merge_blocks[0][x]) check=true ;



        }

        return check;

    }





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
                    System.out.println(er);
                }


            }

        }
    }


    public void restart_blocks() throws Exception
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
                       System.out.println(er);
                    }


                }

            }

            if(rty)
            {
                first_horizontal_position();


            }

       // if(max_min_ymin==check_record_blocks_ymax+1) first_horizontal_position();

    }



    public boolean max_min2x_dont_go() throws Exception
    {
        int xmax=0, xmin=50, width=0, ymin=50, ymax=0;
        int x_intersection=0, y_intersection=0;
        int x1=0, y1=0 ,x2=0, y2=0;
      //  boolean try2= false, go_left=false, go_up=false;

        int count =0;

        boolean dont_go=false;

        for(int y=0;y<coords.length;y++) {
            for (int x = 0; x < coords[0].length; x++) {
                if(!coords[y][x]) {
                    if(x>xmax)xmax=x;
                    if(x<xmin)xmin=x;
                    if(y>ymax)ymax=y;
                    if(y<ymin)ymin=y;
                }



                if(!record_blocks[y][x] && !coords[y][x])
                {
                    if(xmin<=x && x>=xmax)
                    {
                        dont_go=true;

                    }

                }

            }



        }

        if(x_intersection!=0)
        {

           // if(y1==y2 && x1==x2+1 )  dont_go=true;
          //  if(y1==y2+1 && x1==x2 ) dont_go=true;

         //   if(block_width>block_height)
          //      if(go_left)   horizontal-=(xmax-x_intersection)+1; else horizontal+=(x_intersection-xmin)+1;

        }



            if(dont_go)

            {
                //direction=0;
              // direction--;
                System.out.println("dont go");
            }

        return dont_go;


    }






    public void max_min2x() throws Exception
    {
        int xmax=0, xmin=50, width=0, ymin=50, ymax=0;
        int x_intersection=0, y_intersection=0;

       boolean try2= false, go_left=false, go_up=false;

        for(int y=0;y<coords.length;y++) {
            for (int x = 0; x < coords[0].length; x++) {
                if(!coords[y][x]) {
                    if(x>xmax)xmax=x;
                    if(x<xmin)xmin=x;
                    if(y>ymax)ymax=y;
                    if(y<ymin)ymin=y;
                }

                if(!record_blocks[y][x] && !coords[y][x])
                {
                    if(xmin<=x && x>=xmax)
                    {
                        x_intersection=x;   //try2=true;
                        if(x_intersection!=xmin) go_left=true;

                    }

                }

            }
        }

                 if(x_intersection!=0)
            {

                if(block_width>block_height)

                if(go_left)
                {
                    horizontal-=(xmax-x_intersection)+1;
                }
                    else
                {
                    horizontal+=(x_intersection-xmin)+1; // max_min2x_dont_go();
                }



        }

    }

    public void max_min2y() throws Exception
    {
        int xmax=0, xmin=50, width=0, ymin=50, ymax=0;
        int x_intersection=0, y_intersection=0;

        boolean try2= false,  go_up=false, break_me=false, dont_make_trw_1=false;
        int trw=0;

        for(int y=0;y<coords.length;y++) {
            for (int x = 0; x < coords[0].length; x++) {
                if(!coords[y][x]) {
                    if(x>xmax)xmax=x;
                    if(x<xmin)xmin=x;
                    if(y>ymax)ymax=y;
                    if(y<ymin)ymin=y;
                }



                if(!record_blocks[y][x] && !coords[y][x])
                {
                    trw++;
                    if(ymin<=y && y<=ymax)
                    {
                        y_intersection=y;   //try2=true;
                        if(y_intersection!=ymin) go_up=true;


                    }

                }

            }

        }

        if(y_intersection!=0)
        {

            if(block_width<block_height)


               if(trw==2)
               {
                   vertical=vertical-(ymax-y_intersection)-2;
                  dont_make_trw_1=true;
               }

          if(!dont_make_trw_1)  if(go_up)   vertical-=(ymax-y_intersection)+1; else vertical+=(y_intersection-ymin)+1;


        }

     //   System.out.println("ymax: "+ymax+" ymin: "+ymin+" trw: "+trw);
    }

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



    public void max_min() throws Exception
    {

        int xmax=0, xmin=50, ymin=50, ymax=0,  block_width2=0, block_height2=0;




        for(int y=0;y<coords.length;y++) {



            for (int x = 0; x < coords[0].length; x++) {


                if(!coords[y][x]) {
                    if(x>xmax)xmax=x;
                    if(x<xmin)xmin=x;
                    if(y>ymax)ymax=y;
                    if(y<ymin)ymin=y;

                }

            }

        }




        block_width2=xmax-xmin+1;
        block_height2=ymax-ymin+1;



         int gogo=0, gogo2=0;
      if(block_width2>-10 && vertical>0)   gogo = this.block_width-block_width2;


    if(xmin>0)   horizontal = horizontal-gogo; else horizontal = horizontal+gogo;


        gogo2 = this.block_height-block_height2;
      if(ymax>=19)  vertical = vertical - gogo2;



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
                break;

            case 2:
                horizontal = first_horizontal_position.get("regular_z");
                break;

            case 3:
                horizontal = first_horizontal_position.get("reverse_z");
                break;

            case 4:
                horizontal = first_horizontal_position.get("regular_L");
                break;

            case 5:
                horizontal = first_horizontal_position.get("reverse_L");
                break;
            case 6:
                horizontal = first_horizontal_position.get("T");
                break;

            case 7:
                horizontal = first_horizontal_position.get("square");
                break;

        }

          vertical=-1;  direction=1;

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
            System.out.println(e);
        }


        return thr;

    }

    public boolean dont_go_right2() throws Exception
    {


         boolean thr=true;

         try {
             for(int y=0;y<coords.length;y++) {


                 for (int x = 0; x < coords[0].length; x++) {


                     if(!record_blocks[y][x] && !coords[y][x-1])
                     {
                         thr = false;
                      //   System.out.println("dont go right 2");

                     }


                 }

             }

         }
            catch (Exception e)
            {
                System.out.println(e);
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



    public void direction2() throws Exception
    {

        direction();
        max_min();
       // direction();
         max_min2x();
        max_min2y();

        // max_min2x_dont_go();
        direction();

      //  max_min2();


    }

        public void direction() throws Exception
        {

        try {
            switch (direction)
            {
                case 1:
                    block_type_d1();
                    //horizontal
                    //      is_vertical_horizontal="horizontal";
                    break;

                case 2:
                    block_type_d2();
                    //vertical
                    //      is_vertical_horizontal="vertical";
                    break;

                case 3:
                    block_type_d3();
                    //horizontal
                    //      is_vertical_horizontal="horizontal";
                    break;

                case 4:
                    block_type_d4();
                    //vertical
                    //     is_vertical_horizontal="horizontal";
                    break;


            }








        }
        catch (Exception e)
        {
            System.out.println(e);
        }




        }




    public void block_type_d1() throws Exception
    {
        switch (block_type)
        {
            case 1:
                bar_d1();
                ghost_bar_d2();
                break;

            case 2:
                regular_z_d1();
                ghost_regular_z_d2();
                break;

            case 3:
                reverse_z_d1();
                ghost_reverse_z_d2();
                break;

            case 4:
                regular_L_d1();
                ghost_regular_L_d2();
                break;

            case 5:
                reverse_L_d1();
                ghost_reverse_L_d2();
                break;

            case 6:
               T_d1();
               ghost_T_d2();
                break;

            case 7:
                square();
                ghost_square();
                break;

        }
    }


    public void block_type_d2() throws Exception
    {
        switch (block_type)
        {
            case 1:
                bar_d2();
                ghost_bar_d1();
                break;

            case 2:
                regular_z_d2();
                ghost_regular_z_d1();
                break;

            case 3:
                reverse_z_d2();
                ghost_reverse_z_d1();
                break;

            case 4:
                regular_L_d2();
                ghost_regular_L_d3();
                break;


            case 5:
                reverse_L_d2();
                ghost_reverse_L_d3();
                break;

            case 6:
               T_d2();
               ghost_T_d3();
                break;

        }
    }


    public void block_type_d3() throws Exception
    {
        switch (block_type)
        {

            case 4:
                regular_L_d3();
                ghost_regular_L_d4();
                break;

            case 5:
                reverse_L_d3();
                ghost_reverse_L_d4();
                break;

            case 6:
               T_d3();
               ghost_T_d4();
                break;
        }
    }

    public void block_type_d4() throws Exception
    {
        switch (block_type)
        {

            case 4:
                regular_L_d4();
                ghost_regular_L_d1();
                break;

            case 5:
                reverse_L_d4();
                ghost_reverse_L_d1();
                break;

            case 6:
               T_d4();
               ghost_T_d1();
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

                    /*
                    if(x>=horizontal+1 && x<=horizontal+1 && y>=vertical &&  y<= vertical) coords[y][x]=false;
                else if(x>=horizontal && x<=horizontal+2 && y>=vertical+1 &&  y<= vertical+1) coords[y][x]=false;
                else  coords[y][x]=true;
                     */

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


     //   max_direction=3;

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


                if(x>=horizontal && x<=horizontal+3 && y>=vertical &&  y<= vertical) coords[y][x]=false;
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



                if(x>=horizontal+1 && x<=horizontal+1 && y>=vertical-1 &&  y<= vertical+3-1) coords[y][x]=false;
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

                if(x>=horizontal && x<=horizontal+2 && y>=vertical &&  y<= vertical) ghost1[y][x]=false;
                else if(x>=horizontal+1 && x<=horizontal+1 && y>=vertical+1 &&  y<= vertical+1) ghost1[y][x]=false;
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


                if(x>=horizontal+1 && x<=horizontal+1 && y>=vertical-1 &&  y<= vertical+1) ghost1[y][x]=false;
                else if(x>=horizontal && x<=horizontal && y>=vertical &&  y<= vertical) ghost1[y][x]=false;
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



                if(x>=horizontal+1 && x<=horizontal+1 && y>=vertical-1 &&  y<= vertical-1) ghost1[y][x]=false;
                else if(x>=horizontal && x<=horizontal+2 && y>=vertical &&  y<= vertical) ghost1[y][x]=false;
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

                if(x>=horizontal+2 && x<=horizontal+2 && y>=vertical &&  y<= vertical) ghost1[y][x]=false;
                else if(x>=horizontal+1 && x<=horizontal+1 && y>=vertical-1 &&  y<= vertical+1) ghost1[y][x]=false;
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


                if(x>=horizontal && x<=horizontal+2 && y>=vertical &&  y<= vertical) ghost1[y][x]=false;
                else if(x>=horizontal && x<=horizontal && y>=vertical+1 &&  y<= vertical+1) ghost1[y][x]=false;
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


                if(x>=horizontal+1 && x<=horizontal+1 && y>=vertical-1 &&  y<= vertical+1) ghost1[y][x]=false;
                else if(x>=horizontal && x<=horizontal && y>=vertical-1 &&  y<= vertical-1) ghost1[y][x]=false;
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


                if(x>=horizontal+2 && x<=horizontal+2 && y>=vertical-1 &&  y<= vertical-1) ghost1[y][x]=false;
                else if(x>=horizontal && x<=horizontal+2 && y>=vertical &&  y<= vertical) ghost1[y][x]=false;
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


                if(x>=horizontal+2 && x<=horizontal+2 && y>=vertical+1 &&  y<= vertical+1) ghost1[y][x]=false;
                else if(x>=horizontal+1 && x<=horizontal+1 && y>=vertical-1 &&  y<= vertical+1) ghost1[y][x]=false;
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

                if(x>=horizontal && x<=horizontal+2 && y>=vertical &&  y<= vertical) ghost1[y][x]=false;
                else if(x>=horizontal+2 && x<=horizontal+2 && y>=vertical+1 &&  y<= vertical+1) ghost1[y][x]=false;
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


                if(x>=horizontal+1 && x<=horizontal+1 && y>=vertical-1 &&  y<= vertical+1) ghost1[y][x]=false;
                else if(x>=horizontal && x<=horizontal && y>=vertical+1 &&  y<= vertical+1) ghost1[y][x]=false;
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


                if(x>=horizontal && x<=horizontal && y>=vertical-1 &&  y<= vertical-1) ghost1[y][x]=false;
                else if(x>=horizontal && x<=horizontal+2 && y>=vertical &&  y<= vertical) ghost1[y][x]=false;
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


                if(x>=horizontal+2 && x<=horizontal+2 && y>=vertical-1 &&  y<= vertical-1) ghost1[y][x]=false;
                else if(x>=horizontal+1 && x<=horizontal+1 && y>=vertical-1 &&  y<= vertical+1) ghost1[y][x]=false;
                else  ghost1[y][x]=true;
            }

        }

    }





    public  void ghost_reverse_z_d1() throws Exception
    {
        ghost_block_width=3;
        ghost_block_height=2;


        //   max_direction=3;

        for(int y=0;y<ghost1.length;y++) {

            for (int x = 0; x < ghost1[0].length; x++) {


                if(x>=horizontal+1 && x<=horizontal+2 && y>=vertical &&  y<= vertical) ghost1[y][x]=false;
                else if(x>=horizontal && x<=horizontal+1 && y>=vertical+1 &&  y<= vertical+1) ghost1[y][x]=false;
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



                if(x>=horizontal && x<=horizontal && y>=vertical &&  y<= vertical+1) ghost1[y][x]=false;
                else if(x>=horizontal+1 && x<=horizontal+1 && y>=vertical+1 &&  y<= vertical+2) ghost1[y][x]=false;
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


                if(x>=horizontal && x<=horizontal+1 && y>=vertical &&  y<= vertical) ghost1[y][x]=false;
                else if(x>=horizontal+1 && x<=horizontal+2 && y>=vertical+1 &&  y<= vertical+1) ghost1[y][x]=false;
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


                if(x>=horizontal && x<=horizontal && y>=vertical+1 &&  y<= vertical+2) ghost1[y][x]=false;
                else if(x>=horizontal+1 && x<=horizontal+1 && y>=vertical &&  y<= vertical+1) ghost1[y][x]=false;
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

                if(x>=horizontal && x<=horizontal+1 && y>=vertical &&  y<= vertical+1) ghost1[y][x]=false;
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


                if(x>=horizontal && x<=horizontal+3 && y>=vertical &&  y<= vertical) ghost1[y][x]=false;
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



                if(x>=horizontal+1 && x<=horizontal+1 && y>=vertical-1 &&  y<= vertical+3-1) ghost1[y][x]=false;
                else  ghost1[y][x]=true;
            }

        }




    }



}
