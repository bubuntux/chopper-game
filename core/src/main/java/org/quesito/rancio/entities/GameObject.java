package org.quesito.rancio.entities;

import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;

/**
 * @author Julio Gutierrez (12/23/13)
 */
public abstract class GameObject {

    public final Vector2 _position;
    public final Rectangle _bounds;

    protected GameObject(Vector2 position, Rectangle bounds) {
        _position = position;
        _bounds = bounds;
    }
}
