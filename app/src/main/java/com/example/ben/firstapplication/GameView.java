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

    public static boolean slinging = false;

    private MainThread thread;

    private CharacterSprite characterSprite;

    private Sling sling;

    private Brick brick;

    public static boolean isPaused = false;

    //double gravity;

    public static int imageWidth = 95;

    public static boolean started = false;

    public static double speed = 30;

    public static double xVelocity = 100;
    public static double yVelocity = 100;

    public static int screenWidth = Resources.getSystem().getDisplayMetrics().widthPixels;
    public static int screenHeight = Resources.getSystem().getDisplayMetrics().heightPixels;

    public static int[] centerX = new int[2];

    public static float screenWidthToHeightRatio;

    public GameView(Context context){

        super(context);

        getHolder().addCallback(this);

        thread = new MainThread(getHolder(), this);

        setFocusable(true);

        //gravity = 0.97;
    }

    @Override
    public void surfaceChanged(SurfaceHolder holder, int format, int width, int height){

    }

    @Override
    public void surfaceCreated(SurfaceHolder holder) {
        for(int i = 0; i < Brick.maxBricks; i ++){
            Brick.bricks.add(new float[5]);
            Brick.bricks.get(i)[4] = 1;
        }
        screenWidthToHeightRatio = (float) (screenHeight) / (float) screenWidth;
        characterSprite = new CharacterSprite(BitmapFactory.decodeResource(getResources(), R.drawable.tennisball), imageWidth);
        sling = new Sling(slinging);
        brick = new Brick();
        // Will  change the third parameter (projectedSling) once you create the image for it.need to
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
        System.exit(0);
    }

    public void update(){

        if(Sling.lineIsGrowing.equals("true")){
            if(Sling.lineLength < Sling.maxLineLength) {
                Sling.lineLength += Sling.lineGrowingSpeed;
            }else{
                Sling.lineIsGrowing = "done";
            }
        }

        if(!isPaused && started) {

            CharacterSprite.x += xVelocity;
            CharacterSprite.y += yVelocity;

        }
        if ((CharacterSprite.x >= screenWidth - CharacterSprite.scaledImage.getWidth())) {
           // synchronized(this) {
                CharacterSprite.x = screenWidth - CharacterSprite.scaledImage.getWidth() - 1;
                xVelocity = xVelocity * -1;
           // }
        }
        if((CharacterSprite.x <= 0)){
           // synchronized(this) {
                CharacterSprite.x = 1;
                xVelocity = xVelocity * -1;
           // }
        }
        if ((CharacterSprite.y >= screenHeight - CharacterSprite.scaledImage.getHeight())) {
            //synchronized(this) {
                characterSprite.y = screenHeight - CharacterSprite.scaledImage.getHeight() - 1;
                yVelocity = yVelocity * -1;
            //}
        }

        if(CharacterSprite.y <= 0){
            CharacterSprite.y = 1;
            yVelocity = yVelocity * -1;
        }

        /*if(Math.abs(xVelocity) <= 0.5 || Math.abs(yVelocity) <= 0.5 && started && !slinging){
            started = false;
            isPaused = false;
        }*/
    }

    @Override
    public void draw(Canvas canvas){
        super.draw(canvas);
        canvas.drawColor(Color.rgb(42, 145, 21));
        brick.draw(canvas);
        if(canvas != null){
            characterSprite.draw(canvas);
            if(slinging) {
                sling.draw(canvas);
            }
        }
    }

}
