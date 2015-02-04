package com.ezreb.alarm;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JComboBox;

@SuppressWarnings("serial")
public class RemoveAlarm extends JDialog implements Runnable {

	private final JPanel contentPanel = new JPanel();
	private JButton btnRemoveAlarm;
	private JComboBox comboBox;

	/**
	 * Launch the application.
	 */
	public void run() {
		try {
			RemoveAlarm dialog = new RemoveAlarm();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public RemoveAlarm() {
		setBounds(100, 100, 450, 300);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setLayout(new FlowLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		{
			alarms = new Alarm[Main.alarmsAll.length];
			for (int i = 0; i < alarms.length; i++) {
				alarms[i] = Main.alarmsAll[i];
			}
			String[] alarmStr = new String[Main.alarmsAll.length];
			for (int i = 0; i < alarms.length; i++) {
				alarmStr[i] = Main.alarmsAll[i].name+": "+Main.alarmsAll[i].time.toString();
			}
			comboBox = new JComboBox(alarmStr);
			comboBox.setMaximumRowCount(100);
			contentPanel.add(comboBox);
		}
		{
			btnRemoveAlarm = new JButton("Remove Alarm");
			btnRemoveAlarm.addActionListener(new ActionListener() {
				
				@Override
				public void actionPerformed(ActionEvent e) {
					alarms[comboBox.getSelectedIndex()].isActive = false;
					alarms[comboBox.getSelectedIndex()] = null;
					int i2 = 0;
					for (int i = 0; i < Main.alarmsAll.length; i++) {
						Main.alarmsAll[i2].isActive = false;
					}
					Main.alarmsAll = new Alarm[Main.alarmsAll.length-1];
					for (int i = 0; i < alarms.length; i++) {
						if(alarms[i] != null) {
							Main.alarmsAll[i2] = alarms[i];
						}
						i2++;
					}
					AlarmFile.saveAlarms(Main.alarmsAll);
					
				}
			});
			contentPanel.add(btnRemoveAlarm);
		}
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton cancelButton = new JButton("Cancel");
				cancelButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						RemoveAlarm.this.dispose();
					}
				});
				buttonPane.add(cancelButton);
			}
		}
	}
	Alarm[] alarms;

	public JButton getBtnRemoveAlarm() {
		return btnRemoveAlarm;
	}
	public JComboBox getComboBox() {
		return comboBox;
	}
}
