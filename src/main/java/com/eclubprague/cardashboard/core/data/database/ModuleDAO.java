package com.eclubprague.cardashboard.core.data.database;

import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.AsyncTask;
import android.util.Log;

import com.eclubprague.cardashboard.core.R;
import com.eclubprague.cardashboard.core.data.Constants;
import com.eclubprague.cardashboard.core.data.modules.ModuleCreationToolsMap;
import com.eclubprague.cardashboard.core.data.modules.ModuleLoader;
import com.eclubprague.cardashboard.core.model.resources.IconResource;
import com.eclubprague.cardashboard.core.model.resources.StringResource;
import com.eclubprague.cardashboard.core.modules.base.AbstractShortcutModule;
import com.eclubprague.cardashboard.core.modules.base.IModule;
import com.eclubprague.cardashboard.core.modules.base.IModuleContext;
import com.eclubprague.cardashboard.core.modules.base.IParentModule;
import com.eclubprague.cardashboard.core.utils.ErrorReporter;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.net.URISyntaxException;

/**
 * Created by Michael on 18.08.2015.
 */
public class ModuleDAO {
    public static final String TAG = ModuleDAO.class.getSimpleName();

    public static final String EXCEPTION_START = "JSON file error: ";

    private ModuleDAO() {
    }

    public static void saveParentModuleAsync( IModuleContext context, IParentModule parentModule ) throws IOException {
        new SaveAsyncTask().execute( new SaveMessenger( context, parentModule ) );
    }

    public static IParentModule loadParentModule( IModuleContext context ) throws IOException {
        LoadDAO loadDAO = new LoadDAO( context );
        return loadDAO.loadParentModule();
    }

    private static class SaveDAO {
        private final IModuleContext context;

        public SaveDAO( IModuleContext context ) {
            this.context = context;
        }

        public void saveParentModule( IParentModule parentModule ) throws IOException {
            new SaveAsyncTask().execute( new SaveMessenger( context, parentModule ) );
        }

        public String writeParentModule( IParentModule parentModule ) throws IOException {
            try {
                JSONObject jsonObject = ModuleCreationToolsMap.getInstance().getLoader( parentModule.getClass() ).save( context, parentModule );
                return jsonObject.toString();
            } catch ( JSONException e ) {
                e.printStackTrace();
                throw new IOException( e.getMessage(), e.getCause() );
            }
        }
    }

    private static class LoadDAO {
        private final IModuleContext context;

        public LoadDAO( IModuleContext context ) {
            this.context = context;
        }

        public IParentModule loadParentModule() throws IOException {
            File file = new File( context.getContext().getFilesDir(), Constants.FILE_PERSONAL_UI );
            if ( !file.exists() ) {
                throw new IOException( EXCEPTION_START + "save file does not exist" );
            }
            BufferedReader br = new BufferedReader( new FileReader( file ) );
            StringBuilder sb = new StringBuilder();
            String line;
            while ( ( line = br.readLine() ) != null ) {
                sb.append( line );
            }
            br.close();
            return readParentModule( sb.toString() );
        }

        public IParentModule readParentModule( String data ) throws IOException {
            try {
                JSONObject jsonObject = new JSONObject( data );
                return (IParentModule) loadModule( context, jsonObject );
            } catch ( JSONException e ) {
                e.printStackTrace();
                throw new IOException( EXCEPTION_START + e.getMessage(), e.getCause() );
            }
        }

        private IModule loadModule( IModuleContext context, JSONObject moduleObject ) throws IOException, JSONException {
            return ModuleLoader.getModuleLoader( moduleObject ).load( context, moduleObject );
        }
    }

    public static class SaveAsyncTask extends AsyncTask<SaveMessenger, Void, Void> {

        @Override
        protected Void doInBackground( SaveMessenger... params ) {
            //Log.d(TAG, "Executing saving task");
            SaveMessenger msg = params[0];
            SaveRunnable sr = new SaveRunnable( msg.context, msg.parentModule );
            sr.run();
            return null;
        }
    }

    public static class SaveRunnable implements Runnable {
        private final IModuleContext context;
        private final IParentModule parentModule;

        public SaveRunnable( IModuleContext context, IParentModule parentModule ) {
            this.context = context;
            this.parentModule = parentModule;
        }

        @Override
        public void run() {
            File file = new File( context.getContext().getFilesDir(), Constants.FILE_PERSONAL_UI );
            BufferedWriter bw;
            try {
                bw = new BufferedWriter( new FileWriter( file ) );
                SaveDAO saveDAO = new SaveDAO( context );
                String data = saveDAO.writeParentModule( parentModule );
                //Log.d(TAG, "write parent module = " + data);
                bw.write( data );
                bw.close();

                BufferedReader br = new BufferedReader( new FileReader( file ) );
                String line;
                while ( ( line = br.readLine() ) != null ) {
                    //Log.d(TAG, "file: " + line);
                }
                br.close();
            } catch ( IOException e ) {
                ErrorReporter.reportApplicationError( null, null, null, null );
            }
        }
    }

    public static class SaveMessenger {
        public final IModuleContext context;
        public final IParentModule parentModule;

        public SaveMessenger( IModuleContext context, IParentModule parentModule ) {
            this.context = context;
            this.parentModule = parentModule;
        }
    }
}
