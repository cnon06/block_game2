package com.tetblocks.tetb1;

import android.util.Log;

public class blocks {

    int first = 3, vertical = 0, max_direction = 3, block_type = 1 ,direction=2;
    boolean coords[][] = new boolean[20][10], record_blocks [][] = new boolean[20][10];
    boolean direction_control = true;


    blocks() {
        Log.d("", "Class works");


    }


    public void record_blocks_2()
    {
        for(int y=0;y<coords.length;y++) {

            for (int x = 0; x < coords[0].length; x++) {


                if(!coords[y][x] && !record_blocks [y+1][x])
                {
                    record_blocks [y][x] = coords[y][x] ;
                }


            }

        }
    }

    public void calibrate_record_blocks() {
        for(int y=0;y<coords.length;y++) {

            for (int x = 0; x < coords[0].length; x++) {



                    record_blocks [y][x] = true ;



            }

        }
    }

    public void record_blocks() {
        for(int y=0;y<coords.length;y++) {

            for (int x = 0; x < coords[0].length; x++) {


                if(!coords[y][x])
                {
                     record_blocks [y][x] = coords[y][x] ;
                }


            }

        }
    }

    public void horizontal_calibrate_blocks() {


            direction=1;
        if (block_type > 7) block_type = 0;
        vertical = -1;

        switch (block_type) {

            case 1:
                case 2:
            case 3:
            case 4:
            case 5:
            case 6:

                first = 3;
                break;


            case 7:
                first = 4;
                break;



        }
    }




    public void direction_right_left_block_control()

    {


            switch (block_type)
            {
                case 1:

                    switch (direction)
                    {
                        case 1:
                            if(first==-1) first++;
                            if(first==8) first=first-2;
                            if(first==7) first=first-1;
                            break;


                    }


                    break;

                case 2: case 3:
                    switch (direction)
                    {
                        case 1:
                           // if(first==-1) first++;
                            if(first==8) first=first-1;
                          //  if(first==7) first=first-1;
                            break;

                    }
                    break;

                case 4: case 5: case 6:
                switch (direction)
                {

                    case 1:
                        // if(first==-1) first++;

                        if(first==-1) first++;
                        //  if(first==7) first=first-1;
                        break;

                    case 3:
                        // if(first==-1) first++;
                        if(first==8) first=first-1;
                        if(first==-1) first++;
                        //  if(first==7) first=first-1;
                        break;

                    case 4:
                        // if(first==-1) first++;

                        if(first==-1) first++;
                        //  if(first==7) first=first-1;
                        break;

                }
                break;


                default:

                    break;
            }



    }




        public void right_left_block_control(boolean right_left)
    {
        boolean dont_move_to_right = true, dont_move_to_left = true;

        for (int y = 0; y < coords.length; y++) {

            for (int x = 0; x < coords[0].length; x++) {


                if (coords[y][x] == false) {
                    if (x < 1) dont_move_to_right = false;
                    if (x > 8) dont_move_to_left = false;

                }


            }

        }

    if(dont_move_to_right && right_left) first--;
    if(dont_move_to_left && !right_left) first++;


    }



    public void up_down_block_control()
    {
        for(int y=0;y<coords.length;y++) {

            for (int x = 0; x < coords[0].length; x++) {



                if(coords[y][x]==false)
                {

                    switch (block_type)
                    {

                        case 1:
                        if(y<=0) vertical=1;
                        if(y>17) vertical=17;
                        break;

                        case 2: case 3:
                            if(y<=0) vertical=0;
                            if(y>18) vertical=17;
                            break;

                        case 4: case 5: case 6:
                            if(y<=0) vertical=1;
                            if(y>18) vertical=18;
                            break;


                    }


                }

            }

        }
    }





    public void block_type_d1()
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
                regular_T_d1();
                break;

            case 7:
                square();
                break;

        }
    }


    public void block_type_d2()
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
                regular_T_d2();
                break;

        }
    }


    public void block_type_d3()
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
                regular_T_d3();
                break;
        }
    }

    public void block_type_d4()
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
                regular_T_d4();
                break;

        }
    }



    public  void regular_T_d1()
    {
        direction_control=true;
       // max_right=7;
        max_direction=5;


        for(int y=0;y<coords.length;y++) {

            for (int x = 0; x < coords[0].length; x++) {

                if(x>=first && x<=first+2 && y>=vertical &&  y<= vertical) coords[y][x]=false;
                else if(x>=first+1 && x<=first+1 && y>=vertical+1 &&  y<= vertical+1) coords[y][x]=false;
                else  coords[y][x]=true;
            }

        }

    }


    public  void regular_T_d2()
    {
        direction_control=true;
      // max_right=8;
        max_direction=5;

        for(int y=0;y<coords.length;y++) {

            for (int x = 0; x < coords[0].length; x++) {


                if(x>=first+1 && x<=first+1 && y>=vertical-1 &&  y<= vertical+1) coords[y][x]=false;
                else if(x>=first && x<=first && y>=vertical &&  y<= vertical) coords[y][x]=false;
                else  coords[y][x]=true;
            }

        }

    }

    public  void regular_T_d3()
    {
        direction_control=true;
        // max_right=7;
        max_direction=5;

        for(int y=0;y<coords.length;y++) {

            for (int x = 0; x < coords[0].length; x++) {


                if(x>=first+1 && x<=first+1 && y>=vertical &&  y<= vertical) coords[y][x]=false;
                else if(x>=first && x<=first+2 && y>=vertical+1 &&  y<= vertical+1) coords[y][x]=false;
                else  coords[y][x]=true;
            }

        }

    }


    public  void regular_T_d4()
    {

        direction_control=false;

        max_direction=5;


        for(int y=0;y<coords.length;y++) {

            for (int x = 0; x < coords[0].length; x++) {

                if(x>=first+2 && x<=first+2 && y>=vertical &&  y<= vertical) coords[y][x]=false;
                else if(x>=first+1 && x<=first+1 && y>=vertical-1 &&  y<= vertical+1) coords[y][x]=false;
                else  coords[y][x]=true;
            }

        }

    }


    public  void reverse_L_d1()
    {
        direction_control=true;

        max_direction=5;


        for(int y=0;y<coords.length;y++) {

            for (int x = 0; x < coords[0].length; x++) {


                if(x>=first && x<=first+2 && y>=vertical &&  y<= vertical) coords[y][x]=false;
                else if(x>=first && x<=first && y>=vertical+1 &&  y<= vertical+1) coords[y][x]=false;
                else  coords[y][x]=true;
            }

        }

    }

    public  void reverse_L_d2()
    {
        direction_control=true;

        max_direction=5;

        for(int y=0;y<coords.length;y++) {

            for (int x = 0; x < coords[0].length; x++) {


                if(x>=first+1 && x<=first+1 && y>=vertical-1 &&  y<= vertical+1) coords[y][x]=false;
                else if(x>=first && x<=first && y>=vertical-1 &&  y<= vertical-1) coords[y][x]=false;
                else  coords[y][x]=true;
            }

        }

    }


    public  void reverse_L_d3()
    {
        direction_control=true;

        max_direction=5;

        for(int y=0;y<coords.length;y++) {

            for (int x = 0; x < coords[0].length; x++) {


                if(x>=first+2 && x<=first+2 && y>=vertical-1 &&  y<= vertical-1) coords[y][x]=false;
                else if(x>=first && x<=first+2 && y>=vertical &&  y<= vertical) coords[y][x]=false;
                else  coords[y][x]=true;
            }

        }

    }

    public  void reverse_L_d4()
    {

        direction_control=false;

        max_direction=5;


        for(int y=0;y<coords.length;y++) {

            for (int x = 0; x < coords[0].length; x++) {


                if(x>=first+2 && x<=first+2 && y>=vertical+1 &&  y<= vertical+1) coords[y][x]=false;
                else if(x>=first+1 && x<=first+1 && y>=vertical-1 &&  y<= vertical+1) coords[y][x]=false;
                else  coords[y][x]=true;
            }

        }

    }



    public  void regular_L_d1()
    {
        direction_control=true;
       // max_right=7;
        max_direction=5;


        for(int y=0;y<coords.length;y++) {

            for (int x = 0; x < coords[0].length; x++) {

                if(x>=first && x<=first+2 && y>=vertical &&  y<= vertical) coords[y][x]=false;
                else if(x>=first+2 && x<=first+2 && y>=vertical+1 &&  y<= vertical+1) coords[y][x]=false;
                else  coords[y][x]=true;
            }

        }

    }


    public  void regular_L_d2()
    {
        direction_control=true;
       // max_right=8;
        max_direction=5;

        for(int y=0;y<coords.length;y++) {

            for (int x = 0; x < coords[0].length; x++) {


                if(x>=first+1 && x<=first+1 && y>=vertical-1 &&  y<= vertical+1) coords[y][x]=false;
                else if(x>=first && x<=first && y>=vertical+1 &&  y<= vertical+1) coords[y][x]=false;
                else  coords[y][x]=true;
            }

        }

    }





    public  void regular_L_d3()
    {
        direction_control=true;
       // max_right=7;
        max_direction=5;

        for(int y=0;y<coords.length;y++) {

            for (int x = 0; x < coords[0].length; x++) {


                if(x>=first && x<=first && y>=vertical-1 &&  y<= vertical-1) coords[y][x]=false;
                else if(x>=first && x<=first+2 && y>=vertical &&  y<= vertical) coords[y][x]=false;
                else  coords[y][x]=true;
            }

        }

    }

    public  void regular_L_d4()
    {

        direction_control=false;

        max_direction=5;


        for(int y=0;y<coords.length;y++) {

            for (int x = 0; x < coords[0].length; x++) {


                if(x>=first+2 && x<=first+2 && y>=vertical-1 &&  y<= vertical-1) coords[y][x]=false;
                else if(x>=first+1 && x<=first+1 && y>=vertical-1 &&  y<= vertical+1) coords[y][x]=false;
                else  coords[y][x]=true;
            }

        }

    }





    public  void reverse_z_d1()
    {



        max_direction=3;

        for(int y=0;y<coords.length;y++) {

            for (int x = 0; x < coords[0].length; x++) {


                if(x>=first+1 && x<=first+2 && y>=vertical &&  y<= vertical) coords[y][x]=false;
                else if(x>=first && x<=first+1 && y>=vertical+1 &&  y<= vertical+1) coords[y][x]=false;
                else  coords[y][x]=true;
            }

        }

    }

    public  void reverse_z_d2()
    {

        max_direction=3;

        for(int y=0;y<coords.length;y++) {

            for (int x = 0; x < coords[0].length; x++) {



                if(x>=first && x<=first && y>=vertical &&  y<= vertical+1) coords[y][x]=false;
                else if(x>=first+1 && x<=first+1 && y>=vertical+1 &&  y<= vertical+2) coords[y][x]=false;
                else  coords[y][x]=true;
            }
        }




    }


    public  void regular_z_d1()
    {

        max_direction=3;

        for(int y=0;y<coords.length;y++) {

            for (int x = 0; x < coords[0].length; x++) {


                if(x>=first && x<=first+1 && y>=vertical &&  y<= vertical) coords[y][x]=false;
                else if(x>=first+1 && x<=first+2 && y>=vertical+1 &&  y<= vertical+1) coords[y][x]=false;
                else  coords[y][x]=true;
            }
        }

    }

    public  void regular_z_d2()
    {



        max_direction=3;

        for(int y=0;y<coords.length;y++) {

            for (int x = 0; x < coords[0].length; x++) {


                if(x>=first && x<=first && y>=vertical+1 &&  y<= vertical+2) coords[y][x]=false;
                else if(x>=first+1 && x<=first+1 && y>=vertical &&  y<= vertical+1) coords[y][x]=false;
                else  coords[y][x]=true;
            }
        }

    }


    public  void square()
    {

        //max_right=9;


        max_direction=2;

        for(int y=0;y<coords.length;y++) {

            for (int x = 0; x < coords[0].length; x++) {

                if(x>=first && x<=first+1 && y>=vertical &&  y<= vertical+1) coords[y][x]=false;
                else  coords[y][x]=true;
            }

        }

    }





    public  void bar_d1()
    {


        direction_control=true;
        //max_right=6;

        max_direction=3;

        for(int y=0;y<coords.length;y++) {

            for (int x = 0; x < coords[0].length; x++) {


                if(x>=first && x<=first+3 && y>=vertical &&  y<= vertical) coords[y][x]=false;
                else  coords[y][x]=true;
            }

        }



    }



    public  void bar_d2()
    {
        direction_control=false;
        //max_right=8;
        max_direction=3;



        for(int y=0;y<coords.length;y++) {

            for (int x = 0; x < coords[0].length; x++) {



                if(x>=first+1 && x<=first+1 && y>=vertical-1 &&  y<= vertical+3-1) coords[y][x]=false;
                else  coords[y][x]=true;
            }

        }

    }



}
