package api.endpoints;

import api.payload.User;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

import static io.restassured.RestAssured.given;

/*
* UserEndPoints.java
* It is for performing CRUD
*
*/
public class UserEndPoints {

    public static Response createUser(User payload)
    {
        Response response=given()
            .contentType(ContentType.JSON)
            .accept(ContentType.JSON)
            .body(payload)
        .when()
                .post(Routes.postUrl);
        return response;
    }

    public static Response getUser(String userName)
    {
        Response response=given()
                .pathParam("username",userName)
                .when()
                .get(Routes.getUrl);
        return response;
    }

    public static Response updateUser(String userName, User payload)
    {
        Response response=given()
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON)
                .pathParam("username", userName)
                .body(payload)
                .when()
                .put(Routes.updateUrl);
        return response;
    }

    public static Response deleteUser(String userName)
    {
        Response response=given()
                .pathParam("username", userName)
                .when()
                .get(Routes.deleteUrl);
        return response;
    }

    public static Response getDogByBreed(String breed)
    {
        Response response = given()
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON)
                .pathParam("breed", breed)
                .when()
                .get(Routes.getDogUrl);
        return  response;
    }

    public static Response getDogBySubBreed(String sub)
    {
        Response response = given()
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON)
                .pathParam("sub",sub)
                .when()
                .get(Routes.getSubUrl);
        return response;
    }

    public static Response getRandomDogImage(String random)
    {
        Response response = given()
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON)
                .pathParam("imasge",random)
                .when()
                .get(Routes.getRandomDogUrl);
        return response;
    }
}
