package com.tetblocks.tetb1;

import android.util.Log;

public class blocks



{


    int first=3, vertical=0, max_right=6, max_direction=3, block_type=4;
    boolean coords [][]  = new boolean[20][10];
    boolean direction_control=true;

blocks()
{
    Log.d("","Class works");




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
        max_right=7; max_direction=5;


        for(int y=0;y<coords.length;y++) {

            for (int x = 0; x < coords[0].length; x++) {

                if(first>max_right) first--;
                if(first<0) first++;

                if(x>=first && x<=first+2 && y>=vertical &&  y<= vertical) coords[y][x]=false;
                else if(x>=first+1 && x<=first+1 && y>=vertical+1 &&  y<= vertical+1) coords[y][x]=false;
                else  coords[y][x]=true;
            }

        }

    }


    public  void regular_T_d2()
    {
        direction_control=true;
        max_right=8; max_direction=5;

        for(int y=0;y<coords.length;y++) {

            for (int x = 0; x < coords[0].length; x++) {


                if(first>max_right) first--;
                if(first<0) first++;

                if(x>=first+1 && x<=first+1 && y>=vertical-1 &&  y<= vertical+1) coords[y][x]=false;
                else if(x>=first && x<=first && y>=vertical &&  y<= vertical) coords[y][x]=false;
                else  coords[y][x]=true;
            }

        }

    }

    public  void regular_T_d3()
    {
        direction_control=true;
        max_right=7; max_direction=5;

        for(int y=0;y<coords.length;y++) {

            for (int x = 0; x < coords[0].length; x++) {

                if(first>max_right) first--;
                if(first<0) first++;


                if(x>=first+1 && x<=first+1 && y>=vertical &&  y<= vertical) coords[y][x]=false;
                else if(x>=first && x<=first+2 && y>=vertical+1 &&  y<= vertical+1) coords[y][x]=false;
                else  coords[y][x]=true;
            }

        }

    }


    public  void regular_T_d4()
    {

        direction_control=false;
        max_right=7; max_direction=5;


        for(int y=0;y<coords.length;y++) {

            for (int x = 0; x < coords[0].length; x++) {


                if(first>max_right) first--;
                if(first<-1) first++;

                if(x>=first+2 && x<=first+2 && y>=vertical &&  y<= vertical) coords[y][x]=false;
                else if(x>=first+1 && x<=first+1 && y>=vertical-1 &&  y<= vertical+1) coords[y][x]=false;
                else  coords[y][x]=true;
            }

        }

    }


    public  void reverse_L_d1()
    {
        direction_control=true;
        max_right=7; max_direction=5;


        for(int y=0;y<coords.length;y++) {

            for (int x = 0; x < coords[0].length; x++) {

                if(first>max_right) first--;
                if(first<0) first++;

                if(x>=first && x<=first+2 && y>=vertical &&  y<= vertical) coords[y][x]=false;
                else if(x>=first && x<=first && y>=vertical+1 &&  y<= vertical+1) coords[y][x]=false;
                else  coords[y][x]=true;
            }

        }

    }

    public  void reverse_L_d2()
    {
        direction_control=true;
        max_right=8; max_direction=5;

        for(int y=0;y<coords.length;y++) {

            for (int x = 0; x < coords[0].length; x++) {


                if(first>max_right) first--;
                if(first<0) first++;

                if(x>=first+1 && x<=first+1 && y>=vertical-1 &&  y<= vertical+1) coords[y][x]=false;
                else if(x>=first && x<=first && y>=vertical-1 &&  y<= vertical-1) coords[y][x]=false;
                else  coords[y][x]=true;
            }

        }

    }


    public  void reverse_L_d3()
    {
        direction_control=true;
        max_right=7; max_direction=5;

        for(int y=0;y<coords.length;y++) {

            for (int x = 0; x < coords[0].length; x++) {

                if(first>max_right) first--;
                if(first<0) first++;


                if(x>=first+2 && x<=first+2 && y>=vertical-1 &&  y<= vertical-1) coords[y][x]=false;
                else if(x>=first && x<=first+2 && y>=vertical &&  y<= vertical) coords[y][x]=false;
                else  coords[y][x]=true;
            }

        }

    }

    public  void reverse_L_d4()
    {

        direction_control=false;
        max_right=7; max_direction=5;


        for(int y=0;y<coords.length;y++) {

            for (int x = 0; x < coords[0].length; x++) {


                if(first>max_right) first--;
                if(first<-1) first++;

                if(x>=first+2 && x<=first+2 && y>=vertical+1 &&  y<= vertical+1) coords[y][x]=false;
                else if(x>=first+1 && x<=first+1 && y>=vertical-1 &&  y<= vertical+1) coords[y][x]=false;
                else  coords[y][x]=true;
            }

        }

    }



    public  void regular_L_d1()
    {
        direction_control=true;
        max_right=7; max_direction=5;


        for(int y=0;y<coords.length;y++) {

            for (int x = 0; x < coords[0].length; x++) {

                if(first>max_right) first--;
                if(first<0) first++;


                if(x>=first && x<=first+2 && y>=vertical &&  y<= vertical) coords[y][x]=false;
                else if(x>=first+2 && x<=first+2 && y>=vertical+1 &&  y<= vertical+1) coords[y][x]=false;
                else  coords[y][x]=true;
            }

        }

    }


    public  void regular_L_d2()
    {
        direction_control=true;
        max_right=8; max_direction=5;

        for(int y=0;y<coords.length;y++) {

            for (int x = 0; x < coords[0].length; x++) {


                if(first>max_right) first--;
                if(first<0) first++;


                if(x>=first+1 && x<=first+1 && y>=vertical-1 &&  y<= vertical+1) coords[y][x]=false;
                else if(x>=first && x<=first && y>=vertical+1 &&  y<= vertical+1) coords[y][x]=false;
                else  coords[y][x]=true;
            }

        }

    }





    public  void regular_L_d3()
    {
        direction_control=true;
        max_right=7; max_direction=5;

        for(int y=0;y<coords.length;y++) {

            for (int x = 0; x < coords[0].length; x++) {

                if(first>max_right) first--;
                if(first<0) first++;


                if(x>=first && x<=first && y>=vertical-1 &&  y<= vertical-1) coords[y][x]=false;
                else if(x>=first && x<=first+2 && y>=vertical &&  y<= vertical) coords[y][x]=false;
                else  coords[y][x]=true;
            }

        }

    }

    public  void regular_L_d4()
    {

        direction_control=false;
        max_right=7; max_direction=5;


        for(int y=0;y<coords.length;y++) {

            for (int x = 0; x < coords[0].length; x++) {


                if(first>max_right) first--;
                if(first<-1) first++;

                if(x>=first+2 && x<=first+2 && y>=vertical-1 &&  y<= vertical-1) coords[y][x]=false;
                else if(x>=first+1 && x<=first+1 && y>=vertical-1 &&  y<= vertical+1) coords[y][x]=false;
                else  coords[y][x]=true;
            }

        }

    }







    public  void reverse_z_d1()
    {

        max_right=7; max_direction=3;

        for(int y=0;y<coords.length;y++) {

            for (int x = 0; x < coords[0].length; x++) {

                if(first>max_right) first--;

                if(x>=first+1 && x<=first+2 && y>=vertical &&  y<= vertical) coords[y][x]=false;
                else if(x>=first && x<=first+1 && y>=vertical+1 &&  y<= vertical+1) coords[y][x]=false;
                else  coords[y][x]=true;
            }

        }

    }

    public  void reverse_z_d2()
    {


        max_right=8; max_direction=3;

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
        max_right=7; max_direction=3;

        for(int y=0;y<coords.length;y++) {

            for (int x = 0; x < coords[0].length; x++) {


                if(first>max_right) first--;

                if(x>=first && x<=first+1 && y>=vertical &&  y<= vertical) coords[y][x]=false;
                else if(x>=first+1 && x<=first+2 && y>=vertical+1 &&  y<= vertical+1) coords[y][x]=false;
                else  coords[y][x]=true;
            }
        }

    }

    public  void regular_z_d2()
    {
        max_right=8; max_direction=3;

        for(int y=0;y<coords.length;y++) {

            for (int x = 0; x < coords[0].length; x++) {

                if(vertical<1) vertical++;
                if(vertical>17) vertical--;


                if(x>=first && x<=first && y>=vertical+1 &&  y<= vertical+2) coords[y][x]=false;
                else if(x>=first+1 && x<=first+1 && y>=vertical &&  y<= vertical+1) coords[y][x]=false;
                else  coords[y][x]=true;
            }
        }

    }





    public  void square()
    {

        max_right=8; max_direction=2;

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
        max_right=6; max_direction=3;

        for(int y=0;y<coords.length;y++) {

            for (int x = 0; x < coords[0].length; x++) {

                if(first>max_right) first--;
                if(first<0) first++;


                if(x>=first && x<=first+3 && y>=vertical &&  y<= vertical) coords[y][x]=false;
                else  coords[y][x]=true;
            }

        }



    }



    public  void bar_d2()
    {
        direction_control=false;
        max_right=8; max_direction=3;



        for(int y=0;y<coords.length;y++) {

            for (int x = 0; x < coords[0].length; x++) {

                if(vertical<1) vertical++;
                if(vertical>17) vertical--;


                if(x>=first+1 && x<=first+1 && y>=vertical-1 &&  y<= vertical+3-1) coords[y][x]=false;
                else  coords[y][x]=true;
            }

        }

    }



}
