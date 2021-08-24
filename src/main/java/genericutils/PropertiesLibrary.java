package genericutils;

import java.io.*;
import java.util.Properties;

public class PropertiesLibrary {

    public static String getValue(String propPath, String key) {
        FileInputStream fis;
        Properties prop = new Properties();
        try {
            fis = new FileInputStream(new File(propPath));
            prop.load(fis);
            fis.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return prop.getProperty(key);
    }
}