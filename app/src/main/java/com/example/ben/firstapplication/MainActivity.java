package com.example.ben.firstapplication;

import android.app.Activity;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.support.v4.view.GestureDetectorCompat;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.Window;
import android.view.WindowManager;

import java.util.Random;

public class MainActivity extends Activity implements GestureDetector.OnDoubleTapListener, GestureDetector.OnGestureListener{

    GestureDetectorCompat gestureDetectorCompat;

    public static float xVelSin;

    public static float yVelCos;

    public static double mouseX, mouseY;

    public static boolean isDraggingPlatform = false;

    @Override
    public boolean onDoubleTapEvent(MotionEvent e) {
        if(GameView.gameIsDone){
            GameView.gameIsDone = false;
            GameView.restartGame();
        }
        return false;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(new GameView(this));
        try {
            setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        }catch(Exception e){
            System.exit(0);
        }
        gestureDetectorCompat = new GestureDetectorCompat(this, this);
        getWindow().setFlags(
                WindowManager.LayoutParams.FLAG_HARDWARE_ACCELERATED,
                WindowManager.LayoutParams.FLAG_HARDWARE_ACCELERATED);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {

        if(event.getAction() == MotionEvent.ACTION_UP && GameView.started && !GameView.gameIsDone){
            isDraggingPlatform = false;
        }else if(event.getAction() == MotionEvent.ACTION_UP && GameView.slinging && Sling.lineLength >= Sling.maxLineLength && !GameView.gameIsDone){
            synchronized (this){

                GameView.started = true;
                GameView.slinging = false;

                xVelSin = (float) Math.cos((double) Sling.constrainedAngle);
                yVelCos = (float) Math.sin((double) Sling.constrainedAngle);

                GameView.xVelocity = (float) GameView.speed * xVelSin;
                GameView.yVelocity = (float) GameView.speed * yVelCos;

                Sling.lineIsGrowing = "false";
            }
        }else if(event.getAction() == MotionEvent.ACTION_UP && GameView.slinging && Sling.lineLength < Sling.maxLineLength && !GameView.gameIsDone){
            GameView.started = false;
            GameView.slinging = false;

            Sling.lineIsGrowing = "false";
            Sling.lineLength = 0;
        }

        gestureDetectorCompat.onTouchEvent(event);

        return super.onTouchEvent(event);
    }

    @Override
    public boolean onSingleTapConfirmed(MotionEvent e) {

        return false;
    }

    @Override
    public boolean onDoubleTap(MotionEvent e) {
        return false;
    }

    @Override
    public boolean onDown(MotionEvent e) {

        if(!GameView.started){
            GameView.slinging = true;
        }

        return false;
    }

    @Override
    public void onShowPress(MotionEvent e) {

    }

    @Override
    public boolean onSingleTapUp(MotionEvent e) {
        return false;
    }

    @Override
    public boolean onScroll(MotionEvent e1, MotionEvent e2, float distanceX, float distanceY) {
        mouseX = e2.getX();
        mouseY = e2.getY();
        if(GameView.slinging){
           Sling.lineIsGrowing = "true";

           CharacterSprite.angle = ((float) Math.atan2(((float) mouseY) - ((float) (CharacterSprite.y) + (float) (GameView.imageWidth / 2)), ((float) mouseX) - ((float) (CharacterSprite.x) + (float) (GameView.imageWidth / 2))));
        }

        if(GameView.started) {
            isDraggingPlatform = true;
        }

        return false;
    }

    @Override
    public void onLongPress(MotionEvent e) {

    }

    @Override
    public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY) {
        return false;
    }

}
