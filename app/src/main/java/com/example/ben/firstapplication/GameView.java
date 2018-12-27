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

    private int xVelocity = 10;
    private int yVelocity = 5;
    private int screenWidth = Resources.getSystem().getDisplayMetrics().widthPixels;
    private int screenHeight = Resources.getSystem().getDisplayMetrics().heightPixels;

    public static int screenWidthToHeightRatio;

    public GameView(Context context){
        super(context);

        getHolder().addCallback(this);

        thread = new MainThread(getHolder(), this);
        setFocusable(true);
    }

    @Override
    public void surfaceChanged(SurfaceHolder holder, int format, int width, int height){

    }

    @Override
    public void surfaceCreated(SurfaceHolder holder) {
        screenWidthToHeightRatio = screenHeight / screenWidth;
        characterSprite = new CharacterSprite(BitmapFactory.decodeResource(getResources(), R.drawable.benface2), 500 );

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
        CharacterSprite.x += xVelocity;
        CharacterSprite.y += yVelocity;
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
