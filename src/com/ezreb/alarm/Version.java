package com.ezreb.alarm;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

public class Version {

	public Version(int edition, int version, int patch) {
		this.edition = edition;
		this.version = version;
		this.patch = patch;
	}
	public Version(String fromString) {
		String[] s = new String[fromString.length()];
		for (int i = 0; i < s.length; i++) {
			s[i] = new String(new char[] {fromString.charAt(i)});
		}
		int eDot = 0;
		int vDot = 0;
		String eS = "";
		String vS = "";
		String pS = "";
		for (int i = 0; i < s.length; i++) {
			if(i == s.length-1) {
				for(int p = vDot+1; p < s.length; p++) {
					pS = pS+s[p];
				}
			} else if(s[i].equals(".")) {
				if(eDot == 0) {
					eDot = i;
					for(int e = 0; e < eDot; e++) {
						eS = eS+s[e];
					}
				} else if(vDot == 0) {
					vDot = i;
					for(int v = eDot+1; v < vDot; v++) {
						vS = vS+s[v];
					}
				}
			}
		}
		this.edition = new Integer(eS);
		this.version = new Integer(vS);
		this.patch = new Integer(pS);
	}
	public static Version getVersion() {
		Version v = null;
		URL versionData;
		try {
			versionData = new URL("https://raw.githubusercontent.com/MrabEzreb/VersionChecks/master/EzrebAlarm2");
			URLConnection urlc = versionData.openConnection();
			InputStream srcRaw = urlc.getInputStream();
			InputStreamReader srcRR = new InputStreamReader(srcRaw);
			BufferedReader srcBuf = new BufferedReader(srcRR);
			String ver = srcBuf.readLine();
			v = new Version(ver);
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return v;
	}
	public static boolean checkVersion() {
		Version web = Version.getVersion();
		System.out.println("Current version is "+current.toString());
		System.out.println("Remote version is "+web.toString());
		if(Version.current.equals(web)) {
			return true;
		} else {
			return false;
		}
	}
	public static Version current = new Version(2, 5, 3);
	public int edition;
	public int version;
	public int patch;
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		String retVal = "v"+this.edition+"."+this.version+"."+this.patch;
		return retVal;
	}
	@Override
	public boolean equals(Object obj) {
		// TODO Auto-generated method stub
		boolean equal = true;
		if(obj instanceof Version == false) {
			return false;
		}
		Version v = (Version) obj;
		if(this.edition != v.edition) {
			equal = false;
		}
		if(this.version != v.version) {
			equal = false;
		}
		if(this.patch != v.patch) {
			equal = false;
		}
		return equal;
	}
}
