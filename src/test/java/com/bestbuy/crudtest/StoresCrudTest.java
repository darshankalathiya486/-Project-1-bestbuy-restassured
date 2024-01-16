package com.bestbuy.crudtest;

import com.bestbuy.model.ProductPojo;
import com.bestbuy.model.StorePojo;
import com.bestbuy.testbase.StoreTestBase;
import com.bestbuy.utils.TestUtils;
import io.restassured.response.Response;
import org.testng.annotations.Test;

import java.util.HashMap;

import static io.restassured.RestAssured.given;

public class StoresCrudTest extends StoreTestBase {

    static String name = "Prime"+ TestUtils.getRandomValue()+"123";
    static String type = "Testing";
    static String address = "Offshore";
    static String address2 = "Zoom";
    static String city = "London";
    static String state = "UK";
    static String zip = "HA00AA";
    static int lat = 123456;
    static int lng = 456789;
    static String hours = "2";

    static int storeID;

    @Test
    public void test001() {

        System.out.println("============= Starting of Add Product==============");

        HashMap<String, Object> services = new HashMap<>();
        services.put("JAVA", "3 Weeks");
        services.put("API", "2 Weeks");

        StorePojo storePojo = new StorePojo();
        storePojo.setName(name);
        storePojo.setType(type);
        storePojo.setAddress(address);
        storePojo.setAddress2(address2);
        storePojo.setCity(city);
        storePojo.setState(state);
        storePojo.setZip(zip);
        storePojo.setLat(lat);
        storePojo.setLng(lng);
        storePojo.setHours(hours);
        storePojo.setServices(services);

        Response response = given()
                .header("Content-Type", "application/json")
                .header("Accept", "application/json")
                .when()
                .body(storePojo)
                .post();
        response.prettyPrint();
        response.then().statusCode(201);

        storeID = response.then().extract().path("id");
        System.out.println("ID = " + storeID);

        System.out.println("============= Ending of Add Product==============");
    }

    @Test
    public void test002() {

        System.out.println("============= Starting of Get Product==============");

        Response response = given()
                .header("Accept", "application/json")
                .when()
                .get("/" + storeID);
        response.then().statusCode(200);

        response.prettyPrint();

        System.out.println("============= Ending of Get Product==============");
    }

    @Test
    public void test003() {

        System.out.println("============= Starting of Update Product==============");

        ProductPojo productPojo = new ProductPojo();
        productPojo.setName(name + "_123");
        productPojo.setType(type + "_good");

        Response response = given()
                .header("Content-Type", "application/json")
                .header("Accept", "application/json")
                .body(productPojo)
                .when()
                .patch("/" + storeID);
        response.then().statusCode(200);

        response.prettyPrint();

        System.out.println("============= Ending of Update Product==============");
    }

    @Test
    public void test004() {

        System.out.println("============= Starting of Delete Product==============");

        given()
                .when()
                .delete("/" + storeID)
                .then()
                .statusCode(200);

        Response response = given()
                .header("Accept", "application/json")
                .when()
                .get("/" + storeID);
        response.then().statusCode(404);

        System.out.println("============= Ending of Delete Product==============");
    }
}
