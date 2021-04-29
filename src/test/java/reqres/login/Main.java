package reqres.login;

import dev.aliaga.login.model.CreateUserRequest;
import dev.aliaga.login.model.CreateUserResponse;
import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.http.ContentType;
import org.apache.http.HttpStatus;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.notNullValue;

public class Main {

    @BeforeAll
    public static void setUp() {

        RestAssured.requestSpecification = new RequestSpecBuilder()
                .setContentType(ContentType.JSON)
                .setBaseUri("https://reqres.in")
                .setBasePath("/api")
                .addFilter(new RequestLoggingFilter())
                .addFilter(new RequestLoggingFilter())
                .build();
    }

    @Test
    public void createUser() {

        CreateUserRequest request = CreateUserRequest
                .builder()
                .name("morpheus")
                .job("leader")
                .build();

        CreateUserResponse createUserResponse = given()
                .when()
                .body(request)
                .post("/users")
                .then()
                .statusCode(HttpStatus.SC_CREATED)
                .contentType(equalTo("application/json; charset=utf-8"))
                .extract()
                .body()
                .as(CreateUserResponse.class);

        assertThat(createUserResponse, notNullValue());
        assertThat(createUserResponse.getName(), equalTo("morpheus"));
        assertThat(createUserResponse.getJob(), equalTo("leader"));

    }
}
