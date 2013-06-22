package org.quesito.rancio.core.screens;

import tripleplay.game.ScreenStack;

public class OptionScreen extends Screen {

	protected OptionScreen(ScreenStack stack) {
		super(stack);
	}

	@Override
	public String getButtonLabel() {
		return "Options";
	}
}
