package com.tetblocks.tetb1;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;

public class start_game extends View

{

    int width  = Resources.getSystem().getDisplayMetrics().widthPixels;
    int height = Resources.getSystem().getDisplayMetrics().heightPixels;


    int x=200;
    int y= 1500;

    Bitmap ic_launcher = BitmapFactory.decodeResource(this.getResources(),R.drawable.start1);

    Bitmap ic_launcher2=Bitmap.createScaledBitmap(ic_launcher,ic_launcher.getWidth()/4,ic_launcher.getHeight()/4,false);

    Bitmap ic_launcher3 = BitmapFactory.decodeResource(this.getResources(),R.drawable.start2);

    Bitmap ic_launcher4=Bitmap.createScaledBitmap(ic_launcher3,ic_launcher.getWidth()/4,ic_launcher.getHeight()/4,false);


    Bitmap cath = BitmapFactory.decodeResource(this.getResources(),R.drawable.cath2);

    Bitmap cath2=Bitmap.createScaledBitmap(cath,cath.getWidth(),cath.getHeight(),false);


    boolean button_up_down=false;

    public start_game(Context context) {
        super(context);

    }





    public boolean onTouchEvent(MotionEvent event) {





        int eventaction = event.getAction();


        int event_x= (int) event.getX();
        int event_y= (int) event.getY();






                switch (eventaction ) {

        case MotionEvent.ACTION_DOWN: // touch down so check if the finger is on a ball

            if(event_x>x+120 && event_y>y && event_x<(x+ic_launcher2.getWidth()-110) &&  event_y<(y+ic_launcher2.getHeight()-30))
            {
                button_up_down=true;
                invalidate();
            }


            Log.e("", "ACTION_DOWN " );

        break;


        case MotionEvent.ACTION_MOVE:   // touch drag with the ball
            Log.e("", "ACTION_MOVE" );
        break;

        case MotionEvent.ACTION_UP:
            button_up_down=false;
            invalidate();
          //  canvas.drawBitmap(ic_launcher2,x,y,null);
            Log.e("", "ACTION_UP" );

        break;
    }


      return true;
    }

    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        Paint paint = new Paint();

        paint.setStyle(Paint.Style.FILL);
      //  paint.setColor(Color.RED);
        paint.setColor(Color.WHITE);
        canvas.drawPaint(paint);



        int x_scale=(width/2)-(ic_launcher2.getWidth()/2);
        int y_scale=(height/2)-(ic_launcher2.getHeight()/2);

        int x_scale_cath =(width/2)-(cath2.getWidth()/2);
        int y_scale_cath=(height/2)-(cath2.getHeight()/2);



        canvas.drawBitmap(cath2,x_scale_cath,y_scale_cath+200,null);

        if(!button_up_down)
            canvas.drawBitmap(ic_launcher2,x_scale,y,null);
        else
            canvas.drawBitmap(ic_launcher4,x_scale,y,null);


        Log.e("", "Get width: "+ic_launcher2.getWidth() );




    }
}
