package com.devEx.utilities;

import io.restassured.http.ContentType;
import io.restassured.response.Response;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.given;

public class ApiUtils {

    public static Map<String,Object> getAccessToken(String email, String password) {
        Map<String,Object> tokenMap = new HashMap<>();
        tokenMap.put("email",email);
        tokenMap.put("password", password);

        Response response = given().accept(ContentType.JSON)
                .and()
                .contentType(ContentType.JSON)
                .and()
                .body(tokenMap)
                .when()
                .post("/api/auth");

        String token =response.path("token");

        Map<String,Object> authorization = new HashMap<>();
        authorization.put("x-auth-token",token);

        return authorization;
    }
}
