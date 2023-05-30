package com.spg2018184028.sprinter;

import android.graphics.Canvas;
import android.graphics.Rect;
import android.graphics.RectF;

import com.spg2018184028.sprinter.framework.BaseScene;
import com.spg2018184028.sprinter.framework.IBoxCollidable;
import com.spg2018184028.sprinter.framework.Sprite;

public class EnemyBullet extends Sprite implements IBoxCollidable {

    static int resIds = R.mipmap.ebullet;
    private int bulletId;

    private int moveDir;

    private float moveSpeed;
    private float bulletScale = 1;
    public EnemyBullet(float x, float y,int id, int dir, float speed, float scale) {
        super(resIds, x, y, 1.0f * scale, 1.0f * scale);
        bulletId = id;
        moveDir = dir;
        moveSpeed = speed;
        bulletScale = scale;
    }

    protected static Rect[] srcRects = {
            new Rect(0, 0, 16, 16),
            new Rect(0, 16, 16, 32),
            new Rect(0, 32, 16, 48),
    };

    @Override
    public void draw(Canvas canvas) {
        canvas.drawBitmap(bitmap, srcRects[bulletId], dstRect, null);
    }

    @Override
    public void update() {
        if(x > 2.5 && x < 24.5)
        {
            x+=moveSpeed * moveDir;
        }
        else
        {
            MainScene scene = (MainScene) BaseScene.getTopScene();
            scene.remove(MainScene.Layer.ebullet,this);
        }
        fixDstRect();
    }

    @Override
    public RectF getCollisionRect() {
        return dstRect;
    }
}
