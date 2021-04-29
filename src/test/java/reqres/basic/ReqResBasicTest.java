package reqres.basic;

import dev.aliaga.basic.model.User;
import io.restassured.http.ContentType;
import org.junit.jupiter.api.Test;
import reqres.BaseTest;

import java.util.List;
import java.util.Map;

import static io.restassured.RestAssured.given;
import static io.restassured.path.json.JsonPath.from;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

public class ReqResBasicTest extends BaseTest {

    @Test
    public void parserBody() {
        String body = given()
                .contentType(ContentType.JSON)
                .get("/users?page=2")
                .then()
                .extract()
                .body()
                .asString();

        int page = from(body).get("page");
        int totalPages = from(body).get("total_pages");
        int idFirstUser = from(body).get("data[0].id");

        System.out.println("page" + page);
        System.out.println("Total Pages " + totalPages);
        System.out.println("ID First dev.aliaga.model.User " + idFirstUser);

        List<Map> usersWithIdGreaterThan10 = from(body).get("data.findAll { user -> user.id > 10}");

        String email = usersWithIdGreaterThan10.get(0).get("email").toString();
        System.out.println("EMAIL " + email);

        assertThat(email, equalTo("george.edwards@reqres.in"));

        List<Map> usersWithIdGreaterThan10andLastName = from(body).get("data.findAll { user -> user.id > 10 && user.last_name == 'Howell'}");

        int id = Integer.parseInt(usersWithIdGreaterThan10andLastName.get(0).get("id").toString());

        System.out.println("ID " + id);

        assertThat(id, equalTo(12));
    }

    @Test
    public void parsePostCreateUser() {
        String body = given()
                .when()
                .body("{\n" +
                        "    \"name\": \"morpheus\",\n" +
                        "    \"job\": \"leader\"\n" +
                        "}")
                .post("/users")
                .then()
                .extract()
                .body()
                .asString();

        User user = from(body).getObject("", User.class);

        System.out.println("ID " + user.getId());
        System.out.println("Name " + user.getName());
        System.out.println("Job " + user.getJob());
        System.out.println("CreateAt " + user.getCreatedAt());
    }
}
