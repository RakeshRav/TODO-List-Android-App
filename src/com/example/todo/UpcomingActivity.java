package com.example.todo;

import android.os.Bundle;
import android.app.ActionBar;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

public class UpcomingActivity extends Activity {

	Intent upNewTask, upBirthDay,upMeeting;
	ActionBar ab;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_upcoming);
		ab = getActionBar();

		ColorDrawable cd = new ColorDrawable();
		cd.setColor(Color.rgb(0, 123, 213));

		ab.setBackgroundDrawable(cd);

		ab.setTitle("To Do");
		ab.setHomeButtonEnabled(true);
		ab.setDisplayHomeAsUpEnabled(true);
		ab.setIcon(R.drawable.todoicon1);

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

	public void UpcomingTask(View v) {
		upNewTask = new Intent(getBaseContext(), UpcomingNewTaskActivity.class);

		startActivity(upNewTask);
	}

	public void UpcomingBirthday(View v) {
		upBirthDay = new Intent(getBaseContext(),
				UpcomingBirthdayActivity.class);

		startActivity(upBirthDay);
	}

	public void UpcomingMeeting(View v) {
		upMeeting = new Intent(getBaseContext(),
				UpcomingMeetingActivity.class);

		startActivity(upMeeting);
	}
}
