package com.example.todo;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;

import android.R.integer;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.Service;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.IBinder;
import android.telephony.gsm.SmsManager;
import android.widget.Toast;

public class Task_Meeting extends Service {

	@Override
	public IBinder onBind(Intent arg0) {
		// TODO Auto-generated method stub
		return null;
	}

	

//	SmsManager sms_manager ;
	int j,i_Meeting,k,l;
	SharedPreferences sp,sp1,spId;
	SharedPreferences.Editor sped;
	NotificationManager nmNew;
	Timer timerNew;
	Date today;
	int year;
	int month;
	int day;
	int hour;
	int minute;
	Notification nNew;
	String title;
	String desc,place;
	String s1,s2,temp,use;
	int yearCal,monthCal,dayCal,hourCal,secondCal,	minuteCal;
//	ArrayList<String> FileNames = new ArrayList<String>();
//	String dbName = "Files";
//	int match;
//	
	
	
	
	@Override
	public int onStartCommand(Intent intent, int flags, int startId) {

		
//		today = new 
		
	//	Toast.makeText(getBaseContext(), "Service Started", Toast.LENGTH_LONG);
		
//		sms_manager = SmsManager.getDefault();
		
		sp = getSharedPreferences("SaveFileNamesMeeting", MODE_APPEND);
//		sped = sp.edit();
		
		
		
		spId = getSharedPreferences("SaveValueOfIMeeting", MODE_PRIVATE);
		i_Meeting = spId.getInt("i", 0);
		
//		Toast.makeText(getBaseContext(), i_Meeting+"", Toast.LENGTH_LONG).show();
		
////		spName = getSharedPreferences("SaveFileNames", MODE_PRIVATE);
////		
//		s1 = spName.getString("FileName", null);
		

		
//		FileNames.add(s1);
//		Toast.makeText(getBaseContext(), ""+FileNames.size(),
//				Toast.LENGTH_LONG).show();
		
		
	
		nmNew = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);

		timerNew = new Timer();

		TimerTask tTask = new TimerTask() {

			@Override
			public void run() {
				
				for (k = 0; k<i_Meeting ; k++)
				{
					
					temp = sp.getString("FileName"+k, null);
					//getSubString(temp);
					
			sp1 = getSharedPreferences(temp, MODE_PRIVATE);
					
					year=sp1.getInt("Year", 0);
					month=sp1.getInt("Month", 0);
					day=sp1.getInt("Day", 0);
					hour=sp1.getInt("Hour", 0);
					minute=sp1.getInt("Minute", 0);
					
					
					Calendar c = Calendar.getInstance();
					yearCal = c.get(Calendar.YEAR);
					monthCal = c.get(Calendar.MONTH);
					dayCal = c.get(Calendar.DAY_OF_MONTH);
					hourCal = c.get(Calendar.HOUR_OF_DAY);
					minuteCal = c.get(Calendar.MINUTE);
					secondCal = c.get(Calendar.SECOND);
					
					if (year == yearCal)
					{
						if (month == monthCal+1) 
						{
							if (day == dayCal)
							{
								if (hour == hourCal) 
								{
									if (minute == minuteCal)
									{
										//if(secondCal==0)
										//{
											title=sp1.getString("Name", "null");
											desc = sp1.getString("Desc", "null");
											place = sp1.getString("Place", "null");
											nNew = new Notification(R.drawable.info, "You Have a Meeting "+title+" at "+place,
													System.currentTimeMillis());
											nNew.defaults = Notification.DEFAULT_SOUND;
											
											nNew.setLatestEventInfo(getBaseContext(), "You Have a Meeting "+title+" at "+place, desc,
													null);
											
											nmNew.notify(j++, nNew);
											
											
											 
//											if(sms_manager == null)
//											{
//												Toast.makeText(getBaseContext(), "Device Not Supported", Toast.LENGTH_LONG).show();
//												
//											}
//											else {
//												sms_manager.sendTextMessage(number, null, desc, null, null);
//											}
											
										//}
									}
								}
								
							}
						}
					}
				}
			
				

				
			}

			
		};

		timerNew.scheduleAtFixedRate(tTask, 0,  60*1000);
		
//		Toast.makeText(getBaseContext(), year+"/"+month+"/"+day+"/"+hour+"/"+minute, Toast.LENGTH_LONG).show();
		
		
		//db.close();
		return START_STICKY;
	}
//	public void getSubString(String temp) 
//	{
//		
//		year = Integer.parseInt(temp.substring(8, 12));
//		month = Integer.parseInt(temp.substring(13, 15));
//		day = Integer.parseInt(temp.substring(16, 18));
//		hour = Integer.parseInt(temp.substring(19, 21));
//		minute = Integer.parseInt(temp.substring(22));
//		
//		
//		
//		
//	}

	@Override
	public void onDestroy() {
		// TODO Auto-generated method stub

		Toast.makeText(getBaseContext(), "Destroyed", Toast.LENGTH_LONG).show();
		super.onDestroy();
	}

}
