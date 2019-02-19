package com.example.ben.firstapplication;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.graphics.Typeface;

public class Text {

    public Text(){

    }

    public static int scoreIncrease = 0;

    public static RectF bottomRect;

    public static int scoreAlpha = 0;

    int alphaSpeed = 8;

    public static int bricksHit = 0;

    public static boolean scoreIsVisible = false;

    public void draw(Canvas canvas){

        if(scoreAlpha <= alphaSpeed){
            scoreIsVisible = false;
        }else if(scoreAlpha > alphaSpeed){
            scoreIsVisible = true;
            scoreAlpha -= alphaSpeed;
        }

        Paint paint = new Paint();
        paint.setStyle(Paint.Style.FILL);
        paint.setColor(Color.rgb(31, 115, 130));

        if(Platform.platformRect != null) {
            bottomRect = new RectF((float) 0, Platform.platformRect.bottom + (float) 35, (float) GameView.screenWidth, (float) GameView.screenHeight);
        }

        if(Brick.bricks.get(Brick.bricksInvisible) != null) {
            canvas.drawRect(0, 0, GameView.screenWidth, 5 + (float) (Math.ceil(Brick.bricksInvisible / 10) * (float) (Math.ceil(GameView.screenHeight / 25))), paint);
        }

        if(Platform.platformRect != null) {
            canvas.drawRect(bottomRect, paint);
        }

        paint.setColor(Color.rgb(158, 207, 216));

        paint.setTextSize(GameView.screenHeight / 25);

        canvas.drawText("Score: " + String.valueOf(GameView.score), GameView.screenWidth / 27, GameView.screenHeight / 44 + (5 + (float) (Math.ceil(Brick.bricksInvisible / 10) * (float) (Math.ceil(GameView.screenHeight / 25)))) / 2, paint);

        paint.setColor(Color.GREEN);

        paint.setAlpha(scoreAlpha);

        if(scoreIsVisible && scoreIncrease != 0){

            canvas.drawText("+" + scoreIncrease, GameView.screenWidth / 2, GameView.screenHeight / 44 + (5 + (float) (Math.ceil(Brick.bricksInvisible / 10) * (float) (Math.ceil(GameView.screenHeight / 25)))) / 2, paint);

        }

        if(GameView.gameIsDone){
            paint.setColor(Color.rgb(191, 104, 45));
            paint.setTextSize(GameView.screenWidth / 10);

            if(!GameView.gameBeat) {
                canvas.drawText("Game Over!", GameView.screenWidth / 31,
            (5 + (float) (Math.ceil(Brick.bricksInvisible / 10) * (float) (Math.ceil(GameView.screenHeight / 25)))) + (GameView.screenHeight / 25) + paint.getTextSize() / 2, paint);
            }else{
                canvas.drawText("You won!", GameView.screenWidth / 31,
            (5 + (float) (Math.ceil(Brick.bricksInvisible / 10) * (float) (Math.ceil(GameView.screenHeight / 25)))) + (GameView.screenHeight / 25) + paint.getTextSize() / 2, paint);
            }

            canvas.drawText("Highscore:", GameView.screenWidth / 31,
                    (5 + (float) (Math.ceil(Brick.bricksInvisible / 10) * (float) (Math.ceil(GameView.screenHeight / 25)))) + (GameView.screenHeight / 25) + paint.getTextSize() / 2 + (int) (1.5 * paint.getTextSize()), paint);

            canvas.drawText("Double tap to play", GameView.screenWidth / 31,
        (5 + (float) (Math.ceil(Brick.bricksInvisible / 10) * (float) (Math.ceil(GameView.screenHeight / 25)))) + (GameView.screenHeight / 25) + paint.getTextSize() / 2 + (int) (3.0 * paint.getTextSize()), paint);

            canvas.drawText("again.", GameView.screenWidth / 31,
        (5 + (float) (Math.ceil(Brick.bricksInvisible / 10) * (float) (Math.ceil(GameView.screenHeight / 25)))) + (GameView.screenHeight / 25) + paint.getTextSize() / 2 + (int) (4.15 * paint.getTextSize()), paint);
        }

    }
}
