package utils;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import lombok.extern.slf4j.Slf4j;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

@Slf4j
public class ConfigManager {

    private static final Properties properties = new Properties();
    public static JsonObject testData;

    static {
        String configFilePath = System.getProperty("user.dir") + "/properties/" + System.getenv("env") + ".properties";
        try(FileInputStream reader = new FileInputStream(new File(configFilePath))){
            properties.load(reader);
            log.info(String.format("properties for env %s loaded successfully", System.getenv("env")));
        }
        catch (IOException io){
            throw new RuntimeException("unable to read config file");
        }
    }
    public String get(String key){
        return properties.getProperty(key);
    }

    public static void setTestData(){
        String testDataPath = System.getProperty("user.dir") + "/src/main/resources/TestData.json";
        try(FileReader reader = new FileReader(testDataPath)){
            JsonElement jsonElement = JsonParser.parseReader(reader);
            testData = jsonElement.getAsJsonObject();
            log.info("test data is in place");
        }
        catch (IOException io){
            throw new RuntimeException("unable to test data file");
        }
    }
}


