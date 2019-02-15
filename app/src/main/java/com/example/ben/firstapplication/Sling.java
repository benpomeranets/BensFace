package com.example.ben.firstapplication;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.Paint;

public class Sling{

    boolean visible;

    public static int lineGrowingSpeed = 20;

    public static int lineLength = 0;

    public static int maxLineLength = 200;

    public static String lineIsGrowing = "false";

    public static float constrainedAngle;

    public Sling(boolean slinging){
        this.visible = slinging;
    }

    public void draw(Canvas canvas){
        if(CharacterSprite.angle >= (float) -2.44346095 && CharacterSprite.angle <= (float) -0.698131701 && !GameView.isPaused){
            constrainedAngle = CharacterSprite.angle;
        }else if(CharacterSprite.angle < (float) -2.44346095 && !GameView.isPaused){
            constrainedAngle = (float) -2.44346095;
        }else if(CharacterSprite.angle > (float) -0.698131701 && !GameView.isPaused){
            constrainedAngle = (float) -0.698131701;
        }
        Paint paint = new Paint();
        paint.setStyle(Paint.Style.FILL);
        paint.setColor(Color.rgb(234, 105, 105));
        paint.setStrokeWidth((int) (GameView.imageWidth / 7));
        paint.setStrokeCap(Paint.Cap.ROUND);

        if(lineIsGrowing.equals("done") && !GameView.isPaused){
            lineLength = maxLineLength;
        }else if(lineIsGrowing.equals("false")){
            lineLength = 0;
        }

        if(!GameView.slinging){
            lineLength = 0;
            lineIsGrowing = "false";
        }

        canvas.save();
        canvas.translate((float) CharacterSprite.x  + (float) (GameView.imageWidth / 2), (float) CharacterSprite.y + (float) (GameView.imageWidth / 2));
        canvas.rotate(-90 + (float) Math.toDegrees(constrainedAngle));
        canvas.drawLine(0, 0, 0, lineLength, paint);
        canvas.restore();

    }

}
