package org.quesito.rancio.entities;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import org.quesito.rancio.World;

/**
 * @author Julio Gutierrez (12/23/13)
 */
public class Chopper extends DynamicGameObject {

    private final Vector2 _accelation = new Vector2(1f, 2f);
    private final Vector2 _maxSpeed = new Vector2(6f, 5f);
    private final float _weight = 0.05f;

    private final Texture _texture;

    public Chopper(float x, float y) {
        super(x, y);
        _texture = new Texture(Gdx.files.internal("chopper.png"));
    }

    @Override
    public void update(float delta) {
        if (Gdx.input.isKeyPressed(Input.Keys.ANY_KEY) || Gdx.input.isTouched()) {
            _velocity.add(_accelation.x * delta, (_accelation.y - _weight - World.GRAVITY) * delta);
        } else {
            _velocity.add(_accelation.x * delta, -World.GRAVITY * delta);
        }

        if (_velocity.y > _maxSpeed.y) {
            _velocity.y = _maxSpeed.y;
        }
        if (_velocity.x > _maxSpeed.x) {
            _velocity.x = _maxSpeed.x;
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