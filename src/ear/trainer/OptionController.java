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
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.TextView;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.Spinner;

public class OptionController extends Activity {
	private AudioManager audio;


	private static OptionController mInstance = null;
	private static int timer = 7 * 1000;
	private static boolean timerMode = false;
	private static boolean winSoundMode = true;
	private static String instrument = "Piano";
	private static String clef = "Treble";
	private static int numNotes = 1;

	protected void onStart() {
		super.onStart();
		Log.d("LIFECYCLE", "Options onStart()");
	}

	protected void onRestart() {
		super.onRestart();
		Log.d("LIFECYCLE", "Options onRestart()");
	}

	protected void onResume() {
		super.onResume();
		Log.d("LIFECYCLE", "Options onResume()");
		
		CheckBox timerCB = (CheckBox) findViewById(R.id.checkBox1);
		CheckBox winCB = (CheckBox) findViewById(R.id.checkBox2);
		if (timerMode && !timerCB.isChecked()) {
			timerCB.setChecked(true);
			timerMode=true;
		}
		else if(!timerMode && timerCB.isChecked() )
		{
			timerCB.setChecked(false);
			timerMode=false;
		}
		
		if (!winSoundMode && winCB.isChecked()) {
			winCB.setChecked(false);
			winSoundMode=false;
		}
		else if (winSoundMode && !winCB.isChecked()) {
			winCB.setChecked(true);
			winSoundMode=true;
		}
		
		Spinner notesSpinner = (Spinner) findViewById(R.id.spinner4);
		if (numNotes == 1) {
			notesSpinner.setSelection(0);
		} else if (numNotes == 4) {
			notesSpinner.setSelection(1);
		}

		if (timerMode == true) {
			Spinner timerSpinner = (Spinner) findViewById(R.id.spinner2);
			if (timer == 2 * 1000) {
				timerSpinner.setSelection(0);
			}
			if (timer == 5 * 1000) {
				timerSpinner.setSelection(1);
			}
			if (timer == 7 * 1000) {
				timerSpinner.setSelection(2);
			}
			if (timer == 10 * 1000) {
				timerSpinner.setSelection(3);
			}
			if (timer == 15 * 1000) {
				timerSpinner.setSelection(4);
			}
		}

		Spinner instSpinner = (Spinner) findViewById(R.id.spinner1);
		Spinner clefSpinner = (Spinner) findViewById(R.id.spinner3);
		if (clef.equals("Treble")) {
			clefSpinner.setSelection(0);
			if (instrument.equals("Piano")) {
				instSpinner.setSelection(0);
			} else if (instrument.equals("Violin")) {
				instSpinner.setSelection(1);
			} else if (instrument.equals("Guitar")) {
				instSpinner.setSelection(2);
			} else if (instrument.equals("Trumpet")) {
				instSpinner.setSelection(3);
			}
		}
		if (clef.equals("Bass")) {
			clefSpinner.setSelection(1);
			if (instrument.equals("Piano")) {
				instSpinner.setSelection(0);
			} else if (instrument.equals("Bass")) {
				instSpinner.setSelection(1);
			}
		}

	}

	protected void onPause() {
		super.onPause();
		Log.d("LIFECYCLE", "Options onPause()");
	}

	protected void onStop() {
		super.onStop();
		Log.d("LIFECYCLE", "Options onStop()");
	}

	protected void onDestroy() {
		super.onDestroy();
		Log.d("LIFECYCLE", "Options onDestroy()");
	}

	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.options);
		Log.d("LIFECYCLE", "onCreate()");
		audio = (AudioManager) getSystemService(Context.AUDIO_SERVICE);

		// ==============Timed Mode Check Box
		CheckBox cb = (CheckBox) findViewById(R.id.checkBox1);

		cb.setOnCheckedChangeListener(new OnCheckedChangeListener() {

			public void onCheckedChanged(CompoundButton buttonView,
					boolean isChecked) {
				// TODO Auto-generated method stub
				if (buttonView.isChecked()) {
					setTimerMode(true);
				} else {
					setTimerMode(false);
				}
			}
		});
		// ================End Check Box

		// ==============Win Sound Check Box
		CheckBox cb1 = (CheckBox) findViewById(R.id.checkBox2);

		cb1.setOnCheckedChangeListener(new OnCheckedChangeListener() {

			public void onCheckedChanged(CompoundButton buttonView,
					boolean isChecked) {
				// TODO Auto-generated method stub
				if (buttonView.isChecked()) {
					setWinSoundMode(true);
				} else {
					setWinSoundMode(false);
				}
			}

		});
		// ================End Check Box

		// =================Instrument Spinner
		Spinner instSpinner = (Spinner) findViewById(R.id.spinner1);
		ArrayAdapter<CharSequence> instAdapter = ArrayAdapter
				.createFromResource(this, R.array.instrument_array,
						android.R.layout.simple_spinner_item);
		instAdapter
				.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		instSpinner.setAdapter(instAdapter);
		instSpinner.setOnItemSelectedListener(new instSpinnerListener());

		// ==============End Instrument Spinner

		// =================Clef Spinner
		Spinner clefSpinner = (Spinner) findViewById(R.id.spinner3);
		ArrayAdapter<CharSequence> clefAdapter = ArrayAdapter
				.createFromResource(this, R.array.clef_array,
						android.R.layout.simple_spinner_item);
		clefAdapter
				.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		clefSpinner.setAdapter(clefAdapter);
		clefSpinner.setOnItemSelectedListener(new clefSpinnerListener());

		// ==============End Clef Spinner

		// =================Timer Spinner
		Spinner timerSpinner = (Spinner) findViewById(R.id.spinner2);
		ArrayAdapter<CharSequence> timerAdapter = ArrayAdapter
				.createFromResource(this, R.array.timer_array,
						android.R.layout.simple_spinner_item);
		timerAdapter
				.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		timerSpinner.setAdapter(timerAdapter);
		timerSpinner.setSelection(2);
		timerSpinner.setOnItemSelectedListener(new timerSpinnerListener());

		// ==============End Timer Spinner

		// =================Notes Spinner
		Spinner notesSpinner = (Spinner) findViewById(R.id.spinner4);
		ArrayAdapter<CharSequence> notesAdapter = ArrayAdapter
				.createFromResource(this, R.array.notes_array,
						android.R.layout.simple_spinner_item);
		notesAdapter
				.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		notesSpinner.setAdapter(notesAdapter);
		notesSpinner.setOnItemSelectedListener(new notesSpinnerListener());

		// ==============End Timer Spinner

		// ==============Fonts
		Typeface font = Typeface.createFromAsset(getAssets(), "Fonts/dum1.ttf");
		TextView txt1 = (TextView) findViewById(R.id.textView1);
		txt1.setTypeface(font);

		TextView txt2 = (TextView) findViewById(R.id.textView2);
		txt2.setTypeface(font);
		TextView txt3 = (TextView) findViewById(R.id.textView3);
		txt3.setTypeface(font);
		TextView txt4 = (TextView) findViewById(R.id.textView4);
		txt4.setTypeface(font);
		TextView txt5 = (TextView) findViewById(R.id.textView5);
		txt5.setTypeface(font);
		TextView txt6 = (TextView) findViewById(R.id.textView6);
		txt6.setTypeface(font);
		TextView txt7 = (TextView) findViewById(R.id.textView7);
		txt7.setTypeface(font);

		Button back = (Button) findViewById(R.id.back);
		back.setTypeface(font);

	}

	public static synchronized OptionController getInstance() {
		if (null == mInstance) {
			mInstance = new OptionController();
		}
		return mInstance;
	}

	public static synchronized int getSeconds() {
		// TODO Auto-generated method stub
		if (null == mInstance) {
			mInstance = new OptionController();
		}
		return timer;
	}

	public static synchronized String getInstrument() {
		if (null == mInstance) {
			mInstance = new OptionController();
		}
		return instrument;
	}

	public static synchronized boolean getTimerMode() {
		// TODO Auto-generated method stub
		if (null == mInstance) {
			mInstance = new OptionController();
		}
		return timerMode;
	}

	public static synchronized int getnumNotes() {
		// TODO Auto-generated method stub

		return numNotes;
	}

	public static synchronized String getClef() {

		return clef;
	}

	public static synchronized boolean getWinSoundMode() {
		return winSoundMode;
	}

	private void clefChanged() {
		// TODO Auto-generated method stub
		instrument = "Piano";
		if (clef.equals("Treble")) {
			Spinner instSpinner = (Spinner) findViewById(R.id.spinner1);
			ArrayAdapter<CharSequence> instAdapter = ArrayAdapter
					.createFromResource(this, R.array.instrument_array,
							android.R.layout.simple_spinner_item);
			instAdapter
					.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
			instSpinner.setAdapter(instAdapter);
			instSpinner.setOnItemSelectedListener(new instSpinnerListener());
		}
		if (clef.equals("Bass")) {
			Spinner instSpinner = (Spinner) findViewById(R.id.spinner1);
			ArrayAdapter<CharSequence> instAdapter = ArrayAdapter
					.createFromResource(this, R.array.bass_instrument_array,
							android.R.layout.simple_spinner_item);
			instAdapter
					.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
			instSpinner.setAdapter(instAdapter);
			instSpinner.setOnItemSelectedListener(new instSpinnerListener());

		}

	}

	private static synchronized void setTimerMode(boolean b) {
		// TODO Auto-generated method stub
		if (null == mInstance) {
			mInstance = new OptionController();
		}
		timerMode = b;
	}

	private static synchronized void setWinSoundMode(boolean b) {
		// TODO Auto-generated method stub
		winSoundMode = b;
	}

	private static synchronized void setClef(String c) {
		// TODO Auto-generated method stub
		clef = c;
	}

	private static synchronized void setNotes(String string) {
		// TODO Auto-generated method stub
		if (string.equals("Single Note")) {
			numNotes = 1;
		}
		if (string.equals("1 Measure")) {
			numNotes = 4;
		}

	}

	private static synchronized void setTime(String string) {
		// TODO Auto-generated method stub
		if (null == mInstance) {
			mInstance = new OptionController();
		}
		if (string.equals("2 Seconds")) {
			timer = 2 * 1000;
		}
		if (string.equals("5 Seconds")) {
			timer = 5 * 1000;
		}
		if (string.equals("7 Seconds")) {
			timer = 7 * 1000;
		}
		if (string.equals("10 Seconds")) {
			timer = 10 * 1000;
		}
		if (string.equals("15 Seconds")) {
			timer = 15 * 1000;
		}
	}

	public void buttonPressed(View v) {

		String button = (String) ((Button) v).getText();

		if (button.equals("Back")) {
			finish();
		}

	}

	// ================Spinner Listeners
	public class instSpinnerListener implements OnItemSelectedListener {

		public void onItemSelected(AdapterView<?> parent, View view, int pos,
				long id) {

			instrument = parent.getItemAtPosition(pos).toString();

		}

		public void onNothingSelected(AdapterView<?> arg0) {
			// TODO Auto-generated method stub

		}
	}

	public class timerSpinnerListener implements OnItemSelectedListener {

		public void onItemSelected(AdapterView<?> parent, View view, int pos,
				long id) {
			setTime(parent.getItemAtPosition(pos).toString());
		}

		public void onNothingSelected(AdapterView<?> arg0) {
			// TODO Auto-generated method stub

		}
	}

	public class clefSpinnerListener implements OnItemSelectedListener {

		public void onItemSelected(AdapterView<?> parent, View view, int pos,
				long id) {

			setClef(parent.getItemAtPosition(pos).toString());
			clefChanged();
		}

		public void onNothingSelected(AdapterView<?> arg0) {
			// TODO Auto-generated method stub

		}
	}

	public class notesSpinnerListener implements OnItemSelectedListener {

		public void onItemSelected(AdapterView<?> parent, View view, int pos,
				long id) {

			setNotes(parent.getItemAtPosition(pos).toString());
		}

		public void onNothingSelected(AdapterView<?> arg0) {
			// TODO Auto-generated method stub

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
