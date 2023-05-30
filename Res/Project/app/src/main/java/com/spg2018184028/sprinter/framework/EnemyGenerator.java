package com.spg2018184028.sprinter.framework;

import android.graphics.Canvas;

import java.util.ArrayList;
import java.util.Random;

import com.spg2018184028.sprinter.Boss;
import com.spg2018184028.sprinter.Enemy;
import com.spg2018184028.sprinter.EnemyBullet;
import com.spg2018184028.sprinter.MainScene;
import com.spg2018184028.sprinter.framework.BaseScene;
import com.spg2018184028.sprinter.framework.IGameObject;

public class EnemyGenerator implements IGameObject {
    private static final float GEN_INTERVAL = 5.0f;
    private float time;

    private Boolean isBossSpawn = false;
    @Override
    public void update() {
        if(!MainScene.isBossStage)
        {
            time += BaseScene.frameTime;
            if (time > GEN_INTERVAL) {
                generate();
                time -= GEN_INTERVAL;
            }
        }
        else
        {
            if(!isBossSpawn)
            {
                bossStage();
            }
        }
    }

    private int[] rn= new int[5];
    private void generate() {
        Random r = new Random();
        BaseScene scene = BaseScene.getTopScene();
        for(int i=0; i<5; i++)
        {
            rn[i] = r.nextInt(12);
            for(int j=0; j<i; j++)
            {
                if(rn[j]==rn[i])
                {
                    rn[i] = r.nextInt(12);
                }
            }
        }
        int n = 1;
        for (int i = 0; i < 5; i++) {
            if(MainScene.player.stageLevel>0)
            {
                n = r.nextInt(MainScene.player.stageLevel)+1;
            }
            if(n<4)
            {
                if(n==2)scene.add(MainScene.Layer.enemy ,new Enemy(rn[i]*2 + 2.5f, 9,n-1,0.06f));
                else if(n==3) scene.add(MainScene.Layer.enemy ,new Enemy(rn[i]*2 + 2.5f, 9,n-1,0.04f));
                else scene.add(MainScene.Layer.enemy ,new Enemy(rn[i]*2 + 2.5f, 9,n-1,0.02f));
            }
            else if(n>=4 && n<7)
            {
                if(n!=5)scene.add(MainScene.Layer.enemy ,new Enemy(rn[i]*2 + 2.5f, 0,n-1,0.02f));
                else scene.add(MainScene.Layer.enemy ,new Enemy(rn[i]*2 + 2.5f, 0,n-1,0.01f));
            }
            else
            {
                scene.add(MainScene.Layer.enemy ,new Enemy(rn[i]*2 + 2.5f, 9,n-1,0.02f));
            }
        }
    }
    private void bossStage()
    {
        BaseScene scene = BaseScene.getTopScene();
        ArrayList<IGameObject> enemies = scene.getObjectsAt(MainScene.Layer.enemy);
        ArrayList<IGameObject> enemyBullets = scene.getObjectsAt(MainScene.Layer.ebullet);
        for (IGameObject o1 : enemies) {
            if (!(o1 instanceof Enemy)) {
                continue;
            }
            Enemy enemy = (Enemy) o1;
            enemy.state = Enemy.State.dead;
        }
        for (int i = enemyBullets.size() - 1; i >= 0; i--) {
            IGameObject gobj = enemyBullets.get(i);
            scene.remove(MainScene.Layer.ebullet, gobj);
        }
        if(!isBossSpawn)
        {
            scene.add(MainScene.Layer.boss ,new Boss(13.5f, 9,0,0.02f,3, -1));
            MainScene.bossNum++;
            isBossSpawn = true;
        }

    }

    @Override
    public void draw(Canvas canvas) {}
}
