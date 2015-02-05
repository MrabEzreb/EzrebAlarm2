package com.ezreb.alarm;

import java.io.File;

public interface SelectionListener {

	public void onSelection(File f);
	public void onSelection(Alarm a, AlarmEntry a2);
}
