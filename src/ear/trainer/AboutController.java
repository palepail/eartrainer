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

public class AboutController extends Activity {
	private AudioManager audio;

	protected void onStart() {
		super.onStart();
		Log.d("LIFECYCLE", "About onStart()");
	}

	protected void onRestart() {
		super.onRestart();
		Log.d("LIFECYCLE", "About onRestart()");
	}

	protected void onResume() {
		super.onResume();
		Log.d("LIFECYCLE", "About onResume()");
	}

	protected void onPause() {
		super.onPause();
		Log.d("LIFECYCLE", "About onPause()");
	}

	protected void onStop() {
		super.onStop();
		Log.d("LIFECYCLE", "About onStop()");
	}

	protected void onDestroy() {
		super.onDestroy();
		Log.d("LIFECYCLE", "About onDestroy()");
	}

	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.about);
		Log.d("LIFECYCLE", "onCreate()");
		audio = (AudioManager) getSystemService(Context.AUDIO_SERVICE);

		// ===================Fonts
		Typeface font = Typeface.createFromAsset(getAssets(), "Fonts/dum1.ttf");
		TextView txt1 = (TextView) findViewById(R.id.textView1);
		txt1.setTypeface(font);

		Button aboutET = (Button) findViewById(R.id.aboutEarTrainer);
		aboutET.setTypeface(font);
		Button aboutLQ = (Button) findViewById(R.id.aboutLQuiz);
		aboutLQ.setTypeface(font);
		Button aboutP = (Button) findViewById(R.id.aboutPractice);
		aboutP.setTypeface(font);
		Button aboutRQ = (Button) findViewById(R.id.aboutRQuiz);
		aboutRQ.setTypeface(font);
		Button back = (Button) findViewById(R.id.back);
		back.setTypeface(font);

		// ==================End Fonts
	}

	public void buttonPressed(View v) {

		String button = (String) ((Button) v).getText();

		if (button.equals("Back")) {
			finish();
		}
		if (button.equals("Ear Trainer")) {
			utilities.aboutEarTrainer(this);
		}
		if (button.equals("Practice")) {
			utilities.aboutPractice(this);
		}
		if (button.equals("Reading Quiz")) {
			utilities.aboutRQuiz(this);
		}
		if (button.equals("Listening Quiz")) {
			utilities.aboutLQuiz(this);
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

		utilities.hub("options", item, this);

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
