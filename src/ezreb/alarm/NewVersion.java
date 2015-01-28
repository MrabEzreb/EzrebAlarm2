package ezreb.alarm;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import java.awt.GridLayout;

public class NewVersion extends JDialog {

	private final JPanel contentPanel = new JPanel();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			NewVersion dialog = new NewVersion(null);
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public NewVersion(Version web) {
		setBounds(100, 100, 450, 300);
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
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton okButton = new JButton("Update");
				okButton.setActionCommand("OK");
				buttonPane.add(okButton);
				getRootPane().setDefaultButton(okButton);
			}
			{
				JButton cancelButton = new JButton("Continue");
				cancelButton.setActionCommand("Cancel");
				buttonPane.add(cancelButton);
			}
		}
	}

}
