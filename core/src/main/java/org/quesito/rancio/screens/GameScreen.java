package org.quesito.rancio.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL10;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import org.quesito.rancio.World;

/**
 * @author Julio Gutierrez (12/23/13)
 */
public class GameScreen implements Screen {

    private static final int GAME_READY = 0;
    private static final int GAME_RUNNING = 1;
    private static final int GAME_PAUSED = 2;
    private static final int GAME_OVER = 3;

    private final OrthographicCamera _guiCam;
    private final SpriteBatch _spriteBatch;
    private final World _world;
    private final BitmapFont _font;

    private int _state;

    public GameScreen() {
        super();

        _guiCam = new OrthographicCamera(320, 480);
        _guiCam.position.set(320 / 2, 480 / 2, 0);

        _spriteBatch = new SpriteBatch();

        _world = new World();

        _font = new BitmapFont();

        _state = GAME_READY;
    }

    @Override
    public void render(float delta) {
        update(delta);
        draw();
    }

    @Override
    public void resize(int width, int height) {

    }

    @Override
    public void show() {

    }

    @Override
    public void hide() {

    }

    @Override
    public void pause() {
        if (_state == GAME_RUNNING) {
            _state = GAME_PAUSED;
        }
    }

    @Override
    public void resume() {
        _state = GAME_RUNNING;
    }

    @Override
    public void dispose() {
        _spriteBatch.dispose();
        _world.dispose();
    }

    private void update(float delta) {
        switch (_state) {
            case GAME_READY:
                updateReady();
                break;
            case GAME_RUNNING:
                updateRunning(delta);
                break;
            case GAME_PAUSED:
                //updatePaused();
                break;
            case GAME_OVER:
                //updateGameOver();
                break;
        }
    }

    private void updateReady() {
        if (Gdx.input.justTouched() || Gdx.input.isKeyPressed(Input.Keys.ANY_KEY)) {
            _state = GAME_RUNNING;
        }
    }

    private void updateRunning(float delta) {
        _world.update(delta);
    }

    private void draw() {
        Gdx.gl.glClear(GL10.GL_COLOR_BUFFER_BIT);

        _world.draw(_spriteBatch);

        _guiCam.update();
        _spriteBatch.setProjectionMatrix(_guiCam.combined);
        _spriteBatch.enableBlending();
        _spriteBatch.begin();
        switch (_state) {
            case GAME_READY:
                drawReady();
                break;
            case GAME_RUNNING:
                drawRunning();
                break;
            case GAME_PAUSED:
                //drawPaused();
                break;
            case GAME_OVER:
                //drawGameOver();
                break;
        }
        _spriteBatch.end();
    }

    private void drawReady() {
        _font.draw(_spriteBatch, "Ready?", 10, 20);
    }

    private void drawRunning() {
        _font.draw(_spriteBatch, "Score: " + _world.getScore(), 0, 20);
    }
}
