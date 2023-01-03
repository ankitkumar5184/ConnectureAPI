package modules;

import io.qameta.allure.Description;
import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;


public class BaseClass {
    public static String pharmacyEndPointUrl = "/APIPlanCompare/v1/Session/%s/Pharmacies/";
    public static Properties prop;
    public static String accessToken;
    public static Object sessionId;

    @BeforeClass
    public void usePropertyFile() {
        try {
            InputStream ip = new FileInputStream(System.getProperty("user.dir") + "\\src\\main\\" +
                    "java\\config\\data.properties");
            prop = new Properties();
            prop.load(ip);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test()
    @Description("Generating Bearer token")
    public void createToken() {
        RestAssured.baseURI = prop.getProperty("auth-host");
        RequestSpecification httpRequest = RestAssured.given()
                .header("Authorization", "Basic " +prop.getProperty("basic-auth"))
                .formParam("grant_type", "client_credentials");
        Response response = httpRequest.post("/v1/auth/token?format=json");
        response.body().prettyPrint();
        JsonPath jsonPathEvaluator = response.jsonPath();

        accessToken = jsonPathEvaluator.get("access_token");
        System.out.println(accessToken);
    }

    @Test()
    @Description("Generating Session ID")
    public void generateSessionId() {
        RestAssured.baseURI = prop.getProperty("host");

        RequestSpecification request = RestAssured.given();
        File createSessionDataFile = new File(System.getProperty("user.dir") + "\\src\\main\\" +
                "java\\config\\createSessionData.json");
        request.header("Content-Type", "application/json")
                .header("Authorization", "Bearer " + accessToken);
        System.out.println(accessToken);
        Response responseFormToken = request.body(createSessionDataFile).post("/APIPlanCompare/v1/Session/");
        System.out.println(responseFormToken.body());
        sessionId = responseFormToken.jsonPath().getString("[0].SessionID");
        responseFormToken.prettyPrint();
        System.out.println("SessionID " + sessionId);
    }
}