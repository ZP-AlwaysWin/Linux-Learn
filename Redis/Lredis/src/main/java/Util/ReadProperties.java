package Util;



import java.io.IOException;
import java.util.Properties;

public class ReadProperties {

    public static Properties loadProperties(String fileName){
        Properties pps = null;
        try{
            pps = new Properties();
            pps.load(ReadProperties.class.getClassLoader().getResourceAsStream(fileName));
        }catch (IOException e){
            e.printStackTrace();
        }
        return pps;

    }
}
