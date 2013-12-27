package org.quesito.rancio.entities;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

/**
 * @author Julio Gutierrez (12/27/13)
 */
public class Bird extends DynamicGameObject {

    private static final Texture _texture = new Texture(Gdx.files.internal("bird.png"));

    public Bird(float x, float y) {
        super(x, y);

    }

    @Override
    public void update(float delta) {

    }

    @Override
    public void draw(SpriteBatch spriteBatch) {
        spriteBatch.draw(_texture, _position.x, _position.y, 1, 1);
    }

    @Override
    public void dispose() {

    }
}
