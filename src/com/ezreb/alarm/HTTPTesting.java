package com.ezreb.alarm;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONException;
import org.kohsuke.github.GHAsset;
import org.kohsuke.github.GHRelease;
import org.kohsuke.github.GHRepository;
import org.kohsuke.github.GitHub;
import org.kohsuke.github.GitHubBuilder;
import org.kohsuke.github.PagedIterable;

public class HTTPTesting {

	public static void main(String[] args) {
		try {
			//System.out.println(new JSONArray(new HTTPTesting().sendGet()).toString(4));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//System.out.println(new HTTPTesting().getRecent());
		try {
			GitHub gh = GitHub.connectAnonymously();
			GHRepository ghr = gh.getRepository("MrabEzreb/EzrebAlarm2");
			PagedIterable<GHRelease> ghrs = ghr.listReleases();
			for (GHRelease ghRelease : ghrs) {
				System.out.println(ghRelease.getTagName());
				if(ghRelease.getTagName().equals(Version.getVersion().toString())) {
					System.out.println(ghRelease.getUploadUrl());
					System.out.println(ghRelease.getAssetsUrl());
					System.out.println(ghRelease.getHtmlUrl());
					System.out.println(ghRelease.getUrl());
					List<GHAsset> ghas = ghRelease.getAssets();
					for (GHAsset ghAsset : ghas) {
						System.out.println(ghAsset.getUrl());
						System.out.println(ghAsset.getBrowserDownloadUrl());
					}
					break;
				}
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private String sendGet() throws Exception {
		 
		String url = "https://api.github.com/repos/MrabEzreb/EzrebAlarm2/releases/assets/398472";
		System.out.println(url);
 
		URL obj = new URL(url);
		HttpURLConnection con = (HttpURLConnection) obj.openConnection();
		System.out.println("Opened Connection");
 
		// optional default is GET
		con.setRequestMethod("GET");
		System.out.println("Set GET");
 
		//add request header
		con.setRequestProperty("User-Agent", "Mozilla/5.0");
 
		System.out.println("Getting response code");
		int responseCode = con.getResponseCode();
		System.out.println("Got response code");
		System.out.println("\nSending 'GET' request to URL : " + url);
		System.out.println("Response Code : " + responseCode);
 
		BufferedReader in = new BufferedReader(
		        new InputStreamReader(con.getInputStream()));
		String inputLine;
		StringBuffer response = new StringBuffer();
 
		while ((inputLine = in.readLine()) != null) {
			response.append(inputLine);
		}
		in.close();
 
		//print result
		return response.toString();
 
	}
	private String getRecent() {
		JSONArray get = new JSONArray();
		try {
			get = new JSONArray(new HTTPTesting().sendGet());
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		String version = Version.getVersion().toString();
		int downloadable = 0;
		for (int i = 0; i < get.length(); i++) {
			if(get.getJSONObject(i).getString("tag_name").equals(version)) {
				downloadable = (int) get.getJSONObject(i).getJSONArray("assets").getJSONObject(0).get("id");
				break;
			}
		}
		return "https://api.github.com/repos/MrabEzreb/EzrebAlarm2/releases/893546"+"/assets/"+downloadable;
	}
}
