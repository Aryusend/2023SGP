package com.spg2018184028.sprinter.framework;

import android.graphics.Canvas;

import java.util.ArrayList;

import com.spg2018184028.sprinter.Enemy;
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
        ArrayList<IGameObject> enemies = scene.getObjectsAt(MainScene.Layer.enemy);
        for (IGameObject o1 : enemies) {
            if (!(o1 instanceof Enemy)) {
                continue;
            }
            Enemy enemy = (Enemy) o1;
            if (CollisionHelper.collides(enemy, MainScene.player))
            {

            }
            if(CollisionHelper.collides(enemy, weapon))
            {
                enemy.state = Enemy.State.dead;
            }
        }
        /*
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
                scene.add(MainScene.Layer.item, new Item(weapon.x, weapon.y, 0));
            }
        }
        */
    }

    @Override
    public void draw(Canvas canvas) {}
}