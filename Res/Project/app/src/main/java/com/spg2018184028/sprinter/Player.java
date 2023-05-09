package com.spg2018184028.sprinter;

import android.util.Log;

import com.spg2018184028.sprinter.R;
import com.spg2018184028.sprinter.framework.AnimSprite;
public class Player extends AnimSprite {
    public static final String TAG = Player.class.getSimpleName();
    public Player() {
        super(R.mipmap.player, 13.5f, 6, 2.0f, 2.0f, 8, 2);
    }

    protected enum State
    {
        running, jump;
    }

    protected State state = State.jump;

    public void Jump()
    {
        if (state == State.running) {
            state = State.jump;
        }
        else
        {
            state = State.running;
        }
        Log.d(TAG, "jump button clicked!!");
    }

}
