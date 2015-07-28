package com.example.todo;

import java.util.Calendar;

import android.R.layout;
import android.R.string;
import android.os.Bundle;
import android.app.ActionBar;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.app.Dialog;
import android.app.Notification;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnLongClickListener;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.RelativeLayout.LayoutParams;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

public class UpcomingNewTaskActivity extends Activity {

	RelativeLayout relative_layout, relative_layout1;
	RelativeLayout rl;
	String title, desc;
	ScrollView sc;
	TextView tv1, tv2, tv3, tv4, tv5;

	int idCurr, idPrev, getid;
	int year;
	int month;
	int day;
	int hour;
	int minute;
	int yearCal, monthCal, dayCal, hourCal, secondCal, minuteCal;
	String temp;
	SharedPreferences sp, sp1, spId,spcopy,spcopy1;
	SharedPreferences.Editor sped,spedCopy,spedCopy1;

	int i, j, k, a,z,increase,increase1,increase2, prev, curr;
	ActionBar ab;
	static String[] filenames;

	static int[] yearArr, monthArr, dayArr, hourArr, secondArr, minuteArr;
	String time, date;
	ColorDrawable cd;
	final int Delete_Dialog = 999;
	final int r_u_sure = 998;

	CharSequence[] Delete = { "Delete" };

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_upcoming_new_task);

		prev = 1000;
		cd = new ColorDrawable();
		cd.setColor(Color.rgb(0, 123, 213));

		ab = getActionBar();

		// ab.setBackgroundDrawable(cd);

		ab.setBackgroundDrawable(new ColorDrawable(Color.rgb(0, 123, 213)));
		ab.setTitle("Upcoming Task");
		ab.setHomeButtonEnabled(true);
		ab.setDisplayHomeAsUpEnabled(true);
		ab.setIcon(R.drawable.todoicon1);

		sp = getSharedPreferences("SaveFileNames", MODE_APPEND);
		sped = sp.edit();

		
		spcopy = getSharedPreferences("SaveFileNamesCopy", MODE_APPEND);
		spedCopy = spcopy.edit();
		
		
		spcopy1 = getSharedPreferences("SaveFileNamesCopy1", MODE_APPEND);
		spedCopy1= spcopy1.edit();
		
		
		spId = getSharedPreferences("SaveValueOfI", MODE_PRIVATE);
		i = spId.getInt("i", 0);
		
		
		for (int k = 0; k < i; k++)
		{
			
			
			temp =  sp.getString("FileName"+k, "null");
			if (!(temp.equals("null"))) {

				spedCopy.putString("FileName"+increase, temp);
				spedCopy.commit();
				
				increase++;
			}
		}
		
		
		
		relative_layout = (RelativeLayout) findViewById(R.id.relat1);

		Calendar c = Calendar.getInstance();
		yearCal = c.get(Calendar.YEAR);
		monthCal = c.get(Calendar.MONTH) + 1;
		dayCal = c.get(Calendar.DAY_OF_MONTH);
		hourCal = c.get(Calendar.HOUR_OF_DAY);
		minuteCal = c.get(Calendar.MINUTE);
		secondCal = c.get(Calendar.SECOND);
		// Toast.makeText(getBaseContext(),
		// "year "+yearCal+"mon "+monthCal+"day "+dayCal+"hour "+hourCal+"min "+minuteCal,
		// Toast.LENGTH_LONG).show();

		yearArr = new int[increase];

		monthArr = new int[increase];

		dayArr = new int[increase];
		hourArr = new int[increase];
		minuteArr = new int[increase];
		filenames = new String[increase];
		// rl = new RelativeLayout[i];

		for (int k = 0; k < increase; k++) {

			temp = spcopy.getString("FileName" + k, "null");

//			if (!(temp.equals("null"))) {

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

//				a++;
//			}

		}

		sort(minuteArr);
		sort(hourArr);
		sort(dayArr);
		sort(monthArr);
		sort(yearArr);
		

		
//		for (int k = 0; k < increase; k++) {
//			
//			
//			spedCopy.putString("FileName"+k, filenames[k]);
//			spedCopy.commit();
//		}
		
		
		

		for (k = 0; k < increase; k++) {

			// if (!(filenames[k].equals(null))) {
			// temp = sp.getString(filenames[k], null);
			sp1 = getSharedPreferences(filenames[k], MODE_PRIVATE);

			year = sp1.getInt("Year", 0);
			month = sp1.getInt("Month", 0);
			day = sp1.getInt("Day", 0);
			hour = sp1.getInt("Hour", 0);
			minute = sp1.getInt("Minute", 0);
			// Toast.makeText(getBaseContext(),
			// "year "+year+"mon "+month+"day "+day+"hour "+hour+"min "+minute,
			// Toast.LENGTH_LONG).show();

			// Toast.makeText(getBaseContext(), "" + temp,
			// Toast.LENGTH_LONG)
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
		}

	}

	//
	// }

	public void setList() {

		
		spedCopy1.putString("FileName"+z, filenames[k]);
		spedCopy1.commit();
		z++;
		
		date = sp1.getString("Date", "null");
		time = sp1.getString("Time", "null");
		title = sp1.getString("Name", "null");
		desc = sp1.getString("Desc", "null");

		idCurr = idPrev + 1;

		relative_layout1 = new RelativeLayout(getApplicationContext());
		relative_layout1.setId(idCurr);

		RelativeLayout.LayoutParams paramsll = new RelativeLayout.LayoutParams(
				RelativeLayout.LayoutParams.MATCH_PARENT,
				RelativeLayout.LayoutParams.WRAP_CONTENT);
		paramsll.addRule(RelativeLayout.BELOW, idPrev);
		relative_layout1.setLayoutParams(paramsll);

		idPrev = idCurr;

		relative_layout1.setLongClickable(true);
		relative_layout1.setClickable(true);
		relative_layout1.setOnLongClickListener(new OnLongClickListener() {

			@Override
			public boolean onLongClick(View arg0) {
				getid = arg0.getId();
				showDialog(Delete_Dialog);

				return false;
			}
		});

		// 1

		tv1 = new TextView(this);
		tv1.setText("Task :" + " " + title);
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

		relative_layout1.addView(tv1, params1);

		// 2

		tv2 = new TextView(this);

		curr = prev + 1;

		tv2.setId(curr);
		tv2.setText("Desc :" + " " + desc);
		tv2.setTextSize(20);
		tv2.setTextColor(Color.rgb(0, 123, 213));
		tv2.setPadding(10, 5, 0, 0);

		RelativeLayout.LayoutParams params2 = new RelativeLayout.LayoutParams(
				RelativeLayout.LayoutParams.FILL_PARENT,
				RelativeLayout.LayoutParams.WRAP_CONTENT);

		params2.addRule(RelativeLayout.BELOW, prev);

		tv2.setLayoutParams(params2);

		prev = curr;

		relative_layout1.addView(tv2, params2);

		// 3
		tv3 = new TextView(this);

		curr = prev + 1;

		tv3.setId(curr);
		tv3.setText("Date :" + " " + date);
		tv3.setTextSize(20);
		tv3.setTextColor(Color.rgb(0, 123, 213));
		tv3.setPadding(10, 5, 0, 0);

		RelativeLayout.LayoutParams params3 = new RelativeLayout.LayoutParams(
				RelativeLayout.LayoutParams.FILL_PARENT,
				RelativeLayout.LayoutParams.WRAP_CONTENT);

		params3.addRule(RelativeLayout.BELOW, prev);

		tv3.setLayoutParams(params3);

		prev = curr;

		relative_layout1.addView(tv3, params3);

		// 4
		tv4 = new TextView(this);

		curr = prev + 1;

		tv4.setId(curr);
		tv4.setText("Time:" + " " + time);
		tv4.setTextSize(20);
		tv4.setTextColor(Color.rgb(0, 123, 213));
		tv4.setPadding(10, 5, 0, 5);

		// tv4.setPadding(left, top, right, bottom)
		RelativeLayout.LayoutParams params4 = new RelativeLayout.LayoutParams(
				RelativeLayout.LayoutParams.FILL_PARENT,
				RelativeLayout.LayoutParams.WRAP_CONTENT);

		params4.addRule(RelativeLayout.BELOW, prev);

		tv4.setLayoutParams(params4);

		prev = curr;

		relative_layout1.addView(tv4, params4);

		// 5

		tv5 = new TextView(this);
		curr = prev + 1;

		tv5.setId(curr);
		tv5.setText("");
		tv5.setTextSize(20);
		tv5.setPadding(10, 10, 0, 0);

		tv5.setBackgroundDrawable(cd);
		RelativeLayout.LayoutParams params5 = new RelativeLayout.LayoutParams(
				RelativeLayout.LayoutParams.MATCH_PARENT, 3);

		params5.addRule(RelativeLayout.BELOW, prev);

		tv5.setLayoutParams(params5);

		prev = curr;

		relative_layout1.addView(tv5, params5);

		relative_layout.addView(relative_layout1, paramsll);
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

		case Delete_Dialog:

			AlertDialog.Builder delete = new AlertDialog.Builder(this);
			delete.setTitle("");
			delete.setItems(Delete, new DialogInterface.OnClickListener() {

				@Override
				public void onClick(DialogInterface arg0, int arg1) {

					// Toast.makeText(getBaseContext(), ""+arg1,
					// Toast.LENGTH_LONG).show();
					if (arg1 == 0) {

						showDialog(r_u_sure);
					}
				}
			});

			return delete.create();

		case r_u_sure:

			AlertDialog.Builder Sure = new AlertDialog.Builder(this);
			Sure.setTitle("Are You Sure ??");
			Sure.setMessage("Do you want to delete this task");
			Sure.setIcon(R.drawable.warning);
			Sure.setPositiveButton("Delete", new OnClickListener() {

				@Override
				public void onClick(DialogInterface arg0, int arg1) {
					// TODO Auto-generated method stub

					Remove();
				}
			});

			Sure.setNegativeButton("Cancel", new OnClickListener() {

				@Override
				public void onClick(DialogInterface arg0, int arg1) {
					// TODO Auto-generated method stub

				}
			});

			return Sure.create();

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

	public void sort(int[] temp) {

		int x, y;
		int swap;
		String swap1;

		for (x = 0; x < (increase - 1); x++) {
			for (y = 0; y < (increase - x - 1); y++) {

				if (temp[y] > temp[y + 1]) {

					swap = minuteArr[y];
					minuteArr[y] = minuteArr[y + 1];
					minuteArr[y + 1] = swap;

					swap = hourArr[y];
					hourArr[y] = hourArr[y + 1];
					hourArr[y + 1] = swap;

					swap = dayArr[y];
					dayArr[y] = dayArr[y + 1];
					dayArr[y + 1] = swap;

					swap = monthArr[y];
					monthArr[y] = monthArr[y + 1];
					monthArr[y + 1] = swap;

					swap = yearArr[y];
					yearArr[y] = yearArr[y + 1];
					yearArr[y + 1] = swap;

					swap1 = filenames[y];
					filenames[y] = filenames[y + 1];
					filenames[y + 1] = swap1;

				}
			}

		}

		// TODO Auto-generated method stub

	}

	public void Remove() {

		Toast.makeText(getBaseContext(), "" + getid, Toast.LENGTH_LONG).show();
		int rm;
		rm = getid - 1;
		spedCopy1.remove("FileName" + rm);
		spedCopy1.commit();

		filenames[rm] = "null";

		
//		for (int j = 0; j < a - 1; j++) {
//
//			if (!(filenames[j].equals("null")))
//			{
//				sped.putString("FileName" + k, filenames[j]);
//				sped.commit();
//				k++;
//			}
//
//		}

			
		
//		for (int k = 0; k < increase1; k++)
//		{
//			
//			
//			temp =  sp.getString("FileName"+k, "null");
//			if (!(temp.equals("null"))) {
//
//				spedCopy.putString("FileName"+increase2, temp);
//				spedCopy.commit();
//				
//				increase2++;
//			}
//		}
	}
	
	
	@Override
	protected void onPause() {

		super.onPause();
		
		
		sped.clear();
		sped.commit();

		
		
		for (int k = 0; k < increase; k++)
		{
			
			
			temp =  spcopy1.getString("FileName"+k, "null");
			if (!(temp.equals("null"))) {

				sped.putString("FileName"+increase1, temp);
				sped.commit();
				
				increase1++;
			}
		}
		
		
		
		spedCopy1.clear();
		spedCopy1.commit();
		
		
		spedCopy.clear();
		spedCopy.commit();

	}

}
