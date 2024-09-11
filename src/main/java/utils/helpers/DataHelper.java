package utils.helpers;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;
import java.io.InputStream;
import java.util.Map;

public class DataHelper {
    private static final Logger log = LogManager.getLogger(DataHelper.class.getName());
    private Map<String, Object> data;

    public DataHelper(String filePath) {
        log.debug("Loading data from file json {} ", filePath);
        ObjectMapper mapper = new ObjectMapper();
        InputStream inputStream = ClassLoader.getSystemClassLoader().getResourceAsStream(filePath);
        try {
            data = mapper.readValue(inputStream, new TypeReference<Map<String, Object>>() {});
        } catch (IOException e) {
            log.error("Cannot load json from resources path: " + filePath);
            e.printStackTrace();
        }
    }

    public String getStringData(String key) {
        return data.get(key).toString();
    }

    public Integer getIntData(String key){
        return Integer.parseInt(data.get(key).toString());
    }

    public <T> T getData(String key, Class<T> clazz) {
        ObjectMapper mapper = new ObjectMapper();
        return mapper.convertValue(data.get(key), clazz);
    }
}
