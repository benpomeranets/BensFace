package com.example.ben.firstapplication;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;


public class Text {

    public static Bitmap originalLogo;

    private static Bitmap scaledLogo;

    public static int scoreIncrease = 0;

    public static RectF bottomRect, topRect;

    public static int scoreAlpha = 0;

    int alphaSpeed = 8;

    public static int bricksHit = 0;

    public static boolean scoreIsVisible = false;

    private static float topRectHeight;

    Paint paint = new Paint();

    float imageWidthFloat = (float) (GameView.screenWidth * 0.66);

    float imageHeightFloat = (float) (imageWidthFloat * 0.19168900804);

    int imageWidth =  Math.round(imageWidthFloat);

    int imageHeight = Math.round(imageHeightFloat);

    public Text(Bitmap img) {
        originalLogo = img;
        scaledLogo = Bitmap.createScaledBitmap(img, imageWidth, imageHeight, true);

        topRectHeight = GameView.screenHeight / 11;
    }

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

        topRect = new RectF((float) 0, (float) 0, (float) GameView.screenWidth, topRectHeight);

        canvas.drawRect(topRect, paint);


        if(Platform.platformRect != null) {
            canvas.drawRect(bottomRect, paint);
        }

        paint.setColor(Color.rgb(158, 207, 216));

        paint.setTextSize(GameView.screenHeight / 25);

        paint.setTextAlign(Paint.Align.LEFT);

        if(!GameView.gameIsDone) {
            canvas.drawText("Score: " + String.valueOf(GameView.score), GameView.screenWidth / 27,  (topRect.bottom + topRect.top) / 2 + (paint.getTextSize()) / 2, paint);
        }
        paint.setColor(Color.GREEN);

        paint.setAlpha(scoreAlpha);

        if(scoreIsVisible && scoreIncrease != 0){

            canvas.drawText("+" + scoreIncrease, GameView.screenWidth / 2, (topRect.bottom + topRect.top) / 2 + (paint.getTextSize()) / 2, paint);

        }

        if(GameView.gameIsDone && GameView.score != 0){
            paint.setColor(Color.WHITE);
            paint.setTextSize(GameView.screenWidth / 10);
            paint.setTextAlign(Paint.Align.CENTER);

            if(!GameView.gameBeat ) {
                canvas.drawText("Game over!", GameView.screenWidth / 2,
            (5 + (float) (topRectHeight + (GameView.screenHeight / 25) + paint.getTextSize() / 2 + (int) (paint.getTextSize()))), paint);
            }else{
                canvas.drawText("You won!", GameView.screenWidth / 2,
            (5 + (float) (topRectHeight + (GameView.screenHeight / 25) + paint.getTextSize() / 2 + (int) (paint.getTextSize()))), paint);
            }

            canvas.drawText("Score: " + GameView.score, GameView.screenWidth / 2,
        (5 + (float) (topRectHeight + (GameView.screenHeight / 25) + paint.getTextSize() / 2 + (int) (2.8 * paint.getTextSize()))), paint);

            canvas.drawText("High score: " + GameView.highscore, GameView.screenWidth / 2,
        (5 + (float) (topRectHeight + (GameView.screenHeight / 25) + paint.getTextSize() / 2 + (int) (4.6 * paint.getTextSize()))), paint);

            canvas.drawText("Double tap to play", GameView.screenWidth / 2,
        (5 + (float) (topRectHeight + (GameView.screenHeight / 25) + paint.getTextSize() / 2 + (int) (6.4 * paint.getTextSize()))), paint);

            canvas.drawText("again.", GameView.screenWidth / 2,
        (5 + (float) (topRectHeight + (GameView.screenHeight / 25) + paint.getTextSize() / 2 + (int) (7.55 * paint.getTextSize()))), paint);
        }

        if(bottomRect != null) {
            canvas.drawBitmap(scaledLogo, (GameView.screenWidth - scaledLogo.getWidth()) / 2, (bottomRect.top) + ((scaledLogo.getHeight()) / 3), null);
        }

    }
}
