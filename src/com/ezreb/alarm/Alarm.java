package com.ezreb.alarm;

import java.awt.Desktop;
import java.io.File;
import java.io.IOException;

import org.json.JSONObject;

public class Alarm implements Runnable {

	public Alarm(String name, Time time, AlarmAction action, File run) {
		this.name = name;
		this.time = time;
		this.action = action;
		this.toRun = run;
		this.runner.start();
	}
	public Alarm(JSONObject json) {
		this.name = json.getString("Name");
		this.time = new Time(json.getJSONObject("Time"));
		this.action = new AlarmPopup();
		this.toRun = new File((String) json.get("toRun"));
		this.runner.start();
	}
	public String name = "Unnamed Alarm";
	public Time time;
	public boolean isActive = true;
	public AlarmAction action;
	public Thread runner = new Thread(this);
	public File toRun;
	public JSONObject toJSON() {
		JSONObject retVal = new JSONObject();
		retVal.put("Name", this.name);
		retVal.put("Time", this.time.toJSON());
		retVal.put("toRun", this.toRun.getAbsolutePath());
		return retVal;
	}
	@Override
	public void run() {
		System.out.println("Thread started");
		while(isActive) {
			if(this.time.isNow()) {
				this.action.onAlarm(this);
				this.isActive = false;
				if(this.toRun.getAbsolutePath().equals(new File(System.getProperty("os.arch")).getAbsolutePath())) {
					
				} else {
					try {
						Desktop.getDesktop().open(this.toRun);
					} catch (IOException e) {
						System.out.println("couldn't run file");
					}
				}
				System.out.println("Thread Finished");
			} else {
				try {
					Thread.sleep(10);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
	}
}
