package ezreb.alarm;

import org.json.JSONObject;

public class Alarm implements Runnable {

	public Alarm(String name, Time time, AlarmAction action) {
		this.name = name;
		this.time = time;
		this.action = action;
		this.runner.start();
	}
	public Alarm(JSONObject json) {
		this.name = json.getString("Name");
		this.time = new Time(json.getJSONObject("Time"));
		this.action = new AlarmPopup();
		this.runner.start();
	}
	public String name = "Unnamed Alarm";
	public Time time;
	public boolean isActive = true;
	public AlarmAction action;
	public Thread runner = new Thread(this);
	public JSONObject toJSON() {
		JSONObject retVal = new JSONObject();
		retVal.put("Name", this.name);
		retVal.put("Time", this.time.toJSON());
		return retVal;
	}
	@Override
	public void run() {
		System.out.println("Thread started");
		while(isActive) {
			if(this.time.isNow()) {
				this.action.onAlarm(this);
				this.isActive = false;
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
