package com.example.ben.firstapplication;

import android.graphics.Bitmap;
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

    private static Bitmap originalLogo;

    Paint paint = new Paint();

    public void draw(Canvas canvas){

        paint.setStyle(Paint.Style.FILL);

        if(scoreAlpha <= alphaSpeed){
            scoreIsVisible = false;
        }else if(scoreAlpha > alphaSpeed){
            scoreIsVisible = true;
            scoreAlpha -= alphaSpeed;
        }

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

        paint.setTextAlign(Paint.Align.LEFT);

        if(!GameView.gameIsDone) {
            canvas.drawText("Score: " + String.valueOf(GameView.score), GameView.screenWidth / 27, GameView.screenHeight / 44 + (5 + (float) (Math.ceil(Brick.bricksInvisible / 10) * (float) (Math.ceil(GameView.screenHeight / 25)))) / 2, paint);
        }
        paint.setColor(Color.GREEN);

        paint.setAlpha(scoreAlpha);

        if(scoreIsVisible && scoreIncrease != 0){

            canvas.drawText("+" + scoreIncrease, GameView.screenWidth / 2, GameView.screenHeight / 44 + (5 + (float) (Math.ceil(Brick.bricksInvisible / 10) * (float) (Math.ceil(GameView.screenHeight / 25)))) / 2, paint);

        }

        if(GameView.gameIsDone && GameView.score != 0){
            paint.setColor(Color.WHITE);
            paint.setTextSize(GameView.screenWidth / 10);
            paint.setTextAlign(Paint.Align.CENTER);

            if(!GameView.gameBeat ) {
                canvas.drawText("Game over!", GameView.screenWidth / 2,
            (5 + (float) (Math.ceil(Brick.bricksInvisible / 10) * (float) (Math.ceil(GameView.screenHeight / 25)))) + (GameView.screenHeight / 25) + paint.getTextSize() / 2 + (int) (paint.getTextSize()), paint);
            }else{
                canvas.drawText("You won!", GameView.screenWidth / 2,
            (5 + (float) (Math.ceil(Brick.bricksInvisible / 10) * (float) (Math.ceil(GameView.screenHeight / 25)))) + (GameView.screenHeight / 25) + paint.getTextSize() / 2 + (int) (paint.getTextSize()), paint);
            }

            canvas.drawText("Score: " + GameView.score, GameView.screenWidth / 2,
        (5 + (float) (Math.ceil(Brick.bricksInvisible / 10) * (float) (Math.ceil(GameView.screenHeight / 25)))) + (GameView.screenHeight / 25) + paint.getTextSize() / 2 + (int) (2.5 * paint.getTextSize()), paint);

            canvas.drawText("High score: " + GameView.highscore, GameView.screenWidth / 2,
        (5 + (float) (Math.ceil(Brick.bricksInvisible / 10) * (float) (Math.ceil(GameView.screenHeight / 25)))) + (GameView.screenHeight / 25) + paint.getTextSize() / 2 + (int) (4.0 * paint.getTextSize()), paint);

            canvas.drawText("Double tap to play", GameView.screenWidth / 2,
        (5 + (float) (Math.ceil(Brick.bricksInvisible / 10) * (float) (Math.ceil(GameView.screenHeight / 25)))) + (GameView.screenHeight / 25) + paint.getTextSize() / 2 + (int) (5.5 * paint.getTextSize()), paint);

            canvas.drawText("again.", GameView.screenWidth / 2,
        (5 + (float) (Math.ceil(Brick.bricksInvisible / 10) * (float) (Math.ceil(GameView.screenHeight / 25)))) + (GameView.screenHeight / 25) + paint.getTextSize() / 2 + (int) (6.65 * paint.getTextSize()), paint);
        }

    }
}
