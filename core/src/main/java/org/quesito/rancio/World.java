package org.quesito.rancio;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.Disposable;
import org.quesito.rancio.entities.Chopper;

/**
 * @author Julio Gutierrez (12/23/13)
 */
public class World implements Disposable {

    public static final float GRAVITY = 9.8f;

    private final OrthographicCamera _cam;
    private final Texture _background;

    private final Chopper _chopper;

    public World() {
        _cam = new OrthographicCamera(10, 15);
        _cam.position.set(5, 7.5f, 0);
        _chopper = new Chopper(0.5f, 0);
        _background = new Texture(Gdx.files.internal("background.jpg"));
    }

    public void update(float delta) {
        _chopper.update(delta);
    }

    public void draw(SpriteBatch spriteBatch) {
        _cam.update();
        spriteBatch.setProjectionMatrix(_cam.combined);

        renderBackground(spriteBatch);
        drawObjects(spriteBatch);
    }

    public void renderBackground(SpriteBatch spriteBatch) {
        spriteBatch.disableBlending();
        spriteBatch.begin();
        spriteBatch.draw(_background, 0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
      /*  spriteBatch.draw(Assets.backgroundRegion, cam.position.x - FRUSTUM_WIDTH / 2, cam.position.y - FRUSTUM_HEIGHT / 2, FRUSTUM_WIDTH,
                FRUSTUM_HEIGHT);*/
        spriteBatch.end();
    }

    private void drawObjects(SpriteBatch spriteBatch) {
        spriteBatch.enableBlending();
        spriteBatch.begin();
        _chopper.draw(spriteBatch);
        spriteBatch.end();
    }

    @Override
    public void dispose() {
        _chopper.dispose();
    }
}