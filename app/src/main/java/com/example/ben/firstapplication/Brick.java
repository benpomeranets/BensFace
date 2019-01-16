package com.example.ben.firstapplication;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;

public class Brick {

    public static int[][] bricks;

    public Brick(){

    }

    public void draw(Canvas canvas) {
        Paint paint = new Paint();
        paint.setColor(Color.rgb(226, 132, 24));
        paint.setStyle(Paint.Style.FILL);
        for(int i = 0; i < 60; i ++){
            canvas.drawRect(4 + (i % 6)*(float)(Math.ceil(GameView.screenWidth / 6)), 4 + (float) (Math.ceil(i/6) * (float)(Math.ceil(GameView.screenHeight / 35))),
            4 + (i % 6)*(float)(Math.ceil(GameView.screenWidth / 6)) + (float)(Math.ceil(GameView.screenWidth / 6)) - 10,
            4 + (float) (Math.ceil(i/6) * (float)(Math.ceil(GameView.screenHeight / 35))) + ((float)(Math.ceil((GameView.screenHeight / 35))* 4/5)), paint);
        }
    }

}
