package org.quesito.rancio.core.screens;

import tripleplay.game.ScreenStack;

public class GameScreen extends Screen {

	public GameScreen(ScreenStack stack) {
		super(stack);
	}

	@Override
	public String getButtonLabel() {
		return "Start Game";
	}
}
