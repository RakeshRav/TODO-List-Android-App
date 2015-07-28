package com.example.todo;

import java.util.Calendar;

import android.os.Bundle;
import android.app.ActionBar;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.TimePickerDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.DialogInterface.OnClickListener;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

public class NewMeetingActivity extends Activity {

	int i_Meeting;
	SharedPreferences spStore, spId, spName;
	SharedPreferences.Editor spedStore, spedId, spedName;
	String name, desc, place;

	EditText edName, edDesc, edPl;
	String date, time, hour24Str, minuteStr, yearStr, monthStr, dayStr;
	DatePicker dpMeeting;
	TimePicker tpMeeting;
	TextView tv7, tv8;
	int hour12, hour24, minute, day, month, year;
	int hour24Now, minuteNow, yearNow, monthNow, dayNow;
	Intent Meeting;
	String MeetingStr, FileName;
	final int SHOW_DATE_DIALOG = 999;
	final int SHOW_TIME_DIALOG = 998;
	final int Invalid_Dialog = 997;
	final int Invalid_Date_Time = 996;

	ActionBar ab;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_new_meeting);
		ab = getActionBar();

		MeetingStr = "Meeting";

		edName = (EditText) findViewById(R.id.editText1);
		edDesc = (EditText) findViewById(R.id.editText2);
		edPl = (EditText) findViewById(R.id.editText5);

		ColorDrawable cd = new ColorDrawable();
		cd.setColor(Color.rgb(0, 123, 213));
		tv7 = (TextView) findViewById(R.id.textView7);
		tv8 = (TextView) findViewById(R.id.textView8);
		tpMeeting = new TimePicker(getBaseContext());
		dpMeeting = new DatePicker(getBaseContext());
		ab.setBackgroundDrawable(cd);

		ab.setTitle("Add Meeting");
		ab.setHomeButtonEnabled(true);
		ab.setDisplayHomeAsUpEnabled(true);
		ab.setIcon(R.drawable.todoicon1);

	}

	public void setCurrentDateNTime() {
		Calendar c = Calendar.getInstance();
		yearNow = c.get(Calendar.YEAR);
		monthNow = c.get(Calendar.MONTH);
		dayNow = c.get(Calendar.DAY_OF_MONTH);
		hour24Now = c.get(Calendar.HOUR_OF_DAY);
		minuteNow = c.get(Calendar.MINUTE);
		dpMeeting.init(yearNow, monthNow, dayNow, null);
		tpMeeting.setCurrentHour(hour24Now);
		tpMeeting.setCurrentMinute(minuteNow);

	}

	public void setDate(View v) {
		setCurrentDateNTime();

		showDialog(SHOW_DATE_DIALOG);
	}

	DatePickerDialog.OnDateSetListener datePickerListener = new DatePickerDialog.OnDateSetListener() {

		@Override
		public void onDateSet(DatePicker arg0, int arg1, int arg2, int arg3) {

			year = arg1;
			month = arg2 + 1;
			day = arg3;

			if (month < 10) {
				monthStr = "0" + month;
			} else {
				monthStr = month + "";
			}
			if (day < 10) {
				dayStr = "0" + day;
			} else {
				dayStr = day + "";
			}

			tv7.setText(passDate(year, month, day));
			dpMeeting.init(year, month, day, null);

		}

	};

	private String passDate(int year, int month, int day) {

		if (month < 10) {
			date = "0" + month + "/" + year;
		} else {
			date = month + "/" + year;
		}
		if (day < 10) {
			date = "0" + day + "/" + date;
		} else {
			date = day + "/" + date;
		}
		return date;
	}

	public void setTime(View v) {
		setCurrentDateNTime();

		showDialog(SHOW_TIME_DIALOG);
	}

	TimePickerDialog.OnTimeSetListener timePickerListener = new TimePickerDialog.OnTimeSetListener() {

		@Override
		public void onTimeSet(TimePicker arg0, int arg1, int arg2) {
			// TODO Auto-generated method stub
			hour24 = arg1;
			minute = arg2;

			if (hour24 < 10) {
				hour24Str = "0" + hour24;
			} else {
				hour24Str = hour24 + "";
			}
			if (minute < 10) {
				minuteStr = "0" + minute;
			} else {
				minuteStr = minute + "";
			}

			tv8.setText(passTime(hour24, minute));
			tpMeeting.setCurrentHour(hour24);
			tpMeeting.setCurrentMinute(minute);
		}

	};

	public String passTime(int hour, int minute) {
		if (hour < 10) {
			time = "0" + hour;
		} else {
			time = hour + "";
		}

		if (minute < 10) {
			time = time + ":" + "0" + minute;
		} else {
			time = time + ":" + minute;
		}
		if (hour == 12 && minute >= 10) {
			time = hour + ":" + minute;
			return time = time + " PM";
		}
		if (hour == 12 && minute < 10) {
			time = hour + ":0" + minute;
			return time = time + " PM";
		}
		if (hour > 12 && minute >= 10) {
			hour12 = hour - 12;
			if (hour12 < 10) {
				time = "0" + hour12 + ":" + minute;

			} else {
				time = hour12 + ":" + minute;
			}
			return time = time + " PM";
		} else if (hour > 12 && minute < 10) {
			hour12 = hour - 12;
			if (hour12 < 10) {
				time = "0" + hour12 + ":0" + minute;

			} else {
				time = hour12 + ":0" + minute;
			}
			return time = time + " PM";

		}
		return time = time + " AM";
	}

	@Override
	public Dialog onCreateDialog(int id) {

		switch (id) {
		case 1:
			AlertDialog.Builder about = new AlertDialog.Builder(this);
			about.setTitle("About");
			about.setIcon(R.drawable.info1);
			about.setMessage("This ToDo list application helps you to manage your daily tasks or the tasks you want to do in future. It keeps track of the birthday dates also and automatically sends greeting message."
					+ " Reminders are also provided to remind your task as per the date and time given by you."
					+ "In addition to this it also supports shake sensors for clearing all the completed tasks."
					+ ""
					+ "\n\n"
					+ "This basic application is very useful to remind you of the tasks you need to do.To know more of this application just use it.");
			about.setNeutralButton("OK", new OnClickListener() {

				@Override
				public void onClick(DialogInterface arg0, int arg1) {

				}

			});
			return about.create();
		case 2:
			AlertDialog.Builder developers = new AlertDialog.Builder(this);
			developers.setTitle("Developers");
			developers.setMessage("Developed by : Rakesh Rav, Saurabh Dayya");
			developers.setNeutralButton("OK", new OnClickListener() {

				@Override
				public void onClick(DialogInterface arg0, int arg1) {

				}

			});
			return developers.create();
		case SHOW_DATE_DIALOG:

			return new DatePickerDialog(this, datePickerListener, yearNow,
					monthNow, dayNow);

		case SHOW_TIME_DIALOG:

			return new TimePickerDialog(this, timePickerListener, hour24Now,
					minuteNow, false);

		case Invalid_Dialog:

			AlertDialog.Builder invalid = new AlertDialog.Builder(this);
			invalid.setTitle("Invalid Title/Description/Place");
			invalid.setMessage("Please enter Title/Description/Place");

			return invalid.create();

		case Invalid_Date_Time:

			AlertDialog.Builder invalid2 = new AlertDialog.Builder(this);
			invalid2.setTitle("Invalid Date/Time");
			invalid2.setMessage("Please enter valid Date/Time");

			return invalid2.create();

		default:
			break;
		}

		return super.onCreateDialog(id);
	}

	@Override
	public boolean onMenuItemSelected(int featureId, MenuItem item) {
		switch (item.getItemId()) {
		case 1:
			showDialog(1);
			break;
		case 2:
			showDialog(2);
			break;
		case android.R.id.home:

			finish();

			break;

		default:
			break;
		}
		return super.onMenuItemSelected(featureId, item);
	}

	public void Add_Meeting(View v) {

		
//		Calendar c = Calendar.getInstance();
//		yearNow = c.get(Calendar.YEAR);
//		monthNow = c.get(Calendar.MONTH);
//		dayNow = c.get(Calendar.DAY_OF_MONTH);
//		hour24Now = c.get(Calendar.HOUR_OF_DAY);
//		minuteNow = c.get(Calendar.MINUTE);
		
		name = edName.getText().toString();
		desc = edDesc.getText().toString();
		place = edPl.getText().toString();
		String hint6 = tv7.getText().toString();
		String hint7 = tv8.getText().toString();
		
		
		if (name.equals("") || desc.equals("") || place.equals("")) {

			showDialog(Invalid_Dialog);

		} 		else if ((hint6.equals("")) || (hint7.equals("")) ) {
			showDialog(Invalid_Date_Time);

		} else if ((year < yearNow)) {
			showDialog(Invalid_Date_Time);
		}else if ((year == yearNow) && (month < monthNow+1)) {
			showDialog(Invalid_Date_Time);

		}else if ((year==yearNow) && (month == monthNow+1) && (day < dayNow)) {
			showDialog(Invalid_Date_Time);

		}
		
		
		else if ((year==yearNow) && (month == monthNow+1) && (day == dayNow) && (hour24 < hour24Now)) {
			showDialog(Invalid_Date_Time);

		}else if ((year==yearNow) && (month==monthNow+1)  && (day == dayNow) && (hour24 == hour24Now) && (minute < minuteNow)) {
			showDialog(Invalid_Date_Time);

		}
		else {

			FileName = MeetingStr + "_" + year + "_" + monthStr + "_" + dayStr
					+ "_" + hour24Str + "_" + minuteStr;
			spStore = getSharedPreferences(FileName, MODE_PRIVATE);
			spedStore = spStore.edit();

			spId = getSharedPreferences("SaveValueOfIMeeting", MODE_PRIVATE);
			spedId = spId.edit();

			i_Meeting = spId.getInt("i", 0);

			spedStore.putString("Name", name);
			spedStore.putString("Desc", desc);
			spedStore.putString("Place", place);
			spedStore.putString("Date", date);
			spedStore.putString("Time", time);
			spedStore.putInt("Year", year);
			spedStore.putInt("Month", month);
			spedStore.putInt("Day", day);
			spedStore.putInt("Hour", hour24);
			spedStore.putInt("Minute", minute);

			spedStore.commit();

			Meeting = new Intent(getBaseContext(), Task_Meeting.class);

			spName = getSharedPreferences("SaveFileNamesMeeting", MODE_APPEND);
			spedName = spName.edit();

			spedName.putString("FileName" + i_Meeting, FileName);
			spedName.commit();

			i_Meeting++;
			spedId.putInt("i", i_Meeting);
			spedId.commit();

			startService(Meeting);

			// Toast.makeText(getBaseContext(), FileName+" Created",
			// Toast.LENGTH_SHORT).show();
			Toast.makeText(getBaseContext(), "Meeting Added", Toast.LENGTH_LONG)
					.show();

		}

	}

}
