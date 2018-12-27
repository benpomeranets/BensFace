package com.example.ben.firstapplication;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.view.SurfaceView;
import android.view.SurfaceHolder;
import android.graphics.Color;
import android.graphics.Paint;
import android.widget.ImageView;
import android.widget.LinearLayout;

public class GameView extends SurfaceView implements SurfaceHolder.Callback {

    private MainThread thread;

    private CharacterSprite characterSprite;

    public static int xVelocity = 10;
    public static int yVelocity = 5;
    private int screenWidth = Resources.getSystem().getDisplayMetrics().widthPixels;
    private int screenHeight = Resources.getSystem().getDisplayMetrics().heightPixels;

    public static float screenWidthToHeightRatio;

    private int imageWidth = 500;

    public static int[] centerX = new int[2];

    public GameView(Context context){

        super(context);

        getHolder().addCallback(this);

        centerX[0] = (screenWidth / 2) - (imageWidth / 2);
        centerX[1] = (screenHeight / 2);

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
        if ((CharacterSprite.x > screenWidth - CharacterSprite.scaledImage.getWidth()) || (CharacterSprite.x < 0)) {
            xVelocity = xVelocity * -1;
        }
        if ((CharacterSprite.y > screenHeight - CharacterSprite.scaledImage.getHeight()) || (CharacterSprite.y < 0)) {
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
