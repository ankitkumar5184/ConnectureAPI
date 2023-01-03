package modules;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import io.qameta.allure.Description;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.testng.Assert;
import org.testng.annotations.Test;
import pojoClasses.PharmacyPojo;

import java.io.File;
import java.util.List;

public class Test_2 extends BaseClass{

    @Test()
    @Description("Adding Pharmacies to session")
    public void test2_addPharmaciesToSession(){
        createToken();
        generateSessionId();
        RestAssured.baseURI = prop.getProperty("host");
        File addPharmaciesToSessionFile = new File(System.getProperty("user.dir") + "\\src\\main\\" +
                "java\\config\\addPharmaciesToSessionFile.json");
        RestAssured.given()
                .header("Content-Type", "application/json")
                .header("Authorization", "Bearer " + accessToken)
                .when().body(addPharmaciesToSessionFile)
                .post(String.format(pharmacyEndPointUrl, sessionId))
                .then()
                .statusCode(200)
                .log().body();
    }

    @Test(dependsOnMethods = {"test2_addPharmaciesToSession"})
    @Description("GET Request Operation")
    public void test2_getPharmacy() {
        RestAssured.baseURI = prop.getProperty("host");
        RequestSpecification request = RestAssured
                .given()
                .header("Content-Type", "application/json")
                .header("Authorization", "Bearer " + accessToken);
        Response response = request.get(String.format(pharmacyEndPointUrl, sessionId));
        var body = response.getBody();
        String responseBody = body.asString();
//        System.out.println(responseBody);
        Gson gson = new Gson();
        List<PharmacyPojo> data = gson.fromJson(responseBody, new TypeToken<List<PharmacyPojo>>(){}.getType());
        String isMailOrder = data.get(0).getIsMailOrder();
        Assert.assertEquals(isMailOrder, "false");
        String pharmacyNABP = data.get(0).getPharmacyNABP();
        Assert.assertEquals(pharmacyNABP, "5641166");
        Assert.assertEquals(response.statusCode(), StatusCode.CODE_200.code);
    }

    @Test(dependsOnMethods = {"test2_getPharmacy"})
    @Description("Updating the Pharmacies")
    public void test2_updatingPharmaciesToSession(){
        RestAssured.baseURI = prop.getProperty("host");
        File putPharmaciesToSessionFile = new File(System.getProperty("user.dir") + "\\src\\main\\" +
                "java\\config\\putPharmaciesToSessionFile.json");
        RequestSpecification request = RestAssured.given()
                .header("Content-Type", "application/json")
                .header("Authorization", "Bearer " + accessToken)
                .when()
                .body(putPharmaciesToSessionFile);
        Response response = request.put(String.format(pharmacyEndPointUrl, sessionId));
//        ResponseBody body = response.getBody();
        var body = response.getBody();
        String responseBody = body.asString();
        System.out.println(responseBody);
        Gson gson = new Gson();
        List<PharmacyPojo> data = gson.fromJson(responseBody, new TypeToken<List<PharmacyPojo>>() { }.getType());
        String isMailOrder = data.get(0).getIsMailOrder();
        Assert.assertEquals(isMailOrder, "false");
        System.out.println(data.get(0).getIsMailOrder());
        System.out.println(data.get(1).getPharmacyNABP());
    }
}