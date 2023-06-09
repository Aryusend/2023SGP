package com.spg2018184028.sprinter;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.RectF;
import android.util.Log;

import com.spg2018184028.sprinter.R;
import com.spg2018184028.sprinter.framework.AnimSprite;
import com.spg2018184028.sprinter.framework.BaseScene;
import com.spg2018184028.sprinter.framework.Gauge;
import com.spg2018184028.sprinter.framework.IBoxCollidable;

import java.util.Random;

public class Player extends AnimSprite implements IBoxCollidable {
    public static final String TAG = Player.class.getSimpleName();
    private final float ground;
    private float jumpSpeed;
    private static final float JUMP_POWER = 10.0f;
    private static final float GRAVITY = 18.0f;
    public float moveSpeed = 0.04f;
    private int moveDir = 0;
    public int stageLevel = 0;

    private int stageTime = 40;
    private Gauge hpGauge = new Gauge(0.1f, R.color.red,R.color.gray_600);
    public float maxHp = 10;
    public float curHp = 0;

    private Gauge expGauge = new Gauge(0.1f, R.color.yellow,R.color.white);
    public float reqExp;
    public float curExp = 0;

    public int expPlus = 1;
    protected Paint timePaint;
    private float eTime = 0;
    public Player() {
        super(R.mipmap.player, 13.5f, 6, 2.0f, 2.0f, 8, 2);
        this.ground = y;
        Init();

        timePaint = new Paint();
        timePaint.setColor(Color.BLACK);
        timePaint.setTextSize(0.5f);
    }
    public enum State
    {
        running, jump, falling, COUNT;
    }
    public State state = State.running;

    public Boolean isDamaged = false;
    private float damagedTime = 0;
    protected static Rect[][] srcRects = {
            new Rect[] {
                    new Rect(0, 0, 16, 16),
                    new Rect(16, 0, 32, 16),
            },
            new Rect[] {
                    new Rect(0, 16+1, 16, 32+1),
                    new Rect(16, 16+1, 32, 32+1),
            },
            new Rect[]{
                    new Rect(0, 32+1, 16, 48+1),
                    new Rect(16, 32+1, 32, 48+1),
            },
            new Rect[]{
                    new Rect(0, 48+2, 16, 64+2),
                    new Rect(16, 48+2, 32, 64+2),
            }
    };
    @Override
    public void draw(Canvas canvas) {
        long now = System.currentTimeMillis();
        float time = (now - createdOn) / 1000.0f;
        Rect[] rects;
        if(moveDir==-1)
        {
            if(!isDamaged)
            {
                rects = srcRects[1];
            }
            else
            {
                rects = srcRects[3];
            }

        }
        else
        {
            if(!isDamaged)
            {
                rects = srcRects[0];
            }
            else
            {
                rects = srcRects[2];
            }

        }
        int frameIndex = Math.round(time * fps) % rects.length;
        if(moveDir!=0)
        {
            canvas.drawBitmap(bitmap, rects[frameIndex], dstRect, null);
        }
        else
        {
            canvas.drawBitmap(bitmap, rects[0], dstRect, null);
        }

        canvas.save();
        canvas.translate(15.0f, 1f);
        canvas.scale(10.0f, 10.0f);
        hpGauge.draw(canvas, curHp / maxHp);
        canvas.restore();

        canvas.save();
        canvas.translate(0f, 0.2f);
        canvas.scale(27.0f, 6.0f);
        expGauge.draw(canvas, curExp / reqExp);
        canvas.restore();

        if(!MainScene.isBossStage)
        {
            canvas.save();
            canvas.drawText("다", 1.0f, 1.6f, timePaint);
            canvas.drawText("음", 2.0f, 1.6f, timePaint);
            canvas.drawText("적", 3.0f, 1.6f, timePaint);
            canvas.drawText("등", 4.0f, 1.6f, timePaint);
            canvas.drawText("장", 5.0f, 1.6f, timePaint);
            canvas.drawText("까", 6.0f, 1.6f, timePaint);
            canvas.drawText("지", 7.0f, 1.6f, timePaint);
            canvas.restore();

            canvas.save();
            canvas.drawText((stageTime - (int)eTime)/10+"", 9.0f, 1.6f, timePaint);
            canvas.drawText((stageTime - (int)eTime)%10+"", 10.0f, 1.6f, timePaint);
            canvas.drawText("초", 11.0f, 1.6f, timePaint);
            canvas.restore();
        }
    }

    @Override
    public void update() {
        if(!MainScene.isBossStage)
        {
            eTime += BaseScene.frameTime;
        }
        if(eTime>stageTime)
        {
            eTime = 0;
            if(stageLevel<10)
            {
                stageLevel++;
                if(stageLevel==4 || stageLevel==7 || stageLevel == 10)
                {
                    MainScene.isBossStage = true;
                }
            }
        }
        if (state == State.jump) {
            float dy = jumpSpeed * BaseScene.frameTime;
            jumpSpeed += GRAVITY * BaseScene.frameTime;
            if (y + dy >= ground) {
                dy = ground - y;
                state = State.running;
            }
            y += dy;
        }
        //String msg = String.format("x : %f / y : %f",x,y);
        //Log.d(TAG, msg);
        x += moveSpeed * moveDir;
        if(x < 2.5)
        {
            moveDir = -1;
            x+=moveSpeed;
        }
        else if(x>24.5)
        {
            moveDir = 1;
            x-=moveSpeed;
        }
        if(curExp>=reqExp)
        {
            reqExp= (float) Math.floor((double) (reqExp + reqExp/2));
            curExp = 0;

            MainScene.isLevelUpPause = true;
            MainScene.isGamePause = true;

            Random r = new Random();
            int[] rs = new int[3];
            for(int i=0; i<3; i++)
            {
                if(curHp!=maxHp)
                {
                    rs[i] = r.nextInt(6);
                }
                else
                {
                    rs[i] = r.nextInt(5)+1;
                }
            }
            MainScene.levelUpSelect = rs;
        }

        if(isDamaged)
        {
            damagedTime += BaseScene.frameTime;
            if(damagedTime>1.0f)
            {
                damagedTime = 0;
                isDamaged = false;
            }
        }

        if(curHp<=0)
        {
            MainScene.isGameOver=true;
            MainScene.isGamePause = true;
        }

        fixDstRect();
    }
    public void Jump()
    {
        if (state == State.running) {
            state = State.jump;
            jumpSpeed = -JUMP_POWER;
        }
        //Log.d(TAG, "jump button clicked!!");
    }
    @Override
    public RectF getCollisionRect() {
        return dstRect;
    }

    public void ChangeMoveDir(int dir)
    {
        moveDir = dir;
    }

    public float GetX()
    {
        return x;
    }

    public float GetY()
    {
        return y;
    }

    public float GetDir()
    {
        return moveDir;
    }

    public void Init()
    {
        stageLevel = 0;
        moveDir = 0;
        x= 13.5f;
        y=6f;
        maxHp = 10;
        curHp = maxHp;
        reqExp = 10;
        curExp = 0;
        expPlus = 1;
        eTime = 0;
        isDamaged =false;
        state = State.running;
        moveSpeed =0.04f;
    }


}
