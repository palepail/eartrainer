package ear.trainer;

import java.util.Random;

import android.content.Context;
import android.content.res.AssetFileDescriptor;
import android.media.MediaPlayer;

public class Instrument {
	static MediaPlayer player = new MediaPlayer();

	public void playNote(Context context, int note) throws Exception {
		String instrument = OptionController.getInstrument();

		String noteString = Integer.toString(findOctave(note)) + utilities.getNoteLet(note);

		AssetFileDescriptor afd = context.getAssets().openFd(
				"Sounds/" + instrument + "_" + noteString + ".mp3");

		if (player != null) {
			player.release();
			player = null;
		}
		player = new MediaPlayer();
		player.setDataSource(afd.getFileDescriptor(), afd.getStartOffset(),
				afd.getLength());
		player.prepare();
		player.start();

	}

	public static int findOctave(int noteNum) {
		int octave = 0;
		if (OptionController.getClef().equals("Bass")) {
			if (noteNum >= 12) {
				octave = 4;
			} else if (noteNum >= 5) {
				octave = 3;
			} else if (noteNum >= 0) {
				octave = 2;
			}
		}
		else{
			if (noteNum >= 14) {
				octave = 6;
			} else if (noteNum >= 7) {
				octave = 5;
			} else if (noteNum >= 0) {
				octave = 4;
			}
		}

		

		return octave;
	}

	public void playAnswer(Context context, String result) throws Exception {
		Random generator = new Random();
		int ran = generator.nextInt(3);

		AssetFileDescriptor afd = context.getAssets().openFd(
				"Sounds/" + result + ran + ".mp3");
		if (player != null) {
			player.release();
			player = null;
		}
		player = new MediaPlayer();
		player.setDataSource(afd.getFileDescriptor(), afd.getStartOffset(),
				afd.getLength());
		player.prepare();
		player.start();

	}
}
