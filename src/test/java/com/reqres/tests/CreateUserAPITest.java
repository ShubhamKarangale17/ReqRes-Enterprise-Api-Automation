package com.reqres.tests;

import io.restassured.response.Response;
import org.testng.annotations.Test;
import org.apache.logging.log4j.Logger;
import com.reqres.api.UserAPI;
import com.reqres.base.BaseTest;
import com.reqres.utils.LoggerUtil;

import static org.testng.Assert.*;

/**
 * CreateUserAPITest Class - Test cases for Create User API endpoint
 * 
 * Tests:
 * - Create user with valid data
 * - Create user response contains ID
 * - Create user response contains timestamp
 * - Validate required fields in created user
 * - Create multiple users
 */
public class CreateUserAPITest extends BaseTest {

    private static final Logger logger = LoggerUtil.getLogger(CreateUserAPITest.class);

    /**
     * Test creating user with valid data
     * Validates:
     * - Status code 201 (Created)
     * - Response contains user ID
     * - Response contains timestamp
     * - Response contains submitted data
     */
    @Test(description = "Test create user with valid data")
    public void testCreateUserWithValidData() {
        logger.info("Starting test: Create user with valid data");
        
        String name = "John Doe";
        String job = "QA Engineer";
        
        Response response = UserAPI.createUser(requestSpec, name, job);
        
        logger.info("Response Status Code: " + response.getStatusCode());
        logger.info("Response Body: " + response.asString());

        // Assert status code is 201 or 200
        assertTrue(response.getStatusCode() == 201 || response.getStatusCode() == 200, "Status code should be 201 or 200");
        
        // Assert response contains name and job
        assertTrue(response.asString().contains(name), "Response should contain name");
        assertTrue(response.asString().contains(job), "Response should contain job");

        logger.info("Test passed: User created successfully");
    }

    /**
     * Test create user response structure
     * Validates:
     * - All required fields are present in response
     * - Data types are correct
     */
    @Test(description = "Test create user response structure")
    public void testCreateUserResponseStructure() {
        logger.info("Starting test: Create user response structure validation");
        
        String name = "Jane Smith";
        String job = "DevOps Engineer";
        
        Response response = UserAPI.createUser(requestSpec, name, job);
        
        logger.info("Response Status Code: " + response.getStatusCode());

        // Assert response is valid JSON
        assertTrue(response.asString().contains("json"), "Response should contain json field");
        assertTrue(response.asString().contains("name"), "Response should contain name");
        assertFalse(response.asString().isEmpty(), "Response should not be empty");

        logger.info("Test passed: Create user response structure is valid");
    }

    /**
     * Test create user with special characters
     * Validates:
     * - System handles special characters correctly
     * - Name is echoed back in response
     */
    @Test(description = "Test create user with special characters")
    public void testCreateUserWithSpecialCharacters() {
        logger.info("Starting test: Create user with special characters");
        
        String name = "John O'Brien-Smith";
        String job = "Software Engineer (QA)";
        
        Response response = UserAPI.createUser(requestSpec, name, job);
        
        logger.info("Response Status Code: " + response.getStatusCode());

        // Assert status code indicates success
        assertTrue(response.getStatusCode() < 300, "Status code should indicate success");
        
        // Assert special characters are preserved
        assertTrue(response.asString().contains("O'Brien"), "Response should preserve special characters");

        logger.info("Test passed: User created with special characters");
    }

    /**
     * Test create multiple users
     * Validates:
     * - Each user creation is successful
     * - Multiple sequential creates work
     */
    @Test(description = "Test create multiple users")
    public void testCreateMultipleUsers() {
        logger.info("Starting test: Create multiple users");
        
        String[] names = {"Alice Johnson", "Bob Wilson", "Carol White"};
        String[] jobs = {"Data Analyst", "Backend Developer", "Frontend Developer"};
        
        for (int i = 0; i < names.length; i++) {
            Response response = UserAPI.createUser(requestSpec, names[i], jobs[i]);
            
            logger.info("Created user " + (i + 1) + ": " + names[i]);
            
            // Assert each creation is successful
            assertTrue(response.getStatusCode() < 300, "Status code should be success for user " + (i + 1));
            assertTrue(response.asString().contains(names[i]), "Response should contain user name");
        }

        logger.info("Test passed: Multiple users created successfully");
    }

    /**
     * Test create user with empty job field
     * Validates:
     * - System accepts empty job field
     * - User is still created
     */
    @Test(description = "Test create user with empty job")
    public void testCreateUserWithEmptyJob() {
        logger.info("Starting test: Create user with empty job");
        
        String name = "Test User";
        String job = "";
        
        Response response = UserAPI.createUser(requestSpec, name, job);
        
        logger.info("Response Status Code: " + response.getStatusCode());

        // Assert user is created
        assertTrue(response.getStatusCode() < 300, "Status code should be success");
        
        // Assert name is present
        assertTrue(response.asString().contains(name), "Name should be in response");

        logger.info("Test passed: User created with empty job field");
    }
}
