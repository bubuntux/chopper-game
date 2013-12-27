package org.quesito.rancio;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.Disposable;
import org.quesito.rancio.entities.Bird;
import org.quesito.rancio.entities.Chopper;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * @author Julio Gutierrez (12/23/13)
 */
public class World implements Disposable {

    public static final float GRAVITY = 0.98f;

    private final OrthographicCamera _cam;
    private final Texture _background;

    private final Chopper _chopper;
    private final List<Bird> _birds;
    private final int _maxBirds;

    private final Random _random;

    private int _score;
    private float _deltaScore;

    public World() {
        _cam = new OrthographicCamera(10, 15);
        _cam.position.set(5, 7.5f, 0);
        _chopper = new Chopper(0.5f, 0);
        _background = new Texture(Gdx.files.internal("background.jpg"));
        _score = 0;
        _deltaScore = 0;
        _maxBirds = 5;
        _random = new Random();
        _birds = new ArrayList<Bird>(_maxBirds);
    }

    public int getScore() {
        return _score;
    }

    public void update(float delta) {
        _deltaScore += delta;
        if (_deltaScore > 1) {
            _deltaScore--;
            _score++; //Increment score with every second. TODO ???
        }
        _chopper.update(delta);
        if (_birds.size() < _maxBirds) {
            _birds.add(new Bird(_random.nextFloat() * 90, _random.nextFloat() * 14));
        }
        for (Bird bird : _birds) {
            bird.update(delta);
        }
        _cam.position.x = _chopper.getPosition().x + 4.5f; // 5 - 0.5 (original positions from constructor)
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
        spriteBatch.draw(_background, 0, 0, 100, 15);
        spriteBatch.end();
    }

    private void drawObjects(SpriteBatch spriteBatch) {
        spriteBatch.enableBlending();
        spriteBatch.begin();
        for (Bird bird : _birds) {
            bird.draw(spriteBatch);
        }
        _chopper.draw(spriteBatch);
        spriteBatch.end();
    }

    @Override
    public void dispose() {
        _chopper.dispose();
        _background.dispose();
    }
}