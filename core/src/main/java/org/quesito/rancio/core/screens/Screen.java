package org.quesito.rancio.core.screens;

import playn.core.Font;
import playn.core.Keyboard;
import tripleplay.game.ScreenStack;
import tripleplay.game.UIAnimScreen;
import tripleplay.game.trans.SlideTransition;
import tripleplay.ui.Root;

import static playn.core.PlayN.graphics;
import static playn.core.PlayN.keyboard;

public abstract class Screen extends UIAnimScreen {

	protected static final Font TITLE_FONT = graphics().createFont("Helvetica", Font.Style.PLAIN, 24);
	private final ScreenStack _stack;
	protected Root _root;

	protected Screen(ScreenStack stack) {
		_stack = stack;
	}

	public abstract String getButtonLabel();

	@Override
	public void wasRemoved() {
		super.wasRemoved();
		//iface.destroyRoot(_root);
		while (layer.size() > 0) {
			layer.get(0).destroy();
		}
	}

	@Override
	public void wasShown() {
		super.wasShown();
		keyboard().setListener(new Keyboard.Adapter() {
			@Override
			public void onKeyDown(Keyboard.Event event) {
				switch (event.key()) {
					case ESCAPE:
						_stack.remove(Screen.this, new SlideTransition(_stack));
						break;
				}
			}
		});
	}
}
