package com.spg2018184028.sprinter.framework;

import com.spg2018184028.sprinter.framework.Metrics;
import com.spg2018184028.sprinter.framework.Sprite;

public class Background extends Sprite {
    public Background(int bitmapResId) {
        super(bitmapResId, Metrics.game_width / 2, Metrics.game_height / 2, Metrics.game_width, Metrics.game_height);
        float height = bitmap.getHeight() * Metrics.game_width / bitmap.getWidth();
        setSize(Metrics.game_width, height);
    }
}