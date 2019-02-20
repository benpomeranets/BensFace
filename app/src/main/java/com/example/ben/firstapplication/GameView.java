package com.example.ben.firstapplication;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.RectF;
import android.view.SurfaceView;
import android.view.SurfaceHolder;
import android.graphics.Color;

public class GameView extends SurfaceView implements SurfaceHolder.Callback {

    public static boolean slinging = false;

    public static int highscore = 0;

    private MainThread thread;

    public static boolean gameBeat = false;

    private CharacterSprite characterSprite;

    private Platform platform;

    private Text text;

    private Sling sling;

    private Brick brick;

    public static boolean isPaused = false;

    public static int imageWidth = 50;

    public static boolean started = false;

    public static double speed = 21;

    public static boolean gameIsDone = false;

    public static double xVelocity = 100;
    public static double yVelocity = 100;

    public static int screenWidth;

    public static int screenHeight;

    public static int score;

    public static int[] centerX = new int[2];

    public static float screenWidthToHeightRatio;

    public static RectF gameOverRect;

    float platFormToBallAngle;

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
        score = 0;
        screenWidth = Resources.getSystem().getDisplayMetrics().widthPixels;
        screenHeight = Resources.getSystem().getDisplayMetrics().heightPixels;
        for (int i = 0; i < Brick.maxBricks; i++) {
            Brick.bricks.add(new float[7]);
            Brick.bricks.get(i)[4] = Brick.lives;
            Brick.bricks.get(i)[6] = 0;
        }
        if(Text.bottomRect != null && Brick.bricks.get(1) != null) {
            gameOverRect = new RectF(0, Brick.bricks.get(Brick.bricksInvisible)[1] - 35, GameView.screenWidth, Text.bottomRect.top);
        }
        screenWidthToHeightRatio = (float) (screenHeight) / (float) screenWidth;
        characterSprite = new CharacterSprite(BitmapFactory.decodeResource(getResources(), R.drawable.tennisball), imageWidth);
        sling = new Sling(slinging);
        brick = new Brick();
        platform = new Platform();
        text = new Text();
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

        if ((CharacterSprite.x >= screenWidth - imageWidth)) {
           // synchronized(this) {
                CharacterSprite.x = screenWidth - GameView.imageWidth - 1;
                xVelocity = xVelocity * -1;
           // }
        }
        if((CharacterSprite.x <= 0)){
           // synchronized(this) {
                CharacterSprite.x = 1;
                xVelocity = xVelocity * -1;
           // }
        }

        if(platform != null && platform.platformRect != null && started && yVelocity > 0) {
            if (platform.platformRect.contains((float) CharacterSprite.x, CharacterSprite.playerRect.bottom) ||
                    platform.platformRect.contains((float) CharacterSprite.x + GameView.imageWidth, CharacterSprite.playerRect.bottom)) {

                platFormToBallAngle = ((float) Math.atan2(((float) Platform.platformRect.bottom) - ((float) (CharacterSprite.y + (float) (GameView.imageWidth / 2))), ((float) Platform.platformRect.centerX()) - ((float) (CharacterSprite.x) + (float) (GameView.imageWidth / 2))));

                if((float) Math.toDegrees(platFormToBallAngle) > -30 && (float) Math.toDegrees(platFormToBallAngle) < 0){
                    platFormToBallAngle = (float) Math.toRadians(-30);
                }else if((float) Math.toDegrees(platFormToBallAngle) < 30 && (float) Math.toDegrees(platFormToBallAngle) > 0) {
                    platFormToBallAngle = (float) Math.toRadians(30);
                }else if((float) Math.toDegrees(platFormToBallAngle) > 150){
                    platFormToBallAngle = (float) Math.toRadians(150);
                }else if((float) Math.toDegrees(platFormToBallAngle) < -150){
                    platFormToBallAngle = (float) Math.toRadians(-150);
                }

                Text.bricksHit = 0;

                synchronized(this) {
                    CharacterSprite.y = platform.platformRect.top - (GameView.imageWidth + 1);
                    GameView.xVelocity = (float) GameView.speed * (float) Math.cos((double) platFormToBallAngle) * -1;
                    GameView.yVelocity = (float) GameView.speed * (float) Math.sin((double) platFormToBallAngle) * -1;
                }
            }
        }

        if(CharacterSprite.y <= 0){
            CharacterSprite.y = 1;
            yVelocity = yVelocity * -1;
        }

        if(Brick.bricksLeft <= 0 && Text.bricksHit > 0){
            gameBeat = true;
            if(score > highscore){
                highscore = score;
            }
        }

        if(CharacterSprite.playerRect != null && Text.bottomRect != null) {
            if (CharacterSprite.playerRect.intersect(Text.bottomRect) || gameBeat && !gameIsDone) {
                gameIsDone = true;
                if(score > highscore){
                    highscore = score;
                }
            }
        }
    }

    @Override
    public void draw(Canvas canvas){
        super.draw(canvas);
        if(!gameIsDone) {
            canvas.drawColor(Color.rgb(45, 167, 188));
        }else{
            canvas.drawColor(Color.rgb(232, 141, 67));
        }
        text.draw(canvas);
        if(!gameIsDone) {
            brick.draw(canvas);
        }
        if(canvas != null){
            if((CharacterSprite.y + imageWidth < screenHeight || !gameBeat) && !gameIsDone) {
                characterSprite.draw(canvas);
            }
            if(slinging && !gameIsDone) {
                sling.draw(canvas);
            }
        }
        if(!gameIsDone) {
            platform.draw(canvas);
        }

    }

    public static void restartGame(){
        Text.scoreIncrease = 0;
        Text.scoreAlpha = 0;
        Text.bricksHit = 0;
        score = 0;
        Brick.restartArray();
        isPaused = false;
        started = false;
        slinging = false;
        gameIsDone = false;
        MainActivity.isDraggingPlatform = false;
        Platform.canStartDragging = false;
        Platform.platformX = GameView.screenWidth / 2;
        CharacterSprite.x = GameView.centerX[0];
        CharacterSprite.y = GameView.centerX[1];
    }

}
