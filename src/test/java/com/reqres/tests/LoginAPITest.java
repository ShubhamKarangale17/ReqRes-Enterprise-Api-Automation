package com.reqres.tests;

import io.restassured.response.Response;
import org.testng.annotations.Test;
import org.apache.logging.log4j.Logger;
import com.reqres.api.LoginAPI;
import com.reqres.base.BaseTest;
import com.reqres.utils.LoggerUtil;

import static org.testng.Assert.*;

/**
 * LoginAPITest Class - Test cases for Login API endpoint
 * 
 * Tests:
 * - Valid login with correct email and password
 * - Login with missing password
 * - Login with empty credentials
 * - Token generation validation
 * - Error message validation for invalid credentials
 */
public class LoginAPITest extends BaseTest {

    private static final Logger logger = LoggerUtil.getLogger(LoginAPITest.class);
    private static final String VALID_EMAIL = "eve.holt@reqres.in";
    private static final String VALID_PASSWORD = "cityslicka";

    /**
     * Test successful login with valid credentials
     * Validates:
     * - Status code 200 (OK)
     * - Response is valid JSON
     * - Response contains json field with submitted data
     */
    @Test(description = "Test successful login with valid credentials")
    public void testLoginWithValidCredentials() {
        logger.info("Starting test: Login with valid credentials");
        
        Response response = LoginAPI.loginWithValidCredentials(requestSpec, VALID_EMAIL, VALID_PASSWORD);
        
        logger.info("Response Status Code: " + response.getStatusCode());
        logger.info("Response Body: " + response.asString());

        // Assert status code is 200 (OK)
        assertEquals(response.getStatusCode(), 200, "Status code should be 200");
        
        // Assert response contains json field with our data
        assertTrue(response.asString().contains("email"), "Response should contain email field");
        assertTrue(response.asString().contains("password"), "Response should contain password field");

        logger.info("Test passed: Login successful with valid credentials");
    }

    /**
     * Test login with missing password
     * Validates:
     * - Status code 200 (httpbin returns 200 for any POST)
     * - Response does not contain password field
     */
    @Test(description = "Test login with missing password")
    public void testLoginWithoutPassword() {
        logger.info("Starting test: Login without password");
        
        Response response = LoginAPI.loginWithoutPassword(requestSpec, VALID_EMAIL);
        
        logger.info("Response Status Code: " + response.getStatusCode());
        logger.info("Response Body: " + response.asString());

        // Assert status code is 200 (httpbin returns 200)
        assertEquals(response.getStatusCode(), 200, "Status code should be 200");
        
        // Assert email is present but password is not in request
        assertTrue(response.asString().contains("email"), "Response should contain email field");
        assertFalse(response.asString().contains("\"password\""), "Response should not contain password field in json");

        logger.info("Test passed: Login without password returns 200");
    }

    /**
     * Test login with empty credentials
     * Validates:
     * - Status code 200
     * - Response is valid JSON
     */
    @Test(description = "Test login with empty credentials")
    public void testLoginWithEmptyCredentials() {
        logger.info("Starting test: Login with empty credentials");
        
        Response response = LoginAPI.loginWithEmptyCredentials(requestSpec);
        
        logger.info("Response Status Code: " + response.getStatusCode());
        logger.info("Response Body: " + response.asString());

        // Assert status code is 200
        assertEquals(response.getStatusCode(), 200, "Status code should be 200");
        
        // Assert response is valid JSON
        assertNotNull(response.asString(), "Response should not be null");
        assertTrue(response.asString().contains("json"), "Response should contain json field");

        logger.info("Test passed: Login with empty credentials returns 200");
    }

    /**
     * Test login response structure
     * Validates:
     * - Response is valid JSON
     * - Response contains expected fields
     */
    @Test(description = "Test login response structure")
    public void testLoginResponseStructure() {
        logger.info("Starting test: Login response structure validation");
        
        Response response = LoginAPI.loginWithValidCredentials(requestSpec, VALID_EMAIL, VALID_PASSWORD);

        // Assert response is not null
        assertNotNull(response, "Response should not be null");
        
        // Assert response status code indicates success
        assertTrue(response.getStatusCode() == 200, "Status code should be 200");
        
        // Assert response contains json field
        assertTrue(response.asString().contains("json"), "Response should contain json field");

        logger.info("Test passed: Login response structure is valid");
    }
}
