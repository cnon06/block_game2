package com.tetblocks.tetb1;

public class ghost1 {

    int  block_width=0, block_height=2, direction=0, block_type=0;
    boolean  ghost_blocks1 [][] = new boolean[20][10];
    blocks blcks = new blocks();
    
    
    ghost1()
    {
        
    }


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



                if(x>=blcks.horizontal+1 && x<=blcks.horizontal+1 && y>=blcks.vertical-1 &&  y<= blcks.vertical+3-1) ghost_blocks1[y][x]=false;
                else  ghost_blocks1[y][x]=true;
            }

        }




    }






    
}
