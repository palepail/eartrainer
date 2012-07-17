package ear.trainer;

import android.app.Activity;
import android.content.Context;
import android.media.AudioManager;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;


public class ListenController extends Activity {
	private AudioManager audio;

	protected void onStart() {
		super.onStart();
		Log.d("LIFECYCLE", "onStart()");
	}

	protected void onRestart() {
		super.onRestart();
		Log.d("LIFECYCLE", "onRestart()");
	}

	protected void onResume() {
		super.onResume();
		Log.d("LIFECYCLE", "onResume()");
	}

	protected void onPause() {
		super.onPause();
		Log.d("LIFECYCLE", "onPause()");
	}

	protected void onStop() {
		super.onStop();
		Log.d("LIFECYCLE", "onStop()");
	}

	protected void onDestroy() {
		super.onDestroy();
		Log.d("LIFECYCLE", "onDestroy()");
	}

	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.listen);
		Log.d("LIFECYCLE", "onCreate()");
		audio = (AudioManager) getSystemService(Context.AUDIO_SERVICE);

		// TextView nameDisplay = (TextView) findViewById(R.id.textView3);
		// //Name Display
		// nameDisplay.setText(utilities.getName());

		/*
		//  AdSpace
		// Create the adView
		AdView adView = new AdView(this, AdSize.BANNER, "a14e097ecc8d3d9");
		// Lookup your LinearLayout assuming it’s been given
		// the attribute android:id="@+id/mainLayout"
		LinearLayout layout = (LinearLayout) findViewById(R.id.linearLayout1);
		// Add the adView to it
		layout.addView(adView);
		adView.setGravity(Gravity.CENTER_HORIZONTAL | Gravity.BOTTOM);
		// Initiate a generic request to load it with an ad
		adView.loadAd(new AdRequest());
        */
		if (OptionController.getClef().equals("Bass")) {
			ImageView bar = (ImageView) findViewById(R.id.imageView1);
			bar.setImageResource(R.drawable.bscale);
		}
		if (OptionController.getClef().equals("Treble")) {
			ImageView bar = (ImageView) findViewById(R.id.imageView1);
			bar.setImageResource(R.drawable.scale);
		}

	}

	public void playNote(View v) throws Exception {
		int noteNum = (int) Integer.parseInt((String) ((Button) v).getText());

		{
			Toast.makeText(this, utilities.getNoteLet(noteNum), Toast.LENGTH_SHORT)
			.show();
		}
			
		
		Instrument player = new Instrument();
		
		player.playNote(this, noteNum);

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		MenuInflater inflater = getMenuInflater();
		inflater.inflate(R.menu.trainer_menu, menu);
		return true;
	}

	public boolean onOptionsItemSelected(MenuItem item) {
		super.onOptionsItemSelected(item);

		utilities.hub("practice", item, this);

		return false;
	}

	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		switch (keyCode) {
		case KeyEvent.KEYCODE_VOLUME_UP: {
			audio.adjustStreamVolume(AudioManager.STREAM_MUSIC,
					AudioManager.ADJUST_RAISE, AudioManager.FLAG_SHOW_UI);
			return true;
		}
		case KeyEvent.KEYCODE_VOLUME_DOWN: {
			audio.adjustStreamVolume(AudioManager.STREAM_MUSIC,
					AudioManager.ADJUST_LOWER, AudioManager.FLAG_SHOW_UI);
			return true;
		}
		case KeyEvent.KEYCODE_BACK: {
			finish();
			return true;
		}
		default: {
			return false;
		}
		}
	}

}
