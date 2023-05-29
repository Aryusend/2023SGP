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
    private int moveDir=0;
    private float scale = 1;
    public Boolean isDamaged = false;
    private float damagedTime = 0;
    public int hp;

    public enum State{
        spawn, common, dead
    }

    public State state = State.spawn;

    public Boss(float x, float y, int _id, float _speed, float _scale) {
        super(boss_ResIds[_id], x, y, 2.0f * _scale, 2.0f * _scale, 8, 2);
        id = _id;
        moveSpeed = _speed;
        scale = _scale;
        hp = (int)_scale;
    }
    protected static Rect[][] srcRects = {
            new Rect[] {
                    new Rect(0, 0, 16, 16),
                    new Rect(16 + 1, 0, 32 + 1, 16),
            },
            new Rect[]{
                    new Rect(0,16+1,16,32+1)
            },
            new Rect[]{
                    new Rect(16+1,16+1,32+1,32+1)
            },
            new Rect[] {
                    new Rect(0, 32+1, 16, 48+1),
                    new Rect(16 + 1, 32+1, 32 + 1, 48+1),
            },
    };

    @Override
    public void draw(Canvas canvas) {
        long now = System.currentTimeMillis();
        float time = (now - createdOn) / 1000.0f;
        Rect[] rects = null;
        if(state== Boss.State.spawn)
        {
            rects = srcRects[1];
        }
        else if(state== Boss.State.common)
        {
            rects = srcRects[0];
        }
        else if(state== Boss.State.dead)
        {
            rects = srcRects[2];
        }
        int frameIndex = 0;
        if(rects!=null)
        {
            frameIndex = Math.round(time * fps) % rects.length;
        }
        canvas.drawBitmap(bitmap, rects[frameIndex], dstRect, null);
    }

    @Override
    public void update() {
        if(state== Boss.State.spawn)
        {
            y-=0.01;
            if(y<4)
            {
                state= Boss.State.common;
            }
        }

        if(isDamaged)
        {
            damagedTime+=BaseScene.frameTime;
            if (damagedTime > 2)
            {
                damagedTime = 0;
                isDamaged = false;
            }
        }

        if(hp==0)
        {
            MainScene.isBossStage = false;
        }
        fixDstRect();
    }
    @Override
    public RectF getCollisionRect() {
        return dstRect;
    }
}
