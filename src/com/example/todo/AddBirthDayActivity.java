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
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

public class AddBirthDayActivity extends Activity {

	int i_Birth;
	SharedPreferences spStore, spId, spName;
	SharedPreferences.Editor spedStore, spedId, spedName;
	String name, desc, number;
	String date, time, hour24Str, minuteStr, yearStr, monthStr, dayStr;
	Intent Birthday;
	String BirthDay, FileName;

	EditText edName, edDesc, edNo;
	CheckBox ck1;
	ActionBar ab;
	DatePicker dpBirth;
	TimePicker tpBirth;
	TextView tv5, tv6;
	int hour24, hour12, minute, day, month, year;
	boolean check_karo;
	int hour24Now, minuteNow, yearNow, monthNow, dayNow;

	RelativeLayout r1;
	final int SHOW_DATE_DIALOG = 999;
	final int SHOW_TIME_DIALOG = 998;
	final int Warning_Dialog = 997;
	final int Invalid_Dialog = 996;
	final int Invalid_Dialog1 = 995;
	final int Invalid_Date_Time = 994;

	@Override
	protected void onCreate(Bundle savedInstanceState) {

		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_add_birth_day);

		BirthDay = "BirthDay";

		edName = (EditText) findViewById(R.id.editText1);
		edDesc = (EditText) findViewById(R.id.editText5);
		edNo = (EditText) findViewById(R.id.editText4);

		ck1 = (CheckBox) findViewById(R.id.checkBox1);
		dpBirth = new DatePicker(getBaseContext());
		tpBirth = new TimePicker(getBaseContext());
		tv5 = (TextView) findViewById(R.id.textView5);
		tv6 = (TextView) findViewById(R.id.textView6);
		ab = getActionBar();

		ColorDrawable cd = new ColorDrawable();
		cd.setColor(Color.rgb(0, 123, 213));

		ab.setBackgroundDrawable(cd);

		ab.setTitle("Add BirthDay");
		ab.setHomeButtonEnabled(true);
		ab.setDisplayHomeAsUpEnabled(true);
		ab.setIcon(R.drawable.todoicon1);
		r1 = (RelativeLayout) findViewById(R.id.relativeFocus);
		r1.setVisibility(View.GONE);
		ck1.setOnCheckedChangeListener(new OnCheckedChangeListener() {

			@Override
			public void onCheckedChanged(CompoundButton arg0, boolean arg1) {

				if (arg1 == true) {
					showDialog(Warning_Dialog);

				} else {

					r1.setVisibility(View.GONE);
					check_karo = false;
				}

			}
		});

	}

	public void setCurrentDateNTime() {
		Calendar c = Calendar.getInstance();
		yearNow = c.get(Calendar.YEAR);
		monthNow = c.get(Calendar.MONTH);
		dayNow = c.get(Calendar.DAY_OF_MONTH);
		hour24Now = c.get(Calendar.HOUR_OF_DAY);
		minuteNow = c.get(Calendar.MINUTE);
		dpBirth.init(yearNow, monthNow, dayNow, null);
		tpBirth.setCurrentHour(hour24Now);
		tpBirth.setCurrentMinute(minuteNow);

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

			tv5.setText(passDate(year, month, day));
			dpBirth.init(year, month, day, null);

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

			tv6.setText(passTime(hour24, minute));
			tpBirth.setCurrentHour(hour24);
			tpBirth.setCurrentMinute(minute);
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
	public boolean onCreateOptionsMenu(Menu menu) {
		MenuItem m1 = menu.add(1, 1, 1, "About");
		MenuItem m2 = menu.add(1, 2, 2, "Developers");
		return super.onCreateOptionsMenu(menu);
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
		case Warning_Dialog:

			AlertDialog.Builder warning = new AlertDialog.Builder(this);
			warning.setTitle("Are You Sure");
			warning.setIcon(R.drawable.warning);
			warning.setMessage("It will send a regular SMS. Operator rates will apply");

			warning.setCancelable(false);
			warning.setPositiveButton("Ok", new OnClickListener() {

				@Override
				public void onClick(DialogInterface arg0, int arg1) {
					r1.setVisibility(View.VISIBLE);
					check_karo = true;

				}
			});
			warning.setNegativeButton("Cancel", new OnClickListener() {

				@Override
				public void onClick(DialogInterface arg0, int arg1) {
					ck1.setChecked(false);
					check_karo = false;
				}
			});

			return warning.create();
		case SHOW_DATE_DIALOG:

			return new DatePickerDialog(this, datePickerListener, yearNow,
					monthNow, dayNow);
		case SHOW_TIME_DIALOG:
			return new TimePickerDialog(this, timePickerListener, hour24Now,
					minuteNow, false);

		case Invalid_Dialog:

			AlertDialog.Builder invalid = new AlertDialog.Builder(this);
			invalid.setTitle("Invalid Name");
			invalid.setMessage("Please enter Name");

			return invalid.create();

		case Invalid_Dialog1:

			AlertDialog.Builder invalid1 = new AlertDialog.Builder(this);
			invalid1.setTitle("Invalid Name/Greetings/Number");
			invalid1.setMessage("Please enter Name/Greetings/Number");

			return invalid1.create();

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

	public void Add_Birth(View v) {

		// Calendar c = Calendar.getInstance();
		// yearNow = c.get(Calendar.YEAR);
		// monthNow = c.get(Calendar.MONTH);
		// dayNow = c.get(Calendar.DAY_OF_MONTH);
		// hour24Now = c.get(Calendar.HOUR_OF_DAY);
		// minuteNow = c.get(Calendar.MINUTE);

		name = edName.getText().toString();
		desc = edDesc.getText().toString();
		number = edNo.getText().toString();
		String hint6 = tv5.getText().toString();
		String hint7 = tv6.getText().toString();

		if (check_karo == false && name.equals("")) {
			showDialog(Invalid_Dialog);
		} else if ((hint6.equals("")) || (hint7.equals(""))) {
			showDialog(Invalid_Date_Time);

		} else if ((year < yearNow)) {
			showDialog(Invalid_Date_Time);
		} else if ((year == yearNow) && (month < monthNow + 1)) {
			showDialog(Invalid_Date_Time);

		} else if ((year == yearNow) && (month == monthNow + 1)
				&& (day < dayNow)) {
			showDialog(Invalid_Date_Time);

		}

		else if ((year == yearNow) && (month == monthNow + 1)
				&& (day == dayNow) && (hour24 < hour24Now)) {
			showDialog(Invalid_Date_Time);

		} else if ((year == yearNow) && (month == monthNow+1) && (day == dayNow)
				&& (hour24 == hour24Now) && (minute < minuteNow)) {
			showDialog(Invalid_Date_Time);

		} else {
			if (check_karo == true
					&& (number.equals("") || desc.equals("") || name.equals(""))) {
				showDialog(Invalid_Dialog1);
			}

			else {

				FileName = BirthDay + "_" + year + "_" + monthStr + "_"
						+ dayStr + "_" + hour24Str + "_" + minuteStr;
				spStore = getSharedPreferences(FileName, MODE_PRIVATE);
				spedStore = spStore.edit();

				spId = getSharedPreferences("SaveValueOfIBirth", MODE_PRIVATE);
				spedId = spId.edit();

				i_Birth = spId.getInt("i", 0);

				spedStore.putString("Name", name);
				spedStore.putString("Desc", desc);
				spedStore.putString("Number", number);
				spedStore.putString("Date", date);
				spedStore.putString("Time", time);
				spedStore.putBoolean("Check", check_karo);
				spedStore.putInt("Year", year);
				spedStore.putInt("Month", month);
				spedStore.putInt("Day", day);
				spedStore.putInt("Hour", hour24);
				spedStore.putInt("Minute", minute);

				spedStore.commit();

				Birthday = new Intent(getBaseContext(), Task_Sms.class);

				spName = getSharedPreferences("SaveFileNamesBirthDay",
						MODE_APPEND);
				spedName = spName.edit();

				spedName.putString("FileName" + i_Birth, FileName);
				spedName.commit();

				i_Birth++;
				spedId.putInt("i", i_Birth);
				spedId.commit();

				startService(Birthday);

				// Toast.makeText(getBaseContext(), FileName+" Created",
				// Toast.LENGTH_SHORT).show();

				Toast.makeText(getBaseContext(), "Birthday Added",
						Toast.LENGTH_LONG).show();

			}

		}

	}

}
