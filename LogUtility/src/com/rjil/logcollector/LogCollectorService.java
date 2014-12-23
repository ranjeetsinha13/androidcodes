
package com.rjil.logcollector;

import android.app.Service;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.IBinder;
import android.util.Log;

import java.io.IOException;

/**
 * Service to be started on device boot up to write logs to a file
 *
 * @author ranjeet.sinha
 */
 
 ////http://stackoverflow.com/questions/6854127/filter-logcat-to-get-only-the-messages-from-my-application-in-android
public class LogCollectorService extends Service {

    private LogDumper mLogDumper;

    @Override
    public IBinder onBind(Intent intent) {

        return null;
    }

    @Override
    public void onCreate() {

        super.onCreate();
        Log.d("Service ", "service created");

        mLogDumper = new LogDumper();
        new LogOperationMain().execute();
        new LogOperationSystem().execute();
    }

    private class LogOperationMain extends AsyncTask<Void, Void, Void> {
        @Override
        protected Void doInBackground(Void... params) {

            try {
                mLogDumper.deleteLogDirs();
                mLogDumper.dump("main");
            } catch (IOException e) {

                e.printStackTrace();
            }

            return null;

        }

    }

    private class LogOperationSystem extends AsyncTask<Void, Void, Void> {
        @Override
        protected Void doInBackground(Void... params) {

            try {
                mLogDumper.dump("system");
            } catch (IOException e) {

                e.printStackTrace();
            }

            return null;

        }

    }

}
