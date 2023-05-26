package com.spg2018184028.sprinter;

import android.graphics.Canvas;
import android.graphics.Rect;
import android.graphics.RectF;
import java.util.Random;

import com.spg2018184028.sprinter.framework.AnimSprite;
import com.spg2018184028.sprinter.framework.BaseScene;
import com.spg2018184028.sprinter.framework.IBoxCollidable;
import com.spg2018184028.sprinter.framework.Item;
import com.spg2018184028.sprinter.framework.Sprite;

public class Enemy extends AnimSprite implements IBoxCollidable {
    float lifeTime = 0;
    static int[] enemy_ResIds = {
            R.mipmap.e1,
            R.mipmap.e2,
            R.mipmap.e3,
            R.mipmap.e4,
            R.mipmap.e5,
            R.mipmap.e6,
            R.mipmap.e7,
            R.mipmap.e8,
            R.mipmap.e9,
    };
    static private Random r = new Random();
    private int id;
    private float moveSpeed;
    private int moveDir=0;
    private int moveDirY = 1;

    private float fallSpeed = 0;
    private float jumpCoolTime = 0;
    private float jumpTime = 0;
    public enum State{
        spawn, common, dead
    }
    public State state = State.spawn;
    public Enemy(float x, float y, int _id, float _speed) {
        super(enemy_ResIds[_id], x, y, 2.0f, 2.0f, 8, 2);
        id = _id;
        moveSpeed = _speed;
    }
    protected static Rect[][] srcRects = {
            //Rabbit
            new Rect[] {
                    new Rect(0, 0, 16, 16),
                    new Rect(16 + 1, 0, 32 + 1, 16),
            },
            new Rect[]{
                    new Rect(0,16+1,16,32+1)
            },
            new Rect[]{
                    new Rect(16+1,16+1,32+1,32+1)
            },
            new Rect[] {
                    new Rect(0, 32+1, 16, 48+1),
                    new Rect(16 + 1, 32+1, 32 + 1, 48+1),
            },
    };
    @Override
    public void draw(Canvas canvas) {
        long now = System.currentTimeMillis();
        float time = (now - createdOn) / 1000.0f;
        if(id<3)
        {
            DrawRabbit(canvas, time);
        }
        else if(id>=3 && id < 6)
        {
            DrawBat(canvas,time);
        }
        else
        {
            DrawWizard(canvas,time);
        }
    }
    @Override
    public void update() {
        lifeTime+= BaseScene.frameTime;
        if(id<3)
        {
            UpdateRabit();
        }
        else if(id>=3 && id < 6)
        {
            UpdateBat();
        }
        else
        {
            UpdateWizard();
        }
        fixDstRect();
    }

    @Override
    public RectF getCollisionRect() {
        return dstRect;
    }

    private void DrawRabbit(Canvas canvas, float time)
    {
        Rect[] rects = null;
        if(state==State.spawn)
        {
            rects = srcRects[1];
        }
        else if(state==State.common)
        {
            rects = srcRects[0];
        }
        else if(state==State.dead)
        {
            rects = srcRects[2];
        }
        int frameIndex = 0;
        if(rects!=null)
        {
            frameIndex = Math.round(time * fps) % rects.length;
        }
        canvas.drawBitmap(bitmap, rects[frameIndex], dstRect, null);
    }

    private void UpdateRabit()
    {
        if(id==0)
        {
            if(state==State.spawn)
            {
                y-=0.01;
                if(y<6)
                {
                    state=State.common;

                    if(r.nextInt(10)<5)
                    {
                        moveDir = -1;
                    }
                    else
                    {
                        moveDir = 1;
                    }
                }
            }
            else if(state== State.common)
            {
                x += moveDir * moveSpeed;
                if(lifeTime>3)
                {
                    moveDir*=-1;
                    lifeTime = 0;
                }
            }
            else if(state== State.dead)
            {
                y+=0.04;
                if(y>9)
                {
                    MainScene scene = (MainScene) BaseScene.getTopScene();
                    scene.remove(MainScene.Layer.enemy,this);
                }
            }
        }
        else if(id==1)
        {
            if(state==State.spawn)
            {
                y-=0.01;
                if(y<6)
                {
                    state=State.common;

                    if(r.nextInt(10)<5)
                    {
                        moveDir = -1;
                    }
                    else
                    {
                        moveDir = 1;
                    }
                }
            }
            else if(state== State.common)
            {
                x += moveDir * moveSpeed;
            }
            else if(state== State.dead)
            {
                y+=0.04;
                if(y>9)
                {
                    MainScene scene = (MainScene) BaseScene.getTopScene();
                    scene.remove(MainScene.Layer.enemy,this);
                }
            }
        }
        else if(id==2)
        {
            if(state==State.spawn)
            {
                y-=0.01;
                if(y<6)
                {
                    state=State.common;

                    if(r.nextInt(10)<5)
                    {
                        moveDir = -1;
                    }
                    else
                    {
                        moveDir = 1;
                    }
                    jumpCoolTime = r.nextInt(5)+1;
                }
            }
            else if(state== State.common)
            {
                x += moveDir * moveSpeed;

                jumpTime+=BaseScene.frameTime;
                if(jumpTime>jumpCoolTime)
                {
                    fallSpeed = -10;
                    jumpTime = 0;
                    jumpCoolTime = r.nextInt(5)+1;
                }

                float dy = fallSpeed * BaseScene.frameTime;
                fallSpeed += 18 * BaseScene.frameTime;
                if (y + dy >= 6) {
                    dy = 6 - y;
                }
                y += dy;
            }
            else if(state== State.dead)
            {
                y+=0.04;
                if(y>9)
                {
                    MainScene scene = (MainScene) BaseScene.getTopScene();
                    scene.remove(MainScene.Layer.enemy,this);
                }
            }
        }

        if(x < 2.5)
        {
            x+=moveSpeed;
            moveDir = 1;
        }
        else if(x>24.5)
        {
            x-=moveSpeed;
            moveDir = -1;
        }
    }

    private void DrawBat(Canvas canvas, float time)
    {
        Rect[] rects = null;
        if(state==State.spawn)
        {
            rects = srcRects[1];
        }
        else if(state==State.common)
        {
            rects = srcRects[0];
        }
        else if(state==State.dead)
        {
            rects = srcRects[2];
        }
        int frameIndex = 0;
        if(rects!=null)
        {
            frameIndex = Math.round(time * fps) % rects.length;
        }
        canvas.drawBitmap(bitmap, rects[frameIndex], dstRect, null);
    }

    private void UpdateBat()
    {
        if(id==3)
        {
            if(state==State.spawn)
            {
                y+=0.01;
                if(y>3.5)
                {
                    state=State.common;
                }
            }
            else if(state== State.dead)
            {
                y+=0.04;
                if(y>9)
                {
                    MainScene scene = (MainScene) BaseScene.getTopScene();
                    scene.remove(MainScene.Layer.enemy,this);
                }
            }
        }
        if(id==4)
        {
            if(state==State.spawn)
            {
                y+=0.01;
                if(y>3.5)
                {
                    state=State.common;
                }
            }
            else if(state== State.common)
            {
                if(MainScene.player.GetX()>x)
                {
                    moveDir = 1;
                }
                else
                {
                    moveDir = -1;
                }
                x+= moveDir * moveSpeed;
            }
            else if(state== State.dead)
            {
                y+=0.04;
                if(y>9)
                {
                    MainScene scene = (MainScene) BaseScene.getTopScene();
                    scene.remove(MainScene.Layer.enemy,this);
                }
            }
        }
        if(id==5)
        {
            if(state==State.spawn)
            {
                y+=0.01;
                if(y>3.5)
                {
                    state=State.common;
                    if(r.nextInt(10)<5)
                    {
                        moveDir = -1;
                    }
                    else
                    {
                        moveDir = 1;
                    }
                }
            }
            else if(state== State.common)
            {
                x+= moveDir * moveSpeed;
                y+= moveDirY * moveSpeed;
                if(y>6 || y<3.5f)
                {
                    moveDirY *=-1;
                }
            }
            else if(state== State.dead)
            {
                y+=0.04;
                if(y>9)
                {
                    MainScene scene = (MainScene) BaseScene.getTopScene();
                    scene.remove(MainScene.Layer.enemy,this);
                }
            }
        }

        if(x < 2.5)
        {
            x+=moveSpeed;
            moveDir = 1;
        }
        else if(x>24.5)
        {
            x-=moveSpeed;
            moveDir = -1;
        }
    }

    private void DrawWizard(Canvas canvas, float time)
    {
        Rect[] rects = null;
        if(state==State.spawn)
        {
            rects = srcRects[1];
        }
        else if(state==State.common)
        {
            if(id==6||id==8)
            {
                if(MainScene.player.GetX()>x)
                {
                    rects = srcRects[0];
                }
                else
                {
                    rects = srcRects[3];
                }
            }
            else
            {
                if(moveDir==1)
                {
                    rects = srcRects[0];
                }
                else
                {
                    rects = srcRects[3];
                }
            }
        }
        else if(state==State.dead)
        {
            rects = srcRects[2];
        }
        int frameIndex = 0;
        if(rects!=null)
        {
            frameIndex = Math.round(time * fps) % rects.length;
        }
        canvas.drawBitmap(bitmap, rects[frameIndex], dstRect, null);
    }

    private void UpdateWizard()
    {
        if(id==6)
        {
            if(state==State.spawn)
            {
                y-=0.01;
                if(y<6)
                {
                    state=State.common;
                }
            }
            else if(state==State.common)
            {
                //공격
            }
            else if(state== State.dead)
            {
                y+=0.04;
                if(y>9)
                {
                    MainScene scene = (MainScene) BaseScene.getTopScene();
                    scene.remove(MainScene.Layer.enemy,this);
                }
            }
        }
        if(id==7)
        {
            if(state==State.spawn)
            {
                y-=0.01;
                if(y<6)
                {
                    state=State.common;
                    if(r.nextInt(10)<5)
                    {
                        moveDir = -1;
                    }
                    else
                    {
                        moveDir = 1;
                    }
                }
            }
            else if(state==State.common)
            {
                x+=moveDir * moveSpeed;

                if(x < 2.5)
                {
                    x+=moveSpeed;
                    moveDir = 1;
                }
                else if(x>24.5)
                {
                    x-=moveSpeed;
                    moveDir = -1;
                }

                //공격

            }
            else if(state== State.dead)
            {
                y+=0.04;
                if(y>9)
                {
                    MainScene scene = (MainScene) BaseScene.getTopScene();
                    scene.remove(MainScene.Layer.enemy,this);
                }
            }
        }
        if(id==8)
        {
            if(state==State.spawn)
            {
                y-=0.01;
                if(y<6)
                {
                    state=State.common;
                    if(MainScene.player.GetX() > x)
                    {
                        moveDir = -1;
                    }
                    else
                    {
                        moveDir = 1;
                    }
                }
            }
            else if(state==State.common)
            {
                if(x > 2.5 && x < 24.5)
                {
                    x+=moveDir * moveSpeed;
                }
                
                //공격

            }
            else if(state== State.dead)
            {
                y+=0.04;
                if(y>9)
                {
                    MainScene scene = (MainScene) BaseScene.getTopScene();
                    scene.remove(MainScene.Layer.enemy,this);
                }
            }
        }
    }
}
