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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(new GameView(this));
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        gestureDetectorCompat = new GestureDetectorCompat(this, this);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {

        if(event.getAction() == MotionEvent.ACTION_UP && GameView.started){

            GameView.isPaused = false;
        }else if(event.getAction() == MotionEvent.ACTION_UP && GameView.slinging && Sling.lineLength >= Sling.maxLineLength){
            synchronized (this){

                GameView.started = true;
                GameView.slinging = false;

                xVelSin = (float) Math.cos((double) CharacterSprite.angle);
                yVelCos = (float) Math.sin((double) CharacterSprite.angle);

                GameView.xVelocity = (float) GameView.speed * xVelSin;
                GameView.yVelocity = (float) GameView.speed * yVelCos;

                Sling.lineIsGrowing = "false";
            }
        }else if(event.getAction() == MotionEvent.ACTION_UP && GameView.slinging && Sling.lineLength < Sling.maxLineLength){
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
    public boolean onDoubleTapEvent(MotionEvent e) {
        return false;
    }

    @Override
    public boolean onDown(MotionEvent e) {
        /*if(GameView.started) {
           // GameView.isPaused = true;
        }else*/ if(!GameView.started && e.getX() >= CharacterSprite.x && e.getX() <= CharacterSprite.x + GameView.imageWidth && e.getY() >= CharacterSprite.y &&
                e.getY() <= CharacterSprite.y + GameView.imageWidth){
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
        if(GameView.slinging){
           Sling.lineIsGrowing = "true";

           mouseX = e2.getX();
           mouseY = e2.getY();
           CharacterSprite.angle = ((float) Math.atan2(((float) mouseY) - ((float) (CharacterSprite.y) + (float) (GameView.imageWidth / 2)), ((float) mouseX) - ((float) (CharacterSprite.x) + (float) (GameView.imageWidth / 2))));
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
