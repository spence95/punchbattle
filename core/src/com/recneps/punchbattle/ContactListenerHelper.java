package com.recneps.punchbattle;

import com.badlogic.gdx.physics.box2d.Contact;
import com.badlogic.gdx.physics.box2d.ContactImpulse;
import com.badlogic.gdx.physics.box2d.ContactListener;
import com.badlogic.gdx.physics.box2d.Manifold;

/**
 * Created by spencersutton on 10/8/16.
 */
public class ContactListenerHelper implements ContactListener {

    Game game;

    public  ContactListenerHelper(Game game){
        this.game = game;
    }

    @Override
    public void beginContact(Contact contact) {
        if(contact.getFixtureA().getBody().getUserData() == "ground" &&
                contact.getFixtureB().getBody().getUserData() == "mainCharacter")
        {
            game.mainCharacter.touchingPlatform = true;
        }
    }

    @Override
    public void endContact(Contact contact) {
        if(contact.getFixtureA().getBody().getUserData() == "ground" &&
                contact.getFixtureB().getBody().getUserData() == "mainCharacter")
        {
            game.mainCharacter.touchingPlatform = false;
        }
    }

    @Override
    public void preSolve(Contact contact, Manifold oldManifold) {

    }

    @Override
    public void postSolve(Contact contact, ContactImpulse impulse) {

    }
}
