package com.spg2018184028.sprinter.framework;

import android.graphics.Canvas;

import java.util.ArrayList;

import com.spg2018184028.sprinter.MainScene;
import com.spg2018184028.sprinter.Weapon;
import com.spg2018184028.sprinter.framework.IBoxCollidable;
import com.spg2018184028.sprinter.framework.IGameObject;
import com.spg2018184028.sprinter.framework.BaseScene;
import com.spg2018184028.sprinter.framework.CollisionHelper;

public class CollisionChecker implements IGameObject {

    private Weapon weapon;
    public CollisionChecker(Weapon _weapon) {
        weapon = _weapon;
    }

    @Override
    public void update() {
        MainScene scene = (MainScene) BaseScene.getTopScene();
        ArrayList<IGameObject> items = scene.getObjectsAt(MainScene.Layer.item);
        ArrayList<IGameObject> enemys = scene.getObjectsAt(MainScene.Layer.enemy);
        for (int i = items.size() - 1; i >= 0; i--) {
            IGameObject gobj = items.get(i);
            if (!(gobj instanceof IBoxCollidable)) {
                continue;
            }
            if (CollisionHelper.collides(MainScene.player, (IBoxCollidable) gobj)) {
                scene.remove(MainScene.Layer.item, gobj);
            }
        }
        for (int i = enemys.size() - 1; i >= 0; i--) {
            IGameObject gobj = enemys.get(i);
            if (!(gobj instanceof IBoxCollidable)) {
                continue;
            }
            if (CollisionHelper.collides(weapon, (IBoxCollidable) gobj)) {
                scene.remove(MainScene.Layer.enemy, gobj);
            }
        }
    }

    @Override
    public void draw(Canvas canvas) {}
}