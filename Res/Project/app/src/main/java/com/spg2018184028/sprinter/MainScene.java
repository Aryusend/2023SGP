package com.spg2018184028.sprinter;

import com.spg2018184028.sprinter.framework.BaseScene;
import com.spg2018184028.sprinter.framework.Metrics;

public class MainScene extends BaseScene {
    public static final Player player = new Player();
    public enum Layer {
        bg, player, COUNT
    }

    public MainScene() {
        Metrics.setGameSize(27.0f, 9.0f);
        initLayers(Layer.COUNT);

        add(Layer.player, player);
    }


}