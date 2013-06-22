package org.quesito.rancio.core;

import org.quesito.rancio.core.screens.MenuScreen;
import playn.core.Game;
import playn.core.util.Clock;
import tripleplay.game.ScreenStack;

public class Rancio extends Game.Default {

	public static final int UPDATE_RATE = 50;
	private final Clock.Source _clock;
	private final ScreenStack _screens;

	public Rancio() {
		super(UPDATE_RATE);
		_clock = new Clock.Source(UPDATE_RATE);
		_screens = new ScreenStack();
	}

	@Override
	public void init() {
		_screens.push(new MenuScreen(_screens));
	}

	@Override
	public void update(int delta) {
		_clock.update(delta);
		_screens.update(delta);
	}

	@Override
	public void paint(float alpha) {
		_clock.paint(alpha);
		_screens.paint(_clock);
	}
}
