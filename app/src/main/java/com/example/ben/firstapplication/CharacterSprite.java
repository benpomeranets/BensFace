package com.example.ben.firstapplication;

import android.graphics.Bitmap;
import android.graphics.Canvas;

public class CharacterSprite {

    public static Bitmap image;
    public static Bitmap scaledImage;

    public static int x, y;
    private int imageWidth;
    public static float imageHeight;
    public static int intImageHeight;

    public CharacterSprite(Bitmap bmp, int imageWidth) {
        this.imageWidth = imageWidth;
        imageHeight = imageWidth * GameView.screenWidthToHeightRatio;
        intImageHeight = (int) Math.ceil(imageHeight);
        image = bmp;
        scaledImage = Bitmap.createScaledBitmap(image, imageWidth, intImageHeight, true);
        x = GameView.centerX[0];
        y = GameView.centerX[1];
    }

    public void draw(Canvas canvas){
        canvas.drawBitmap(scaledImage, x, y, null);
    }
}
