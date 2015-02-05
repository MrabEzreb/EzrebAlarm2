package com.ezreb.alarm;

import java.awt.BorderLayout;
import java.awt.Desktop;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GraphicsEnvironment;
import java.awt.Point;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTextArea;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

@SuppressWarnings("serial")
public class NewFile extends JDialog implements Runnable {

	private final JPanel contentPanel = new JPanel();

	/**
	 * Launch the application.
	 */
	public void run() {
		try {
			NewFile dialog = new NewFile();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public NewFile() {
		setBounds(100, 100, 450, 300);
		Dimension size = new Dimension(450, 300);
		Point center = GraphicsEnvironment.getLocalGraphicsEnvironment().getCenterPoint();
		Point frame = new Point((int) Math.floor(center.x-(size.getWidth()/2)), (int) Math.floor(center.y-(size.getHeight()/2)));
		this.setLocation(frame);
		this.setSize(size);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setLayout(new FlowLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		{
			JTextArea txtpnACurrentAlarmsjson = new JTextArea();
			txtpnACurrentAlarmsjson.setWrapStyleWord(true);
			txtpnACurrentAlarmsjson.setLineWrap(true);
			txtpnACurrentAlarmsjson.setRows(3);
			txtpnACurrentAlarmsjson.setEditable(false);
			txtpnACurrentAlarmsjson.setText("A current Alarms.json file was not found. Would you like to open the folder where it is stored and put in an existing file, or create one from scratch?");
			contentPanel.add(txtpnACurrentAlarmsjson);
		}
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton okButton = new JButton("Existing File");
				okButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						try {
							File f = new File(System.getProperty("user.home")+"\\AppData\\Roaming\\Ezreb\\EzrebAlarm");
							System.out.println(f.toURI());
							Desktop.getDesktop().browse(f.toURI());
							NewFile.this.setVisible(false);
							NewFile.this.setEnabled(false);
							while(Main.alarmJSON.exists() == false) {}
							NewFile.this.pressed = true;
						} catch (IOException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
					}
				});
				okButton.setActionCommand("OK");
				buttonPane.add(okButton);
				getRootPane().setDefaultButton(okButton);
			}
			{
				JButton cancelButton = new JButton("Create New File");
				cancelButton.addActionListener(new ActionListener() {
					
					@Override
					public void actionPerformed(ActionEvent e) {
						try {
							Main.alarmJSON.createNewFile();
							NewFile.this.setVisible(false);
							NewFile.this.setEnabled(false);
							BufferedWriter bw = new BufferedWriter(new FileWriter(Main.alarmJSON));
							bw.write("{\"Sunday\":[],\"Monday\":[],\"Tuesday\":[],\"Wednesday\":[],\"Thursday\":[],\"Friday\":[],\"Saturday\":[]}");
							bw.flush();
							bw.close();
						} catch (IOException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
						NewFile.this.pressed = true;
					}
				});
				cancelButton.setActionCommand("Cancel");
				buttonPane.add(cancelButton);
			}
		}
	}
	private boolean pressed = false;
	public void waitForButton() {
		while(this.pressed == false) {try {
			Thread.sleep(500);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}}
		return;
	}

}
