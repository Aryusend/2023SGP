package com.spg2018184028.sprinter;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.RectF;

import com.spg2018184028.sprinter.framework.BaseScene;
import com.spg2018184028.sprinter.framework.IBoxCollidable;
import com.spg2018184028.sprinter.framework.Item;
import com.spg2018184028.sprinter.framework.Sprite;

public class Pause extends Sprite implements IBoxCollidable {
    static int resIds = R.mipmap.pausebg;

    protected Paint paint;

    public Pause(float x, float y) {
        super(resIds, x, y, 27, 9);
    }

    protected static Rect[] srcRects = {
            new Rect(0, 0, 80, 192),
    };

    @Override
    public void draw(Canvas canvas) {
        if(MainScene.isGamePause)
        {
            canvas.drawBitmap(bitmap, srcRects[0], dstRect, null);
        }
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