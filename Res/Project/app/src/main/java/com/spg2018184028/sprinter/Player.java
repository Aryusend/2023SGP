package com.spg2018184028.sprinter;

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
    public Player() {
        super(R.mipmap.player, 13.5f, 6, 2.0f, 2.0f, 8, 2);
        this.ground = y;
    }
    protected enum State
    {
        running, jump, falling, COUNT;
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
            fixDstRect();
        }
    }

    protected State state = State.running;
    public void Jump()
    {
        if (state == State.running) {
            state = State.jump;
            jumpSpeed = -JUMP_POWER;
        }
        Log.d(TAG, "jump button clicked!!");
    }

}
