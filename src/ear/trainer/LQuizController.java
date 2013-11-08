package ear.trainer;

import java.util.Random;

import android.app.Activity;
import android.content.Context;
import android.graphics.Typeface;
import android.media.AudioManager;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.util.Log;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class LQuizController extends Activity{
	private AudioManager audio;
	private int score = 0;
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
			view.setText(millisUntilFinished / 1000 + "");
		}

		public void onFinish() {
			try {
				end(true);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	};

	// private String answerString = "";

	protected void onStart() {
		super.onStart();

		Log.d("LIFECYCLE", "L Quiz onStart()");

	}

	protected void onRestart() {
		super.onRestart();
		Log.d("LIFECYCLE", "L Quiz onRestart()");

	}

	protected void onResume() {
		super.onResume();

		populate();

		Log.d("LIFECYCLE", "L Quiz onResume()");

	}

	protected void onPause() {
		super.onPause();
		timer.cancel();
		TextView tv = (TextView) findViewById(R.id.textView3);
		tv.setText("");
		Log.d("LIFECYCLE", "L Quiz onPause()");
	}

	protected void onStop() {
		super.onStop();
		timer.cancel();
		Log.d("LIFECYCLE", "L Quiz onStop()");
	}

	protected void onDestroy() {
		super.onDestroy();
		Log.d("LIFECYCLE", "L Quiz onDestroy()");
	}

	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		audio = (AudioManager) getSystemService(Context.AUDIO_SERVICE);
		super.onCreate(savedInstanceState);
		setContentView(R.layout.lquiz);
		answerKey[0] = 5;
		Typeface font = Typeface.createFromAsset(getAssets(), "Fonts/dum1.ttf");

		TextView tv = (TextView) findViewById(R.id.textView1);
		tv.setTypeface(font);
		tv.setText("Streak = " + score);

		// TextView nameDisplay = (TextView) findViewById(R.id.textView3);
		// //Name Display
		// nameDisplay.setText(utilities.getName());



		
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
		TextView tv1 = (TextView) findViewById(R.id.textView2);
		tv1.setText("");


		/*
		 * TextView tv1 = (TextView) findViewById(R.id.textView2);
		 * tv1.setText(answerString); answerString = "";
		 */
		for (int i = 0; i < answerKey.length; i++) {
			answerKey[i] = 0;
			playerAnswer[i] = 99;
		}
		switch (OptionController.getnumNotes()) {
		case 1:
			changeNote(iv2, generateRandom(), 0);
			iv2.setImageResource(R.drawable.nani);
			setRest(iv3);
			setRest(iv4);
			setRest(iv5);
			break;
		case 2:
			changeNote(iv2, generateRandom(), 0);
			iv2.setImageResource(R.drawable.nani);
			changeNote(iv3, generateRandom(), 1);
			iv3.setImageResource(R.drawable.nani);
			setRest(iv4);
			setRest(iv5);
			break;
		case 3:
			changeNote(iv2, generateRandom(), 0);
			iv2.setImageResource(R.drawable.nani);
			changeNote(iv3, generateRandom(), 1);
			iv3.setImageResource(R.drawable.nani);
			changeNote(iv4, generateRandom(), 2);
			iv4.setImageResource(R.drawable.nani);
			setRest(iv5);
			break;
		case 4:
			changeNote(iv2, generateRandom(), 0);
			iv2.setImageResource(R.drawable.nani);
			changeNote(iv3, generateRandom(), 1);
			iv3.setImageResource(R.drawable.nani);
			changeNote(iv4, generateRandom(), 2);
			iv4.setImageResource(R.drawable.nani);
			changeNote(iv5, generateRandom(), 3);
			iv5.setImageResource(R.drawable.nani);
			break;
		}

	

		if (OptionController.getTimerMode() == true) {
			timer = new CountDownTimer(OptionController.getSeconds(), 1000) {

				public void onTick(long millisUntilFinished) {
					TextView view = (TextView) findViewById(R.id.textView3);
					Typeface font = Typeface.createFromAsset(getAssets(),
							"Fonts/dum1.ttf");
					view.setTypeface(font);
					view.setText("" + millisUntilFinished / 1000);
				}

				public void onFinish() {
					try {
						end(true);
					} catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			};
			timer.start();
			try {
				playNote(answerKey[counter]);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} else {
			timer.cancel();
			TextView textView3 = (TextView) findViewById(R.id.textView3);
			textView3.setText("");

		}

	}

	public void submit(boolean force, int noteNum) throws Exception {
		playerAnswer[counter] = noteNum;
		if (OptionController.getnumNotes()!=1)
		{
			TextView tv = (TextView) findViewById(R.id.textView2); 
			String answerString = (String) tv.getText();
			answerString += utilities.getNoteLet(noteNum) +", ";
			tv.setText(answerString);
		}
	
		counter++;

		if (OptionController.getTimerMode() == true) {
			playNote(answerKey[counter]);
		}

		if (counter >= OptionController.getnumNotes()) {
			end(force);
		}

	}

	private void end(boolean force) throws Exception {
		// TODO Auto-generated method stub
		boolean win=true;
		if (OptionController.getTimerMode() == true) {
			timer.cancel();
		}
		for (int i = 0; i < OptionController.getnumNotes(); i++) {
			if (utilities.getNoteLet(answerKey[i]).equals(
					utilities.getNoteLetAnswer(playerAnswer[i]))) {
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

			if (OptionController.getTimerMode() == false
					&& OptionController.getWinSoundMode()) {

				playAnswer("fail");

			}
			String answerString = "";
			for (int j = 0; j < OptionController.getnumNotes(); j++) {
				answerString += utilities.getNoteLet(answerKey[j]);
				if (j != 0 || j != OptionController.getnumNotes() - 1) {
					answerString += ", ";
				}
			}
			score = 0;
			Toast toast = Toast.makeText(this, "Correct Answer: "
					+ answerString, Toast.LENGTH_SHORT);
			toast.setGravity(Gravity.TOP|Gravity.CENTER, 0, 25);
			toast.show();
			// playNote(notes[i]);
		}
		if (win == true) {
			score++;
			if (OptionController.getTimerMode() == false
					&& OptionController.getWinSoundMode()) {
				playAnswer("win");
			}
			Toast toast = Toast.makeText(this, "Correct",
					Toast.LENGTH_SHORT);
			toast.setGravity(Gravity.TOP|Gravity.CENTER, 0, 25);
			toast.show();
		}
		populate();

	}

	public void play0(View v) throws Exception {
		playNote(answerKey[0]);
	}

	public void play1(View v) throws Exception {
		if (OptionController.getnumNotes() != 1) {
			playNote(answerKey[1]);
		}
	}

	public void play2(View v) throws Exception {
		if (OptionController.getnumNotes() != 1) {
			playNote(answerKey[2]);
		}
	}

	public void play3(View v) throws Exception {
		if (OptionController.getnumNotes() != 1) {
			playNote(answerKey[3]);
		}
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
			iv.setImageResource(R.drawable.hhc);
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

	public void pickA(View v) throws Exception {
		submit(false,utilities.getNoteNum('A'));
	}

	public void pickB(View v) throws Exception {
		submit(false,utilities.getNoteNum('B'));
	}

	public void pickC(View v) throws Exception {
		submit(false,utilities.getNoteNum('C'));
	}

	public void pickD(View v) throws Exception {
		submit(false,utilities.getNoteNum('D'));
	}
	public void pickE(View v) throws Exception {
		submit(false,utilities.getNoteNum('E'));
	}
	public void pickF(View v) throws Exception {
		submit(false,utilities.getNoteNum('F'));
	}
	public void pickG(View v) throws Exception {
		submit(false,utilities.getNoteNum('G'));
	}
	

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