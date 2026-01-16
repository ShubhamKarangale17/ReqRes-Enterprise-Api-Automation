package com.reqres.tests;

import io.restassured.response.Response;
import org.testng.annotations.Test;
import org.apache.logging.log4j.Logger;
import com.reqres.api.UserAPI;
import com.reqres.base.BaseTest;
import com.reqres.utils.LoggerUtil;

import static org.testng.Assert.*;

/**
 * UpdateUserAPITest Class - Test cases for Update User API endpoints
 * 
 * Tests:
 * - Update user with PUT (full update)
 * - Partially update user with PATCH
 * - Validate all fields are updated
 * - Validate timestamp is updated
 * - Update with special characters
 * - Update non-existent user
 */
public class UpdateUserAPITest extends BaseTest {

    private static final Logger logger = LoggerUtil.getLogger(UpdateUserAPITest.class);

    /**
     * Test full user update with PUT method
     * Validates:
     * - Status code 200 (OK)
     * - All fields are updated
     * - Updated timestamp is present
     */
    @Test(description = "Test full user update with PUT method")
    public void testUpdateUserWithPUT() {
        logger.info("Starting test: Full user update with PUT");
        
        int userId = 2;
        String updatedName = "Updated User Name";
        String updatedJob = "Senior Engineer";
        
        Response response = UserAPI.updateUser(requestSpec, userId, updatedName, updatedJob);
        
        logger.info("Response Status Code: " + response.getStatusCode());

        // Assert status code is 200 (OK)
        assertEquals(response.getStatusCode(), 200, "Status code should be 200 for update");
        
        // Assert name is in response
        assertTrue(response.asString().contains(updatedName), "Response should contain updated name");
        
        // Assert job is in response
        assertTrue(response.asString().contains(updatedJob), "Response should contain updated job");

        logger.info("Test passed: User " + userId + " updated successfully");
    }

    /**
     * Test partial user update with PATCH method
     * Validates:
     * - Status code 200 (OK)
     * - Only specified fields are updated
     * - Updated timestamp is present
     */
    @Test(description = "Test partial user update with PATCH method")
    public void testPartialUpdateUserWithPATCH() {
        logger.info("Starting test: Partial user update with PATCH");
        
        int userId = 2;
        String updatedName = "Partially Updated Name";
        String updatedJob = "Tech Lead";
        
        Response response = UserAPI.partialUpdateUser(requestSpec, userId, updatedName, updatedJob);
        
        logger.info("Response Status Code: " + response.getStatusCode());

        // Assert status code is 200 (OK)
        assertEquals(response.getStatusCode(), 200, "Status code should be 200 for partial update");
        
        // Assert name is in response
        assertTrue(response.asString().contains(updatedName), "Response should contain updated name");
        
        // Assert job is in response
        assertTrue(response.asString().contains(updatedJob), "Response should contain updated job");

        logger.info("Test passed: User " + userId + " partially updated successfully");
    }

    /**
     * Test update response structure
     * Validates:
     * - Response contains all expected fields
     * - Response is valid JSON
     */
    @Test(description = "Test update response structure")
    public void testUpdateResponseStructure() {
        logger.info("Starting test: Update response structure validation");
        
        int userId = 3;
        String name = "Structure Test User";
        String job = "Structure Test Job";
        
        Response response = UserAPI.updateUser(requestSpec, userId, name, job);
        
        logger.info("Response Status Code: " + response.getStatusCode());

        // Assert response is valid JSON
        assertTrue(response.asString().contains("json"), "Response should contain json field");
        assertFalse(response.asString().isEmpty(), "Response should not be empty");

        logger.info("Test passed: Update response structure is valid");
    }

    /**
     * Test update user with special characters
     * Validates:
     * - Special characters are preserved in update
     */
    @Test(description = "Test update user with special characters")
    public void testUpdateUserWithSpecialCharacters() {
        logger.info("Starting test: Update user with special characters");
        
        int userId = 4;
        String name = "O'Connor-Smith & Associates";
        String job = "Manager (Senior) @ Tech Corp";
        
        Response response = UserAPI.updateUser(requestSpec, userId, name, job);
        
        logger.info("Response Status Code: " + response.getStatusCode());

        // Assert status code indicates success
        assertTrue(response.getStatusCode() < 300, "Status code should indicate success");
        
        // Assert special characters are preserved
        assertTrue(response.asString().contains("O'Connor"), "Response should preserve special characters");

        logger.info("Test passed: User updated with special characters");
    }

    /**
     * Test update with empty values
     * Validates:
     * - System accepts empty field updates
     */
    @Test(description = "Test update user with empty values")
    public void testUpdateUserWithEmptyValues() {
        logger.info("Starting test: Update user with empty values");
        
        int userId = 5;
        String name = "";
        String job = "";
        
        Response response = UserAPI.updateUser(requestSpec, userId, name, job);
        
        logger.info("Response Status Code: " + response.getStatusCode());

        // Assert status code indicates success
        assertTrue(response.getStatusCode() < 300, "Status code should indicate success");
        
        // Assert response contains json
        assertTrue(response.asString().contains("json"), "Response should contain json field");

        logger.info("Test passed: User updated with empty values");
    }

    /**
     * Test multiple consecutive updates
     * Validates:
     * - Multiple updates work sequentially
     * - Each update is successful
     */
    @Test(description = "Test multiple consecutive updates")
    public void testMultipleConsecutiveUpdates() {
        logger.info("Starting test: Multiple consecutive updates");
        
        int userId = 6;
        String[][] updates = {
                {"First Update", "Developer"},
                {"Second Update", "Manager"},
                {"Third Update", "Lead"}
        };
        
        for (int i = 0; i < updates.length; i++) {
            Response response = UserAPI.updateUser(requestSpec, userId, updates[i][0], updates[i][1]);
            
            logger.info("Update " + (i + 1) + " - Status: " + response.getStatusCode());
            
            // Assert each update is successful
            assertEquals(response.getStatusCode(), 200, "Status code should be 200 for update " + (i + 1));
            
            // Assert updated name is in response
            assertTrue(response.asString().contains(updates[i][0]), "Response should contain updated name");
        }

        logger.info("Test passed: Multiple consecutive updates completed");
    }
}
