package org.gmol.TranslatingSysTray.gui;

import java.awt.event.MouseListener;

public interface IFrame extends IGui {

	public void addMouseListener(MouseListener l);
	void showFrame();
	void hideFrame();
	void setText(String txt);
	

}
