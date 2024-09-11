package utils.helpers;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import drivers.DriverFactory;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;


public class LocatorHelper {
    private static final Logger log = LogManager.getLogger(LocatorHelper.class.getName());
    private static JsonNode jsonNode;

    public static JsonNode loadLocators(String filePath) {
        log.info("Loading locator value from {}", filePath);
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            InputStream inputStream = ClassLoader.getSystemClassLoader().getResourceAsStream(filePath);
            if (inputStream == null) {
                log.error("Unable to locate the following file: {}", filePath);
                throw new FileNotFoundException();
            } else {
                jsonNode = objectMapper.readTree(inputStream);
            }
        } catch (IOException e) {
            log.error("Cannot load value from resources path: " + filePath);
            e.printStackTrace();
        }
        return  jsonNode.get(DriverFactory.getBrowserNameDriver());
    }
}
