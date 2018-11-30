package com.blauband.ods.config;

import com.blauband.ods.Settings;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author sergeizenevich
 */
public class ConfigDaoBean {

    private static final Logger LOGGER = Logger.getLogger(ConfigDaoBean.class.getName());

    private static final String CONFIG_FILE = "config.properties";

    private final String workingFolder;

    public ConfigDaoBean() {
        this(Settings.FILES_FOLDER);
    }

    public ConfigDaoBean(final String workingFolder) {
        this.workingFolder = workingFolder;
    }
    
    private String getConfigFile() {
        return workingFolder + CONFIG_FILE;
    }

    public ConfigDto find() {
        final File configDir = new File(workingFolder);
        if (!configDir.exists()) {
            configDir.mkdir();
        }

        final Properties properties = new Properties();

        InputStream in = null;
        try {
            final File configFile = new File(getConfigFile());
            if (configFile.exists()) {
                properties.load(in = new FileInputStream(configFile));
            } else {
                storeProperties(properties, "Initial create");
            }
        } catch (IOException e) {
            LOGGER.log(Level.SEVERE, "Problem with loading config", e);
        } finally {
            try {
                if (in != null) {
                    in.close();
                }
            } catch (IOException e) {
                LOGGER.log(Level.SEVERE, "Problem with closing stream during loading config", e);
            }
        }

        return new ConfigDto(properties);
    }

    private void storeProperties(final Properties properties, final String comment) {
        OutputStream out = null;
        try {
            final File configFile = new File(getConfigFile());
            properties.store(out = new FileOutputStream(configFile), comment);
        } catch (IOException e) {
            LOGGER.log(Level.SEVERE, "Problem with storing config", e);
        } finally {
            try {
                if (out != null) {
                    out.close();
                }
            } catch (IOException e) {
                LOGGER.log(Level.SEVERE, "Problem with closing stream during storing config", e);
            }
        }
    }

    ConfigDto merge(final ConfigDto config) {
        final Properties properties = config.getProperties();
        storeProperties(properties, "Merge");
        return find();
    }
}
