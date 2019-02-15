package com.example.ben.firstapplication;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;

public class Platform {

    public static float platformVel = 0;

    public static float platformX = GameView.screenWidth / 2;

    public static RectF platformRect;

    public static float platformWidth = 175, platformHeight = 30;

    private static float platformHeightOffGround;

    public static boolean canStartDragging = false;

    public void draw(Canvas canvas){

        if(GameView.centerX != null){
            platformHeightOffGround = (GameView.screenHeight) - (GameView.centerX[1]) - (GameView.imageWidth + 20);
        }

        platformRect = new RectF(platformX - (platformWidth / 2), GameView.screenHeight - (platformHeight + platformHeightOffGround), platformX + (platformWidth / 2),
                GameView.screenHeight - platformHeightOffGround);

        if(CharacterSprite.y + GameView.imageWidth < GameView.screenHeight - (platformHeight + platformHeightOffGround) - 5 && !canStartDragging && MainActivity.isDraggingPlatform){
            canStartDragging = true;
        }

        if(GameView.started && canStartDragging && !GameView.isPaused) {
            platformVel = ((float) MainActivity.mouseX - platformX) / 2;
            platformX += platformVel;
        }

        Paint paint = new Paint();
        paint.setColor(Color.rgb(234, 105, 105));
        paint.setStyle(Paint.Style.FILL);
        canvas.drawRect(platformRect, paint);
    }
}
