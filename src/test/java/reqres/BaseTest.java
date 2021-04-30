package reqres;

import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.http.ContentType;
import org.junit.jupiter.api.BeforeAll;
import reqres.config.ConfVariables;

public abstract class BaseTest {

    @BeforeAll
    public static void setUp() {

        RestAssured.requestSpecification = new RequestSpecBuilder()
                .setContentType(ContentType.JSON)
                .setBaseUri(ConfVariables.getHost())
                .setBasePath(ConfVariables.getPath())
                .addFilter(new RequestLoggingFilter())
                .addFilter(new RequestLoggingFilter())
                .build();
    }
}
