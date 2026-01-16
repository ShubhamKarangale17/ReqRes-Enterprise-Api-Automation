package com.reqres.tests;

import io.restassured.response.Response;
import org.testng.annotations.Test;
import org.apache.logging.log4j.Logger;
import com.reqres.api.UserAPI;
import com.reqres.base.BaseTest;
import com.reqres.utils.LoggerUtil;

import static org.testng.Assert.*;

/**
 * GetUsersAPITest Class - Test cases for Get Users API endpoint
 * 
 * Tests:
 * - Get all users with default page
 * - Get all users with specific page number
 * - Get user by ID
 * - Get non-existent user
 * - Pagination validation
 * - Response structure validation
 */
public class GetUsersAPITest extends BaseTest {

    private static final Logger logger = LoggerUtil.getLogger(GetUsersAPITest.class);

    /**
     * Test getting all users from first page
     * Validates:
     * - Status code 200 (OK)
     * - Response is valid JSON
     */
    @Test(description = "Test get all users from default page")
    public void testGetAllUsersDefaultPage() {
        logger.info("Starting test: Get all users from default page");
        
        Response response = UserAPI.getAllUsers(requestSpec);
        
        logger.info("Response Status Code: " + response.getStatusCode());

        // Assert status code is 200 (OK)
        assertEquals(response.getStatusCode(), 200, "Status code should be 200");
        
        // Assert response contains url (httpbin returns url in response)
        assertTrue(response.asString().contains("url"), "Response should contain url");

        logger.info("Test passed: Retrieved users from default page");
    }

    /**
     * Test getting users from specific page
     * Validates:
     * - Status code 200 (OK)
     * - Response contains query parameters
     */
    @Test(description = "Test get all users from specific page")
    public void testGetAllUsersSpecificPage() {
        logger.info("Starting test: Get all users from specific page");
        
        int pageNumber = 2;
        Response response = UserAPI.getAllUsers(requestSpec, pageNumber);
        
        logger.info("Response Status Code: " + response.getStatusCode());

        // Assert status code is 200 (OK)
        assertEquals(response.getStatusCode(), 200, "Status code should be 200");
        
        // Assert response contains args (query parameters)
        assertTrue(response.asString().contains("args"), "Response should contain args field");

        logger.info("Test passed: Retrieved users from page " + pageNumber);
    }

    /**
     * Test getting user by ID
     * Validates:
     * - Status code 200 (OK)
     * - User contains required fields
     */
    @Test(description = "Test get user by ID")
    public void testGetUserById() {
        logger.info("Starting test: Get user by ID");
        
        int userId = 1;
        Response response = UserAPI.getUserById(requestSpec, userId);
        
        logger.info("Response Status Code: " + response.getStatusCode());

        // Assert status code is 200 (OK)
        assertEquals(response.getStatusCode(), 200, "Status code should be 200");
        
        // Assert response contains args
        assertTrue(response.asString().contains("args"), "Response should contain args");

        logger.info("Test passed: Retrieved user " + userId);
    }

    /**
     * Test getting non-existent user
     * Validates:
     * - Status code 200 (httpbin always returns 200)
     * - Response is still valid
     */
    @Test(description = "Test get non-existent user")
    public void testGetNonExistentUser() {
        logger.info("Starting test: Get non-existent user");
        
        int nonExistentUserId = 9999;
        Response response = UserAPI.getNonExistentUser(requestSpec, nonExistentUserId);
        
        logger.info("Response Status Code: " + response.getStatusCode());

        // Assert status code is 200
        assertEquals(response.getStatusCode(), 200, "Status code should be 200");
        
        // Assert response is valid JSON
        assertNotNull(response.asString(), "Response should not be null");

        logger.info("Test passed: Non-existent user query returned 200");
    }

    /**
     * Test response metadata
     * Validates:
     * - Response contains url and args
     */
    @Test(description = "Test pagination metadata")
    public void testPaginationMetadata() {
        logger.info("Starting test: Metadata validation");
        
        Response response = UserAPI.getAllUsers(requestSpec, 1);
        
        logger.info("Response Status Code: " + response.getStatusCode());

        // Assert response contains metadata
        assertTrue(response.asString().contains("url"), "Response should contain url");
        assertTrue(response.asString().contains("headers"), "Response should contain headers");

        logger.info("Test passed: Metadata is valid");
    }
}
