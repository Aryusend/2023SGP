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
            R.mipmap.e1,
            R.mipmap.e2,
            R.mipmap.e3,
            R.mipmap.e4,
            R.mipmap.e5,
            R.mipmap.e6,
            R.mipmap.e7,
            R.mipmap.e8,
            R.mipmap.e9,
    };
    static private Random r = new Random();
    private int id;
    private float moveSpeed;
    private int moveDir=0;

    public enum State{
        spawn, common, dead
    }
    public Boss(float x, float y, int _id, float _speed) {
        super(boss_ResIds[_id], x, y, 2.0f, 2.0f, 8, 2);
        id = _id;
        moveSpeed = _speed;
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
        Rect[] rects;
        rects = srcRects[id];
    }

    @Override
    public void update() {
        fixDstRect();
    }
    @Override
    public RectF getCollisionRect() {
        return dstRect;
    }
}
