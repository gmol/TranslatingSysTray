package org.gmol.TranslatingSysTray;

import java.io.File;
import java.io.InputStream;
import java.util.Properties;

import org.apache.log4j.BasicConfigurator;
import org.apache.log4j.PropertyConfigurator;
import org.apache.log4j.Logger;

public class LogConfig {
    private final static Logger logger = Logger.getLogger(LogConfig.class);

    public static void configureLogging(String propertiesFile){
        File log4jConfigFile = new File(propertiesFile);
        if (log4jConfigFile.exists() && log4jConfigFile.canRead()) {
            PropertyConfigurator.configure(log4jConfigFile.getAbsolutePath());
            logger.debug("log4j properties loaded from : "  + log4jConfigFile.getAbsolutePath());
        } else {
            Properties log4jProperties = new Properties();
            try {
            	String log4jPropertiesFile = "log4j.properties";
            	InputStream in = LogConfig.class.getClassLoader().getResourceAsStream( log4jPropertiesFile );    
                log4jProperties.load(in);
                PropertyConfigurator.configure(log4jProperties);
                logger.debug("log4j properties loaded from : " + log4jPropertiesFile);
            } catch (Exception e) {
                //as a last resort use basic configurator
                BasicConfigurator.configure();
                logger.error("Cannot load default log4j properties.Reason:  " + e.getMessage());
                logger.debug("Caught exception. Stack trace follows: " + e);
                org.apache.log4j.helpers.PatternConverter p;
                org.apache.log4j.PatternLayout l;
            }
        }
    }

}