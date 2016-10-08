package com.recneps.punchbattle;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.EdgeShape;
import com.badlogic.gdx.physics.box2d.FixtureDef;

/**
 * Created by spence95 on 3/5/2016.
 */
public class Platform {
    Sprite sprite;
    float width;
    float height;
    float x;
    float y;
    Body body;

    public Platform(Sprite sprite, float width, float height, float x, float y) {
        this.sprite = sprite;

        this.width = width;
        this.height = height;
        this.x = x;
        this.y = y;

        sprite.setPosition(x, y);
        sprite.setSize(width, height);

        instantiate();
    }

    private void instantiate(){
        BodyDef bodyDef2 = new BodyDef();
        bodyDef2.type = BodyDef.BodyType.StaticBody;
        bodyDef2.position.set(0,0);
        FixtureDef fixtureDef2 = new FixtureDef();

        EdgeShape edgeShape = new EdgeShape();
        edgeShape.set(-width/2,-height/2,width/2,-height/2);
        fixtureDef2.shape = edgeShape;
        fixtureDef2.filter.categoryBits = Game.WORLD_ENTITY;
        fixtureDef2.filter.maskBits = Game.PHYSICS_ENTITY;

        body = Game.world.createBody(bodyDef2);
        body.createFixture(fixtureDef2);

        edgeShape.dispose();
    }
}
