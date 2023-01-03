package ModulesPackage;

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
    public static String host = "https://qa.drxwebservices.com";
    public static String auth_host = "https://qa-auth.drxwebservices.com";
    public static Properties prop;
    public static String accessToken;
    public static Object sessionId;

    @BeforeClass
    public void usePropertyFile() {
        try {
            InputStream ip = new FileInputStream(System.getProperty("user.dir") + "\\src\\main\\" +
                    "java\\ConfigPackage\\data.properties");
            prop = new Properties();
            prop.load(ip);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test(priority = 0)
    @Description("Generating Bearer token")
    public void createToken() {
        RestAssured.baseURI = auth_host;
        RequestSpecification httpRequest = RestAssured.given()
                .header("Authorization", "Basic eDZxSzJwWmJFZUtLdHdBVlhRd3JCQTpwYXNzd29yZA==")
                .formParam("grant_type", "client_credentials");
        Response response = httpRequest.post("/v1/auth/token?format=json");
        response.body().prettyPrint();
        JsonPath jsonPathEvaluator = response.jsonPath();

        accessToken = jsonPathEvaluator.get("access_token");
        System.out.println(accessToken);
    }

    @Test(priority = 1)
    @Description("Generating Session ID")
    public void generateSessionId() {
        RestAssured.baseURI = prop.getProperty("host");

        RequestSpecification request = RestAssured.given();
        File createSessionDataFile = new File(System.getProperty("user.dir") + "\\src\\main\\" +
                "java\\ConfigPackage\\createSessionData.json");
        request.header("Content-Type", "application/json")
                .header("Authorization", "Bearer " + accessToken);
        System.out.println(accessToken);
        Response responseFormToken = request.body(createSessionDataFile).post("/APIPlanCompare/v1/Session/");
        System.out.println(responseFormToken.body());
        sessionId = responseFormToken.jsonPath().getString("[0].SessionID");
        responseFormToken.prettyPrint();
        System.out.println("SessionID " + sessionId);
    }

//    @Test(priority = 1)
//    public void createSessionID() {
//        RestAssured.baseURI = host;
//        File createSessionDataFile = new File(System.getProperty("user.dir") + "\\src\\main\\" +
//                "java\\ConfigPackage\\createSessionData.json");
//
//        RequestSpecification httpRequest = RestAssured.given()
//                .header("Authorization", "Bearer " + accessToken);
//        httpRequest.contentType(ContentType.JSON);
//        httpRequest.body(createSessionDataFile);
//        Response response = httpRequest.post("/APIPlanCompare/v1/Session/");
////        System.out.println(response.getBody().asString());
//
//        Object responseAsObject = response.as(Object.class);
//        if (responseAsObject instanceof List) {
//            List responseAsList = (List) responseAsObject;
//            System.out.println(responseAsList.get(0));
//
//            Map<String, Object> emp1 = (Map<String, Object>) responseAsList.get(0);
//            sessionId = emp1.get("SessionID");
//            System.out.println(sessionId);
//        } else if (responseAsObject instanceof Map) {
//            Map responseAsMap = (Map) responseAsObject;
//            System.out.println(((Map) responseAsMap).keySet());
//        }
//    }

//    @Test
//    public void createToken() {
//        RestAssured.baseURI = prop.getProperty("auth-host");
//        RequestSpecification httpRequest = RestAssured.given()
//                .header("Authorization", "Basic eDZxSzJwWmJFZUtLdHdBVlhRd3JCQTpwYXNzd29yZA==")
//                .formParam("grant_type", "client_credentials");
//        Response response = httpRequest.post("/v1/auth/token?format=json");
//
//        JsonPath jsonPathEvaluator = response.jsonPath();
//
//        accessToken = jsonPathEvaluator.get("access_token");
//        System.out.println(accessToken);
//    }

//    @Test
//    public void generateBearerToken() {
//        RestAssured.baseURI = prop.getProperty("auth-host"); //"https://qa-auth.drxwebservices.com";
//
//        RequestSpecification request = RestAssured.given();
//        String payload = "grant_type=client_credentials";
//
//        request.header("Content-Type", "application/json")
//                .header("Authorization", "Basic " + prop.getProperty("auth"))
//                .contentType(ContentType.JSON);
//        Response responseFormToken = request.body(payload).post("/v1/auth/token?format=json");
//        accessToken = responseFormToken.jsonPath().getString("access_token");
//        System.out.println(accessToken);
//        responseFormToken.prettyPrint();
//    }


//    @Test
//    public void createSessionID() {
//        RestAssured.baseURI = prop.getProperty("host");
//        File jsonDataInFile = new File(System.getProperty("user.dir") + "/src/test/java/JSONData/SessionData.json");
//
//        RequestSpecification httpRequest = RestAssured.given()
//                .header("Authorization", "Bearer " + accessToken);
//        httpRequest.contentType(ContentType.JSON);
//        httpRequest.body(jsonDataInFile);
//        Response response = httpRequest.post("/APIPlanCompare/v1/Session/");
////        System.out.println(response.getBody().asString());
//
//        Object responseAsObject = response.as(Object.class);
//        if (responseAsObject instanceof List) {
//            List responseAsList = (List) responseAsObject;
//            System.out.println(responseAsList.get(0));
//
//            Map<String, Object> emp1 = (Map<String, Object>) responseAsList.get(0);
//            sessionId = (String) emp1.get("SessionID");
//            System.out.println(sessionId);
//        } else if (responseAsObject instanceof Map) {
//            Map responseAsMap = (Map) responseAsObject;
//            System.out.println((responseAsMap).keySet());
//        }
//    }

}