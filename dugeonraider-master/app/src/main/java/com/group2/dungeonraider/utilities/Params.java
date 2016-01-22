package com.group2.dungeonraider.utilities;

import android.util.Log;

import com.group2.dungeonraider.exception.SystemLoadException;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * Created by Rohit on 10/27/2015.
 */
public class Params {

    private static final String LOG = Params.class.getSimpleName();

    public static Properties loadParams() throws SystemLoadException {

        Properties properties;
        InputStream inputStream = null;
        properties = new Properties();
        try {
            inputStream = new FileInputStream("config.properties");
            properties.load(inputStream);

        } catch (FileNotFoundException e) {
            Log.e(LOG, "loadParams() -> Error loading parameters.", e);
            throw new SystemLoadException("Error loading parameters.");
        } catch (IOException e) {
            Log.e(LOG, "loadParams() -> Error loading parameters.", e);
            throw new SystemLoadException("Error loading parameters.");
        }finally {
            if(inputStream != null){
                try {
                    inputStream.close();
                } catch (IOException e) {
                    Log.e(LOG, "loadParams() -> Error loading parameters.", e );
                    throw new SystemLoadException("Error loading parameters.");
                }
            }
        }
        return properties;
    }
}
