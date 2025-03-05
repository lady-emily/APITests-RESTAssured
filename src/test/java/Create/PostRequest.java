package Create;

import io.restassured.http.ContentType;
import org.json.simple.JSONObject;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;

public class PostRequest{

    @Test
   public static void newMethod()

    {
        JSONObject requestData = new JSONObject();
        requestData.put("name", "morpheus");
        requestData.put("job", "leader");
        given()
                .contentType(ContentType.JSON)
                .body(requestData.toString())
                .when().post("https://reqres.in/api/users?page=2")
                .then().statusCode(201); // Change if expected status code is different
    }
}
