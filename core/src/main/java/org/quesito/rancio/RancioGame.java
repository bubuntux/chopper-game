package org.quesito.rancio;

import com.badlogic.gdx.Game;
import org.quesito.rancio.screens.GameScreen;

public class RancioGame extends Game {

    @Override
    public void create() {
        setScreen(new GameScreen());
    }

}