package com.ezreb.alarm;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URL;
import java.net.URLConnection;

public class Downloader {

	public Downloader(URL file, File out) {
		this.file = file;
		this.out = out;
	}
	URL file;
	File out;
	public File download() {
		InputStream ins = null;
		OutputStream outs = null;
		try {
			this.out.createNewFile();
			outs = new FileOutputStream(this.out);
			System.out.println(1);
			URLConnection c = this.file.openConnection();
			System.out.println(2);
			System.out.println(c.getURL());
			System.out.println(c.getReadTimeout());
			ins = c.getInputStream();
			System.out.println(3);
			byte[] bytes = new byte[1024];
			System.out.println(ins.available());
			while(ins.available() > 0) {
				System.out.println(00);
				ins.read(bytes);
				outs.write(bytes);
				bytes = new byte[1024];
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				ins.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			try {
				outs.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		System.out.println("pancakes");
		return this.out;
		
	}
}
