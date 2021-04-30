package reqres.config;

import java.io.IOException;
import java.io.InputStream;
import java.util.Optional;
import java.util.Properties;


public class ApplicationProperties {

    private static Properties instance;
    private static final String APPLICATION_PREFIX = "application";
    private static final String APPLICATION_SUFFIX = "yaml";

    private ApplicationProperties() {
    }

    public static synchronized Properties getInstance() {
        if (instance == null) instance = loadPropertiesFile();
        return instance;
    }

    private static Properties loadPropertiesFile() {
        Properties prop = new Properties();

        String environment = Optional.ofNullable(System.getenv("env")).orElse("dev");
        String fileName = String.format("%s-%s.%s", APPLICATION_PREFIX, environment, APPLICATION_SUFFIX);

        try (InputStream input = ApplicationProperties.class.getClassLoader().getResourceAsStream(fileName)) {

            if (input == null) {
                System.out.println("Sorry, unable to find " + fileName);
            }
            prop.load(input);

        } catch (IOException io) {
            io.printStackTrace();
        }
        return prop;
    }
}
