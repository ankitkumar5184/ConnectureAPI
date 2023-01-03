package ModulesPackage;

import io.qameta.allure.Description;
import io.restassured.RestAssured;
import org.testng.annotations.Test;
import java.io.File;

public class PharmacyClass extends BaseClass{

    @Test(priority = 2)
    @Description("Adding Pharmacies to session")
    public void addPharmaciesToSession(){
        RestAssured.baseURI = prop.getProperty("host");
        File addPharmaciesToSessionFile = new File(System.getProperty("user.dir") + "\\src\\main\\" +
                "java\\ConfigPackage\\addPharmaciesToSessionFile.json");
        RestAssured.given()
                .header("Content-Type", "application/json")
                .header("Authorization", "Bearer " + accessToken)
                .when().body(addPharmaciesToSessionFile)
                .post(String.format(pharmacyEndPointUrl, sessionId))
                .body().prettyPrint();
//                .then().statusCode(200);
    }

    @Test(priority = 3)
    @Description("GET Request Operation")
    public void pharmacy() {
        RestAssured.given()
                .header("Content-Type", "application/json")
                .header("Authorization", "Bearer " + accessToken)
                .when()
                .get(String.format(pharmacyEndPointUrl, sessionId))
                .body().prettyPrint();
    }

    @Test(priority = 4)
    @Description("Updating the Pharmacies")
    public void updatingPharmaciesToSession(){
        RestAssured.baseURI = prop.getProperty("host");
        File putPharmaciesToSessionFile = new File(System.getProperty("user.dir") + "\\src\\main\\" +
                "java\\ConfigPackage\\addPharmaciesToSessionFile.json");
        RestAssured.given()
                .header("Content-Type", "application/json")
                .header("Authorization", "Bearer " + accessToken)
                .when().body(putPharmaciesToSessionFile)
                .put(String.format(pharmacyEndPointUrl, sessionId))
                .body().prettyPrint();
//                .then().statusCode(200);
    }
}