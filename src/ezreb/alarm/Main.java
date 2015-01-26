package ezreb.alarm;

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
import java.io.BufferedReader;
import java.io.File;
import java.io.FilenameFilter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.JarURLConnection;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.net.URLDecoder;
import java.nio.file.FileSystem;
import java.nio.file.Path;
import java.security.AccessController;
import java.security.CodeSource;
import java.security.PrivilegedAction;
import java.util.Calendar;
import java.util.jar.JarFile;

import javax.swing.ImageIcon;

import org.json.JSONArray;
import org.json.JSONObject;
import org.json.XML;

public class Main {

	@SuppressWarnings("unchecked")
	public static void main(String[] args) throws IOException, URISyntaxException {
		// TODO Auto-generated method stub
		//testing
		Version.getVersion();
		System.out.println(Main.class.getResource(""));
		System.out.println(Main.class.getResource("/").getPath());
		System.out.println(Main.class.getResource("/").getFile());
		System.out.println(Main.class.getResource("/").toExternalForm());
		System.out.println(Main.class.getResource("/").toString());
		System.out.println(Main.class.getResource("").getFile());
		System.out.println(Main.class.getProtectionDomain().getCodeSource().getLocation().getPath());
		System.out.println(Main.class.getProtectionDomain().getCodeSource().getLocation().getFile());
		System.out.println(Main.class.getProtectionDomain().getCodeSource().getLocation().getFile());
		System.out.println(new File(Main.class.getClassLoader().getResource("").getFile()).getAbsolutePath());
		System.out.println(ClassLoader.getSystemClassLoader().getResource(".").getPath());
		System.out.println(ClassLoader.getSystemClassLoader().getResource(".").getFile());
		String path = Main.class.getProtectionDomain().getCodeSource().getLocation().getPath();
		String decodedPath = URLDecoder.decode(path, "UTF-8");
		System.out.println(new File(decodedPath));
		System.out.println(URLDecoder.decode(ClassLoader.getSystemClassLoader().getResource(".").getFile(), "UTF-8"));
		URL url = Main.class.getResource(Main.class.getSimpleName()+".class");
		System.out.println(url.toString());
		System.out.println(url.toURI().normalize());
		System.out.println(new File(url.toURI().toString()));
		System.out.println(new File(url.toURI().toString()).getParentFile());
		System.out.println(new File(url.toURI().toString()).getParentFile().getParentFile());
		System.out.println(new File(url.toURI().toString()).getParentFile().getParentFile());
		System.out.println(new File(url.toURI().toString()).toURI().getPath());
		System.out.println(Main.class.getProtectionDomain().getCodeSource().getLocation());
		final String[] myLocationViaProtectionDomain = {null};
		AccessController.doPrivileged(new PrivilegedAction(){
		    public Object run(){
		        myLocationViaProtectionDomain[0] = getClass().getProtectionDomain().getCodeSource().getLocation().toString();
		        System.out.println("myLocationViaProtectionDomain: " + myLocationViaProtectionDomain[0]);
		        return null;
		    }
		});
		if(alarmData.exists() == false) {
			alarmData.mkdirs();
		}
		if(alarmJSON.exists() == false) {
			NewFile dialog = new NewFile();
			dialog.setVisible(true);
			dialog.waitForButton();
		}
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
