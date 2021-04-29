package reqres.login;

import dev.aliaga.login.model.CreateUserRequest;
import dev.aliaga.login.model.CreateUserResponse;
import org.apache.http.HttpStatus;
import org.junit.jupiter.api.Test;
import reqres.BaseTest;

import static io.restassured.RestAssured.given;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.notNullValue;

public class Main extends BaseTest {

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
