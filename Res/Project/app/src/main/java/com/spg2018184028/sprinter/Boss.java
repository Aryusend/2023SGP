package com.spg2018184028.sprinter;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.RectF;

import com.spg2018184028.sprinter.framework.AnimSprite;
import com.spg2018184028.sprinter.framework.BaseScene;
import com.spg2018184028.sprinter.framework.Gauge;
import com.spg2018184028.sprinter.framework.IBoxCollidable;

import java.util.Random;

public class Boss extends AnimSprite implements IBoxCollidable {

    static int[] boss_ResIds = {
            R.mipmap.b1,
    };
    static private Random r = new Random();
    private int id;
    private float moveSpeed;
    private int moveDir = -1;
    private float boundaryL = 2.5f;
    private float boundaryR = 24.5f;
    private float ground = 4;
    private float scale = 1;
    public Boolean isDamaged = false;
    private float damagedTime = 0;
    private Gauge hpGauge = new Gauge(0.1f, R.color.red,R.color.gray_600);
    public float hp;

    private int moveDirY = 1;
    private float fallSpeed = 0;
    private float jumpCoolTime = 0;
    private float coolTime = 0;

    private float chargeTime = 0;
    private Boolean isCharging = false;

    public enum State{
        spawn, common, dead
    }

    public State state = State.spawn;

    public Boss(float x, float y, int _id, float _speed, float _scale, int dir) {
        super(boss_ResIds[_id], x, y, 2.0f * _scale, 2.0f * _scale, 8, 2);
        id = _id;
        moveSpeed = _speed;
        scale = _scale;
        hp = _scale + 1;
        moveDir = dir;

        if(_scale==3)
        {
            boundaryL = 4.5f;
            boundaryR = 22.5f;
            ground = 4;
        }
        else if (_scale==2)
        {
            boundaryL = 3.5f;
            boundaryR = 23.5f;
            ground = 5;
        }
        else
        {
            boundaryL = 2.5f;
            boundaryR = 24.5f;
            ground = 6;
        }
    }
    protected static Rect[][] srcRects = {
            new Rect[] {
                    new Rect(0, 0, 16, 16),
                    new Rect(16 + 1, 0, 32 + 1, 16),
            },
            new Rect[]{
                    new Rect(0,16+1,16,32+1),
                    new Rect(16+1,16+1,32+1,32+1)
            },
    };


    @Override
    public void draw(Canvas canvas) {
        long now = System.currentTimeMillis();
        float time = (now - createdOn) / 1000.0f;
        Rect[] rects = null;
        if(state== Boss.State.spawn||isDamaged)
        {
            rects = srcRects[1];
        }
        else if(state== Boss.State.common)
        {
            rects = srcRects[0];
        }
        else if(state== Boss.State.dead)
        {
            rects = srcRects[1];
        }
        int frameIndex = 0;
        if(rects!=null)
        {
            frameIndex = Math.round(time * fps) % rects.length;
        }
        if(!isCharging)
        {
            canvas.drawBitmap(bitmap, rects[frameIndex], dstRect, null);
        }
        else
        {
            canvas.drawBitmap(bitmap, rects[1], dstRect, null);
        }


        if(state==State.common)
        {
            canvas.save();
            if(scale==3) {
                canvas.translate(x - 3f, y + 3.5f);
                canvas.scale(6.0f, 6.0f);
            }
            if(scale==2)
            {
                canvas.translate(x - 2.5f, y + 2.5f);
                canvas.scale(5.0f, 6.0f);
            }
            if(scale==1)
            {
                canvas.translate(x - 2f, y + 1.5f);
                canvas.scale(4.0f, 6.0f);
            }
            hpGauge.draw(canvas, hp / (scale + 1));
            canvas.restore();
        }
    }

    @Override
    public void update() {
        if(id==0)UpdateSlime();
        fixDstRect();
    }
    @Override
    public RectF getCollisionRect() {
        return dstRect;
    }

    void UpdateSlime()
    {
        if(state==State.spawn)
        {
            y-=0.01;
            if(y< ground)
            {
                state= Boss.State.common;
            }
        }
        if(state==State.common)
        {
            if(!isCharging)
            {
                x+= moveDir*moveSpeed;
            }
            if(x < boundaryL)
            {
                x+=moveSpeed;
                moveDir = 1;
            }
            else if(x > boundaryR)
            {
                x-=moveSpeed;
                moveDir = -1;
            }

            coolTime+=BaseScene.frameTime;
            if(coolTime>jumpCoolTime)
            {
                isCharging = true;
                chargeTime+=BaseScene.frameTime;

                if (chargeTime>2)
                {
                    fallSpeed = -16;
                    coolTime = 0;
                    jumpCoolTime = r.nextInt(5)+3;

                    chargeTime = 0;
                    isCharging = false;
                }
            }

            float dy = fallSpeed * BaseScene.frameTime;
            fallSpeed += 18 * BaseScene.frameTime;
            if (y + dy >= ground) {
                dy = ground - y;
            }
            y += dy;
        }

        if(isDamaged)
        {
            damagedTime+=BaseScene.frameTime;
            if (damagedTime > 3)
            {
                damagedTime = 0;
                isDamaged = false;
            }
        }

        if(hp==0)
        {
            MainScene.isBossStage = false;
        }
    }
}