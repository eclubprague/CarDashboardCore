package com.eclubprague.cardashboard.core.obd;

import android.content.Context;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStreamWriter;

public class ObdLogWritter {

    BufferedWriter writer;

    public ObdLogWritter(Context c, String filename) {
        File file = new File(c.getFilesDir() + "/logs/", filename);
        try {
            file.createNewFile();
            writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(file)));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void write(ObdCommandJob in) throws IOException {
        if (writer == null) throw new IOException();
        writer.write(in.getCommand().getName() + ";" + in.getCommand().getCalculatedResult() + ";" + in.getCommand().getResultUnit());
        writer.write("\n");
    }


    public void close() throws IOException {
        if (writer == null) throw new IOException();
        writer.flush();
        writer.close();
    }
}
