package ear.trainer;

import android.app.Activity;
import android.content.Context;
import android.graphics.Typeface;
import android.media.AudioManager;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class StartController extends Activity {
	private AudioManager audio;

	protected void onStart() {
		super.onStart();
		Log.d("LIFECYCLE", "Start onStart()");
	}

	protected void onRestart() {
		super.onRestart();
		Log.d("LIFECYCLE", "Start onRestart()");
	}

	protected void onResume() {
		super.onResume();
		Log.d("LIFECYCLE", "Start onResume()");
	}

	protected void onPause() {
		super.onPause();
		Log.d("LIFECYCLE", "Start onPause()");
	}

	protected void onStop() {
		super.onStop();
		Log.d("LIFECYCLE", "Start onStop()");
	}

	protected void onDestroy() {
		super.onDestroy();
		Log.d("LIFECYCLE", "Start onDestroy()");
	}

	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.start);
		Log.d("LIFECYCLE", "onCreate()");
		audio = (AudioManager) getSystemService(Context.AUDIO_SERVICE);

		// ===========================Fonts
		Typeface font = Typeface.createFromAsset(getAssets(), "Fonts/dum1.ttf");

		TextView txt1 = (TextView) findViewById(R.id.textView1);
		txt1.setTypeface(font);

		TextView txt2 = (TextView) findViewById(R.id.textView2);
		txt2.setTypeface(font);
		TextView txt3 = (TextView) findViewById(R.id.textView3);
		txt3.setTypeface(font);

		Button sRQ = (Button) findViewById(R.id.rquiz);
		sRQ.setTypeface(font);
		Button sLQ = (Button) findViewById(R.id.lquiz);
		sLQ.setTypeface(font);
		Button sP = (Button) findViewById(R.id.practice);
		sP.setTypeface(font);
		Button sO = (Button) findViewById(R.id.options);
		sO.setTypeface(font);
		Button sA = (Button) findViewById(R.id.about);
		sA.setTypeface(font);
		// ==========================End Fonts

	}

	public void buttonPressed(View v) {

		String button = (String) ((Button) v).getText();

		if (button.equals("Reading Quiz")) {
			utilities.goToRQuiz(this);
		}
		if (button.equals("Practice")) {
			utilities.goToListen(this);
		}
		if (button.equals("Listening Quiz")) {
			utilities.goToLQuiz(this);
		}
		if (button.equals("About")) {
			utilities.goToAbout(this);
		}
		if (button.equals("Options")) {
			utilities.goToOptions(this);
		}

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
