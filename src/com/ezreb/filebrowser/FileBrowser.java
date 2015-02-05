package com.ezreb.filebrowser;

import java.awt.Desktop;
import java.awt.GraphicsEnvironment;
import java.io.File;
import java.io.IOException;

import javax.swing.WindowConstants;

import com.ezreb.alarm.Alarm;
import com.ezreb.alarm.AlarmEntry;
import com.ezreb.alarm.NewAlarm;
import com.ezreb.alarm.SelectionListener;
import com.ezreb.filebrowser.images.ImageLoader;

public class FileBrowser {

	public static void main(String[] args) {
		FileBrowser fb = new FileBrowser();
		try {
			File output = fb.run();
			System.out.println("output: "+output.getAbsolutePath());
			Desktop.getDesktop().open(output);
		} catch(NullPointerException | IOException npe) {
			
		}
	}
	public FileBrowser() {
	}
	public File run() {
		ImageLoader.loadImages();
		System.out.println("loaded images");
		MainWindow mw = new MainWindow();
		mw.setVisible(true);
		mw.requestFocus();
		int x = GraphicsEnvironment.getLocalGraphicsEnvironment().getCenterPoint().x;
		int y = GraphicsEnvironment.getLocalGraphicsEnvironment().getCenterPoint().y;
		mw.setLocation(x-225, y-150);
		System.out.println("set visible");
		mw.setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE);
		System.out.println("set close");
		try {
			Thread.sleep(10000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		File retVal = mw.getSelectedFile();
		return retVal;
	}
	public MainWindow run2() {
		ImageLoader.loadImages();
		System.out.println("loaded images");
		MainWindow mw = new MainWindow();
		mw.setVisible(true);
		mw.requestFocus();
		int x = GraphicsEnvironment.getLocalGraphicsEnvironment().getCenterPoint().x;
		int y = GraphicsEnvironment.getLocalGraphicsEnvironment().getCenterPoint().y;
		mw.setLocation(x-225, y-150);
		System.out.println("set visible");
		mw.setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE);
		System.out.println("set close");
		return mw;
	}
	public void run3(NewAlarm na) {
		MainWindow mw = run2();
		this.na = na;
		mw.addSelectionListener(new SelectionListener() {
			
			@Override
			public void onSelection(File f) {
				FileBrowser.this.f = f;
				FileBrowser.this.na.getFile(f);
			}
			@Override
			public void onSelection(Alarm a, AlarmEntry a2) {
				// TODO Auto-generated method stub
				
			}
		});
	}
	@SuppressWarnings("unused")
	private File f;
	private NewAlarm na;

}
