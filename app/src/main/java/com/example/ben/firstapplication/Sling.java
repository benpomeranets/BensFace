package com.example.ben.firstapplication;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.Paint;

public class Sling{

    boolean visible;

    public static int lineGrowingSpeed = 6;

    public static int lineLength = 0;

    public static int maxLineLength = 200;

    public static String lineIsGrowing = "false";

    public Sling(boolean slinging){
        this.visible = slinging;
    }

    public void draw(Canvas canvas){
        Paint paint = new Paint();
        paint.setStyle(Paint.Style.FILL);
        paint.setColor(Color.RED);
        paint.setStrokeWidth(10);
        paint.setStrokeCap(Paint.Cap.ROUND);

        if(lineIsGrowing.equals("done")){
            lineLength = maxLineLength;
        }else if(lineIsGrowing.equals("false")){
            lineLength = 0;
        }

        if(!CharacterSprite.useFixedRatio) {
            canvas.save();
            canvas.translate((float) CharacterSprite.x  + (float) (GameView.imageWidth / 2), (float) CharacterSprite.y + (float) (GameView.imageWidth / 2));
            canvas.rotate(-90 + (float) Math.toDegrees(CharacterSprite.angle));
            canvas.drawLine(0, 0, 0, lineLength, paint);
            canvas.restore();
        }else{
            canvas.drawCircle((float) CharacterSprite.x + (float) (GameView.imageWidth / 2), (float) CharacterSprite.y + (float) (CharacterSprite.intImageHeight / 2), Math.round((float) GameView.imageWidth / 21), paint);
        }

    }

}
