package com.example.ben.firstapplication;

import android.graphics.Bitmap;
import android.graphics.Canvas;

public class CharacterSprite {

    public static Bitmap image;
    public static Bitmap scaledImage;

    public static int x, y;
    private int imageWidth;

    private float imageHeight;
    private static int intImageHeight;

    public CharacterSprite(Bitmap bmp, int imageWidth) {
        this.imageWidth = imageWidth;
        imageHeight = imageWidth * GameView.screenWidthToHeightRatio;
        intImageHeight = Math.round(imageHeight);

        image = bmp;
        scaledImage = Bitmap.createScaledBitmap(image, imageWidth, intImageHeight, true);

        GameView.centerX[0] = Math.round((GameView.screenWidth / 2) - (imageWidth / 2));
        GameView.centerX[1] = Math.round((GameView.screenHeight / 2) - (intImageHeight / 2));

        x = GameView.centerX[0];
        y = GameView.centerX[1];
    }

    public void draw(Canvas canvas){
        canvas.drawBitmap(scaledImage, x, y, null);
    }
}
