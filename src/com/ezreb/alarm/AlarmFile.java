package com.ezreb.alarm;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Calendar;

import org.json.JSONArray;
import org.json.JSONObject;

public class AlarmFile {

	public static Alarm[] getAlarms() {
		File alarms = Main.alarmJSON;
		if(alarms.exists() == false) {
			try {
				alarms.createNewFile();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		try {
			FileReader f = new FileReader(alarms);
			BufferedReader b = new BufferedReader(f);
			JSONObject alarmsJSON = new JSONObject(b.readLine());
			f.close();
			b.close();
			int dayint = Calendar.getInstance().get(Calendar.DAY_OF_WEEK);
			String day = "";
			if(dayint == Calendar.SUNDAY) {
				day = "Sunday";
			} else if(dayint == Calendar.MONDAY) {
				day = "Monday";
			} else if(dayint == Calendar.TUESDAY) {
				day = "Tuesday";
			} else if(dayint == Calendar.WEDNESDAY) {
				day = "Wednesday";
			} else if(dayint == Calendar.THURSDAY) {
				day = "Thursday";
			} else if(dayint == Calendar.FRIDAY) {
				day = "Friday";
			} else if(dayint == Calendar.SATURDAY) {
				day = "Saturday";
			}
			JSONArray alarmsArray = alarmsJSON.getJSONArray(day);
			Alarm[] alarms3 = new Alarm[alarmsArray.length()];
			for (int i = 0; i < alarmsArray.length(); i++) {
				System.out.println(alarmsArray.getJSONObject(i));
				alarms3[i] = new Alarm(alarmsArray.getJSONObject(i));
			}
			System.out.println("loaded "+alarmsArray.length()+" alarms");
			return alarms3;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
		
	}
	public static void saveAlarms(Alarm[] alarms) {
		JSONArray todaysAlarms = new JSONArray();
		for (Alarm alarm : alarms) {
			todaysAlarms.put(alarm.toJSON());
		}
		try {
			FileReader f = new FileReader(Main.alarmJSON);
			BufferedReader b = new BufferedReader(f);
			JSONObject alarmsJSON = new JSONObject(b.readLine());
			f.close();
			b.close();
			FileWriter f2 = new FileWriter(Main.alarmJSON);
			BufferedWriter b2 = new BufferedWriter(f2);
			int dayint = Calendar.getInstance().get(Calendar.DAY_OF_WEEK);
			String day = "";
			if(dayint == Calendar.SUNDAY) {
				day = "Sunday";
			} else if(dayint == Calendar.MONDAY) {
				day = "Monday";
			} else if(dayint == Calendar.TUESDAY) {
				day = "Tuesday";
			} else if(dayint == Calendar.WEDNESDAY) {
				day = "Wednesday";
			} else if(dayint == Calendar.THURSDAY) {
				day = "Thursday";
			} else if(dayint == Calendar.FRIDAY) {
				day = "Friday";
			} else if(dayint == Calendar.SATURDAY) {
				day = "Saturday";
			}
			alarmsJSON.remove(day);
			alarmsJSON.put(day, todaysAlarms);
			Main.alarmJSON.delete();
			Main.alarmJSON.createNewFile();
			System.out.println(alarmsJSON.toString());
			b2.write(alarmsJSON.toString());
			b2.flush();
			f2.close();
			f2.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
}
