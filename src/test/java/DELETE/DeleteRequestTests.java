package DELETE;

import api.base.BaseTests;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;

public class DeleteRequestTests extends BaseTests {

    @Test
    public void testDeleteCart() {
        Response response = given()
                .when().delete("/carts/6")
                .then().statusCode(200)
                .extract().response();

        Assert.assertNotNull(response.getBody());

        // Validating response body structure
        Assert.assertTrue(response.jsonPath().get("id") != null, "ID is missing in response");
        Assert.assertTrue(response.jsonPath().get("userId") != null, "UserId is missing in response");
        Assert.assertTrue(response.jsonPath().get("date") != null, "Date is missing in response");
        Assert.assertTrue(response.jsonPath().get("products") != null, "Products array is missing in response");
        Assert.assertTrue(response.jsonPath().get("__v") != null, "__v is missing in response");
    }
}
