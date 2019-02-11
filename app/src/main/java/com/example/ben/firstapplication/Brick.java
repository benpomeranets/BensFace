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

    public static List<float[]> bricks = new ArrayList<float[]>();

    public static int maxBricks = 150;

    public Brick(){

    }

    public static RectF brickRect;

    private Runnable once;

    public void draw(Canvas canvas) {

        Point btl, btr, bbl, bbr, center;

        ptl = new Point((int) CharacterSprite.x, (int) CharacterSprite.y);
        ptr = new Point((int) CharacterSprite.x + (int) GameView.imageWidth, (int) CharacterSprite.y);
        pbl = new Point((int) CharacterSprite.x, (int) CharacterSprite.y + (int) (GameView.imageWidth));
        pbr = new Point((int) CharacterSprite.x + (int) GameView.imageWidth, (int) CharacterSprite.y + (int) GameView.imageWidth);

        for (int i = 0; i < bricks.size(); i++) {

            //the parameters for the bricks
            bricks.get(i)[0] = 4 + (float) (i % 10) * (float) (Math.ceil(GameView.screenWidth / 10));
            bricks.get(i)[1] = 4 + (float) (Math.ceil(i / 10) * (float) (Math.ceil(GameView.screenHeight / 25)));
            bricks.get(i)[2] = 4 + (float) (i % 10) * (float) (Math.ceil(GameView.screenWidth / 10)) + (float) (Math.ceil(GameView.screenWidth / 10)) - 6;
            bricks.get(i)[3] = 4 + (float) (Math.ceil(i / 10) * (float) (Math.ceil(GameView.screenHeight / 25))) + ((float) (Math.ceil((GameView.screenHeight / 25)) - 6));
            bricks.get(i)[5] = 0;

            btl = new Point((int) bricks.get(i)[0], (int) bricks.get(i)[1]);
            btr = new Point((int) bricks.get(i)[2], (int) bricks.get(i)[1]);
            bbl = new Point((int) bricks.get(i)[0], (int) bricks.get(i)[3]);
            bbr = new Point((int) bricks.get(i)[2], (int) bricks.get(i)[3]);

            center = new Point((int) ((bricks.get(i)[0] + bricks.get(i)[2]) / 2), (int) ((bricks.get(i)[1] + bricks.get(i)[3]) / 2));

            //angle = ((float) Math.atan2(((float) mouseY) - ((float) (CharacterSprite.y) + (float) (GameView.imageWidth / 2)), ((float) mouseX) - ((float) (CharacterSprite.x) + (float) (GameView.imageWidth / 2))));



            //the brick rectangle
            brickRect = new RectF((float) bricks.get(i)[0], (float) bricks.get(i)[1], (float) bricks.get(i)[2], (float) bricks.get(i)[3]);

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

            //drawing the bricks if it has any lives left
            if (bricks.get(i)[4] != 0) {
                canvas.drawRect(brickRect, paint);
            }

            float angle_from_bottom_left = ((float) Math.atan2(((float) center.y) - ((float) (bricks.get(i)[3])), ((float) center.x) - ((float) (bricks.get(i)[0]))));
            float angle_from_bottom_right = ((float) Math.atan2(((float) center.y) - ((float) (bricks.get(i)[3])), ((float) center.x) - ((float) (bricks.get(i)[2]))));
            float angle_from_top_left = ((float) Math.atan2(((float) center.y) - ((float) (bricks.get(i)[1])), ((float) center.x) - ((float) (bricks.get(i)[0]))));
            float angle_from_top_right = ((float) Math.atan2(((float) center.y) - ((float) (bricks.get(i)[1])), ((float) center.x) - ((float) (bricks.get(i)[2]))));

            float angle_between_centroids = ((float) Math.atan2(((float) center.y) - (float) (CharacterSprite.y + (GameView.imageWidth / 2)),
                ((float) center.x) - ((float) (CharacterSprite.x + (GameView.imageWidth / 2)))));

            if(CharacterSprite.playerRect != null) {

                if(RectF.intersects(CharacterSprite.playerRect, brickRect) && bricks.get(i)[4] != 0){

                    //TODO: add to 'if' statements a constraint that the player has to be above, below, or to a certain side of the brick rectangle in order to change certain directions

                    if(angle_between_centroids <= angle_from_bottom_left && angle_between_centroids >= angle_from_bottom_right){
                        bricks.get(i)[4] = 0;
                        GameView.yVelocity *= -1;
                    }else if(angle_between_centroids > angle_from_bottom_left && angle_between_centroids <= angle_from_top_left && GameView.xVelocity > 0){
                        bricks.get(i)[4] = 0;
                        GameView.xVelocity *= -1;
                    }else if(angle_between_centroids > angle_from_top_left && angle_between_centroids <= angle_from_top_right && GameView.yVelocity > 0){
                        bricks.get(i)[4] = 0;
                        GameView.yVelocity *= -1;
                    }else if(angle_between_centroids > angle_from_top_right && angle_between_centroids < angle_from_bottom_right && GameView.xVelocity < 0){
                        bricks.get(i)[4] = 0;
                        GameView.xVelocity *= -1;
                    }else{
                        bricks.get(i)[4] = 0;
                    }

                }
            }

        }

    }

}
