package ezreb.alarm;

import java.util.Calendar;

import org.json.JSONObject;

public class Time {

	public Time(int hour, int minute, int second) {
		this.hour = hour;
		this.minute = minute;
		this.second = second;
	}
	public Time(JSONObject json) {
		this.hour = json.getInt("Hour");
		this.minute = json.getInt("Minute");
		this.second = json.getInt("Second");
	}
	public int hour;
	public int minute;
	public int second;
	public boolean isNow() {
		Time current = Time.getTime();
		return this.equals(current);
	}
	public JSONObject toJSON() {
		JSONObject retVal = new JSONObject();
		retVal.put("Hour", this.hour);
		retVal.put("Minute", this.minute);
		retVal.put("Second", this.second);
		return retVal;
	}
	@Override
	public boolean equals(Object obj) {
		// TODO Auto-generated method stub
		Time oth = (Time) obj;
		boolean retVal = false;
		if(this.hour == oth.hour && this.minute == oth.minute && this.second == oth.second) {
			retVal = true;
		} else {
			retVal = false;
		}
		return retVal;
	}
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return this.hour+":"+this.minute+":"+this.second;
	}
	public static Time getTime() {
		int hour = Calendar.getInstance().get(Calendar.HOUR_OF_DAY);
		int minute = Calendar.getInstance().get(Calendar.MINUTE);
		int second = Calendar.getInstance().get(Calendar.SECOND);
		return new Time(hour, minute, second);
	}
}
