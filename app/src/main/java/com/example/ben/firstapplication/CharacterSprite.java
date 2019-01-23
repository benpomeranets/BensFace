package com.example.ben.firstapplication;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.RectF;

public class CharacterSprite {

    public static Bitmap image;
    static Bitmap scaledImage;

    public static double x, y;

    public static boolean useFixedRatio;

    public static int intImageHeight;

    public static float angle;

    public static RectF playerRect;

    CharacterSprite(Bitmap bmp, int imageWidth) {

        useFixedRatio = false;

        float imageHeight = imageWidth * GameView.screenWidthToHeightRatio;
        intImageHeight = Math.round(imageHeight);

        image = bmp;

        //git

        if(useFixedRatio) {
            scaledImage = Bitmap.createScaledBitmap(image, imageWidth, intImageHeight, true);
        }else {
            scaledImage = Bitmap.createScaledBitmap(image, imageWidth, imageWidth, true);
        }

        GameView.centerX[0] = Math.round((GameView.screenWidth / 2) - (imageWidth / 2));

        if(useFixedRatio) {
            GameView.centerX[1] = Math.round((GameView.screenWidth / 2) - (intImageHeight / 2));
        }else{
            GameView.centerX[1] = Math.round((GameView.screenHeight) - (imageWidth) - (GameView.screenHeight / 20));
        }

        x = GameView.centerX[0];
        y = GameView.centerX[1];

        playerRect = new RectF((float) x, (float) y, (float) x + (GameView.imageWidth), (float) y + (float) (GameView.imageWidth));
    }

    public void draw(Canvas canvas){
        Paint paint = new Paint();
        paint.setColor(Color.rgb(226, 132, 24));
        paint.setStyle(Paint.Style.FILL);
        canvas.drawRect(playerRect, paint);
    }
}
