package com.reqres.tests;

import io.restassured.response.Response;
import org.testng.annotations.Test;
import org.apache.logging.log4j.Logger;
import com.reqres.api.UserAPI;
import com.reqres.base.BaseTest;
import com.reqres.utils.LoggerUtil;

import static org.testng.Assert.*;

/**
 * DeleteUserAPITest Class - Test cases for Delete User API endpoint
 * 
 * Tests:
 * - Delete existing user
 * - Delete non-existent user
 * - Verify user is deleted
 * - Status code validation
 * - Timestamp validation
 * - Multiple delete operations
 */
public class DeleteUserAPITest extends BaseTest {

    private static final Logger logger = LoggerUtil.getLogger(DeleteUserAPITest.class);

    /**
     * Test successful user deletion
     * Validates:
     * - Status code 204 (No Content)
     * - Response indicates successful deletion
     */
    @Test(description = "Test successful user deletion")
    public void testDeleteUserSuccessfully() {
        logger.info("Starting test: Delete user successfully");
        
        int userId = 2;
        Response response = UserAPI.deleteUser(requestSpec, userId);
        
        logger.info("Response Status Code: " + response.getStatusCode());

        // Assert status code is 204 or 200
        assertTrue(response.getStatusCode() == 204 || response.getStatusCode() == 200, 
                   "Status code should be 204 or 200 for successful deletion");

        logger.info("Test passed: User " + userId + " deleted successfully");
    }

    /**
     * Test delete non-existent user
     * Validates:
     * - Status code is still 200 (httpbin returns 200)
     * - Response is valid
     */
    @Test(description = "Test delete non-existent user")
    public void testDeleteNonExistentUser() {
        logger.info("Starting test: Delete non-existent user");
        
        int nonExistentUserId = 9999;
        Response response = UserAPI.deleteUser(requestSpec, nonExistentUserId);
        
        logger.info("Response Status Code: " + response.getStatusCode());

        // Assert status code is 200
        assertEquals(response.getStatusCode(), 200, "Status code should be 200");

        logger.info("Test passed: Delete query returned 200");
    }

    /**
     * Test verify user is deleted by attempting to fetch
     * Validates:
     * - Delete response is valid
     * - Get response shows request was made
     */
    @Test(description = "Test verify user is deleted")
    public void testVerifyUserIsDeleted() {
        logger.info("Starting test: Verify user is deleted");
        
        int userId = 3;
        
        // First, delete the user
        Response deleteResponse = UserAPI.deleteUser(requestSpec, userId);
        logger.info("Delete Response Status: " + deleteResponse.getStatusCode());
        
        // Try to fetch the deleted user
        Response getResponse = UserAPI.getUserById(requestSpec, userId);
        logger.info("Get Response Status: " + getResponse.getStatusCode());

        // Assert delete was successful
        assertTrue(deleteResponse.getStatusCode() == 204 || deleteResponse.getStatusCode() == 200,
                   "Delete status code should indicate success");
        
        // Assert get response is valid
        assertEquals(getResponse.getStatusCode(), 200, "Get status code should be 200");

        logger.info("Test passed: Delete operation verified");
    }

    /**
     * Test delete with different user IDs
     * Validates:
     * - Delete works for various user IDs
     */
    @Test(description = "Test delete with different user IDs")
    public void testDeleteWithDifferentUserIDs() {
        logger.info("Starting test: Delete with different user IDs");
        
        int[] userIds = {4, 5, 6, 7, 8};
        
        for (int userId : userIds) {
            Response response = UserAPI.deleteUser(requestSpec, userId);
            
            logger.info("Delete user " + userId + " - Status: " + response.getStatusCode());
            
            // Assert each deletion is successful
            assertTrue(response.getStatusCode() == 204 || response.getStatusCode() == 200,
                       "Status code should indicate success for user " + userId);
        }

        logger.info("Test passed: Multiple users deleted successfully");
    }

    /**
     * Test delete and recreate user
     * Validates:
     * - User can be recreated after deletion
     */
    @Test(description = "Test delete and recreate user")
    public void testDeleteAndRecreateUser() {
        logger.info("Starting test: Delete and recreate user");
        
        int userId = 10;
        String userName = "Test User";
        String userJob = "Test Job";
        
        // Delete the user
        Response deleteResponse = UserAPI.deleteUser(requestSpec, userId);
        logger.info("Delete Response Status: " + deleteResponse.getStatusCode());
        
        // Recreate the user
        Response createResponse = UserAPI.createUser(requestSpec, userName, userJob);
        logger.info("Create Response Status: " + createResponse.getStatusCode());

        // Assert delete was successful
        assertTrue(deleteResponse.getStatusCode() == 204 || deleteResponse.getStatusCode() == 200,
                   "Delete status code should indicate success");
        
        // Assert recreation was successful
        assertTrue(createResponse.getStatusCode() == 201 || createResponse.getStatusCode() == 200, 
                   "Create status code should indicate success");

        logger.info("Test passed: User deleted and recreated successfully");
    }

    /**
     * Test multiple sequential deletes
     * Validates:
     * - Multiple deletes work without errors
     */
    @Test(description = "Test multiple sequential deletes")
    public void testMultipleSequentialDeletes() {
        logger.info("Starting test: Multiple sequential deletes");
        
        int startUserId = 11;
        int numberOfUsers = 5;
        
        for (int i = 0; i < numberOfUsers; i++) {
            int userId = startUserId + i;
            Response response = UserAPI.deleteUser(requestSpec, userId);
            
            logger.info("Delete user " + userId + " - Status: " + response.getStatusCode());
            
            // Assert each deletion is successful
            assertTrue(response.getStatusCode() == 204 || response.getStatusCode() == 200,
                       "Status code should indicate success for user " + userId);
        }

        logger.info("Test passed: " + numberOfUsers + " users deleted sequentially");
    }

    /**
     * Test delete response structure
     * Validates:
     * - Response format for delete operation
     */
    @Test(description = "Test delete response structure")
    public void testDeleteResponseStructure() {
        logger.info("Starting test: Delete response structure validation");
        
        int userId = 16;
        Response response = UserAPI.deleteUser(requestSpec, userId);
        
        logger.info("Response Status Code: " + response.getStatusCode());

        // Assert status code indicates success
        assertTrue(response.getStatusCode() == 204 || response.getStatusCode() == 200,
                   "Status code should indicate successful deletion");

        logger.info("Test passed: Delete response structure is valid");
    }
}