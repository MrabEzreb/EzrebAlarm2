package com.ezreb.alarm;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSeparator;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

import org.eclipse.wb.swing.FocusTraversalOnArray;
import org.json.JSONObject;

import com.ezreb.filebrowser.FileBrowser;

@SuppressWarnings("serial")
public class NewAlarm extends JDialog implements Runnable {

	private final JPanel contentPanel = new JPanel();
	private JTextField nameField;
	private JLabel lblNewLabel;
	private JTextField HourField;
	private JLabel lblHour;
	private JTextField MinuteField;
	private JLabel lblMinute;
	private JTextField secondField;
	private JLabel lblSecond;
	private JSeparator separator;
	private JSeparator separator_1;
	private JButton btnClickToChoose;
	private JCheckBox tglbtnRunFileOn;
	private int yoffset = 2;
	private int xoffset = 0;

	/**
	 * Launch the application.
	 */
	public void run() {
		try {
			NewAlarm dialog = new NewAlarm();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public NewAlarm() {
		setBounds(100, 100, 450, 300);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		GridBagLayout gbl_contentPanel = new GridBagLayout();
		gbl_contentPanel.columnWidths = new int[] {50, 50, 50, 50, 50, 50, 50, 50};
		gbl_contentPanel.rowHeights = new int[] {20, 20, 20, 23, 20, 20, 20};
		gbl_contentPanel.columnWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0};
		gbl_contentPanel.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0};
		contentPanel.setLayout(gbl_contentPanel);
		{
			separator = new JSeparator();
			separator.setPreferredSize(new Dimension(125, 2));
			GridBagConstraints gbc_separator = new GridBagConstraints();
			gbc_separator.insets = new Insets(10, 5, 10, 5);
			gbc_separator.fill = GridBagConstraints.BOTH;
			gbc_separator.gridwidth = 3;
			gbc_separator.gridx = 0+xoffset;
			gbc_separator.gridy = 0+yoffset;
			contentPanel.add(separator, gbc_separator);
		}
		{
			lblNewLabel = new JLabel("Name: ");
			lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
			GridBagConstraints gbc_lblNewLabel = new GridBagConstraints();
			gbc_lblNewLabel.anchor = GridBagConstraints.WEST;
			gbc_lblNewLabel.insets = new Insets(0, 0, 5, 5);
			gbc_lblNewLabel.gridx = 3+xoffset;
			gbc_lblNewLabel.gridy = 0+yoffset;
			contentPanel.add(lblNewLabel, gbc_lblNewLabel);
		}
		lblNewLabel.setLabelFor(nameField);
		{
			nameField = new JTextField();
			nameField.setHorizontalAlignment(SwingConstants.CENTER);
			GridBagConstraints gbc_nameField = new GridBagConstraints();
			gbc_nameField.insets = new Insets(0, 0, 5, 5);
			gbc_nameField.fill = GridBagConstraints.BOTH;
			gbc_nameField.gridx = 4+xoffset;
			gbc_nameField.gridy = 0+yoffset;
			contentPanel.add(nameField, gbc_nameField);
			nameField.setColumns(10);
		}
		{
			separator_1 = new JSeparator();
			separator_1.setPreferredSize(new Dimension(125, 2));
			GridBagConstraints gbc_separator_1 = new GridBagConstraints();
			gbc_separator_1.fill = GridBagConstraints.BOTH;
			gbc_separator_1.insets = new Insets(10, 5, 10, 5);
			gbc_separator_1.gridwidth = 3;
			gbc_separator_1.gridx = 5+xoffset;
			gbc_separator_1.gridy = 0+yoffset;
			contentPanel.add(separator_1, gbc_separator_1);
		}
		{
			lblHour = new JLabel("Hour:");
			GridBagConstraints gbc_lblHour = new GridBagConstraints();
			gbc_lblHour.anchor = GridBagConstraints.WEST;
			gbc_lblHour.insets = new Insets(0, 0, 5, 5);
			gbc_lblHour.gridx = 1+xoffset;
			gbc_lblHour.gridy = 1+yoffset;
			contentPanel.add(lblHour, gbc_lblHour);
		}
		lblHour.setLabelFor(HourField);
		{
			HourField = new JTextField();
			GridBagConstraints gbc_HourField = new GridBagConstraints();
			gbc_HourField.fill = GridBagConstraints.BOTH;
			gbc_HourField.insets = new Insets(0, 0, 5, 5);
			gbc_HourField.gridx = 2+xoffset;
			gbc_HourField.gridy = 1+yoffset;
			contentPanel.add(HourField, gbc_HourField);
			HourField.setColumns(10);
		}
		tglbtnRunFileOn = new JCheckBox("Run File On Activation?");
		tglbtnRunFileOn.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				NewAlarm.this.btnClickToChoose.setVisible(NewAlarm.this.tglbtnRunFileOn.isSelected());
			}
		});
		{
			lblMinute = new JLabel("Minute:");
			GridBagConstraints gbc_lblMinute = new GridBagConstraints();
			gbc_lblMinute.anchor = GridBagConstraints.WEST;
			gbc_lblMinute.insets = new Insets(0, 0, 5, 5);
			gbc_lblMinute.gridx = 3+xoffset;
			gbc_lblMinute.gridy = 1+yoffset;
			contentPanel.add(lblMinute, gbc_lblMinute);
		}
		lblMinute.setLabelFor(MinuteField);
		{
			MinuteField = new JTextField();
			MinuteField.setColumns(10);
			GridBagConstraints gbc_MinuteField = new GridBagConstraints();
			gbc_MinuteField.fill = GridBagConstraints.BOTH;
			gbc_MinuteField.insets = new Insets(0, 0, 5, 5);
			gbc_MinuteField.gridx = 4+xoffset;
			gbc_MinuteField.gridy = 1+yoffset;
			contentPanel.add(MinuteField, gbc_MinuteField);
		}
		{
			lblSecond = new JLabel("Second:");
			GridBagConstraints gbc_lblSecond = new GridBagConstraints();
			gbc_lblSecond.anchor = GridBagConstraints.WEST;
			gbc_lblSecond.insets = new Insets(0, 0, 5, 5);
			gbc_lblSecond.gridx = 5+xoffset;
			gbc_lblSecond.gridy = 1+yoffset;
			contentPanel.add(lblSecond, gbc_lblSecond);
		}
		{
			lblSecond.setLabelFor(secondField);
		}
		{
			secondField = new JTextField();
			secondField.setColumns(10);
			GridBagConstraints gbc_secondField = new GridBagConstraints();
			gbc_secondField.fill = GridBagConstraints.BOTH;
			gbc_secondField.insets = new Insets(0, 0, 5, 5);
			gbc_secondField.gridx = 6+xoffset;
			gbc_secondField.gridy = 1+yoffset;
			contentPanel.add(secondField, gbc_secondField);
		}
		GridBagConstraints gbc_tglbtnRunFileOn = new GridBagConstraints();
		gbc_tglbtnRunFileOn.anchor = GridBagConstraints.NORTHWEST;
		gbc_tglbtnRunFileOn.insets = new Insets(0, 0, 0, 5);
		gbc_tglbtnRunFileOn.gridwidth = 3;
		gbc_tglbtnRunFileOn.gridx = 0+xoffset;
		gbc_tglbtnRunFileOn.gridy = 2+yoffset;
		contentPanel.add(tglbtnRunFileOn, gbc_tglbtnRunFileOn);
		btnClickToChoose = new JButton("Click To Choose File To Run On Alarm Activation");
		btnClickToChoose.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				FileBrowser fb = new FileBrowser();
				fb.run3(NewAlarm.this);
			}
		});
		GridBagConstraints gbc_btnClickToChoose = new GridBagConstraints();
		gbc_btnClickToChoose.anchor = GridBagConstraints.NORTHEAST;
		gbc_btnClickToChoose.gridwidth = 5;
		gbc_btnClickToChoose.gridx = 3+xoffset;
		gbc_btnClickToChoose.gridy = 2+yoffset;
		contentPanel.add(btnClickToChoose, gbc_btnClickToChoose);
		contentPanel.setFocusTraversalPolicy(new FocusTraversalOnArray(new Component[]{separator, lblNewLabel, nameField, separator_1, lblHour, HourField, lblMinute, MinuteField, lblSecond, secondField, tglbtnRunFileOn, btnClickToChoose}));
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton okButton = new JButton("OK");
				okButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						Thread adder = new Thread(new Runnable() {
							
							@Override
							public void run() {
								NewAlarm.this.addNewAlarm(NewAlarm.this.nameField.getText(), new Integer(NewAlarm.this.HourField.getText()), new Integer(NewAlarm.this.MinuteField.getText()), new Integer(NewAlarm.this.secondField.getText()));
							}
						});
						adder.start();
					}
				});
				okButton.setActionCommand("OK");
				buttonPane.add(okButton);
				getRootPane().setDefaultButton(okButton);
			}
			{
				JButton cancelButton = new JButton("Cancel");
				cancelButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						NewAlarm.this.dispose();
					}
				});
				cancelButton.setActionCommand("Cancel");
				buttonPane.add(cancelButton);
			}
		}
	}
	public File selectedFile;
	public JLabel getLblNewLabel() {
		return lblNewLabel;
	}
	public JLabel getLblHour() {
		return lblHour;
	}
	public JLabel getLblMinute() {
		return lblMinute;
	}
	public JLabel getLblSecond() {
		return lblSecond;
	}
	private void addNewAlarm(String name, int Hour, int Minute, int Second) {
		Alarm newOne;
		if(this.selectedFile == null) {
			newOne = new Alarm(name, new Time(Hour, Minute, Second), new AlarmPopup(), new File(System.getProperty("os.arch")));
		} else {
			newOne = new Alarm(name, new Time(Hour, Minute, Second), new AlarmPopup(), this.selectedFile);
		}
		Alarm[] oldOnes = Main.alarmsAll;
		Alarm[] newOnes = new Alarm[oldOnes.length+1];
		JSONObject[] newJsons = new JSONObject[oldOnes.length+1];
		for (int i = 0; i < oldOnes.length; i++) {
			newJsons[i] = oldOnes[i].toJSON();
			oldOnes[i].isActive = false;
		}
		newJsons[oldOnes.length] = newOne.toJSON();
		newOne.isActive = false;
		for (int i = 0; i < newJsons.length; i++) {
			System.out.println(newJsons[i]);
			newOnes[i] = new Alarm(newJsons[i]);
		}
		Main.alarmsAll = newOnes;
		AlarmFile.saveAlarms(Main.alarmsAll);
	}
	public void getFile(File f) {
		NewAlarm.this.selectedFile = f;
		System.out.println(f.getAbsolutePath());
	}

}
