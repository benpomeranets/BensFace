package com.example.ben.firstapplication;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;

public class Pause {

    public static boolean isPaused = false;

    public Pause(){

    }

    public void draw(Canvas canvas){

        Paint paint = new Paint();
        paint.setStyle(Paint.Style.FILL);
        paint.setColor(Color.WHITE);
        paint.setStrokeWidth(GameView.screenWidth / 50);
        paint.setStrokeCap(Paint.Cap.ROUND);

        if(!isPaused){

            //draw pause button

        }

    }

}
