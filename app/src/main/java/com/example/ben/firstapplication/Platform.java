package com.example.ben.firstapplication;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;

public class Platform {

    public static float platformX = GameView.screenWidth / 2;

    public static RectF platformRect;

    public static float platformWidth = 175, platformHeight = 30;

    private static float platformHeightOffGround = 70;

    public void draw(Canvas canvas){

        platformRect = new RectF(platformX - (platformWidth / 2), GameView.screenHeight - (platformHeight + platformHeightOffGround), platformX + (platformWidth / 2),
                GameView.screenHeight - platformHeightOffGround);
        if(MainActivity.isDraggingPlatform){
            platformX = (float) MainActivity.mouseX;
        }
        Paint paint = new Paint();
        paint.setColor(Color.rgb(234, 105, 105));
        paint.setStyle(Paint.Style.FILL);
        canvas.drawRect(platformRect, paint);
    }
}
