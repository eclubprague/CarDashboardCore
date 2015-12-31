package com.eclubprague.cardashboard.core.obd;

import android.content.Context;
import android.util.Log;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStreamWriter;

public class ObdLogWritter {

    BufferedWriter writer;
    private static final String TAG = ObdLogWritter.class.getSimpleName();

    public ObdLogWritter(Context c, String filename) {
        File file = new File(c.getFilesDir(), filename);
        Log.d(TAG, file.getAbsolutePath());
        try {
            writer = new BufferedWriter(new FileWriter(file));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void write(ObdCommandJob in) throws IOException {
        if (writer == null) throw new IOException();
        writer.write(in.getCommand().getName() + ";" + in.getCommand().getCalculatedResult() + ";" + in.getCommand().getResultUnit());
        Log.d(TAG, in.getCommand().getName() + ";" + in.getCommand().getCalculatedResult() + ";" + in.getCommand().getResultUnit());
        writer.write("\n");
    }


    public void close() throws IOException {
        if (writer == null) throw new IOException();
        writer.flush();
        writer.close();
    }
}
