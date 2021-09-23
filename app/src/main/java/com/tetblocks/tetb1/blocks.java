package com.tetblocks.tetb1;

import android.util.Log;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.NavigableMap;
import java.util.Set;
import java.util.TreeMap;



public class blocks {

   protected int horizontal = 3, vertical = -1, max_direction = 5, block_type =1 ,direction=1,
           block_width=0, block_height=0, ghost_block_width=0, ghost_block_height=0,ghost_horizontal = 3, ghost_vertical = -1, gogo_x=0, gogo_y=0, gogo_y2=0;




    boolean coords[][] = new boolean[20][10], record_blocks [][] = new boolean[20][10], merge_blocks [][] = new boolean[20][10], ghost1 [][] = new boolean[20][10],
            ghost2 [][] = new boolean[20][10], ghost4=false;

    List <Integer> intersection_list_x = new ArrayList<>();
    List <Integer> intersection_list_y = new ArrayList<>();


    NavigableMap<String,Integer> max_direction2=new TreeMap();
   NavigableMap<String,Integer> first_horizontal_position= new TreeMap();
   // HashMap<Integer,Integer> x_y_record= new HashMap<>();

    NavigableMap<Integer,Integer> x_y_record= new TreeMap();



    blocks() {


    }


    public boolean ghost5(int gogox) throws Exception
    {


        boolean dont_go=false;



        int xmax=0, xmin=50, width=0, ymin=50, ymax=0;
       // int x_intersection=0, y_intersection=0, gogo_x=0, gogo_y=0;

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

        ghost4=false;

        int b_width=ghost_block_width-(xmax-xmin+1);




            for(int y=0;y<ghost1.length;y++) {

                for (int x = 0; x < ghost1[0].length; x++) {

                    try {

                        if (!ghost1[y][x] && !record_blocks[y][x-gogox])
                        {
                            dont_go = true;
                            ghost4=true;
                            System.out.println("dont go ghost5");
                        }


                    }
                    catch (Exception er)
                    {
                        System.out.println(er);
                    }


                }



        }








        return dont_go;
    }



    public boolean ghost3() throws Exception
    {

        int gogox=0, gogoy=0;
        boolean dont_go=false;

        /*
        gogo_y2
         if(ghost_block_width>ghost_block_height) gogox = gogo_x;
        else gogoy= gogo_y;
         */

        ghost4=false;

        for(int y=0;y<ghost1.length;y++) {

            for (int x = 0; x < ghost1[0].length; x++) {

                try {

                    if (!ghost1[y][x] && !record_blocks[y-gogo_y2][x])
                    {
                        dont_go = true;
                        ghost4=true;
                        System.out.println("dont go ghost3");
                    }

                    //  if(y+gogoy>19) dont_go2 =true;


                }
                catch (Exception er)
                {
                    System.out.println(er);
                }


            }

        }


        return dont_go;
    }



    public boolean ghost2() throws Exception
    {

        ghost4=false;
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
                      ghost4=true;
                      System.out.println("dont go feweew");
                  }

                //  if(y+gogoy>19) dont_go2 =true;


                }
                catch (Exception er)
                {
                    System.out.println(er);
                }


            }

        }


        return dont_go;
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



    }






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

       // gogo_y = ghost_block_height-(ymax-ymin+1);


      //  if(xmax==9)   gogo_x*=-1;

       // if(xmin==0)  ghost_horizontal +=gogo_x;





       if(xmax==9)   ghost_horizontal -=gogo_x;
       if(xmin==0)  ghost_horizontal +=gogo_x;


           // ghost_horizontal +=gogo_x;








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



       // if(ymax==19)   vertical -=gogo_y;




          if(!ghost3())if(ymax==19)

        {
            ghost_vertical -=gogo_y2;
            vertical=ghost_vertical;
        }


/*
 ghost_vertical -=gogo_y2;
        vertical=ghost_vertical;
 */







        /*
        if(ghost_max_min_control()) ghost_vertical=vertical;
        else vertical=ghost_vertical;
         */

     // vertical=ghost_vertical;

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



       // ghost2();




                 if(!ghost2())
             {

                 if(ghost_block_width>ghost_block_height) ghost_horizontal += gogo_x;
                 else ghost_vertical += gogo_y;

             }






      //  ghost2();



    //  horizontal = ghost_horizontal;
        // vertical=ghost_vertical;



        /*
         horizontal = ghost_horizontal;
        vertical=ghost_vertical;
         */




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


          ghost_horizontal=horizontal;
          ghost_vertical=vertical;


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
