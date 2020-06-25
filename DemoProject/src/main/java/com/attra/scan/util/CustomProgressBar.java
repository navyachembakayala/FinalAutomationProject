package com.attra.scan.util;

import javax.swing.JFrame;
import javax.swing.JProgressBar;

public class CustomProgressBar extends JFrame {
	public JProgressBar jb;

	private static final long serialVersionUID = -6414386421760615531L;
	int i = 0, num = 0;

	public CustomProgressBar() {

		jb = new JProgressBar(0, 100);

		jb.setBounds(40, 40, 260, 30);
		jb.setValue(0);
		jb.setStringPainted(true);
		add(jb);
		setSize(450, 550);
		setLayout(null);
	}
}
