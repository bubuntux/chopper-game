package org.quesito.rancio;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import org.quesito.rancio.entities.Chopper;

/**
 * @author Julio Gutierrez (12/23/13)
 */
public class World {

    public static final float GRAVITY = -550f;

    private final OrthographicCamera _cam;

    private final Chopper _chopper;

    public World() {
        _cam = new OrthographicCamera(10, 15);
        _cam.position.set(5, 7.5f, 0);
        _chopper = new Chopper(0.5f, 0);
    }

    public void update(float delta) {
        _chopper.update(delta);
    }

    public void draw(SpriteBatch spriteBatch) {
        _cam.update();
        spriteBatch.setProjectionMatrix(_cam.combined);
        spriteBatch.enableBlending();
        spriteBatch.begin();
        _chopper.draw(spriteBatch);
        spriteBatch.end();
    }
}
