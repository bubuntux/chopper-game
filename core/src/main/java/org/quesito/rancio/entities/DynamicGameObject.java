package org.quesito.rancio.entities;

import com.badlogic.gdx.math.Vector2;

/**
 * @author Julio Gutierrez (12/23/13)
 */
public abstract class DynamicGameObject extends GameObject {

    protected final Vector2 _velocity;
    protected final Vector2 _accel;

    public DynamicGameObject(float x, float y) {
        super(x, y);
        _velocity = new Vector2();
        _accel = new Vector2();
    }

}
