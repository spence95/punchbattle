package com.recneps.punchbattle;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.input.GestureDetector;
import com.badlogic.gdx.math.Vector2;

public class PunchBattle extends ApplicationAdapter implements InputProcessor{
	SpriteBatch batch;
	SpritesHelper spritesHelper;
	GameRenderer gameRenderer;
	Game game;

	@Override
	public void create () {
		spritesHelper = new SpritesHelper();
		gameRenderer = new GameRenderer();
		game = new Game(spritesHelper);
		batch = new SpriteBatch();

		GestureDetector gd = new GestureDetector(0, 0, 0, 5, new GestureDetector.GestureListener() {
			@Override
			public boolean touchDown(float x, float y, int pointer, int button) {
				return false;
			}

			@Override
			public boolean tap(float x, float y, int count, int button) {
				System.out.println("tapped");
				game.swipedUp();
				return true;
			}

			@Override
			public boolean longPress(float x, float y) {

				return false;
			}

			@Override
			public boolean fling(float velocityX, float velocityY, int button) {
				System.out.println("fling");
				if (Math.abs(velocityX) > Math.abs(velocityY)) {
					if (velocityX > 0) {
						//swipe right
					} else {
						//swipe left
					}
				} else {
					if (velocityY > 0) {
						//swipe down
					} else {
						game.swipedUp();
					}
				}
				return false;
			}

			@Override
			public boolean pan(float x, float y, float deltaX, float deltaY) {
				System.out.println("panning: " + x + " " + y + " " + deltaX + " " + deltaY);
				System.out.println("start moving?");
				game.mainCharacter.startMoving(x, y);
				return true;
			}

			@Override
			public boolean panStop(float x, float y, int pointer, int button) {
				System.out.println("stop moving?");
				game.mainCharacter.stopMoving();
				return true;
			}

			@Override
			public boolean zoom(float initialDistance, float distance) {
				return false;
			}

			@Override
			public boolean pinch(Vector2 initialPointer1, Vector2 initialPointer2, Vector2 pointer1, Vector2 pointer2) {
				return false;
			}

		});
		Gdx.input.setInputProcessor(gd);

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
		// Hey, I actually did some clean up in a code sample!
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
