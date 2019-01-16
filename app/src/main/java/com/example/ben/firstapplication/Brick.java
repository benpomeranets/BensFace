package com.example.ben.firstapplication;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.support.constraint.solver.widgets.Rectangle;

import java.util.ArrayList;
import java.util.List;

public class Brick {

    public static List<float[]> bricks = new ArrayList<float[]>();

    public static int maxBricks = 60;

    public Brick(){

    }



    public void draw(Canvas canvas) {
        Paint paint = new Paint();
        paint.setColor(Color.rgb(226, 132, 24));
        paint.setStyle(Paint.Style.FILL);

        for(int i = 0; i < bricks.size(); i ++){
            bricks.get(i)[0] = 4 + (float) (i % 6)*(float)(Math.ceil(GameView.screenWidth / 6));
            bricks.get(i)[1] = 4 + (float) (Math.ceil(i/6) * (float)(Math.ceil(GameView.screenHeight / 35)));
            bricks.get(i)[2] = 4 + (float) (i % 6)*(float)(Math.ceil(GameView.screenWidth / 6)) + (float)(Math.ceil(GameView.screenWidth / 6)) - 10;
            bricks.get(i)[3] = 4 + (float) (Math.ceil(i/6) * (float)(Math.ceil(GameView.screenHeight / 35))) + ((float)(Math.ceil((GameView.screenHeight / 35)) - 10));
            if(bricks.get(i)[4] != 0) {
                canvas.drawRect(bricks.get(i)[0], bricks.get(i)[1], bricks.get(i)[2], bricks.get(i)[3], paint);
            }
            //0 = left 1 = top 2 = right 3 = bottom

            if((CharacterSprite.x <= bricks.get(i)[2] && CharacterSprite.x + GameView.imageWidth >= bricks.get(i)[0])
            && CharacterSprite.y <= bricks.get(i)[3] && CharacterSprite.y + GameView.imageWidth >= bricks.get(i)[1] && bricks.get(i)[4] != 0){
                bricks.get(i)[4] = 0;
            }
        }
    }

}
