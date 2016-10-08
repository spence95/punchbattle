package com.recneps.punchbattle;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

/**
 * Created by spence95 on 3/4/2016.
 */
public class GameRenderer {
    public GameRenderer(){
    }

    public void render(SpriteBatch batch, float delta, Game game){
        Gdx.gl.glClearColor(1, 1, 1, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        batch.setProjectionMatrix(game.camera.combined);
        game.debugMatrix = batch.getProjectionMatrix().cpy().scale(Game.PIXELS_TO_METERS,
                Game.PIXELS_TO_METERS, 0);
        batch.begin();
        renderPlayers(batch, game);
        renderGround(batch, game);
        batch.end();

        game.debugRenderer.render(game.world, game.debugMatrix);
    }

    private void renderPlayers(SpriteBatch batch, Game game){
            batch.draw(game.mainCharacter.sprite, game.mainCharacter.sprite.getX(), game.mainCharacter.sprite.getY(),
                    game.mainCharacter.sprite.getOriginX(), game.mainCharacter.sprite.getOriginY(),
                    game.mainCharacter.width * 2, game.mainCharacter.height * 2,
                    game.mainCharacter.sprite.getScaleX(), game.mainCharacter.sprite.getScaleY(), game.mainCharacter.sprite.getRotation());
    }

    private void renderGround(SpriteBatch batch, Game game){
       // game.ground.sprite.draw(batch);
//        batch.draw(game.ground.sprite, game.ground.sprite.getX() - (game.ground.width * Game.PIXELS_TO_METERS)/2, game.ground.sprite.getY() - (game.ground.height * Game.PIXELS_TO_METERS)/2,
//                game.ground.width * Game.PIXELS_TO_METERS, game.ground.height * Game.PIXELS_TO_METERS);
        batch.draw(game.ground.sprite, game.ground.x + game.ground.width/2, game.ground.y, game.ground.width * Game.PIXELS_TO_METERS, game.ground.height);
    }
}
