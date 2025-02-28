package Read;

import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;

public class GetRequestTests {
    @Test
    public void testGetUsers() {
        Response response = given()
                .queryParam("page", 1)
                .when().get("/api/users")
                .then().statusCode(200)
                .extract().response();

        Assert.assertNotNull(response.getBody());
    }

    @Test
    public void testGetUserNotFound() {
        given().when().get("/api/users/23")
                .then().statusCode(404);
    }
}
