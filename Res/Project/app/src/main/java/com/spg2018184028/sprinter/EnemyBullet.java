package com.spg2018184028.sprinter;

import android.graphics.Canvas;
import android.graphics.Rect;
import android.graphics.RectF;

import com.spg2018184028.sprinter.framework.IBoxCollidable;
import com.spg2018184028.sprinter.framework.Sprite;

public class EnemyBullet extends Sprite implements IBoxCollidable {

    static int resIds = R.mipmap.items;
    public int bulletId;

    public EnemyBullet(float x, float y,int id) {
        super(resIds, x, y, 2.0f, 2.0f);
        bulletId = id;
    }

    protected static Rect[] srcRects = {
            new Rect(0, 16, 16, 32),
    };

    @Override
    public void draw(Canvas canvas) {
        canvas.drawBitmap(bitmap, srcRects[bulletId], dstRect, null);
    }

    @Override
    public void update() {
        //fixDstRect();
    }

    @Override
    public RectF getCollisionRect() {
        return dstRect;
    }
}
