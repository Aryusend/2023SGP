package com.spg2018184028.sprinter;

import com.spg2018184028.sprinter.framework.Button;
import com.spg2018184028.sprinter.framework.BaseScene;

public class PauseScene extends BaseScene {
    public enum Layer {
        bg, title, touch, COUNT
    }
    public PauseScene() {
        initLayers(Layer.COUNT);
        //add(Layer.bg, new Sprite(R.mipmap.bg_city_landscape, 8.0f, 4.5f, 16, 9));
        //add(Layer.bg, new Sprite(R.mipmap.cookie_run_title, 8.0f, 4.5f, 3.69f, 1.36f));
        add(Layer.touch, new Button(R.mipmap.button_pause, 14.5f, 1.0f, 2.0f, 0.75f, new Button.Callback() {
            @Override
            public boolean onTouch() {
                return onTouch(null);
            }

            @Override
            public boolean onTouch(Button.Action action) {
                if (action == Button.Action.pressed) {
                    popScene();
                }
                return false;
            }
        }));
    }

    @Override
    protected int getTouchLayerIndex() {
        return Layer.touch.ordinal();
    }
}