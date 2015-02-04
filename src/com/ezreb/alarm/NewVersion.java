package com.ezreb.alarm;

import java.awt.BorderLayout;
import java.awt.Desktop;
import java.awt.FlowLayout;
import java.awt.GraphicsEnvironment;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;

import java.awt.GridLayout;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

@SuppressWarnings("serial")
public class NewVersion extends JDialog implements Runnable {

	private final JPanel contentPanel = new JPanel();

	/**
	 * Launch the application.
	 */
	public void run() {
		try {
			this.setAlwaysOnTop(true);
			this.setUndecorated(true);
			this.setVisible(true);
			this.requestFocus();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public NewVersion(String web) {
		setBounds(100, 100, 450, 300);
		int x = GraphicsEnvironment.getLocalGraphicsEnvironment().getCenterPoint().x;
		int y = GraphicsEnvironment.getLocalGraphicsEnvironment().getCenterPoint().y;
		this.setLocation(x-225, y-150);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(new GridLayout(5, 1, 0, 0));
		{
			JLabel lblAnUpdateFor = new JLabel("An update for EzrebAlarm 2 availiable.");
			contentPanel.add(lblAnUpdateFor);
		}
		{
			JLabel lblCurrentVersionIs = new JLabel("Current Version is: "+web.toString());
			contentPanel.add(lblCurrentVersionIs);
		}
		{
			JLabel lblThisVersionIs = new JLabel("This Version is: "+Version.current);
			contentPanel.add(lblThisVersionIs);
		}
		{
			JLabel lblWouldYouLike = new JLabel("Would you like to update?");
			contentPanel.add(lblWouldYouLike);
		}
		{
			JLabel lblAfterUpdatingPlease = new JLabel("After updating, please restart EzrebAlarm 2.");
			contentPanel.add(lblAfterUpdatingPlease);
		}
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton okButton = new JButton("Update");
				okButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						Desktop d = Desktop.getDesktop();
						try {
							NewVersion.this.dispose();
							d.browse(new URI("https://github.com/MrabEzreb/EzrebAlarm2/releases/latest"));
						} catch (IOException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						} catch (URISyntaxException e1) {
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
				JButton cancelButton = new JButton("Continue to EzrebAlarm");
				cancelButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						NewVersion.this.dispose();
					}
				});
				cancelButton.setActionCommand("Cancel");
				buttonPane.add(cancelButton);
			}
		}
	}

}
