package io.leocad.blankpage;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;

public class BlankPageActivity extends Activity {
	
	private static final String KEY_TEXT = "oehuboetudoteuioetuxotuxkt";
	
	private EditText mEditText;

	@Override
	protected void onCreate(Bundle savedInstanceState) {

		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_blank_page);
		
		mEditText = (EditText) findViewById(R.id.et);		
	}
	
	@Override
	protected void onResume() {
		super.onResume();
		
		final String text = getPreferences(MODE_PRIVATE).getString(KEY_TEXT, null);
		mEditText.setText(text);
	}
	
	@Override
	protected void onPause() {
	
		final String text = mEditText.getText().toString();
		
		getPreferences(MODE_PRIVATE)
		.edit()
		.putString(KEY_TEXT, text)
		.commit();
		
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
