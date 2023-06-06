package com.spg2018184028.sprinter.framework;

import android.graphics.Canvas;

import java.util.ArrayList;
import java.util.Random;

import com.spg2018184028.sprinter.Boss;
import com.spg2018184028.sprinter.Enemy;
import com.spg2018184028.sprinter.EnemyBullet;
import com.spg2018184028.sprinter.MainScene;
import com.spg2018184028.sprinter.Player;
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
        ArrayList<IGameObject> enemyBullets = scene.getObjectsAt(MainScene.Layer.ebullet);
        ArrayList<IGameObject> boss = scene.getObjectsAt(MainScene.Layer.boss);
        for (IGameObject o1 : enemies) {
            if (!(o1 instanceof Enemy)) {
                continue;
            }
            Enemy enemy = (Enemy) o1;
            if (CollisionHelper.collides(enemy, MainScene.player))
            {
                if(enemy.state== Enemy.State.common && !MainScene.player.isDamaged)
                {
                    MainScene.player.curHp--;
                    MainScene.player.isDamaged = true;
                }
            }
            if(CollisionHelper.collides(enemy, weapon))
            {
                if(enemy.state== Enemy.State.common)
                {
                    enemy.state = Enemy.State.dead;
                    Random r = new Random();
                    int n = r.nextInt(100);
                    if(n<15)
                    {
                        scene.add(MainScene.Layer.item,new Item(enemy.x,enemy.y,0));
                    }
                    else
                    {
                        scene.add(MainScene.Layer.item,new Item(enemy.x,enemy.y,1));
                    }
                    MainScene.score+=5;
                }
            }
        }
        for (int i = items.size() - 1; i >= 0; i--) {
            IGameObject gobj = items.get(i);
            if (!(gobj instanceof IBoxCollidable)) {
                continue;
            }
            if (CollisionHelper.collides(MainScene.player, (IBoxCollidable) gobj)) {
                Item item = (Item) gobj;
                if(item.state== Item.State.interactive)
                {
                    if(item.itemId==1)
                    {
                        MainScene.player.curExp += MainScene.player.expPlus;
                    }
                    if(item.itemId==0)
                    {
                        if(MainScene.player.curHp<MainScene.player.maxHp)
                        {
                            MainScene.player.curHp++;
                        }
                    }
                    if(item.itemId==2)
                    {
                        scene.add(MainScene.Layer.item,new Item(item.x, item.y,3));
                        item.state= Item.State.obtained;
                    }
                    if(item.itemId==3)
                    {
                        MainScene.isGamePause = true;
                        MainScene.isLevelUpPause = true;
                        MainScene.player.curHp = MainScene.player.maxHp;
                        Random r = new Random();
                        int[] rs = new int[3];
                        for(int j=0; j<3; j++)
                        {
                            if(MainScene.player.curHp!=MainScene.player.maxHp)
                            {
                                rs[i] = r.nextInt(6);
                            }
                            else
                            {
                                rs[i] = r.nextInt(5)+1;
                            }
                        }
                        MainScene.levelUpSelect = rs;
                        MainScene.isBossStage = false;

                    }
                    if(item.itemId==4)
                    {
                        Random r = new Random();
                        int n = r.nextInt(100);
                        if(n<30) {
                            MainScene.player.curHp--;
                        }
                    }
                    scene.remove(MainScene.Layer.item, gobj);
                }
            }
        }
        for (int i = enemyBullets.size() - 1; i >= 0; i--) {
            IGameObject gobj = enemyBullets.get(i);
            if (!(gobj instanceof IBoxCollidable)) {
                continue;
            }
            if (CollisionHelper.collides(MainScene.player, (IBoxCollidable) gobj)) {
                EnemyBullet eBullet = (EnemyBullet) gobj;
                if(!MainScene.player.isDamaged)
                {
                    MainScene.player.curHp--;
                    MainScene.player.isDamaged = true;
                    scene.remove(MainScene.Layer.ebullet, gobj);
                }
            }
        }
        for (IGameObject o1 : boss) {
            if (!(o1 instanceof Boss)) {
                continue;
            }
            Boss bossObj = (Boss) o1;
            if (CollisionHelper.collides(bossObj, MainScene.player))
            {
                if(bossObj.state== Boss.State.common && !MainScene.player.isDamaged && !bossObj.isDamaged)
                {
                    MainScene.player.curHp--;
                    MainScene.player.isDamaged = true;
                }
            }
            if(CollisionHelper.collides(bossObj, weapon))
            {
                if(bossObj.state== Boss.State.common && !bossObj.isDamaged)
                {
                    bossObj.hp--;
                    bossObj.isDamaged = true;
                }
            }
        }
    }

    @Override
    public void draw(Canvas canvas) {}
}