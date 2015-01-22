package ezreb.alarm;

import java.awt.BorderLayout;
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

@SuppressWarnings("serial")
public class OutputFrame extends JDialog {

	private final JPanel contentPanel = new JPanel();

	/**
	 * Create the dialog.
	 */
	public OutputFrame(Dimension size, String message, String LeftButton, String RightButton, ActionListener LeftAction, ActionListener RightAction) {
		setBounds(100, 100, 450, 300);
		this.setUndecorated(true);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		Point center = GraphicsEnvironment.getLocalGraphicsEnvironment().getCenterPoint();
		Point frame = new Point((int) Math.floor(center.x-(size.getWidth()/2)), (int) Math.floor(center.y-(size.getHeight()/2)));
		this.setLocation(frame);
		this.setSize(size);
		contentPanel.setLayout(new BorderLayout(0, 0));
		{
			JTextArea txtrTest = new JTextArea();
			txtrTest.setText(message);
			txtrTest.setWrapStyleWord(true);
			txtrTest.setLineWrap(true);
			txtrTest.setEditable(false);
			contentPanel.add(txtrTest, BorderLayout.CENTER);
		}
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton okButton = new JButton(LeftButton);
				buttonPane.add(okButton);
				getRootPane().setDefaultButton(okButton);
			}
			{
				JButton cancelButton = new JButton(RightButton);
				buttonPane.add(cancelButton);
			}
		}
	}

}
