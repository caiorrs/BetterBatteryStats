/*
 * Copyright (C) 2011 asksven
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.asksven.betterbatterystats;


import java.util.logging.Logger;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.util.Log;


/**
 * General broadcast handler: handles event as registered on Manifest
 * @author sven
 *
 */
public class BroadcastHandler extends BroadcastReceiver
{	
	private static final String TAG = "BroadcastHandler";
	private BatteryChangedHandler m_batteryHandler = null;
	
	/* (non-Javadoc)
	 * @see android.content.BroadcastReceiver#onReceive(android.content.Context, android.content.Intent)
	 */
	@Override
	public void onReceive(Context context, Intent intent)
	{

 
        if (intent.getAction().equals(Intent.ACTION_BOOT_COMPLETED))
		{
			Log.i(TAG, "Received Broadcast ACTION_BOOT_COMPLETED");
			// todo: delete whatever references we have saved here
		}

        if (intent.getAction().equals(Intent.ACTION_POWER_CONNECTED))
		{
			Log.i(TAG, "Received Broadcast ACTION_POWER_CONNECTED, registering ACTION_BATTER_CHANGED");
			// egister the receiver for BATTERY_CHANGED to be informed when battery reaches full charge
			m_batteryHandler = new BatteryChangedHandler();
            context.registerReceiver(m_batteryHandler, new IntentFilter(Intent.ACTION_BATTERY_CHANGED));

		}

        if (intent.getAction().equals(Intent.ACTION_POWER_DISCONNECTED))
		{
			Log.i(TAG, "Received Broadcast ACTION_POWER_DISCONNECTED");
			// todo: store the "since unplugged" refs here
			
			// unregister the receiver for BATTERY_CHANGED as the battery is not charging anymore
			context.unregisterReceiver(m_batteryHandler);
		}

        if (intent.getAction().equals(Intent.ACTION_SCREEN_OFF))
		{
			Log.i(TAG, "Received Broadcast ACTION_SCREEN_OFF");
			// todo: store the "since screen off" refs here
		}

        if (intent.getAction().equals(Intent.ACTION_SCREEN_ON))
		{
			Log.i(TAG, "Received Broadcast ACTION_SCREEN_ON");
			// todo: evaluate what hapened while screen was off here
		}

	}
}