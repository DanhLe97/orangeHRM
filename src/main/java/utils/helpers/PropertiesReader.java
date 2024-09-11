package utils.helpers;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.*;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

public class PropertiesReader {
    private static final Logger log = LogManager.getLogger(PropertiesReader.class.getName());
    private static Map<String, Properties> sections = new HashMap<>();
    private static Properties defaultProperties = new Properties();

    public static Properties loadProperties(String fileName, String sectionName) {
        InputStream inputStream = ClassLoader.getSystemClassLoader()
                .getResourceAsStream(fileName);
        if (inputStream == null) {
            log.error("Unable to locate the properties file {}", fileName);
            try {
                throw new FileNotFoundException();
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
        } else {
            try (BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream))) {
                String line;
                Properties currentSection = defaultProperties;
                while ((line = reader.readLine()) != null) {
                    line = line.trim();
                    if (line.isEmpty() || line.startsWith("#")) {
                        continue;
                    }
                    if (line.startsWith("[") && line.endsWith("]")) {
                        String section = line.substring(1, line.length() - 1);
                        currentSection = new Properties();
                        sections.put(section, currentSection);
                    } else {
                        int index = line.indexOf('=');
                        if (index != -1) {
                            String key = line.substring(0, index).trim();
                            String value = line.substring(index + 1).trim();
                            currentSection.setProperty(key, value);
                        }
                    }
                }

            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        if (sectionName != "") {
            return sections.get(sectionName);
        } else {
            return defaultProperties;
        }
    }

    public Properties getSectionProperties(String section) {
        return sections.get(section);
    }

}