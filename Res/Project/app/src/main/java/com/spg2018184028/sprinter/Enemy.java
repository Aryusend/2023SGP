package com.spg2018184028.sprinter;

import android.graphics.Canvas;
import android.graphics.Rect;
import android.graphics.RectF;

import com.spg2018184028.sprinter.framework.AnimSprite;
import com.spg2018184028.sprinter.framework.IBoxCollidable;
import com.spg2018184028.sprinter.framework.Sprite;

public class Enemy extends AnimSprite implements IBoxCollidable {
    static int resIds = R.mipmap.e1;

    public Enemy(float x, float y) {
        super(R.mipmap.e1, x, y, 2.0f, 2.0f, 8, 2);
    }

    protected static Rect[][] srcRects = {
            new Rect[] {
                    new Rect(0, 0, 16, 16),
                    new Rect(16 + 1, 0, 32 + 1, 16),
            }
    };

    @Override
    public void draw(Canvas canvas) {
        long now = System.currentTimeMillis();
        float time = (now - createdOn) / 1000.0f;
        Rect[] rects;
        rects = srcRects[0];
        int frameIndex = Math.round(time * fps) % rects.length;
        canvas.drawBitmap(bitmap, rects[frameIndex], dstRect, null);
    }
    @Override
    public void update() {
    }

    @Override
    public RectF getCollisionRect() {
        return dstRect;
    }
}
