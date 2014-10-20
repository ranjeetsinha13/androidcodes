
package com.rjil.logcollector;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

/**
 * Broadcast receiver to receive boot completed and start LogCollector service
 *
 * @author ranjeet.sinha
 */

public class DeviceBroadcastReceiver extends BroadcastReceiver {

    private static final String TAG = "DeviceBroadcastReceiver";

    @Override
    public void onReceive(Context context, Intent intent) {
        final String action = intent.getAction();

        Log.d(TAG, "inside on receive " + action);

        if (action != null && action.equals(Intent.ACTION_BOOT_COMPLETED)) {

            context.startService(new Intent(context, LogCollectorService.class));

        }
    }

}
