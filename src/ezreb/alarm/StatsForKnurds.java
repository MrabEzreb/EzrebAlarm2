package ezreb.alarm;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JLabel;

@SuppressWarnings("serial")
public class StatsForKnurds extends JDialog implements Runnable{

	private final JPanel contentPanel = new JPanel();
	private JLabel freeMem;
	private JLabel totalMem;
	private JLabel maxMem;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			StatsForKnurds dialog = new StatsForKnurds();
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public StatsForKnurds() {
		setBounds(100, 100, 450, 300);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(new GridLayout(3, 1, 0, 0));
		{
			freeMem = new JLabel("New label");
			contentPanel.add(freeMem);
		}
		{
			totalMem = new JLabel("New label");
			contentPanel.add(totalMem);
		}
		{
			maxMem = new JLabel("New label");
			contentPanel.add(maxMem);
		}
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton cancelButton = new JButton("Close");
				cancelButton.setActionCommand("Cancel");
				cancelButton.addActionListener(new ActionListener() {
					
					@Override
					public void actionPerformed(ActionEvent e) {
						StatsForKnurds.this.setVisible(false);
						StatsForKnurds.this.setEnabled(false);
					}
				});
				buttonPane.add(cancelButton);
			}
		}
	}
	public void run() {
		this.setVisible(true);
		while(this.isEnabled() && this.isVisible()) {
			freeMem.setText("Free Memory: "+Runtime.getRuntime().freeMemory()/1048576+"MB");
			totalMem.setText("Total Memory: "+Runtime.getRuntime().totalMemory()/1048576+"MB");
			maxMem.setText("Max Memory: "+Runtime.getRuntime().maxMemory()/1048576+"MB");
			try {
				Thread.sleep(250);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}	public JLabel getFreeMem() {
		return freeMem;
	}
	public JLabel getTotalMem() {
		return totalMem;
	}
	public JLabel getMaxMem() {
		return maxMem;
	}
}