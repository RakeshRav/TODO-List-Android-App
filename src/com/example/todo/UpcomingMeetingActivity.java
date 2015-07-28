package com.example.todo;

import java.util.Calendar;

import android.R.layout;
import android.R.string;
import android.os.Bundle;
import android.app.ActionBar;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.app.Notification;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.RelativeLayout.LayoutParams;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

public class UpcomingMeetingActivity extends Activity {

	RelativeLayout relative_layout, relative_layout1, relative_layout2,
			relative_layout3, relative_layout4;
	LinearLayout ll;
	LinearLayout llh[];
	String title, desc;
	ScrollView sc;
	TextView tv1, tv2, tv3, tv4, tv5, tv6;
	int year;
	int month;
	int day;
	int hour;
	int minute;
	int yearCal, monthCal, dayCal, hourCal, secondCal, minuteCal, a;
	String temp;
	SharedPreferences sp, sp1, spId;
	int i, j, k, prev, curr;
	ActionBar ab;
	String time, date, place;
	ColorDrawable cd;
static	String[] filenames;
	
	static int[] yearArr, monthArr, dayArr, hourArr, secondArr, minuteArr;

	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_upcoming_new_task);

		 cd = new ColorDrawable();
		cd.setColor(Color.rgb(0, 123, 213));

		ab = getActionBar();

		// ab.setBackgroundDrawable(cd);

		ab.setTitle("Upcoming Meetings");
		ab.setBackgroundDrawable(new ColorDrawable(Color.rgb(0, 123, 213)));

		ab.setHomeButtonEnabled(true);
		ab.setDisplayHomeAsUpEnabled(true);
		ab.setIcon(R.drawable.todoicon1);

		sp = getSharedPreferences("SaveFileNamesMeeting", MODE_APPEND);

		spId = getSharedPreferences("SaveValueOfIMeeting", MODE_PRIVATE);
		i = spId.getInt("i", 0);

		relative_layout = (RelativeLayout) findViewById(R.id.relat1);

		Calendar c = Calendar.getInstance();
		yearCal = c.get(Calendar.YEAR);
		monthCal = c.get(Calendar.MONTH)+1;
		dayCal = c.get(Calendar.DAY_OF_MONTH);
		hourCal = c.get(Calendar.HOUR_OF_DAY);
		minuteCal = c.get(Calendar.MINUTE);
		secondCal = c.get(Calendar.SECOND);

		yearArr = new int[i];

		monthArr = new int[i];

		dayArr = new int[i];
		hourArr = new int[i];
		minuteArr= new int[i];
		filenames = new String[i];
		
		for (int k = 0; k < i; k++) {
			

			temp = sp.getString("FileName" + k, null);
			sp1 = getSharedPreferences(temp, MODE_PRIVATE);

			year = sp1.getInt("Year", 0);
			month = sp1.getInt("Month", 0);
			day = sp1.getInt("Day", 0);
			hour = sp1.getInt("Hour", 0);
			minute = sp1.getInt("Minute", 0);

			yearArr[k] = year;
			monthArr[k] = month;
			dayArr[k] = day;
			hourArr[k] = hour;
			minuteArr[k] = minute;
			filenames[k] = temp;
			
			
		}
		
		
		sort(minuteArr);
		sort(hourArr);
		sort(dayArr);
		sort(monthArr);
		sort(yearArr);
		
		

		
		
		
		
		
		
		
		for (k = 0; k < i; k++) {

//			temp = sp.getString("FileName" + k, null);
			sp1 = getSharedPreferences(filenames[k], MODE_PRIVATE);

			year = sp1.getInt("Year", 0);
			month = sp1.getInt("Month", 0);
			day = sp1.getInt("Day", 0);
			hour = sp1.getInt("Hour", 0);
			minute = sp1.getInt("Minute", 0);

			// Toast.makeText(getBaseContext(), "" + temp, Toast.LENGTH_LONG)
			// .show();
			//
			if ((year == yearCal)) {
				if ((month == monthCal)) {
					if ((day == dayCal)) {
						if ((hour == hourCal)) {
							if ((minute == minuteCal) || (minute > minuteCal)) {
								setList();
							}

						} else if (hour > hourCal) {
							setList();
						}

					} else if (day > dayCal) {
						setList();
					}

				} else if (month > monthCal) {
					setList();
				}
			} else if (year > yearCal) {
				setList();
			}

			// Toast.makeText(getBaseContext(), "" + temp, Toast.LENGTH_LONG)
			// .show();
			//

		}
	}

	public void setList() {
		
		
		date = sp1.getString("Date", "null");
		time = sp1.getString("Time", "null");
		title = sp1.getString("Name", "null");
		desc = sp1.getString("Desc", "null");
		place = sp1.getString("Place", "null");

		// 1

		tv1 = new TextView(this);
		tv1.setText("Title : " + title);
		tv1.setTextSize(20);
		tv1.setPadding(10, 10, 0, 0);
		curr = prev + 1;

		tv1.setTextColor(Color.rgb(0, 123, 213));
		tv1.setId(curr);

		RelativeLayout.LayoutParams params1 = new RelativeLayout.LayoutParams(
				RelativeLayout.LayoutParams.FILL_PARENT,
				RelativeLayout.LayoutParams.WRAP_CONTENT);

		params1.addRule(RelativeLayout.BELOW, prev);

		tv1.setLayoutParams(params1);

		prev = curr;

		relative_layout.addView(tv1, params1);

		// 2
		tv2 = new TextView(this);

		curr = prev + 1;

		tv2.setId(curr);
		tv2.setText("Desc : " + desc);
		tv2.setTextSize(20);
		tv2.setTextColor(Color.rgb(0, 123, 213));
		tv2.setPadding(10, 5, 0, 0);

		RelativeLayout.LayoutParams params2 = new RelativeLayout.LayoutParams(
				RelativeLayout.LayoutParams.FILL_PARENT,
				RelativeLayout.LayoutParams.WRAP_CONTENT);

		params2.addRule(RelativeLayout.BELOW, prev);

		tv2.setLayoutParams(params2);

		prev = curr;

		relative_layout.addView(tv2, params2);

		// 3

		tv3 = new TextView(this);

		curr = prev + 1;

		tv3.setId(curr);
		tv3.setText("Place : " + place);
		tv3.setTextSize(20);
		tv3.setTextColor(Color.rgb(0, 123, 213));
		tv3.setPadding(10, 5, 0, 0);

		RelativeLayout.LayoutParams params3 = new RelativeLayout.LayoutParams(
				RelativeLayout.LayoutParams.FILL_PARENT,
				RelativeLayout.LayoutParams.WRAP_CONTENT);

		params3.addRule(RelativeLayout.BELOW, prev);

		tv3.setLayoutParams(params3);

		prev = curr;

		relative_layout.addView(tv3, params3);

		// 4
		tv4 = new TextView(this);

		curr = prev + 1;

		tv4.setId(curr);
		tv4.setText("Date:" + " " + date);
		tv4.setTextSize(20);
		tv4.setTextColor(Color.rgb(0, 123, 213));
		tv4.setPadding(10, 5, 0, 0);

		RelativeLayout.LayoutParams params4 = new RelativeLayout.LayoutParams(
				RelativeLayout.LayoutParams.FILL_PARENT,
				RelativeLayout.LayoutParams.WRAP_CONTENT);

		params4.addRule(RelativeLayout.BELOW, prev);

		tv4.setLayoutParams(params4);

		prev = curr;

		relative_layout.addView(tv4, params4);

		// 5
		tv5 = new TextView(this);

		curr = prev + 1;

		tv5.setId(curr);
		tv5.setText("Time:" + " " + time);
		tv5.setTextSize(20);
		tv5.setTextColor(Color.rgb(0, 123, 213));
		tv5.setPadding(10, 5, 0, 5);

		RelativeLayout.LayoutParams params5 = new RelativeLayout.LayoutParams(
				RelativeLayout.LayoutParams.FILL_PARENT,
				RelativeLayout.LayoutParams.WRAP_CONTENT);

		params5.addRule(RelativeLayout.BELOW, prev);

		tv5.setLayoutParams(params5);

		prev = curr;

		relative_layout.addView(tv5, params5);

		// 6

		tv6 = new TextView(this);
		curr = prev + 1;

		tv6.setId(curr);
		tv6.setText("");
		tv6.setTextSize(20);
		tv6.setPadding(10, 10, 0, 0);

		tv6.setBackgroundDrawable(cd);
		RelativeLayout.LayoutParams params6 = new RelativeLayout.LayoutParams(
				RelativeLayout.LayoutParams.MATCH_PARENT, 3);

		params6.addRule(RelativeLayout.BELOW, prev);

		tv6.setLayoutParams(params6);

		prev = curr;

		relative_layout.addView(tv6, params6);

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
					+ " Reminders are also provided to remi8nd your task as per the date and time given by you."
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
	
	public  void sort(int[] temp) {
		
		
		int x,y;
		int swap;
		String swap1;
		
		for ( x = 0; x < (i-1); x++)
		{
			for (y = 0; y < (i-x-1); y++) {
				
				
				if (temp[y]>temp[y+1])
				{
					
					swap = minuteArr[y];
					minuteArr[y] = minuteArr[y+1];
					minuteArr[y+1] = swap;
					
					swap = hourArr[y];
					hourArr[y] = hourArr[y+1];
					hourArr[y+1] = swap;
					
					swap = dayArr[y];
					dayArr[y] = dayArr[y+1];
					dayArr[y+1] = swap;
					
					swap = monthArr[y];
					monthArr[y] = monthArr[y+1];
					monthArr[y+1] = swap;
					
					swap = yearArr[y];
					yearArr[y] = yearArr[y+1];
					yearArr[y+1] = swap;
					
					swap1 = filenames[y];
					filenames[y] = filenames[y+1];
					filenames[y+1] = swap1;
					
					
				}
			}
			
		}
		
		
		
		// TODO Auto-generated method stub
		
	}

}
