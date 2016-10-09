package com.recneps.punchbattle;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputAdapter;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.input.GestureDetector;
import com.badlogic.gdx.math.Vector2;

public class PunchBattle extends ApplicationAdapter implements InputProcessor{
	SpriteBatch batch;
	SpritesHelper spritesHelper;
	GameRenderer gameRenderer;
	Game game;
	InputHelper inputHelper;

	@Override
	public void create () {
		spritesHelper = new SpritesHelper();
		gameRenderer = new GameRenderer();
		game = new Game(spritesHelper);
		batch = new SpriteBatch();
		inputHelper = new InputHelper(game);
	}

	@Override
	public void render () {
		//should only call two methods, one for logic, the other for rendering
		//see application lifecycle here: https://github.com/libgdx/libgdx/wiki/The-life-cycle
		game.update(Gdx.graphics.getDeltaTime());
		gameRenderer.render(batch, Gdx.graphics.getDeltaTime(), game);
	}

	@Override
	public void dispose() {
		//img.dispose();
		//world.dispose();
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
		System.out.println("start moving?");
		game.mainCharacter.startMoving(screenX, screenY);
		return false;
	}

	@Override
	public boolean touchUp(int screenX, int screenY, int pointer, int button) {
		System.out.println("stop moving?");
		game.mainCharacter.stopMoving();
		return false;
	}

	@Override
	public boolean touchDragged(int screenX, int screenY, int pointer) {
		System.out.println("Touch dragged: " + screenX + " " + screenY + " " + pointer);
		return false;
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
