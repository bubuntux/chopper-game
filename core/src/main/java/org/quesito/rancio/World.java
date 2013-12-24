package org.quesito.rancio;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import org.quesito.rancio.entities.Chopper;

/**
 * @author Julio Gutierrez (12/23/13)
 */
public class World {

    public static final Vector2 GRAVITY = new Vector2(0, -12);

    private final Chopper _chopper;

    public World() {
        _chopper = new Chopper(100, 100);
    }

    public void update(float delta) {
        _chopper.update(delta);
    }

    public void draw(SpriteBatch spriteBatch) {
        spriteBatch.disableBlending();
        spriteBatch.begin();
        _chopper.draw(spriteBatch);
        spriteBatch.end();
    }
}
