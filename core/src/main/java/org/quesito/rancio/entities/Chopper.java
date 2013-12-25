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

    private final float _accelerationUp = 15f;
    private final float _maxSpeedUp = 5f;

    private final Texture _texture;

    public Chopper(float x, float y) {
        super(x, y);
        _texture = new Texture(Gdx.files.internal("chopper.png"));
    }

    @Override
    public void update(float delta) {
        if (Gdx.input.isKeyPressed(Input.Keys.ANY_KEY) || Gdx.input.isTouched()) {
            _velocity.add(0, _accelerationUp * delta);
        } else {
            _velocity.add(0, World.GRAVITY * delta);
        }

        if (_velocity.y > _maxSpeedUp) {
            _velocity.y = _maxSpeedUp;
        }

        _position.add(_velocity.cpy().scl(delta));
    }

    @Override
    public void draw(SpriteBatch spriteBatch) {
        spriteBatch.draw(_texture, _position.x, _position.y, 1, 1);
    }

    @Override
    public void dispose() {
        _texture.dispose();
    }

}