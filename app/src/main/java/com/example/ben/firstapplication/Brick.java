package com.example.ben.firstapplication;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.support.constraint.solver.widgets.Rectangle;

import java.util.ArrayList;
import java.util.List;

public class Brick {

    public static List<float[]> bricks = new ArrayList<float[]>();

    public static int maxBricks = 48;

    static float lastBrickHitChangedDirections = 0;

    public static boolean canContinue = true;

    boolean isProcessing = false;

    public Brick(){

    }

    public static RectF brickRect;

    private Runnable once;

    public void draw(Canvas canvas) {

        for (int i = 0; i < bricks.size(); i++) {

            String sideContained = "none";

            bricks.get(i)[0] = 4 + (float) (i % 6) * (float) (Math.ceil(GameView.screenWidth / 6));
            bricks.get(i)[1] = 4 + (float) (Math.ceil(i / 6) * (float) (Math.ceil(GameView.screenHeight / 15)));
            bricks.get(i)[2] = 4 + (float) (i % 6) * (float) (Math.ceil(GameView.screenWidth / 6)) + (float) (Math.ceil(GameView.screenWidth / 6)) - 10;
            bricks.get(i)[3] = 4 + (float) (Math.ceil(i / 6) * (float) (Math.ceil(GameView.screenHeight / 15))) + ((float) (Math.ceil((GameView.screenHeight / 15)) - 10));
            bricks.get(i)[5] = 0;

            brickRect = new RectF(bricks.get(i)[0], bricks.get(i)[1], bricks.get(i)[2], bricks.get(i)[3]);

            float brickHeight = bricks.get(i)[3] - bricks.get(i)[1];
            float brickWidth = bricks.get(i)[2] - bricks.get(i)[0];

            Paint paint = new Paint();
            if(bricks.get(i)[4] == 5) {
                paint.setColor(Color.rgb(104, 130, 135));

            }else if(bricks.get(i)[4] == 4){
                paint.setColor(Color.rgb(108, 145, 153));

            }else if(bricks.get(i)[4] == 3){
                paint.setColor(Color.rgb(106, 153, 163));

            }else if(bricks.get(i)[4] == 2){
                paint.setColor(Color.rgb(103, 165, 178));

            }else if(bricks.get(i)[4] == 1){
                paint.setColor(Color.rgb(101, 196, 216));

            }
            paint.setStyle(Paint.Style.FILL);

            /*Paint paint1 = new Paint();
            paint1.setStyle(Paint.Style.FILL);
            paint1.setColor(Color.WHITE);

            canvas.drawText(String.valueOf(bricks.get(i)[4]), bricks.get(i)[0], bricks.get(i)[1], paint1);*/

            if (bricks.get(i)[4] != 0) {
                //canvas.drawRect(bricks.get(i)[0], bricks.get(i)[1], bricks.get(i)[2], bricks.get(i)[3], paint);
                canvas.drawRect(brickRect, paint);
            }
            //0 = left 1 = top 2 = right 3 = bottom

            /*if((CharacterSprite.x <= bricks.get(i)[2] && CharacterSprite.x + GameView.imageWidth >= bricks.get(i)[0])
            && CharacterSprite.y <= bricks.get(i)[3] && CharacterSprite.y + GameView.imageWidth >= bricks.get(i)[1] && bricks.get(i)[4] != 0 && canContinue){
                //lastBrickHitChangedDirections = 0;
                if(CharacterSprite.x >= bricks.get(i)[0]  && CharacterSprite.x <= bricks.get(i)[2] && GameView.yVelocity < 0 && canContinue
                        && CharacterSprite.y <= bricks.get(i)[3] && CharacterSprite.y > bricks.get(i)[3] - (brickHeight / 10)){
                    do {
                        canContinue = false;
                        synchronized (this) {
                            GameView.yVelocity *= -1;
                            lastBrickHitChangedDirections = 1;
                            canContinue = true;
                            isProcessing = true;
                            bricks.get(i)[4] = 0;
                            lastBrickHitChangedDirections = 0;
                        }
                    }while(CharacterSprite.x >= bricks.get(i)[0]  && CharacterSprite.x <= bricks.get(i)[2] && GameView.yVelocity < 0 && canContinue
                            && CharacterSprite.y <= bricks.get(i)[3] && CharacterSprite.y > bricks.get(i)[3] - (brickHeight / 10));
                }else if(CharacterSprite.x >= bricks.get(i)[0]  && CharacterSprite.x <= bricks.get(i)[2] && GameView.yVelocity > 0 && canContinue
                        && CharacterSprite.y + GameView.imageWidth >= bricks.get(i)[1] && CharacterSprite.y + GameView.imageWidth < bricks.get(i)[1] + (brickHeight / 10)) {
                    do {
                        canContinue = false;
                        synchronized (this) {
                            GameView.yVelocity *= -1;
                            lastBrickHitChangedDirections = 1;
                            canContinue = true;
                            isProcessing = true;
                            bricks.get(i)[4] = 0;
                            lastBrickHitChangedDirections = 0;
                        }
                    }while(CharacterSprite.x >= bricks.get(i)[0]  && CharacterSprite.x <= bricks.get(i)[2] && GameView.yVelocity > 0 && canContinue
                            && CharacterSprite.y + GameView.imageWidth >= bricks.get(i)[1] && CharacterSprite.y + GameView.imageWidth < bricks.get(i)[1] + (brickHeight / 10));
                }if(CharacterSprite.x <= (bricks.get(i)[2]) && CharacterSprite.x >= (bricks.get(i)[2] - (brickWidth / 10)) && (CharacterSprite.y + (GameView.imageWidth / 2)) <= (bricks.get(i)[3])
                        && (CharacterSprite.y + (GameView.imageWidth / 2)) >= bricks.get(i)[1] && GameView.xVelocity < 0){
                    do {
                        canContinue = false;
                        synchronized (this) {
                            GameView.xVelocity *= -1;
                            lastBrickHitChangedDirections = 1;
                            canContinue = true;
                            isProcessing = true;
                            bricks.get(i)[4] = 0;
                            lastBrickHitChangedDirections = 0;
                        }
                    }while(CharacterSprite.x <= (bricks.get(i)[2]) && CharacterSprite.x >= (bricks.get(i)[2] - (brickWidth / 10)) && (CharacterSprite.y + (GameView.imageWidth / 2)) <= (bricks.get(i)[3])
                            && (CharacterSprite.y + (GameView.imageWidth / 2)) >= bricks.get(i)[1] && GameView.xVelocity < 0);
                }else if(CharacterSprite.x >= (bricks.get(i)[0]) && CharacterSprite.x <= (bricks.get(i)[0] + (brickWidth / 10)) && (CharacterSprite.y + (GameView.imageWidth / 2)) <= (bricks.get(i)[3])
                        && (CharacterSprite.y + (GameView.imageWidth / 2)) >= bricks.get(i)[1] && GameView.xVelocity > 0){
                    do {
                        canContinue = false;
                        synchronized (this) {
                            GameView.xVelocity *= -1;
                            lastBrickHitChangedDirections = 1;
                            canContinue = true;
                            isProcessing = true;
                            bricks.get(i)[4] = 0;
                            lastBrickHitChangedDirections = 0;
                        }
                    }while(CharacterSprite.x >= (bricks.get(i)[0]) && CharacterSprite.x <= (bricks.get(i)[0] + (brickWidth / 10)) && (CharacterSprite.y + (GameView.imageWidth / 2)) <= (bricks.get(i)[3])
                            && (CharacterSprite.y + (GameView.imageWidth / 2)) >= bricks.get(i)[1] && GameView.xVelocity > 0);
                }
            }else{
                lastBrickHitChangedDirections = 0;
            }

            if (CharacterSprite.playerRect != null && GameView.started && CharacterSprite.heightAwayFromStart >= 50) {

                if ((brickRect.contains(CharacterSprite.playerRect.left, CharacterSprite.playerRect.top) || brickRect.contains(CharacterSprite.playerRect.right, CharacterSprite.playerRect.top)) && bricks.get(i)[4] != 0
                        && GameView.yVelocity < 0 && !sideContained.equals("bottom") && !sideContained.equals("right") && !sideContained.equals("left")) {

                    if ((float) bricks.get(i)[3] - (float) CharacterSprite.y <= 30.0) {
                        sideContained = "top";
                    }
                }
                if ((brickRect.contains(CharacterSprite.playerRect.left, CharacterSprite.playerRect.bottom) || brickRect.contains(CharacterSprite.playerRect.right, CharacterSprite.playerRect.bottom)) && bricks.get(i)[4] != 0
                        && GameView.yVelocity > 0 && !sideContained.equals("top") && !sideContained.equals("right") && !sideContained.equals("left")) {

                    if ((float) bricks.get(i)[1] - (float) CharacterSprite.y + (float) GameView.imageWidth <= 30.0) {
                        sideContained = "bottom";
                    }
                }
                if ((brickRect.contains(CharacterSprite.playerRect.right, CharacterSprite.playerRect.top) || brickRect.contains(CharacterSprite.playerRect.right, CharacterSprite.playerRect.bottom)) && bricks.get(i)[4] != 0
                        && GameView.xVelocity > 0 && !sideContained.equals("bottom") && !sideContained.equals("top") && !sideContained.equals("left")) {
                    sideContained = "right";
                }
                if ((brickRect.contains(CharacterSprite.playerRect.left, CharacterSprite.playerRect.top) || brickRect.contains(CharacterSprite.playerRect.left, CharacterSprite.playerRect.bottom)) && bricks.get(i)[4] != 0
                        && GameView.xVelocity < 0 && !sideContained.equals("bottom") && !sideContained.equals("right") && !sideContained.equals("top")) {

                    sideContained = "left";
                }
            }

            if(sideContained.equals("top")){
                bricks.get(i)[4] = 0;
                GameView.yVelocity *= -1;
                sideContained = "none";
            }if(sideContained.equals("bottom")){
                bricks.get(i)[4] = 0;
                GameView.yVelocity *= -1;
                sideContained = "none";
            }if(sideContained.equals("right")){
                bricks.get(i)[4] = 0;
                GameView.xVelocity *= -1;
                sideContained = "none";
            }if(sideContained.equals("left")){
                bricks.get(i)[4] = 0;
                GameView.xVelocity *= -1;
                sideContained = "none";
            }*/

            if(CharacterSprite.playerRect != null) {
                if (CharacterSprite.playerRect.intersect(brickRect) && bricks.get(i)[4] != 0) {
                    if((((float) CharacterSprite.y <= (float) brickRect.bottom && (float) brickRect.bottom - (float) CharacterSprite.y <= (float) (GameView.speed * 2)) && GameView.yVelocity < 0) ||
                        ((float) CharacterSprite.y + (float) GameView.imageWidth >= (float) brickRect.top && (((float) CharacterSprite.y + (float) GameView.imageWidth)) - (float) brickRect.top <= (float) (GameView.speed * 2)) && GameView.yVelocity > 0){

                        bricks.get(i)[4] = 0;
                        GameView.yVelocity *= -1;

                    }else if(((float) CharacterSprite.x + (float) GameView.imageWidth >= (float) brickRect.left && GameView.xVelocity > 0) || ((float) CharacterSprite.x <= (float) brickRect.right && GameView.xVelocity < 0)){
                        bricks.get(i)[4] = 0;
                        GameView.xVelocity *= -1;
                    }
                }
            }

        }

    }

}
