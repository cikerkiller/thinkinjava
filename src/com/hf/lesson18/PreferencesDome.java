package com.hf.lesson18;

import java.util.prefs.BackingStoreException;
import java.util.prefs.Preferences;

public class PreferencesDome {
	public static void main(String[] args) throws BackingStoreException {
		Preferences preferences = Preferences.userNodeForPackage(PreferencesDome.class);
		preferences.put("Location", "Oz");
		preferences.put("hf", "ei");
		preferences.putInt("companions", 4);
		preferences.putBoolean("are there where?", true);
		int usageCount = preferences.getInt("usageCount", 0);
		usageCount++;
		preferences.putInt("usageCount", usageCount);
		for(String key : preferences.keys()) {
			System.out.println(key+" "+preferences.get(key, null));
		}
		System.out.println("com:"+preferences.getInt("companions", 0));
				
	}
}
