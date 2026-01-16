package com.reqres.api;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

/**
 * UserAPI Class - Handles User related API endpoints
 * 
 * EndPoints:
 * - GET /get - Get request (httpbin.org)
 * - POST /post - Post request (httpbin.org)
 * - PUT /put - Put request (httpbin.org)
 * - PATCH /patch - Patch request (httpbin.org)
 * - DELETE /delete - Delete request (httpbin.org)
 */
public class UserAPI {

    /**
     * Get all users with pagination
     * 
     * @param requestSpec RequestSpecification object
     * @param page Page number (default 1)
     * @return Response object
     */
    public static Response getAllUsers(RequestSpecification requestSpec, int page) {
        return RestAssured
                .given(requestSpec)
                .queryParam("page", page)
                .get("/get");
    }

    /**
     * Get all users from first page
     * 
     * @param requestSpec RequestSpecification object
     * @return Response object
     */
    public static Response getAllUsers(RequestSpecification requestSpec) {
        return RestAssured
                .given(requestSpec)
                .get("/get");
    }

    /**
     * Get user by ID
     * 
     * @param requestSpec RequestSpecification object
     * @param userId User ID
     * @return Response object
     */
    public static Response getUserById(RequestSpecification requestSpec, int userId) {
        return RestAssured
                .given(requestSpec)
                .queryParam("id", userId)
                .get("/get");
    }

    /**
     * Get non-existent user (testing error handling)
     * 
     * @param requestSpec RequestSpecification object
     * @param userId Non-existent user ID
     * @return Response object
     */
    public static Response getNonExistentUser(RequestSpecification requestSpec, int userId) {
        return RestAssured
                .given(requestSpec)
                .queryParam("id", userId)
                .get("/get");
    }

    /**
     * Create new user
     * 
     * @param requestSpec RequestSpecification object
     * @param name User name
     * @param job User job
     * @return Response object
     */
    public static Response createUser(RequestSpecification requestSpec, String name, String job) {
        String requestBody = String.format("{\n" +
                "  \"name\": \"%s\",\n" +
                "  \"job\": \"%s\"\n" +
                "}", name, job);

        return RestAssured
                .given(requestSpec)
                .body(requestBody)
                .post("/post");
    }

    /**
     * Update user with PUT request
     * 
     * @param requestSpec RequestSpecification object
     * @param userId User ID to update
     * @param name Updated user name
     * @param job Updated user job
     * @return Response object
     */
    public static Response updateUser(RequestSpecification requestSpec, int userId, String name, String job) {
        String requestBody = String.format("{\n" +
                "  \"id\": %d,\n" +
                "  \"name\": \"%s\",\n" +
                "  \"job\": \"%s\"\n" +
                "}", userId, name, job);

        return RestAssured
                .given(requestSpec)
                .body(requestBody)
                .put("/put");
    }

    /**
     * Partially update user with PATCH request
     * 
     * @param requestSpec RequestSpecification object
     * @param userId User ID to update
     * @param name Updated user name
     * @param job Updated user job
     * @return Response object
     */
    public static Response partialUpdateUser(RequestSpecification requestSpec, int userId, String name, String job) {
        String requestBody = String.format("{\n" +
                "  \"id\": %d,\n" +
                "  \"name\": \"%s\",\n" +
                "  \"job\": \"%s\"\n" +
                "}", userId, name, job);

        return RestAssured
                .given(requestSpec)
                .body(requestBody)
                .patch("/patch");
    }

    /**
     * Delete user
     * 
     * @param requestSpec RequestSpecification object
     * @param userId User ID to delete
     * @return Response object
     */
    public static Response deleteUser(RequestSpecification requestSpec, int userId) {
        return RestAssured
                .given(requestSpec)
                .queryParam("id", userId)
                .delete("/delete");
    }
}
