package com.spg2018184028.sprinter;

import com.spg2018184028.sprinter.framework.Sprite;

public class Ground  extends Sprite {
    static int resIds = R.mipmap.ground;

    public Ground(float x, float y) {
        super(resIds, x, y, 2.0f, 2.0f);
    }
}
