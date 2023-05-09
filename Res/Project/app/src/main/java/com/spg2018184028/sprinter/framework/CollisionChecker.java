package com.spg2018184028.sprinter.framework;

import android.graphics.Canvas;

import java.util.ArrayList;

import com.spg2018184028.sprinter.MainScene;
import com.spg2018184028.sprinter.framework.IBoxCollidable;
import com.spg2018184028.sprinter.framework.IGameObject;
import com.spg2018184028.sprinter.framework.BaseScene;
import com.spg2018184028.sprinter.framework.CollisionHelper;

public class CollisionChecker implements IGameObject {

    public CollisionChecker() {
    }

    @Override
    public void update() {
        MainScene scene = (MainScene) BaseScene.getTopScene();
        ArrayList<IGameObject> items = scene.getObjectsAt(MainScene.Layer.item);
        for (int i = items.size() - 1; i >= 0; i--) {
            IGameObject gobj = items.get(i);
            if (!(gobj instanceof IBoxCollidable)) {
                continue;
            }
            if (CollisionHelper.collides(MainScene.player, (IBoxCollidable) gobj)) {
                scene.remove(MainScene.Layer.item, gobj);
            }
        }
    }

    @Override
    public void draw(Canvas canvas) {}
}