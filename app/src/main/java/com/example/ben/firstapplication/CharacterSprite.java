package com.example.ben.firstapplication;

import android.graphics.Bitmap;
import android.graphics.Canvas;

public class CharacterSprite {

    public static Bitmap image;
    public static Bitmap scaledImage;

    public static int x, y;
    private int imageWidth;

    public CharacterSprite(Bitmap bmp, int imageWidth) {
        this.imageWidth = imageWidth;
        image = bmp;
        scaledImage = Bitmap.createScaledBitmap(image, imageWidth, imageWidth * GameView.screenWidthToHeightRatio, true);
        x = 100;
        y = 100;
    }

    public void draw(Canvas canvas){
        canvas.drawBitmap(scaledImage, x, y, null);
    }
}
