package Update;

import api.base.BaseTests;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;

public class PutRequestTests extends BaseTests {

    @Test
    public void testUpdateCart() {
        String requestBody = "{"
                + "\"userId\":3,"
                + "\"date\":\"2019-12-10\","
                + "\"products\":["
                + "{"
                + "\"productId\":1,"
                + "\"quantity\":3"
                + "}"
                + "]"
                + "}";

        Response response = given()
                .contentType(ContentType.JSON)
                .body(requestBody)
                .when().put("/carts/7")
                .then().statusCode(200)
                .extract().response();

        // Print response details for debugging
        System.out.println("Response Status Code: " + response.getStatusCode());
        System.out.println("Response Body: " + response.getBody().asString());

        // Parse JSON response
        JsonPath jsonPath = response.jsonPath();

        // Validate response structure
        Assert.assertNotNull(jsonPath.get("id"), "ID should be present");
        Assert.assertNotNull(jsonPath.get("userId"), "userId should be present");
        Assert.assertNotNull(jsonPath.get("date"), "date should be present");
        Assert.assertNotNull(jsonPath.get("products"), "products array should be present");

        // Validate types and values
        Assert.assertTrue(jsonPath.getInt("id") >= 0, "ID should be a non-negative integer");
        Assert.assertTrue(jsonPath.getInt("userId") >= 0, "userId should be a non-negative integer");
        Assert.assertTrue(jsonPath.getList("products").size() > 0, "Products array should not be empty");
    }
}
