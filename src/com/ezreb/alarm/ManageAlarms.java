package com.ezreb.alarm;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSplitPane;
import javax.swing.border.EmptyBorder;

import java.awt.GridBagLayout;

import javax.swing.JScrollPane;

import java.awt.Dimension;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.util.Calendar;
import java.awt.GridLayout;

import javax.swing.JTextField;

import java.awt.GridBagConstraints;
import java.awt.Insets;

import javax.swing.JSeparator;
import javax.swing.JCheckBox;

import com.ezreb.filebrowser.FileBrowser;
import com.ezreb.filebrowser.images.ImageLoader;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import javax.swing.SwingConstants;
import javax.swing.Icon;

@SuppressWarnings("serial")
public class ManageAlarms extends JDialog implements Runnable {

	private final JPanel contentPanel = new JPanel();
	private JScrollPane scrollPane;
	private JPanel viewContainer;
	private int dayCalendar;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			ManageAlarms dialog = new ManageAlarms();
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public void run() {
		setVisible(true);
		fullRefresh();
	}
	public ManageAlarms(int dayCalendar) {
		this();
		this.dayCalendar = dayCalendar;
	}
	/**
	 * Create the dialog.
	 */
	public ManageAlarms() {
		this.dayCalendar = Calendar.getInstance().get(Calendar.DAY_OF_WEEK);
		setTitle("test");
		setPreferredSize(new Dimension(450, 300));
		setUndecorated(true);
		setBounds(100, 100, 450, 300);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		GridBagLayout gbl_contentPanel = new GridBagLayout();
		gbl_contentPanel.columnWidths = new int[] {100, 240};
		gbl_contentPanel.rowHeights = new int[] {25, 25, 25, 25, 25, 25, 0};
		gbl_contentPanel.columnWeights = new double[]{0.0, 0.0};
		gbl_contentPanel.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 1.0};
		contentPanel.setLayout(gbl_contentPanel);
		{
			JLabel lblAlarmName = new JLabel("Alarm Name");
			GridBagConstraints gbc_lblAlarmName = new GridBagConstraints();
			gbc_lblAlarmName.fill = GridBagConstraints.VERTICAL;
			gbc_lblAlarmName.insets = new Insets(0, 5, 5, 5);
			gbc_lblAlarmName.gridx = 0;
			gbc_lblAlarmName.gridy = 0;
			contentPanel.add(lblAlarmName, gbc_lblAlarmName);
		}
		{
			textField = new JTextField();
			GridBagConstraints gbc_textField = new GridBagConstraints();
			gbc_textField.insets = new Insets(0, 0, 5, 0);
			gbc_textField.fill = GridBagConstraints.BOTH;
			gbc_textField.gridx = 1;
			gbc_textField.gridy = 0;
			contentPanel.add(textField, gbc_textField);
			textField.setColumns(10);
		}
		{
			JLabel lblAlarmTime = new JLabel("Alarm Time");
			GridBagConstraints gbc_lblAlarmTime = new GridBagConstraints();
			gbc_lblAlarmTime.fill = GridBagConstraints.VERTICAL;
			gbc_lblAlarmTime.insets = new Insets(0, 0, 5, 5);
			gbc_lblAlarmTime.gridx = 0;
			gbc_lblAlarmTime.gridy = 1;
			contentPanel.add(lblAlarmTime, gbc_lblAlarmTime);
		}
		{
			JSeparator separator = new JSeparator();
			GridBagConstraints gbc_separator = new GridBagConstraints();
			gbc_separator.fill = GridBagConstraints.HORIZONTAL;
			gbc_separator.insets = new Insets(0, 5, 5, 0);
			gbc_separator.gridx = 1;
			gbc_separator.gridy = 1;
			contentPanel.add(separator, gbc_separator);
		}
		{
			JLabel lblHour = new JLabel("Hour");
			GridBagConstraints gbc_lblHour = new GridBagConstraints();
			gbc_lblHour.insets = new Insets(0, 0, 5, 5);
			gbc_lblHour.gridx = 0;
			gbc_lblHour.gridy = 2;
			contentPanel.add(lblHour, gbc_lblHour);
		}
		{
			textField_1 = new JTextField();
			GridBagConstraints gbc_textField_1 = new GridBagConstraints();
			gbc_textField_1.insets = new Insets(0, 0, 5, 0);
			gbc_textField_1.fill = GridBagConstraints.HORIZONTAL;
			gbc_textField_1.gridx = 1;
			gbc_textField_1.gridy = 2;
			contentPanel.add(textField_1, gbc_textField_1);
			textField_1.setColumns(10);
		}
		{
			JLabel lblMinute = new JLabel("Minute");
			GridBagConstraints gbc_lblMinute = new GridBagConstraints();
			gbc_lblMinute.insets = new Insets(0, 0, 5, 5);
			gbc_lblMinute.gridx = 0;
			gbc_lblMinute.gridy = 3;
			contentPanel.add(lblMinute, gbc_lblMinute);
		}
		{
			textField_2 = new JTextField();
			GridBagConstraints gbc_textField_2 = new GridBagConstraints();
			gbc_textField_2.insets = new Insets(0, 0, 5, 0);
			gbc_textField_2.fill = GridBagConstraints.HORIZONTAL;
			gbc_textField_2.gridx = 1;
			gbc_textField_2.gridy = 3;
			contentPanel.add(textField_2, gbc_textField_2);
			textField_2.setColumns(10);
		}
		{
			JLabel lblSeconds = new JLabel("Seconds");
			GridBagConstraints gbc_lblSeconds = new GridBagConstraints();
			gbc_lblSeconds.insets = new Insets(0, 0, 5, 5);
			gbc_lblSeconds.gridx = 0;
			gbc_lblSeconds.gridy = 4;
			contentPanel.add(lblSeconds, gbc_lblSeconds);
		}
		{
			textField_3 = new JTextField();
			GridBagConstraints gbc_textField_3 = new GridBagConstraints();
			gbc_textField_3.insets = new Insets(0, 0, 5, 0);
			gbc_textField_3.fill = GridBagConstraints.HORIZONTAL;
			gbc_textField_3.gridx = 1;
			gbc_textField_3.gridy = 4;
			contentPanel.add(textField_3, gbc_textField_3);
			textField_3.setColumns(10);
		}
		{
			JButton btnSelect = new JButton("Select");
			btnSelect.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					FileBrowser fb = new FileBrowser();
					fb.run3(ManageAlarms.this);
				}
			});
			GridBagConstraints gbc_btnSelect = new GridBagConstraints();
			gbc_btnSelect.insets = new Insets(0, 0, 5, 5);
			gbc_btnSelect.gridx = 0;
			gbc_btnSelect.gridy = 5;
			contentPanel.add(btnSelect, gbc_btnSelect);
		}
		{
			JCheckBox chckbxRunProgramOn = new JCheckBox("Run Program on Alarm?");
			GridBagConstraints gbc_chckbxRunProgramOn = new GridBagConstraints();
			gbc_chckbxRunProgramOn.insets = new Insets(0, 0, 5, 0);
			gbc_chckbxRunProgramOn.gridx = 1;
			gbc_chckbxRunProgramOn.gridy = 5;
			contentPanel.add(chckbxRunProgramOn, gbc_chckbxRunProgramOn);
		}
		{
			JPanel buttonPane = new JPanel();
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			GridBagLayout gbl_buttonPane = new GridBagLayout();
			gbl_buttonPane.columnWidths = new int[] {23, 53, 23, 175, 45, 45, 30};
			gbl_buttonPane.rowHeights = new int[] {23, 0};
			gbl_buttonPane.columnWeights = new double[]{0.0, 0.0, 0.0, 1.0, 0.0, 0.0};
			gbl_buttonPane.rowWeights = new double[]{0.0, Double.MIN_VALUE};
			buttonPane.setLayout(gbl_buttonPane);
			{
				JButton btnUp = new JButton(new ImageIcon(ImageLoader.ARROW_UP));
				btnUp.setPreferredSize(new Dimension(23, 23));
				btnUp.setMargin(new Insets(2, 4, 2, 4));
				btnUp.addActionListener(new ActionListener() {
					
					@Override
					public void actionPerformed(ActionEvent e) {
						ManageAlarms.this.moveAlarm(ManageAlarms.this.selected, true);
						System.out.println("moved alarms");
					}
				});
				GridBagConstraints gbc_btnUp = new GridBagConstraints();
				gbc_btnUp.fill = GridBagConstraints.BOTH;
				gbc_btnUp.gridx = 0;
				gbc_btnUp.gridy = 0;
				buttonPane.add(btnUp, gbc_btnUp);
			}
			{
				JLabel lblArrange = new JLabel("Arrange");
				lblArrange.setHorizontalAlignment(SwingConstants.CENTER);
				lblArrange.setPreferredSize(new Dimension(53, 23));
				GridBagConstraints gbc_lblArrange = new GridBagConstraints();
				gbc_lblArrange.gridx = 1;
				gbc_lblArrange.gridy = 0;
				buttonPane.add(lblArrange, gbc_lblArrange);
			}
			{
				JButton button = new JButton(new ImageIcon(ImageLoader.ARROW_DOWN));
				button.addActionListener(new ActionListener() {
					
					@Override
					public void actionPerformed(ActionEvent e) {
						ManageAlarms.this.moveAlarm(ManageAlarms.this.selected, false);
						System.out.println("moved alarms");
					}
				});
				button.setPreferredSize(new Dimension(23, 23));
				button.setMargin(new Insets(2, 4, 2, 4));
				GridBagConstraints gbc_button = new GridBagConstraints();
				gbc_button.fill = GridBagConstraints.BOTH;
				gbc_button.gridx = 2;
				gbc_button.gridy = 0;
				buttonPane.add(button, gbc_button);
			}
			{
				JButton okButton = new JButton("Load Different Day");
				okButton.setActionCommand("OK");
				GridBagConstraints gbc_okButton = new GridBagConstraints();
				gbc_okButton.fill = GridBagConstraints.BOTH;
				gbc_okButton.insets = new Insets(0, 0, 0, 5);
				gbc_okButton.gridx = 5;
				gbc_okButton.gridy = 0;
				buttonPane.add(okButton, gbc_okButton);
				getRootPane().setDefaultButton(okButton);
			}
			{
				JButton cancelButton = new JButton("Close");
				cancelButton.addActionListener(new ActionListener() {
					
					@Override
					public void actionPerformed(ActionEvent e) {
						ManageAlarms.this.setVisible(false);
						ManageAlarms.this.setEnabled(false);
					}
				});
				cancelButton.setActionCommand("Cancel");
				GridBagConstraints gbc_cancelButton = new GridBagConstraints();
				gbc_cancelButton.fill = GridBagConstraints.BOTH;
				gbc_cancelButton.gridx = 6;
				gbc_cancelButton.gridy = 0;
				buttonPane.add(cancelButton, gbc_cancelButton);
			}
		}
		{
			scrollPane = new JScrollPane();
			scrollPane.setPreferredSize(new Dimension(100, 2));
			getContentPane().add(scrollPane, BorderLayout.WEST);
		}
		{
			viewContainer = new JPanel();
			viewContainer.setBackground(Color.WHITE);
			viewContainer.setPreferredSize(new Dimension(80, 200));
			viewContainer.setIgnoreRepaint(true);
			scrollPane.setViewportView(viewContainer);
		}
		
	}
	public Alarm[] alarms;
	public AlarmEntry[] alarmes;

	public int selected;
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;
	private JTextField textField_3;
	public JScrollPane getScrollPane() {
		return scrollPane;
	}
	public void refresh(AlarmEntry ae) {
		System.out.println("ran");
		for (int i = 0; i < alarmes.length; i++) {
			if(alarmes[i].equals(ae)) {
				this.selected = i;
			}
		}
		for (AlarmEntry alarmEntry : alarmes) {
			alarmEntry.setSelected(false);
			alarmEntry.setBackground(Color.WHITE);
//			alarmEntry.setContentAreaFilled(true);
		}
		alarmes[this.selected].setSelected(true);
	}
	private void fullRefresh() {
		if(alarms != null) {
			for (int i = 0; i < alarms.length; i++) {
				alarms[i].isActive = false;
			}
		}
		this.alarms = AlarmFile.getAlarms(dayCalendar);
		viewContainer.removeAll();
		viewContainer.paint(scrollPane.getViewport().getGraphics());
		this.alarmes = new AlarmEntry[alarms.length];
		for (int i = 0; i < alarms.length; i++) {
			alarmes[i] = new AlarmEntry(alarms[i], new SelectionListener() {
				
				@Override
				public void onSelection(Alarm a, AlarmEntry a2) {
					ManageAlarms.this.refresh(a2);
				}
				@Override
				public void onSelection(File f) {}
			});
			
			alarmes[i].setBackground(new Color(0, 0, 0, 0));
			viewContainer.add(alarmes[i]);
		}
		viewContainer.paintAll(viewContainer.getGraphics());
	}
	public JPanel getViewContainer() {
		return viewContainer;
	}
	public void getFile(File f) {
		alarmes[this.selected].a.toRun = f;
		AlarmFile.saveAlarms(this.alarms, dayCalendar);
	}
	public void moveAlarm(int number, boolean up) {
		Alarm[] oldAls = AlarmFile.getAlarms(dayCalendar);
		Alarm[] newAls = new Alarm[oldAls.length];
		for (int i = 0; i < newAls.length; i++) {
			newAls[i] = new Alarm(oldAls[i].toJSON());
		}
		if(up) {
			newAls[number] = new Alarm(oldAls[number-1].toJSON());
			newAls[number-1] = new Alarm(oldAls[number].toJSON());
		} else {
			newAls[number] = new Alarm(oldAls[number+1].toJSON());
			newAls[number+1] = new Alarm(oldAls[number].toJSON());
		}
		for (int i = 0; i < oldAls.length; i++) {
			oldAls[i].isActive = false;
		}
		AlarmFile.saveAlarms(newAls, dayCalendar);
		for (int i = 0; i < newAls.length; i++) {
			newAls[i].isActive = false;
		}
		this.selected = -1;
		System.out.println("done");
	}
}
