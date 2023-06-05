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

    protected Paint bluePaint;
    protected Paint bluePaint2;

    public Pause(float x, float y) {
        super(resIds, x, y, 27, 9);


        bluePaint = new Paint();
        bluePaint.setColor(Color.BLUE);
        bluePaint.setTextSize(3f);

        bluePaint2 = new Paint();
        bluePaint2.setColor(Color.BLUE);
        bluePaint2.setTextSize(2f);
    }

    protected static Rect[] srcRects = {
            new Rect(0, 0, 80, 192),
    };

    @Override
    public void draw(Canvas canvas) {
        if(MainScene.isGamePause)
        {
            canvas.drawBitmap(bitmap, srcRects[0], dstRect, null);
            if(MainScene.isLevelUpPause)
            {
                canvas.save();
                canvas.drawText("능력치 업", 9f, 2f, bluePaint2);
                canvas.restore();
            }
            else
            {
                canvas.save();
                canvas.drawText("일시정지", 8f, 5.5f, bluePaint);
                canvas.restore();
            }
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