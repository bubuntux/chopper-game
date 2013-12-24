package org.quesito.rancio.entities;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import org.quesito.rancio.World;

/**
 * @author Julio Gutierrez (12/23/13)
 */
public class Chopper extends DynamicGameObject {

    private static final float ACCEL = 300f;

    private final Texture _texture;

    public Chopper(float x, float y) {
        super(x, y);
        _texture = new Texture(Gdx.files.internal("chopper.png"));
    }

    @Override
    public void update(float delta) {
        if (Gdx.input.isKeyPressed(Input.Keys.ANY_KEY) || Gdx.input.isTouched()) {
            _velocity.add(0, ACCEL * delta);
        } else {
            _velocity.add(0, World.GRAVITY * delta);
        }
        _position.add(_velocity.scl(delta));
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