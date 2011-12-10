package ear.trainer;

import java.util.Random;

import android.content.Context;
import android.content.res.AssetFileDescriptor;
import android.media.MediaPlayer;

public class Instrument {
	static MediaPlayer player = new MediaPlayer();

	public void playNote(Context context, int note) throws Exception {
		String instrument = OptionController.getInstrument();

		String noteString = Integer.toString(findOctave(note)) + findNote(note);

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

	public static String findNote(int noteNum) {
		int note = noteNum % 7;
		String noteLet = "?";

		switch (note) {
		case 0:
			noteLet = "C";
			break;
		case 1:
			noteLet = "D";
			break;
		case 2:
			noteLet = "E";
			break;
		case 3:
			noteLet = "F";
			break;
		case 4:
			noteLet = "G";
			break;
		case 5:
			noteLet = "A";
			break;
		case 6:
			noteLet = "B";
		}

		return noteLet;
	}

	public static int findOctave(int noteNum) {
		int octave = 0;

		if (noteNum >= 14) {
			octave = 6;
		} else if (noteNum >= 7) {
			octave = 5;
		} else if (noteNum >= 0) {
			octave = 4;
		}

		if (OptionController.getClef().equals("Bass")) {
			octave -= 2;
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
