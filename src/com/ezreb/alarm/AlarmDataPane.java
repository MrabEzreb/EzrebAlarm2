package com.ezreb.alarm;

import javax.swing.JPanel;

import org.json.JSONObject;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import javax.swing.JLabel;
import javax.swing.JSplitPane;

public class AlarmDataPane extends JPanel {

	/**
	 * Create the panel.
	 */
	public AlarmDataPane(Alarm a) {
		setLayout(new GridLayout(10, 1, 0, 0));
		
		JSplitPane splitPane = new JSplitPane();
		add(splitPane);
		
		JLabel lblName = new JLabel("Name");
		splitPane.setLeftComponent(lblName);

	}

}
