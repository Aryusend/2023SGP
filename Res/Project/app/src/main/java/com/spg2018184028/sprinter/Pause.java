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
    protected Paint borderPaint;
    protected Paint borderPaint2;
    protected Paint blackPaint;

    protected static RectF[] Rects = {
            new RectF(1.5f, 3, 8.5f, 8),
            new RectF(10, 3, 17, 8),
            new RectF(18.5f, 3, 25.5f, 8),
    };

    public Pause(float x, float y) {
        super(resIds, x, y, 27, 9);
        SetPaint();
    }

    protected static Rect[] srcRects = {
            new Rect(0, 0, 80, 192),
    };

    private String[] selectOption ={
            "HP회복",
            "이동속도",
            "최대HP",
            "무기길이",
            "경험치배율",
            "점수100"
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
                canvas.drawText("능력치 업", 9f, 2f, bluePaint2);
                canvas.restore();

                for(int i=0; i<3; i++)
                {
                    canvas.save();
                    canvas.drawRect(Rects[i], borderPaint);
                    canvas.restore();
                }

                canvas.save();
                canvas.drawText(selectOption[0], 2.5f, 6f, blackPaint);
                canvas.restore();
                canvas.save();
                canvas.drawText(selectOption[5], 11f, 6f, blackPaint);
                canvas.restore();
                canvas.save();
                canvas.drawText(selectOption[4], 19.5f, 6f, blackPaint);
                canvas.restore();


                canvas.save();
                canvas.drawRect(Rects[MainScene.levelUpIndex], borderPaint2);
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

    private void SetPaint()
    {
        bluePaint = new Paint();
        bluePaint.setColor(Color.BLUE);
        bluePaint.setTextSize(3f);

        bluePaint2 = new Paint();
        bluePaint2.setColor(Color.BLUE);
        bluePaint2.setTextSize(2f);

        borderPaint = new Paint();
        borderPaint.setColor(Color.WHITE);
        borderPaint.setStyle(Paint.Style.FILL);
        borderPaint.setStrokeWidth(0.1f);

        borderPaint2 = new Paint();
        borderPaint2.setColor(Color.RED);
        borderPaint2.setStyle(Paint.Style.STROKE);
        borderPaint2.setStrokeWidth(0.1f);


        blackPaint = new Paint();
        blackPaint.setColor(Color.BLACK);
        blackPaint.setTextSize(1f);
    }

}