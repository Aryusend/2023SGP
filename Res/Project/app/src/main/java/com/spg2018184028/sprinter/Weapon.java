package com.spg2018184028.sprinter;

import android.graphics.Canvas;
import android.graphics.Rect;
import android.graphics.RectF;

import com.spg2018184028.sprinter.framework.BaseScene;
import com.spg2018184028.sprinter.framework.IBoxCollidable;
import com.spg2018184028.sprinter.framework.Sprite;

public class Weapon extends Sprite implements IBoxCollidable {
    static int resIds = R.mipmap.items;
    public static float widthPlus = 0.0f;
    public static float xOffset = 0;
    public static float yOffset = 0;
    public Weapon(float x, float y) {
        super(resIds, x, y, 2.0f, 2.0f);
    }

    protected static Rect[] srcRects = {
            new Rect(0, 0, 16, 15),
            new Rect(16, 0, 32, 15),
    };

    @Override
    public void draw(Canvas canvas) {
        if(!MainScene.isGamePause)
            dstRect = new RectF(dstRect.left,dstRect.top,dstRect.right + widthPlus,dstRect.bottom + widthPlus);
        if (MainScene.player.GetDir() == -1) {
            canvas.drawBitmap(bitmap, srcRects[1], dstRect, null);
        } else {
            canvas.drawBitmap(bitmap, srcRects[0], dstRect, null);
        }
    }
    @Override
    public void update() {

        if(MainScene.player.GetDir() == -1)
        {
            x = MainScene.player.GetX() - 2 + xOffset;
        }
        else
        {
            x = MainScene.player.GetX() + 2;
        }
        y= MainScene.player.GetY()+0.3f + yOffset;

        fixDstRect();
    }

    @Override
    public RectF getCollisionRect() {
        return dstRect;
    }

}