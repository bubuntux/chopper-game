package org.quesito.rancio.core.inputs;

import org.quesito.rancio.core.Chopper;
import playn.core.Keyboard;

public class KeyboardListener extends Keyboard.Adapter {

	private final Chopper _chopper;

	public KeyboardListener(Chopper chopper) {
		_chopper = chopper;
	}

	@Override
	public void onKeyDown(Keyboard.Event event) {
		switch (event.key()) {
			case UP:
				_chopper.setFalling(false);
				break;
		}
	}

	@Override
	public void onKeyUp(Keyboard.Event event) {
		switch (event.key()) {
			case UP:
				_chopper.setFalling(true);
				break;
		}
	}
}
