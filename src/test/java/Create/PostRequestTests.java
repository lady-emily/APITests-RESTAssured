package Create;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;

public class PostRequestTests {
    @Test
    public void testCreateUser() {
        String requestBody = "{\"name\": \"emily\", \"job\": \"leader\"}";

        Response response = given()
                .contentType(ContentType.JSON)
                .body(requestBody)
                .when().post("/api/users")
                .then().statusCode(201)
                .extract().response();

        Assert.assertEquals(response.jsonPath().getString("name"), "emily");
        Assert.assertEquals(response.jsonPath().getString("job"), "leader");
    }
}
