package Update;

import api.base.BaseTests;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;

public class PutRequestTests extends BaseTests {

    @Test
    public void testUpdateUser() {
        String requestBody = "{\"name\": \"lady\", \"job\": \"qa engineer\"}";

        Response response = given()
                .contentType(ContentType.JSON)
                .body(requestBody)
                .when().put("/api/users/2")
                .then().statusCode(200)
                .extract().response();

        Assert.assertEquals(response.jsonPath().getString("name"), "lady");
        Assert.assertEquals(response.jsonPath().getString("job"), "qa engineer");
    }
}
