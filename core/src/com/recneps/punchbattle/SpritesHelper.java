package com.recneps.punchbattle;

import com.badlogic.gdx.graphics.g2d.Sprite;

/**
 * Created by spence95 on 3/4/2016.
 */
public class SpritesHelper {

    public Sprite getNewPlayerSprite(){
        return new Sprite(Assets.img);
    }

    public Sprite getNewGroundSprite(){
        return new Sprite(Assets.ground);
    }
}
