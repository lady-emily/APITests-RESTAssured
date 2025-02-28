package api.base;

import io.qameta.allure.testng.AllureTestNg;
import io.restassured.RestAssured;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Listeners;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Paths;

@Listeners(AllureTestNg.class)
public class BaseTests {

    private static final String BASE_URL = "https://fakestoreapi.com"; // Base URL for API

    @BeforeSuite
    public void setupAllureResultsDirectory() {
        String allureResultsPath = Paths.get(System.getProperty("user.dir"), "allure-results").toString();
        System.setProperty("allure.results.directory", allureResultsPath);

        // Ensure the directory exists
        File allureResultsDir = new File(allureResultsPath);
        if (!allureResultsDir.exists()) {
            allureResultsDir.mkdirs();
        }

        // Create environment.properties file
        File envFile = new File(allureResultsPath, "environment.properties");
        try (FileWriter writer = new FileWriter(envFile)) {
            writer.write("Base URL=" + BASE_URL + "\n");
            writer.write("Environment=QA\n");
            writer.write("Tester=Emily\n");
            writer.write("Platform=Windows 11\n");
            writer.write("Java Version=" + System.getProperty("java.version") + "\n");
            writer.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @BeforeClass
    public void setupRestAssured() {
        RestAssured.baseURI = BASE_URL;
    }
}
