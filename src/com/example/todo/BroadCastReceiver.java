package com.example.todo;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

public class BroadCastReceiver extends BroadcastReceiver 
{

	@Override
	public void onReceive(Context arg0, Intent arg1)
	{
		Intent NewTask = new Intent(arg0, Task_New.class);
		arg0.startService(NewTask);

		Intent BirthDay = new Intent(arg0, Task_Sms.class);
		arg0.startService(BirthDay);
		
		Intent Meeting = new Intent(arg0, Task_Meeting.class);
		arg0.startService(Meeting);		
		
	}

}
