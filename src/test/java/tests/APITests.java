package tests;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.testng.annotations.Test;

import static org.hamcrest.Matchers.equalTo;

public class APITests {

    @Test
    public void addNewPetPossibilityTest() {
        RestAssured.given()
                .baseUri("https://petstore.swagger.io/")
                .basePath("v2/pet/25")
                .contentType(ContentType.JSON)
                .when().get()
                .then()
                .statusCode(404);

        RestAssured.given()
                .baseUri("https://petstore.swagger.io/")
                .basePath("v2/pet")
                .contentType(ContentType.JSON)
                .body("{\n" +
                        "  \"id\":" + 25 + ",\n" +
                        "  \"name\": \"doggie\",\n" +
                        "  \"photoUrls\": [],\n" +
                        "  \"tags\": [],\n" +
                        "  \"status\": \"available\"\n" +
                        "}")
                .when().post()
                .then()
                .statusCode(200)
                .body("id", equalTo(25))
                .extract().response().prettyPrint();

        RestAssured.given()
                .baseUri("https://petstore.swagger.io/")
                .basePath("v2/pet/25")
                .contentType(ContentType.JSON)
                .when().get()
                .then()
                .statusCode(200)
                .body("id", equalTo(25));
    }

    @Test
    public void deletePetDataTest(){
        RestAssured.given()
                .baseUri("https://petstore.swagger.io/")
                .basePath("v2/pet/25")
                .contentType(ContentType.JSON)
                .when().delete()
                .then()
                .statusCode(404)
                .extract().response().prettyPrint();
    }

    @Test
    public void updatePetDataTest(){
        RestAssured.given()
                .baseUri("https://petstore.swagger.io/")
                .basePath("v2/pet")
                .contentType(ContentType.JSON)
                .body("{\n" +
                        "    \"id\": 3,\n" +
                        "    \"category\": {\n" +
                        "        \"id\": 0,\n" +
                        "        \"name\": \"kitty\"\n" +
                        "    },\n" +
                        "    \"name\": \"King Kong\",\n" +
                        "    \"photoUrls\": [\n" +
                        "        \"string\"\n" +
                        "    ],\n" +
                        "    \"tags\": [\n" +
                        "        {\n" +
                        "            \"id\": 0,\n" +
                        "            \"name\": \"string\"\n" +
                        "        }\n" +
                        "    ],\n" +
                        "    \"status\": \"sold\"\n" +
                        "}")
                .when().put()
                .then()
                .statusCode(200)
                .body("category.name", equalTo("kitty"));
    }

    @Test
    public void getPetDataTest(){
        RestAssured.given()
                .baseUri("https://petstore.swagger.io/")
                .basePath("v2/pet/3")
                .contentType(ContentType.JSON)
                .when().get()
                .then()
                .statusCode(200)
                .extract().response().prettyPrint();
    }
}