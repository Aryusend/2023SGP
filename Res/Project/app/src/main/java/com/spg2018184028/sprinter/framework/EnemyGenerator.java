package com.spg2018184028.sprinter.framework;

import android.graphics.Canvas;

import java.util.Random;

import com.spg2018184028.sprinter.Enemy;
import com.spg2018184028.sprinter.MainScene;
import com.spg2018184028.sprinter.framework.BaseScene;
import com.spg2018184028.sprinter.framework.IGameObject;

public class EnemyGenerator implements IGameObject {
    private static final float GEN_INTERVAL = 5.0f;
    private float time;
    @Override
    public void update() {
        time += BaseScene.frameTime;
        if (time > GEN_INTERVAL) {
            generate();
            time -= GEN_INTERVAL;
        }
    }

    private void generate() {
        Random r = new Random();
        BaseScene scene = BaseScene.getTopScene();
        for (int i = 0; i < 5; i++) {
            scene.add(MainScene.Layer.enemy ,new Enemy(r.nextInt(24)+2, 9,0,0.02f));
        }
    }

    @Override
    public void draw(Canvas canvas) {}
}
