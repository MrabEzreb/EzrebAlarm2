package ezreb.alarm;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JSeparator;
import javax.swing.SwingConstants;

import org.json.JSONObject;

import java.awt.Dimension;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

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
		FlowLayout fl_contentPanel = new FlowLayout(FlowLayout.CENTER, 5, 5);
		contentPanel.setLayout(fl_contentPanel);
		{
			separator = new JSeparator();
			separator.setPreferredSize(new Dimension(125, 2));
			contentPanel.add(separator);
		}
		{
			lblNewLabel = new JLabel("Name: ");
			lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
			contentPanel.add(lblNewLabel);
		}
		{
			nameField = new JTextField();
			nameField.setHorizontalAlignment(SwingConstants.CENTER);
			lblNewLabel.setLabelFor(nameField);
			contentPanel.add(nameField);
			nameField.setColumns(10);
		}
		{
			separator_1 = new JSeparator();
			separator_1.setPreferredSize(new Dimension(125, 2));
			contentPanel.add(separator_1);
		}
		{
			lblHour = new JLabel("Hour:");
			contentPanel.add(lblHour);
		}
		{
			HourField = new JTextField();
			lblHour.setLabelFor(HourField);
			contentPanel.add(HourField);
			HourField.setColumns(10);
		}
		{
			lblMinute = new JLabel("Minute:");
			contentPanel.add(lblMinute);
		}
		{
			MinuteField = new JTextField();
			lblMinute.setLabelFor(MinuteField);
			MinuteField.setColumns(10);
			contentPanel.add(MinuteField);
		}
		{
			lblSecond = new JLabel("Second:");
			contentPanel.add(lblSecond);
		}
		{
			secondField = new JTextField();
			lblSecond.setLabelFor(secondField);
			secondField.setColumns(10);
			contentPanel.add(secondField);
		}
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
		Alarm newOne = new Alarm(name, new Time(Hour, Minute, Second), new AlarmPopup());
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
			newOnes[i] = new Alarm(newJsons[i]);
		}
		Main.alarmsAll = newOnes;
		AlarmFile.saveAlarms(Main.alarmsAll);
	}

}
