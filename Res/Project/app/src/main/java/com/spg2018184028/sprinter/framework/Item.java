package com.spg2018184028.sprinter.framework;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.RectF;

import com.spg2018184028.sprinter.Boss;
import com.spg2018184028.sprinter.MainScene;
import com.spg2018184028.sprinter.Player;
import com.spg2018184028.sprinter.R;

public class Item extends Sprite implements IBoxCollidable {
    static int resIds = R.mipmap.items;
    public int itemId;
    private float fallSpeed = 0;
    protected enum State{
        spawn, interactive, obtained;
    }
    protected State state = State.spawn;
    protected Paint paint;

    public Item(float x, float y,int id) {
        super(resIds, x, y, 2.0f, 2.0f);
        itemId = id;
        fallSpeed = -10.0f;

        if(id==2)
        {
            paint = new Paint();
            paint.setColor(Color.BLACK);
            paint.setTextSize(2f);
        }
    }

    protected static Rect[] srcRects = {
            new Rect(0, 16, 16, 32),
            new Rect(0, 32, 16, 48),
            new Rect(16, 16, 32, 32),
            new Rect(16, 32, 32, 48),
    };

    @Override
    public void draw(Canvas canvas) {
        canvas.drawBitmap(bitmap, srcRects[itemId], dstRect, null);

        if(itemId==2&&state==State.spawn)
        {
            canvas.save();
            canvas.drawText("보스 클리어", 8.5f, 5.5f, paint);
            canvas.restore();
        }
    }
    @Override
    public void update() {
        if(state==State.spawn)
        {
            if(itemId!=2)
            {
                float dy = fallSpeed * BaseScene.frameTime;
                fallSpeed += 18 * BaseScene.frameTime;
                if (y + dy >= 6) {
                    dy = 6 - y;
                    state = State.interactive;
                }
                y += dy;
            }
            else
            {
                y+=0.02;
                if(y > 6)
                {
                    state = State.interactive;
                }
            }
        }
        if(state==State.obtained)
        {
            y+=0.04;
            if(y>9)
            {
                MainScene scene = (MainScene) BaseScene.getTopScene();
                scene.remove(MainScene.Layer.item,this);
            }
        }
        fixDstRect();
    }

    @Override
    public RectF getCollisionRect() {
        return dstRect;
    }
}