package util;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class utility {

    static Properties properties;
    static InputStream input;

    public static Properties loadProperties(String path) {
        try {
            input = new FileInputStream(path);
            properties = new Properties();
            properties.load(input);
            return properties;

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return properties;
    }
    
    public static double celsiusToKelvin(String tempUI)
    {
    	return Double.parseDouble(tempUI) + 273.15; 	
    }
    
    public static boolean tempMatcher(double a, double b, double var)
    {
    	return Math.abs(a-b)<=var?true:false;
    }
}
