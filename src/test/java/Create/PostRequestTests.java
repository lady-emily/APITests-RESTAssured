package Create;

import api.base.BaseTests;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;

public class PostRequestTests extends BaseTests {

    @Test
    public void testCreateCart() {
        String requestBody = "{\n" +
                "    \"userId\": 5,\n" +
                "    \"date\": \"2020-02-03\",\n" +
                "    \"products\": [\n" +
                "        {\n" +
                "            \"productId\": 5,\n" +
                "            \"quantity\": 1\n" +
                "        },\n" +
                "        {\n" +
                "            \"productId\": 1,\n" +
                "            \"quantity\": 5\n" +
                "        }\n" +
                "    ]\n" +
                "}";

        Response response = given()
                .contentType(ContentType.JSON)
                .body(requestBody)
                .when().post("/carts")
                .then().statusCode(200) // Change if expected status code is different
                .extract().response();

        Assert.assertNotNull(response.jsonPath().getInt("id"), "ID is missing");
        Assert.assertEquals(response.jsonPath().getInt("userId"), 5, "User ID mismatch");
        Assert.assertEquals(response.jsonPath().getString("date"), "2020-02-03", "Date mismatch");

        // Validate products array
        Assert.assertTrue(response.jsonPath().getList("products").size() > 0, "Products array is empty");

        response.jsonPath().getList("products").forEach(product -> {
            Assert.assertTrue(((Integer) ((java.util.Map<?, ?>) product).get("productId")) >= 0, "Invalid productId");
            Assert.assertTrue(((Integer) ((java.util.Map<?, ?>) product).get("quantity")) >= 0, "Invalid quantity");
        });
    }
}
