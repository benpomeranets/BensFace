package com.example.ben.firstapplication;

import android.animation.RectEvaluator;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Point;
import android.graphics.Rect;
import android.graphics.RectF;
import android.support.constraint.solver.widgets.ChainHead;
import android.support.constraint.solver.widgets.Rectangle;

import java.util.ArrayList;
import java.util.List;

public class Brick {

    Point ptl, ptr, pbl, pbr;

    public static int bricksInvisible = 20;

    public static List<float[]> bricks = new ArrayList<float[]>();

    public static int maxBricks = 130;

    public Brick(){

    }

    public static RectF brickRect;

    public void draw(Canvas canvas) {

        ptl = new Point((int) CharacterSprite.x, (int) CharacterSprite.y);
        ptr = new Point((int) CharacterSprite.x + (int) GameView.imageWidth, (int) CharacterSprite.y);
        pbl = new Point((int) CharacterSprite.x, (int) CharacterSprite.y + (int) (GameView.imageWidth));
        pbr = new Point((int) CharacterSprite.x + (int) GameView.imageWidth, (int) CharacterSprite.y + (int) GameView.imageWidth);

        for (int i = 0; i < bricks.size(); i++) {

            if(i <= bricksInvisible || i % 10 == 0 || i % 10 == 9 || i >= maxBricks - 9){
                bricks.get(i)[4] = 0;
            }

            //the parameters for the bricks
            bricks.get(i)[0] = 4 + (float) (i % 10) * (float) (Math.ceil(GameView.screenWidth / 10));
            bricks.get(i)[1] = 40 + (float) (Math.ceil(i / 10) * (float) (Math.ceil(GameView.screenHeight / 25)));
            bricks.get(i)[2] = 4 + (float) (i % 10) * (float) (Math.ceil(GameView.screenWidth / 10)) + (float) (Math.ceil(GameView.screenWidth / 10)) - 6;
            bricks.get(i)[3] = 40 + (float) (Math.ceil(i / 10) * (float) (Math.ceil(GameView.screenHeight / 25))) + ((float) (Math.ceil((GameView.screenHeight / 25)) - 6));
            bricks.get(i)[5] = 0;

            Point center = new Point((int) ((bricks.get(i)[0] + bricks.get(i)[2]) / 2), (int) ((bricks.get(i)[1] + bricks.get(i)[3]) / 2));

            //the brick rectangle
            brickRect = new RectF((float) bricks.get(i)[0], (float) bricks.get(i)[1], (float) bricks.get(i)[2], (float) bricks.get(i)[3]);

            float brickHeight = bricks.get(i)[3] - bricks.get(i)[1];
            float brickWidth = bricks.get(i)[2] - bricks.get(i)[0];

            Paint paint = new Paint();

            if(bricks.get(i)[4] >= 3) {
                paint.setColor(Color.rgb(104, 130, 135));

            }else if(bricks.get(i)[4] == 2){
                paint.setColor(Color.rgb(112, 152, 160));

            }else if(bricks.get(i)[4] == 1) {
                paint.setColor(Color.rgb(117, 174, 186));
            }

            paint.setStyle(Paint.Style.FILL);

            //drawing the bricks if it has any lives left
            if (bricks.get(i)[4] != 0) {
                canvas.drawRect(brickRect, paint);
            }

            //collision detection using MINKOWSKI SUM

            float w = (float) 0.5 * ((float) GameView.imageWidth + brickWidth);
            float h = (float) 0.5 * ((float) GameView.imageWidth + brickHeight);
            float dx = ((float) CharacterSprite.x + (float) (GameView.imageWidth / 2)) - center.x;
            float dy = ((float) CharacterSprite.y + (float) (GameView.imageWidth / 2)) - center.y;

            if(CharacterSprite.playerRect != null && !GameView.isPaused) {
                if (Math.abs(dx) <= w && Math.abs(dy) <= h && bricks.get(i)[4] != 0) {
                    /* collision! */
                    float wy = w * dy;
                    float hx = h * dx;

                    if (wy > hx){
                        if (wy > -hx) {
                            /* on the top */
                            if(GameView.yVelocity < 0) {
                                bricks.get(i)[4] -= 1; //deleting the brick
                                GameView.yVelocity *= -1; //changing directions
                            }
                        } else {
                            if(GameView.xVelocity > 0) {
                                /* on the left */
                                bricks.get(i)[4] -= 1;
                                GameView.xVelocity *= -1;

                            }
                        }
                    }else{
                        if (wy > -hx) {
                            /* on the right */
                            if(GameView.xVelocity < 0) {
                                bricks.get(i)[4] -= 1;
                                GameView.xVelocity *= -1;
                            }
                        } else{
                            if(GameView.yVelocity > 0) {
                                /* at the bottom */
                                bricks.get(i)[4] -= 1;
                                GameView.yVelocity *= -1;
                            }
                        }
                    }
                }
            }

        }

    }

    public static void restartArray(){
        bricks = new ArrayList<float[]>();
        for (int i = 0; i < Brick.maxBricks; i++) {
            Brick.bricks.add(new float[7]);
            Brick.bricks.get(i)[4] = 3;
            Brick.bricks.get(i)[6] = 0;
        }
    }
}
