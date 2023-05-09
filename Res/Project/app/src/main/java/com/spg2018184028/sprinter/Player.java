package com.spg2018184028.sprinter;

import android.graphics.Canvas;
import android.graphics.Rect;
import android.util.Log;

import com.spg2018184028.sprinter.R;
import com.spg2018184028.sprinter.framework.AnimSprite;
import com.spg2018184028.sprinter.framework.BaseScene;

public class Player extends AnimSprite {
    public static final String TAG = Player.class.getSimpleName();
    private final float ground;
    private float jumpSpeed;
    private static final float JUMP_POWER = 10.0f;
    private static final float GRAVITY = 18.0f;
    private float moveSpeed = 0.04f;
    private int moveDir = 0;
    public Player() {
        super(R.mipmap.player, 13.5f, 6, 2.0f, 2.0f, 8, 2);
        this.ground = y;
    }
    protected enum State
    {
        running, jump, falling, COUNT;
    }
    protected static Rect[][] srcRects = {
            new Rect[] {
                    new Rect(0, 0, 16, 16),
                    new Rect(16, 0, 32, 16),
            },
            new Rect[] {
                    new Rect(0, 16+1, 16, 32+1),
                    new Rect(16, 16+1, 32, 32+1),
            }
    };

    @Override
    public void draw(Canvas canvas) {
        long now = System.currentTimeMillis();
        float time = (now - createdOn) / 1000.0f;
        Rect[] rects;
        if(moveDir==-1)
        {
            rects = srcRects[1];
        }
        else
        {
            rects = srcRects[0];
        }
        int frameIndex = Math.round(time * fps) % rects.length;
        canvas.drawBitmap(bitmap, rects[frameIndex], dstRect, null);
    }

    @Override
    public void update() {
        if (state == State.jump) {
            float dy = jumpSpeed * BaseScene.frameTime;
            jumpSpeed += GRAVITY * BaseScene.frameTime;
            if (y + dy >= ground) {
                dy = ground - y;
                state = State.running;
            }
            y += dy;
        }
        x += moveSpeed * moveDir;
        fixDstRect();
    }

    protected State state = State.running;
    public void Jump()
    {
        if (state == State.running) {
            state = State.jump;
            jumpSpeed = -JUMP_POWER;
        }
        //Log.d(TAG, "jump button clicked!!");
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
}
