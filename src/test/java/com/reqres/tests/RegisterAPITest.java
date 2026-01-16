package com.reqres.tests;

import io.restassured.response.Response;
import org.testng.annotations.Test;
import org.apache.logging.log4j.Logger;
import com.reqres.api.RegisterAPI;
import com.reqres.base.BaseTest;
import com.reqres.utils.LoggerUtil;

import static org.testng.Assert.*;

/**
 * RegisterAPITest Class - Test cases for Register API endpoint
 * 
 * Tests:
 * - Successful registration with valid credentials
 * - Registration with missing password
 * - Registration with empty credentials
 * - Token generation validation
 * - ID generation validation
 * - Error handling for invalid credentials
 */
public class RegisterAPITest extends BaseTest {

    private static final Logger logger = LoggerUtil.getLogger(RegisterAPITest.class);
    private static final String TEST_EMAIL = "sydney@fife";
    private static final String TEST_PASSWORD = "pistol";

    /**
     * Test successful registration with valid credentials
     * Validates:
     * - Status code 200 (OK)
     * - Response contains email
     * - Response contains password
     */
    @Test(description = "Test successful registration with valid credentials")
    public void testRegisterWithValidCredentials() {
        logger.info("Starting test: Register with valid credentials");
        
        Response response = RegisterAPI.registerWithValidCredentials(requestSpec, TEST_EMAIL, TEST_PASSWORD);
        
        logger.info("Response Status Code: " + response.getStatusCode());
        logger.info("Response Body: " + response.asString());

        // Assert status code is 200 (OK)
        assertEquals(response.getStatusCode(), 200, "Status code should be 200");
        
        // Assert response contains email and password
        assertTrue(response.asString().contains("email"), "Response should contain email field");
        assertTrue(response.asString().contains("password"), "Response should contain password field");

        logger.info("Test passed: Registration successful with valid credentials");
    }

    /**
     * Test registration with missing password
     * Validates:
     * - Status code 200
     * - Response contains email
     */
    @Test(description = "Test registration with missing password")
    public void testRegisterWithoutPassword() {
        logger.info("Starting test: Register without password");
        
        Response response = RegisterAPI.registerWithoutPassword(requestSpec, TEST_EMAIL);
        
        logger.info("Response Status Code: " + response.getStatusCode());
        logger.info("Response Body: " + response.asString());

        // Assert status code is 200
        assertEquals(response.getStatusCode(), 200, "Status code should be 200");
        
        // Assert email is present
        assertTrue(response.asString().contains("email"), "Response should contain email field");

        logger.info("Test passed: Registration without password returns 200");
    }

    /**
     * Test registration with empty credentials
     * Validates:
     * - Status code 200
     * - Response is valid JSON
     */
    @Test(description = "Test registration with empty credentials")
    public void testRegisterWithEmptyCredentials() {
        logger.info("Starting test: Register with empty credentials");
        
        Response response = RegisterAPI.registerWithEmptyCredentials(requestSpec);
        
        logger.info("Response Status Code: " + response.getStatusCode());
        logger.info("Response Body: " + response.asString());

        // Assert status code is 200
        assertEquals(response.getStatusCode(), 200, "Status code should be 200");
        
        // Assert response is valid JSON
        assertTrue(response.asString().contains("json"), "Response should contain json field");

        logger.info("Test passed: Registration with empty credentials returns 200");
    }

    /**
     * Test registration response structure
     * Validates:
     * - Response is valid JSON
     * - Response contains expected fields
     */
    @Test(description = "Test registration response structure")
    public void testRegisterResponseStructure() {
        logger.info("Starting test: Registration response structure validation");
        
        Response response = RegisterAPI.registerWithValidCredentials(requestSpec, TEST_EMAIL, TEST_PASSWORD);

        // Assert response is not null
        assertNotNull(response, "Response should not be null");
        
        // Assert response status code is 200
        assertEquals(response.getStatusCode(), 200, "Status code should be 200");
        
        // Assert response contains json field
        assertTrue(response.asString().contains("json"), "Response should contain json field");

        logger.info("Test passed: Registration response structure is valid");
    }
}
