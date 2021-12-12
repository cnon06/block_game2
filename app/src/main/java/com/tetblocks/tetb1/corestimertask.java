package com.tetblocks.tetb1;

import java.util.TimerTask;

public class corestimertask extends TimerTask

{

    int count=0;

    public boolean hasStarted = false;


    @Override
    public boolean cancel() {
        return super.cancel();
    }

    public void run() {
        this.hasStarted = false;
        //rest of run logic here...
      //  timer_count++;
       System.out.println("Timer is running: "+count);
        count++;
        if(count>=100) cancel();
        else this.hasStarted = true;


    }

    public boolean hasRunStarted() {
        return this.hasStarted;
    }


}
