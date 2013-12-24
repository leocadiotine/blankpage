package io.leocad.blankpage;

import android.app.backup.BackupAgentHelper;
import android.app.backup.SharedPreferencesBackupHelper;


public class MyBackupAgent extends BackupAgentHelper {

	static final String PREFS_BACKUP_KEY = "gpyfcgprylfgprycfgplrfcgr";
	
	@Override
	public void onCreate() {
		super.onCreate();
		
		SharedPreferencesBackupHelper helper = new SharedPreferencesBackupHelper(this, BlankPageActivity.PREFS_NAME);
        addHelper(PREFS_BACKUP_KEY, helper);
	}
	
}
