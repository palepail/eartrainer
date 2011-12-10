package ear.trainer;

import java.util.Random;
import android.app.Activity;
import android.content.Context;
import android.graphics.Typeface;
import android.media.AudioManager;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RadioGroup.OnCheckedChangeListener;
import android.widget.TextView;
import android.widget.Toast;
import com.google.ads.*;
import android.os.CountDownTimer;

public class RQuizController extends Activity implements
		OnCheckedChangeListener {
	private AudioManager audio;
	private int radioNote;
	private int score = 0;
	private int manyNotes = 1;
	private int[] answerKey = new int[10];
	private int[] playerAnswer = new int[10];
	private int counter = 0;
	CountDownTimer timer = new CountDownTimer(OptionController.getSeconds(),
			1000) {

		public void onTick(long millisUntilFinished) {
			TextView view = (TextView) findViewById(R.id.textView3);
			Typeface font = Typeface.createFromAsset(getAssets(),
					"Fonts/dum1.ttf");
			view.setTypeface(font);
			view.setText("" + millisUntilFinished / 1000);
		}

		public void onFinish() {
			try {
				submit(true);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	};

	// private String answerString = ""; user answers submitted for use with
	// multiple note quiz

	protected void onStart() {
		super.onStart();

		Log.d("LIFECYCLE", "R Quiz onStart()");
	}

	protected void onRestart() {
		super.onRestart();

		Log.d("LIFECYCLE", "R Quiz onRestart()");
	}

	protected void onResume() {
		super.onResume();

		populate();

		Log.d("LIFECYCLE", "R Quiz onResume()");
	}

	protected void onPause() {
		super.onPause();
		timer.cancel();
		TextView tv = (TextView) findViewById(R.id.textView3);
		tv.setText("");
		Log.d("LIFECYCLE", "R Quiz onPause()");
	}

	protected void onStop() {
		super.onStop();
		timer.cancel();
		TextView tv = (TextView) findViewById(R.id.textView3);
		tv.setText("");
		Log.d("LIFECYCLE", "R Quiz onStop()");
	}

	protected void onDestroy() {
		super.onDestroy();
		Log.d("LIFECYCLE", "R Quiz onDestroy()");
	}

	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.rquiz);
		answerKey[0] = 5;
		Typeface font = Typeface.createFromAsset(getAssets(), "Fonts/dum1.ttf");
		TextView tv = (TextView) findViewById(R.id.textView1);
		tv.setTypeface(font);
		tv.setText("Streak = " + score);

		audio = (AudioManager) getSystemService(Context.AUDIO_SERVICE);

		RadioGroup radio = (RadioGroup) findViewById(R.id.radioGroup1);
		radio.setOnCheckedChangeListener(this);
		
		/*
		// AdSpace
		// Create the adView
		AdView adView = new AdView(this, AdSize.BANNER, "a14e097ecc8d3d9");
		// Lookup your LinearLayout assuming it�s been given
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
			bar.setImageResource(R.drawable.bassblankmeasure);
		}
		if (OptionController.getClef().equals("Treble")) {
			ImageView bar = (ImageView) findViewById(R.id.imageView1);
			bar.setImageResource(R.drawable.blankmeasure);
		}

	}

	private void populate() {
		counter = 0;
		ImageView iv2 = (ImageView) findViewById(R.id.imageView2);
		ImageView iv3 = (ImageView) findViewById(R.id.imageView3);
		ImageView iv4 = (ImageView) findViewById(R.id.imageView4);
		ImageView iv5 = (ImageView) findViewById(R.id.imageView5);

		TextView tv = (TextView) findViewById(R.id.textView1);
		tv.setText("Streak = " + score);

		// TextView nameDisplay = (TextView) findViewById(R.id.textView3);
		// //Name Display
		// nameDisplay.setText(utilities.getName());

		/*
		 * answerString = ""; TextView tv1 = (TextView)
		 * findViewById(R.id.textView2); tv1.setText(answerString);
		 */
		for (int i = 0; i < answerKey.length; i++) {
			answerKey[i] = 0;
			playerAnswer[i] = 99;
		}
		switch (manyNotes) {
		case 1:
			changeNote(iv2, generateRandom(), 0);
			setRest(iv3);
			setRest(iv4);
			setRest(iv5);
			break;
		case 2:
			changeNote(iv2, generateRandom(), 0);
			changeNote(iv3, generateRandom(), 1);
			setRest(iv4);
			setRest(iv5);
			break;
		case 3:
			changeNote(iv2, generateRandom(), 0);
			changeNote(iv3, generateRandom(), 1);
			changeNote(iv4, generateRandom(), 2);
			setRest(iv5);
			break;
		case 4:
			changeNote(iv2, generateRandom(), 0);
			changeNote(iv3, generateRandom(), 1);
			changeNote(iv4, generateRandom(), 2);
			changeNote(iv5, generateRandom(), 3);
			break;
		}

		RadioGroup radio = (RadioGroup) findViewById(R.id.radioGroup1);
		radio.check(-1);

		if (OptionController.getTimerMode() == true) {
			timer = new CountDownTimer(OptionController.getSeconds(), 1000) {

				public void onTick(long millisUntilFinished) {
					TextView view = (TextView) findViewById(R.id.textView3);
					Typeface font = Typeface.createFromAsset(getAssets(),
							"Fonts/dum1.ttf");
					view.setTypeface(font);
					view.setText(millisUntilFinished / 1000 + "");
				}

				public void onFinish() {
					try {
						submit(true);
					} catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			};

			timer.start();
		} else {
			timer.cancel();
			TextView textView3 = (TextView) findViewById(R.id.textView3);
			textView3.setText("");
		}
	}

	public void submit(boolean force) throws Exception {
		boolean win = true;

		playerAnswer[counter] = radioNote;
		/*
		 * TextView tv = (TextView) findViewById(R.id.textView2); answerString
		 * += radioNote + " "; tv.setText(answerString);
		 */
		counter++;
		if (OptionController.getTimerMode() == true) {
			timer.cancel();
		}

		if (counter >= manyNotes) {
			for (int i = 0; i < manyNotes; i++) {
				if (Instrument.findNote(answerKey[i]).equals(
						Instrument.findNote(playerAnswer[i]))) {

					win = true;

				} else {
					win = false;
				}
				counter = 0;

			}
			if (force == true) {
				win = false;
			}

			if (win == false) {
				String answerString = "";
				if (OptionController.getWinSoundMode() == true) {
					playAnswer("fail");
				}
				for (int j = 0; j < manyNotes; j++) {
					answerString += Instrument.findNote(answerKey[j]);
					if (j != 0 || j != manyNotes - 1) {
						answerString += ", ";
					}
				}
				Toast toast = Toast.makeText(this, "Correct Answer: "
						+ answerString, Toast.LENGTH_SHORT);
				toast.setGravity(Gravity.CENTER, 0, -30);
				toast.show();
				score = 0;
			}
			if (win == true) {
				score++;
				if (OptionController.getWinSoundMode() == true) {
					playAnswer("win");
				}
				Toast toast = Toast.makeText(this, "Correct",
						Toast.LENGTH_SHORT);
				toast.setGravity(Gravity.CENTER, 0, -30);
				toast.show();

			}
			populate();

		}

	}

	public void play(View v) throws Exception {
		playNote(answerKey[counter]);
	}

	private void changeNote(ImageView iv, int ran, int num) {
		switch (ran) {
		case 0: {

			answerKey[num] = 0;
			iv.setImageResource(R.drawable.lc);
			break;
		}
		case 1: {

			answerKey[num] = 1;
			iv.setImageResource(R.drawable.ld);
			break;
		}
		case 2: {

			answerKey[num] = 2;
			iv.setImageResource(R.drawable.le);
			break;
		}
		case 3: {

			answerKey[num] = 3;
			iv.setImageResource(R.drawable.lf);
			break;
		}
		case 4: {
			answerKey[num] = 4;
			iv.setImageResource(R.drawable.lg);
			break;
		}
		case 5: {
			answerKey[num] = 5;
			iv.setImageResource(R.drawable.la);
			break;
		}
		case 6: {
			answerKey[num] = 6;
			iv.setImageResource(R.drawable.lb);
			break;
		}
		case 7: {
			answerKey[num] = 7;
			iv.setImageResource(R.drawable.hc);
			break;
		}
		case 8: {
			answerKey[num] = 8;
			iv.setImageResource(R.drawable.hd);
			break;
		}
		case 9: {
			answerKey[num] = 9;
			iv.setImageResource(R.drawable.he);
			break;
		}
		case 10: {
			answerKey[num] = 10;
			iv.setImageResource(R.drawable.hf);
			break;
		}
		case 11: {
			answerKey[num] = 11;
			iv.setImageResource(R.drawable.hg);
			break;
		}
		case 12: {
			answerKey[num] = 12;
			iv.setImageResource(R.drawable.ha);
			break;
		}
		case 13: {
			answerKey[num] = 13;
			iv.setImageResource(R.drawable.hb);
			break;
		}
		case 14: {
			answerKey[num] = 14;
			iv.setImageResource(R.drawable.hhc);
			break;
		}
		default: {
			answerKey[num] = 0;
			iv.setImageResource(R.drawable.lc);
			break;
		}
		}

	}

	private void setRest(ImageView iv) {
		iv.setImageResource(R.drawable.qr);
	}

	private int generateRandom() {
		Random generator = new Random();
		int ran = generator.nextInt(15);
		return ran;

	}

	private void playNote(int note) throws Exception {
		Instrument player = new Instrument();
		player.playNote(this, note);

	}

	private void playAnswer(String result) throws Exception {
		if (OptionController.getWinSoundMode() == true) {
			Instrument player = new Instrument();
			player.playAnswer(this, result);
		}
	}

	@Override
	public void onCheckedChanged(RadioGroup group, int checkedId) {

		for (int i = 0; i < group.getChildCount(); i++) {
			RadioButton btn = (RadioButton) group.getChildAt(i);
			if (btn.getId() == checkedId) {
				String text = (String) btn.getText();
				// do something with text
				char charNote = text.charAt(0);
				int noteNum;
				switch (charNote) {
				case 'C':
					noteNum = 0;
					break;
				case 'D':
					noteNum = 1;
					break;
				case 'E':
					noteNum = 2;
					break;
				case 'F':
					noteNum = 3;
					break;
				case 'G':
					noteNum = 4;
					break;
				case 'A':
					noteNum = 5;
					break;
				case 'B':
					noteNum = 6;
					break;
				default:
					noteNum = 0;
					break;
				}

				radioNote = noteNum;
			}

		}
		if (group.getCheckedRadioButtonId() != -1) {
			try {
				submit(false);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

	}

	// =======================Menus====================
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		MenuInflater inflater = getMenuInflater();
		inflater.inflate(R.menu.trainer_menu, menu);
		return true;
	}

	public boolean onOptionsItemSelected(MenuItem item) {
		super.onOptionsItemSelected(item);
		utilities.hub("rQuiz", item, this);
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
			timer.cancel();
			TextView tv = (TextView) findViewById(R.id.textView3);
			tv.setText("");
			finish();
			return true;
		}
		case KeyEvent.KEYCODE_MENU: {
			timer.cancel();
			TextView tv = (TextView) findViewById(R.id.textView3);
			tv.setText("");
			openOptionsMenu();
			return true;
		}
		case KeyEvent.KEYCODE_HOME: {
			timer.cancel();
			TextView tv = (TextView) findViewById(R.id.textView3);
			tv.setText("");
			finish();
			return true;
		}
		default: {
			return false;
		}
		}
	}

}