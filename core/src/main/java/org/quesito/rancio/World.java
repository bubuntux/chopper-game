package org.quesito.rancio;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import org.quesito.rancio.entities.Chopper;

/**
 * @author Julio Gutierrez (12/23/13)
 */
public class World {

    public static final float GRAVITY = -500f;

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
