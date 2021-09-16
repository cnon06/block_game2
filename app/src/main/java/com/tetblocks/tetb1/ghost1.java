package com.tetblocks.tetb1;

import java.util.NavigableMap;
import java.util.TreeMap;

public class ghost1 {

    int vertical=0, horizontal=0;
    int  block_width=0, block_height=2, direction=2, block_type=1;
    boolean  ghost_blocks1 [][] = new boolean[20][10];
    blocks blcks = new blocks();
    NavigableMap<String,Integer> first_horizontal_position= new TreeMap();



    ghost1()
    {
        for(int y=0;y<ghost_blocks1.length;y++) {

            for (int x = 0; x < ghost_blocks1[0].length; x++) {

                 ghost_blocks1[y][x]=true;
            }

        }


        try {
            first_horizontal_position_list();
        }
        catch (Exception e)
        {
            System.out.println(e);
        }


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


        //record_blocks();

        /*
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
         */


        vertical=1;  direction=1; horizontal=3;
        bar_d2();
        System.out.println("ghst first horizontal");

    }


    /*


    public void restart_blocks() throws Exception
    {

        boolean rty=false;

        for(int y=0;y<ghost_blocks1.length;y++) {



            for (int x = 0; x < ghost_blocks1[0].length; x++) {

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

        if(rty) first_horizontal_position();

        // if(max_min_ymin==check_record_blocks_ymax+1) first_horizontal_position();

    }

     */


    public void direction() throws Exception
    {




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

        for(int y=0;y<ghost_blocks1.length;y++) {

            for (int x = 0; x < ghost_blocks1[0].length; x++) {

                if(x>=blcks.horizontal && x<=blcks.horizontal+2 && y>=blcks.vertical &&  y<= blcks.vertical) ghost_blocks1[y][x]=false;
                else if(x>=blcks.horizontal+1 && x<=blcks.horizontal+1 && y>=blcks.vertical+1 &&  y<= blcks.vertical+1) ghost_blocks1[y][x]=false;
                else  ghost_blocks1[y][x]=true;
            }

        }

    }


    public  void T_d2() throws Exception
    {


        block_width=2;
        block_height=3;

        for(int y=0;y<ghost_blocks1.length;y++) {

            for (int x = 0; x < ghost_blocks1[0].length; x++) {


                if(x>=blcks.horizontal+1 && x<=blcks.horizontal+1 && y>=blcks.vertical-1 &&  y<= blcks.vertical+1) ghost_blocks1[y][x]=false;
                else if(x>=blcks.horizontal && x<=blcks.horizontal && y>=blcks.vertical &&  y<= blcks.vertical) ghost_blocks1[y][x]=false;
                else  ghost_blocks1[y][x]=true;
            }

        }

    }

    public  void T_d3() throws Exception
    {

        block_width=3;
        block_height=2;

        for(int y=0;y<ghost_blocks1.length;y++) {

            for (int x = 0; x < ghost_blocks1[0].length; x++) {


                if(x>=blcks.horizontal+1 && x<=blcks.horizontal+1 && y>=blcks.vertical &&  y<= blcks.vertical) ghost_blocks1[y][x]=false;
                else if(x>=blcks.horizontal && x<=blcks.horizontal+2 && y>=blcks.vertical+1 &&  y<= blcks.vertical+1) ghost_blocks1[y][x]=false;
                else  ghost_blocks1[y][x]=true;
            }

        }

    }


    public  void T_d4() throws Exception
    {
        block_width=2;
        block_height=3;



        for(int y=0;y<ghost_blocks1.length;y++) {

            for (int x = 0; x < ghost_blocks1[0].length; x++) {

                if(x>=blcks.horizontal+2 && x<=blcks.horizontal+2 && y>=blcks.vertical &&  y<= blcks.vertical) ghost_blocks1[y][x]=false;
                else if(x>=blcks.horizontal+1 && x<=blcks.horizontal+1 && y>=blcks.vertical-1 &&  y<= blcks.vertical+1) ghost_blocks1[y][x]=false;
                else  ghost_blocks1[y][x]=true;
            }

        }

    }


    public  void reverse_L_d1() throws Exception
    {

        block_width = 3;
        block_height=2;


        for(int y=0;y<ghost_blocks1.length;y++) {

            for (int x = 0; x < ghost_blocks1[0].length; x++) {


                if(x>=blcks.horizontal && x<=blcks.horizontal+2 && y>=blcks.vertical &&  y<= blcks.vertical) ghost_blocks1[y][x]=false;
                else if(x>=blcks.horizontal && x<=blcks.horizontal && y>=blcks.vertical+1 &&  y<= blcks.vertical+1) ghost_blocks1[y][x]=false;
                else  ghost_blocks1[y][x]=true;
            }

        }

    }

    public  void reverse_L_d2() throws Exception
    {

        block_width = 2;
        block_height=3;

        for(int y=0;y<ghost_blocks1.length;y++) {

            for (int x = 0; x < ghost_blocks1[0].length; x++) {


                if(x>=blcks.horizontal+1 && x<=blcks.horizontal+1 && y>=blcks.vertical-1 &&  y<= blcks.vertical+1) ghost_blocks1[y][x]=false;
                else if(x>=blcks.horizontal && x<=blcks.horizontal && y>=blcks.vertical-1 &&  y<= blcks.vertical-1) ghost_blocks1[y][x]=false;
                else  ghost_blocks1[y][x]=true;
            }

        }

    }


    public  void reverse_L_d3() throws Exception
    {

        block_width = 3;
        block_height=2;

        for(int y=0;y<ghost_blocks1.length;y++) {

            for (int x = 0; x < ghost_blocks1[0].length; x++) {


                if(x>=blcks.horizontal+2 && x<=blcks.horizontal+2 && y>=blcks.vertical-1 &&  y<= blcks.vertical-1) ghost_blocks1[y][x]=false;
                else if(x>=blcks.horizontal && x<=blcks.horizontal+2 && y>=blcks.vertical &&  y<= blcks.vertical) ghost_blocks1[y][x]=false;
                else  ghost_blocks1[y][x]=true;
            }

        }

    }

    public  void reverse_L_d4() throws Exception
    {

        block_width = 2;
        block_height=3;

        for(int y=0;y<ghost_blocks1.length;y++) {

            for (int x = 0; x < ghost_blocks1[0].length; x++) {


                if(x>=blcks.horizontal+2 && x<=blcks.horizontal+2 && y>=blcks.vertical+1 &&  y<= blcks.vertical+1) ghost_blocks1[y][x]=false;
                else if(x>=blcks.horizontal+1 && x<=blcks.horizontal+1 && y>=blcks.vertical-1 &&  y<= blcks.vertical+1) ghost_blocks1[y][x]=false;
                else  ghost_blocks1[y][x]=true;
            }

        }

    }



    public  void regular_L_d1() throws Exception
    {


        block_width = 3;
        block_height=2;


        for(int y=0;y<ghost_blocks1.length;y++) {

            for (int x = 0; x < ghost_blocks1[0].length; x++) {

                if(x>=blcks.horizontal && x<=blcks.horizontal+2 && y>=blcks.vertical &&  y<= blcks.vertical) ghost_blocks1[y][x]=false;
                else if(x>=blcks.horizontal+2 && x<=blcks.horizontal+2 && y>=blcks.vertical+1 &&  y<= blcks.vertical+1) ghost_blocks1[y][x]=false;
                else  ghost_blocks1[y][x]=true;
            }

        }

    }


    public  void regular_L_d2() throws Exception
    {

        block_width = 2;
        block_height=3;

        for(int y=0;y<ghost_blocks1.length;y++) {

            for (int x = 0; x < ghost_blocks1[0].length; x++) {


                if(x>=blcks.horizontal+1 && x<=blcks.horizontal+1 && y>=blcks.vertical-1 &&  y<= blcks.vertical+1) ghost_blocks1[y][x]=false;
                else if(x>=blcks.horizontal && x<=blcks.horizontal && y>=blcks.vertical+1 &&  y<= blcks.vertical+1) ghost_blocks1[y][x]=false;
                else  ghost_blocks1[y][x]=true;
            }

        }

    }





    public  void regular_L_d3() throws Exception
    {

        block_width = 3;
        block_height=2;

        for(int y=0;y<ghost_blocks1.length;y++) {

            for (int x = 0; x < ghost_blocks1[0].length; x++) {


                if(x>=blcks.horizontal && x<=blcks.horizontal && y>=blcks.vertical-1 &&  y<= blcks.vertical-1) ghost_blocks1[y][x]=false;
                else if(x>=blcks.horizontal && x<=blcks.horizontal+2 && y>=blcks.vertical &&  y<= blcks.vertical) ghost_blocks1[y][x]=false;
                else  ghost_blocks1[y][x]=true;
            }

        }

    }

    public  void regular_L_d4() throws Exception
    {

        block_width = 2;
        block_height=3;


        for(int y=0;y<ghost_blocks1.length;y++) {

            for (int x = 0; x < ghost_blocks1[0].length; x++) {


                if(x>=blcks.horizontal+2 && x<=blcks.horizontal+2 && y>=blcks.vertical-1 &&  y<= blcks.vertical-1) ghost_blocks1[y][x]=false;
                else if(x>=blcks.horizontal+1 && x<=blcks.horizontal+1 && y>=blcks.vertical-1 &&  y<= blcks.vertical+1) ghost_blocks1[y][x]=false;
                else  ghost_blocks1[y][x]=true;
            }

        }

    }





    public  void reverse_z_d1() throws Exception
    {
        block_width=3;
        block_height=2;


        //   max_direction=3;

        for(int y=0;y<ghost_blocks1.length;y++) {

            for (int x = 0; x < ghost_blocks1[0].length; x++) {


                if(x>=blcks.horizontal+1 && x<=blcks.horizontal+2 && y>=blcks.vertical &&  y<= blcks.vertical) ghost_blocks1[y][x]=false;
                else if(x>=blcks.horizontal && x<=blcks.horizontal+1 && y>=blcks.vertical+1 &&  y<= blcks.vertical+1) ghost_blocks1[y][x]=false;
                else  ghost_blocks1[y][x]=true;
            }

        }

    }

    public  void reverse_z_d2() throws Exception
    {
        block_width=2;
        block_height=3;


        for(int y=0;y<ghost_blocks1.length;y++) {

            for (int x = 0; x < ghost_blocks1[0].length; x++) {



                if(x>=blcks.horizontal && x<=blcks.horizontal && y>=blcks.vertical &&  y<= blcks.vertical+1) ghost_blocks1[y][x]=false;
                else if(x>=blcks.horizontal+1 && x<=blcks.horizontal+1 && y>=blcks.vertical+1 &&  y<= blcks.vertical+2) ghost_blocks1[y][x]=false;
                else  ghost_blocks1[y][x]=true;
            }
        }




    }


    public  void regular_z_d1() throws Exception
    {

        block_width=3;
        block_height=2;



        for(int y=0;y<ghost_blocks1.length;y++) {

            for (int x = 0; x < ghost_blocks1[0].length; x++) {


                if(x>=blcks.horizontal && x<=blcks.horizontal+1 && y>=blcks.vertical &&  y<= blcks.vertical) ghost_blocks1[y][x]=false;
                else if(x>=blcks.horizontal+1 && x<=blcks.horizontal+2 && y>=blcks.vertical+1 &&  y<= blcks.vertical+1) ghost_blocks1[y][x]=false;
                else  ghost_blocks1[y][x]=true;
            }
        }

    }

    public  void regular_z_d2() throws Exception
    {

        block_width=2;
        block_height=3;



        for(int y=0;y<ghost_blocks1.length;y++) {

            for (int x = 0; x < ghost_blocks1[0].length; x++) {


                if(x>=blcks.horizontal && x<=blcks.horizontal && y>=blcks.vertical+1 &&  y<= blcks.vertical+2) ghost_blocks1[y][x]=false;
                else if(x>=blcks.horizontal+1 && x<=blcks.horizontal+1 && y>=blcks.vertical &&  y<= blcks.vertical+1) ghost_blocks1[y][x]=false;
                else  ghost_blocks1[y][x]=true;
            }
        }

    }


    public  void square() throws  Exception
    {


        block_width=2;
        block_height=2;


        for(int y=0;y<ghost_blocks1.length;y++) {

            for (int x = 0; x < ghost_blocks1[0].length; x++) {

                if(x>=blcks.horizontal && x<=blcks.horizontal+1 && y>=blcks.vertical &&  y<= blcks.vertical+1) ghost_blocks1[y][x]=false;
                else  ghost_blocks1[y][x]=true;
            }

        }

    }





    public  void bar_d1() throws Exception
    {


        block_width=4;
        block_height=1;



        for(int y=0;y<ghost_blocks1.length;y++) {

            for (int x = 0; x < ghost_blocks1[0].length; x++) {


                if(x>=blcks.horizontal && x<=blcks.horizontal+3 && y>=blcks.vertical &&  y<= blcks.vertical) ghost_blocks1[y][x]=false;
                else  ghost_blocks1[y][x]=true;
            }

        }




    }



    public  void bar_d2() throws Exception
    {

        block_width=1;
        block_height=4;


        for(int y=0;y<ghost_blocks1.length;y++) {

            for (int x = 0; x < ghost_blocks1[0].length; x++) {



                if(x>=blcks.horizontal+1 && x<=blcks.horizontal+1 && y>=vertical-1 &&  y<= vertical+3-1) ghost_blocks1[y][x]=false;
                else  ghost_blocks1[y][x]=true;
            }

        }




    }






    
}
