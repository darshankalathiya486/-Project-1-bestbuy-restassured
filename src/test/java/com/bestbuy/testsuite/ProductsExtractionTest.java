package com.bestbuy.testsuite;

import io.restassured.RestAssured;
import io.restassured.response.ValidatableResponse;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.HashMap;
import java.util.List;

import static io.restassured.RestAssured.given;

public class ProductsExtractionTest {

    static ValidatableResponse response;

    @BeforeClass
    public static void inIt(){
        RestAssured.baseURI = "http://localhost";
        RestAssured.port = 3030;
        response = given()
                .when()
                .get("/products")
                .then().statusCode(200);

    }

    // 1) Extract the limit
    @Test
    public void test001() {
        int limit = response.extract().path("limit");

        System.out.println("------------------StartingTest---------------------------");
        System.out.println("The value of limit is : " + limit);
        System.out.println("------------------End of Test---------------------------");
    }

    // 2) Extract the total
    @Test
    public void test002() {
        int total = response.extract().path("total");
        System.out.println("------------------StartingTest---------------------------");
        System.out.println("The value of total is : " + total);
        System.out.println("------------------End of Test---------------------------");
    }

    // 3) Extract the name of 5th product
    @Test
    public void test003() {
        String productName = response.extract().path("data[4].name");

        System.out.println("------------------StartingTest---------------------------");
        System.out.println("The fifth product name is : " + productName);
        System.out.println("------------------End of Test---------------------------");
    }

    // 4) Extract the names of all the products
    @Test
    public void test004() {
        List<String> listOfProductName = response.extract().path("data.name");

        System.out.println("------------------StartingTest---------------------------");
        System.out.println("List of products Name are : " + listOfProductName);
        System.out.println("------------------End of Test---------------------------");
    }

    // 5) Extract the productId of all the products
    @Test
    public void test005() {
        List<Integer> listOfProductId = response.extract().path("data.id");

        System.out.println("------------------StartingTest---------------------------");
        System.out.println("List of products Id are : " + listOfProductId);
        System.out.println("------------------End of Test---------------------------");
    }

    // 6) Print the size of the data list
    @Test
    public void test006() {
        int dataListSize = response.extract().path("data.size()");

        System.out.println("------------------StartingTest---------------------------");
        System.out.println("The size of the data is : " + dataListSize);
        System.out.println("------------------End of Test---------------------------");
    }

    // 7) Get all the value of the product where product name = Energizer - MAX Batteries AA (4-Pack)
    @Test
    public void test007() {
        List<HashMap<String, ?>> values = response.extract().path("data.findAll{it.name == 'Energizer - MAX Batteries AA (4-Pack)'}");

        System.out.println("------------------StartingTest---------------------------");
        System.out.println("The values for product name 'Energizer - MAX Batteries AA (4-Pack)' are: " + values);
        System.out.println("------------------End of Test---------------------------");
    }

    // 8) Get the model of the product where product name = Energizer - N Cell E90 Batteries (2-Pack)
    @Test
    public void test008() {
        List<String> model = response.extract().path("data.findAll{it.name == 'Energizer - N Cell E90 Batteries (2-Pack)'}.model");

        System.out.println("------------------StartingTest---------------------------");
        System.out.println("The model for product name 'Energizer - N Cell E90 Batteries (2-Pack)' is: " + model.get(0));
        System.out.println("------------------End of Test---------------------------");
    }

    // 9) Get all the categories of 8th products
    @Test
    public void test009() {
        List<String> categories = response.extract().path("data[7].categories");

        System.out.println("------------------StartingTest---------------------------");
        System.out.println("The categories for eight products name are: " + categories);
        System.out.println("------------------End of Test---------------------------");
    }

    // 10) Get categories of the store where product id = 150115
    @Test
    public void test010() {
        List<HashMap<String, ?>> categories = response.extract().path("data.findAll{it.id == 150115 }.categories");

        System.out.println("------------------StartingTest---------------------------");
        System.out.println("The product categories for product id ('150115') are: " + categories);
        System.out.println("------------------End of Test---------------------------");
    }

    // 11) Get all the descriptions of all the products
    @Test
    public void test011() {
        List<Integer> descriptions = response.extract().path("data.description");

        System.out.println("------------------StartingTest---------------------------");
        System.out.println("List of descriptions are : " + descriptions);
        System.out.println("------------------End of Test---------------------------");
    }

    // 12) Get id of all the all categories of all the products
    @Test
    public void test012() {
        List<Integer> listOfCategoriesId = response.extract().path("data.categories.id");

        System.out.println("------------------StartingTest---------------------------");
        System.out.println("List of categories Id are : " + listOfCategoriesId);
        System.out.println("------------------End of Test---------------------------");
    }

    // 13) Find the product names Where type = HardGood
    @Test
    public void test013() {
        List<String> productName = response.extract().path("data.findAll{it.type == 'HardGood'}.name");

        System.out.println("------------------StartingTest---------------------------");
        System.out.println("product name of type ('HardGood') is : " + productName.get(0));
        System.out.println("------------------End of Test---------------------------");
    }

    // 14) Find the Total number of categories for the product where product name = Duracell - AA 1.5V CopperTop Batteries (4-Pack)
    @Test
    public void test014() {
        int numberOfCategories = response.extract().path("data.findAll{it.name == 'Duracell - AA 1.5V CopperTop Batteries (4-Pack)'}.categories.size()");

        System.out.println("------------------StartingTest---------------------------");
        System.out.println("categories for product name of ('Duracell - AA 1.5V CopperTop Batteries (4-Pack)') is : " + numberOfCategories);
        System.out.println("------------------End of Test---------------------------");
    }

    // 15) Find the createdAt for all products whose price < 5.49
    @Test
    public void test015() {
        List<HashMap<String, ?>> createdAt = response.extract().path("data.findAll{ it.price < 5.49 }.createdAt");

        System.out.println("------------------StartingTest---------------------------");
        System.out.println("CreatedAt for products of price < 5.49 are : " + createdAt);
        System.out.println("------------------End of Test---------------------------");
    }

    // 16) Find the name of all categories Where product name = “Energizer - MAX Batteries AA (4-Pack)”
    @Test
    public void test016(){
        List<HashMap<String, ?>> nameOfAllCategories = response.extract().path("data.findAll{it.name == 'Energizer - MAX Batteries AA (4-Pack)'}.categories.name");

        System.out.println("------------------StartingTest---------------------------");
        System.out.println("Name of all categories Name for store of ('Energizer - MAX Batteries AA (4-Pack)') are : " + nameOfAllCategories);
        System.out.println("------------------End of Test---------------------------");
    }

    // 17)  Find the manufacturer of all the products
    @Test
    public void test017(){
        List<Integer> listOfProductsManufacturer = response.extract().path("data.manufacturer");

        System.out.println("------------------StartingTest---------------------------");
        System.out.println("List of products manufacturer are : " + listOfProductsManufacturer);
        System.out.println("------------------End of Test---------------------------");
    }

    // 18) Find the image of products whose manufacturer is = Energizer
    @Test
    public void test018(){
        List<String> productsImage = response.extract().path("data.findAll{it.manufacturer == 'Energizer'}.image");

        System.out.println("------------------StartingTest---------------------------");
        System.out.println("products image of manufacturer ('Energizer') is : " + productsImage.get(0));
        System.out.println("------------------End of Test---------------------------");
    }

    //19. Find the createdAt for all categories products whose price > 5.99
    @Test
    public void test19() {
        List<HashMap<String, ?>> createdAt = response.extract().path("data.findAll{ it.price > 5.99 }.createdAt");

        System.out.println("------------------StartingTest---------------------------");
        System.out.println("CreatedAt for products of price > 5.99 are : " + createdAt);
        System.out.println("------------------End of Test---------------------------");
    }

    // 20) Find the url of all the products
    @Test
    public void test020(){
        List<?> listOfProductsUrl = response.extract().path("data.url");

        System.out.println("------------------StartingTest---------------------------");
        System.out.println("List of products url are : " + listOfProductsUrl);
        System.out.println("------------------End of Test---------------------------");
    }
}
