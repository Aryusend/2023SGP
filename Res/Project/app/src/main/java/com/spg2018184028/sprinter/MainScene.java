package com.spg2018184028.sprinter;

import com.spg2018184028.sprinter.framework.BaseScene;
import com.spg2018184028.sprinter.framework.Metrics;

public class MainScene extends BaseScene {
    public static final Player player = new Player();
    public enum Layer {
        bg, ground, player, COUNT
    }

    public MainScene() {
        Metrics.setGameSize(27.0f, 9.0f);
        initLayers(Layer.COUNT);

        for(int i=0; i<12; i++)
        {
            add(Layer.ground, new Ground(i*2+2.5f,8));
        }
        add(Layer.player, player);
    }


}