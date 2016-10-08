package com.recneps.punchbattle;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;

/**
 * Created by spence95 on 3/4/2016.
 *
 * This is the logical implementation of a Sprite in the game

 */
public class Character {
    float x;
    float y;
    float width;
    float height;
    Body body;
    Sprite sprite;

    boolean isMoving;
    float moveToX;
    float moveToY;

    public Character(Sprite sprite, float x, float y) {
        this.sprite = sprite;
        width = sprite.getWidth() / 2;
        height = sprite.getHeight() / 2;
        setPosition(x, y);
        instantiate();
    }

    public void update(){
        if(isMoving){
            move(moveToX, moveToY);
        }
        setPosition((body.getPosition().x * Game.PIXELS_TO_METERS) - width, (body.getPosition().y * Game.PIXELS_TO_METERS) - height);
    }

    public void setPosition(float x, float y){
        this.x = x;
        this.y = y;
        sprite.setPosition(x, y);
    }

    private void setBody(Body body){
        this.body = body;
    }

    private void instantiate(){
        BodyDef bodydef = new BodyDef();
        bodydef.type = BodyDef.BodyType.DynamicBody;

        //1 to 1 dimensions, for every pixel
        bodydef.position.set((x + width) / Game.PIXELS_TO_METERS, (y + height) / Game.PIXELS_TO_METERS);

        Body body = Game.world.createBody(bodydef);
        body.setFixedRotation(true);
        PolygonShape shape = new PolygonShape();
        shape.setAsBox((width)/Game.PIXELS_TO_METERS, (height)/Game.PIXELS_TO_METERS);

        FixtureDef fixtureDef = new FixtureDef();
        fixtureDef.shape = shape;
        fixtureDef.density = 1f;
        fixtureDef.friction = 5f;
        fixtureDef.restitution = 0.4f;
        fixtureDef.filter.categoryBits = Game.PHYSICS_ENTITY;
        fixtureDef.filter.maskBits = Game.WORLD_ENTITY;
        body.createFixture(fixtureDef);
        setBody(body);
        // Shape is the only disposable of the lot, so get rid of it
        shape.dispose();
    }

    public void jump(){
        float impulse = body.getMass() * 5;
        body.applyLinearImpulse(new Vector2(0, impulse), body.getWorldCenter(), true);
    }

    public void move(float screenX, float screenY){
        //convert to game dimensions
        if(body.getLinearVelocity().x < 6f && body.getLinearVelocity().x > -6f) {
            float force = body.getMass() * 5;
            if (screenX < Gdx.graphics.getWidth()/2) {
                force = -force;
            }
            body.applyLinearImpulse(new Vector2(force, 0), body.getWorldCenter(), true);
        }
    }

    public void startMoving(float x, float y){
        isMoving = true;
        moveToX = x;
        moveToY = y;
    }

    public void stopMoving(){
        isMoving = false;
    }

    public void die() {
        body.destroyFixture(body.getFixtureList().get(0));
    }
}
