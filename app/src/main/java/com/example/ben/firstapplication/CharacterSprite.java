package com.example.ben.firstapplication;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Matrix;

public class CharacterSprite {

    public static Bitmap image;
    static Bitmap scaledImage;

    public static double x, y;

    public static boolean useFixedRatio;

    public static int intImageHeight;

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
            GameView.centerX[1] = Math.round((GameView.screenHeight / 2) - (intImageHeight / 2));
        }else{
            GameView.centerX[1] = Math.round((GameView.screenHeight) - (imageWidth * 2));
        }

        x = GameView.centerX[0];
        y = GameView.centerX[1];
    }

    public void draw(Canvas canvas){
        canvas.drawBitmap(scaledImage, Math.round(x), Math.round(y), null);
    }
}
