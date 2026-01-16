package com.reqres.api;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.json.simple.JSONObject;

/**
 * RegisterAPI Class - Handles Register API endpoints
 * 
 * EndPoints:
 * - POST /post - Mock register endpoint (using httpbin.org)
 */
public class RegisterAPI {

    /**
     * Register with valid email and password
     * 
     * @param requestSpec RequestSpecification object
     * @param email User email
     * @param password User password
     * @return Response object
     */
    public static Response registerWithValidCredentials(RequestSpecification requestSpec, String email, String password) {
        JSONObject requestBody = new JSONObject();
        requestBody.put("email", email);
        requestBody.put("password", password);

        return RestAssured
                .given(requestSpec)
                .body(requestBody.toJSONString())
                .post("/post");
    }

    /**
     * Register with only email (missing password)
     * 
     * @param requestSpec RequestSpecification object
     * @param email User email
     * @return Response object
     */
    public static Response registerWithoutPassword(RequestSpecification requestSpec, String email) {
        JSONObject requestBody = new JSONObject();
        requestBody.put("email", email);

        return RestAssured
                .given(requestSpec)
                .body(requestBody.toJSONString())
                .post("/post");
    }

    /**
     * Register with empty credentials
     * 
     * @param requestSpec RequestSpecification object
     * @return Response object
     */
    public static Response registerWithEmptyCredentials(RequestSpecification requestSpec) {
        JSONObject requestBody = new JSONObject();

        return RestAssured
                .given(requestSpec)
                .body(requestBody.toJSONString())
                .post("/post");
    }
}
