package com.ezreb.alarm;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JSplitPane;
import javax.swing.JToggleButton;
import javax.swing.SpringLayout.Constraints;



public class AlarmEntry extends JToggleButton {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1233931565469912244L;

	public AlarmEntry(final Alarm a, final SelectionListener sl) {
		setText(a.name);
		setToolTipText(a.time.toString());
		setBorder(null);
		setBorderPainted(false);
		setRolloverEnabled(false);
		setPreferredSize(new Dimension(70, 23));
		setSelected(false);
		addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				sl.onSelection(a, AlarmEntry.this);
			}
		});
	}
}