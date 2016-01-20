package com.irtrans_demo;

import android.os.Bundle;
import android.preference.PreferenceActivity;


public class IRTransPreferences extends PreferenceActivity {
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		addPreferencesFromResource(R.xml.preferences);
	}
}
