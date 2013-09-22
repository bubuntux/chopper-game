package org.quesito.rancio.core.screens;

import org.quesito.rancio.core.World;
import playn.core.util.Clock;
import tripleplay.game.ScreenStack;

import static playn.core.PlayN.graphics;

public class GameScreen extends Screen {

	World _world;

	public GameScreen(ScreenStack stack) {
		super(stack);
	}

	@Override
	public void showTransitionCompleted() {
		super.showTransitionCompleted();
		_world = new World();
		graphics().rootLayer().add(_world.getLayer());
	}

	@Override
	public void update(int delta) {
		super.update(delta);
		if (_world != null) {
			_world.update(delta);
		}
	}

	@Override
	public void paint(Clock clock) {
		super.paint(clock);
	}

	@Override
	public String getButtonLabel() {
		return "Start Game";
	}
}
