package com.example.ben.firstapplication;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;

public class Sling{

    boolean visible;

    public Sling(boolean slinging){
        this.visible = slinging;
    }

    public void draw(Canvas canvas){
        Paint paint = new Paint();
        paint.setStyle(Paint.Style.FILL);
        paint.setColor(Color.RED);
        paint.setStrokeWidth(15);
        if(!CharacterSprite.useFixedRatio) {
            canvas.drawLine((float) CharacterSprite.x + (float) GameView.imageWidth / 2, (float) CharacterSprite.y + (float) GameView.imageWidth / 2,
                    (float) CharacterSprite.x + (float) GameView.imageWidth / 2, (float) CharacterSprite.y + (float) GameView.imageWidth / 2 - MainActivity.mouseDistance,
                    paint);
        }else{
            canvas.drawCircle((float) CharacterSprite.x + (float) (GameView.imageWidth / 2), (float) CharacterSprite.y + (float) (CharacterSprite.intImageHeight / 2), Math.round((float) GameView.imageWidth / 21), paint);
        }

    }

}
