package com.spg2018184028.sprinter;

import android.graphics.Canvas;
import android.graphics.Rect;

import com.spg2018184028.sprinter.framework.BaseScene;
import com.spg2018184028.sprinter.framework.Sprite;

public class Weapon  extends Sprite {
    static int resIds = R.mipmap.items;

    public Weapon(float x, float y) {
        super(resIds, x, y, 2.0f, 2.0f);
    }

    protected static Rect[] srcRects = {
            new Rect(0, 0, 16, 16),
            new Rect(16, 0, 32, 16),
    };

    @Override
    public void draw(Canvas canvas) {
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
            x = MainScene.player.GetX() - 2;
        }
        else
        {
            x = MainScene.player.GetX() + 2;
        }
        y= MainScene.player.GetY()+0.3f;

        fixDstRect();
    }

}