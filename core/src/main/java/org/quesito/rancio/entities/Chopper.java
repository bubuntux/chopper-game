package org.quesito.rancio.entities;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

/**
 * @author Julio Gutierrez (12/23/13)
 */
public class Chopper extends DynamicGameObject {

    private final static int CHOPPER_STATE_FALL = 0;
    private final static int CHOPPER_STATE_RISE = 1;
    private final static int CHOPPER_STATE_HIT = 2;

    private final Texture _texture;

    private int _state;

    public Chopper(float x, float y) {
        super(x, y);
        _texture = new Texture(Gdx.files.internal("chopper.png"));
        _state = CHOPPER_STATE_FALL;
    }

    @Override
    public void update(float delta) {

    }

    @Override
    public void draw(SpriteBatch spriteBatch) {
        spriteBatch.draw(_texture, _position.x, _position.y, 100, 50);
    }

    @Override
    public void dispose() {
        _texture.dispose();
    }
}
