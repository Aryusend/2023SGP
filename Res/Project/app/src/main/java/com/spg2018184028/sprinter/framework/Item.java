package com.spg2018184028.sprinter.framework;

import android.graphics.Canvas;
import android.graphics.Rect;
import android.graphics.RectF;

import com.spg2018184028.sprinter.MainScene;
import com.spg2018184028.sprinter.Player;
import com.spg2018184028.sprinter.R;

public class Item extends Sprite implements IBoxCollidable {
    static int resIds = R.mipmap.items;
    private int itemId;

    private float fallSpeed = 0;

    protected enum State{
        spawn, interactive;
    }
    protected State state = State.spawn;

    public Item(float x, float y,int id) {
        super(resIds, x, y, 2.0f, 2.0f);
        itemId = id;
        fallSpeed = -10.0f;
    }

    protected static Rect[] srcRects = {
            new Rect(0, 16, 16, 32),
            new Rect(0, 32, 16, 48),
    };

    @Override
    public void draw(Canvas canvas) {
        canvas.drawBitmap(bitmap, srcRects[itemId], dstRect, null);
    }
    @Override
    public void update() {
        if(state==State.spawn)
        {
            float dy = fallSpeed * BaseScene.frameTime;
            fallSpeed += 18 * BaseScene.frameTime;
            if (y + dy >= 6) {
                dy = 6 - y;
                state = State.interactive;
            }
            y += dy;
        }
        fixDstRect();
    }

    @Override
    public RectF getCollisionRect() {
        return dstRect;
    }
}