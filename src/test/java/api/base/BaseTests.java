package api.base;

import io.qameta.allure.testng.AllureTestNg;
import io.restassured.RestAssured;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Listeners;

import static io.restassured.RestAssured.given;

@Listeners(AllureTestNg.class)  // Ensure correct package name
public class BaseTests {

    private static final String BASE_URL = "https://fakestoreapi.com";

    @BeforeClass
    public void setup() {
        RestAssured.baseURI = BASE_URL;
    }



}
