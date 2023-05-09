package com.spg2018184028.sprinter.framework;

import android.graphics.Canvas;
import android.graphics.Rect;
import android.graphics.RectF;

import com.spg2018184028.sprinter.MainScene;
import com.spg2018184028.sprinter.R;

public class Item extends Sprite implements IBoxCollidable {
    static int resIds = R.mipmap.items;
    private int itemId;

    public Item(float x, float y,int id) {
        super(resIds, x, y, 2.0f, 2.0f);
        itemId = id;
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
    }

    @Override
    public RectF getCollisionRect() {
        return dstRect;
    }
}