package DELETE;

import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;

public class DeleteRequestTests {

    @Test
    public void testDeleteUser() {
        given().when().delete("/api/users/2")
                .then().statusCode(204);
    }
}
