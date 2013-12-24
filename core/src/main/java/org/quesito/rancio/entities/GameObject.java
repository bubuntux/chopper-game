package org.quesito.rancio.entities;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Disposable;

/**
 * @author Julio Gutierrez (12/23/13)
 */
public abstract class GameObject implements Disposable{

    protected final Vector2 _position;

    public GameObject(float x, float y) {
        _position = new Vector2(x, y);
    }

    public abstract void update(float delta);

    public abstract void draw(SpriteBatch spriteBatch);
}
