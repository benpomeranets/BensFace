package com.example.ben.firstapplication;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Point;
import android.view.SurfaceView;
import android.view.SurfaceHolder;
import android.graphics.Color;
import android.graphics.Paint;
import android.widget.ImageView;
import android.widget.LinearLayout;

public class GameView extends SurfaceView implements SurfaceHolder.Callback {

    private MainThread thread;

    private CharacterSprite characterSprite;

    public static int xVelocity = 5;
    public static int yVelocity = -10;
    public static int screenWidth = Resources.getSystem().getDisplayMetrics().widthPixels;
    public static int screenHeight = Resources.getSystem().getDisplayMetrics().heightPixels;

    public static float screenWidthToHeightRatio;

    public static int timer = 0;
    public static int timeSpend = 25;

    private int imageWidth = 250;

    public static int[] centerX = new int[2];

    Point p;

    public GameView(Context context){

        super(context);

        getHolder().addCallback(this);

        centerX[0] = (screenWidth / 2) - (imageWidth / 2);

        thread = new MainThread(getHolder(), this);
        setFocusable(true);
    }

    @Override
    public void surfaceChanged(SurfaceHolder holder, int format, int width, int height){

    }

    @Override
    public void surfaceCreated(SurfaceHolder holder) {
        screenWidthToHeightRatio = ((float) screenHeight) / screenWidth;
        characterSprite = new CharacterSprite(BitmapFactory.decodeResource(getResources(), R.drawable.benface2), imageWidth );

        thread.setRunning(true);
        thread.start();
    }

    @Override
    public void surfaceDestroyed(SurfaceHolder holder){
        boolean retry = true;
        while (retry){
            try {
                thread.setRunning(false);
                thread.join();
            } catch(InterruptedException e){
                e.printStackTrace();
            }

            retry = false;
        }
    }

    public void update(){

        if(!MainActivity.isPaused && MainActivity.started) {
            CharacterSprite.x += xVelocity;
            CharacterSprite.y += yVelocity;
        }else{

        }
        if (((CharacterSprite.x > screenWidth - CharacterSprite.scaledImage.getWidth()) && xVelocity > 0) || ((CharacterSprite.x < 0) && xVelocity < 0)) {
            xVelocity = xVelocity * -1;
        }
        if (((CharacterSprite.y > screenHeight - CharacterSprite.scaledImage.getHeight()) && yVelocity > 0) || ((CharacterSprite.y < 0) && yVelocity < 0)) {
            yVelocity = yVelocity * -1;
        }
    }

    @Override
    public void draw(Canvas canvas){
        super.draw(canvas);
        if(canvas != null){
            characterSprite.draw(canvas);
        }
    }

}
