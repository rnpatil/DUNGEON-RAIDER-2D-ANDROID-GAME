package com.group2.dungeonraider.utilities;

import java.util.Locale;
import java.util.MissingResourceException;
import java.util.ResourceBundle;

/**
 * Created by Rohit on 10/27/2015.
 */
public final class FPropsLoader {
    private final String _propsFile;
    public String getPropsFile() {return _propsFile;}

    private ResourceBundle _props;

    /**
     * ctor
     */
    public FPropsLoader(String propsFile) {
        _propsFile = propsFile;
        _props = ResourceBundle.getBundle(_propsFile);
    }

    public String getValue(String key) {
        if (null == key)
            throw new NullPointerException("Key is null!");
        synchronized (_props) {
            return _props.getString(key);
        }
    }

    public String getValue(String key, String defaultValue) {
        if (null == key)
            throw new NullPointerException("Key is null!");
        String ret = null;
        synchronized (_props) {
            try {
                ret = _props.getString(key);
                return null == ret ? defaultValue : ret;
            } catch (MissingResourceException mre) {
                return defaultValue;
            }
        }
    }

}