package com.ezreb.alarm;

import java.awt.AWTException;
import java.awt.Desktop;
import java.awt.Image;
import java.awt.MenuItem;
import java.awt.PopupMenu;
import java.awt.SystemTray;
import java.awt.TrayIcon;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.Calendar;

import javax.swing.ImageIcon;

import org.json.JSONArray;
import org.json.JSONObject;

public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		//testing
//		System.out.println(new File(Main.class.getClassLoader().getResource("").getFile()).getAbsolutePath());
//		System.out.println(new File(Main.class.getClassLoader().getResource("").getFile()));
//		File dir = new File(Main.class.getClassLoader().getResource("").getFile());
//		String jar = System.getProperty("java.class.path");
//		File jarFile = new File(dir.getAbsolutePath(), jar);
//		System.out.println(System.getProperty("java.class.path"));
//		System.out.println(dir);
//		System.out.println(dir.getAbsolutePath());
//		System.out.println(jar);
//		System.out.println(jarFile.getAbsolutePath());
//		Version.getVersion();
		System.out.println(new File(System.getProperty("os.arch")).getAbsolutePath());
		Thread t = new Thread(new Runnable() {
			
			@Override
			public void run() {
				System.out.println("Checking for updates...");
				if(Version.checkVersion() == false) {
					System.out.println("Update availiable.");
					NewVersion nv = new NewVersion(Version.getVersion().toString());
					new Thread(nv).start();
				} else {
					System.out.println("EzrebAlarm 2 is up to date");
				}
			}
		});
		t.start();
		if(alarmData.exists() == false) {
			alarmData.mkdirs();
		}
		if(alarmJSON.exists() == false) {
			NewFile dialog = new NewFile();
			dialog.setVisible(true);
			dialog.waitForButton();
		}
		JSONObject j = new JSONObject();
		j.put("toRun", "C:\\Users\\bram.zerbe\\Desktop\\Programming\\EzrebAlarm2\\amd64");
		System.out.println(j.toString());
		System.out.println(Calendar.getInstance().get(Calendar.HOUR_OF_DAY));
		System.out.println(Time.getTime().isNow());
		Time current = Time.getTime();
		Time future = new Time(current.hour, current.minute, current.second+5);
		System.out.println(current.toString());
		System.out.println(future.toString());
		if(args.length > 0) {
			JSONObject alarms = new JSONObject(args[0]);
			JSONArray alarms2 = alarms.getJSONArray("Alarms");
			Alarm[] alarms3 = new Alarm[alarms2.length()];
			for (int i = 0; i < alarms2.length(); i++) {
				alarms3[i] = new Alarm(alarms2.getJSONObject(i));
			}
			Main.alarmsAll = alarms3;
		} else if(args.length == 0) {
			File alarms = alarmJSON;
			if(alarms.exists() == false) {
				try {
					alarms.createNewFile();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			Alarm[] alarms3 = AlarmFile.getAlarms();
			Main.alarmsAll = alarms3;
		}
		Thread trayThread = new Thread(new Runnable() {
			
			@Override
			public void run() {
				Main.tray();
				
			}
		});
		trayThread.start();

	}
	public static File alarmData = new File(System.getProperty("user.home")+"\\AppData\\Roaming\\Ezreb\\EzrebAlarm");
	public static File alarmJSON = new File(alarmData, "Alarms.json");
	public static Alarm[] alarmsAll;
	public static NewAlarm newAlarmDia;
	public static RemoveAlarm oldAlarmDia;
	public static About ab;
	public static StatsForKnurds stats;
	private static void tray() {
		if (!SystemTray.isSupported()) {
            System.out.println("SystemTray is not supported");
            return;
        }
        final PopupMenu popup = new PopupMenu();
        final TrayIcon trayIcon =
                new TrayIcon(createImage("clock.png", "tray icon"));
        final SystemTray tray = SystemTray.getSystemTray();
       
        // Create a pop-up menu components
        MenuItem createNew = new MenuItem("New Alarm");
        createNew.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				newAlarmDia = new NewAlarm();
				new Thread(newAlarmDia).start();
			}
		});
        MenuItem removeOld = new MenuItem("Remove Alarm");
        removeOld.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				oldAlarmDia = new RemoveAlarm();
				new Thread(oldAlarmDia).start();
			}
		});
        MenuItem exitItem = new MenuItem("Exit");
        exitItem.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});
        MenuItem close = new MenuItem("About EzrebAlarm");
        close.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				ab = new About();
				new Thread(ab).start();
				//System.out.println("Wut");
			}
		});
        MenuItem source = new MenuItem("Source on Github");
        source.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				URI git;
				try {
					git = new URI("https://github.com/MrabEzreb/EzrebAlarm2");
					Desktop.getDesktop().browse(git);
				} catch (URISyntaxException | IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
			}
		});
        MenuItem knurds = new MenuItem("Stats for Nerds");
        knurds.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				stats = new StatsForKnurds();
				Thread stats2 = new Thread(stats);
				stats2.start();
			}
		});
       
        //Add components to pop-up menu
        popup.add(createNew);
        popup.add(removeOld);
        popup.addSeparator();
        popup.add(close);
        popup.add(knurds);
        popup.add(source);
        popup.addSeparator();
        popup.add(exitItem);
      
        trayIcon.setPopupMenu(popup);
        trayIcon.setImageAutoSize(true);
        trayIcon.setToolTip("EzrebAlarm");
        trayIcon.addMouseListener(new MouseAdapter() {
        	@Override
        	public void mouseClicked(MouseEvent e) {
        		// TODO Auto-generated method stub
        		URI idiot;
        		if(e.getButton()==MouseEvent.BUTTON1) {
					try {
						idiot = new URI("http://youareanidiot.org");
						Desktop d = Desktop.getDesktop();
						d.browse(idiot);
					} catch (URISyntaxException | IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
        		}
        	}
		});
               
        try {
            tray.add(trayIcon);
        } catch (AWTException e) {
            System.out.println("TrayIcon could not be added.");
        }
	}
	private static Image createImage(String path, String description) {
	        URL imageURL = Main.class.getResource(path);
	         
	        if (imageURL == null) {
	            System.err.println("Resource not found: " + path);
	            return null;
	        } else {
	            return (new ImageIcon(imageURL, description)).getImage();
	        }
	    }

}
