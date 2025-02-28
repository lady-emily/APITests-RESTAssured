package DELETE;

import api.base.BaseTests;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;

public class DeleteRequestTests extends BaseTests {

    @Test
    public void testDeleteUser() {
        given().when().delete("/api/users/2")
                .then().statusCode(204);
    }
}
