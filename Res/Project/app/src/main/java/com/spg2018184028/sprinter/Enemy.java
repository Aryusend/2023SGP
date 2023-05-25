package com.spg2018184028.sprinter;

import android.graphics.Canvas;
import android.graphics.Rect;
import android.graphics.RectF;
import java.util.Random;

import com.spg2018184028.sprinter.framework.AnimSprite;
import com.spg2018184028.sprinter.framework.BaseScene;
import com.spg2018184028.sprinter.framework.IBoxCollidable;
import com.spg2018184028.sprinter.framework.Sprite;

public class Enemy extends AnimSprite implements IBoxCollidable {

    float lifeTime = 0;
    static int[] enemy_ResIds = {
            R.mipmap.e1
    };
    static private Random r = new Random();
    private int id;
    private float moveSpeed;
    private int moveDir=0;

    public enum State{
        spawn, common, dead
    }
    public State state = State.spawn;

    public Enemy(float x, float y, int _id, float _speed) {
        super(enemy_ResIds[_id], x, y, 2.0f, 2.0f, 8, 2);
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
            }
    };
    @Override
    public void draw(Canvas canvas) {
        long now = System.currentTimeMillis();
        float time = (now - createdOn) / 1000.0f;
        Rect[] rects = null;
        if(state==State.spawn)
        {
            rects = srcRects[1];
        }
        else if(state==State.common)
        {
            rects = srcRects[0];
        }
        else if(state==State.dead)
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
        lifeTime+= BaseScene.frameTime;
        if(id==0)
        {
            if(state==State.spawn)
            {
                y-=0.01;
                if(y<6)
                {
                    state=State.common;

                    if(r.nextInt(10)<5)
                    {
                        moveDir = -1;
                    }
                    else
                    {
                        moveDir = 1;
                    }
                }
            }
            else if(state== State.common)
            {
                x += moveDir * moveSpeed;
                if(lifeTime>3)
                {
                    moveDir*=-1;
                    lifeTime = 0;
                }
            }
            else if(state== State.dead)
            {
                y+=0.04;
                if(y>9)
                {
                    MainScene scene = (MainScene) BaseScene.getTopScene();
                    scene.remove(MainScene.Layer.enemy,this);
                }
            }
        }

        if(x < 2.5)
        {
            x+=moveSpeed;
            moveDir = 1;
        }
        else if(x>24.5)
        {
            x-=moveSpeed;
            moveDir = -1;
        }
        fixDstRect();
    }

    @Override
    public RectF getCollisionRect() {
        if(state==State.common)
        {
            return dstRect;
        }
        else
        {
            return new RectF(0,0,0,0);
        }
    }
}
