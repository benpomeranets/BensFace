package com.example.ben.firstapplication;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;

public class CharacterSprite {

    public static float heightAwayFromStart;

    Paint paint = new Paint();

    public static double x, y;

    public static float angle;

    public static RectF playerRect;

    CharacterSprite(Bitmap bmp, int imageWidth) {

        GameView.centerX[0] = Math.round((GameView.screenWidth / 2) - (imageWidth / 2));

        GameView.centerX[1] = Math.round((GameView.screenHeight) - (imageWidth) - (3 * (GameView.screenHeight / 20)));

        x = GameView.centerX[0];
        y = GameView.centerX[1];

    }

    public void draw(Canvas canvas){

        playerRect = new RectF((float) x, (float) y, (float) x + (GameView.imageWidth), (float) y + (float) (GameView.imageWidth));
        paint.setColor(Color.rgb(234, 239, 249));
        paint.setStyle(Paint.Style.FILL);
        canvas.drawCircle(playerRect.centerX(), playerRect.centerY(), GameView.imageWidth / 2, paint);
        heightAwayFromStart = (float) GameView.centerX[1] - (float) x;
    }
}
