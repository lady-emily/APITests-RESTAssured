package Read;

import api.base.BaseTests;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;

public class GetRequestTests extends BaseTests {

    @Test
    public void testGetAllCarts() {
        Response response = given()
                .when().get("/carts")
                .then().statusCode(200)
                .extract().response();

        // Debugging: Print response body
        System.out.println("Response Body: " + response.getBody().asString());

        // Parse JSON response
        JsonPath jsonPath = response.jsonPath();

        // Validate response is a non-empty array
        Assert.assertFalse(jsonPath.getList("$").isEmpty(), "Response should not be empty");

        // Validate required fields in each cart object
        jsonPath.getList("$").forEach(cart -> {
            Assert.assertNotNull(((java.util.Map<?, ?>) cart).get("id"), "Cart ID should be present");
            Assert.assertNotNull(((java.util.Map<?, ?>) cart).get("userId"), "userId should be present");
            Assert.assertNotNull(((java.util.Map<?, ?>) cart).get("date"), "date should be present");
            Assert.assertNotNull(((java.util.Map<?, ?>) cart).get("products"), "products should be present");
            Assert.assertNotNull(((java.util.Map<?, ?>) cart).get("__v"), "__v should be present");
        });
    }

    @Test
    public void testGetCartsByUser() {
        Response response = given()
                .when().get("/carts/user/2")
                .then().statusCode(200)
                .extract().response();

        // Debugging: Print response body
        System.out.println("Response Body: " + response.getBody().asString());

        // Parse JSON response
        JsonPath jsonPath = response.jsonPath();

        // Validate response is an array
        Assert.assertTrue(jsonPath.getList("$") instanceof java.util.List, "Response should be a list");

        // Validate required fields in each cart object
        jsonPath.getList("$").forEach(cart -> {
            Assert.assertNotNull(((java.util.Map<?, ?>) cart).get("id"), "Cart ID should be present");
            Assert.assertNotNull(((java.util.Map<?, ?>) cart).get("userId"), "userId should be present");
            Assert.assertNotNull(((java.util.Map<?, ?>) cart).get("date"), "date should be present");
            Assert.assertNotNull(((java.util.Map<?, ?>) cart).get("products"), "products should be present");
            Assert.assertNotNull(((java.util.Map<?, ?>) cart).get("__v"), "__v should be present");

            // Validate products array
            java.util.List<?> products = (java.util.List<?>) ((java.util.Map<?, ?>) cart).get("products");
            Assert.assertFalse(products.isEmpty(), "Products array should not be empty");

            products.forEach(product -> {
                Assert.assertNotNull(((java.util.Map<?, ?>) product).get("productId"), "productId should be present");
                Assert.assertNotNull(((java.util.Map<?, ?>) product).get("quantity"), "quantity should be present");
            });
        });
    }
}
