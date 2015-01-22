package ezreb.alarm;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GraphicsEnvironment;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.SwingConstants;

@SuppressWarnings("serial")
public class AlarmPopup extends JDialog implements AlarmAction {

	private final JPanel contentPanel = new JPanel();
	private JLabel lblAlarm;
	private JLabel lblTime;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			AlarmPopup dialog = new AlarmPopup();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public AlarmPopup() {
		setUndecorated(true);
		setBounds(100, 100, 450, 300);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(new GridLayout(4, 1, 0, 0));
		{
			JLabel lblNewLabel = new JLabel("An alarm has activated!");
			lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
			contentPanel.add(lblNewLabel);
		}
		{
			lblAlarm = new JLabel("Alarm: ");
			lblAlarm.setHorizontalAlignment(SwingConstants.CENTER);
			contentPanel.add(lblAlarm);
		}
		{
			lblTime = new JLabel("Time: ");
			lblTime.setHorizontalAlignment(SwingConstants.CENTER);
			contentPanel.add(lblTime);
		}
		{
			JLabel lblThankYouFor = new JLabel("Thank you for using Ezreb Alarm.");
			lblThankYouFor.setHorizontalAlignment(SwingConstants.CENTER);
			contentPanel.add(lblThankYouFor);
		}
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.CENTER));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton cancelButton = new JButton("Stop Alarm");
				cancelButton.addActionListener(new ActionListener() {
					
					@Override
					public void actionPerformed(ActionEvent e) {
						AlarmPopup.this.dispose();
						
					}
				});
				buttonPane.add(cancelButton);
			}
		}
	}

	@Override
	public void onAlarm(Alarm a) {
		// TODO Auto-generated method stub
		this.lblAlarm.setText("Alarm: "+a.name);
		this.lblTime.setText("Time: "+a.time.toString());
		this.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		this.setVisible(true);
		int x = GraphicsEnvironment.getLocalGraphicsEnvironment().getCenterPoint().x-225;
		int y = GraphicsEnvironment.getLocalGraphicsEnvironment().getCenterPoint().y-150;
		this.setLocation(x, y);
		this.toFront();
		this.requestFocus();
		this.setAlwaysOnTop(true);
		
	}

	public JLabel getLblAlarm() {
		return lblAlarm;
	}
	public JLabel getLblTime() {
		return lblTime;
	}
}
