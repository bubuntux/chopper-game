package org.quesito.rancio.core.screens;

import react.UnitSlot;
import tripleplay.game.ScreenStack;
import tripleplay.game.UIScreen;
import tripleplay.game.trans.SlideTransition;
import tripleplay.ui.Background;
import tripleplay.ui.Button;
import tripleplay.ui.Group;
import tripleplay.ui.Label;
import tripleplay.ui.Root;
import tripleplay.ui.Shim;
import tripleplay.ui.SimpleStyles;
import tripleplay.ui.Style;
import tripleplay.ui.layout.AxisLayout;
import tripleplay.ui.layout.TableLayout;

public class MenuScreen extends UIScreen {

	private final ScreenStack _stack;
	private final Screen[] _screens;
	private Root _root;

	public MenuScreen(ScreenStack stack) {
		_stack = stack;
		_screens = new Screen[]{new GameScreen(stack), new OptionScreen(stack)};
	}

	@Override
	public void wasShown() {
		super.wasShown();
		_root = iface.createRoot(AxisLayout.vertical().gap(15), SimpleStyles.newSheet(), layer);
		_root.addStyles(Style.BACKGROUND.is(Background.bordered(0xFFCCCCCC, 0xFF99CCFF, 5).inset(5, 10)));

		_root.setSize(width(), height());

		_root.add(new Label("Chopper Game").addStyles(Style.FONT.is(Screen.TITLE_FONT)));

		Group grid = new Group(
			new TableLayout(TableLayout.COL.alignRight(), TableLayout.COL.stretch(), TableLayout.COL.stretch(), TableLayout.COL.stretch())
				.gaps(10, 10));
		_root.add(grid);

		for (int i = 0; i < _screens.length; i++) {
			final Screen screen = _screens[i];
			grid.add(new Shim(1, 1));
			grid.add(new Button(screen.getButtonLabel()).onClick(new UnitSlot() {
				@Override
				public void onEmit() {
					_stack.push(screen, new SlideTransition(_stack));
				}
			}));
		}
	}

	@Override
	public void wasHidden() {
		super.wasHidden();
		iface.destroyRoot(_root);
	}
}
