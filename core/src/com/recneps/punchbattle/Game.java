package com.recneps.punchbattle;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.math.Matrix4;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;
import com.badlogic.gdx.physics.box2d.World;

import java.util.ArrayList;

/**
 * Created by spence95 on 3/4/2016.
 */
public class Game {
    static World world;
    Platform ground;
    Box2DDebugRenderer debugRenderer;
    Matrix4 debugMatrix;
    OrthographicCamera camera;

    Character mainCharacter;



    static final float PIXELS_TO_METERS = 100f;

    private static final int velocityIterations = 6;
    private static final int positionIterations = 2;
    private static final float gravity = -9.8f;
    private static final float gravityAdjuster = 4;
    public static final short PHYSICS_ENTITY = 0x1;    // 0001
    public static final short WORLD_ENTITY = 0x1 << 1; // 0010 or 0x2 in hex


    public Game(SpritesHelper spritesHelper) {
        boolean doSleep = true;
        world = new World(new Vector2(0, gravity), doSleep);



//        createPlayer(spritesHelper, -256, -256);
//        createPlayer(spritesHelper, 256, 2048);
        createPlayer(spritesHelper, 0, 0);
//        createPlayer(spritesHelper, -256, 25000);
        createGround(spritesHelper);

        debugRenderer = new Box2DDebugRenderer();
        camera = new OrthographicCamera(Gdx.graphics.getWidth(),
                Gdx.graphics.getHeight());
    }

    public void update(float delta) {
        //center camera on player
        camera.position.set(mainCharacter.sprite.getX() + mainCharacter.width, mainCharacter.sprite.getY() + mainCharacter.height/2 + Gdx.graphics.getHeight()/4, 0);
        camera.update();
        world.step(delta, velocityIterations, positionIterations);
        updatePlayers();
    }

    public void updatePlayers(){
        mainCharacter.update();
    }

    public void createPlayer(SpritesHelper spritesHelper, float startingX, float startingY){
        mainCharacter = new Character(spritesHelper.getNewPlayerSprite(), startingX, startingY);
    }

    public void createGround(SpritesHelper spritesHelper){
        ground = new Platform(spritesHelper.getNewGroundSprite(),Gdx.graphics.getWidth()*10/PIXELS_TO_METERS,
                Gdx.graphics.getHeight()/PIXELS_TO_METERS- 50/PIXELS_TO_METERS, 0, 0);
    }

    public void swipedUp(){
        System.out.println("game jumping");
        mainCharacter.jump();
    }

    public void touched(float screenX, float screenY){

    }
}
