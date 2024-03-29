package com.spg2018184028.sprinter;

import com.spg2018184028.sprinter.framework.Background;
import com.spg2018184028.sprinter.framework.BaseScene;
import com.spg2018184028.sprinter.framework.CollisionChecker;
import com.spg2018184028.sprinter.framework.EnemyGenerator;
import com.spg2018184028.sprinter.framework.Item;
import com.spg2018184028.sprinter.framework.Metrics;

public class MainScene extends BaseScene {
    public static boolean isGamePause = false;
    public static boolean isLevelUpPause = false;
    public static int levelUpIndex = 0;
    public static int[] levelUpSelect = {0,1,2};
    public static int score = 0;

    public static int highScore = 0;
    public static final Player player = new Player();
    private Weapon weapon;

    public static Boolean isBossStage = false;

    public static Boolean isGameOver = false;

    public static int bossNum = 0;
    public enum Layer {
        bg, ground, item, player, weapon, enemy, ebullet, boss, controller, COUNT
    }
    public MainScene() {
        Metrics.setGameSize(27.0f, 9.0f);
        initLayers(Layer.COUNT);

        for(int i=0; i<12; i++)
        {
            add(Layer.ground, new Ground(i*2+2.5f,8));
        }
        add(Layer.bg, new Background(R.mipmap.sky));
        add(Layer.bg, new Background(R.mipmap.bg1,13.5f,2.5f,24.0f,9.0f));
        add(Layer.bg, new Background(R.mipmap.wall,0.5f,7.0f,2.0f,8.0f));
        add(Layer.bg, new Background(R.mipmap.wall,26.5f,7.0f,2.0f,8.0f));

        add(Layer.controller, new EnemyGenerator());

        add(Layer.player, player);

        weapon = new Weapon(player.GetX(),player.GetY());
        add(Layer.weapon, weapon);
        add(Layer.controller, new CollisionChecker(weapon));

        add(Layer.controller, new Pause(13.5f,4.5f));
    }

    @Override
    protected void onStart() {
        //Sound.playMusic();
    }

    @Override
    protected void onEnd() {
        Sound.stopMusic();
    }

}