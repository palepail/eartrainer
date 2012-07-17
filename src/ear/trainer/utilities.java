package ear.trainer;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.text.Editable;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.Toast;

public class utilities extends Activity {
	private static utilities mInstance = null;
	private static String name = "Guest";
	private static int notes = 1;
	private static int mode = 1;

	protected utilities() {
	}

	/*
	 * public static synchronized void initialize() { if (null == mInstance) {
	 * mInstance = new utilities(); } }
	 */

	// START OF OPTIONS
	// ===========================================================================
	public static synchronized utilities getInstance() {
		if (null == mInstance) {
			mInstance = new utilities();
		}
		return mInstance;
	}

	public static synchronized void setNotes(int num) {
		if (null == mInstance) {
			mInstance = new utilities();
		}
		// optionChanged++;
		notes = num;
	}

	public static synchronized void setMode(int num) {
		if (null == mInstance) {
			mInstance = new utilities();
		}
		mode = num;
	}

	public static synchronized int getMode() {
		if (null == mInstance) {
			mInstance = new utilities();
		}
		return mode;
	}

	public static synchronized int getNotes() {
		if (null == mInstance) {
			mInstance = new utilities();
		}
		return notes;
	}

	public static synchronized String getName() {
		if (null == mInstance) {
			mInstance = new utilities();
		}
		return name;
	}

	// END OF OPTIONS
	// =====================================================================================

	// START OF ALERTS
	// ========================================================================================

	public static void aboutEarTrainer(Context context) {
		AlertDialog.Builder builder = new AlertDialog.Builder(context);
		builder.setTitle("About Ear Trainer");
		builder.setMessage(
				"Ear Trainer was conceptualized as a solo project during Hawaii "
						+ "Pacific University's CSCI 4702 Mobile Programming course. "
						+ "It is designed to aid in learning to distinguish notes by ear. "
						+ "Please send any questions or comments to eartrainerapp@gmail.com. "
						+ "Ear Trainer was created and is maintained by Derek Kam.")
				.setCancelable(false)
				.setNeutralButton("OK", new DialogInterface.OnClickListener() {
					public void onClick(DialogInterface dialog, int id) {
						dialog.cancel();
					}
				});
		builder.show();
	}

	public static void aboutRQuiz(Context context) {
		AlertDialog.Builder builder = new AlertDialog.Builder(context);
		builder.setTitle("About Reading Quiz");
		builder.setMessage(
				"The Reading Quiz mode is where you can test yourself "
						+ "at reading sheet music. A note is displayed and you must select what note it is from the "
						+ "7 options. You may press the note to hear it.")
				.setCancelable(false)
				.setNeutralButton("OK", new DialogInterface.OnClickListener() {
					public void onClick(DialogInterface dialog, int id) {
						dialog.cancel();
					}
				});
		builder.show();
	}

	public static void aboutLQuiz(Context context) {
		AlertDialog.Builder builder = new AlertDialog.Builder(context);
		builder.setTitle("About Listening Quiz");
		builder.setMessage(
				"The Listening Quiz mode is where you can test yourself "
						+ "at picking whole notes out by ear. To listen to the note press the '?,' "
						+ "then choose the note played from the 7 options. The result sound is disabled"
						+ "in this mode. In timed mode the note to be answered will be played automatically.")
				.setCancelable(false)
				.setNeutralButton("OK", new DialogInterface.OnClickListener() {
					public void onClick(DialogInterface dialog, int id) {
						dialog.cancel();
					}
				});
		builder.show();
	}

	public static void aboutPractice(Context context) {
		AlertDialog.Builder builder = new AlertDialog.Builder(context);
		builder.setTitle("About Practice Mode");
		builder.setMessage(
				"The Practice mode is where you can listen to and view notes "
						+ "at at your leisure. There is no goal other than to give you a feel for "
						+ "what notes go where and what the sound like. Press a note ont he measure "
						+ "to listen to it and view what not it is. "
						+ "You can scroll horizontally to view more notes.")
				.setCancelable(false)
				.setNeutralButton("OK", new DialogInterface.OnClickListener() {
					public void onClick(DialogInterface dialog, int id) {
						dialog.cancel();
					}
				});
		builder.show();
	}
	public static void aboutVersion(Context context) {
		AlertDialog.Builder builder = new AlertDialog.Builder(context);
		builder.setTitle("About Version 1.11");
		builder.setMessage(
				"Version 1.11 sees the introduction of measures in Ear Trainer. By selecting 1 Measure in the options menu," +
				"the use must correctly identify 4 notes in order to get the question correct. The notes currently selected are" +
				"displayed in the top right corner of the screen.")
				.setCancelable(false)
				.setNeutralButton("OK", new DialogInterface.OnClickListener() {
					public void onClick(DialogInterface dialog, int id) {
						dialog.cancel();
					}
				});
		builder.show();
	}

	public static void switchMode(final Context context) {
		final CharSequence[] items = { "Practice", "Reading Quiz",
				"Listening Quiz" };

		AlertDialog.Builder builder = new AlertDialog.Builder(context);
		builder.setTitle("Select Mode");
		builder.setItems(items, new DialogInterface.OnClickListener() {
			public void onClick(DialogInterface dialog, int item) {

				if (items[item].equals("Practice")) {
					dialog.cancel();
					goToListen(context);
				}
				if (items[item].equals("Reading Quiz")) {
					dialog.cancel();
					goToRQuiz(context);
				}
				if (items[item].equals("Listening Quiz")) {
					dialog.cancel();
					goToLQuiz(context);
				}
			}
		});

		builder.show();

	}

	public static void statOptions(final Context context) {
		// TODO Auto-generated method stub
		AlertDialog.Builder builder = new AlertDialog.Builder(context);
		builder.setMessage("Reset Statistics?")
				.setCancelable(false)
				.setPositiveButton("Yes",
						new DialogInterface.OnClickListener() {
							public void onClick(DialogInterface dialog, int id) {
								clearStats(context);
								dialog.cancel();
							}
						})
				.setNegativeButton("No", new DialogInterface.OnClickListener() {
					public void onClick(DialogInterface dialog, int id) {
						dialog.cancel();
					}
				});
		builder.show();

	}

	public static void setName(final Context context) {
		AlertDialog.Builder alert = new AlertDialog.Builder(context);

		alert.setTitle("Change User");
		alert.setMessage("What's your name?");

		// Set an EditText view to get user input
		final EditText input = new EditText(context);
		alert.setView(input);
		alert.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
			public void onClick(DialogInterface dialog, int whichButton) {
				Editable value = input.getText();
				name = value.toString();
				Toast.makeText(context, "Hello " + name + "!",
						Toast.LENGTH_SHORT).show();
				dialog.cancel();
			}
		});

		alert.setNegativeButton("Cancel",
				new DialogInterface.OnClickListener() {
					public void onClick(DialogInterface dialog, int whichButton) {
						// Canceled.
						dialog.cancel();
					}
				});

		alert.show();
	}

	// END OF ALERTS
	// ========================================================================================

	// START METHODS
	// ========================================================================================
	public static int getNoteNum(char charNote) {
		// TODO Auto-generated method stub
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
		
		
		return noteNum;

	}

	public static String getNoteLet(int charNum) {
		String noteLet;
		int Num = charNum % 7;

		if (OptionController.getClef().equals("Bass")) {
			Num += 2;
		}
		switch (Num) {
		case -2:
			noteLet = "A";
			break;
		case -1:
			noteLet = "B";
			break;
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
			break;
		case 7:
			noteLet = "C";
			break;
		case 8:
			noteLet = "D";
			break;
			
		default:
			noteLet = "C";
			break;
		}
		return noteLet;

	}

	public static void goToRQuiz(Context context) {
		Intent intent = new Intent(Intent.ACTION_VIEW);
		intent.setClassName(context, RQuizController.class.getName());
		context.startActivity(intent);
	}

	public static void goToLQuiz(Context context) {
		Intent intent = new Intent(Intent.ACTION_VIEW);
		intent.setClassName(context, LQuizController.class.getName());
		context.startActivity(intent);
	}

	public static void goToListen(Context context) {
		Intent intent = new Intent(Intent.ACTION_VIEW);
		intent.setClassName(context, ListenController.class.getName());
		context.startActivity(intent);
	}

	public static void goToAbout(Context context) {
		// TODO Auto-generated method stub
		Intent intent = new Intent(Intent.ACTION_VIEW);
		intent.setClassName(context, AboutController.class.getName());
		context.startActivity(intent);

	}

	public static void goToOptions(Context context) {
		// TODO Auto-generated method stub
		Intent intent = new Intent(Intent.ACTION_VIEW);
		intent.setClassName(context, OptionController.class.getName());
		context.startActivity(intent);

	}

	private static void clearStats(Context context) { // not implemented
		Toast.makeText(context, "Statistics Reset", Toast.LENGTH_SHORT).show();
	}

	public static void hub(String string, MenuItem item, Context context) {
		// TODO Auto-generated method stub
		switch (item.getItemId()) {
		case R.id.about:
			goToAbout(context);
			break;
		case R.id.mode:
			switchMode(context);
			break;
		case R.id.options:
			goToOptions(context);
			break;

		/*
		 * 
		 * case R.id.statistics: statOptions(context); break; case R.id.user:
		 * setName(context); break;
		 */
		}
	}

}
