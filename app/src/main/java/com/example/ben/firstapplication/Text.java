package com.example.ben.firstapplication;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;

public class Text {

    public Text(){

    }

    public static RectF bottomRect;

    public void draw(Canvas canvas){

        Paint paint = new Paint();
        paint.setStyle(Paint.Style.FILL);
        paint.setColor(Color.rgb(31, 115, 130));

        if(Platform.platformRect != null) {
            bottomRect = new RectF((float) 0, Platform.platformRect.bottom + (float) 35, (float) GameView.screenWidth, (float) GameView.screenHeight);
        }

        if(Brick.bricks.get(Brick.bricksInvisible) != null) {
            canvas.drawRect(0, 0, GameView.screenWidth, 5 + (float) (Math.ceil(Brick.bricksInvisible / 10) * (float) (Math.ceil(GameView.screenHeight / 25))), paint);
        }

        if(Platform.platformRect != null) {
            canvas.drawRect(bottomRect, paint);
        }


    }
}
