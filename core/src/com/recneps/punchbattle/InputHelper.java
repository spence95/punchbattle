package com.recneps.punchbattle;

import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputAdapter;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.assets.loaders.resolvers.InternalFileHandleResolver;

import java.util.ArrayList;

/**
 * Created by spencersutton on 10/8/16.
 */
public class InputHelper implements InputProcessor {

    class Touch {
        float x;
        float y;
    }

    Game game;
    ArrayList<Touch> touchDragged;

    public InputHelper(Game game){
        this.game = game;
        touchDragged = new ArrayList<Touch>();
        Gdx.input.setInputProcessor(this);
    }

    @Override
    public boolean keyDown(int keycode) {
        return false;
    }

    @Override
    public boolean keyUp(int keycode) {
        return false;
    }

    @Override
    public boolean keyTyped(char character) {
        return false;
    }

    @Override
    public boolean touchDown(int screenX, int screenY, int pointer, int button) {
        game.mainCharacter.startMoving(screenX, screenY);
        return true;
    }

    @Override
    public boolean touchUp(int screenX, int screenY, int pointer, int button) {
        game.mainCharacter.stopMoving();
        return true;
    }

    @Override
    public boolean touchDragged(int screenX, int screenY, int pointer) {
        System.out.println("drag touch");
        System.out.println(Integer.toString(pointer) + " " + Integer.toString(screenX) + " " + Integer.toString(screenY));
        Touch touch = new Touch();
        touch.x = screenX;
        touch.y = screenY;
        if(touchDragged.size() - 1 > -1) {
            Touch lastTouch = touchDragged.get(touchDragged.size() - 1);
            if(touch.y > lastTouch.y){
                game.swipedUp();
            }
        }
        touchDragged.add(touch);
        return true;
    }

    @Override
    public boolean mouseMoved(int screenX, int screenY) {
        return false;
    }

    @Override
    public boolean scrolled(int amount) {
        return false;
    }


}
