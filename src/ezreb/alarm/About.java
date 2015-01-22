package ezreb.alarm;

import java.awt.BorderLayout;
import java.awt.GraphicsEnvironment;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

@SuppressWarnings("serial")
public class About extends JFrame implements Runnable {

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public void run() {
		try {
			this.toFront();
			this.requestFocus();
			this.setAlwaysOnTop(true);
			this.setVisible(true);
			this.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
			int x = GraphicsEnvironment.getLocalGraphicsEnvironment().getCenterPoint().x-225;
			int y = GraphicsEnvironment.getLocalGraphicsEnvironment().getCenterPoint().y-150;
			this.setLocation(x, y);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the frame.
	 */
	public About() {
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(0, 0));
		
		JPanel panel = new JPanel();
		contentPane.add(panel, BorderLayout.SOUTH);
		panel.setLayout(new BorderLayout(0, 0));
		
		JButton btnNewButton_1 = new JButton("OK");
		btnNewButton_1.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				About.this.setVisible(false);
				About.this.setEnabled(false);
			}
		});
		panel.add(btnNewButton_1, BorderLayout.EAST);
		
		JPanel panel_1 = new JPanel();
		contentPane.add(panel_1, BorderLayout.CENTER);
		panel_1.setLayout(new GridLayout(10, 1, 0, 0));
		
		JLabel lblEzrebAlarm = new JLabel("Ezreb Alarm");
		panel_1.add(lblEzrebAlarm);
		
		JLabel lblMadeByMrab = new JLabel("Made by Mrab Ezreb");
		panel_1.add(lblMadeByMrab);
		
		JLabel lblInCollaborationWith = new JLabel("In Collaboration With \"Jam, The Professional Lurker\".");
		panel_1.add(lblInCollaborationWith);
		
		JLabel lblSoYeah = new JLabel("So, yeah...");
		panel_1.add(lblSoYeah);
		
		JLabel lblUmIGuess = new JLabel("Um... I guess you could just click ok now?");
		panel_1.add(lblUmIGuess);
		
		JLabel lblIDontKnow = new JLabel("I don't know, :P");
		panel_1.add(lblIDontKnow);
		
		JLabel lblByep = new JLabel("Bye :P");
		panel_1.add(lblByep);
		setVisible(false);
		this.addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				// TODO Auto-generated method stub
				About.this.setVisible(false);
				About.this.setEnabled(false);
			}
		});
	}

}
