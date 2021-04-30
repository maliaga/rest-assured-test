package reqres.config;

import java.util.Optional;

public class ConfVariables {

    public static String getHost() {
        return Optional.ofNullable(System.getenv("host"))
                .orElse(ApplicationProperties.getInstance().getProperty("host"));
    }

    public static String getPath() {
        return Optional.ofNullable(System.getenv("path"))
                .orElse(ApplicationProperties.getInstance().getProperty("path"));
    }
}
