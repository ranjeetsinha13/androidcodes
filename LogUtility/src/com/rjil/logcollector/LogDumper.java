
package com.rjil.logcollector;

import android.os.Environment;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileFilter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Date;

/**
 * Utility Class to dump the logs to a file in /mnt/sdcard/.logs location
 *
 * @author ranjeet.sinha
 */

public class LogDumper {

    static final SimpleDateFormat LOG_FILE_FORMAT = new SimpleDateFormat("yyyy-MM-dd-HH-mm-ssZ");

    private static final String TAG = "LogDumper";

    final File path = new File(Environment.getExternalStorageDirectory(), ".logs/"
            + LOG_FILE_FORMAT.format(new Date()));

    public synchronized void dump(String bufferType) throws IOException {

        BufferedReader br = null;
        BufferedWriter bw = null;
        int count = 0;
        Process p = null;
        File file = new File(path + "/logcat." + bufferType + count + ".txt");

        try {

            // logcat -d -v brief -b main *:V
            p = Runtime.getRuntime().exec(new String[] {
                    "logcat", "-b", bufferType, "*:V"
            });

            if (!path.exists()) {
                path.mkdirs();

            }
            String line = "";

            br = new BufferedReader(new InputStreamReader(p.getInputStream()));

            bw = new BufferedWriter(new FileWriter(file, true));

            while ((line = br.readLine()) != null) {
                if (line.contains("CheckAVSyncError") == false) {
                    bw.write(line);
                    bw.write('\n');
                }

                if (file.exists() && file.length() >= 250 * 1024) {

                    file = new File(path + "/logcat." + bufferType + ++count + ".txt");
                    file.createNewFile();
                    bw.flush();
                    bw.close();
                    bw = new BufferedWriter(new FileWriter(file, true));

                } else {
                    if (!file.exists())
                        file.createNewFile();
                }

            }

        } finally {

            if (br != null)
                br.close();
            if (bw != null)
                bw.close();
            if (p != null) {
                p.destroy();
            }

        }
    }

    /**
     * delete the older logs directory
     */
    public void deleteLogDirs() {
        File directory = new File(Environment.getExternalStorageDirectory().getPath());
        File[] files = directory.listFiles(new FileFilter() {

            @Override
            public boolean accept(File file) {
                if (file.getName().contains("logs") && file.isHidden())
                    return true;
                return false;
            }
        });

        File[] logsDirs = null;
        if (files != null && files.length > 0)
            logsDirs = files[0].listFiles();
        if (logsDirs != null && logsDirs.length > 2) {
            Arrays.sort(logsDirs, new Comparator<File>() {
                public int compare(File f1, File f2) {
                    return Long.valueOf(f1.lastModified()).compareTo(f2.lastModified());
                }
            });

            for (int i = 0; i < logsDirs.length - 2; i++) {
                try {

                    delete(logsDirs[i]);
                } catch (IOException e) {

                    e.printStackTrace();
                }
            }
        }
    }

    /**
     * utility method to delete a directory
     *
     * @param file
     * @throws IOException
     */

    private void delete(File file) throws IOException {

        if (file.isDirectory()) {

            // directory is empty, then delete it

            if (file.list().length == 0) {
                file.delete();
            } else {

                String files[] = file.list();

                for (String temp : files) {
                    // construct the file structure
                    File fileDelete = new File(file, temp);
                    fileDelete.delete();
                }

                // check the directory again, if empty then delete it
                if (file.list().length == 0) {
                    file.delete();

                }
            }

        } else {
            // if file, then delete it
            file.delete();

        }
    }
}
