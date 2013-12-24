package io.leocad.blankpage;

import android.app.Activity;
import android.app.backup.BackupManager;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;

public class BlankPageActivity extends Activity {
	
	static final String PREFS_NAME = "qkxbjwmxbqmwbkxqmbxqwjmkbxqmbk";
	private static final String KEY_TEXT = "oehuboetudoteuioetuxotuxkt";
	
	private EditText mEditText;
	private String mLastSavedText;

	@Override
	protected void onCreate(Bundle savedInstanceState) {

		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_blank_page);
		
		mEditText = (EditText) findViewById(R.id.et);
	}
	
	@Override
	protected void onResume() {
		super.onResume();
		
		final String text = getSharedPreferences(PREFS_NAME, MODE_PRIVATE).getString(KEY_TEXT, "");
		mEditText.setText(text);
		mLastSavedText = text;
	}
	
	@Override
	protected void onPause() {
	
		final String text = mEditText.getText().toString(); // Will never be null
		
		if (!text.equals(mLastSavedText)) {
			
			getSharedPreferences(PREFS_NAME, MODE_PRIVATE)
			.edit()
			.putString(KEY_TEXT, text)
			.commit();
			
			new BackupManager(this).dataChanged();
			
			mLastSavedText = text;
		}
		
		super.onPause();
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {

		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.activity_blank_page, menu);
		return true;
	}
	
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		
		final int id = item.getItemId();
		if (id == R.id.menu_clear) {
			mEditText.setText("");
			return true;
		}
		
		if (id == R.id.menu_share) {
			String text = mEditText.getText().toString();
			
			Intent intent = new Intent(Intent.ACTION_SEND);
			intent.setType("text/plain");
			intent.putExtra(Intent.EXTRA_TEXT, text);
			startActivity(Intent.createChooser(intent, "Share via"));
			
			return true;
		}
		
		return super.onOptionsItemSelected(item);
	}

}
